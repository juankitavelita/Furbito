package com.example.trabajofinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class RegistroController {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField pswContra;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnVolver;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/futbol";

    static final String USER = "root";
    static final String PASS = "631534833Poly";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    @FXML
    public void guardar(ActionEvent event) {
        String nombre = this.txtNombre.getText();
        String apellidos = this.txtApellido.getText();
        String usuario = this.txtUsuario.getText();
        String contrasena = this.pswContra.getText();

        if (nombre.isEmpty() || apellidos.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            // Lanzar una excepción si algún campo está vacío
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Faltan datos obligatorios");
            alert.setContentText("Por favor ingresa todos los campos obligatorios.");
            alert.showAndWait();
        } else {
            try (Connection conn = RegistroController.getConnection();
                 Statement stmt = conn.createStatement()) {

                String sql = "INSERT INTO usuarios (nombre, apellido, usuario, contrasena) VALUES ('" + nombre +
                        "', '" + apellidos + "', '" + usuario + "', '" + contrasena + "')";

                stmt.executeUpdate(sql);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));

                Parent root = loader.load();

                MenuController controlador = loader.getController();

                Scene scene = new Scene(root);


                Stage stage = (Stage) btnGuardar.getScene().getWindow();
                stage.setScene(scene);

                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void volver(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));

            Parent root = loader.load();

            MenuController controlador = loader.getController();

            // Crea la escena de la ventana de registro
            Scene scene = new Scene(root);

            // Obtiene la etapa actual (ventana) y establece la escena de la ventana de registro
            Stage stage = (Stage) btnVolver.getScene().getWindow();
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
