package com.example.aluno.getre;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aluno.getre.model.Entrega;
import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.model.enums.ECrud;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class CadViewEntregaActivity extends AppCompatActivity {
    EditText edtId, edtCiente, edtMotorista, edtValor,
            edtKmPercorrido, edtEndOrigem, edtEndDestino,
            edtProduto;
    TextView txtViewTitle;
    Button btnVoltar, btnGravar, btnParadas;
    ECrud op;
    Usuario user;
    Entrega entrega;

    final int VIEW_LIST_PONTOS_PARADA = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_view_entrega);

        Binding();
        op = (ECrud) getIntent().getExtras().getSerializable("op");
        user = (Usuario) getIntent().getExtras().getSerializable("usuario");
        ConfigViewOperation();
        loadData();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);

                finish();
            }
        });

        btnParadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), ListPontosParadaActivity.class);
                itn.putExtra("idEntrega", entrega.getId());
                startActivityForResult(itn, VIEW_LIST_PONTOS_PARADA);
            }
        });
    }


    private void Binding() {
        edtId = (EditText) findViewById(R.id.frmCadViewEntrega_Id);
        edtCiente = (EditText) findViewById(R.id.frmCadViewEntrega_Cliente);
        edtMotorista = (EditText) findViewById(R.id.frmCadViewEntrega_Motorista);
        edtValor = (EditText) findViewById(R.id.frmCadViewEntrega_Valor);
        edtKmPercorrido = (EditText) findViewById(R.id.frmCadViewEntrega_KmPercorrido);
        edtEndOrigem = (EditText) findViewById(R.id.frmCadViewEntrega_Origem);
        edtEndDestino = (EditText) findViewById(R.id.frmCadViewEntrega_Destino);
        edtProduto = (EditText) findViewById(R.id.frmCadViewEntrega_Produto);
        btnGravar = (Button) findViewById(R.id.frmCadViewEntrega_btnGravar);
        btnVoltar = (Button) findViewById(R.id.frmCadViewEntrega_btnVoltar);
        txtViewTitle = (TextView)findViewById(R.id.frmCadViewEntrega_Title);
        btnParadas = (Button) findViewById(R.id.frmCadViewEntrega_btnParadas);
    }

    private void ConfigViewOperation() {
        if (op == ECrud.create || op == ECrud.alter) {
            edtId.setEnabled(false);
            btnGravar.setVisibility(View.VISIBLE);
            edtCiente.setEnabled(true);
            edtMotorista.setEnabled(true);
            edtValor.setEnabled(true);
            edtKmPercorrido.setEnabled(true);
            edtEndOrigem.setEnabled(true);
            edtEndDestino.setEnabled(true);
            edtProduto.setEnabled(true);
        } else {
            edtId.setEnabled(false);
            btnGravar.setVisibility(View.INVISIBLE);
            edtCiente.setEnabled(false);
            edtMotorista.setEnabled(false);
            edtValor.setEnabled(false);
            edtKmPercorrido.setEnabled(false);
            edtEndOrigem.setEnabled(false);
            edtEndDestino.setEnabled(false);
            edtProduto.setEnabled(false);
        }
    }

    private void loadData() {
        if (op == ECrud.alter || op == ECrud.view || op == ECrud.delete) {
            entrega = (Entrega) getIntent().getExtras().getSerializable("entrega");
            edtId.setText(Integer.toString(entrega.getId()));
            edtCiente.setText(entrega.getCliente().getNome());
            edtMotorista.setText(entrega.getMotorista().getNome());
            edtValor.setText("R$ " + Double.toString(entrega.getValor()));
            edtKmPercorrido.setText(Double.toString(entrega.getKmPercorrido()) + " km");
            edtEndOrigem.setText(entrega.getEnderecoOrigem().getDescricao());
            edtEndDestino.setText(entrega.getEnderecoDestino().getDescricao());
            edtProduto.setText(entrega.getProduto().getDescricao());
            if(entrega.isEntregaAberta()){
                txtViewTitle.setText("Entrega - " + Integer.toString(entrega.getId()) + " - Em Aberto");
            }else{
                txtViewTitle.setText("Entrega - " + Integer.toString(entrega.getId()) + " - Concluido");
            }
        }
    }




}
