package com.yarhen.biblioteca.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.yarhen.biblioteca.models.Autor;
import com.yarhen.biblioteca.models.Editorial;
import com.yarhen.biblioteca.models.Estante;
import com.yarhen.biblioteca.models.Libro;

import java.util.ArrayList;

public class DbLibro extends DbHelper {

    public DbLibro(@Nullable Context context) {
        super(context);
        this.contexto = context;
    }
    //METODO PARA REGISTRAR UN NUEVO LIBRO
    public long nuevoLibro(Libro libro){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues datos = new ContentValues();

        datos.put("titulo", libro.getTitulo());
        datos.put("descripcion", libro.getDescripcion());
        datos.put("fecha_publicacion", libro.getFecha_publicacion());
        datos.put("copias", libro.getCopias());
        datos.put("numero_paginas", libro.getNumero_paginas());
        datos.put("autor", libro.getAutor().getId());
        datos.put("editorial", libro.getEditorial().getId());
        datos.put("estante", libro.getEstante().getId());

        long nuevoRegistro = db.insert(DB_TABLE_LIBRO, null, datos);

        return nuevoRegistro;
    }

    //METODO PARA OBTENER TODOS LOS LIBROS DE LA BASE DE DATOS
    public ArrayList<Libro> todosLosLibros(){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Libro> AllLibros = new ArrayList<>();
        Libro libro;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_LIBRO, null);
        DbAutor baseAutor = new DbAutor(contexto);
        DbEditorial baseEditorial = new DbEditorial(contexto);
        DbEstante baseEstante = new DbEstante(contexto);

        if (cursor.moveToFirst()){
            do {
                Autor autor = baseAutor.autorPorId(cursor.getInt(6));
                Editorial editorial = baseEditorial.editorialPorId(cursor.getInt(7));
                Estante estante = baseEstante.estantePorId(cursor.getInt(8));

                libro = new Libro();
                libro.setId(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha_publicacion(cursor.getInt(3));
                libro.setCopias(cursor.getInt(4));
                libro.setNumero_paginas(cursor.getInt(5));
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEstante(estante);

                AllLibros.add(libro);

            }while (cursor.moveToNext());
            return AllLibros;
        }else{
            return null;
        }
    }

    //METODO PARA FILTRAR EL LIBRO POR AUTOR
    public ArrayList<Libro> libroPorAutor(int idAutor){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Libro> LibrosAutor = new ArrayList<>();
        Libro libro;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_LIBRO+" WHERE autor = ?",
                new String[]{String.valueOf(idAutor)});
        DbAutor dbAutor = new DbAutor(contexto);

        if (cursor.moveToFirst()){
            do {
                Autor a = dbAutor.autorPorId(cursor.getInt(6));

                libro = new Libro();
                libro.setId(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha_publicacion(cursor.getInt(3));
                libro.setCopias(cursor.getInt(4));
                libro.setNumero_paginas(cursor.getInt(5));
                libro.setAutor(a);
                LibrosAutor.add(libro);

            }while (cursor.moveToNext());
            return LibrosAutor;
        }else{
            return null;
        }
    }

    //METODO PARA FILTRAR EL LIBRO POR LA EDITORIAL
    public ArrayList<Libro> libroPorEditorial(int idEditorial){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Libro> LibrosEditorial = new ArrayList<>();
        Libro libro;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_LIBRO+" WHERE editorial = ?",
                new String[]{String.valueOf(idEditorial)});
        DbEditorial dbEditorial = new DbEditorial(contexto);

        if (cursor.moveToFirst()){
            do {
                Editorial editorial = dbEditorial.editorialPorId(7);

                libro = new Libro();
                libro.setId(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha_publicacion(cursor.getInt(3));
                libro.setCopias(cursor.getInt(4));
                libro.setNumero_paginas(cursor.getInt(5));
                libro.setEditorial(editorial);
                LibrosEditorial.add(libro);

            }while (cursor.moveToNext());
            return LibrosEditorial;
        }else{
            return null;
        }
    }

    //METODO PARA FILTRAR EL LIBRO POR EL TITULO
    public ArrayList<Libro> libroPorTitulo(int titulo){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Libro> LibrosTitulo = new ArrayList<>();
        Libro libro;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_LIBRO+" WHERE titulo = ?",
                new String[]{String.valueOf(titulo)});

        if (cursor.moveToFirst()){
            do {
                libro = new Libro();
                libro.setId(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha_publicacion(cursor.getInt(3));
                libro.setCopias(cursor.getInt(4));
                libro.setNumero_paginas(cursor.getInt(5));
                LibrosTitulo.add(libro);
            }while (cursor.moveToNext());
            return LibrosTitulo;
        }else{
            return null;
        }
    }

    //METODO PARA ELIMINAR UN LIBRO
    public int eliminarLibro(int id){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();

        int eliminarRegistro = db.delete(DB_TABLE_LIBRO, "id = ?", new String[]{String.valueOf(id)});
        return eliminarRegistro;

    }

    //METODO PARA MODIFICAR UN LIBRO
    public long modificarLibro(Libro libro){
        DbHelper helper = new DbHelper(contexto);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues datos = new ContentValues();
        datos.put("titulo", libro.getTitulo());
        datos.put("descripcion", libro.getDescripcion());
        datos.put("fecha_publicacion", libro.getFecha_publicacion());
        datos.put("copias", libro.getCopias());
        datos.put("numero_paginas", libro.getNumero_paginas());
        datos.put("autor", libro.getAutor().getId());
        datos.put("editorial", libro.getEditorial().getId());
        datos.put("estante", libro.getEstante().getId());

        long modificarRegistro = db.update(DB_TABLE_LIBRO, datos, "id = ? ", new String[]{String.valueOf(libro.getId())});
        return modificarRegistro;

    }

}
