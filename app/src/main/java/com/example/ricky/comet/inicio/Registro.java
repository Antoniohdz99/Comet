package com.example.ricky.comet.inicio;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class Registro extends AppCompatActivity {

    //validacion del usuario ingresado
    FirebaseUser usuario;
    FirebaseAuth myauth;
    //Datos del reguistro
    EditText
    nombre,
    correo,
    contra,

    edad;
//Botton del reguistro
    Button reg;
//Conesión con la base
    private FirebaseAuth mAuth;
    private static final String CERO = "0";
    private static final String BARRA = "-";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Edit text del ingreso a la aplicacion
        //nombre correo contraseña y edad
        nombre = findViewById(R.id.ag_name);
        correo =findViewById(R.id.ag_correo);
        contra = findViewById(R.id.ag_contra);

        edad = findViewById(R.id.ag_edad);

        edad.setInputType(InputType.TYPE_NULL);
        edad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog recogerFecha = new DatePickerDialog(Registro.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                        final int mesActual = month + 1;
                        //Formateo el día obtenido: antepone el 0 si son menores de 10
                        String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                        //Formateo el mes obtenido: antepone el 0 si son menores de 10
                        String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                        //Muestro la fecha con el formato deseado
                        edad.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


                    }
                    //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                    /**
                     *También puede cargar los valores
                     */
                },anio, mes, dia);
                //Muestro el widget
                recogerFecha.show();
            }
        });

        //Boton de registro
        reg = findViewById(R.id.registrar);

        //Agregar instacia a la base de datos
        mAuth = FirebaseAuth.getInstance();



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (correo.getText().toString() != "" && contra.getText().toString() != "" && edad.getText().toString() != "" && nombre.getText().toString() != "") {

                mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contra.getText().toString())
                        .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("ingresado", "createUserWithEmail:success");
                                    //Tomando el uid del usuario resien reguistrado
                                    String User_id = mAuth.getCurrentUser().getUid();
                                    //ingresando los datos del usuario
                                    FirebaseDatabase.getInstance().getReference().child("Usuarios").child(User_id).child("nombre").setValue(nombre.getText().toString());
                                    FirebaseDatabase.getInstance().getReference().child("Usuarios").child(User_id).child("correo").setValue(correo.getText().toString());
                                    FirebaseDatabase.getInstance().getReference().child("Usuarios").child(User_id).child("edad").setValue(edad.getText().toString());
                                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                                    Intent ir = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(ir);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    //si el usuario no se pudo crear susedeta lo siguiete
                                    Log.w("Error", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "fallo al autentificar.", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }else{
                    Toast.makeText(getApplicationContext(), "LLene todos los datos por favor", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        usuario=myauth.getInstance().getCurrentUser();

        //SI EL USUARIO ESTÁ LOGEADO, LO REDIRIJIMOS
        if(usuario!=null){
            Intent ir = new Intent(getApplicationContext(),Principal.class);
            startActivity(ir);
        }
    }
}
