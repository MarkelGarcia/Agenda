package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TareaAdapter extends ArrayAdapter<Tarea> {
    public TareaAdapter(Context context, ArrayList<Tarea> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Tarea task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_items, parent, false);
        }
        // Lookup view for data population
        TextView tvNombre = convertView.findViewById(R.id.nombreLi);
        TextView tvDescripcion = convertView.findViewById(R.id.descripcionLi);
        TextView tvFecha = convertView.findViewById(R.id.fechaLi);
        TextView tvCoste = convertView.findViewById(R.id.costeLi);
        TextView tvPrioridad = convertView.findViewById(R.id.prioridadLi);
        TextView tvEstado = convertView.findViewById(R.id.realizadaLi);
        // Populate the data into the template view using the data object
        tvNombre.setText(task.getNombre());
        tvDescripcion.setText(task.getDescripcion());
        tvFecha.setText(task.getFecha());
        tvCoste.setText(String.valueOf(task.getCoste()));
        tvPrioridad.setText(task.getPrioridad());
        tvEstado.setText(String.valueOf(task.getEstado()));
        // Return the completed view to render on screen
        return convertView;
    }
}