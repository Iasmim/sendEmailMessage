package com.example.iasmimc.sendemailmessage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by iasmim.c on 2/2/2015.
 */
public class ContactListAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> lista;

    public  ContactListAdapter(Context context, List<Contact> lista)
    {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
     return   lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact c = lista.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_contacts, null);

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(c.getName());

        TextView address = (TextView) view.findViewById(R.id.address);
        address.setText(c.getContact());

        TextView number = (TextView) view.findViewById(R.id.number);
        number.setText(c.getNumber());




        return  view;
    }
}
