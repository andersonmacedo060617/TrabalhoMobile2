package com.example.aluno.getre.model;

import java.io.Serializable;

/**
 * Created by aluno on 08/11/2017.
 */

public class PontoParada implements Serializable {
    private int id;
    private Cidade cidade;
    private Rota rota;
    private int ordem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }
}
