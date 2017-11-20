package com.example.aluno.getre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.getre.model.Endereco;
import com.example.aluno.getre.model.enums.ECrud;
import com.example.aluno.getre.service.pontosParada_service.FindEnderecoByIdThread;

import java.util.concurrent.ExecutionException;

public class CadViewPontosParadaActivity extends AppCompatActivity {
    EditText edtId, edtDescricao, edtDetalhes, edtKmFaltante, edtKmPercorrido, edtHorario;
    Button btnVoltar;
    ECrud op;
    Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_view_pontos_parada);

        Biding();

        op = (ECrud) getIntent().getSerializableExtra("op");
        int idEndereco = getIntent().getIntExtra("idEndereco", 0);


        if(idEndereco == 0){
            finish();
        }else{
            ConfigViewOperation();
            loadData(idEndereco);
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });

    }

    private void Biding() {
        edtId = (EditText) findViewById(R.id.frmCadViewPontoParada_Id);
        edtDescricao = (EditText) findViewById(R.id.frmCadViewPontoParada_Descricao);
        edtDetalhes = (EditText) findViewById(R.id.frmCadViewPontoParada_Detalhes);
        edtHorario = (EditText) findViewById(R.id.frmCadViewPontoParada_Horario);
        edtKmFaltante = (EditText) findViewById(R.id.frmCadViewPontoParada_KmFaltante);
        edtKmPercorrido = (EditText) findViewById(R.id.frmCadViewPontoParada_KmPercorrido);
        btnVoltar = (Button) findViewById(R.id.frmCadViewPontoParada_btnVoltar);
        edtId.setEnabled(false);
    }

    private void ConfigViewOperation() {
        if (op == ECrud.create || op == ECrud.alter) {
            FieldEnable(true);
        } else {
            FieldEnable(false);
        }
    }

    private void loadData(int idEndereco) {
        if (op == ECrud.alter || op == ECrud.view || op == ECrud.delete) {
            try {
                endereco = new FindEnderecoByIdThread().execute(Integer.toString(idEndereco)).get();
                edtId.setText(Integer.toString(endereco.getId()));
                edtHorario.setText(endereco.getHorrarioStr());
                edtKmFaltante.setText(Double.toString(endereco.getKmFaltante()) + " Km");
                edtKmPercorrido.setText(Double.toString(endereco.getKmPercorrido()) + " Km");
                edtDetalhes.setText(endereco.getDetalhe());
                edtDescricao.setText(endereco.getDescricao());

            } catch (InterruptedException e) {
                Toast.makeText(getApplicationContext(), "Falha na consulta \r\n Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (ExecutionException e) {
                Toast.makeText(getApplicationContext(), "Falha na consulta \r\n Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void FieldEnable(boolean b){
        edtDescricao.setEnabled(b);
        edtDetalhes.setEnabled(b);
        edtKmPercorrido.setEnabled(b);
        edtKmFaltante.setEnabled(b);
        edtHorario.setEnabled(b);
    }
}
