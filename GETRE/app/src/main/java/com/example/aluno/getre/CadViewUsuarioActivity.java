package com.example.aluno.getre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.model.enums.ECrud;
import com.example.aluno.getre.model.enums.ETipoUsuario;
import com.example.aluno.getre.service.usuario_service.AllUsuariosThread;
import com.example.aluno.getre.service.usuario_service.SaveUsuarioThread;

import java.time.Instant;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CadViewUsuarioActivity extends AppCompatActivity {
    EditText edtNome, edtEmail, edtLogin, edtSenha;
    Button btnGravar, btnVoltar;
    ETipoUsuario tipoUsuario;
    ECrud op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_view_usuario);

        Biding();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtNome.getText().toString().equals("") ||
                        edtSenha.getText().toString().equals("") ||
                        edtLogin.getText().toString().equals("") ||
                        edtEmail.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Todos os campos são Obrigatorios!", Toast.LENGTH_LONG).show();
                }else{
                    try {
                        List<Usuario> users = new AllUsuariosThread().execute().get();
                        boolean jaExiste = false;
                        for(Usuario user : users){
                            if(user.getLogin().equals(edtLogin.getText().toString())){
                                jaExiste = true;
                            }
                        }

                        if(jaExiste){
                            Toast.makeText(getApplicationContext(), "Login já em uso!", Toast.LENGTH_LONG).show();
                        }else{
                            Usuario u = new Usuario();
                            u.setAtivo(true);
                            u.setTipoUsuario(tipoUsuario);
                            u.setCadastro(new Date());
                            u.setLogin(edtLogin.getText().toString());
                            u.setNome(edtNome.getText().toString());
                            u.setEmail(edtEmail.getText().toString());
                            u.setSenha(edtSenha.getText().toString());

                            u = new SaveUsuarioThread().execute(u).get();

                            setResult(1);
                            finish();
                        }

                    } catch (InterruptedException e) {
                        Toast.makeText(getApplicationContext(), "Falha ao Logar \r\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (ExecutionException e) {
                        Toast.makeText(getApplicationContext(), "Falha ao Logar \r\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

    private void Biding() {
        btnGravar = (Button) findViewById(R.id.frmCadViewUsuario_btnGravar);
        btnVoltar = (Button) findViewById(R.id.frmCadViewUsuario_btnVoltar);
        edtEmail = (EditText) findViewById(R.id.frmCadViewUsuario_edtEmail);
        edtLogin = (EditText) findViewById(R.id.frmCadViewUsuario_edtLogin);
        edtSenha = (EditText) findViewById(R.id.frmCadViewUsuario_edtSenha);
        edtNome = (EditText) findViewById(R.id.frmCadViewUsuario_edtNome);

        tipoUsuario = (ETipoUsuario) getIntent().getSerializableExtra("tipoUsuario");
        op = (ECrud) getIntent().getSerializableExtra("op");
    }
}
