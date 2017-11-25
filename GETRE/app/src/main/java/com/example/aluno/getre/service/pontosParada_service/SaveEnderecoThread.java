package com.example.aluno.getre.service.pontosParada_service;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.aluno.getre.model.Endereco;
import com.example.aluno.getre.service.dao_json.Endereco_DaoJson;

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

/**
 * Created by Anderson2 on 25/11/2017.
 */

public class SaveEnderecoThread extends AsyncTask<Endereco, Void, Endereco> {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Endereco doInBackground(Endereco... enderecos) {
        String urll = "https://service.davesmartins.com.br/api/referencia";

        HttpURLConnection conn = null;
        URL url = null;
        Endereco end = null;

        try {
            url = new URL(urll);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            String jsonStr = new Endereco_DaoJson().MontaObJson(enderecos[0]).toString();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", String.valueOf(jsonStr.getBytes("UTF-8").length));
            conn.getOutputStream().write(jsonStr.getBytes("UTF-8"));

            InputStream in = new BufferedInputStream(conn.getInputStream());
            StringBuilder sb = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while((nextLine = reader.readLine()) != null){
                sb.append(nextLine);
            }
            String linha = sb.toString();
            JSONObject objJson = new JSONObject(linha);
            end = new Endereco_DaoJson().MontaEndereco(objJson);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return end;
    }
}
