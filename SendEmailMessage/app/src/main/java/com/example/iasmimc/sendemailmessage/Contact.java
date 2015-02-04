package com.example.iasmimc.sendemailmessage;

import android.net.Uri;
import android.provider.BaseColumns;
/**
 * Created by iasmim.c on 2/2/2015.
 */
public class Contact {
    private long id;
    private String name;
    private String contact;
    private String number;

    public static  final String NAME = "name";
    public static  final String CONTACT = "contact";
    public static  final String NUMBER = "number";

    public static final String AUTHORITY = "com.example.iasmimc.sendemailmessage";

    public Contact(String name, String contact, String number)
    {
        super();
        this.setName(name);
        this.setContact(contact);
        this.setNumber(number);

    }
    public Contact()
    {
        super();
        this.setName(name);
        this.setContact(contact);
        this.setNumber(number);

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static final class Contatos implements BaseColumns {


        private Contatos()
        {

        }
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/contatos");



    }
}


