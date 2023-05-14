package com.example.trabajofinal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    // Método para configurar las columnas de la TableView de ligas
    private void configurarColumnasLigas() {
        columnaLigas.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
    }

    // Método para cargar los datos de las ligas en la TableView
    private void cargarDatosLigas() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol", "root", "contraseña");
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