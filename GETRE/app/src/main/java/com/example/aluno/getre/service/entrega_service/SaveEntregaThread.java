package com.example.aluno.getre.service.entrega_service;

import android.os.AsyncTask;

import com.example.aluno.getre.model.Entrega;
import com.example.aluno.getre.service.dao_json.Entrega_DaoJson;

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

public class SaveEntregaThread extends AsyncTask<Entrega, Void, Entrega> {

    @Override
    protected Entrega doInBackground(Entrega... entregas) {
        String urll = "https://service.davesmartins.com.br/api/referencia";

        HttpURLConnection conn = null;
        URL url = null;
        Entrega ent = null;

        try {
            url = new URL(urll);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            String jsonStr = new Entrega_DaoJson().MontaObJson(entregas[0]).toString();
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
            ent = new Entrega_DaoJson().MontaEntrega(objJson);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return ent;
    }
}
