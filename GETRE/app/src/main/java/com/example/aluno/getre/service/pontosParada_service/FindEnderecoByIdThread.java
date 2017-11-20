package com.example.aluno.getre.service.pontosParada_service;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.aluno.getre.model.Endereco;
import com.example.aluno.getre.service.dao_json.Endereco_DaoJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Anderson2 on 19/11/2017.
 */

public class FindEnderecoByIdThread extends AsyncTask<String, Void, Endereco> {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Endereco doInBackground(String... strings) {
        String urll = "https://service.davesmartins.com.br/api/referencia/" + strings[0];

        Endereco resposta = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urll);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream in = new BufferedInputStream(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String nextLine = "";
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String linha = sb.toString();

            JSONObject jObject = new JSONObject(linha);

            resposta = new Endereco_DaoJson().MontaEndereco(jObject);
            conn.disconnect();

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
