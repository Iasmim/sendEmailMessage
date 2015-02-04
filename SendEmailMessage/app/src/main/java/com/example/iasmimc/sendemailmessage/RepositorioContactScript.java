package com.example.iasmimc.sendemailmessage;

import android.content.Context;

/**
 * Created by iasmim.c on 2/2/2015.
 */
public class RepositorioContactScript extends RepositorioContact {

    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXIST contatos";

    private static final String[] SCRIPT_DATABASE_CREATE = new String[]
            {
                    "create table contatos ( _id integer primary key autoincrement, name text not null,contact text not null,number text not null);"
                    , "insert into contatos (name,contact,number) values('iasmim cunha','iasmim.c@samsung.com','982615234');"
            };
    private static final String NOME_BANCO = "baco_dados";
    private static final int VERSAO_BANCO = 1;
    public static final String TABELA_CONTATOS = "contatos";

    private SQLiteHelper dbHelper;

    public RepositorioContactScript(Context ctx) {
        dbHelper = new SQLiteHelper(ctx, RepositorioContactScript.NOME_BANCO, RepositorioContactScript.VERSAO_BANCO
                , RepositorioContactScript.SCRIPT_DATABASE_CREATE, RepositorioContactScript.SCRIPT_DATABASE_DELETE);

        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void fechar() {
        super.fechar();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }


}
