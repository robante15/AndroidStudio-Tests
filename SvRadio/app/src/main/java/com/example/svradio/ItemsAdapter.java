package com.example.svradio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private Context mCtx;
    List<Item> itemList;
    //private List<Item> mTestItemList;
    private View.OnClickListener mOnItemClickListener;
    public ItemsAdapter(Context mCtx,List<Item> itemList) {
        this.itemList = itemList;
        this.mCtx = mCtx;
    }

    //TODO: Paso 2 de 4: Asignar itemClickListener a tu variable local View.OnClickListener
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_item, url_item;

        public MyViewHolder(View view) {
            super(view);

            name_item = (TextView) view.findViewById(R.id.name_item);
            url_item = (TextView) view.findViewById(R.id.url_item);

            // TODO: Paso 3 de 4: Asignar a setTag() como titular de la vista actual junto con setOnClickListener()
            // como su variable local View.OnClickListener.
            //Puede configurar el mismo mOnItemClickListener en varias vistas si es necesario
            // y luego diferenciar esos clics utilizando el ID de la vista.
           // Es decir Pasamos la  instancia del ViewHolder via itemView al  listener usando setTag().
            view.setTag(this);
            view.setOnClickListener(mOnItemClickListener);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item =(Item) itemList.get(position);
        holder.name_item.setText(item.getName());
        holder.url_item.setText(item.getUrl());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
