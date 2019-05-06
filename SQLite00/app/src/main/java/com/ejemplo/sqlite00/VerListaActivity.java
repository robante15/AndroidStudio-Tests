package com.ejemplo.sqlite00;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class VerListaActivity extends AppCompatActivity {
    private RecyclerView listaReciclada;
    Cursor consultaRegistros;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista);
        listaReciclada = (RecyclerView) findViewById(R.id.recyclerResultados);
        // use un linear layout manager
        layoutManager = new LinearLayoutManager(this);
        listaReciclada.setLayoutManager(layoutManager);
        mAdapter = new AdaptadorRecycler(this,getData());
        listaReciclada.setAdapter(mAdapter);

        // Agregar un floating action click handler para iniciar una nueva Activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });
    }

    // Traer datos de Contactos del Sistema de Android
    public List<Producto> getData() {
        List<Producto> productos = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admin_prod", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase(); // la conexion se establece de solo lectura
        consultaRegistros = db.rawQuery("SELECT * FROM  articulos",null);
        //aray para guardar la info que traemos de la bd en el cursor
        StringBuffer stringBuffer = new StringBuffer();
        Producto dataModel = null;

        if (consultaRegistros!= null) {
            while (consultaRegistros.moveToNext()){
                dataModel= new Producto();
                int codigo = consultaRegistros.getInt(consultaRegistros.
                        getColumnIndex("codigo"));
                String descripcion = consultaRegistros.getString(consultaRegistros.
                        getColumnIndex("descripcion"));
                double precio= consultaRegistros.getDouble(consultaRegistros.
                        getColumnIndex("precio"));

                dataModel.setCodigo(codigo);
                dataModel.setDescripcion(descripcion);
                dataModel.setPrecio(precio);
                stringBuffer.append(dataModel);
                productos.add(dataModel);
            }
            for (Producto prod:productos) {
                Log.i("Producto: ",prod.getDescripcion());
            }
        }
        consultaRegistros.close();
        db.close();
        return productos;
    }
}

