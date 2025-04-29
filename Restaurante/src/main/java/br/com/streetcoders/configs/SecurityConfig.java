package br.com.streetcoders.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.streetcoders.security.SecurityFilter;
import br.com.streetcoders.services.IdentityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	SecurityFilter securityFilter;
	@Autowired
	IdentityService identityService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	            .authenticationProvider(authenticationProvider())
	            .csrf(csrf -> csrf.disable())
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authorizeHttpRequests(authorize -> authorize
	                    // LIBERA SWAGGER
	                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

	                    // SEM ROLE NECESSÁRIA
	                    .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
	                    .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()

	                    // CLIENTE
	                    .requestMatchers(HttpMethod.GET, "/api/produtos").hasRole("CLIENTE")
	                    .requestMatchers(HttpMethod.GET, "/api/produtos/{id}").hasRole("CLIENTE")
	                    .requestMatchers(HttpMethod.POST, "/api/pedidos").hasRole("CLIENTE")
	                    .requestMatchers(HttpMethod.POST, "/api/pedidos/{id}/produto").hasRole("CLIENTE")
	                    .requestMatchers(HttpMethod.DELETE, "/api/pedidos/{id}/produto").hasRole("CLIENTE")
	                    .requestMatchers(HttpMethod.DELETE, "/api/pedidos/{id}/produtos").hasRole("CLIENTE")
	                    .requestMatchers(HttpMethod.GET, "/api/pedidos/{id}").hasRole("CLIENTE")
	                    .requestMatchers(HttpMethod.GET, "/api/usuarios/{id}").hasRole("CLIENTE")

	                    // COLABORADOR
	                    .requestMatchers(HttpMethod.PATCH, "/api/setores/pedido/{id}/status").hasRole("COLABORADOR")
	                    .requestMatchers(HttpMethod.GET, "/api/setores/cozinha/pedidos").hasRole("COLABORADOR")
	                    .requestMatchers(HttpMethod.GET, "/api/setores/copa/pedidos").hasRole("COLABORADOR")
	                    .requestMatchers(HttpMethod.GET, "/api/historico").hasRole("COLABORADOR")
	                    .requestMatchers(HttpMethod.POST, "/api/produtos").hasRole("COLABORADOR")
	                    .requestMatchers(HttpMethod.PUT, "/api/produtos/{id}").hasRole("COLABORADOR")

	                    // ADMIN
	                    .requestMatchers(HttpMethod.DELETE, "/api/produtos/{id}").hasRole("ADMIN")
	                    .requestMatchers(HttpMethod.PUT, "/api/usuarios/{id}").hasRole("ADMIN")
	                    .requestMatchers(HttpMethod.DELETE, "/api/usuarios/{id}").hasRole("ADMIN")

	                    // TODO RESTO PRECISA ESTAR AUTENTICADO
	                    .anyRequest().authenticated())
	            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(identityService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}