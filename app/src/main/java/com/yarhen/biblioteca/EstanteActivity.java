package com.yarhen.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yarhen.biblioteca.models.Estante;
import com.yarhen.biblioteca.sqlite.DbEstante;

public class EstanteActivity extends AppCompatActivity {

    TextView etxtLetra, etxtNumero, etxtColor;
    Button btn_estante_crear, btn_omitir_registro_estante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estante);

        etxtLetra = findViewById(R.id.etxtLetra);
        etxtNumero = findViewById(R.id.etxtNumero);
        etxtColor = findViewById(R.id.etxtColor);
        btn_estante_crear = findViewById(R.id.btn_estante_crear);
        btn_omitir_registro_estante = findViewById(R.id.btn_omitir_registro_estante);

        btn_estante_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OBTENER LOS DATOS DE LAS CAJAS
                String letra = etxtLetra.getText().toString();
                int numero = Integer.parseInt(etxtNumero.getText().toString());
                String color = etxtColor.getText().toString();

                //INSERTAR EL ESTANTE A LA BASE DE DATOS
                Estante estante = new Estante(letra, numero, color);
                Toast.makeText(EstanteActivity.this, "Estante Registrado", Toast.LENGTH_LONG).show();
                DbEstante dbEstante = new DbEstante(EstanteActivity.this);
                dbEstante.nuevoEstante(estante);

                Intent intento = new Intent(EstanteActivity.this, LibroActivity.class);
                startActivity(intento);

            }
        });

        btn_omitir_registro_estante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(EstanteActivity.this, LibroActivity.class);
                startActivity(intento);
            }
        });

    }
}