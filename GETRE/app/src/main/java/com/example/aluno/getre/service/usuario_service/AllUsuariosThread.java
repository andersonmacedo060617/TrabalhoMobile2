package com.example.aluno.getre.service.usuario_service;

import android.os.AsyncTask;

import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.service.dao_json.Usuario_DaoJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson2 on 15/11/2017.
 */

public class AllUsuariosThread extends AsyncTask<String, Void, ArrayList<Usuario>> {

    @Override
    protected ArrayList<Usuario> doInBackground(String... params) {
        String urll = "https://service.davesmartins.com.br/api/usuarios";

        ArrayList<Usuario> resposta = new ArrayList<>();
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urll);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream in = new BufferedInputStream(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))){
                String nextLine = "";
                while ((nextLine = reader.readLine()) != null){
                    sb.append(nextLine);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

            String linha = sb.toString();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            JSONArray jArray = new JSONArray(linha);

            for(int i = 0; i< jArray.length(); i++){
                resposta.add(new Usuario_DaoJson().MontaUsuario(jArray.getJSONObject(i)));
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resposta;
    }
}
