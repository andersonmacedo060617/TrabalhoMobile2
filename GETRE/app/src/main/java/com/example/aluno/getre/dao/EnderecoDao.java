package com.example.aluno.getre.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

import com.example.aluno.getre.database.DataBase;
import com.example.aluno.getre.model.Endereco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 09/11/2017.
 */

public class EnderecoDao {
    private DataBase banco;

    public EnderecoDao(DataBase banco){
        this.banco = banco;
    }

    public void inserir(Endereco endereco){
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DataBase.ENDERECO_BAIRRO, endereco.getBairro());
        cv.put(DataBase.ENDERECO_CIDADE, endereco.getCidade().getId());
        cv.put(DataBase.ENDERECO_COMPLEMENTO, endereco.getComplemento());
        cv.put(DataBase.ENDERECO_RUA, endereco.getRua());
        cv.put(DataBase.ENDERECO_NUMERO, endereco.getNumero());


        con.insert(DataBase.TABLE_ENDERECO, "", cv);
        con.close();
    }

    public void alterar(Endereco endereco) {
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DataBase.ENDERECO_NUMERO,endereco.getNumero());
        cv.put(DataBase.ENDERECO_RUA,endereco.getRua());
        cv.put(DataBase.ENDERECO_BAIRRO,endereco.getBairro());
        cv.put(DataBase.ENDERECO_COMPLEMENTO,endereco.getComplemento());
        cv.put(DataBase.ENDERECO_CIDADE,endereco.getCidade().getId());

        con.update(DataBase.TABLE_ENDERECO
                ,cv
                , DataBase.ENDERECO_ID + "=" + endereco.getId()
                , null);
        con.close();
    }

    public List<Endereco> findAll() {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " + DataBase.TABLE_ENDERECO;
            Cursor c = con.rawQuery (sql,null);
            List<Endereco> lista = new ArrayList<>();
            while (c.moveToNext()){
                Endereco endereco = new Endereco();
                endereco.setId(c.getInt( c.getColumnIndex( DataBase.ENDERECO_ID ) ));
                endereco.setBairro(c.getString( c.getColumnIndex( DataBase.ENDERECO_BAIRRO ) ));
                endereco.setComplemento(c.getString( c.getColumnIndex( DataBase.ENDERECO_COMPLEMENTO ) ));
                endereco.setNumero(c.getString( c.getColumnIndex( DataBase.ENDERECO_NUMERO ) ));
                endereco.setRua(c.getString( c.getColumnIndex( DataBase.ENDERECO_RUA ) ));
                endereco.setCidade(new CidadeDao(banco).findById(c.getInt( c.getColumnIndex( DataBase.ENDERECO_CIDADE ) )));

                lista.add(endereco);
            }
            return lista;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }

    public Endereco findById(int id) {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " +
                    DataBase.TABLE_ENTREGA +" where "+
                    DataBase.ENDERECO_ID+"='"+ id +"' ";
            Cursor c = con.rawQuery (sql,null);
            //c.moveToFirst();
            Endereco endereco = null;
            if (c.moveToNext()){
                endereco = new Endereco();
                endereco.setId(c.getInt( c.getColumnIndex( DataBase.ENDERECO_ID ) ));
                endereco.setBairro(c.getString( c.getColumnIndex( DataBase.ENDERECO_BAIRRO ) ));
                endereco.setComplemento(c.getString( c.getColumnIndex( DataBase.ENDERECO_COMPLEMENTO ) ));
                endereco.setNumero(c.getString( c.getColumnIndex( DataBase.ENDERECO_NUMERO ) ));
                endereco.setRua(c.getString( c.getColumnIndex( DataBase.ENDERECO_RUA ) ));
                endereco.setCidade(new CidadeDao(banco).findById(c.getInt( c.getColumnIndex( DataBase.ENDERECO_CIDADE ) )));

            }
            return endereco;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }
}
