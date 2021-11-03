package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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
}