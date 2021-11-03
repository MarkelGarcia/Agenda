package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button btnListRealizadas = findViewById(R.id.btnListRealizadas);
        Button btnListPendientes = findViewById(R.id.btnListPendientes);
        Button btnListCancelar = findViewById(R.id.btnListCancelar);

        btnListRealizadas.setOnClickListener( (view) -> {
            loadRealizadas();
        } );

        btnListPendientes.setOnClickListener( (view) -> {
            loadPendientes();
        } );

        btnListCancelar.setOnClickListener( (view) -> {
            finish();
        } );

        loadRealizadas();

    }

    private void loadRealizadas() {
        DBManager db = new DBManager(this);
        db.getWritableDatabase();

        String user = this.getSharedPreferences("temp", Context.MODE_PRIVATE).getString("session", null);
        ArrayList<Tarea> tasks = db.selectAllDoneTasksByUser(user);

        // Mostrar tabla
        ListView listTareas = findViewById(R.id.listTareas);

        TareaAdapter adapter = new TareaAdapter(this,
                tasks
        );

        listTareas.setAdapter( adapter );
    }

    private void loadPendientes() {
        DBManager db = new DBManager(this);
        db.getWritableDatabase();

        String user = this.getSharedPreferences("temp", Context.MODE_PRIVATE).getString("session", null);
        ArrayList<Tarea> tasks = db.selectAllRemainingTasksByUser(user);

        // Mostrar tabla
        ListView listTareas = findViewById(R.id.listTareas);

        TareaAdapter adapter = new TareaAdapter(this,
                tasks
        );

        listTareas.setAdapter( adapter );
    }
}