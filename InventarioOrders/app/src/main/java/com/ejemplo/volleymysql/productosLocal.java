package com.ejemplo.volleymysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class productosLocal extends AppCompatActivity {

    List<Product> productList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_local);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //inicializar el array para los productos a traer
        productList = new ArrayList<>();
        sincronizar();
    }

    public void sincronizar() {
        Toast.makeText(getBaseContext(), "Cargando productos de la BD Local", Toast.LENGTH_LONG).show();
        cargarProductosBDLocal();
    }

    private void cargarProductosBDLocal() {
        AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(getBaseContext(), "articulos", null, 1);
        ProductsAdapter adapter = new ProductsAdapter(productosLocal.this, adminSQLiteOpenHelper.baseDatosLocal());
        recyclerView.setAdapter(adapter);
    }
}
