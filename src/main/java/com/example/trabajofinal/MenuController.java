package com.example.trabajofinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegistro;

    @FXML
    protected void iniciarSesion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

            Parent root = loader.load();

            LoginController controlador = loader.getController();

            // Crea la escena de la ventana de registro
            Scene scene = new Scene(root);

            // Obtiene la etapa actual (ventana) y establece la escena de la ventana de registro
            Stage stage = (Stage) btnLogin.getScene().getWindow();
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
    protected void registroUsuario(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Registro.fxml"));

            Parent root = loader.load();

            RegistroController controlador = loader.getController();

            Scene scene = new Scene(root);

            Stage stage = (Stage) btnRegistro.getScene().getWindow();
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
}