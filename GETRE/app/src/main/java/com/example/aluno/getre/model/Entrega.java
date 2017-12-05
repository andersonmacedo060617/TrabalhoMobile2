package com.example.aluno.getre.model;

import com.example.aluno.getre.model.enums.EStatus;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private double valor;
    private double kmPercorrido;
    private Endereco enderecoOrigem;
    private Endereco enderecoDestino;
    private ArrayList<Endereco> registroParadas;
    private Produto produto;

    private boolean entregaAberta;
    private Date cadastro;

    public Entrega() {
        this.registroParadas = new ArrayList<>();
    }

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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public double getKmPercorrido() {
        return kmPercorrido;
    }

    public void setKmPercorrido(double kmPercorrido) {
        this.kmPercorrido = kmPercorrido;
    }

    public boolean isEntregaAberta() {
        return entregaAberta;
    }

    public void setEntregaAberta(boolean entregaAberta) {
        this.entregaAberta = entregaAberta;
    }

    public ArrayList<Endereco> getRegistroParadas() {
        return registroParadas;
    }

    public void setRegistroParadas(ArrayList<Endereco> registroParadas) {
        this.registroParadas = registroParadas;
    }

    public void addRegistroParadas(Endereco endereco){
        this.registroParadas.add(endereco);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public void CalculaKmTotal(){
        double DistanciaTotal = 0;
        for (Endereco ponto : registroParadas){
            DistanciaTotal = DistanciaTotal + ponto.getKmPercorrido();
        }
        this.kmPercorrido = DistanciaTotal;
    }


}
