package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluno.getre.model.Entrega;
import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.model.enums.ECrud;
import com.example.aluno.getre.model.enums.ETipoUsuario;
import com.example.aluno.getre.service.entrega_service.AllEntregasThead;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListEntregasActivity extends AppCompatActivity {
    ListView lstViewEntregas;
    Button btnVoltar;
    ArrayList<Entrega> lstEntregas;
    boolean entregasDisponiveis = false;
    final int VIEW_ENTREGA = 5;
    Usuario user;
    int[] vetorPosicoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_entregas);
        user = (Usuario)getIntent().getExtras().getSerializable("usuario");
        Binding();

        entregasDisponiveis = getIntent().getExtras().getBoolean("EntregasDisponiveis", false);

        CarregarLstViewEntregas();

        lstViewEntregas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent itn = new Intent(getApplicationContext(),
                        CadViewEntregaActivity.class);
                itn.putExtra("op", ECrud.view);
                itn.putExtra("entrega", lstEntregas.get(vetorPosicoes[i]));
                itn.putExtra("usuario", user);
                itn.putExtra("EntregaDisponivel", entregasDisponiveis);
                startActivityForResult(itn, VIEW_ENTREGA);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void Binding() {
        lstViewEntregas = (ListView) findViewById(R.id.frmLstEntregas_lstVEntregas);
        btnVoltar = (Button) findViewById(R.id.frmLstEntregas_btnVoltar);
    }

    /*
    Preencher ListView de Motoristas
     */
    private void CarregarLstViewEntregas() {
        lstEntregas = new ArrayList<>();
        try {
            lstEntregas = new AllEntregasThead().execute().get();

        } catch (InterruptedException e) {
            Toast.makeText(getApplicationContext(), "Falha na consulta \r\n Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (ExecutionException e) {
            Toast.makeText(getApplicationContext(), "Falha na consulta \r\n Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //Transforma em vetor
        String[] vetorEntregas = new String[lstEntregas.size()];
        vetorPosicoes = new int[lstEntregas.size()];
        int i = 0;
        int posicao = 0;

        if (user.getTipoUsuario() == ETipoUsuario.Cliente) {
            for (Entrega e : lstEntregas) {
                if (e.getCliente().getId() == user.getId()) {
                    vetorEntregas[i] = e.getId() + " - Cliente: " + e.getCliente().getNome() + " - Produto: " + e.getProduto().getDescricao() +
                            " - Status: " + (e.isEntregaAberta() ? "Em aberto" : "Concluido");
                    vetorPosicoes[i] = e.getId();
                    i = i+1;
                }
                posicao = posicao+1;
            }

        } else if(user.getTipoUsuario() == ETipoUsuario.Motorista){
            if(entregasDisponiveis){
                for (Entrega e : lstEntregas) {
                    if (e.getMotorista().getId() == 0) {
                        vetorEntregas[i] = e.getId() + " - Cliente: " + e.getCliente().getNome() + " - Produto: " + e.getProduto().getDescricao() +
                                " - Status: " + (e.isEntregaAberta() ? "Em aberto" : "Concluido");
                        vetorPosicoes[i] = posicao;
                        i = i+1;
                    }
                    posicao = posicao+1;
                }
            }else{
                for (Entrega e : lstEntregas) {
                    if (e.getMotorista().getId() == user.getId()) {
                        vetorEntregas[i] = e.getId() + " - Cliente: " + e.getCliente().getNome() + " - Produto: " + e.getProduto().getDescricao() +
                                " - Status: " + (e.isEntregaAberta() ? "Em aberto" : "Concluido");
                        vetorPosicoes[i] = posicao;
                        i = i+1;
                    }

                    posicao = posicao+1;
                }
            }

        }else{
            for(Entrega e : lstEntregas){
                vetorEntregas[i] = e.getId() + " - Cliente: " + e.getCliente().getNome() + " - Produto: " + e.getProduto().getDescricao() +
                        " - Status: " + (e.isEntregaAberta()?"Em aberto":"Concluido");
                vetorPosicoes[i] = posicao;
                i = i+1;
                posicao = posicao+1;
            }


        }


        //Preencher o Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                vetorEntregas
        );

        //troca o adapter
        lstViewEntregas.setAdapter(adapter);

    }
}
