package com.example.iasmimc.sendemailmessage;
import  android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by iasmim.c on 2/2/2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private  String[] scriptSQLCreate;
    private  String scriptSQLDelete;

    SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String scriptSQLDelete)
    {
        super(context, nomeBanco, null, versaoBanco);
        this.scriptSQLCreate = scriptSQLCreate;
        this.scriptSQLDelete = scriptSQLDelete;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        int qtScript = scriptSQLCreate.length;

        for (int i = 0; i < qtScript ; i++) {

            String sql = scriptSQLCreate[i];
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(scriptSQLDelete);
        onCreate(db);
    }
}
