package com.example.aluno.getre.model;

import com.example.aluno.getre.model.enums.ETipoUsuario;

import java.io.Serializable;

/**
 * Created by Anderson2 on 15/11/2017.
 */

public class Administrador extends Usuario implements Serializable {
    public Administrador(){
        this.setTipoUsuario(ETipoUsuario.Administrador);
    }
}
