package com.yarhen.biblioteca.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.yarhen.biblioteca.models.Autor;
import com.yarhen.biblioteca.models.Estante;

import java.util.ArrayList;

public class DbEstante extends DbHelper {

    public DbEstante(@Nullable Context context) {
        super(context);
        this.contexto = context;
    }

    //METODO PARA INGRESAR UN NUEVO ESTANTE
    public long nuevoEstante(Estante estante){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues datos = new ContentValues();

        datos.put("letra", estante.getLetra());
        datos.put("numero", estante.getNumero());
        datos.put("color", estante.getColor());

        long nuevoRegistro = db.insert(DB_TABLE_ESTANTE, null, datos);

        return nuevoRegistro;
    }

    //METODO PARA OBTENER TODOS LOS ESTANTES
    public ArrayList<Estante> todosLosEstantes(){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Estante> AllEstantes = new ArrayList<>();
        Estante estante;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_ESTANTE, null);

        if (cursor.moveToFirst()){
            do {
                estante = new Estante();
                estante.setId(cursor.getInt(0));
                estante.setLetra(cursor.getString(1));
                estante.setNumero(cursor.getInt(2));
                estante.setColor(cursor.getString(3));

                AllEstantes.add(estante);
            }while (cursor.moveToNext());
            return AllEstantes;
        }else{
            return null;
        }
    }

    //METODO PARA OBTENER LOS ESTANTES POR EL ID
    public Estante estantePorId(int id){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();
        Estante estante = null;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_ESTANTE+" WHERE id = ?",
                new String[]{String.valueOf(id)});

        if ( cursor.moveToFirst()){
            estante = new Estante();
            estante.setId(cursor.getInt(0));
            estante.setLetra(cursor.getString(1));
            estante.setNumero(cursor.getInt(2));
            estante.setColor(cursor.getString(3));
        }
        return estante;

    }

    //METODO PARA ELIMINAR UN ESTANTE
    public int eliminarEstante(int id){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();

        int eliminarRegistro = db.delete(DB_TABLE_ESTANTE, "id = ?", new String[]{String.valueOf(id)});
        return eliminarRegistro;

    }

    //METODO PARA MODIFICAR UN ESTANTE
    public long modificarEstante(Estante estante){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put("letra", estante.getLetra());
        datos.put("numero", estante.getNumero());
        datos.put("color", estante.getColor());

        long modificarRegistro = db.update(DB_TABLE_ESTANTE, datos, "id = ? ", new String[]{String.valueOf(estante.getId())});
        return modificarRegistro;

    }

}
