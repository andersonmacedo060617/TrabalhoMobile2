package com.example.aluno.getre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluno.getre.model.Cliente;
import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.model.enums.ETipoUsuario;
import com.example.aluno.getre.service.usuario_service.AllUsuariosThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListClientesActivity extends AppCompatActivity {

    ListView lstViewClientes;
    Button btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clientes);
        final Usuario user = (Usuario)getIntent().getExtras().getSerializable("usuario");

        Biding();
        CarregarLstViewClientes();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /*
    Ligação dos campos
     */
    private void Biding() {
        lstViewClientes = (ListView) findViewById(R.id.frmLstCliente_lstVClientes);
        btnVoltar = (Button) findViewById(R.id.frmLstCliente_btnVoltar);
    }

    /*
    Preencher ListView de Clientes
     */
    private void CarregarLstViewClientes() {
        ArrayList<Usuario> lstClientes = new ArrayList<>();
        try {
            ArrayList<Usuario> lstAllUser = new AllUsuariosThread().execute().get();
            for (Usuario c :
                    lstAllUser) {
                if(c.getTipoUsuario() == ETipoUsuario.Cliente){
                    lstClientes.add(c);
                }
            }
        } catch (InterruptedException e) {
            Toast.makeText(getApplicationContext(), "Falha na consulta \r\n Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (ExecutionException e) {
            Toast.makeText(getApplicationContext(), "Falha na consulta \r\n Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //Transforma em verto
        String[] vetorCli = new String[lstClientes.size()];
        int i =0;
        for(Usuario u : lstClientes){
            vetorCli[i++] = u.getId() + " - " + u.getNome() + " - Tipo:" + u.getTipoUsuario().toString();
        }

        //Preencher o Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                vetorCli
        );

        //troca o adapter
        lstViewClientes.setAdapter(adapter);

    }
}
