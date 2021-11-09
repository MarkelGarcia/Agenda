package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView listTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listTareas = findViewById(R.id.listTareas);

        Button btnListRealizadas = findViewById(R.id.btnListRealizadas);
        Button btnListPendientes = findViewById(R.id.btnListPendientes);
        Button btnListCancelar = findViewById(R.id.btnListCancelar);

        btnListRealizadas.setOnClickListener( (view) -> {
            loadRealizadas();
            btnListRealizadas.setEnabled( false );
            btnListPendientes.setEnabled( true );
        } );

        btnListPendientes.setOnClickListener( (view) -> {
            loadPendientes();
            btnListRealizadas.setEnabled( true );
            btnListPendientes.setEnabled( false );
        } );

        btnListCancelar.setOnClickListener( (view) -> {
            finish();
        } );

        listTareas.setOnItemClickListener( (parent, view, position, id) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            Tarea li = parent.getItemAtPosition(position);
            intent.putExtra("TaskID", );
        } );

        loadRealizadas();

    }

    private void loadRealizadas() {
        DBManager db = new DBManager(this);
        db.getWritableDatabase();

        String user = this.getSharedPreferences("temp", Context.MODE_PRIVATE).getString("session", "");
        ArrayList<Tarea> tasks = db.selectAllCompletedTasksByUser(user);

        // Mostrar tabla
        TareaAdapter adapter = new TareaAdapter(this,
                tasks
        );

        listTareas.setAdapter( adapter );
    }

    private void loadPendientes() {
        DBManager db = new DBManager(this);
        db.getWritableDatabase();

        String user = this.getSharedPreferences("temp", Context.MODE_PRIVATE).getString("session", "");
        ArrayList<Tarea> tasks = db.selectAllRemainingTasksByUser(user);

        // Mostrar tabla
        TareaAdapter adapter = new TareaAdapter(this,
                tasks
        );

        listTareas.setAdapter( adapter );
    }
}