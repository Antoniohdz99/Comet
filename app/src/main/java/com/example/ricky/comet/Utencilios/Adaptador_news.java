package com.example.ricky.comet.Utencilios;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricky.comet.R;

import java.util.ArrayList;

public class Adaptador_news extends  RecyclerView.Adapter<Adaptador_news.ViewHolderDatos>
        implements View.OnClickListener {

    ArrayList<Comentario_item> ListData;
    private View.OnClickListener listener;

    public Adaptador_news(ArrayList<Comentario_item> listData1) {
        this.ListData = listData1;
    }

    @Override
    public Adaptador_news.ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(Adaptador_news.ViewHolderDatos holder, int position) {
        holder.textView.setText(ListData.get(position).getNews_nombre_user() );
        holder.imageView.setImageResource(ListData.get(position).getNews_img_perfil_user());
        holder.imageView2.setImageResource(ListData.get(position).getNews_img_comen_user());
    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }
    public void setOnClickListener(View.OnClickListener listener1)
    {
        this.listener = listener1;
    }
    @Override
    public void onClick(View v) {
        if (listener != null)
        {
            listener.onClick(v);
        }

    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView ,imageView2;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.news_tex_nom_usr);
            imageView = itemView.findViewById(R.id.news_img_usr);
          imageView2= itemView.findViewById(R.id.nesw_img_com_usr);
        }

    }
}
