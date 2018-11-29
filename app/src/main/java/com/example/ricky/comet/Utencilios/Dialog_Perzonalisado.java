package com.example.ricky.comet.Utencilios;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import com.example.ricky.comet.R;
import com.example.ricky.comet.inicio.Registro;

public class Dialog_Perzonalisado {

   private AlertDialog alerta;
    AlertDialog.Builder builder;
    public Dialog_Perzonalisado(Context context, int v) {

         builder= new AlertDialog.Builder(context);



        builder.setTitle("Cargando");

        builder.setView(v);

    }
    public void no_celable() {
        builder.setCancelable(false);
    }
    public void activar() {
        this.alerta =builder.create();
    }

    public void mostrar() {
        alerta.show();
    }

    public void ocultar() {
        alerta.cancel();
    }
}
