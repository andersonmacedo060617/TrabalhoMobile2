package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.getre.model.Endereco;
import com.example.aluno.getre.service.pontosParada_service.SaveEnderecoThread;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class CadEnderecoActivity extends AppCompatActivity {

    String propriedade;
    Button btnVoltar, btnGravar;
    TextView edtDescricao, edtDetalhe;
    Endereco endereco = new Endereco();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_endereco);

        Biding();
        propriedade = getIntent().getExtras().getString("tpEndereco");

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endereco.setDescricao(edtDescricao.getText().toString());
                endereco.setDetalhe(edtDetalhe.getText().toString());
                endereco.setHorrario(new Date());
                endereco.setKmPercorrido(0);
                endereco.setKmFaltante(0);
                try {
                    endereco = new SaveEnderecoThread().execute(endereco).get();
                    Intent itn = new Intent();
                    if(propriedade.equals("Origem")){
                        itn.putExtra("EnderecoOrigem", endereco);
                        setResult(1, itn);
                    }else{
                        itn.putExtra("EnderecoDestino", endereco);
                        setResult(2, itn);
                    }
                    finish();

                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), "Falha ao Salvar. Tente Novamente. \r\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (ExecutionException e) {
                    Toast.makeText(getApplicationContext(), "Falha ao Salvar. Tente Novamente. \r\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });

    }

    private void Biding() {
        edtDescricao = (TextView) findViewById(R.id.frmCadEndereco_edtDescricao);
        edtDetalhe = (TextView) findViewById(R.id.frmCadEndereco_edtDetalhe);

        btnVoltar = (Button) findViewById(R.id.frmCadEndereco_BtnVoltar);
        btnGravar = (Button) findViewById(R.id.frmCadEndereco_BtnGravar);
    }
}
