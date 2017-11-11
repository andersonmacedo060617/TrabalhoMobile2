package com.example.aluno.getre.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

import com.example.aluno.getre.database.DataBase;
import com.example.aluno.getre.model.PontoParada;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anderson2 on 11/11/2017.
 */

public class PontoParadaDao {
    private DataBase banco;

    public PontoParadaDao(DataBase banco){
        this.banco = banco;
    }

    public void inserir(PontoParada pontoParada){
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DataBase.PONTOPARADA_CIDADE, pontoParada.getCidade().getId());
        cv.put(DataBase.PONTOPARADA_ORDEM, pontoParada.getOrdem());
        cv.put(DataBase.PONTOPARADA_ROTA, pontoParada.getRota().getId());



        con.insert(DataBase.TABLE_PONTOPARADA, "", cv);
        con.close();
    }

    public void alterar(PontoParada pontoParada) {
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DataBase.PONTOPARADA_CIDADE, pontoParada.getCidade().getId());
        cv.put(DataBase.PONTOPARADA_ROTA, pontoParada.getRota().getId());
        cv.put(DataBase.PONTOPARADA_ORDEM, pontoParada.getOrdem());

        con.update(DataBase.TABLE_PONTOPARADA
                ,cv
                , DataBase.PONTOPARADA_ID + "=" + pontoParada.getId()
                , null);
        con.close();
    }

    public List<PontoParada> findAll() {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " + DataBase.TABLE_PONTOPARADA;
            Cursor c = con.rawQuery (sql,null);
            List<PontoParada> lista = new ArrayList<>();
            while (c.moveToNext()){
                PontoParada pontoParada = new PontoParada();
                pontoParada.setId(c.getInt( c.getColumnIndex( DataBase.PONTOPARADA_ID ) ));
                pontoParada.setCidade(new CidadeDao(banco).findById(c.getInt( c.getColumnIndex( DataBase.PONTOPARADA_CIDADE))));
                pontoParada.setRota(new RotaDao(banco).findById(c.getInt( c.getColumnIndex( DataBase.PONTOPARADA_ROTA))));
                pontoParada.setOrdem(c.getInt(c.getColumnIndex(DataBase.PONTOPARADA_ORDEM)));

                lista.add(pontoParada);
            }
            return lista;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }

    public PontoParada findById(int id) {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " +
                    DataBase.TABLE_PONTOPARADA +" where "+
                    DataBase.PONTOPARADA_ID+"='"+ id +"' ";
            Cursor c = con.rawQuery (sql,null);
            //c.moveToFirst();
            PontoParada pontoParada = null;
            if (c.moveToNext()){
                pontoParada = new PontoParada();
                pontoParada.setId(c.getInt( c.getColumnIndex( DataBase.PONTOPARADA_ID ) ));
                pontoParada.setOrdem(c.getInt( c.getColumnIndex( DataBase.PONTOPARADA_ORDEM) ));
                pontoParada.setCidade(new CidadeDao(banco).findById(c.getInt( c.getColumnIndex( DataBase.PONTOPARADA_CIDADE))));
                pontoParada.setRota(new RotaDao(banco).findById(c.getInt( c.getColumnIndex( DataBase.PONTOPARADA_ROTA))));

            }
            return pontoParada;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }
}
