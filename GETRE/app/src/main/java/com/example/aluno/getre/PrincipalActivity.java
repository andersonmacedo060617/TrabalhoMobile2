package com.example.aluno.getre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aluno.getre.dao.UsuarioDao;
import com.example.aluno.getre.database.DataBase;
import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.model.enums.ETipoUsuario;

public class PrincipalActivity extends AppCompatActivity {


    final int LOGIN_VIEW = 1;
    final int MENU_CLIENTE_VIEW = 2;
    final int MENU_MOTORISTA_VIEW = 3;
    final int MENU_ADMINISTRADOR_VIEW = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        DataBase conect = new DataBase(getApplicationContext());

        UsuarioDao uDao = new UsuarioDao(conect);

        CallLoginView();

    }

    private void CallLoginView() {
        Intent itn = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(itn, LOGIN_VIEW);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_VIEW){
            if(resultCode == 4){
                //Administrador
//                Toast.makeText(getApplicationContext(),"Usuario Administrador", Toast.LENGTH_LONG).show();
                Usuario user = (Usuario)data.getExtras().getSerializable("usuario");
                Intent itn = new Intent(getApplicationContext(), MainAdministradorActivity.class);
                itn.putExtra("usuario", user);
                startActivityForResult(itn, MENU_ADMINISTRADOR_VIEW);

            }else if(resultCode == 3){
                //Motorista
//                Toast.makeText(getApplicationContext(),"Usuario Motorista", Toast.LENGTH_LONG).show();
                Usuario user = (Usuario)data.getExtras().getSerializable("usuario");
                Intent itn = new Intent(getApplicationContext(), MainMotoristaActivity.class);
                itn.putExtra("usuario", user);
                startActivityForResult(itn, MENU_MOTORISTA_VIEW);
            }else if(resultCode == 2){
                //Cliente
//                Toast.makeText(getApplicationContext(),"Usuario Cliente", Toast.LENGTH_LONG).show();
                Usuario user = (Usuario)data.getExtras().getSerializable("usuario");
                Intent itn = new Intent(getApplicationContext(), MainClienteActivity.class);
                itn.putExtra("usuario", user);
                startActivityForResult(itn, MENU_CLIENTE_VIEW);
            }else{
                //Usuario não Logado
                CallLoginView();
            }
        }
    }
}
