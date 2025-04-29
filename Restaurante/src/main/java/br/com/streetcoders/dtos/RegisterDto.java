package br.com.streetcoders.dtos;

import br.com.streetcoders.enums.UsuarioRole;

import br.com.streetcoders.models.Usuario.UsuarioSetor;
import lombok.Data;

@Data
public class RegisterDto {
	private String login;
	private String password;
	private UsuarioRole role;
	private UsuarioSetor setor;

}
