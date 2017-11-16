package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aluno.getre.model.Usuario;

public class MainAdministradorActivity extends AppCompatActivity {
    final int LIST_CLIENTES_VIEW = 41;
    final int LIST_ENTREGAS_VIEW = 42;
    final int LIST_MOTORISTAS_VIEW = 43;
    final int LOGIN_VIEW = 1;

    TextView txtBemVindo;
    Button btnClientes, btnEntregas, btnMotoristas;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CallPrincipalView();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_administrador);

        Binding();
        final Usuario user = (Usuario)getIntent().getExtras().getSerializable("usuario");
        txtBemVindo.setText("Bem vindo " + user.getNome());

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallPageLstClientes(user);
            }
        });

        btnMotoristas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallPageLstMotoristas(user);
            }
        });

        btnEntregas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallPageLstEntregas(user);
            }
        });
    }

    private void CallPageLstClientes(Usuario u){
        Intent itn = new Intent(getApplicationContext(),
                ListClientesActivity.class);
        itn.putExtra("usuario", u);
        startActivityForResult(itn, LIST_CLIENTES_VIEW);
    }

    private void CallPageLstMotoristas(Usuario u){
        Intent itn = new Intent(getApplicationContext(),
                ListMotoristasActivity.class);
        itn.putExtra("usuario", u);
        startActivityForResult(itn, LIST_MOTORISTAS_VIEW);
    }

    private void CallPageLstEntregas(Usuario u){
        Intent itn = new Intent(getApplicationContext(),
                ListEntregasActivity.class);
        itn.putExtra("usuario", u);
        startActivityForResult(itn, LIST_ENTREGAS_VIEW);
    }

    private void Binding() {
        txtBemVindo = (TextView) findViewById(R.id.frmAdministrador_bemVindo);
        btnClientes = (Button) findViewById(R.id.frmAdministrador_btnClientes);
        btnEntregas = (Button) findViewById(R.id.frmAdministrador_btnEntregas);
        btnMotoristas = (Button) findViewById(R.id.frmAdministrador_btnMototista);
    }



    private void CallPrincipalView() {
        Intent itn = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivityForResult(itn, LOGIN_VIEW);
    }
}
