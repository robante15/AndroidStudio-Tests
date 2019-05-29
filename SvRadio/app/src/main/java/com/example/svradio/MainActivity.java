package com.example.svradio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
            /*
             private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
             */
        private List<Item> itemList = new ArrayList<>();
        RecyclerView recyclerView;
        private ItemsAdapter mAdapter;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Paso 4 de 4: Finalmente, llame al metodo getTag () en la vista.
            // Este viewHolder tendra los valores reuqeridos.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();

            int position = viewHolder.getAdapterPosition();
            Item thisItem = itemList.get(position);
            String nombre,url;
            nombre=thisItem.getName();
            url =thisItem.getUrl();

            Toast.makeText(MainActivity.this, "Seleccion: " + nombre, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), RadioActivity.class);

            Bundle b = new Bundle();
            b.putString("nombre", nombre);
            b.putString("url", url);
            intent.putExtras(b);
            getBaseContext().startActivity(intent);

        }
    };
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            recyclerView = (RecyclerView) findViewById(R.id.item_list);
            mAdapter = new ItemsAdapter(this,itemList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(mAdapter);
            //TODO: Paso 1 de 4: Crear y configurar OnItemClickListener al adaptador.
            mAdapter.setOnItemClickListener(onItemClickListener);
            prepareData();


        }
    private void prepareData() {

        Item item=new Item("CADENA CUSCATLAN","http://195.154.182.222:3587");
        itemList.add(item);
        String nombre="RADIO PROGRESO";
        String url="http://streamingcontrol.com:9110/stream?type=http&nocache=205";
        item=new Item(nombre,url);
        itemList.add(item);
        nombre="Exa FM";
        url="http://158.69.219.162:8028/stream/";
        item=new Item(nombre,url);
        itemList.add(item);
        nombre="Radio ABC";
        url="http://gruposamixfm.mixstre.am:8006/";
        item=new Item(nombre,url);
        itemList.add(item);
        nombre="Radio Fiesta";
        url="http://cdn4.siscompnetwork.com:8062/stream";
        item=new Item(nombre,url);
        itemList.add(item);
        url="http://158.69.200.1:8022/stream/";
        nombre="Scan 96.1";
        item=new Item(nombre,url);
        itemList.add(item);
        url="http://gruposamixfm.mixstre.am:8008/stream";
        nombre="Radio La Chevere";
        item=new Item(nombre,url);
        itemList.add(item);
        url="http://cdn4.siscompnetwork.com:8066/;stream.mp3";
        nombre="Laser Español";
        item=new Item(nombre,url);
        itemList.add(item);
        url="http://23.239.68.75:8104/";
        nombre="Radio 1080";
        item=new Item(nombre,url);
        itemList.add(item);
        url="http://media.dominiocreativo.com:8000/radioyskl";
        nombre="Radio YSKL ";
        item=new Item(nombre,url);
        itemList.add(item);
        url="http://67.212.189.122:8107/stream";
        nombre="Radio Paz";
        item=new Item(nombre,url);
        itemList.add(item);
        mAdapter.notifyDataSetChanged();

    }
}
