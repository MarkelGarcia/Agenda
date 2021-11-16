package com.example.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TareaAdapter extends ArrayAdapter<Tarea> {

    public TareaAdapter(Context context, ArrayList<Tarea> tasks) {
        super(context, R.layout.activity_list_items, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Tarea task = getItem(position);

        if (task != null) {
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_items, parent, false);
            }
            // Lookup view for data population
            TextView tvID = convertView.findViewById(R.id.idLi);
            TextView tvNombre = convertView.findViewById(R.id.nombreLi);
            TextView tvDescripcion = convertView.findViewById(R.id.descripcionLi);
            TextView tvFecha = convertView.findViewById(R.id.fechaLi);
            TextView tvCoste = convertView.findViewById(R.id.costeLi);
            TextView tvPrioridad = convertView.findViewById(R.id.prioridadLi);
            // Populate the data into the template view using the data object

            tvID.setText( String.valueOf(task.getId()) );
            tvNombre.setText(task.getNombre());
            tvDescripcion.setText(task.getDescripcion());
            tvFecha.setText(task.getFecha());
            tvCoste.setText(String.valueOf(task.getCoste()));
            tvPrioridad.setText(task.getPrioridad());
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
