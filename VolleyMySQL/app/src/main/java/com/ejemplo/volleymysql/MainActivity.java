package com.ejemplo.volleymysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String IP_SERVER  ="http://apps.fmoues.edu.sv/gestion/";
    //consultarProd.php

    private static final String ROUTE_SERVER="consultarProd.php";
    // direccion del servidor que da la URL, DESDE DONDE TRAEMOS LA INFORMACION
    private static final String URL_PRODUCTS = IP_SERVER+ROUTE_SERVER;

    List<Product> productList;
    // recyclerview
    RecyclerView recyclerView;

    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtener el recyclerview de xml
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //inicializar el array para los productos a traer
        productList = new ArrayList<>();

        //Este metodo  trae el JSON y lo pasa al recyclerview
       // loadProducts();
    }
    public void sincronizar(View v) {
        Toast.makeText(getBaseContext(),"Intentando conectar a Servidor "+URL_PRODUCTS,Toast.LENGTH_LONG).show();
        loadProducts();
    }
    private void loadProducts() {
        /*
         * Crear  un String Request
         * El tipo de peticion es GET definido como primer parametro
         * La URL  es definida como primer parametro
         * Entonces tenemos  un Response Listener y un Error Listener
         * En el response listener obtenemos el  JSON como un String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(getBaseContext(),"conectando ",Toast.LENGTH_LONG).show();
                            //convertir  el string a json array object
                            JSONArray array = new JSONArray(response);
                            productList = Arrays.asList(gson.fromJson(response, Product[].class));
                            Log.i("Main Activity", productList.size() + " productos cargados.");
                            Toast.makeText(getBaseContext(),"Termina carga: "+productList.size() + " productos cargados.",Toast.LENGTH_LONG).show();
                            //crear el  adaptador y asignarlo al  recyclerview
                            ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}