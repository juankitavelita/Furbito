package com.example.trabajofinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioController {

    @FXML
    private Button btnEquipos;
    @FXML
    private Button btnJugadores;
    @FXML
    private Button btnSel;
    @FXML
    private Button btnLigas;

    @FXML
    private Label lblNombreUsuario;
    private String nombreUsuario;

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        lblNombreUsuario.setText(nombreUsuario);
    }
    @FXML
    protected void verJugadores(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Jugadores.fxml"));

            Parent root = loader.load();

            JugadoresController controlador = loader.getController();

            // Crea la escena de la ventana de registro
            Scene scene = new Scene(root);

            // Obtiene la etapa actual (ventana) y establece la escena de la ventana de jugadores
            Stage stage = (Stage) btnJugadores.getScene().getWindow();
            stage.setScene(scene);

            // Muestra la ventana de registro
            stage.show();
        } catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    protected void verSeleciones(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Selecciones.fxml"));

            Parent root = loader.load();

            SeleccionesController controlador = loader.getController();

    @FXML
    protected void verLigas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ligas.fxml"));

            Parent root = loader.load();

            LigasController controlador = loader.getController();

            // Crea la escena de la ventana de registro
            Scene scene = new Scene(root);

            // Obtiene la etapa actual (ventana) y establece la escena de la ventana de jugadores
            Stage stage = (Stage) btnLigas.getScene().getWindow();
            stage.setScene(scene);

            // Muestra la ventana de registro
            stage.show();
        } catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    protected void verEquipos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Equipos.fxml"));

            Parent root = loader.load();

            EquiposController controlador = loader.getController();

            // Crea la escena de la ventana de registro
            Scene scene = new Scene(root);

            // Obtiene la etapa actual (ventana) y establece la escena de la ventana de jugadores

            Stage stage = (Stage) btnSel.getScene().getWindow();

            Stage stage = (Stage) btnEquipos.getScene().getWindow();

            stage.setScene(scene);

            // Muestra la ventana de registro
            stage.show();
        } catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}
