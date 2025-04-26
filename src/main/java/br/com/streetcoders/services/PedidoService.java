package br.com.streetcoders.services;

import java.math.BigDecimal;


import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.streetcoders.dtos.ListPedidoDto;
import br.com.streetcoders.dtos.PedidoDto;
import br.com.streetcoders.mappers.IPedidoMapper;
import br.com.streetcoders.models.Pedido;
import br.com.streetcoders.models.Produto;
import br.com.streetcoders.models.Pedido.PedidoStatus;
import br.com.streetcoders.repositories.IPedidoRepository;
import br.com.streetcoders.repositories.IProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class PedidoService {

    private final IPedidoRepository pedidoRepository;
    private final IProdutoRepository produtoRepository;
    private final IPedidoMapper pedidoMapper;

    public PedidoDto buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        PedidoDto dto = pedidoMapper.toDto(pedido);
        dto.setValorTotal(pedido.calcularValorTotalPedido());
        return dto;
    }

    public List<ListPedidoDto> listarTodosPedidos() {
        return pedidoMapper.toListDto(pedidoRepository.findAll());
    }
    
    public PedidoDto criarPedido(String clienteNome, String mesa) {
        Pedido pedido = Pedido.builder()
                .clienteNome(clienteNome)
                .mesa(mesa)
                .status(PedidoStatus.ABERTO)
                .dataHora(new Date())
                .itens(new HashSet<>())
                .build();
        PedidoDto dto = pedidoMapper.toDto(pedidoRepository.save(pedido));
        return dto;
    }

    public void adicionarProdutoAoPedido(Long pedidoId, Long produtoId, int quantidade) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        Produto produto = produtoRepository.findById(produtoId).orElseThrow();
        pedido.adicionarProduto(produto, quantidade);
        pedidoRepository.save(pedido);
    }
    
    public void atualizarStatusPedido(Long pedidoId, PedidoStatus novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        pedido.setStatus(novoStatus);
        pedidoRepository.save(pedido);
    }

    public void removerProdutoDoPedido(Long pedidoId, Long produtoId, int quantidade) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        Produto produto = produtoRepository.findById(produtoId).orElseThrow();
        pedido.removerProduto(produto, quantidade);
        pedidoRepository.save(pedido);
    }

    public void removerTodosProdutosDoPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        pedido.removerTodosProdutos();
        pedidoRepository.save(pedido);
    }

    public BigDecimal calcularValorTotalPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow();
        return pedido.calcularValorTotalPedido();
    }

}
