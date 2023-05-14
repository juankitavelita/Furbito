package com.example.trabajofinal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Button btnVolver;

    // Método para configurar las columnas de la TableView de ligas
    private void configurarColumnasLigas() {
        columnaLigas.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
    }

    @FXML
    public void volver(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicio.fxml"));

            Parent root = loader.load();

            InicioController controlador = loader.getController();

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

    // Método para cargar los datos de las ligas en la TableView
    private void cargarDatosLigas() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root",
                    "631534833Poly");
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
    // Método que se ejecuta al cargar el FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColumnasLigas();
        cargarDatosLigas();
    }
}