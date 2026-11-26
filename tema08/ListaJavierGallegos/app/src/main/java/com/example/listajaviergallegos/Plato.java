package com.example.listajaviergallegos;

public class Plato {

    private String receta;
    private String tipo;
    private int imagen;

    //Mi constructor
    public Plato(String receta, String tipo, int imagen) {
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

    public int getImagen() {
        return imagen;
    }
}
