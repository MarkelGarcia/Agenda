package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Tarea implements Parcelable {
    private int id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private double coste;
    private String prioridad;
    private int estado;
    private Usuario usuario;

    public Tarea() { }

    public Tarea(int id, String nombre, String descripcion, String fecha, double coste, String prioridad, int estado, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.coste = coste;
        this.prioridad = prioridad;
        this.estado = estado;
        this.usuario = usuario;
    }

    public Tarea ( Parcel in ) {
        readFromParcel(in);
    }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Implementación de Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(String.valueOf(this.id));
        out.writeString(this.nombre);
        out.writeString(this.descripcion);
        out.writeString(this.fecha);
        out.writeString(String.valueOf(this.coste));
        out.writeString(this.prioridad);
        out.writeString(String.valueOf(this.estado));
    }

    private void readFromParcel (Parcel in) {
        this.id = Integer.parseInt(in.readString());
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.fecha = in.readString();
        this.coste = Double.parseDouble(in.readString());
        this.prioridad = in.readString();
        this.estado = Integer.parseInt(in.readString());
    }

    public static final Parcelable.Creator<Tarea> CREATOR = new Parcelable.Creator<Tarea>() {

        @Override
        public Tarea createFromParcel(Parcel in) {
            return new Tarea(in);
        }

        @Override
        public Tarea[] newArray(int size) {
            return new Tarea[size];
        }
    };
}
