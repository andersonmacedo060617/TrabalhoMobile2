package com.example.aluno.getre.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

import com.example.aluno.getre.database.DataBase;
import com.example.aluno.getre.model.Cliente;
import com.example.aluno.getre.model.Motorista;
import com.example.aluno.getre.model.Usuario;
import com.example.aluno.getre.model.enums.ETipoUsuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson2 on 11/11/2017.
 */

public class UsuarioDao {
    private DataBase banco;

    public UsuarioDao(DataBase banco){
        this.banco = banco;
    }

    public void inserir(Usuario usuario){
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DataBase.USUARIO_NOME, usuario.getNome());
        cv.put(DataBase.USUARIO_LOGIN, usuario.getLogin());
        cv.put(DataBase.USUARIO_SENHA, usuario.getSenha());
        cv.put(DataBase.USUARIO_TIPOUSUARIO, usuario.getTipoUsuario().toString());
        cv.put(DataBase.USUARIO_EMAIL, usuario.getEmail());
        cv.put(DataBase.USUARIO_ATIVO, usuario.getAtivoInt());
        cv.put(DataBase.USUARIO_CADASTRO, usuario.getCadastroStr());


        con.insert(DataBase.TABLE_USUARIO, "", cv);
        con.close();
    }

    public void alterar(Usuario usuario) {
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DataBase.USUARIO_NOME, usuario.getNome());
        cv.put(DataBase.USUARIO_LOGIN, usuario.getLogin());
        cv.put(DataBase.USUARIO_SENHA, usuario.getSenha());
        cv.put(DataBase.USUARIO_EMAIL, usuario.getEmail());
        cv.put(DataBase.USUARIO_ATIVO, usuario.getAtivoInt());
        cv.put(DataBase.USUARIO_CADASTRO, usuario.getCadastroStr());
        cv.put(DataBase.USUARIO_TIPOUSUARIO, usuario.getTipoUsuario().toString());

        con.update(DataBase.TABLE_USUARIO
                ,cv
                , DataBase.USUARIO_ID + "=" + usuario.getId()
                , null);
        con.close();
    }

    public List<Usuario> findAll() {
        SQLiteDatabase con = banco.getWritableDatabase();
        List<Usuario> lista = new ArrayList<>();
        try {
            String sql = "select * from " + DataBase.TABLE_USUARIO;
            Cursor c = con.rawQuery (sql,null);

            while (c.moveToNext()){
                Usuario usuario = null;
                if(c.getString( c.getColumnIndex( DataBase.USUARIO_TIPOUSUARIO) ) == ETipoUsuario.Cliente.toString()){
                    usuario = new Cliente();
                }else{
                    usuario = new Motorista();
                }
                usuario.setId(c.getInt( c.getColumnIndex( DataBase.USUARIO_ID ) ));
                usuario.setNome(c.getString( c.getColumnIndex( DataBase.USUARIO_NOME) ));
                usuario.setLogin(c.getString( c.getColumnIndex( DataBase.USUARIO_LOGIN) ));
                usuario.setSenha(c.getString( c.getColumnIndex( DataBase.USUARIO_SENHA) ));
                usuario.setEmail(c.getString( c.getColumnIndex( DataBase.USUARIO_EMAIL) ));
                usuario.setAtivoInt(c.getInt( c.getColumnIndex( DataBase.USUARIO_ATIVO) ));
                usuario.setCadastroToDate(c.getString(c.getColumnIndex(DataBase.USUARIO_CADASTRO)));

                lista.add(usuario);
            }

        } catch (ParseException e) {
            return null;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
        return lista;
    }

    public Usuario findById(int id) {
        SQLiteDatabase con = banco.getWritableDatabase();
        Usuario usuario = null;
        try {
            String sql = "select * from " +
                    DataBase.TABLE_USUARIO +" where "+
                    DataBase.USUARIO_ID+"='"+ id +"' ";
            Cursor c = con.rawQuery (sql,null);
            //c.moveToFirst();

            if (c.moveToNext()){
                if(c.getString( c.getColumnIndex( DataBase.USUARIO_TIPOUSUARIO) ) == ETipoUsuario.Cliente.toString()){
                    usuario = new Cliente();
                }else{
                    usuario = new Motorista();
                }

                usuario.setId(c.getInt( c.getColumnIndex( DataBase.USUARIO_ID ) ));
                usuario.setNome(c.getString( c.getColumnIndex( DataBase.USUARIO_NOME) ));
                usuario.setLogin(c.getString( c.getColumnIndex( DataBase.USUARIO_LOGIN) ));
                usuario.setSenha(c.getString( c.getColumnIndex( DataBase.USUARIO_SENHA) ));
                usuario.setEmail(c.getString( c.getColumnIndex( DataBase.USUARIO_EMAIL) ));
                usuario.setAtivoInt(c.getInt( c.getColumnIndex( DataBase.USUARIO_ATIVO) ));
                usuario.setCadastroToDate(c.getString(c.getColumnIndex(DataBase.USUARIO_CADASTRO)));

            }

        } catch (ParseException e) {
            return null;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
        return usuario;

    }
}
