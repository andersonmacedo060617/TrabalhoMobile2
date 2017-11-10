package com.example.aluno.getre.model;

import com.example.aluno.getre.model.enums.EStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aluno on 08/11/2017.
 */

public class Entrega implements Serializable {
    private int id;
    private Cliente cliente;
    private Motorista motorista;
    private double custoTotal;
    private double peso;
    private double distanciaTotal;
    private Endereco enderecoOrigem;
    private Endereco enderecoDestino;
    private ArrayList<RegistroPontoParada> registroParadas;
    private Rota rota;
    private PorteVeiculo porteVeiculo;
    private Date dataSaida;
    private EStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public Endereco getEnderecoOrigem() {
        return enderecoOrigem;
    }

    public void setEnderecoOrigem(Endereco enderecoOrigem) {
        this.enderecoOrigem = enderecoOrigem;
    }

    public Endereco getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(Endereco enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public ArrayList<RegistroPontoParada> getRegistroParadas() {
        return registroParadas;
    }

    public void setRegistroParadas(ArrayList<RegistroPontoParada> registroParadas) {
        this.registroParadas = registroParadas;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public PorteVeiculo getPorteVeiculo() {
        return porteVeiculo;
    }

    public void setPorteVeiculo(PorteVeiculo porteVeiculo) {
        this.porteVeiculo = porteVeiculo;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }


}
