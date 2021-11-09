package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Button btnListado = findViewById(R.id.btnListado);
        Button btnRegistro = findViewById(R.id.btnRegistro);
        Button btnSalir = findViewById(R.id.btnSalir);

        btnListado.setOnClickListener( (view) -> {
            Intent i = new Intent(this, ListActivity.class);
            startActivity(i);
        } );

        btnRegistro.setOnClickListener( (view) -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        } );

        btnSalir.setOnClickListener( (view) -> {
            finish();
        } );

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.changePassword) {


            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            EditText newContra = new EditText(this);
            alert.setMessage("Introduzca la nueva contraseña");
            alert.setTitle("Cambiar contraseña");

            alert.setView(newContra);

            alert.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //What ever you want to do with the value
                    String password = newContra.getText().toString();

                    Context contexto = getApplicationContext();
                    DBManager db = new DBManager(contexto);
                    SharedPreferences sharedPref = contexto.getSharedPreferences( "temp", Context.MODE_PRIVATE );

                    db.updatePassword(new Usuario(sharedPref.getString("session" , null), password));
                }
            });

            alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });

            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

   /* public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    } */

}