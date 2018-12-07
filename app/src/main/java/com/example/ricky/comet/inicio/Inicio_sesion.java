package com.example.ricky.comet.inicio;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricky.comet.Login.Auth_user;
import com.example.ricky.comet.Login.Login_Facebook;
import com.example.ricky.comet.Login.Login_by_Correo;
import com.example.ricky.comet.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Inicio_sesion extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    EditText txtmail, txtpass;
    String correo,contrasena;
    Button btninicia;
   Login_by_Correo login_by_correo;


    //FACEBOOK
    LoginButton loginButton;
   Login_Facebook login_facebook;


   //Autentificacion
    Auth_user auth_user;


    //GOOGLE Api Client
    GoogleApiClient googleApiClient;
    private SignInButton signgoogle;
    public static final int codigo_inicio=15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);


        //Iniciado la autentificacion
        auth_user = new Auth_user();
        //Listener de autentificacion
        auth_user.check_user(Inicio_sesion.this);

        //Varibles de Autenticacion
        //Correo
        txtmail=findViewById(R.id.txtmail);
        //Contraseña
        txtpass=findViewById(R.id.txtpass);

        //Btn Inicio sesión
        btninicia=findViewById(R.id.btninicia);


        //Iniciando la classe de logion by Facebook
        login_by_correo = new Login_by_Correo();


        //Onclik del boton de registro
        btninicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                correo=String.valueOf(txtmail.getText());
                contrasena=String.valueOf(txtpass.getText());
                if (!txtmail.getText().equals(null) &&  !txtpass.getText().equals(null)) {
                    login_by_correo.signInWithEmailAndPassword(correo, contrasena, Inicio_sesion.this);
                }else
                    {
                        Toast.makeText(getApplicationContext(),"Ingrese todos los datos",Toast.LENGTH_LONG).show();
                    }

            }
        });//CORREO Y CONTRASEÑA

       //Iniciando la classe de login facebook
        login_facebook = new Login_Facebook();
        // Initialize Facebook Login button
        loginButton = (LoginButton)findViewById(R.id.login_button);
        //Tomande el login button
        login_facebook.setLoginButton(loginButton);
        //Activando el registerCallbak
        login_facebook.registerCallback(Inicio_sesion.this);




        //LOGIN GOOGLE

        //GOOGLE SIGN OPTIONS
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //EL GOOGLE API CLIENTE ES EL INTERMEDIARIO ENTRE LAS APPS Y LAS APPS DE GOOGLE
        //CREAR EL API, ERRORES,AÑADIR API PARA INICIO DE SESIÓN
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this )
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signgoogle = findViewById(R.id.signgoogle);
        signgoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,codigo_inicio);
            }
        });

    }//FIN ON CREATE


    //ERROR EN CONEXIÓN A GUGUL
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        login_facebook.getmCallbackManager().onActivityResult(requestCode, resultCode, data);

        if(requestCode==codigo_inicio){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignResult(result);
        }
    }

    //RESULTADO
    private void handleSignResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            goMainScreen();
        }else{
            Toast.makeText(this,"No se pudo iniciar sesión", Toast.LENGTH_SHORT);
        }
    }

    private void goMainScreen() {
        Intent ir = new Intent(Inicio_sesion.this,Principal.class);
        ir.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(ir);
    }

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
