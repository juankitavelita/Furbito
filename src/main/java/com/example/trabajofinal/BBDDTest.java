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
                    "contraseña");
            Statement smt = dbConnection.createStatement();
            System.out.println("Conexión ha sido establecida");
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS comp_selecciones (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), zona VARCHAR(20), num_selecciones INT)");
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS europa (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), num_equipos INT)");
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS nacional (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), pais VARCHAR(20) UNIQUE, num_equipos INT)");
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS seleccion (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), ranking_fifa INT UNIQUE, num_jugadores INT, id_comp_selecciones INT, " +
                    "FOREIGN KEY (id_comp_selecciones) REFERENCES comp_selecciones(id))");
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS equipo (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), ranking_fifa INT UNIQUE, num_jugadores INT, id_liga INT, " +
                    "id_europa INT, FOREIGN KEY (id_liga) REFERENCES nacional(id), FOREIGN KEY (id_europa) REFERENCES europa(id))");
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS futbolista (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), apellido VARCHAR(20), posicion VARCHAR(5), dorsal INT, id_equipo INT, id_seleccion INT," +
                    "FOREIGN KEY (id_equipo) REFERENCES equipo(id), FOREIGN KEY (id_seleccion) REFERENCES seleccion (id))");
            smt.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), apellido VARCHAR(20), usuario VARCHAR(30), contraseña VARCHAR(20))");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}