package com.example.aluno.getre.service.dao_json;

import com.example.aluno.getre.model.Endereco;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by aluno on 17/11/2017.
 */

public class Endereco_DaoJson {
    public Endereco MontaEndereco(JSONObject json_data) throws JSONException, ParseException {
        Endereco endereco = new Endereco();
//        "id":1,
//        "descricao":"Lanchonete MCPizza",
//        "detalhe":"Lanchonete",
//        "kmFaltante":3.0,
//        "kmPercorrido":0.0,
//        "horrario":"=13/11/2017 12:00:00"
        endereco.setId(json_data.getInt("id"));
        endereco.setDescricao(json_data.getString("descricao"));
        endereco.setDetalhe(json_data.getString("detalhe"));
        endereco.setKmFaltante(json_data.getDouble("kmFaltante"));
        endereco.setKmPercorrido(json_data.getDouble("kmPercorrido"));
        endereco.setHorrarioToDate(json_data.getString("horrario").replace("=", ""));

        return endereco;

    }
}
