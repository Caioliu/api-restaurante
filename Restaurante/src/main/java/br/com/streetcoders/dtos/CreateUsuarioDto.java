package br.com.streetcoders.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.streetcoders.enums.UsuarioRole;
import br.com.streetcoders.models.Usuario.UsuarioSetor;
import lombok.Data;

@Data
public class CreateUsuarioDto {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	private String nome;
	private String username;
	private String password;
	private UsuarioRole role;
	private UsuarioSetor setor;
}