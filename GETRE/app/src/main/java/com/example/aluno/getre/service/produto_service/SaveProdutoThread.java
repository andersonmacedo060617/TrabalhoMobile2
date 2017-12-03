package com.example.aluno.getre.service.produto_service;

import android.os.AsyncTask;

import com.example.aluno.getre.model.Produto;
import com.example.aluno.getre.service.dao_json.Produto_DaoJson;

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
 * Created by Anderson2 on 02/12/2017.
 */

public class SaveProdutoThread extends AsyncTask<Produto, Void, Produto> {

    @Override
    protected Produto doInBackground(Produto... produtos) {
        String urll = "https://service.davesmartins.com.br/api/produtos";

        HttpURLConnection conn = null;
        URL url = null;
        Produto prod = null;

        try {
            url = new URL(urll);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            String jsonStr = new Produto_DaoJson().MontaObjJson(produtos[0]).toString();
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
            prod = new Produto_DaoJson().MontaProduto(objJson);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return prod;
    }
}
