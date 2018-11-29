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

public class Inicio_sesion extends AppCompatActivity {


    EditText txtmail, txtpass;
    String correo,contrasena;
    Button btninicia;
   Login_by_Correo login_by_correo;


    //FACEBOOK
    LoginButton loginButton;
   Login_Facebook login_facebook;


   //Autentificacion
    Auth_user auth_user;

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

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.packagename",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }//ON CREATE


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        login_facebook.getmCallbackManager().onActivityResult(requestCode, resultCode, data);
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
