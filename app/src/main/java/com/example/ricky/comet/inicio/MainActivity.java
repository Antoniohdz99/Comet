package com.example.ricky.comet.inicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ricky.comet.R;

public class MainActivity extends AppCompatActivity {

Button
    btn_iniciar_S,
    btn_registrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_iniciar_S = findViewById(R.id.log);
        btn_registrar = findViewById(R.id.reg);

        btn_iniciar_S.setOnClickListener(Click_iniciar_s);
        btn_registrar.setOnClickListener(Click_Registrar);

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
}
