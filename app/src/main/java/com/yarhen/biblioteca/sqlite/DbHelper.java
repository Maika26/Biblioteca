package com.yarhen.biblioteca.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    protected static final String DB_NAME = "Biblioteca";
    protected static final int DB_VERSION = 4;

    protected Context contexto;

    protected static final String DB_TABLE_AUTOR = "autores";
    protected static final String DB_TABLE_EDITORIAL = "editoriales";
    protected static final String DB_TABLE_ESTANTE = "estantes";
    protected static final String DB_TABLE_LIBRO = "libros";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_AUTOR+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombres TEXT NOT NULL,"+
                "apellidos TEXT NOT NULL,"+
                "nacionalidad TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_EDITORIAL+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL,"+
                "nacionalidad TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_ESTANTE+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "letra TEXT NOT NULL,"+
                "numero INTEGER NOT NULL,"+
                "color TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_LIBRO+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "titulo TEXT NOT NULL,"+
                "descripcion TEXT NOT NULL,"+
                "fecha_publicacion TEXT NOT NULL,"+
                "copias INTEGER NOT NULL,"+
                "numero_paginas INTEGER NOT NULL,"+
                "editorial INTEGER NOT NULL,"+
                "estante INTEGER NOT NULL,"+
                "autor INTEGER NOT NULL,"+
                "FOREIGN KEY (autor) REFERENCES autor(id),"+
                "FOREIGN KEY (editorial) REFERENCES editorial(id),"+
                "FOREIGN KEY (estante) REFERENCES estante(id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_AUTOR);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_EDITORIAL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_ESTANTE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_LIBRO);
        onCreate(sqLiteDatabase);

    }

}
