package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
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
            Intent i = new Intent(this, DetailActivity.class);
            Tarea task = (Tarea) parent.getItemAtPosition(position);
            i.putExtra("TaskID", String.valueOf(task.getId()));

            startActivity(i);
        } );

        loadRealizadas();

        listTareas.setOnItemClickListener( (parent, view, position, id) -> {

            if (view.isPressed()) {
                Tarea task = (Tarea) parent.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Borrar selección");
                builder.setMessage("¿Seguro que desea borrar esta Tarea?");

                builder.setPositiveButton("Aceptar", null);
                builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

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