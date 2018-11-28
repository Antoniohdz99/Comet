package com.example.ricky.comet.Login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.ricky.comet.inicio.Inicio_sesion;
import com.example.ricky.comet.inicio.Principal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Auth_user {

    private FirebaseAuth
            mAutentification;
    private FirebaseAuth.AuthStateListener
            stateListener;

    public Auth_user() {
        //instancioamos firebase
        mAutentification = FirebaseAuth.getInstance();

    }

    public void check_user(final Context context)
    {
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //llamamos a nuestra tabla usuario
                FirebaseUser Usuario = FirebaseAuth.getInstance().getCurrentUser();

                //Checamos si existe
                if (Usuario!=null)
                {

                    Intent intent = new Intent(context,Principal.class);

                    context.startActivity(intent);
                }

            }
        };

    }


    public void Start()
    {
        mAutentification.addAuthStateListener(stateListener);
    }

    public void Stop()
    {
        mAutentification.removeAuthStateListener(stateListener);
    }





}
