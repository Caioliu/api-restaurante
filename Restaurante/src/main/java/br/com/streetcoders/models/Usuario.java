package br.com.streetcoders.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.streetcoders.enums.UsuarioRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = -5723107787721800847L;

	public enum UsuarioSetor {
	    COZINHA, COPA;

	    public static UsuarioSetor getByName(String value) {
	        for (UsuarioSetor produto : UsuarioSetor.values()) {
	            if (produto.name().equals(value)) {
	                return produto;
	            }
	        }
	        return null;
	    }
	}
	
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario_seq")
    @SequenceGenerator(name="usuario_seq", sequenceName="sq_usuario", initialValue = 1, allocationSize = 1)
    private Long id;
    
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    
    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 50)
    private UsuarioRole role;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "setor", nullable = true, length = 50)
    private UsuarioSetor setor;

    public Usuario(String nome, String login, String password, UsuarioRole role, UsuarioSetor setor) {
        this.nome = nome;
    	this.username = login;
        this.passwordHash = password;
        this.role = role;
        this.setor = setor;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.role) {
            case ADMIN -> List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                new SimpleGrantedAuthority("ROLE_CLIENTE")
            );
            case COLABORADOR -> List.of(
                new SimpleGrantedAuthority("ROLE_COLABORADOR"),
                new SimpleGrantedAuthority("ROLE_CLIENTE")
            );
            case CLIENTE -> List.of(
                new SimpleGrantedAuthority("ROLE_CLIENTE")
            );
        };
    }


	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return passwordHash;
	}

}
