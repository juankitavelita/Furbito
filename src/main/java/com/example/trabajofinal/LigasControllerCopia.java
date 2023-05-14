package com.example.trabajofinal;

public class LigasControllerCopia {
}
/*
package com.example.trabajofinal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LigasControllerCopia implements Initializable {
    @FXML
    private TableView<Liga> ligas;
    @FXML
    private TableColumn<Liga, String> columnaLigas;
    @FXML
    private TableColumn<Liga, String> columnaPais;
    @FXML
    private TableView<Equipo> equipos;
    @FXML
    private TableView<Equipo> columnaEquipo;
    @FXML
    private TableView<Equipo> columnaRanking;
    @FXML
    private TableView<Equipo> columnaJugadores;
    @FXML
    private TableView<Equipo> columnaLiga;
    @FXML
    private Button btnSeleccionar;

    // Método para configurar las columnas de la TableView de ligas
    private void configurarColumnasLigas() {
        // Configurar las columnas de la TableView de ligas
        TableColumn<Liga, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Liga, String> columnaPais = new TableColumn<>("Pais");
        columnaPais.setCellValueFactory(new PropertyValueFactory<>("pais"));

        ligas.getColumns().addAll(columnaNombre, columnaPais);
    }

    // Método para cargar los datos de las ligas en la TableView
    private void cargarDatosLigas() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "contraseña");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM nacional");

            ligas.getItems().clear();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String pais = resultSet.getString("pais");

                ligas.getItems().add(new Liga(nombre, pais));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método que se ejecuta al presionar el botón "btnSeleccionar"
    @FXML
    private void seleccionarLiga() {
        Liga ligaSeleccionada = ligas.getSelectionModel().getSelectedItem();

        if (ligaSeleccionada != null) {
            cargarDatosEquipos(ligaSeleccionada.getId());
        }
    }

    // Método para cargar los datos de los equipos en la TableView según la liga seleccionada
    private void cargarDatosEquipos(int idLiga) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nombre_basedatos", "usuario", "contraseña");
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM equipos WHERE id_liga = ?");
            statement.setInt(1, idLiga);
            ResultSet resultSet = statement.executeQuery();

            equipos.getItems().clear();

            while (resultSet.next()) {
                // Obtener los datos de los equipos y añadirlos a la TableView de equipos
                String nombre = resultSet.getString("nombre");
                int rankingFifa = resultSet.getInt("ranking_fifa");
                int numJugadores = resultSet.getInt("num_jugadores");
                String nombreLiga = resultSet.getString("nombre_liga");

                equipos.getItems().add(new Equipo(nombre, rankingFifa, numJugadores, nombreLiga));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método que se ejecuta al cargar el FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnasLigas();
        cargarDatosLigas();
    }
}
 */

/*
package com.example.trabajofinal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
public class LigasController implements Initializable {
    @FXML
    private TableView<Liga> ligas;
    @FXML
    private TableColumn<Liga, String> columnaLigas;
    @FXML
    private TableColumn<Liga, String> columnaPais;
    @FXML
    private TableView<Equipo> equipos;
    @FXML
    private TableColumn<Equipo, String> columnaEquipo;
    @FXML
    private TableColumn<Equipo, Integer> columnaRanking;
    @FXML
    private TableColumn<Equipo, Integer> columnaJugadores;
    @FXML
    private TableColumn<Equipo, String> columnaLiga;

    @FXML
    private Button btnSeleccionar;

    // Método para configurar las columnas de la TableView de ligas
    private void configurarColumnasLigas() {
        columnaLigas.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
    }
    private void configurarColumnasEquipos() {
        TableColumn<Equipo, String> columnaNombre = new TableColumn<>("Nombre");
        columnaEquipo.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Equipo, Integer> columnaRankingFifa = new TableColumn<>("Ranking Fifa");
        columnaRanking.setCellValueFactory(new PropertyValueFactory<>("rankingFifa"));

        TableColumn<Equipo, Integer> columnaNumJugadores = new TableColumn<>("Jugadores");
        columnaJugadores.setCellValueFactory(new PropertyValueFactory<>("numJugadores"));

        TableColumn<Equipo, String> columnaNombreLiga = new TableColumn<>("Liga");
        columnaLiga.setCellValueFactory(new PropertyValueFactory<>("nombreLiga"));

        equipos.getColumns().addAll(columnaNombre, columnaRankingFifa, columnaNumJugadores, columnaNombreLiga);
    }

    // Método para cargar los datos de las ligas en la TableView
    private void cargarDatosLigas() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "contraseña");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM nacional");

            ligas.getItems().clear();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String pais = resultSet.getString("pais");

                ligas.getItems().add(new Liga(nombre, pais));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método que se ejecuta al presionar el botón "btnSeleccionar"
    @FXML
    private void seleccionarLiga() {
        Liga ligaSeleccionada = ligas.getSelectionModel().getSelectedItem();

        if (ligaSeleccionada != null) {
            cargarDatosEquipos(ligaSeleccionada.getId());
        }
    }

    // Método para cargar los datos de los equipos en la TableView según la liga seleccionada
    private void cargarDatosEquipos(int idLiga) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "contraseña");
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM equipos WHERE id_liga = ?");
            statement.setInt(1, idLiga);
            ResultSet resultSet = statement.executeQuery();

            equipos.getItems().clear();

            while (resultSet.next()) {
                // Obtener los datos de los equipos y añadirlos a la TableView de equipos
                String nombre = resultSet.getString("nombre");
                int rankingFifa = resultSet.getInt("ranking_fifa");
                int numJugadores = resultSet.getInt("num_jugadores");
                String nombreLiga = resultSet.getString("nombre_liga");

                equipos.getItems().add(new Equipo(nombre, rankingFifa, numJugadores, nombreLiga));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método que se ejecuta al cargar el FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnasLigas();
        configurarColumnasEquipos();
        cargarDatosLigas();
    }
}
 */