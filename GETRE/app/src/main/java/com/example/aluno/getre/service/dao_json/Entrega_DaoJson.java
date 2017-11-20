package com.example.aluno.getre.service.dao_json;

import com.example.aluno.getre.model.Cliente;
import com.example.aluno.getre.model.Entrega;
import com.example.aluno.getre.model.Motorista;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by aluno on 17/11/2017.
 */

public class Entrega_DaoJson {
    public Entrega MontaEndereco(JSONObject json_data) throws JSONException, ParseException {
        Entrega entrega = new Entrega();

//        "id":1, ok
//        "cadastro":"=13/11/2017 12:00:00", ok
//        "origem":{endereco} ok
//        "destino":{endereco} ok
//        "cliente":{usuario} ok
//        "motorista":{usuario} ok
//        "pontos":[{ponto}] ok
//        "produto":{produto}
//        "valor":30.0, ok
//        "kmPercorrido":3.0, ok
//        "entregaAberta":false ok

        entrega.setId(json_data.getInt("id"));
        entrega.setCadastroToDate(json_data.getString("cadastro").replace("=", ""));
        entrega.setEnderecoOrigem(new Endereco_DaoJson().MontaEndereco(json_data.getJSONObject("origem")));
        entrega.setEnderecoDestino(new Endereco_DaoJson().MontaEndereco(json_data.getJSONObject("destino")));
        entrega.setCliente((Cliente) new Usuario_DaoJson().MontaUsuario(json_data.getJSONObject("cliente")));
        entrega.setMotorista((Motorista) new Usuario_DaoJson().MontaUsuario(json_data.getJSONObject("motorista")));

        JSONArray arrPontos = json_data.getJSONArray("pontos");
        for (int i = 0; i < arrPontos.length(); i++){
            entrega.addRegistroParadas(new Endereco_DaoJson().MontaEndereco(arrPontos.getJSONObject(i)));
        }

        entrega.setProduto(new Produto_DaoJson().MontaProduto(json_data.getJSONObject("produto")));

        entrega.setValor(json_data.getDouble("valor"));
        entrega.setValor(json_data.getDouble("kmPercorrido"));
        entrega.setEntregaAberta(json_data.getBoolean("entregaAberta"));

        return entrega;
    }
}
