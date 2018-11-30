package com.example.ricky.comet.Login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.ricky.comet.inicio.Inicio_sesion;
import com.example.ricky.comet.inicio.Principal;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Facebook {

    //Constructor de loguin in facebook
    LoginButton loginButton;
     CallbackManager mCallbackManager;
    FirebaseAuth myauth;

    public Login_Facebook()
    {
        myauth=FirebaseAuth.getInstance();
        mCallbackManager =  CallbackManager.Factory.create();


    }

    public CallbackManager getmCallbackManager()
    {
        return mCallbackManager;
    }

    public void setLoginButton(LoginButton loginButton) {
        this.loginButton = loginButton;
    }


    public void registerCallback(final Context context)
    {

        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginButton.setReadPermissions("email", "public_profile");
                handleFacebookAccessToken(loginResult.getAccessToken(),context);
            }

            @Override
            public void onCancel() {
                Toast.makeText(context,"¡Cancelado!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(context,"¡Error al Iniciar Sesión!", Toast.LENGTH_SHORT).show();
                Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void handleFacebookAccessToken(AccessToken token, final Context context) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        myauth.signInWithCredential(credential).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Error", "onFailure: "+e);
            }
        }).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = myauth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(context,"Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }



}
