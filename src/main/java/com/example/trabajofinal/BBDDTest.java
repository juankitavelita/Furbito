package com.example.trabajofinal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BBDDTest {
    public static void main(String[] args) throws SQLException {
        Connection dbConnection=null;
        try{
            dbConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol","root",
                    "631534833Poly");
            Statement smt = dbConnection.createStatement();

            System.out.println("Conexión ha sido establecida");

            //creacion de tablas
            smt.executeUpdate("CREATE TABLE equipo (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(50) NOT NULL, " +
                    "liga VARCHAR(20), competicion_europea VARCHAR(20))");

            String nombre_equipo[] = {"PSG", "Al-Nassr"};

            for (int i = 0; i < nombre_equipo.length; i++){
                smt.executeUpdate("INSERT INTO equipo (nombre) VALUES ('" + nombre_equipo[i] + "')");
            }

            //tabla seleccion
            smt.executeUpdate("CREATE TABLE seleccion (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(50) NOT NULL, " +
                    "continente VARCHAR(20), competicion_internacional VARCHAR(30))");

            String nombre_seleccion[] = {"Argentina", "Portugal", "Francia"};
            String continente[] = {"America", "Europa", "Europa"};

            for (int i = 0; i < nombre_seleccion.length; i++){
                smt.executeUpdate("INSERT INTO seleccion (nombre, continente) VALUES ('" + nombre_seleccion[i] + "', '" + continente[i] + "')");
            }

            //tabla futbolista
            smt.executeUpdate("CREATE TABLE futbolista (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), apellido VARCHAR(20), posicion VARCHAR(5), dorsal INT, id_equipo INT, id_seleccion INT," +
                    "FOREIGN KEY (id_equipo) REFERENCES equipo (id), FOREIGN KEY (id_seleccion) REFERENCES seleccion (id))");


            String nombre[] = {"Leo", "Cristiano", "Kylian"};
            String apellido[] = {"Messi", "Ronaldo", "Mbappe"};
            String id_equipo[] = {"1", "2", "1"};
            String posicion[] = {"MC","DEL","DEL"};
            String dorsal[] = {"30", "7", "7"};
            String id_seleccion[] = {"1", "2", "3"};


            for (int i = 0; i < nombre.length; i++){
                smt.executeUpdate("INSERT INTO futbolista (nombre, apellido, id_equipo, posicion, dorsal, id_seleccion) VALUES ('" + nombre[i] + "', '"
                        + apellido[i] + "', '" + id_equipo[i] + "', '" + posicion[i] + "', '" + dorsal[i] + "', '" + id_seleccion[i] + "')");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
