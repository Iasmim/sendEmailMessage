package com.example.iasmimc.sendemailmessage;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ActionActivity extends ActionBarActivity {

    public  static RepositorioContact repositorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        Intent intent = getIntent();

        Bundle params = intent.getExtras();


        TextView nametxt = (TextView) findViewById(R.id.name);
        TextView addresstxt = (TextView) findViewById(R.id.address);
        TextView numbertxt = (TextView) findViewById(R.id.number);
        if(params != null)
        {
            String name = params.getString("name");
            nametxt.setText(name);

            String address = params.getString("address");
            addresstxt.setText(address);

            String number = params.getString("number");
            numbertxt.setText(number);

        }


        Button sms = (Button) findViewById(R.id.sms);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView numbertxt = (TextView) findViewById(R.id.number);
                EditText mensagem =(EditText) findViewById(R.id.mensagem);

                String num = numbertxt.getText().toString();


                SmsManager smsManager = SmsManager.getDefault();

                smsManager.sendTextMessage(num,null,mensagem.getText().toString(),null, null);
            }
        });


        Button email = (Button) findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView addresstxt = (TextView) findViewById(R.id.address);
                EditText mensagem =(EditText) findViewById(R.id.mensagem);
                String address = addresstxt.getText().toString();
                String[] recipients = {address};



                Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

                email.setType("message/rfc822");

                email.putExtra(Intent.EXTRA_EMAIL, recipients);
                email.putExtra(Intent.EXTRA_SUBJECT, "iasmim.c@samsung.com");
                email.putExtra(Intent.EXTRA_TEXT,mensagem.getText().toString() );

                startActivity(Intent.createChooser(email, "Choose an email client from..."));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action, menu);
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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            repositorio = new RepositorioContactScript(this);
            Intent intent = getIntent();

            Bundle params = intent.getExtras();
            repositorio.deletar(params.getString("number"));
        }

        return super.onOptionsItemSelected(item);
    }
}
