package com.example.aluno.getre.model;

import com.example.aluno.getre.model.enums.ETipoUsuario;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aluno on 08/11/2017.
 */

public class Usuario implements Serializable {
    private int id;
    private String Nome;
    private String email;
    private String login, senha;
    private boolean ativo;
    private Date cadastro;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public String getCadastroStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(this.cadastro);
    }

    public void setCadastroToDate(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.cadastro = sdf.parse(data);
    }

    public int getAtivoInt(){
        return this.ativo? 1:0;
    }

    public void setAtivoInt(int ativo){
        this.ativo = (ativo == 1 ? true : false);
    }
}
