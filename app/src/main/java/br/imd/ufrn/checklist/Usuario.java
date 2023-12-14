package br.imd.ufrn.checklist;

public class Usuario {
    private final String login;
    private final String senha;
    private final String email;

    public Usuario(String login, String senha, String email) {
        this.login = login;
        this.senha = senha;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }
}
