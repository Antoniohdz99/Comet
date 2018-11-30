package com.example.ricky.comet.MenuP;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ricky.comet.R;
import com.example.ricky.comet.Utencilios.Adaptador_RV_Comido;
import com.example.ricky.comet.Utencilios.TiposComida;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pedidos extends Fragment {


    public Pedidos() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pedidos, container, false);


        recyclerView= view.findViewById(R.id.RcID);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        ArrayList<TiposComida> ListData = new ArrayList<>();
        ListData.add(new TiposComida("Comida Mexicana",R.drawable.comida_mex));
        ListData.add(new TiposComida("Antojitos Mexicana",R.drawable.antojito_mex));
        ListData.add(new TiposComida("Comida China",R.drawable.comida_china));
        ListData.add(new TiposComida("Comida Taylandesa",R.drawable.comida_taylandesa));
        Adaptador_RV_Comido adapterDatos = new Adaptador_RV_Comido(ListData);
        recyclerView.setAdapter(adapterDatos);





        return view;
    }

}
