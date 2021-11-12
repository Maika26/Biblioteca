package com.yarhen.biblioteca.models;

import java.io.Serializable;

public class Libro implements Serializable {

    private int id;
    private String titulo;
    private String descripcion;
    private int fecha_publicacion;
    private int copias;
    private int numero_paginas;
    private Autor autor;
    private Editorial editorial;
    private  Estante estante;

    public Libro() {
    }

    public Libro(String titulo, String descripcion, int fecha_publicacion, int copias, int numero_paginas,
                 Autor autor, Editorial editorial, Estante estante) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_publicacion = fecha_publicacion;
        this.copias = copias;
        this.numero_paginas = numero_paginas;
        this.autor = autor;
        this.editorial = editorial;
        this.estante = estante;
    }

    public Libro(int id, String titulo, String descripcion, int fecha_publicacion, int copias, int numero_paginas,
                 Autor autor, Editorial editorial, Estante estante) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_publicacion = fecha_publicacion;
        this.copias = copias;
        this.numero_paginas = numero_paginas;
        this.autor = autor;
        this.editorial = editorial;
        this.estante = estante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(int fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public int getNumero_paginas() {
        return numero_paginas;
    }

    public void setNumero_paginas(int numero_paginas) {
        this.numero_paginas = numero_paginas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Estante getEstante() {
        return estante;
    }

    public void setEstante(Estante estante) {
        this.estante = estante;
    }

    @Override
    public String toString() {
        return this.titulo;
    }

}
