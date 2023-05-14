package com.example.trabajofinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField pswContra;
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnVolver;

    private Connection conn;
    private String nombreUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }


    public LoginController(){
        try {
            // Conectarse a la base de datos
            conn = DriverManager.getConnection("jdbc:mysql://localhost/futbol", "root",
                    "contraseña");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean verificarUsuario(String usuario, String contrasena) {
        boolean resultado = false;

        try {
            // Verificar si el usuario existe en la base de datos
            String sql = "SELECT usuario FROM usuarios WHERE usuario = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            resultado = rs.next();
            if (rs.next()) {
                nombreUsuario = usuario;
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @FXML
    public void iniciarSesion(ActionEvent event)  {
        String nombreUsuario = txtUsuario.getText();
        String contraseña = pswContra.getText();
        LoginController conexion = new LoginController();
        try {
            if (conexion.verificarUsuario(nombreUsuario, contraseña)) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicio.fxml"));

                Parent root = loader.load();

                InicioController controlador = loader.getController();
                controlador.setNombreUsuario(nombreUsuario);

                Scene scene = new Scene(root);

                Stage stage = (Stage) btnEntrar.getScene().getWindow();
                stage.setScene(scene);

                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Usuario y/o contraseña invalido");
                alert.setContentText("Por favor ingresa los datos correctos.");
                alert.showAndWait();
            }
        } catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    public void volver(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));

            Parent root = loader.load();

            MenuController controlador = loader.getController();

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

}