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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FutbolistaController implements Initializable {
    @FXML
    private TableView<Futbolista> table;
    @FXML
    private TableColumn<Futbolista, String> nombreColumn;
    @FXML
    private TableColumn<Futbolista, String> apellidoColumn;
    @FXML
    private TableColumn<Futbolista, Integer> dorsalColumn;
    @FXML
    private TableColumn<Futbolista, String> posicionColumn;
    @FXML
    private TableColumn<Futbolista, String> equipoColumn;
    @FXML
    private TableColumn<Futbolista, String> seleccionColumn;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnImprimir;

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

    @FXML
    public void imprimirDatos(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        apellidoColumn.setCellValueFactory(cellData -> cellData.getValue().getApellido());
        dorsalColumn.setCellValueFactory(cellData -> cellData.getValue().getDorsal().asObject());
        posicionColumn.setCellValueFactory(cellData -> cellData.getValue().getPosicion());
        equipoColumn.setCellValueFactory(cellData -> cellData.getValue().getEquipo());
        seleccionColumn.setCellValueFactory(cellData -> cellData.getValue().getSeleccion());

        // Cargar los datos de los futbolistas
        table.setItems(Futbolista.obtenerFutbolistas());
    }
}

