package com.yarhen.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yarhen.biblioteca.models.Editorial;
import com.yarhen.biblioteca.sqlite.DbEditorial;

public class EditorialActivity extends AppCompatActivity {

    TextView nombreEditorial, etxtEditNac;
    Button btn_crear_editorial, btn_omitir_registro_editorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorial);

        nombreEditorial = findViewById(R.id.nombreEditorial);
        etxtEditNac = findViewById(R.id.etxtEditNac);
        btn_crear_editorial = findViewById(R.id.btn_crear_editorial);
        btn_omitir_registro_editorial = findViewById(R.id.btn_omitir_registro_editorial);

        btn_crear_editorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OBTENER LOS DATOS DE LAS CAJAS
                String nombre = nombreEditorial.getText().toString();
                String nacionalidad = etxtEditNac.getText().toString();

                //INSERTAR LA EDITORIAL A LA BASE DE DATOS
                Editorial editorial = new Editorial(nombre, nacionalidad);
                Toast.makeText(EditorialActivity.this, "Editorial Registrada", Toast.LENGTH_LONG).show();
                DbEditorial dbEditorial = new DbEditorial(EditorialActivity.this);
                dbEditorial.nuevaEditorial(editorial);

                Intent intento = new Intent(EditorialActivity.this,EstanteActivity.class);
                startActivity(intento);

            }
        });

        btn_omitir_registro_editorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(EditorialActivity.this, EstanteActivity.class);
                startActivity(intento);
            }
        });

    }
}