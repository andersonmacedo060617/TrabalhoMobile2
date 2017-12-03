package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.getre.model.Cliente;
import com.example.aluno.getre.model.Endereco;
import com.example.aluno.getre.model.Entrega;
import com.example.aluno.getre.model.Motorista;
import com.example.aluno.getre.model.Produto;
import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.service.entrega_service.SaveEntregaThread;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class NewEntregaActivity extends AppCompatActivity {

    Button btnCadEnderecoOrigem, btnCadEnderecoDestino, btnCadProduto, btnVoltar, btnGravar;
    TextView txtNomeCliente, txtEnderecoOrigem, txtEnderecoDestino, txtProduto;
    boolean EndOrigemOk, EndDestinoOk, ProdutoOk;
    Entrega entrega  = new Entrega();
    final int CAD_ENDERECO = 51;
    final int CAD_PRODUTO = 52;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entrega);

        Usuario user = (Usuario) getIntent().getExtras().getSerializable("usuario");

        entrega.setCliente((Cliente) user);

        Biding();
        txtNomeCliente.setText(user.getNome());

        btnCadEnderecoOrigem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), CadEnderecoActivity.class);
                itn.putExtra("tpEndereco", "Origem");
                startActivityForResult(itn, CAD_ENDERECO);
            }
        });

        btnCadEnderecoDestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), CadEnderecoActivity.class);
                itn.putExtra("tpEndereco", "Destino");
                startActivityForResult(itn, CAD_ENDERECO);
            }
        });

        btnCadProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(), CadProdutoActivity.class);
                startActivityForResult(itn, CAD_PRODUTO);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    entrega.setKmPercorrido(0);
                    entrega.setCadastro(new Date());
                    entrega.setEntregaAberta(true);
                    entrega.setValor(0);
                    entrega.setMotorista(new Motorista());
                    entrega.setRegistroParadas(new ArrayList<Endereco>());
                    entrega = new SaveEntregaThread().execute(entrega).get();
                    setResult(1);
                    finish();

                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), "Falha ao Salvar. Tente Novamente. \r\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (ExecutionException e) {
                    Toast.makeText(getApplicationContext(), "Falha ao Salvar. Tente Novamente. \r\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 1){//Cadastro de Endereco Origgem
            entrega.setEnderecoOrigem((Endereco) data.getExtras().getSerializable("EnderecoOrigem"));
            txtEnderecoOrigem.setText(Integer.toString(entrega.getEnderecoOrigem().getId()) + " - " + entrega.getEnderecoOrigem().getDescricao() + " - " + entrega.getEnderecoOrigem().getDetalhe());
            EndOrigemOk = true;
        }else if(resultCode == 2){//Cadastro de Endereco Destino
            entrega.setEnderecoDestino((Endereco) data.getExtras().getSerializable("EnderecoDestino"));
            txtEnderecoDestino.setText(Integer.toString(entrega.getEnderecoDestino().getId()) + " - " + entrega.getEnderecoDestino().getDescricao() + " - " + entrega.getEnderecoDestino().getDetalhe());
            EndDestinoOk = true;
        }else if(resultCode == 3){//Cadastro de Produto
            entrega.setProduto((Produto) data.getExtras().getSerializable("Produto"));
            txtProduto.setText(Integer.toString(entrega.getProduto().getId()) + " - " + entrega.getProduto().getDescricao() + " - Peso: " + entrega.getProduto().getPeso());
            ProdutoOk = true;
        }

        //Entrega Pronta para gravar
        btnGravar.setVisibility((EndDestinoOk && EndOrigemOk && ProdutoOk)?View.VISIBLE:View.INVISIBLE);
    }

    private void Biding() {
        btnCadEnderecoOrigem = (Button) findViewById(R.id.frmNewEntrega_BtnCadEnderecoOrigem);
        btnCadEnderecoDestino = (Button) findViewById(R.id.frmNewEntrega_BtnCadEnderecoDestino);
        btnCadProduto = (Button) findViewById(R.id.frmNewEntrega_BtnCadProduto);
        btnVoltar = (Button) findViewById(R.id.frmNewEntrega_BtnVoltar);
        btnGravar = (Button) findViewById(R.id.frmNewEntrega_BtnGravar);

        txtNomeCliente = (TextView) findViewById(R.id.frmNewEntrega_TxtNomeCliente);
        txtEnderecoOrigem = (TextView) findViewById(R.id.frmNewEntrega_TxtEnderecoOrigem);
        txtEnderecoDestino = (TextView) findViewById(R.id.frmNewEntrega_TxtEnderecoDestino);
        txtProduto = (TextView) findViewById(R.id.frmNewEntrega_TxtProduto);


        btnGravar.setVisibility((EndDestinoOk && EndOrigemOk && ProdutoOk)?View.VISIBLE:View.INVISIBLE);

    }
}
