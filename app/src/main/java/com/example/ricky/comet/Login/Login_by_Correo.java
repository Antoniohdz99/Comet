package com.example.ricky.comet.Login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.ricky.comet.inicio.Inicio_sesion;
import com.example.ricky.comet.inicio.Principal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_by_Correo {
    FirebaseAuth myauth;

    public Login_by_Correo() {
        myauth=FirebaseAuth.getInstance();
    }

    public void signInWithEmailAndPassword (String correo , String contrasena, final Context context) {
        myauth.signInWithEmailAndPassword(correo,contrasena).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "¡Inició Sesión!", Toast.LENGTH_SHORT).show();
                }else if(task.isCanceled()) {
                    Toast.makeText(context, "¡Error, Verifica tus datos!", Toast.LENGTH_SHORT);
                }
            }

        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {




            }
        });

    }
}
