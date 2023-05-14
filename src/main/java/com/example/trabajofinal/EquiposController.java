package com.example.trabajofinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EquiposController implements Initializable {
    @FXML
    private TableView<Equipo> tablaEquipos;
    @FXML
    private TableColumn<Equipo, Integer> id;
    @FXML
    private TableColumn<Equipo, String> nombre;
    @FXML
    private TableColumn<Equipo, Integer> ranking;
    @FXML
    private TableColumn<Equipo, Integer> numJugadores;
    @FXML
    private TableColumn<Equipo, String> liga;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnFavorito;

    private String nombreUsuario;
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnas();
        cargarDatosEquipos();
    }

    @FXML
    public void volver(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicio.fxml"));

            Parent root = loader.load();

            InicioController controlador = loader.getController();
            controlador.setNombreUsuario(nombreUsuario);

            Scene scene = new Scene(root);

            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    private void configurarColumnas() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ranking.setCellValueFactory(new PropertyValueFactory<>("rankingFifa"));
        numJugadores.setCellValueFactory(new PropertyValueFactory<>("numJugadores"));
        liga.setCellValueFactory(new PropertyValueFactory<>("nombreLiga"));
    }

    private void cargarDatosEquipos() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root",
                    "631534833Poly");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT e.id, e.nombre, e.ranking_fifa, e.num_jugadores, n.nombre AS liga FROM equipo e " +
                    "INNER JOIN nacional n ON e.id_liga = n.id ");

            tablaEquipos.getItems().clear();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int rankingFifa = resultSet.getInt("ranking_fifa");
                int numJugadores = resultSet.getInt("num_jugadores");
                String nombreLiga = resultSet.getString("liga");

                tablaEquipos.getItems().add(new Equipo(nombre, rankingFifa, numJugadores, nombreLiga, id));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void anadirFavorito(ActionEvent event) {
        // Obtener el id del equipo seleccionado en la tabla
        Equipo equipoSeleccionado = tablaEquipos.getSelectionModel().getSelectedItem();
        int idEquipoSeleccionado = equipoSeleccionado.getId();

        // Conectar a la base de datos
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "631534833Poly");

            // Actualizar el campo 'id_equipoFav' de la tabla 'usuarios' para el usuario actual
            PreparedStatement ps = conn.prepareStatement("UPDATE usuarios SET id_equipoFav = ? WHERE usuario = ?");
            ps.setInt(1, idEquipoSeleccionado);
            ps.setString(2, nombreUsuario);
            ps.executeUpdate();

            conn.close();

            // Mostrar mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Añadir favorito");
            alert.setContentText("Equipo añadido a favoritos con éxito");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error al añadir equipo a favoritos");
            alert.showAndWait();
        }
    }
}