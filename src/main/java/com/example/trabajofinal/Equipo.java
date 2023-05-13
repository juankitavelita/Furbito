package com.example.trabajofinal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Equipo {
    private IntegerProperty id;
    private StringProperty nombre;
    private IntegerProperty rankingFifa;
    private IntegerProperty numJugadores;
    private IntegerProperty idLiga;
    private StringProperty liga;

    public StringProperty ligaProperty() {
        return liga;
    }

    public Equipo(int id, String nombre, int rankingFifa, int numJugadores, int idLiga) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.rankingFifa = new SimpleIntegerProperty(rankingFifa);
        this.numJugadores = new SimpleIntegerProperty(numJugadores);
        this.idLiga = new SimpleIntegerProperty(idLiga);
    }

    // Métodos de acceso a las propiedades

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public IntegerProperty rankingFifaProperty() {
        return rankingFifa;
    }

    public IntegerProperty numJugadoresProperty() {
        return numJugadores;
    }

    public IntegerProperty idLigaProperty() {
        return idLiga;
    }

    // Resto de métodos de la clase
}
