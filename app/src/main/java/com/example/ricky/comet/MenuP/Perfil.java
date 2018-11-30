package com.example.ricky.comet.MenuP;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ricky.comet.R;
import com.example.ricky.comet.inicio.MainActivity;
import com.example.ricky.comet.inicio.Principal;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class Perfil extends Fragment {

    Button btncierra;
    public Perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_perfil, container, false);


        Button btncierra = view.findViewById(R.id.btncierra);

        btncierra.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                FirebaseAuth.getInstance().signOut();
                                                                Toast.makeText(getContext(), "¡Cerrando Sesión!", Toast.LENGTH_SHORT).show();
                                                                Intent ir = new Intent(getContext(), MainActivity.class);
                                                                startActivity(ir);
                                                            }});
// Initialize Firebase Auth

        return view;
    }

}
