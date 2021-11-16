package com.example.myapplication;

public class Usuario {
    private String nombre, password;

    public Usuario () {

    }

    public Usuario ( String nombre, String password ) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

}
