package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    Button btnListado;
    Button btnRegistro;
    Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        btnListado = findViewById(R.id.btnListado);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnSalir = findViewById(R.id.btnSalir);

        btnListado.setOnClickListener((view) -> {
            Intent i = new Intent(this, ListActivity.class);
            startActivity(i);
        });

        btnRegistro.setOnClickListener((view) -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });

        btnSalir.setOnClickListener((view) -> finish());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.changePassword) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Introduzca la nueva contraseña");
            builder.setTitle("Cambiar contraseña");

            builder.setView(R.layout.change_password_layout);

            builder.setPositiveButton("Cambiar", (dialog, whichButton) -> {

                Context contexto = getApplicationContext();

                EditText editPassword = ((AlertDialog) dialog).findViewById(R.id.editTextPassword);
                EditText editPasswordConfirm = ((AlertDialog) dialog).findViewById(R.id.editTextPasswordConfirm);

                if (editPassword != null && editPasswordConfirm != null) {
                    if (editPassword.getText().toString().equals(editPasswordConfirm.getText().toString())) {

                        DBManager db = new DBManager(contexto);
                        SharedPreferences sharedPref = contexto.getSharedPreferences("temp", Context.MODE_PRIVATE);

                        if (db.updatePassword(new Usuario(sharedPref.getString("session", null), editPasswordConfirm.getText().toString()))) {
                            Toast t = Toast.makeText(contexto, "Contraseña actualizada", Toast.LENGTH_LONG);
                            t.show();
                        } else {
                            Toast t = Toast.makeText(contexto, "Error al cambiar la contraseña", Toast.LENGTH_LONG);
                            t.show();
                        }
                    } else {
                        Toast t = Toast.makeText(contexto, "Las contraseñas no coinciden", Toast.LENGTH_LONG);
                        t.show();
                    }
                }


            });

            builder.setNegativeButton("Cancelar", null);

            AlertDialog alert = builder.create();

            alert.show();

        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}