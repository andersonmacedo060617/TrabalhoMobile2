package com.example.aluno.getre.service.dao_json;

import com.example.aluno.getre.model.Administrador;
import com.example.aluno.getre.model.Cliente;
import com.example.aluno.getre.model.Motorista;
import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.model.enums.ETipoUsuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson2 on 15/11/2017.
 */

public class Usuario_DaoJson {

    public Usuario MontaUsuario(JSONObject json_data) throws JSONException, ParseException {

        Usuario u = null;
        if(json_data.getString("tipo").equals("MOTORISTA")){
            u = new Motorista();
        }else if(json_data.getString("tipo").equals("ADMIN")){
            u = new Administrador();
        }else{
            u = new Cliente();
        }
//        "id":1,
//        "nome":"Zezin",
//        "email":"ze@ze",
//        "login":"ze",
//        "senha":"123",
//        "ativo":true,
//        "cadastro":"=13/11/2016 12:00:00",
//        "tipo":"CLIENTE"
        u.setId(json_data.getInt("id"));
        u.setNome(json_data.getString("nome"));
        u.setEmail(json_data.getString("email"));
        u.setLogin(json_data.getString("login"));
        u.setSenha(json_data.getString("senha"));
        u.setAtivo(json_data.getBoolean("ativo"));
        u.setCadastroToDate(json_data.getString("cadastro").replace("=", ""));

        return u;
    }

    public JSONObject MontaObjJson(Usuario usuario) throws JSONException {
//        "id":1,
//        "nome":"Zezin",
//        "email":"ze@ze",
//        "login":"ze",
//        "senha":"123",
//        "ativo":true,
//        "cadastro":"=13/11/2016 12:00:00",
//        "tipo":"CLIENTE"
        JSONObject jsonObject = new JSONObject();

        if(usuario.getId() != 0){
            jsonObject.put("id", usuario.getId());
        }
        if(usuario.getTipoUsuario() == ETipoUsuario.Motorista){
            jsonObject.put("tipo", "MOTORISTA");
        }else if(usuario.getTipoUsuario() == ETipoUsuario.Administrador){
            jsonObject.put("tipo", "ADMIN");
        }else{
            jsonObject.put("tipo", "CLIENTE");
        }

        jsonObject.put("nome", usuario.getNome());
        jsonObject.put("email", usuario.getEmail());
        jsonObject.put("login", usuario.getLogin());
        jsonObject.put("senha", usuario.getSenha());
        jsonObject.put("ativo", usuario.isAtivo());
        jsonObject.put("cadastro", "="+usuario.getCadastroStr());

        return jsonObject;
    }

}
