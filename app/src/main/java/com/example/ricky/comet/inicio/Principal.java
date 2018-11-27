package com.example.ricky.comet.inicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ricky.comet.R;
import com.google.firebase.auth.FirebaseAuth;

public class Principal extends AppCompatActivity {

    Button btncierra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        findViewById(R.id.btncierra).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Principal.this,"¡Cerrando Sesión!",Toast.LENGTH_SHORT).show();
                Intent ir = new Intent(Principal.this,MainActivity.class);
            }
        });
    }
}
