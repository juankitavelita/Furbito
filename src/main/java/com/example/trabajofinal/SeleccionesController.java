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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SeleccionesController implements Initializable {
    @FXML
    private TableView<Seleccion> table;
    @FXML
    private TableColumn<Seleccion, String> nombreColumn;
    @FXML
    private TableColumn<Seleccion, Integer> rankingColumn;
    @FXML
    private TableColumn<Seleccion, Integer> numColumn;

    @FXML
    private Button btnVolver;

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

        @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        rankingColumn.setCellValueFactory(cellData -> cellData.getValue().getRanking().asObject());
        numColumn.setCellValueFactory(cellData -> cellData.getValue().getNumJugadores().asObject());

        // Cargar los datos de los futbolistas
        table.setItems(Seleccion.obtenerSelecciones());
    }

}
