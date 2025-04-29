package br.com.streetcoders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.streetcoders.dtos.AuthDto;
import br.com.streetcoders.dtos.LoginResponseDto;
import br.com.streetcoders.models.Usuario;
import br.com.streetcoders.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Gerenciamento de autenticação")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	@Operation(
			summary = "Realizar login",
			responses = {
				@ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
				@ApiResponse(responseCode = "401", description = "Credenciais inválidas")
			}
	)
	public ResponseEntity<LoginResponseDto> login(@RequestBody AuthDto loginDto) {
		// Autenticar o usuário
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.password()));
		
		// Gerar o token JWT
		String token = tokenService.generateToken((Usuario) authentication.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDto(token));
	}
}
