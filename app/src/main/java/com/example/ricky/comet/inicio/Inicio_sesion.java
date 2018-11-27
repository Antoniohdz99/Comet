package com.example.ricky.comet.inicio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricky.comet.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Inicio_sesion extends AppCompatActivity {


    EditText txtmail, txtpass;
    FirebaseAuth myauth;
    String correo,contrasena;
    Button btninicia;
    Intent redirije;


    LoginButton loginButton;
    CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        txtmail=findViewById(R.id.txtmail);
        txtpass=findViewById(R.id.txtpass);
        btninicia=findViewById(R.id.btninicia);
        loginButton = findViewById(R.id.login_button);
        mCallbackManager = CallbackManager.Factory.create();
        myauth=FirebaseAuth.getInstance();

        // Initialize Facebook Login button

        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent ir = new Intent(Inicio_sesion.this,Principal.class);
                startActivity(ir);
            }

            @Override
            public void onCancel() {
                Toast.makeText(Inicio_sesion.this,"¡Cancelado!", Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(Inicio_sesion.this,"¡Error al Iniciar Sesión!", Toast.LENGTH_SHORT);
            }
        });


        btninicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                correo=String.valueOf(txtmail.getText());
                contrasena=String.valueOf(txtpass.getText());

                myauth.signInWithEmailAndPassword(correo,contrasena).addOnCompleteListener(Inicio_sesion.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Inicio_sesion.this, "¡Inició Sesión!", Toast.LENGTH_SHORT).show();

                        }else if(task.isCanceled()) {
                            Toast.makeText(Inicio_sesion.this, "¡Error, Verifica tus datos!", Toast.LENGTH_SHORT);
                        }
                    }

                }).addOnSuccessListener(Inicio_sesion.this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        FirebaseUser usuario;
                        FirebaseAuth autorizacion = FirebaseAuth.getInstance();
                        usuario=autorizacion.getCurrentUser();

                        if(usuario!=null){
                            redirije = new Intent(Inicio_sesion.this,Principal.class);
                            startActivity(redirije);
                        }


                    }
                });


            }
        });


    }/*fin oncreate*/



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
