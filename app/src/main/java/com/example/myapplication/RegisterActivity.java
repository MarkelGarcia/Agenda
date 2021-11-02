package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private Button appear = null;
    private TextView nombre = null;
    private TextView descripcion = null;
    private TextView fecha = null;
    private TextView coste = null;
    private TextView prioridad = null;
    private Button registrar = null;
    private Button cancelar = null;
    private EditText editNombre = null;
    private EditText editDescripcion = null;
    private EditText editFecha = null;
    private EditText editCoste = null;
    private Spinner spinnerPrioridad = null;
    private Switch switchRealizadaPendiente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        appear = (Button) findViewById( R.id.btnRegistrarNuevaTarea);

        nombre = (TextView) findViewById(R.id.textViewNombre);
        descripcion = (TextView) findViewById(R.id.TextViewDescripcion);
        fecha = (TextView) findViewById(R.id.textViewFecha);
        coste = (TextView) findViewById(R.id.textViewCoste);
        prioridad = (TextView) findViewById(R.id.textViewPrioridad);

        registrar = (Button) findViewById( R.id.btnRegistrar);
        cancelar = (Button) findViewById( R.id.btnCancelar2);

        editNombre = (EditText) findViewById(R.id.editTextNombre);
        editDescripcion = (EditText) findViewById(R.id.editTextDescripcion);
        editFecha = (EditText) findViewById(R.id.editTextFecha);
        editCoste = (EditText) findViewById(R.id.editTextCoste);

        spinnerPrioridad = (Spinner) findViewById(R.id.spinnerPrioridad);
        switchRealizadaPendiente = (Switch) findViewById(R.id.switchRealizadoPendiente);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appear.setOnClickListener( (view) -> {
            nombre.setVisibility(View.VISIBLE);
            descripcion.setVisibility(View.VISIBLE);
            fecha.setVisibility(View.VISIBLE);
            coste.setVisibility(View.VISIBLE);
            prioridad.setVisibility(View.VISIBLE);
            registrar.setVisibility(View.VISIBLE);
            cancelar.setVisibility(View.VISIBLE);
            editNombre.setVisibility(View.VISIBLE);
            editDescripcion.setVisibility(View.VISIBLE);
            editFecha.setVisibility(View.VISIBLE);
            editCoste.setVisibility(View.VISIBLE);
            spinnerPrioridad.setVisibility(View.VISIBLE);
            switchRealizadaPendiente.setVisibility(View.VISIBLE);
        });
    }


}