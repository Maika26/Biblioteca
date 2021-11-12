package com.yarhen.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yarhen.biblioteca.models.Autor;
import com.yarhen.biblioteca.models.Editorial;
import com.yarhen.biblioteca.models.Estante;
import com.yarhen.biblioteca.models.Libro;
import com.yarhen.biblioteca.sqlite.DbAutor;
import com.yarhen.biblioteca.sqlite.DbEditorial;
import com.yarhen.biblioteca.sqlite.DbEstante;
import com.yarhen.biblioteca.sqlite.DbHelper;
import com.yarhen.biblioteca.sqlite.DbLibro;

import java.util.ArrayList;

public class LibroActivity extends AppCompatActivity {

    TextView txt_titulo_libro, fecha_publicacion, copias_libro, cantidad_paginas, descripcion_libro;
    Spinner sp_autores, sp_editoriales, sp_estantes;
    Button btn_crear_libro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);

        txt_titulo_libro = findViewById(R.id.txt_titulo_libro);
        fecha_publicacion = findViewById(R.id.fecha_publicacion);
        copias_libro = findViewById(R.id.copias_libro);
        cantidad_paginas = findViewById(R.id.cantidad_paginas);
        descripcion_libro = findViewById(R.id.descripcion_libro);
        sp_autores = findViewById(R.id.sp_autores);
        sp_editoriales = findViewById(R.id.sp_editoriales);
        sp_estantes = findViewById(R.id.sp_estantes);
        btn_crear_libro = findViewById(R.id.btn_crear_libro);

        cargarAutores();
        cargarEditoriales();
        cargarEstantes();



        btn_crear_libro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OBTENER LOS DATOS DE LAS CAJAS
                String titulo = txt_titulo_libro.getText().toString();
                String descripcion = descripcion_libro.getText().toString();
                int fecha = Integer.parseInt(fecha_publicacion.getText().toString());
                int copias = Integer.parseInt(copias_libro.getText().toString());
                int n_paginas = Integer.parseInt(cantidad_paginas.getText().toString());
                Autor autor = (Autor) sp_autores.getSelectedItem();
                Editorial editorial = (Editorial) sp_editoriales.getSelectedItem();
                Estante estante = (Estante) sp_estantes.getSelectedItem();

                //INSERTAR EL LIBRO A LA BASE DE DATOS
                Libro libro = new Libro(titulo, descripcion,fecha,copias,n_paginas, autor, editorial, estante);
                Toast.makeText(LibroActivity.this, "Libro Registrado", Toast.LENGTH_LONG).show();
                DbLibro dbLibro = new DbLibro(LibroActivity.this);
                dbLibro.nuevoLibro(libro);

                Intent intento = new Intent(LibroActivity.this, MainActivity.class);
                startActivity(intento);

            }
        });



    }

    //METODO PARA CARGAR EL SPINNER DE LOS AUTORES
    public void cargarAutores(){
        DbAutor dbautor = new DbAutor(this);
        ArrayList<Autor> autores = dbautor.todosLosAutores();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, autores);
        sp_autores.setAdapter(adapter);
    }
    //METODO PARA CARGAR EL SPINNER DE LAS EDITORIALES
    public void cargarEditoriales(){
        DbEditorial dbEditorial = new DbEditorial(this);
        ArrayList<Editorial> editoriales = dbEditorial.todasLasEditoriales();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,editoriales);
        sp_editoriales.setAdapter(adapter);
    }
    //METODO PARA CARGAR EL SPINNER DE LOS ESTANTES
    public void cargarEstantes(){
        DbEstante dbEstante = new DbEstante(this);
        ArrayList<Estante> estantes = dbEstante.todosLosEstantes();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estantes);
        sp_estantes.setAdapter(adapter);

    }




}