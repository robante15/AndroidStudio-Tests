package com.robante15.sqlote;

import android.content.Context;

import android.support.v7.widget.Recyclerview:

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.MiHolder> {
   Context context;
   private List<Producto> ProductoList;

    public AdaptadorRecycler(Context context,List<Producto> ProductoList) {
        this.ProductoList = ProductoList;
        this.context = context;
    }

    @Override
    public MiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista,parent,false);
        return new MiHolder(v);
    }

    @Override
    public void onBindViewHolder(MiHolder holder, int position) {
        final Producto temporal = (Producto) ProductoList.get(position);
        String precio_txt=Double.toString(temporal.getPrecio());
        holder.nombre.setText(temporal.getCodigo()+" - "+temporal.getDescripcion());

        holder.precio.setText("$ "+precio_txt);
    }

    @Override
    public int getItemCount() {
        return ProductoList.size();
    }

    class MiHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre, precio;
        Button boton;

        public MiHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.textoNombre);
            precio = itemView.findViewById(R.id.textoPrecio);
            boton = itemView.findViewById(R.id.botonDetalle);
            boton.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            final Producto temporal = (Producto) ProductoList.get(pos);
            Toast.makeText(context, "Seleccionado: " + temporal.getCodigo(), Toast.LENGTH_SHORT).show();
        }
    }
}