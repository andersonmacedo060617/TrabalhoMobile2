package com.example.aluno.getre.service.usuario_service;

import android.os.AsyncTask;

import com.example.aluno.getre.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by Anderson2 on 15/11/2017.
 */

public class FindLoginSenhaThread extends AsyncTask<Usuario, Void, Usuario> {

    @Override
    protected Usuario doInBackground(Usuario... user) {
        String urll = "https://service.davesmartins.com.br/api/usuarios/login";

        HttpURLConnection conn = null;
        Usuario u = null;
        try {


            URL url = new URL(urll);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            String valores = "user=" + user;
            conn.getOutputStream().write(valores.getBytes());

            conn.connect();
            InputStream t = conn.getInputStream();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {

                String nextLine = "";
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String linha = sb.toString();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return u;
    }
}
