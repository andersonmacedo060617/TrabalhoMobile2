package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aluno.getre.model.Usuario;

public class MainClienteActivity extends AppCompatActivity {
    TextView txtBemVindo;
    Button btnMinhasEntregas;
    final int LOGIN_VIEW = 1;
    final int LIST_ENTREGAS_VIEW = 42;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CallPrincipalView();
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cliente);

        Binding();
        final Usuario user = (Usuario)getIntent().getExtras().getSerializable("usuario");
        txtBemVindo.setText("Bem vindo " + user.getNome());

        btnMinhasEntregas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), ListEntregasActivity.class);
                itn.putExtra("usuario", user);
                startActivityForResult(itn, LIST_ENTREGAS_VIEW);
            }
        });

    }

    private void Binding() {
        txtBemVindo = (TextView) findViewById(R.id.frmCliente_bemVindo);
        btnMinhasEntregas = (Button)findViewById(R.id.frmMainCliente_btnEntregas);
    }

    private void CallPrincipalView() {
        Intent itn = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivityForResult(itn, LOGIN_VIEW);
    }
}
