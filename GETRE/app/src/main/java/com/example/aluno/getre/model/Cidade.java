package com.example.aluno.getre.model;

import java.io.Serializable;

/**
 * Created by aluno on 08/11/2017.
 */

public class Cidade  implements Serializable {
    private int id;
    private String nome;
    private String UF;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }
}
