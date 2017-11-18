package com.example.aluno.getre.service.dao_json;

import com.example.aluno.getre.model.Produto;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aluno on 17/11/2017.
 */

public class Produto_DaoJson {
    public Produto MontaProduto(JSONObject json_data) throws JSONException {
        Produto produto = new Produto();

//        "id":3,
//        "descricao":"Pizza",
//        "peso":0.4
        produto.setId(json_data.getInt("id"));
        produto.setDescricao(json_data.getString("descricao"));
        produto.setPeso(json_data.getDouble("peso"));

        return produto;
    }
}
