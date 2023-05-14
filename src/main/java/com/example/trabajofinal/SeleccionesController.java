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
    private Button btnFavorito;


    LoginController loginController = new LoginController();
    String nombreUsuario = loginController.getNombreUsuario();

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
    public void seleccionFavorita(ActionEvent event) throws SQLException {
        Seleccion seleccion = table.getSelectionModel().getSelectedItem();
        if (seleccion == null) {
            // Si no se ha seleccionado ninguna fila, mostrar un mensaje de error.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar una selección.");
            alert.showAndWait();
            return;
        }

        // Obtener el nombre de usuario del usuario que ha iniciado sesión
        LoginController loginController = new LoginController();
        String nombreUsuario = loginController.getNombreUsuario();

        // Obtener la conexión a la base de datos
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol",
                "root", "631534833Poly");

        // Obtener el ID de la selección seleccionada
        String sql = "SELECT id FROM seleccion WHERE nombre = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, seleccion.getNombre().getValue()); // suponiendo que el nombre de la selección está en la columna "nombre"
        ResultSet rs = stmt.executeQuery();

        int idSeleccion = -1; // valor por defecto en caso de que no se encuentre la selección
        if (rs.next()) {
            idSeleccion = rs.getInt("id");
        }

        // Insertar el ID de la selección en la tabla "usuarios"
        String sql2 = "UPDATE usuarios SET id_seleccionFav = ? WHERE usuario = ?";
        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        stmt2.setInt(1, idSeleccion);
        stmt2.setString(2, nombreUsuario);
        stmt2.executeUpdate();

        // Cerrar los objetos ResultSet, PreparedStatement y Connection
        rs.close();
        stmt.close();
        stmt2.close();
        conn.close();
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
