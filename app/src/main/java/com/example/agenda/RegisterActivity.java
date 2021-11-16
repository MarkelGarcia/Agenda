package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private TextView nombre = null;
    private TextView descripcion = null;
    private TextView fecha = null;
    private TextView coste = null;
    private TextView prioridad = null;
    private Button registrar = null;
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

        Button appear = findViewById( R.id.btnRegistrarNuevaTarea);

        Button btnCancelar = findViewById(R.id.btnCancelar);

        nombre = findViewById(R.id.textViewNombre);
        descripcion = findViewById(R.id.TextViewDescripcion);
        fecha = findViewById(R.id.textViewFecha);
        coste = findViewById(R.id.textViewCoste);
        prioridad = findViewById(R.id.textViewPrioridad);

        registrar = findViewById( R.id.btnRegistrar);

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
            editNombre.setVisibility(View.VISIBLE);
            editDescripcion.setVisibility(View.VISIBLE);
            editFecha.setVisibility(View.VISIBLE);
            editCoste.setVisibility(View.VISIBLE);
            spinnerPrioridad.setVisibility(View.VISIBLE);
            switchRealizadaPendiente.setVisibility(View.VISIBLE);
        });

        btnCancelar.setOnClickListener( (view) -> finish() );

        registrar.setOnClickListener( (view) -> {
            // Recoger info + objeto

            Tarea tarea = new Tarea(  );
            tarea.setNombre( editNombre.getText().toString() );
            tarea.setDescripcion( editDescripcion.getText().toString() );
            tarea.setFecha( editFecha.getText().toString() );
            tarea.setCoste(Double.parseDouble(editCoste.getText().toString()) );
            tarea.setPrioridad( spinnerPrioridad.getSelectedItem().toString() );

            int estado = switchRealizadaPendiente.isChecked()? 1 : 0;
            tarea.setEstado( estado );

            Usuario usuario = new Usuario( );
            usuario.setNombre( this.getSharedPreferences( "temp", Context.MODE_PRIVATE ).getString("session", null) );
            tarea.setUsuario( usuario );

            // Insertar tarea
            DBManager db = new DBManager(this);
            if (db.insertTarea( tarea )) {
                Toast t = Toast.makeText(this, "Tarea insertada correctamente", Toast.LENGTH_LONG);
                t.show();
                finish();
            } else {
                Toast t = Toast.makeText(this, "Ha ocurrido un error al insertar, comprueba los campos", Toast.LENGTH_LONG);
                t.show();
            }
        } );

    }


}