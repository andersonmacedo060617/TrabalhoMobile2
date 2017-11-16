package com.example.aluno.getre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aluno.getre.model.Usuario;

public class ListEntregasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_entregas);
        final Usuario user = (Usuario)getIntent().getExtras().getSerializable("usuario");

    }
}
