package br.com.streetcoders.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.streetcoders.dtos.CreateUsuarioDto;
import br.com.streetcoders.dtos.UsuarioDto;
import br.com.streetcoders.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuariosController {

    private final UsuarioService usuarioService;

    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar usuário por ID",
            responses = {
                @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
                @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os usuários",
            responses = {
                @ApiResponse(responseCode = "200", description = "Lista de usuários retornada")
            }
    )
    public ResponseEntity<List<UsuarioDto>> listarTodos() {
        return ResponseEntity.ok((List<UsuarioDto>) usuarioService.listarTodosUsuarios());
    }
    
    @PostMapping
    @Operation(
			summary = "Criar novo usuário",
			responses = {
				@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
				@ApiResponse(responseCode = "400", description = "Erro ao criar usuário")
			}
	)
    public ResponseEntity<UsuarioDto> criar(
			@RequestBody CreateUsuarioDto dto) {
    	if (this.usuarioService.buscarUsuarioPorUsername(dto.getUsername()) != null) {
			return ResponseEntity.badRequest().build();
		}
    	
	    String encryptedPass = new BCryptPasswordEncoder().encode(dto.getPassword());
    	
		UsuarioDto usuarioCriado = usuarioService.criarUsuario(dto.getNome(),
				dto.getUsername(),
				encryptedPass,
				dto.getRole(),
				dto.getSetor());
		return ResponseEntity.ok(usuarioCriado);
	}

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar usuário por ID",
            responses = {
                @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
                @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    public ResponseEntity<UsuarioDto> atualizar(
            @PathVariable Long id,
            @RequestBody UsuarioDto dto) {
        UsuarioDto usuarioAtualizado = usuarioService.atualizarUsuario(id,
        		dto.getNome(),
        		dto.getUsername(),
        		dto.getRole(),
        		dto.getSetor());
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Remover usuário por ID",
            responses = {
                @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
                @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        usuarioService.removerUsuario(id);
        return ResponseEntity.noContent().build();
    }
}