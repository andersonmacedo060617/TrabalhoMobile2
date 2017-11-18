package com.example.aluno.getre.model;

import java.io.Serializable;

/**
 * Created by aluno on 17/11/2017.
 */

public class Produto implements Serializable{
    private int id;
    private String descricao;
    private double peso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
