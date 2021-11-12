package com.yarhen.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yarhen.biblioteca.models.Autor;
import com.yarhen.biblioteca.sqlite.DbAutor;

public class AutorActivity extends AppCompatActivity {

    TextView etxtNombres, etxtApellidos, etxtNacionalidadAutor;
    Button btn_crearAutor, btn_omitir_registro_autor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);

        etxtNombres = findViewById(R.id.etxtNombres);
        etxtApellidos = findViewById(R.id.etxtApellidos);
        etxtNacionalidadAutor = findViewById(R.id.etxtNacionalidadAutor);
        btn_crearAutor = findViewById(R.id.btn_crearAutor);
        btn_omitir_registro_autor = findViewById(R.id.btn_omitir_registro_autor);

        btn_crearAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OBTENER LOS DATOS DE LAS CAJAS
                String nombre = etxtNombres.getText().toString();
                String apellido = etxtApellidos.getText().toString();
                String nacionalidad = etxtNacionalidadAutor.getText().toString();

                //INSERTAR EL AUTOR A LA BASE DE DATOS
                Autor autor = new Autor(nombre, apellido, nacionalidad);
                Toast.makeText(AutorActivity.this, "Autor Registrado", Toast.LENGTH_LONG).show();
                DbAutor dbAutor = new DbAutor(AutorActivity.this);
                dbAutor.nuevoAutor(autor);

                Intent intento = new Intent(AutorActivity.this, EditorialActivity.class);
                startActivity(intento);

            }
        });

        btn_omitir_registro_autor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(AutorActivity.this, EditorialActivity.class);
                startActivity(intento);
            }
        });


    }

}