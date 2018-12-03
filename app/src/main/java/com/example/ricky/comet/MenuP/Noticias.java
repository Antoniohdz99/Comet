package com.example.ricky.comet.MenuP;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ricky.comet.R;
import com.example.ricky.comet.Utencilios.Adaptador_RV_Comido;
import com.example.ricky.comet.Utencilios.Adaptador_news;
import com.example.ricky.comet.Utencilios.Comentario_item;
import com.example.ricky.comet.Utencilios.TiposComida;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Noticias extends Fragment {


    public Noticias() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        recyclerView= view.findViewById(R.id.Rc_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        final ArrayList<Comentario_item> ListData = new ArrayList<>();

       ListData.add(new Comentario_item("Los farolitos",R.drawable.antojito_mex,R.drawable.p1));
     ListData.add(new Comentario_item("Antojitos Mexicanos",R.drawable.ic_news,R.drawable.p2));
        ListData.add(new Comentario_item("Encanto Comida",R.drawable.ic_perfil,R.drawable.p3));
        Adaptador_news adapterDatos = new Adaptador_news(ListData);
        adapterDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"Nombre: " +ListData.get(recyclerView.getChildAdapterPosition(v)).getNews_nombre_user(),Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapterDatos);



        return  view;
    }

}
