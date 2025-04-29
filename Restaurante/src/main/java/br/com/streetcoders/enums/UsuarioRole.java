package br.com.streetcoders.enums;

public enum UsuarioRole {
    ADMIN("admin"),
    COLABORADOR("colaborador"),
    CLIENTE("cliente");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
