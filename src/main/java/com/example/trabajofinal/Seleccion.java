package com.example.trabajofinal;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Seleccion {
    private final SimpleStringProperty nombre;
    private final SimpleIntegerProperty ranking;
    private final SimpleIntegerProperty numJugadores;

    private static final String URL = "jdbc:mysql://localhost:3306/futbol";
    private static final String USUARIO = "root";
    private static final String CLAVE = "631534833Poly";

    public static ObservableList<Seleccion> obtenerSelecciones() {
        ObservableList<Seleccion> selecciones = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement stmt = conn.prepareStatement("SELECT nombre, ranking_fifa, num_jugadores FROM seleccion");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Seleccion seleccion = new Seleccion(
                        rs.getString("nombre"),
                        rs.getInt("ranking_fifa"),
                        rs.getInt("num_jugadores")
                );
                selecciones.add(seleccion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las selecciones: " + e.getMessage());
        }
        return selecciones;
    }

    public Seleccion(String nombre, int ranking, int numJugadores) {
        this.nombre = new SimpleStringProperty(nombre);
        this.ranking = new SimpleIntegerProperty(ranking);
        this.numJugadores = new SimpleIntegerProperty(numJugadores);
    }

    public SimpleStringProperty getNombre() {
        return nombre;
    }

    public SimpleIntegerProperty getRanking(){
        return ranking;
    }

    public SimpleIntegerProperty getNumJugadores(){
        return numJugadores;
    }


}
