package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.getre.model.Endereco;
import com.example.aluno.getre.model.Entrega;
import com.example.aluno.getre.model.PontoParada;
import com.example.aluno.getre.model.enums.ECrud;
import com.example.aluno.getre.service.pontosParada_service.FindPontosParadaEntregaThread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListPontosParadaActivity extends AppCompatActivity {
    ListView lstViewPontosParada;
    TextView txtViewTitle;
    ArrayList<Endereco> lstPontoParada;
    Button btnVoltar;
    final int VIEW_PONTOS_PARADA = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pontos_parada);

        Biding();

        int idEntrega = getIntent().getIntExtra("idEntrega", 0);
        if(idEntrega == 0){
            setResult(0);
            finish();
        }else{
            CarregarLstViewPontosParada(idEntrega);
        }

        lstViewPontosParada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent itn = new Intent(getApplicationContext(),
                        CadViewPontosParadaActivity.class);
                itn.putExtra("op", ECrud.view);
                itn.putExtra("idEndereco", lstPontoParada.get(i).getId());

                startActivityForResult(itn, VIEW_PONTOS_PARADA);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    private void CarregarLstViewPontosParada(int idEntrega) {
        try {
            lstPontoParada = new FindPontosParadaEntregaThread().execute(Integer.toString(idEntrega)).get();
        } catch (InterruptedException e) {
            Toast.makeText(getApplicationContext(), "Falha na consulta \r\n Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (ExecutionException e) {
            Toast.makeText(getApplicationContext(), "Falha na consulta \r\n Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //Transforma em verto
        String[] vetorEntregas = new String[lstPontoParada.size()];
        int i =0;
        for(Endereco e : lstPontoParada){
            vetorEntregas[i++] = e.getId() + " - " + e.getDescricao() + " - " + e.getHorrarioStr();
        }

        //Preencher o Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                vetorEntregas
        );

        txtViewTitle.setText("Paradas Entrega N° " + Integer.toString(idEntrega));

        //troca o adapter
        lstViewPontosParada.setAdapter(adapter);
    }

    private void Biding() {
        lstViewPontosParada = (ListView) findViewById(R.id.frmLstPontosParada_lstVPontosParada);
        txtViewTitle = (TextView) findViewById(R.id.frmLstPontosParada_txtViewTitle);
        btnVoltar = (Button) findViewById(R.id.frmLstPontosParada_btnVoltar);
    }
}
