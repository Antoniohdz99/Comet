package com.example.ricky.comet.Utencilios;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricky.comet.R;

import java.util.ArrayList;

public class Adaptador_RV_Comido extends RecyclerView.Adapter<Adaptador_RV_Comido.ViewHolderDatos>  {

    ArrayList<TiposComida> ListData;

    public Adaptador_RV_Comido(ArrayList<TiposComida> listData) {
        ListData = listData;
    }

    @Override
    public Adaptador_RV_Comido.ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tipo_comida,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(Adaptador_RV_Comido.ViewHolderDatos holder, int position) {
        holder.textView.setText(ListData.get(position).getNombre_tipo() );

        holder.imageView.setImageResource(ListData.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return  ListData.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        public ViewHolderDatos(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.Item_comida_texto);
            imageView = itemView.findViewById(R.id.Itme_comida_imagen);
        }


    }
}
