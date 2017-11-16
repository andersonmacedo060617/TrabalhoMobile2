package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aluno.getre.model.Usuario;

public class MainMotoristaActivity extends AppCompatActivity {
    TextView txtBemVindo;
    final int LOGIN_VIEW = 1;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CallPrincipalView();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_motorista);

        Binding();
        Usuario user = (Usuario)getIntent().getExtras().getSerializable("usuario");
        txtBemVindo.setText("Bem vindo " + user.getNome());


    }

    private void Binding() {
        txtBemVindo = (TextView) findViewById(R.id.frmMotorista_bemVindo);
    }

    private void CallPrincipalView() {
        Intent itn = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivityForResult(itn, LOGIN_VIEW);
    }
}
