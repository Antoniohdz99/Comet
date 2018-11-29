package com.example.ricky.comet.inicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ricky.comet.Login.Auth_user;
import com.example.ricky.comet.R;

import com.example.ricky.comet.Utencilios.Dialog_Perzonalisado;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

Button
    btn_iniciar_S,
    btn_registrar;

    Auth_user auth_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth_user = new Auth_user();
        auth_user.check_user(MainActivity.this );
        btn_iniciar_S = findViewById(R.id.log);
        btn_registrar = findViewById(R.id.reg);

        btn_iniciar_S.setOnClickListener(Click_iniciar_s);
        btn_registrar.setOnClickListener(Click_Registrar);
        Dialog_Perzonalisado dialog_perzonalisado = new Dialog_Perzonalisado(MainActivity.this,R.layout.progres_bar);
        dialog_perzonalisado.activar();
        dialog_perzonalisado.mostrar();

    }


    private View.OnClickListener Click_iniciar_s = new View.OnClickListener() {
        public void onClick(View v) {
            Intent vs_inicia_s = new Intent(MainActivity.this,Inicio_sesion.class);
            startActivity(vs_inicia_s);
        }
    };


    private View.OnClickListener Click_Registrar = new View.OnClickListener() {
        public void onClick(View v) {
            Intent vs_inicia_s = new Intent(MainActivity.this,Registro.class);
            startActivity(vs_inicia_s);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        auth_user.Start();


    }
    @Override
    protected void onStop() {
        super.onStop();

        auth_user.Stop();


    }

}
