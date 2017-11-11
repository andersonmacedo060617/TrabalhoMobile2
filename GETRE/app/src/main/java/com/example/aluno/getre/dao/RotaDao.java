package com.example.aluno.getre.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

import com.example.aluno.getre.database.DataBase;
import com.example.aluno.getre.model.Rota;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson2 on 10/11/2017.
 */

public class RotaDao {
    private DataBase banco;

    public RotaDao(DataBase banco){
        this.banco = banco;
    }

    public void inserir(Rota rota){
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DataBase.ROTA_NOME, rota.getNome());


        con.insert(DataBase.TABLE_ROTA, "", cv);
        con.close();
    }

    public void alterar(Rota rota) {
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DataBase.ROTA_NOME, rota.getNome());

        con.update(DataBase.TABLE_ROTA
                ,cv
                , DataBase.ROTA_ID + "=" + rota.getId()
                , null);
        con.close();
    }

    public List<Rota> findAll() {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " + DataBase.TABLE_ROTA;
            Cursor c = con.rawQuery (sql,null);
            List<Rota> lista = new ArrayList<>();
            while (c.moveToNext()){
                Rota rota = new Rota();
                rota.setId(c.getInt( c.getColumnIndex( DataBase.ROTA_ID ) ));
                rota.setNome(c.getString( c.getColumnIndex( DataBase.ROTA_NOME) ));


                lista.add(rota);
            }
            return lista;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }

    public Rota findById(int id) {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " +
                    DataBase.TABLE_ROTA +" where "+
                    DataBase.ROTA_ID+"='"+ id +"' ";
            Cursor c = con.rawQuery (sql,null);
            //c.moveToFirst();
            Rota rota = null;
            if (c.moveToNext()){
                rota = new Rota();
                rota.setId(c.getInt( c.getColumnIndex( DataBase.ROTA_ID ) ));
                rota.setNome(c.getString( c.getColumnIndex( DataBase.ROTA_NOME) ));

            }
            return rota;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }
}
