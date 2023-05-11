package com.example.trabajofinal;

import java.sql.*;

public class BaseDatos {
    public static void main(String[] args) throws SQLException {
        Connection dbConnection=null;
        try{
            dbConnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol","root",
                    "contraseña");
            Statement smt = dbConnection.createStatement();
            // consulta para verificar si las tablas ya existen
            ResultSet rs = smt.executeQuery("SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'futbol' AND table_name IN ('comp_selecciones', 'europa', 'nacional', 'seleccion', 'equipo', 'futbolista', 'usuarios')");

            if (((ResultSet) rs).next() && rs.getInt(1) == 7) {
                System.out.println("Las tablas ya existen en la base de datos.");
                return;
            }
            // creación de las tablas
            smt.executeUpdate("CREATE TABLE comp_selecciones (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), zona VARCHAR(20), num_selecciones INT)");
            smt.executeUpdate("CREATE TABLE europa (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), num_equipos INT)");
            smt.executeUpdate("CREATE TABLE nacional (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), pais VARCHAR(20) UNIQUE, num_equipos INT)");
            smt.executeUpdate("CREATE TABLE seleccion (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), ranking_fifa INT UNIQUE, num_jugadores INT, id_comp_selecciones INT, " +
                    "FOREIGN KEY (id_comp_selecciones) REFERENCES comp_selecciones(id))");
            smt.executeUpdate("CREATE TABLE equipo (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), ranking_fifa INT UNIQUE, num_jugadores INT, id_liga INT, " +
                    "id_europa INT, FOREIGN KEY (id_liga) REFERENCES nacional(id), FOREIGN KEY (id_europa) REFERENCES europa(id))");
            smt.executeUpdate("CREATE TABLE futbolista (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), apellido VARCHAR(20), posicion VARCHAR(5), dorsal INT, id_equipo INT, id_seleccion INT," +
                    "FOREIGN KEY (id_equipo) REFERENCES equipo(id), FOREIGN KEY (id_seleccion) REFERENCES seleccion (id))");
            smt.executeUpdate("CREATE TABLE usuarios (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), apellido VARCHAR(20), usuario VARCHAR(30), contraseña VARCHAR(20))");
            // introducción de datos
            //comp_selecciones
            String com_seleccion[] = {"Eurocopa", "Copa America", "Mundial"};
            String zona[] = {"Europa", "Sudamerica", "Mundial"};
            String num_selec[] = {"53", "10", "32"};
            for (int i = 0; i < com_seleccion.length; i++){
                smt.executeUpdate("INSERT INTO comp_selecciones (nombre, zona, num_selecciones) VALUES ('" + com_seleccion[i] + "', '" + zona[i] + "', " + num_selec[i] + ")");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}