package com.example.agenda;

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
    EditText inputNombre;
    Button btnLogin;
    SwitchCompat rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNombre = findViewById(R.id.inputUser);
        btnLogin = findViewById(R.id.btnLogin);
        rememberMe = findViewById(R.id.rememberMe);

        SharedPreferences sharedPref = this.getSharedPreferences( "temp", Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPref.edit();

        btnLogin.setOnClickListener( (v) -> {
            DBManager dbManager = new DBManager(this);
            dbManager.getWritableDatabase();

            EditText inputPasswd = findViewById(R.id.inputPasswd);

            Usuario user = new Usuario( inputNombre.getText().toString(), inputPasswd.getText().toString() );

            // Comprobar los datos de login
            if ( dbManager.validateLogin( user ) ) {

                // Comprobar Remember Me

                if (rememberMe.isChecked())  editor.putString("usuario", user.getNombre());
                else editor.clear();

                editor.putString("session", user.getNombre());

                editor.apply();

                // Pasamos a BaseActivity
                Intent i = new Intent(this, BaseActivity.class);
                startActivity(i);
                inputPasswd.setText("");

            } else {
                // ERROR de login
                Toast t = Toast.makeText(this, getText(R.string.loginError), Toast.LENGTH_LONG);
                t.show();
            }
        } );

        // Si es verdadero significa que se ha marcado previamente el Remember Me, en ese caso autocompletamos
        if (sharedPref.contains("usuario")) {
            inputNombre.setText( sharedPref.getString("usuario", "") );
            rememberMe.setChecked(true);
        }
    }
}