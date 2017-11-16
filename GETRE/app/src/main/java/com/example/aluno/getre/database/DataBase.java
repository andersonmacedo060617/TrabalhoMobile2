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
    public static final String USUARIO_EMAIL = "email";
    public static final String USUARIO_LOGIN = "login";
    public static final String USUARIO_SENHA = "senha";
    public static final String USUARIO_ATIVO = "ativo";
    public static final String USUARIO_CADASTRO = "cadastro";
    public static final String USUARIO_TIPOUSUARIO = "tipousuario";



    public static final String CREATE_TABLE_USUARIO = "create table " + TABLE_USUARIO + " " +
            "( " + USUARIO_ID + " integer primary key autoincrement, " +
            "  " + USUARIO_NOME + " text not null, " +
            "  " + USUARIO_LOGIN + " text not null, " +
            "  " + USUARIO_SENHA + " text not null, " +
            "  " + USUARIO_EMAIL + " text not null, " +
            "  " + USUARIO_ATIVO + " integer not null, " +
            "  " + USUARIO_CADASTRO + " text not null, " +
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
    public static final String TABLE_ENDERECO = "endereco";
    public static final String ENDERECO_ID = "id";
    public static final String ENDERECO_NUMERO = "numero";
    public static final String ENDERECO_COMPLEMENTO = "complemento";
    public static final String ENDERECO_BAIRRO = "bairro";
    public static final String ENDERECO_CIDADE = "id_cidade";
    public static final String ENDERECO_RUA = "rua";

    public static final String CREATE_TABLE_ENDERECO = "create table " + TABLE_ENDERECO + " " +
            "( " + ENDERECO_ID + " integer primary key autoincrement, " +
            "  " + ENDERECO_RUA + " text, " +
            "  " + ENDERECO_COMPLEMENTO + " text, " +
            "  " + ENDERECO_BAIRRO + " text, " +
            "  " + ENDERECO_NUMERO + " text, " +
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

    //PORTE VEICULO
    public static final String TABLE_PORTEVEICULO = "porte_veiculo";
    public static final String PORTEVEICULO_ID = "id";
    public static final String PORTEVEICULO_DESCRICAO = "descricao";
    public static final String PORTEVEICULO_VEICULO = "veiculo";

    public static final String CREATE_TABLE_PORTEVICULO = "create table " + TABLE_PORTEVEICULO + " " +
            "( " + PORTEVEICULO_ID + " integer primary key autoincrement, " +
            "  " + PORTEVEICULO_DESCRICAO + " text, " +
            "  " + PORTEVEICULO_VEICULO + " text " +
            ");";

    //PORTE REGISTRO PONTO PARADA
    public static final String TABLE_REGISTROPONTOPARADA = "registro_ponto_parada";
    public static final String REGISTROPONTOPARADA_ID = "id";
    public static final String REGISTROPONTOPARADA_DESCRICAO = "descricao";
    public static final String REGISTROPONTOPARADA_PONTOPARADA = "id_ponto_parada";
    public static final String REGISTROPONTOPARADA_ENTREGA = "id_entrega";
    public static final String REGISTROPONTOPARADA_DATAHORACHEGADA = "datahora_chegada";

    public static final String CREATE_TABLE_REGISTROPONTOPARADA = "create table " + TABLE_REGISTROPONTOPARADA + " " +
            "( " + REGISTROPONTOPARADA_ID + " integer primary key autoincrement, " +
            "  " + REGISTROPONTOPARADA_DESCRICAO + " text, " +
            "  " + REGISTROPONTOPARADA_PONTOPARADA + " integer, " +
            "  " + REGISTROPONTOPARADA_ENTREGA + " integer, " +
            "  " + REGISTROPONTOPARADA_DATAHORACHEGADA + " text " +
            ");";

    //PORTE ROTA
    public static final String TABLE_ROTA = "rota";
    public static final String ROTA_ID = "id";
    public static final String ROTA_NOME = "nome";

    public static final String CREATE_TABLE_ROTA = "create table " + TABLE_ROTA + " " +
            "( " + ROTA_ID + " integer primary key autoincrement, " +
            "  " + ROTA_NOME + " text " +
            ");";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CIDADE);
        sqLiteDatabase.execSQL(CREATE_TABLE_ENDERECO);
        sqLiteDatabase.execSQL(CREATE_TABLE_ENTREGA);
        sqLiteDatabase.execSQL(CREATE_TABLE_PONTOPARADA);
        sqLiteDatabase.execSQL(CREATE_TABLE_PORTEVICULO);
        sqLiteDatabase.execSQL(CREATE_TABLE_REGISTROPONTOPARADA);
        sqLiteDatabase.execSQL(CREATE_TABLE_ROTA);
        sqLiteDatabase.execSQL(CREATE_TABLE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table "+TABLE_CIDADE);
        sqLiteDatabase.execSQL("drop table "+TABLE_ENDERECO);
        sqLiteDatabase.execSQL("drop table "+TABLE_ENTREGA);
        sqLiteDatabase.execSQL("drop table "+TABLE_PONTOPARADA);
        sqLiteDatabase.execSQL("drop table "+TABLE_PORTEVEICULO);
        sqLiteDatabase.execSQL("drop table "+TABLE_REGISTROPONTOPARADA);
        sqLiteDatabase.execSQL("drop table "+TABLE_ROTA);
        sqLiteDatabase.execSQL("drop table "+TABLE_USUARIO);

        onCreate(sqLiteDatabase);
    }
}
