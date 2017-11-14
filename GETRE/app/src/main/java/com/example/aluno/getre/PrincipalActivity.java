package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aluno.getre.dao.UsuarioDao;
import com.example.aluno.getre.database.DataBase;

public class PrincipalActivity extends AppCompatActivity {

    final int LOGIN_VIEW = 1;
    final int MENU_CLIENTE_VIEW = 2;
    final int MENU_MOTORISTA_VIEW = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        DataBase conect = new DataBase(getApplicationContext());

        UsuarioDao uDao = new UsuarioDao(conect);

        CallLoginView();

    }

    private void CallLoginView() {
        Intent itn = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(itn, LOGIN_VIEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_VIEW){

        }
    }
}
