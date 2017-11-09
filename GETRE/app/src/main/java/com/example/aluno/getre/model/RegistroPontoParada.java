package com.example.aluno.getre.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aluno on 08/11/2017.
 */

public class RegistroPontoParada implements Serializable {
    private int id;
    private String nomeCidade;
    private Date dataHoraChegada;
    private PontoParada pontoParada;
    private Entrega entrega;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Date getDataHoraChegada() {
        return dataHoraChegada;
    }

    public void setDataHoraChegada(Date dataHoraChegada) {
        this.dataHoraChegada = dataHoraChegada;
    }

    public PontoParada getPontoParada() {
        return pontoParada;
    }

    public void setPontoParada(PontoParada pontoParada) {
        this.pontoParada = pontoParada;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
}
