package com.tiempociudades.edu1010.examen;

public class Platos {
    private String ingredientes;
    private String nombre;
    private String precio;

    public Platos() {
    }

    public Platos(String ingredientes, String nombre, String precio) {
        this.ingredientes = ingredientes;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
