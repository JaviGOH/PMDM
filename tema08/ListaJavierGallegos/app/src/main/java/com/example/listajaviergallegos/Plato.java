package com.example.listajaviergallegos;

public class Plato {

    private String receta;
    private String tipo;
    //La imagen pasa de int a String
    private String imagen;

    //Mi constructor
    public Plato(String receta, String tipo, String imagen) {
        this.receta = receta;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    //Los getters
    public String getReceta() {
        return receta;
    }

    public String getTipo() {
        return tipo;
    }

    public String getImagen() {
        return imagen;
    }
}
