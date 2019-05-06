package com.ejemplo.sqlite00;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Acceso a bd con SQLiteOpenHelper
 * SQLiteOpenHelper: nos permite diseñar, crear, actualizar la base de datos.
 */

public class AdminSQLiteOpenHelper  extends SQLiteOpenHelper {
    String ID_PK=" INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1 ";
    String tabla_articulo="CREATE TABLE articulos(codigo"+ID_PK+",descripcion text, precio real )";
    public AdminSQLiteOpenHelper(Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //deben crearse inicialmente estos 2 metodos  onCreate(SQLiteDatabase db), onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    @Override
    public void onCreate(SQLiteDatabase db) {
      //el metodo  ejecuta una sentencia para crear la tabla con sus campos y tipos
     db.execSQL(tabla_articulo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
