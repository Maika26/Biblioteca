package com.yarhen.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_crear, btn_listar, btn_modificar, btn_eliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_crear = findViewById(R.id.btn_crear);
        btn_listar = findViewById(R.id.btn_listar);
        btn_modificar = findViewById(R.id.btn_modificar);
        btn_eliminar = findViewById(R.id.btn_eliminar);

        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MainActivity.this, AutorActivity.class);
                startActivity(intento);
            }
        });

        btn_listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MainActivity.this, ListaRegistrosActivity.class);
                startActivity(intento);
            }
        });

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MainActivity.this, ListaRegistrosActivity.class);
                startActivity(intento);
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MainActivity.this, ListaRegistrosActivity.class);
                startActivity(intento);
            }
        });

    }
}