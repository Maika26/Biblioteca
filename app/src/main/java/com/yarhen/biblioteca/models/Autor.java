package com.yarhen.biblioteca.models;

public class Autor {

    private int id;
    private String nombres;
    private String apellidos;
    private String nacionalidad;

    public Autor() {
    }

    public Autor(String nombres, String apellidos, String nacionalidad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
    }

    public Autor(int id, String nombres, String apellidos, String nacionalidad) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return this.nombres+ " "+this.apellidos;
    }

}
