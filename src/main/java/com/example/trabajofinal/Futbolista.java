package com.example.trabajofinal;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Futbolista {
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty apellido;
    private final SimpleStringProperty posicion;
    private SimpleIntegerProperty dorsal = null;
    private final SimpleStringProperty equipo;
    private final SimpleStringProperty seleccion;

    private static final String URL = "jdbc:mysql://localhost:3306/futbol";
    private static final String USUARIO = "root";
    private static final String CLAVE = "contraseña";

    public static ObservableList<Futbolista> obtenerFutbolistas() {
        ObservableList<Futbolista> futbolistas = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement stmt = conn.prepareStatement("SELECT f.nombre, f.apellido, f.posicion, f.dorsal," +
                     " s.nombre AS seleccion, e.nombre AS equipo FROM futbolista f " +
                     "INNER JOIN seleccion s ON f.id_seleccion = s.id " +
                     "INNER JOIN equipo e ON f.id_equipo = e.id");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Futbolista futbolista = new Futbolista(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("posicion"),
                        rs.getInt("dorsal"),
                        rs.getString("equipo"),
                        rs.getString("seleccion")

                );
                futbolistas.add(futbolista);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los futbolistas: " + e.getMessage());
        }
        return futbolistas;
    }

    public Futbolista(String nombre, String apellido, String posicion, int dorsal,
                      String nombre_equipo, String nombre_seleccion) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.posicion = new SimpleStringProperty(posicion);
        this.dorsal = new SimpleIntegerProperty(dorsal);
        this.equipo = new SimpleStringProperty(nombre_equipo);
        this.seleccion = new SimpleStringProperty(nombre_seleccion);

    }

    public SimpleStringProperty getNombre() {
        return nombre;
    }

    public SimpleStringProperty getApellido() {
        return apellido;
    }

    public SimpleStringProperty getPosicion(){
        return posicion;
    }

    public SimpleIntegerProperty getDorsal(){
        return dorsal;
    }

    public SimpleStringProperty getEquipo(){
        return equipo;
    }

    public SimpleStringProperty getSeleccion(){
        return seleccion;
    }
}
