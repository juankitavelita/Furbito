package com.example.trabajofinal;
public class Equipo {
    private int id;
    private String nombre;
    private int rankingFifa;
    private int numJugadores;
    private int idLiga;

    public Equipo(int id, String nombre, int rankingFifa, int numJugadores, int idLiga) {
        this.id = id;
        this.nombre = nombre;
        this.rankingFifa = rankingFifa;
        this.numJugadores = numJugadores;
        this.idLiga = idLiga;
    }

    public Equipo(String nombre, int rankingFifa, int numJugadores, String nombreLiga) {
    }

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

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }
}