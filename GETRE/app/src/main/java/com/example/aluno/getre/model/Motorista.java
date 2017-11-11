package com.example.aluno.getre.model;

import com.example.aluno.getre.model.enums.ETipoUsuario;

import java.io.Serializable;

/**
 * Created by aluno on 08/11/2017.
 */

public class Motorista extends Usuario implements Serializable {
    public Motorista(){
        this.setTipoUsuario(ETipoUsuario.Motorista);
    }
}
