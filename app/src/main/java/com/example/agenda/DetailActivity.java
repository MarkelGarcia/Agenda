package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    private EditText editNombre;
    private EditText editDescripcion;
    private EditText editFecha;
    private EditText editCoste;
    private Spinner spPrioridad;
    private SwitchCompat swRealizada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button btnModificar = findViewById(R.id.btnModificar);
        Button btnCancelar = findViewById(R.id.btnCancelarUpdate);
        Button btnEliminar = findViewById(R.id.btnBorrar);

        editNombre = findViewById(R.id.editTextNombreUpdate);
        editDescripcion = findViewById(R.id.editTextDescripcionUpdate);
        editFecha = findViewById(R.id.editTextFechaUpdate);
        editCoste = findViewById(R.id.editTextCosteUpdate);
        spPrioridad = findViewById(R.id.spinnerPrioridadUpdate);
        swRealizada = findViewById(R.id.switchRealizadoPendienteUpdate);

        Intent intent = getIntent();
        Tarea taskExtra = intent.getExtras().getParcelable("task");

        String[] spinnerValues = getResources().getStringArray(R.array.prioridad);

        editNombre.setText(taskExtra.getNombre());
        editDescripcion.setText(taskExtra.getDescripcion());
        editFecha.setText(taskExtra.getFecha());
        editCoste.setText(String.valueOf(taskExtra.getCoste()));

        for (int i = 0; i < spinnerValues.length; i++ ) {
            if (spinnerValues[i].equalsIgnoreCase( taskExtra.getPrioridad() )) spPrioridad.setSelection(i);
        }

        if (taskExtra.getEstado() == 1) swRealizada.setChecked(true);

        btnModificar.setOnClickListener( (view) -> {
            DBManager db = new DBManager(this);
            Tarea task = new Tarea( );

            task.setId( taskExtra.getId() );
            task.setNombre( editNombre.getText().toString() );
            task.setDescripcion( editDescripcion.getText().toString() );
            task.setFecha( editFecha.getText().toString() );
            task.setCoste( Double.parseDouble(editCoste.getText().toString()) );
            task.setPrioridad( spPrioridad.getSelectedItem().toString() );

            int realizada = swRealizada.isChecked()? 1 : 0;
            task.setEstado( realizada );

            if (db.updateTask( task )) {
                Toast t = Toast.makeText(this, "Tarea actualizada", Toast.LENGTH_LONG);
                t.show();
                finish();
            } else {
                Toast t = Toast.makeText(this, "Ha ocurrido un error al actualizar, comprueba los campos", Toast.LENGTH_LONG);
                t.show();
            }
        } );

        btnCancelar.setOnClickListener( (view) -> finish() );

        btnEliminar.setOnClickListener( (view) -> {
            DBManager db = new DBManager(this);

            if (db.deleteTaskById( taskExtra.getId() )) {
                Toast t = Toast.makeText(this, "Tarea eliminada correctamente", Toast.LENGTH_LONG);
                t.show();
                finish();
            } else {
                Toast t = Toast.makeText(this, "Ha ocurrido un error al eliminar la tarea", Toast.LENGTH_LONG);
                t.show();
            }
        } );
    }
}