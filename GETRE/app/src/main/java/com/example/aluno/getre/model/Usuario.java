package com.example.aluno.getre.model;

import com.example.aluno.getre.model.enums.ETipoUsuario;

import java.io.Serializable;

/**
 * Created by aluno on 08/11/2017.
 */

public class Usuario implements Serializable {
    private int id;
    private String Nome;
    private String login, senha;
    private ETipoUsuario TipoUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ETipoUsuario getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(ETipoUsuario tipoUsuario) {
        TipoUsuario = tipoUsuario;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
