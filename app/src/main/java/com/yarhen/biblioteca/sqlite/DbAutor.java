package com.yarhen.biblioteca.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.yarhen.biblioteca.models.Autor;

import java.util.ArrayList;

public class DbAutor extends DbHelper {

    public DbAutor(@Nullable Context context) {
        super(context);
        this.contexto = context;
    }

    //METODO PARA INGRESAR UN NUEVO AUTOR
    public long nuevoAutor(Autor autor){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues datos = new ContentValues();

        datos.put("nombres", autor.getNombres());
        datos.put("apellidos", autor.getApellidos());
        datos.put("nacionalidad", autor.getNacionalidad());

        long nuevoRegistro = db.insert(DB_TABLE_AUTOR, null, datos);

        return nuevoRegistro;
    }

    //METODO PARA OBTENER TODOS LOS AUTORES
    public ArrayList<Autor> todosLosAutores(){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Autor> AllAutores = new ArrayList<>();
        Autor autor;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_AUTOR, null);

        if (cursor.moveToFirst()){
            do {
                autor = new Autor();
                autor.setId(cursor.getInt(0));
                autor.setNombres(cursor.getString(1));
                autor.setApellidos(cursor.getString(2));
                autor.setNacionalidad(cursor.getString(3));
                AllAutores.add(autor);
            }while (cursor.moveToNext());
            return  AllAutores;
        }else{
            return null;
        }
    }

    //METODO PARA OBTENER LOS AUTORES POR EL ID
    public Autor autorPorId(int id){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();
        Autor autor = null;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_AUTOR+" WHERE id = ?",
                new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()){
            autor = new Autor();
            autor.setId(cursor.getInt(0));
            autor.setNombres(cursor.getString(1));
            autor.setApellidos(cursor.getString(2));
            autor.setNacionalidad(cursor.getString(3));

        }
        return autor;
    }

    //METODO PARA ELIMINAR UN AUTOR
    public int eliminarAutor(int id){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();

        int eliminarRegistro = db.delete(DB_TABLE_AUTOR, "id = ?", new String[]{String.valueOf(id)});
        return eliminarRegistro;

    }

    //METODO PARA MODIFICAR UN AUTOR
    public long modificarAutor(Autor autor){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put("nombres", autor.getNombres());
        datos.put("apellidos", autor.getApellidos());
        datos.put("nacionalidad", autor.getNacionalidad());

        long modificarRegistro = db.update(DB_TABLE_AUTOR, datos, "id = ? ", new String[]{String.valueOf(autor.getId())});
        return modificarRegistro;

    }

}
