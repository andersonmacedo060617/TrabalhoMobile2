package com.example.aluno.getre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.model.enums.ETipoUsuario;
import com.example.aluno.getre.service.usuario_service.AllUsuariosThread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListMotoristasActivity extends AppCompatActivity {

    ListView lstViewMotoristas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_motoristas);
        final Usuario user = (Usuario)getIntent().getExtras().getSerializable("usuario");

        Biding();
        CarregarLstViewMotoristas();

    }

    /*
    Ligação dos campos
     */
    private void Biding() {
        lstViewMotoristas = (ListView) findViewById(R.id.frmLstMotoristas_lstVMotoristas);
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
