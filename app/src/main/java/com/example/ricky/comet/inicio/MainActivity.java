package com.example.ricky.comet.inicio;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ricky.comet.Login.Auth_user;
import com.example.ricky.comet.R;

import com.example.ricky.comet.Utencilios.Dialog_Perzonalisado;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        auth_user.check_user(MainActivity.this);
        btn_iniciar_S = findViewById(R.id.log);
        btn_registrar = findViewById(R.id.reg);

        btn_iniciar_S.setOnClickListener(Click_iniciar_s);
        btn_registrar.setOnClickListener(Click_Registrar);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.ricky.comet",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", "KeyHash:" + Base64.encodeToString(md.digest(),
                        Base64.DEFAULT));
                Toast.makeText(getApplicationContext(), Base64.encodeToString(md.digest(),
                        Base64.DEFAULT), Toast.LENGTH_LONG).show();
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

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
