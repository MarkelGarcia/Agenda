package com.example.myapplication;

public class Tarea {
    private String nombre;
    private String descripcion;
    private String fecha;
    private double coste;
    private String prioridad;
    private int estado;
    private Usuario usuario;

    public Tarea() { }

    public Tarea(String nombre, String descripcion, String fecha, double coste, String prioridad, int estado, Usuario usuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.coste = coste;
        this.prioridad = prioridad;
        this.estado = estado;
        this.usuario = usuario;
    }

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
}
