package com.example.ricky.comet.inicio;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricky.comet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registro extends AppCompatActivity {

    EditText
    nombre,
    correo,
    contra,
    re_contra,
    edad;

    Button reg;

    private FirebaseAuth mAuth;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Edit text del ingreso a la aplicacion
        //nombre correo contraseña y edad
        nombre = findViewById(R.id.ag_name);
        correo =findViewById(R.id.ag_correo);
        contra = findViewById(R.id.ag_contra);
        re_contra = findViewById(R.id.ag_re_contra);
        edad = findViewById(R.id.ag_edad);

        //Boton de registro
        reg = findViewById(R.id.reg);

        //Agregar instacia a la base de datos
        mAuth = FirebaseAuth.getInstance();



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contra == re_contra) {
                    mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contra.getText().toString())
                            .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("ingresado", "createUserWithEmail:success");
                                        //Tomando el uid del usuario resien reguistrado
                                        String User_id = mAuth.getCurrentUser().getUid();
                                        //objeto con la lista de datos del usuario
                                        HashMap<String, Object> result = new HashMap<>();
                                        //lista de datos del usuario
                                            result.put("nombre",nombre.getText());
                                            result.put("correo",correo.getText());
                                            result.put("edad",edad.getText());
                                            //ingresando los datos del usuario
                                        FirebaseDatabase.getInstance().getReference().child("Usuarios").child(User_id).setValue(result);


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        //si el usuario no se pudo crear susedeta lo siguiete
                                        Log.w("Error", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), "fallo al autentificar.", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }
                else
                    {
                        Toast.makeText(getApplicationContext(),"Las contraseñas son distintas",Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
}
