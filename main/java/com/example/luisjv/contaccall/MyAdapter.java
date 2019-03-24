
package com.example.luisjv.contaccall;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;


import java.util.ArrayList;

 /*
 * Adaptador personalizado
 */


public class MyAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable{

    Context c;
    ArrayList<Contacto> contactos,filterList;
    CustomFilter filter;


    public MyAdapter(Context ctx,ArrayList<Contacto> contactos)
    {
        this.c=ctx;
        this.contactos = contactos;
        this.filterList= contactos;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //CONVERT XML TO VIEW OBJ
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);
        //HOLDER
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        //BIND DATA
        holder.posTxt.setText(contactos.get(position).getTel());
        holder.nameTxt.setText(contactos.get(position).getName());

        //IMPLEMENT CLICK LISTENER
          /*
        holder.setItemClickListener(new ItemClickListener() {
            @Override

            public void onItemClick(View v, int pos) {
                String nombre= contactos.get(pos).getName();
                String number= contactos.get(pos).getTel();
                number.replace(" ","");
                Snackbar.make(v,nombre,Snackbar.LENGTH_SHORT).show();
            }
        });
         */
    }

    //GET TOTAL NUMBER
    @Override
    public int getItemCount() {
        return contactos.size();
    }

    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }
        return filter;
    }
}
