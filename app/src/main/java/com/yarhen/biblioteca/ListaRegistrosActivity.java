package com.yarhen.biblioteca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.yarhen.biblioteca.adapter.LibroAdapter;
import com.yarhen.biblioteca.models.Autor;
import com.yarhen.biblioteca.models.Editorial;
import com.yarhen.biblioteca.models.Estante;
import com.yarhen.biblioteca.models.Libro;
import com.yarhen.biblioteca.sqlite.DbAutor;
import com.yarhen.biblioteca.sqlite.DbEditorial;
import com.yarhen.biblioteca.sqlite.DbEstante;
import com.yarhen.biblioteca.sqlite.DbLibro;

import java.util.ArrayList;

public class ListaRegistrosActivity extends AppCompatActivity {

    Spinner spRegistros;
    RecyclerView recyclerRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_registros);

        spRegistros = findViewById(R.id.spRegistros);
        recyclerRegistros = findViewById(R.id.recyclerRegistros);
        cargarSpinner();
        cargarRecycler();


    }

    public void cargarSpinner(){
        DbLibro db = new DbLibro(this);
        ArrayList<Libro> libros = new ArrayList<>();
        libros.add(new Libro());
        libros.addAll(db.todosLosLibros());
        ArrayAdapter<Libro> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, libros);
        spRegistros.setAdapter(adapter);
    }

    public void cargarRecycler(){
        ArrayList<Libro> listadeLibros = new DbLibro(getApplicationContext()).todosLosLibros();
        recyclerRegistros.setLayoutManager(new LinearLayoutManager(ListaRegistrosActivity.this));
        LibroAdapter adapter = new LibroAdapter(listadeLibros);
        recyclerRegistros.setAdapter(adapter);
    }

    public void filtrarSpinner(){
        DbAutor dbautor = new DbAutor(this);
        ArrayList<Autor> autores = dbautor.todosLosAutores();
        if (autores != null){
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, autores);
            spRegistros.setAdapter(adapter);
        }

        DbEditorial dbEditorial = new DbEditorial(this);
        ArrayList<Editorial> editoriales = dbEditorial.todasLasEditoriales();
        if (editoriales != null){
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,editoriales);
            spRegistros.setAdapter(adapter);
        }

        DbEstante dbEstante = new DbEstante(this);
        ArrayList<Estante> estantes = dbEstante.todosLosEstantes();
        if (estantes != null){
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estantes);
            spRegistros.setAdapter(adapter);
        }

    }

}