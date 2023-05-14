package com.example.trabajofinal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EquiposController implements Initializable {
    @FXML
    private TableView<Equipo> tablaEquipos;
    @FXML
    private TableColumn<Equipo, String> nombre;
    @FXML
    private TableColumn<Equipo, Integer> ranking;
    @FXML
    private TableColumn<Equipo, Integer> numJugadores;
    @FXML
    private TableColumn<Equipo, String> liga;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnas();
        cargarDatosEquipos();
    }

    private void configurarColumnas() {
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ranking.setCellValueFactory(new PropertyValueFactory<>("rankingFifa"));
        numJugadores.setCellValueFactory(new PropertyValueFactory<>("numJugadores"));
        liga.setCellValueFactory(new PropertyValueFactory<>("nombreLiga"));
    }

    private void cargarDatosEquipos() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "contraseña");
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM equipo");

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int rankingFifa = resultSet.getInt("ranking_fifa");
                int numJugadores = resultSet.getInt("num_jugadores");
                String nombreLiga = resultSet.getString("id_liga");

                tablaEquipos.getItems().add(new Equipo(nombre, rankingFifa, numJugadores, nombreLiga));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}