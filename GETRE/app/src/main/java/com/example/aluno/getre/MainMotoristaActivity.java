package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aluno.getre.model.Usuario;

public class MainMotoristaActivity extends AppCompatActivity {
    TextView txtBemVindo;
    Button btnMinhasEntregas, btnEntregasDiponiveis;
    final int LOGIN_VIEW = 1;
    final int LIST_ENTREGAS_VIEW = 42;
    Usuario user;

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
        user = (Usuario)getIntent().getExtras().getSerializable("usuario");
        txtBemVindo.setText("Bem vindo " + user.getNome());


        btnMinhasEntregas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), ListEntregasActivity.class);
                itn.putExtra("usuario", user);
                startActivityForResult(itn, LIST_ENTREGAS_VIEW);
            }
        });

        btnEntregasDiponiveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), ListEntregasActivity.class);
                itn.putExtra("usuario", user);
                itn.putExtra("EntregasDisponiveis", true);
                startActivityForResult(itn, LIST_ENTREGAS_VIEW);
            }
        });
    }

    private void Binding() {
        txtBemVindo = (TextView) findViewById(R.id.frmMotorista_bemVindo);
        btnMinhasEntregas = (Button)findViewById(R.id.frmMainMotorista_btnEntregas);
        btnEntregasDiponiveis = (Button) findViewById(R.id.frmMainMotorista_btnEntregasDisponiveis);
    }

    private void CallPrincipalView() {
        Intent itn = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivityForResult(itn, LOGIN_VIEW);
    }
}
