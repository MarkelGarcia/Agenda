package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btnLogin);
        EditText inputNombre = findViewById(R.id.inputUser);

        SharedPreferences sharedPref = this.getSharedPreferences( "temp", Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPref.edit();

        btnLogin.setOnClickListener( (v) -> {
            DBManager dbManager = new DBManager(this);
            dbManager.getWritableDatabase();

            EditText inputPasswd = findViewById(R.id.inputPasswd);

            Usuario u = new Usuario( inputNombre.getText().toString(), inputPasswd.getText().toString() );
            // Comprobar los datos de login
            if ( dbManager.validateLogin( u ) ) {

                // Comprobar Remember Me
                SwitchCompat rememberMe = findViewById(R.id.rememberMe);

                if (rememberMe.isChecked())  editor.putString("usuario", u.getNombre());
                else editor.clear();

                editor.apply();

                // Pasamos a BaseActivity
                Intent i = new Intent(this, BaseActivity.class);
                startActivity(i);
            } else {
                // ERROR de login
                Toast t = Toast.makeText(this, getText(R.string.loginError), Toast.LENGTH_LONG);
                t.show();
            }
        } );

        if (sharedPref.contains("usuario")) inputNombre.setText( sharedPref.getString("usuario", "") );
    }
}