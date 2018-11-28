package com.example.ricky.comet.inicio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricky.comet.Login.Login_Facebook;
import com.example.ricky.comet.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Inicio_sesion extends AppCompatActivity {


    EditText txtmail, txtpass;
    FirebaseAuth myauth;
    String correo,contrasena;
    Button btninicia;
    Intent redirije;


    //FACEBOOK
    LoginButton loginButton;
   Login_Facebook login_facebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);


        txtmail=findViewById(R.id.txtmail);
        txtpass=findViewById(R.id.txtpass);
        btninicia=findViewById(R.id.btninicia);
        myauth=FirebaseAuth.getInstance();



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
        });//CORREO Y CONTRASEÑA

        // Initialize Facebook Login button
        login_facebook = new Login_Facebook();
        loginButton = (LoginButton)findViewById(R.id.login_button);

        login_facebook.setLoginButton(loginButton);
        login_facebook.registerCallback(Inicio_sesion.this);








    }//ON CREATE


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        login_facebook.getmCallbackManager().onActivityResult(requestCode, resultCode, data);
    }




}
