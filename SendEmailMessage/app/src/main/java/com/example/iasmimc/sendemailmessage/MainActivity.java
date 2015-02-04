package com.example.iasmimc.sendemailmessage;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    public  static RepositorioContact repositorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repositorio = new RepositorioContactScript(this);
        Button btsave = (Button) findViewById(R.id.save);
        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact c = new Contact();

                EditText nametxt = (EditText) findViewById(R.id.name);
                EditText addresstxt = (EditText) findViewById(R.id.address);
                EditText numbertxt = (EditText) findViewById(R.id.number);

                c.setName(nametxt.getText().toString());
                c.setContact(addresstxt.getText().toString());
                c.setNumber(numbertxt.getText().toString());

                repositorio.inserir(c);

                Toast.makeText(v.getContext(), "Saved Sucessfull",Toast.LENGTH_SHORT).show();
                nametxt.setText("");
                addresstxt.setText("");
                numbertxt.setText("");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
