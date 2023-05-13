package com.example.trabajofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LigasController {
    @FXML
    private TableView<Liga> ligas;

    @FXML
    private TableColumn<Liga, String> columnaLigas;

    @FXML
    private TableColumn<Liga, String> columnaPais;

    @FXML
    private TableView<Equipo> equipos;

    @FXML
    private TableColumn<Equipo, String> columnaNombre;

    @FXML
    private TableColumn<Equipo, Integer> columnaRanking;

    @FXML
    private TableColumn<Equipo, Integer> columnaJugadores;

    @FXML
    private TableColumn<Equipo, String> columnaLiga;

    @FXML
    private Button btnSeleccionar;

    private Connection connection;

    public void initialize() {
        // Establecer conexión con la base de datos
        String url = "jdbc:mysql://localhost/futbol?user=root&password=contraseña";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Configurar columnas de la tabla ligas
        columnaLigas.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaPais.setCellValueFactory(cellData -> cellData.getValue().paisProperty());

        // Configurar columnas de la tabla equipos
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaRanking.setCellValueFactory(cellData -> cellData.getValue().rankingFifaProperty().asObject());
        columnaJugadores.setCellValueFactory(cellData -> cellData.getValue().numJugadoresProperty().asObject());
        columnaLiga.setCellValueFactory(cellData -> cellData.getValue().ligaProperty());

        // Cargar ligas desde la tabla nacional
        cargarLigas();
    }

    private void cargarLigas() {
        String query = "SELECT id, nombre, pais FROM nacional";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Liga liga = new Liga(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("pais"));
                ligas.getItems().add(liga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void seleccionarLiga() {
        Liga ligaSeleccionada = ligas.getSelectionModel().getSelectedItem();
        if (ligaSeleccionada != null) {
            cargarEquipos(ligaSeleccionada.getId());
        }
    }

    private void cargarEquipos(int idLiga) {
        equipos.getItems().clear();

        String query = "SELECT id, nombre, ranking_fifa, num_jugadores, id_liga FROM equipo WHERE id_liga = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idLiga);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Equipo equipo = new Equipo(resultSet.getInt("id"), resultSet.getString("nombre"),
                        resultSet.getInt("ranking_fifa"), resultSet.getInt("num_jugadores"),
                        resultSet.getInt("id_liga"));
                equipos.getItems().add(equipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
