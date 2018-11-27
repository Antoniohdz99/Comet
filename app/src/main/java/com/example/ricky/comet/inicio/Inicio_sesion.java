package com.example.ricky.comet.inicio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricky.comet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Inicio_sesion extends AppCompatActivity {


    EditText txtmail, txtpass;
    FirebaseAuth myauth;
    String correo,contrasena;
    Intent redirije;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        txtmail=findViewById(R.id.txtmail);
        txtpass=findViewById(R.id.txtpass);

        myauth=FirebaseAuth.getInstance();

        correo=String.valueOf(txtmail.getText());
        contrasena=String.valueOf(txtpass.getText());

        myauth.signInWithEmailAndPassword(correo,contrasena).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Inicio_sesion.this, "¡Inició Sesión!", Toast.LENGTH_SHORT).show();
                    redirije = new Intent(Inicio_sesion.this,MainActivity.class);
                    startActivity(redirije);
                }else {
                    Toast.makeText(Inicio_sesion.this, "¡Error, Verifica tus datos!", Toast.LENGTH_SHORT);
                }
            }
        });


    }

}
