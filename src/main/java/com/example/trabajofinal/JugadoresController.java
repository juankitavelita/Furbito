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

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class JugadoresController implements Initializable {
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

    @FXML
    public void imprimirDatos(ActionEvent event) {
        Futbolista jugadorSeleccionado = table.getSelectionModel().getSelectedItem();
        if (jugadorSeleccionado == null) {
            // Si no se ha seleccionado ningún jugador, mostrar un mensaje de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Jugador no seleccionado");
            alert.setContentText("Por favor, seleccione un jugador de la tabla");
            alert.showAndWait();
        } else {
            try {
                // Crear el archivo para guardar los datos del jugador
                String fileName = jugadorSeleccionado.getNombre().getValue() + ".txt";
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                // Escribir los datos del jugador en el archivo
                FileWriter writer = new FileWriter(file);
                writer.write("Nombre: " + jugadorSeleccionado.getNombre().getValue() + "\n");
                writer.write("Apellido: " + jugadorSeleccionado.getApellido().getValue() + "\n");
                writer.write("Dorsal: " + jugadorSeleccionado.getDorsal().getValue() + "\n");
                writer.write("Posición: " + jugadorSeleccionado.getPosicion().getValue() + "\n");
                writer.write("Equipo: " + jugadorSeleccionado.getEquipo().getValue() + "\n");
                writer.write("Selección: " + jugadorSeleccionado.getSeleccion().getValue() + "\n");
                writer.close();
                // Abrir el archivo y leer su contenido
                try (FileReader reader = new FileReader(file);
                     BufferedReader br = new BufferedReader(reader)) {
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    String fileContent = sb.toString();
                    // Mostrar los datos del jugador y el contenido del archivo en ventanas de alerta
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Datos del jugador y contenido del archivo");
                    alert.setContentText("Los datos del jugador han sido guardados en el archivo: " + fileName
                            + "\n\n" + "El contenido del archivo es:\n\n" + fileContent);
                    alert.showAndWait();
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error al leer el archivo");
                    alert.setContentText("Ocurrió un error al intentar leer el archivo: " + ex.getMessage());
                    alert.showAndWait();
                }
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error al guardar los datos");
                alert.setContentText("Ocurrió un error al intentar guardar los datos del jugador: " + ex.getMessage());
                alert.showAndWait();
            }
        }
    }


}

