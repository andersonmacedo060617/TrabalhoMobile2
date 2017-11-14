package com.example.aluno.getre.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;

import com.example.aluno.getre.database.DataBase;
import com.example.aluno.getre.model.Cliente;
import com.example.aluno.getre.model.Entrega;
import com.example.aluno.getre.model.Motorista;
import com.example.aluno.getre.model.enums.EStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Anderson2 on 11/11/2017.
 */

public class EntregaDao {
    private DataBase banco;

    public EntregaDao(DataBase banco){
        this.banco = banco;
    }

    public void inserir(Entrega entrega){
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DataBase.ENTREGA_CLIENTE, entrega.getCliente().getId());
        cv.put(DataBase.ENTREGA_MOTORISTA, entrega.getMotorista().getId());
        cv.put(DataBase.ENTREGA_CUSTOTOTAL, entrega.getCustoTotal());
        cv.put(DataBase.ENTREGA_PESO, entrega.getPeso());
        cv.put(DataBase.ENTREGA_DISTANCIATOTAL, entrega.getDistanciaTotal());
        cv.put(DataBase.ENTREGA_ENDERECO_ORIGEM, entrega.getEnderecoOrigem().getId());
        cv.put(DataBase.ENTREGA_ENDERECO_DESTINO, entrega.getEnderecoDestino().getId());
        cv.put(DataBase.ENTREGA_ROTA, entrega.getRota().getId());
        cv.put(DataBase.ENTREGA_PORTEVEICULO, entrega.getPorteVeiculo().getId());
        cv.put(DataBase.ENTREGA_PORTEVEICULO, entrega.getDataSaidaStr());
        cv.put(DataBase.ENTREGA_STATUS, entrega.getStatus().toString());

