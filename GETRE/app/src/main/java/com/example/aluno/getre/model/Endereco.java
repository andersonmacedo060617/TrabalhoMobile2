package com.example.aluno.getre.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aluno on 08/11/2017.
 */

public class Endereco implements Serializable {
    private int id;
    private String descricao;
    private String detalhe;
    private double kmFaltante;
    private double kmPercorrido;
    private Date horrario;

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

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public double getKmFaltante() {
        return kmFaltante;
    }

    public void setKmFaltante(double kmFaltante) {
        this.kmFaltante = kmFaltante;
    }

    public double getKmPercorrido() {
        return kmPercorrido;
    }

    public void setKmPercorrido(double kmPercorrido) {
        this.kmPercorrido = kmPercorrido;
    }

    public Date getHorrario() {
        return horrario;
    }

    public void setHorrario(Date horrario) {
        this.horrario = horrario;
    }

    public void setHorrarioToDate(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.horrario = sdf.parse(data);
    }

    public String getHorrarioStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(this.horrario);
    }
}
