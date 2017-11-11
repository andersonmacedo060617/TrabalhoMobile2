package com.example.aluno.getre.model;

import com.example.aluno.getre.model.enums.ETipoUsuario;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aluno on 08/11/2017.
 */

public class Cliente extends Usuario implements Serializable {

    public Cliente(){
        this.setTipoUsuario(ETipoUsuario.Cliente);
    }

    private ArrayList<Entrega> entregasContratadas;

    public ArrayList<Entrega> getEntregasContratadas() {
        return entregasContratadas;
    }

    public void setEntregasContratadas(ArrayList<Entrega> entregasContratadas) {
        this.entregasContratadas = entregasContratadas;
    }
}
