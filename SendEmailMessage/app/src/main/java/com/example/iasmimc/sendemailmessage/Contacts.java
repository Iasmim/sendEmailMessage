package com.example.iasmimc.sendemailmessage;

import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import  android.widget.AdapterView.OnItemClickListener;
import java.util.List;
import android.app.ActionBar;
import android.widget.TextView;
import android.widget.ListView;

public class Contacts extends ListActivity implements OnItemClickListener  {

    public  static RepositorioContact repositorio;

    public List<Contact> contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ListView lv = getListView();
        repositorio = new RepositorioContactScript(this);

        contatos = repositorio.ListarContatos();


        Agenda a = new Agenda();
        List<Contact> aux =  a.getContatos(this);

        for (int i = 0; i < aux.size(); i++) {
            if(aux.get(i) != null)
              contatos.add(aux.get(i));
        };

        setListAdapter(new ContactListAdapter(this, contatos));

        lv.setOnItemClickListener(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK) {

            contatos = repositorio.ListarContatos();

            setListAdapter(new ContactListAdapter(this, contatos));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


       // Uri selected = ContentUris.withAppendedId( Uri.parse("content://com.example.iasmimc.sendemailmessage/contatos"), id);

        Intent intent = new Intent(this, ActionActivity.class);
        Bundle params = new Bundle();

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView address = (TextView) view.findViewById(R.id.address);
        TextView number = (TextView) view.findViewById(R.id.number);

        params.putString("name",name.getText().toString());
        params.putString("address",address.getText().toString());
        params.putString("number",number.getText().toString());
        intent.putExtras(params);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contacts, menu);
      return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        startActivity(new Intent(this,MainActivity.class ));

      return  true;
    }
}
