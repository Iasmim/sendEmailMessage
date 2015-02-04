package com.example.iasmimc.sendemailmessage;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompatSideChannelService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iasmim.c on 2/4/2015.
 */
public class Agenda {
    private static final Uri URI = ContactsContract.Contacts.CONTENT_URI;

    public List<Contact> getContatos(Context context) {
        List<Contact> contatos = new ArrayList<Contact>();

        //Recupera o Cursor para percirrer a lista de contatos

        ContentResolver contentResolver = context.getContentResolver();

        Cursor c = contentResolver.query(URI, null, null, null, null);
        try {
            while (c.moveToNext()) {
                Contact a = lerContato(context, c);
                contatos.add(a);
            }
        } finally {
            c.close();
        }
        return contatos;
    }

    public Contact lerContato(Context context, Cursor cursor) {
        Contact c = new Contact();

        long id = cursor.getLong(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
        c.setId(id);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
        c.setName(name);

        //Fone
        boolean temfone = "1".equals(cursor.getString(cursor.getColumnIndexOrThrow(
                ContactsContract.Contacts.HAS_PHONE_NUMBER)));


        if (temfone) {
            List<String> fones = loadFones(context, id);
            c.setNumber(fones.get(0).toString());
            return c;
        }
        else
            return  null;


    }


    private List<String> loadFones(Context context, Long id) {
        List<String> fones = new ArrayList<String>();

        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);

        try {
            while (cursor.moveToNext()) {
                int coluna = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String fone = cursor.getString(coluna);
                fones.add(fone);

            }

        } finally {
            cursor.close();
        }
        return fones;
    }
}
