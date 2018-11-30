package com.example.ricky.comet.inicio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ricky.comet.MenuP.Noticias;
import com.example.ricky.comet.MenuP.Pedidos;
import com.example.ricky.comet.MenuP.Perfil;
import com.example.ricky.comet.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class Principal extends AppCompatActivity {


    private FirebaseAuth mAuth;
    BottomNavigationView navigationView;

    Noticias noticias = new Noticias();
    Pedidos pedidos = new Pedidos();
    Perfil  perfil = new Perfil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        navigationView = findViewById(R.id.menu);




        getSupportFragmentManager().beginTransaction().replace(R.id.Fragmento, pedidos).commit();



        mAuth = FirebaseAuth.getInstance();


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.Perfil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragmento, perfil).commit();

                        break;
                    case R.id.Pedidos:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragmento, pedidos).commit();

                        break;
                    case R.id.Noticias:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragmento, noticias).commit();

                        break;
                }

                return false;
            }
        });

    }

    private void goLoginScreen() {
      //  Intent intent = new Intent(Principal.this, MainActivity.class);
      //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
       // startActivity(intent);
    }

}
