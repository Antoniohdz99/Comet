package com.example.ricky.comet.inicio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricky.comet.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Inicio_sesion extends AppCompatActivity {


    EditText txtmail, txtpass;
    FirebaseAuth myauth;
    FirebaseUser usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        txtmail=findViewById(R.id.txtmail);
        txtpass=findViewById(R.id.txtpass);

        myauth=FirebaseAuth.getInstance();
        usuario=myauth.getCurrentUser();

        if(usuario==null){

        }





    }
}
