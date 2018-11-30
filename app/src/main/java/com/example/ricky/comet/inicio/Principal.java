package com.example.ricky.comet.inicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ricky.comet.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class Principal extends AppCompatActivity {

    Button btncierra;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Button btncierra = findViewById(R.id.btncierra);
        if (!btncierra.equals(null)) {

        findViewById(R.id.btncierra).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Principal.this, "¡Cerrando Sesión!", Toast.LENGTH_SHORT).show();
                Intent ir = new Intent(Principal.this, MainActivity.class);
                startActivity(ir);
            }
        });
    }
        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        }

// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    private void goLoginScreen() {
      //  Intent intent = new Intent(Principal.this, MainActivity.class);
      //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
       // startActivity(intent);
    }

}
