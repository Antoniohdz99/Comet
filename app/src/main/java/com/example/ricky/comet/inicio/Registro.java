package com.example.ricky.comet.inicio;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ricky.comet.R;
import com.example.ricky.comet.Utencilios.Dialog_Perzonalisado;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;

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
    //Imagen del usuario
    Button Foto;
    ImageView Foto_ag;
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



    //Tomar Foto Con la camara
    private static final  String Carpeta_principal ="misImagenesApp/";//Carpeta principal
    private static final  String Carpeta_imagen ="imagenes";//donde se guarda la foto
    private static final  String Directorio_imagen =Carpeta_principal+Carpeta_imagen;//Ruta carpeta y directorio
    private String Path; //Almacenamiento de la ruta de la imagen
    File FileImage;
    Bitmap bitmap;



    FirebaseStorage storage = FirebaseStorage.getInstance();


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
        //Referencia para la foto
        Foto =findViewById(R.id.ag_foto);
        Foto_ag =findViewById(R.id.Foto_ag);




        //onclick de la Foto
        Foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mostrara un dialog con opciones
                MostrarDialogOpciones();
            }
        });


        //onclik de la Fecha
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

                if (correo.getText().length() >0   && contra.getText().toString().length()>0  && edad.getText().toString().length()>0  && nombre.getText().toString().length()>0 ) {
                    final Dialog_Perzonalisado barra_carga= new Dialog_Perzonalisado(Registro.this,R.layout.progres_bar);
                    barra_carga.no_celable();
                    barra_carga.activar();
                    barra_carga.mostrar();

                mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contra.getText().toString())
                        .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("ingresado", "createUserWithEmail:success");
                                    //Tomando el uid del usuario resien reguistrado



                                } else {
                                    // If sign in fails, display a message to the user.
                                    //si el usuario no se pudo crear susedeta lo siguiete
                                    barra_carga.ocultar();
                                    Log.w("Error", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "fallo al autentificar.", Toast.LENGTH_SHORT).show();
                                    reg.setEnabled(true);
                                }

                                // ...
                            }
                        }).addOnSuccessListener(Registro.this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        usuario=myauth.getInstance().getCurrentUser();

                        //SI EL USUARIO ESTÁ LOGEADO, LO REDIRIJIMOS
                        if(usuario!=null){
                            String User_id = mAuth.getCurrentUser().getUid();
                            //ingresando los datos del usuario
                            FirebaseDatabase.getInstance().getReference().child("Usuarios").child("Clientes").child(User_id).child("nombre").setValue(nombre.getText().toString());
                            FirebaseDatabase.getInstance().getReference().child("Usuarios").child("Clientes").child(User_id).child("correo").setValue(correo.getText().toString());
                            FirebaseDatabase.getInstance().getReference().child("Usuarios").child("Clientes").child(User_id).child("edad").setValue(edad.getText().toString());


                            Foto_ag.setDrawingCacheEnabled(true);
                            Foto_ag.buildDrawingCache();
                            Bitmap bitmap = ((BitmapDrawable) Foto_ag.getDrawable()).getBitmap();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            // Create a storage reference from our app
                            StorageReference storageRef = storage.getReference();

                            // Create a reference to "mountains.jpg"
                            StorageReference mountainsRef = storageRef.child("Perfil.jpg");

                            // Create a reference to 'images/mountains.jpg'
                            StorageReference mountainImagesRef = storageRef.child("Img_usuarios/"+User_id+"/Perfil.jpg");
                            byte[] data = baos.toByteArray();
                            UploadTask uploadTask = mountainImagesRef .putBytes(data);
                            uploadTask.addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                    reg.setEnabled(true);
                                }
                            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    barra_carga.ocultar();
                                    Intent ir = new Intent(getApplicationContext(),Principal.class);
                                    startActivity(ir);
                                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                                }
                            });



                        }
                    }
                });
            }else{

                    Toast.makeText(getApplicationContext(), "LLene todos los datos por favor", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void MostrarDialogOpciones() {
        //opciones del dialog
        final CharSequence[] Opciones = {"Tomar Foto","Elegir de la galeria","Cancelar"};
        //Creacion del dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //titulo del dialog
        builder.setTitle("Elige una opción");
        //inteface del dialog
        builder.setItems(Opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (Opciones[i].toString())
                {
                    case "Tomar Foto":{
                       AbrirCamara();
                    }break;
                    case "Elegir de la galeria":{
                        Intent  Imagen = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        Imagen.setType("image/");
                        startActivityForResult(Imagen.createChooser(Imagen,"Selecione"),10);

                    }break;
                    case "Cancelar":{
                        dialogInterface.dismiss();
                    }break;
                    default:break;
                }
            }
        });
        builder.show();

    }

    private void AbrirCamara() {
         File MyFile  = new File(Environment.getExternalStorageDirectory(),Directorio_imagen);
         boolean isCreada=MyFile.exists();
         if (!isCreada)
         {
             isCreada=MyFile.mkdirs();
         }
        if (isCreada)
        {
            Long Consecutivo =System.currentTimeMillis()/1000;
            String nombre = Consecutivo.toString()+".jpg";
            //Seindica la ruta de alamacenamiento
            Path = Environment.getExternalStorageDirectory()+File.separator+Directorio_imagen+File.separator+nombre;
            FileImage = new File(Path);
            Intent  Imagen = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Imagen.putExtra(MediaStore.EXTRA_OUTPUT,FileImage.toURI());
            if (Imagen.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(Imagen, 20);
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 10: {
                try {
                    Uri miPath = data.getData();
                    Foto_ag.setImageURI(miPath);
                }catch (Exception e){}

            }break;
            case 20: {


                    if ( resultCode == RESULT_OK){
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    Foto_ag.setImageBitmap(imageBitmap);
                        }




            }break;


        }
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
