package com.uy.cra.petagram;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contacto extends AppCompatActivity {

    private TextInputLayout form_nombre;
    private TextInputLayout form_email;
    private TextInputLayout form_descripcion;

    private Editable nombre;
    private Editable email;
    private Editable descripcion;

    String sEmail, sPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);



        form_nombre   = (TextInputLayout) findViewById(R.id.form_username);
        form_email = (TextInputLayout) findViewById(R.id.form_email);
        form_descripcion = (TextInputLayout) findViewById(R.id.form_descripcion);

        Button form_submit = (Button) findViewById(R.id.form_submit);

        sEmail = "email@gmail.com";
        sPassword = "pass";

        form_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //nombre
                nombre = form_nombre.getEditText().getText();
                //email
                email = form_email.getEditText().getText();
                //descripcion
                descripcion = form_descripcion.getEditText().getText();

                Toast.makeText(getBaseContext(), "enviando...", Toast.LENGTH_LONG).show();


                    //properties
                    Properties properties = new Properties();
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", "smtp.gmail.com");
                    properties.put("mail.smtp.port", "587");

                    //session
                    Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
//                            return super.getPasswordAuthentication();

                            return new PasswordAuthentication(sEmail, sPassword);

                        }
                    });


                    try {
                        // Mail content
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(sEmail));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.toString().trim()));
                        message.setSubject("contactoPrueba");
                        message.setText(descripcion.toString().trim());

                        new SendMail().execute(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }



        });     }

    private class SendMail extends AsyncTask<Message, String, String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Contacto.this, "Enviando", "espere...", false, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equals("Success")){


                AlertDialog.Builder builder = new AlertDialog.Builder(Contacto.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Mail Send succesfully");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();

            } else {
                Toast.makeText(getApplicationContext(), "algo paso...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.exit:
            finish();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

}