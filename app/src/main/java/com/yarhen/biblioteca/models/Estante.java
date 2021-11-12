package com.yarhen.biblioteca.models;

public class Estante {

    private int id;
    private String letra;
    private int numero;
    private String color;

    public Estante() {
    }

    public Estante(String letra, int numero, String color) {
        this.letra = letra;
        this.numero = numero;
        this.color = color;
    }

    public Estante(int id, String letra, int numero, String color) {
        this.id = id;
        this.letra = letra;
        this.numero = numero;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.letra+this.numero+this.color;
    }

}
