package com.yarhen.biblioteca.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.yarhen.biblioteca.models.Editorial;

import java.util.ArrayList;

public class DbEditorial extends DbHelper {

    public DbEditorial(@Nullable Context context) {
        super(context);
        this.contexto = context;
    }

    //METODO PARA INGRESAR UNA NUEVA EDITORIAL
    public long nuevaEditorial(Editorial editorial){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues datos = new ContentValues();

        datos.put("nombre", editorial.getNombre());
        datos.put("nacionalidad", editorial.getNacionalidad());

        long nuevoRegistro = db.insert(DB_TABLE_EDITORIAL, null, datos);

        return nuevoRegistro;
    }

    //METODO PARA OBTENER TODAS LAS EDITORIALES
    public ArrayList<Editorial> todasLasEditoriales(){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Editorial> AllEditoriales = new ArrayList<>();
        Editorial editorial;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_EDITORIAL, null);

        if (cursor.moveToFirst()){
            do {
                editorial = new Editorial();
                editorial.setId(cursor.getInt(0));
                editorial.setNombre(cursor.getString(1));
                editorial.setNacionalidad(cursor.getString(2));

                AllEditoriales.add(editorial);
            }while (cursor.moveToNext());
            return AllEditoriales;
        }else{
            return null;
        }
    }

    //METODO PARA OBTENER LAS EDITORIALES POR EL ID
    public Editorial editorialPorId(int id){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();
        Editorial editorial = null;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_EDITORIAL+" WHERE id = ?",
                new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()){
            editorial = new Editorial();
            editorial.setId(cursor.getInt(0));
            editorial.setNombre(cursor.getString(1));
            editorial.setNacionalidad(cursor.getString(2));
        }
        return editorial;

    }

    //METODO PARA ELIMINAR UNA EDITORIAL
    public int eliminarEditorial(int id){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();

        int eliminarRegistro = db.delete(DB_TABLE_EDITORIAL, "id = ?", new String[]{String.valueOf(id)});
        return eliminarRegistro;
    }

    //METODO PARA MODIFICAR UNA EDITORIAL
    public long modificarEditorial(Editorial editorial){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put("nombres", editorial.getNombre());
        datos.put("nacionalidad", editorial.getNacionalidad());

        long modificarRegistro = db.update(DB_TABLE_EDITORIAL, datos, "id = ? ", new String[]{String.valueOf(editorial.getId())});
        return modificarRegistro;
    }

}
