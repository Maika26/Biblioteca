package com.yarhen.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yarhen.biblioteca.models.Libro;
import com.yarhen.biblioteca.sqlite.DbLibro;

public class VerLibroActivity extends AppCompatActivity {

    TextView txt_Titulo, txt_Fecha, txt_Descripcion, txt_Copias, txt_Paginas, txt_Autor, txt_Editorial, txt_Estante;
    Button btn_Modificar, btn_Eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_libro);

        txt_Titulo = findViewById(R.id.txt_Titulo);
        txt_Fecha = findViewById(R.id.txt_Fecha);
        txt_Descripcion = findViewById(R.id.txt_Descripcion);
        txt_Copias = findViewById(R.id.txt_Copias);
        txt_Paginas = findViewById(R.id.txt_Paginas);
        txt_Autor = findViewById(R.id.txt_Autor);
        txt_Editorial = findViewById(R.id.txt_Editorial);
        txt_Estante = findViewById(R.id.txt_Estante);
        btn_Modificar = findViewById(R.id.btn_Modificar);
        btn_Eliminar = findViewById(R.id.btn_Eliminar);

        //RECOGER EL LIBRO A MOSTRAR
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Libro libro = (Libro) bundle.get("mostrar_libro");

        txt_Titulo.setText(libro.getTitulo());
        txt_Fecha.setText(libro.getFecha_publicacion());
        txt_Descripcion.setText(libro.getDescripcion());
        txt_Copias.setText(libro.getCopias());
        txt_Paginas.setText(libro.getNumero_paginas());
        txt_Autor.setText(libro.getAutor().getNombres()+" "+libro.getAutor().getApellidos());
        txt_Editorial.setText(libro.getEditorial().getNombre());
        txt_Estante.setText(libro.getEstante().getNumero()+libro.getEstante().getLetra()+libro.getEstante().getColor());

        btn_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(libro);
                Toast.makeText(VerLibroActivity.this, "Libro Modificado", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void modificar(Libro libro){
        DbLibro dbLibro = new DbLibro(this);
        dbLibro.modificarLibro(libro);

    }


}