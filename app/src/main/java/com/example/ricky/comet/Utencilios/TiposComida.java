package com.example.ricky.comet.Utencilios;

public class TiposComida {

    String Nombre_tipo ="";
    int Imagen =0;

    public TiposComida(String nombre_tipo, int imagen) {
        Nombre_tipo = nombre_tipo;
        Imagen = imagen;
    }

    public String getNombre_tipo() {
        return Nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        Nombre_tipo = nombre_tipo;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {

        Imagen = imagen;
    }
}
