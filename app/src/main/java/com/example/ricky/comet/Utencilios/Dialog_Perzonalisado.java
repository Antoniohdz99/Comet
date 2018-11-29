package com.example.ricky.comet.Utencilios;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import com.example.ricky.comet.R;
import com.example.ricky.comet.inicio.Registro;

public class Dialog_Perzonalisado {

   private AlertDialog alerta;

    public Dialog_Perzonalisado(Context context, int v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);



        builder.setTitle("Cargando");
        builder.setCancelable(false);
        builder.setView(v);
         this.alerta =builder.create();
    }


    public void mostrar() {
        alerta.show();
    }

    public void ocultar() {
        alerta.cancel();
    }
}
