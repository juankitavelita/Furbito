package com.example.trabajofinal;

public class Liga {
    private int id;
    private String nombre;
    private String pais;

    public Liga(String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