        con.insert(DataBase.TABLE_ENTREGA, "", cv);
        con.close();
    }

    public void alterar(Entrega entrega) {
        SQLiteDatabase con = banco.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(DataBase.ENTREGA_CLIENTE, entrega.getCliente().getId());
        cv.put(DataBase.ENTREGA_MOTORISTA, entrega.getMotorista().getId());
        cv.put(DataBase.ENTREGA_CUSTOTOTAL, entrega.getCustoTotal());
        cv.put(DataBase.ENTREGA_PESO, entrega.getPeso());
        cv.put(DataBase.ENTREGA_DISTANCIATOTAL, entrega.getDistanciaTotal());
        cv.put(DataBase.ENTREGA_ENDERECO_ORIGEM, entrega.getEnderecoOrigem().getId());
        cv.put(DataBase.ENTREGA_ENDERECO_DESTINO, entrega.getEnderecoDestino().getId());
        cv.put(DataBase.ENTREGA_ROTA, entrega.getRota().getId());
        cv.put(DataBase.ENTREGA_PORTEVEICULO, entrega.getPorteVeiculo().getId());
        cv.put(DataBase.ENTREGA_PORTEVEICULO, entrega.getDataSaidaStr());
        cv.put(DataBase.ENTREGA_STATUS, entrega.getStatus().toString());

        con.update(DataBase.TABLE_ENTREGA
                ,cv
                , DataBase.ENTREGA_ID + "=" + entrega.getId()
                , null);
        con.close();
    }

    public List<Entrega> findAll() {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " + DataBase.TABLE_ENTREGA;
            Cursor c = con.rawQuery (sql,null);
            List<Entrega> lista = new ArrayList<>();
            while (c.moveToNext()){
                Entrega entrega = new Entrega();
                entrega.setId(c.getInt( c.getColumnIndex( DataBase.ENTREGA_ID ) ));
                entrega.setCliente((Cliente) new UsuarioDao(banco).findById(c.getInt( c.getColumnIndex(DataBase.ENTREGA_CLIENTE))));
                entrega.setMotorista((Motorista) new UsuarioDao(banco).findById(c.getInt( c.getColumnIndex(DataBase.ENTREGA_MOTORISTA))));
                entrega.setCustoTotal(c.getDouble(c.getColumnIndex(DataBase.ENTREGA_CUSTOTOTAL)));
                entrega.setPeso(c.getDouble(c.getColumnIndex(DataBase.ENTREGA_PESO)));
                entrega.setDistanciaTotal(c.getDouble(c.getColumnIndex(DataBase.ENTREGA_DISTANCIATOTAL)));
                entrega.setEnderecoOrigem(new EnderecoDao(banco).findById(c.getInt(c.getColumnIndex(DataBase.ENTREGA_ENDERECO_ORIGEM))));
                entrega.setEnderecoDestino(new EnderecoDao(banco).findById(c.getInt(c.getColumnIndex(DataBase.ENTREGA_ENDERECO_DESTINO))));
                entrega.setRota(new RotaDao(banco).findById(c.getInt(c.getColumnIndex(DataBase.ENTREGA_ROTA))));
                entrega.setPorteVeiculo(new PorteVeiculoDao(banco).findById(c.getInt(c.getColumnIndex(DataBase.ENTREGA_PORTEVEICULO))));

                EStatus status;
                if(c.getString(c.getColumnIndex(DataBase.ENTREGA_STATUS)) == EStatus.Contratando.toString()){
                    status = EStatus.Contratando;
                }else if(c.getString(c.getColumnIndex(DataBase.ENTREGA_STATUS)) == EStatus.Enviando.toString()){
                    status = EStatus.Enviando;
                }else{
                    status = EStatus.Entregue;
                }

                entrega.setStatus(status);
                try {
                    entrega.setDataSaidaToDate(c.getString(c.getColumnIndex(DataBase.ENTREGA_DATASAIDA)));
                } catch (java.text.ParseException e) {
                    entrega.setDataSaida(new Date());
                }


                lista.add(entrega);
            }
            return lista;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }

    public Entrega findById(int id) {
        SQLiteDatabase con = banco.getWritableDatabase();
        try {
            String sql = "select * from " +
                    DataBase.TABLE_ENTREGA +" where "+
                    DataBase.ENTREGA_ID+"='"+ id +"' ";
            Cursor c = con.rawQuery (sql,null);
            //c.moveToFirst();
            Entrega entrega = null;
            if (c.moveToNext()){
                entrega = new Entrega();
                entrega.setId(c.getInt( c.getColumnIndex( DataBase.ENTREGA_ID ) ));
                entrega.setCliente((Cliente) new UsuarioDao(banco).findById(c.getInt( c.getColumnIndex(DataBase.ENTREGA_CLIENTE))));
                entrega.setMotorista((Motorista) new UsuarioDao(banco).findById(c.getInt( c.getColumnIndex(DataBase.ENTREGA_MOTORISTA))));
                entrega.setCustoTotal(c.getDouble(c.getColumnIndex(DataBase.ENTREGA_CUSTOTOTAL)));
                entrega.setPeso(c.getDouble(c.getColumnIndex(DataBase.ENTREGA_PESO)));
                entrega.setDistanciaTotal(c.getDouble(c.getColumnIndex(DataBase.ENTREGA_DISTANCIATOTAL)));
                entrega.setEnderecoOrigem(new EnderecoDao(banco).findById(c.getInt(c.getColumnIndex(DataBase.ENTREGA_ENDERECO_ORIGEM))));
                entrega.setEnderecoDestino(new EnderecoDao(banco).findById(c.getInt(c.getColumnIndex(DataBase.ENTREGA_ENDERECO_DESTINO))));
                entrega.setRota(new RotaDao(banco).findById(c.getInt(c.getColumnIndex(DataBase.ENTREGA_ROTA))));
                entrega.setPorteVeiculo(new PorteVeiculoDao(banco).findById(c.getInt(c.getColumnIndex(DataBase.ENTREGA_PORTEVEICULO))));

                EStatus status;
                if(c.getString(c.getColumnIndex(DataBase.ENTREGA_STATUS)) == EStatus.Contratando.toString()){
                    status = EStatus.Contratando;
                }else if(c.getString(c.getColumnIndex(DataBase.ENTREGA_STATUS)) == EStatus.Enviando.toString()){
                    status = EStatus.Enviando;
                }else{
                    status = EStatus.Entregue;
                }

            }
            return entrega;
        } catch (ParseException e) {
            return null;
        } finally {
            con.close();
        }

    }
}
