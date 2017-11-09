package com.example.aluno.getre.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aluno on 08/11/2017.
 */

public class Cliente extends Usuario implements Serializable {

    private ArrayList<Entrega> entregasContratadas;

    public ArrayList<Entrega> getEntregasContratadas() {
        return entregasContratadas;
    }

    public void setEntregasContratadas(ArrayList<Entrega> entregasContratadas) {
        this.entregasContratadas = entregasContratadas;
    }
}
