package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        EditText tvNombre = findViewById(R.id.editTextNombreUpdate);
        EditText tvDescripcion = findViewById(R.id.editTextDescripcionUpdate);
        EditText tvFecha = findViewById(R.id.editTextFechaUpdate);
        EditText tvCoste = findViewById(R.id.editTextCosteUpdate);
        Spinner spPrioridad = findViewById(R.id.spinnerPrioridadUpdate);
        SwitchCompat swRealizada = findViewById(R.id.switchRealizadoPendienteUpdate);

        Intent intent = getIntent();
        Tarea task = intent.getExtras().getParcelable("task");

        String[] spinnerValues = getResources().getStringArray(R.array.prioridad);

        tvNombre.setText(task.getNombre());
        tvDescripcion.setText(task.getDescripcion());
        tvFecha.setText(task.getFecha());
        tvCoste.setText(String.valueOf(task.getCoste()));

        for (int i = 0; i < spinnerValues.length; i++ ) {
            if (spinnerValues[i].equalsIgnoreCase( task.getPrioridad() )) spPrioridad.setSelection(i);
        }

        if (task.getEstado() == 1) swRealizada.setChecked(true);

    }
}