package com.example.aluno.getre.service.entrega_service;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.aluno.getre.model.Entrega;
import com.example.aluno.getre.service.dao_json.Entrega_DaoJson;

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

/**
 * Created by Anderson2 on 25/11/2017.
 */

public class FindEntregaByIdThread extends AsyncTask<String, Void, Entrega> {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Entrega doInBackground(String... idEntregas) {
        String urll = "https://service.davesmartins.com.br/api/entregas/" + idEntregas[0];

        Entrega resposta = null;
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
            JSONObject jObj = new JSONObject(linha);

            resposta = new Entrega_DaoJson().MontaEntrega(jObj);


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
