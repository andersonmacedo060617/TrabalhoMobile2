package com.example.aluno.getre.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

import com.example.aluno.getre.database.DataBase;
import com.example.aluno.getre.model.PorteVeiculo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 09/11/2017.
 */

public class PorteVeiculoDao {
    private DataBase banco;

    public PorteVeiculoDao(DataBase banco){
        this.banco = banco;
    }

    public void inserir(PorteVeiculo porteVeiculo){
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DataBase.PORTEVEICULO_DESCRICAO, porteVeiculo.getDescricao());
        cv.put(DataBase.PORTEVEICULO_VEICULO, porteVeiculo.getVeiculo());

        con.insert(DataBase.TABLE_PORTEVEICULO, "", cv);
        con.close();
    }

    public void alterar(PorteVeiculo porteVeiculo) {
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DataBase.PORTEVEICULO_DESCRICAO, porteVeiculo.getDescricao());
        cv.put(DataBase.PORTEVEICULO_VEICULO, porteVeiculo.getVeiculo());

        con.update(DataBase.TABLE_PORTEVEICULO
                ,cv
                , DataBase.PORTEVEICULO_ID + "=" + porteVeiculo.getId()
                , null);
        con.close();
    }

    public List<PorteVeiculo> findAll() {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " + DataBase.TABLE_PORTEVEICULO;
            Cursor c = con.rawQuery (sql,null);
            List<PorteVeiculo> lista = new ArrayList<>();
            while (c.moveToNext()){
                PorteVeiculo porteVeiculo = new PorteVeiculo();
                porteVeiculo.setId(c.getInt( c.getColumnIndex( DataBase.PORTEVEICULO_ID ) ));
                porteVeiculo.setDescricao(c.getString( c.getColumnIndex( DataBase.PORTEVEICULO_DESCRICAO ) ));
                porteVeiculo.setVeiculo(c.getString( c.getColumnIndex( DataBase.PORTEVEICULO_VEICULO) ));

                lista.add(porteVeiculo);
            }
            return lista;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }

    public PorteVeiculo findById(int id) {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " +
                    DataBase.TABLE_PORTEVEICULO +" where "+
                    DataBase.PORTEVEICULO_ID+"='"+ id +"' ";
            Cursor c = con.rawQuery (sql,null);
            //c.moveToFirst();
            PorteVeiculo porteVeiculo = null;
            if (c.moveToNext()){
                porteVeiculo = new PorteVeiculo();
                porteVeiculo.setId(c.getInt( c.getColumnIndex( DataBase.PORTEVEICULO_ID ) ));
                porteVeiculo.setDescricao(c.getString( c.getColumnIndex( DataBase.PORTEVEICULO_DESCRICAO) ));
                porteVeiculo.setVeiculo(c.getString( c.getColumnIndex( DataBase.PORTEVEICULO_VEICULO ) ));

            }
            return porteVeiculo;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }
}
