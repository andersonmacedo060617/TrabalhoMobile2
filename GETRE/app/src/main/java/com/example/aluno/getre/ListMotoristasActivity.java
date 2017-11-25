package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.model.enums.ECrud;
import com.example.aluno.getre.model.enums.ETipoUsuario;
import com.example.aluno.getre.service.usuario_service.AllUsuariosThread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListMotoristasActivity extends AppCompatActivity {

    ListView lstViewMotoristas;
    Button btnVoltar, btnAddMotorista;
    final int CAD_VIEW_USUARIO = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            Toast.makeText(getApplicationContext(), "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG).show();
        }
        CarregarLstViewMotoristas();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_motoristas);
        final Usuario user = (Usuario)getIntent().getExtras().getSerializable("usuario");

        Biding();
        CarregarLstViewMotoristas();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAddMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), CadViewUsuarioActivity.class);
                itn.putExtra("tipoUsuario", ETipoUsuario.Motorista);
                itn.putExtra("op", ECrud.create);
                startActivityForResult(itn, CAD_VIEW_USUARIO);
            }
        });
    }

    /*
    Ligação dos campos
     */
    private void Biding() {
        lstViewMotoristas = (ListView) findViewById(R.id.frmLstMotoristas_lstVMotoristas);
        btnVoltar = (Button)findViewById(R.id.frmLstMotoristas_btnVoltar);
        btnAddMotorista = (Button) findViewById(R.id.frmLstMotoristas_btnAddMotorista);
    }

    /*
    Preencher ListView de Motoristas
     */
    private void CarregarLstViewMotoristas() {
        ArrayList<Usuario> lstMotoristas = new ArrayList<>();
        try {
            ArrayList<Usuario> lstAllUser = new AllUsuariosThread().execute().get();
            for (Usuario m :
                    lstAllUser) {
                if(m.getTipoUsuario() == ETipoUsuario.Motorista){
                    lstMotoristas.add(m);
                }
            }
        } catch (InterruptedException e) {
            Toast.makeText(getApplicationContext(), "Falha na consulta \r\n Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (ExecutionException e) {
            Toast.makeText(getApplicationContext(), "Falha na consulta \r\n Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //Transforma em verto
        String[] vetorCli = new String[lstMotoristas.size()];
        int i =0;
        for(Usuario u : lstMotoristas){
            vetorCli[i++] = u.getId() + " - " + u.getNome();
        }

        //Preencher o Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                vetorCli
        );

        //troca o adapter
        lstViewMotoristas.setAdapter(adapter);

    }

}
