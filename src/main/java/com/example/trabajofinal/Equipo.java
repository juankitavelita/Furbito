package com.example.trabajofinal;

public class Equipo {
    private String nombre;
    private int rankingFifa;
    private int numJugadores;
    private String nombreLiga;
    private int id;

    public Equipo(String nombre, int rankingFifa, int numJugadores, String nombreLiga, int id) {
        this.nombre = nombre;
        this.rankingFifa = rankingFifa;
        this.numJugadores = numJugadores;
        this.nombreLiga = nombreLiga;
        this.id = id;

    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRankingFifa() {
        return rankingFifa;
    }

    public void setRankingFifa(int rankingFifa) {
        this.rankingFifa = rankingFifa;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public String getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(String nombreLiga) {
        this.nombreLiga = nombreLiga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
