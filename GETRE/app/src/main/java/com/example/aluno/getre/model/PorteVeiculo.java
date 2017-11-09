package com.example.aluno.getre.model;

import java.io.Serializable;

/**
 * Created by aluno on 08/11/2017.
 */

public class PorteVeiculo implements Serializable {
    private int id;
    private String descricao;
    private String veiculo;

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

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }
}
