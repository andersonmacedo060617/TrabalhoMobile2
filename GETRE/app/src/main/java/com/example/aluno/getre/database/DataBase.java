package com.example.aluno.getre.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aluno on 08/11/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    public static final String dbName = "bancoGETRE.db";
    public static final int dbVersion = 1;
    public static Context context;

    public DataBase(Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }


    //USUARIO
    public static final String TABLE_USUARIO = "usuario";
    public static final String USUARIO_ID = "id";
    public static final String USUARIO_NOME = "nome";
    public static final String USUARIO_LOGIN = "login";
    public static final String USUARIO_SENHA = "senha";
    public static final String USUARIO_TIPOUSUARIO = "tipousuario";

    public static final String CREATE_TABLE_USUARIO = "create table " + TABLE_USUARIO + " " +
            "( " + USUARIO_ID + " integer primary key autoincrement, " +
            "  " + USUARIO_NOME + " text not null, " +
            "  " + USUARIO_LOGIN + " text not null, " +
            "  " + USUARIO_SENHA + " text not null, " +
            "  " + USUARIO_TIPOUSUARIO + " text not null " +
            ");";

    //CIDADE
    public static final String TABLE_CIDADE = "cidade";
    public static final String CIDADE_ID = "id";
    public static final String CIDADE_NOME = "nome";
    public static final String CIDADE_UF = "uf";

    public static final String CREATE_TABLE_CIDADE = "create table " + TABLE_CIDADE + " " +
            "( " + CIDADE_ID + " integer primary key autoincrement, " +
            "  " + CIDADE_NOME + " text not null, " +
            "  " + CIDADE_UF + " text not null " +
            ");";


    //ENDERECO
    public static final String TABLE_EDERECO = "endereco";
    public static final String ENDERECO_ID = "id";
    public static final String ENDERECO_RUA = "rua";
    public static final String ENDERECO_COMPLEMENTO = "complemento";
    public static final String ENDERECO_BAIRRO = "bairro";
    public static final String ENDERECO_CIDADE = "id_cidade";

    public static final String CREATE_TABLE_ENDERECO = "create table " + TABLE_EDERECO + " " +
            "( " + ENDERECO_ID + " integer primary key autoincrement, " +
            "  " + ENDERECO_RUA + " text not null, " +
            "  " + ENDERECO_COMPLEMENTO + " text not null, " +
            "  " + ENDERECO_BAIRRO + " text not null, " +
            "  " + ENDERECO_CIDADE + " integer " +
            ");";

    //ENTREGA
    public static final String TABLE_ENTREGA = "entrega";
    public static final String ENTREGA_ID = "id";
    public static final String ENTREGA_CLIENTE = "id_cliente";
    public static final String ENTREGA_MOTORISTA = "id_motorista";
    public static final String ENTREGA_CUSTOTOTAL = "custototal";
    public static final String ENTREGA_PESO = "peso";
    public static final String ENTREGA_DISTANCIATOTAL = "distanciatotal";
    public static final String ENTREGA_ENDERECO_ORIGEM = "id_endereco_origem";
    public static final String ENTREGA_ENDERECO_DESTINO = "id_endereco_destino";
    public static final String ENTREGA_ROTA = "id_rota";
    public static final String ENTREGA_PORTEVEICULO = "id_porteveiculo";
    public static final String ENTREGA_DATASAIDA = "id_datasaida";
    public static final String ENTREGA_STATUS = "status";

    public static final String CREATE_TABLE_ENTREGA = "create table " + TABLE_ENTREGA + " " +
            "( " + ENTREGA_ID + " integer primary key autoincrement, " +
            "  " + ENTREGA_CLIENTE + " integer not null, " +
            "  " + ENTREGA_MOTORISTA + " integer, " +
            "  " + ENTREGA_CUSTOTOTAL + " real not null, " +
            "  " + ENTREGA_PESO + " real, " +
            "  " + ENTREGA_DISTANCIATOTAL + " real, " +
            "  " + ENTREGA_ENDERECO_ORIGEM + " integer, " +
            "  " + ENTREGA_ENDERECO_DESTINO + " integer, " +
            "  " + ENTREGA_ROTA + " integer, " +
            "  " + ENTREGA_PORTEVEICULO + " integer, " +
            "  " + ENTREGA_DATASAIDA + " text, " +
            "  " + ENTREGA_STATUS + " text " +
            ");";

    //PONTO PARADA
    public static final String TABLE_PONTOPARADA = "ponto_parada";
    public static final String PONTOPARADA_ID = "id";
    public static final String PONTOPARADA_CIDADE = "id_cidade";
    public static final String PONTOPARADA_ROTA = "id_rota";
    public static final String PONTOPARADA_ORDEM = "ordem";

    public static final String CREATE_TABLE_PONTOPARADA = "create table " + TABLE_PONTOPARADA + " " +
            "( " + PONTOPARADA_ID + " integer primary key autoincrement, " +
            "  " + PONTOPARADA_CIDADE + " integer, " +
            "  " + PONTOPARADA_ROTA + " integer, " +
            "  " + PONTOPARADA_ORDEM + " integer " +
            ");";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
