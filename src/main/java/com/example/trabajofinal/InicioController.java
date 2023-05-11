package com.example.trabajofinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioController {
    @FXML
    private Button btnCompEu;
    @FXML
    private Button btnEquipos;
    @FXML
    private Button btnJugadores;
    @FXML
    private Button btnCompSel;
    @FXML
    private Button btnSel;
    @FXML
    private Button btnLigas;

    @FXML
    protected void verJugadores(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Jugadores.fxml"));

            Parent root = loader.load();

            FutbolistaController controlador = loader.getController();

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
}
