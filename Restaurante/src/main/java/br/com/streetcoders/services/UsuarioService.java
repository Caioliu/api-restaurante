package br.com.streetcoders.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.streetcoders.dtos.UsuarioDto;
import br.com.streetcoders.enums.UsuarioRole;
import br.com.streetcoders.mappers.IUsuarioMapper;
import br.com.streetcoders.models.Usuario;
import br.com.streetcoders.models.Usuario.UsuarioSetor;
import br.com.streetcoders.repositories.IUsuarioRepository;

@Service
public class UsuarioService {
	
	private final IUsuarioRepository usuarioRepository;
	private final IUsuarioMapper usuarioMapper;
	
	public UsuarioService(IUsuarioRepository usuarioRepository, IUsuarioMapper usuarioMapper) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioMapper = usuarioMapper;
	}
	
	public Iterable<UsuarioDto> listarTodosUsuarios() {
		return usuarioMapper.toDtoList(usuarioRepository.findAll());
	}
	
	public UsuarioDto buscarUsuarioPorId(Long id) {
		return usuarioMapper.toDto(usuarioRepository.findById(id).orElseThrow());
	}
	
	public UserDetails buscarUsuarioPorUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}
	
	public UsuarioDto criarUsuario(String nome, String username, String password, UsuarioRole role, UsuarioSetor setor) {
		Usuario usuario = Usuario.builder()
				.nome(nome)
				.username(username)
				.passwordHash(password)
				.role(role)
				.setor(setor)
				.build();
		
		return usuarioMapper.toDto(usuarioRepository.save(usuario));
	}
	
	public UsuarioDto atualizarUsuario(Long id, String nome, String username, UsuarioRole role, UsuarioSetor setor) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow();
		usuario.setNome(nome);
		usuario.setUsername(username);
		usuario.setRole(role);
		usuario.setSetor(setor);
		
		return usuarioMapper.toDto(usuarioRepository.save(usuario));
	}
	
	public void removerUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
}
