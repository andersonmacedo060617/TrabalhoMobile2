package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.getre.model.Endereco;
import com.example.aluno.getre.model.Entrega;
import com.example.aluno.getre.model.enums.ECrud;
import com.example.aluno.getre.service.entrega_service.FindEntregaByIdThread;
import com.example.aluno.getre.service.entrega_service.SaveEntregaThread;
import com.example.aluno.getre.service.pontosParada_service.FindEnderecoByIdThread;
import com.example.aluno.getre.service.pontosParada_service.FindPontosParadaEntregaThread;
import com.example.aluno.getre.service.pontosParada_service.SaveEnderecoThread;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class CadViewPontosParadaActivity extends AppCompatActivity {
    EditText edtId, edtDescricao, edtDetalhes, edtKmFaltante, edtKmPercorrido, edtHorario;
    Button btnVoltar, btnGravar;
    ECrud op;
    Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_view_pontos_parada);

        Biding();

        op = (ECrud) getIntent().getSerializableExtra("op");
        final int idEndereco = getIntent().getIntExtra("idEndereco", 0);
        final int idEntrega = getIntent().getIntExtra("idEntrega", 0);

        if(idEndereco != 0){
            ConfigViewOperation();
            loadData(idEndereco);
        }

        if(idEntrega == 0){
            setResult(0);
            finish();
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idEntrega == 0){
                    setResult(0);
                    finish();
                }else{
                    try {
                        Double.parseDouble(edtKmFaltante.getText().toString());
                        Double.parseDouble(edtKmPercorrido.getText().toString());

                    endereco = new Endereco();
                    endereco.setDescricao(edtDescricao.getText().toString());
                    endereco.setDetalhe(edtDetalhes.getText().toString());
                    endereco.setKmFaltante(Double.parseDouble(edtKmFaltante.getText().toString()));
                    endereco.setKmPercorrido(Double.parseDouble(edtKmPercorrido.getText().toString()));
                    endereco.setHorrario(new Date());

                    try {
                        //Salvo a endereco. Retorna a endereco salva
                        endereco = new SaveEnderecoThread().execute(endereco).get();

                        //Pego a entrega para adcionar o endereço como  parada e salvar a entrega.
                        Entrega entrega = new FindEntregaByIdThread().execute(Integer.toString(idEntrega)).get();
                        entrega.getRegistroParadas().add(endereco);
                        entrega.CalculaKmTotal();

                        entrega = new SaveEntregaThread().execute(entrega).get();


                        setResult(1);
                        finish();


                    } catch (InterruptedException e) {
                        Toast.makeText(getApplicationContext(), "Falha no cadastro \r\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (ExecutionException e) {
                        Toast.makeText(getApplicationContext(), "Falha no cadastro \r\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Campos de distancia devem ser Numericos", Toast.LENGTH_SHORT).show();
                    }


                }
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
        btnGravar = (Button) findViewById(R.id.frmCadViewPontoParada_btnGravar);
        edtId.setEnabled(false);
        edtHorario.setEnabled(false);
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
        edtHorario.setEnabled(false);
        btnGravar.setVisibility((b?View.VISIBLE:View.INVISIBLE));
    }
}
