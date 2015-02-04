package com.example.iasmimc.sendemailmessage;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iasmim.c on 2/2/2015.
 */
public class RepositorioContact {
    private static final String NOME_BANCO = "baco_dados";
    public static final String NOME_TABELA = "contatos";
    protected SQLiteDatabase db;


    public RepositorioContact(Context ctx) {
        db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
    }
    public RepositorioContact() {

    }

    public long salvar(Contact contact)
    {
        long id = contact.getId();

        if(id != 0)
            id = inserir(contact);


        return  id;
    }

    public  long inserir(Contact contact)
    {
        ContentValues values = new ContentValues();
        values.put(contact.NAME, contact.getName());
        values.put(contact.CONTACT, contact.getContact());
        values.put(contact.NUMBER, contact.getNumber());

      long id = db.insert(NOME_TABELA, "",values);

        return  id;
    }

    public List<Contact> ListarContatos()
    {
        Cursor c = db.query(NOME_TABELA, new String[]{"_id","name","contact","number"}, null, null, null, null, null, null);
        List<Contact> contatos = new ArrayList<Contact>();
        if(c.moveToFirst())
        {
            int idxId = c.getColumnIndex("_id");
            int idxName = c.getColumnIndex("name");
            int idxContact = c.getColumnIndex("contact");
            int idxNumber = c.getColumnIndex("number");

            do{

                Contact contact = new Contact();
                contact.setId(c.getLong(idxId));
                contact.setName(c.getString(idxName));
                contact.setContact(c.getString(idxContact));
                contact.setNumber(c.getString(idxNumber));
                contatos.add(contact);
            }
            while (c.moveToNext());
        }
        return  contatos;

    }

    public int deletar(String id)
    {
        String where = "number =?";
        String _id = String.valueOf(id);
        String[] whereArgs = new String[]{_id};

        int count = db.delete(NOME_TABELA, where, whereArgs);

        return  count;
    }

    public void fechar() {
        if (db != null) {
            db.close();
        }
    }

}
