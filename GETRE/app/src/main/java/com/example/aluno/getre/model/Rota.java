package com.example.aluno.getre.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aluno on 08/11/2017.
 */

public class Rota implements Serializable {
    private int id;
    private String nome;
    private ArrayList<PontoParada> pontoParadas;

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

    public ArrayList<PontoParada> getPontoParadas() {
        return pontoParadas;
    }

    public void setPontoParadas(ArrayList<PontoParada> pontoParadas) {
        this.pontoParadas = pontoParadas;
    }
}
