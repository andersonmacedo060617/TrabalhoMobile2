package com.example.aluno.getre.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

import com.example.aluno.getre.database.DataBase;
import com.example.aluno.getre.model.Cidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 09/11/2017.
 */

public class CidadeDao {
    private DataBase banco;

    public CidadeDao(DataBase banco){
        this.banco = banco;
    }

    public void inserir(Cidade cidade){
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DataBase.CIDADE_NOME, cidade.getNome());
        cv.put(DataBase.CIDADE_UF, cidade.getUF());

        con.insert(DataBase.TABLE_CIDADE, "", cv);
        con.close();
    }

    public void alterar(Cidade cidade) {
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DataBase.CIDADE_NOME,cidade.getNome());
        cv.put(DataBase.CIDADE_UF,cidade.getUF());

        con.update(DataBase.TABLE_CIDADE
                ,cv
                , DataBase.CIDADE_ID + "=" + cidade.getId()
                , null);
        con.close();
    }

    public List<Cidade> findAll() {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " + DataBase.TABLE_CIDADE;
            Cursor c = con.rawQuery (sql,null);
            List<Cidade> lista = new ArrayList<>();
            while (c.moveToNext()){
                Cidade cidade = null;
                cidade.setId(c.getInt( c.getColumnIndex( DataBase.CIDADE_ID ) ));
                cidade.setNome(c.getString( c.getColumnIndex( DataBase.CIDADE_NOME ) ));
                cidade.setUF(c.getString( c.getColumnIndex( DataBase.CIDADE_UF ) ));

                lista.add(cidade);
            }
            return lista;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }

    public Cidade findById(int id) {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " +
                    DataBase.TABLE_CIDADE +" where "+
                    DataBase.CIDADE_ID+"='"+ id +"' ";
            Cursor c = con.rawQuery (sql,null);
            //c.moveToFirst();
            Cidade cidade = null;
            if (c.moveToNext()){
                cidade.setId(c.getInt( c.getColumnIndex( DataBase.CIDADE_ID ) ));
                cidade.setNome(c.getString( c.getColumnIndex( DataBase.CIDADE_NOME ) ));
                cidade.setUF(c.getString( c.getColumnIndex( DataBase.CIDADE_UF ) ));

            }
            return cidade;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }

}
