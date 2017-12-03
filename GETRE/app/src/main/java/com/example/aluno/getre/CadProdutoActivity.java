package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.getre.model.Produto;
import com.example.aluno.getre.service.produto_service.SaveProdutoThread;

import java.util.concurrent.ExecutionException;

public class CadProdutoActivity extends AppCompatActivity {

    Button btnVoltar, btnGravar;
    TextView edtDescricao, edtPeso;
    Produto produto = new Produto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);

        Biding();

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
                produto.setDescricao(edtDescricao.getText().toString());
                produto.setPeso(Double.parseDouble(edtPeso.getText().toString()));
                try {
                    produto = new SaveProdutoThread().execute(produto).get();
                    if(produto.getId()!= 0){
                        Intent itn = new Intent();
                        itn.putExtra("Produto", produto);
                        setResult(3, itn);
                        finish();
                    }
                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), "Falha ao Salvar. Tente Novamente. \r\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (ExecutionException e) {
                    Toast.makeText(getApplicationContext(), "Falha ao Salvar. Tente Novamente. \r\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void Biding() {
        btnGravar = (Button) findViewById(R.id.frmCadProduto_BtnGravar);
        btnVoltar = (Button) findViewById(R.id.frmCadProduto_BtnVoltar);

        edtDescricao = (TextView) findViewById(R.id.frmCadProduto_edtDescricao);
        edtPeso = (TextView) findViewById(R.id.frmCadProduto_edtPeso);

    }
}
