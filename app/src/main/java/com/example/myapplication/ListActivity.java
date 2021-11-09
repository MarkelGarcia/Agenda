package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView listTareas;
    private Button btnListPendientes;
    private Button btnListRealizadas;
    private Button btnListCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listTareas = findViewById(R.id.listTareas);

        btnListPendientes = findViewById(R.id.btnListPendientes);
        btnListRealizadas = findViewById(R.id.btnListRealizadas);
        btnListCancelar = findViewById(R.id.btnListCancelar);

        btnListRealizadas.setOnClickListener((view) -> {
            loadRealizadas();
            btnListRealizadas.setEnabled(false);
            btnListPendientes.setEnabled(true);
        });

        btnListPendientes.setOnClickListener((view) -> {
            loadPendientes();
            btnListRealizadas.setEnabled(true);
            btnListPendientes.setEnabled(false);
        });

        btnListCancelar.setOnClickListener((view) -> {
            finish();
        });

        // Capturamos el click y
        listTareas.setOnItemClickListener((parent, view, position, id) -> {

            Intent i = new Intent(this, DetailActivity.class);
            Tarea task = (Tarea) parent.getItemAtPosition(position);
            i.putExtra("task", task);

            startActivity(i);

        });

        listTareas.setOnItemLongClickListener((parent, view, position, id) -> {

            Tarea task = (Tarea) parent.getItemAtPosition(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Borrar selección");
            builder.setMessage("¿Seguro que desea borrar esta Tarea?");

            builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Context context = getApplicationContext();
                    DBManager db = new DBManager(context);
                    Tarea task = (Tarea) parent.getItemAtPosition(position);

                    if (db.deleteTaskById(task.getId())) {
                        Toast t = Toast.makeText(context, "Tarea eliminada", Toast.LENGTH_LONG);
                        t.show();
                        if (btnListPendientes.isEnabled()) loadRealizadas();
                        else loadPendientes();
                    } else {
                        Toast t = Toast.makeText(context, "No se ha podido eliminar la tarea", Toast.LENGTH_LONG);
                        t.show();
                    }
                }
            });
            builder.setNegativeButton("Cancelar", null);
            AlertDialog dialog = builder.create();
            dialog.show();

            return true;

        });

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

        listTareas.setAdapter(adapter);
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

        listTareas.setAdapter(adapter);
    }

    private void refreshList() {
        if (btnListPendientes.isEnabled()) loadRealizadas();
        else loadPendientes();
    }
}