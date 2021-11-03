package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.util.Log;
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
    private SwitchCompat switchRealizadaPendiente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appear = findViewById( R.id.btnRegistrarNuevaTarea);

        nombre = findViewById(R.id.textViewNombre);
        descripcion = findViewById(R.id.TextViewDescripcion);
        fecha = findViewById(R.id.textViewFecha);
        coste = findViewById(R.id.textViewCoste);
        prioridad = findViewById(R.id.textViewPrioridad);

        registrar = findViewById( R.id.btnRegistrar);
        cancelar = findViewById( R.id.btnCancelar2);

        editNombre = findViewById(R.id.editTextNombre);
        editDescripcion = findViewById(R.id.editTextDescripcion);
        editFecha = findViewById(R.id.editTextFecha);
        editCoste =findViewById(R.id.editTextCoste);

        spinnerPrioridad = findViewById(R.id.spinnerPrioridad);
        switchRealizadaPendiente = findViewById(R.id.switchRealizadoPendiente);



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