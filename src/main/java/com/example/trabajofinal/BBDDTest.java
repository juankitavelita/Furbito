package com.example.trabajofinal;
import java.sql.*;

public class BBDDTest {
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
            //competiciones nacionales (ligas)
            smt.executeUpdate("CREATE TABLE nacional (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), pais VARCHAR(20) UNIQUE, num_equipos INT)");
            //selecciones nacionales
            smt.executeUpdate("CREATE TABLE seleccion (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), ranking_fifa INT UNIQUE, num_jugadores INT)");
            //equipos
            smt.executeUpdate("CREATE TABLE equipo (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), ranking_fifa INT UNIQUE, num_jugadores INT, id_liga INT, " +
                    "FOREIGN KEY (id_liga) REFERENCES nacional(id))");
            //juagdores
            smt.executeUpdate("CREATE TABLE futbolista (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), apellido VARCHAR(20), posicion VARCHAR(5), dorsal INT, id_equipo INT, id_seleccion INT," +
                    "FOREIGN KEY (id_equipo) REFERENCES equipo(id), FOREIGN KEY (id_seleccion) REFERENCES seleccion (id))");
            //datos de usuarios registrados
            smt.executeUpdate("CREATE TABLE usuarios (id INT AUTO_INCREMENT, PRIMARY KEY (id), " +
                    "nombre VARCHAR(20), apellido VARCHAR(20), usuario VARCHAR(30), contrasena VARCHAR(20))");

            // introducción de datos
            //nacional
            String nombre_nacional[] = {"LaLiga", "Premier League", "Ligue One", "Bundesliga", "Serie A"};
            String pais[] = {"España", "Inglaterra", "Francia", "Alemania", "Italia"};
            int num_equipo_nac[] = {20, 20, 20, 18, 20};
            for (int i = 0; i < nombre_nacional.length; i++){
                smt.executeUpdate("INSERT INTO nacional (nombre, pais, num_equipos) VALUES ('" + nombre_nacional[i] + "', '" + pais[i] + "', " + num_equipo_nac[i] + ")");
            }
            //selecciones
            String nombre_seleccion[] = {"Argentina", "Francia", "Brasil", "Belgica", "Inglaterra", "Paises Bajos", "Croacia", "Italia", "Portugal", "España"};
            int ranking_fifa[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int num_jug_sel[] = {26, 25, 25, 22, 25, 24, 25, 26, 25, 24};
            for (int i = 0; i < nombre_seleccion.length; i++){
                smt.executeUpdate("INSERT INTO seleccion (nombre, ranking_fifa, num_jugadores) VALUES ('" + nombre_seleccion[i] + "', " + ranking_fifa[i] + ", " + num_jug_sel[i] + ")");
            }
            //equipos
            String nombre_equipo[] = {"Real Madrid", "Barcelona", "Manchester City", "Manchester United", "Paris Saint-Germain", "Marseille", "Bayern Munich", "Borussia Dortmund", "Juventus", "Napoli"};
            int ranking[] = {2, 5, 1, 15, 17, 31, 3, 14, 18, 4};
            int num_jug_equipo[] = {24, 30, 25, 29, 22, 25, 27, 21, 23, 26};
            int liga[] = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
            for (int i = 0; i < nombre_equipo.length; i++){
                smt.executeUpdate("INSERT INTO equipo (nombre, ranking_fifa, num_jugadores, id_liga) VALUES ('" + nombre_equipo[i] + "', " + ranking[i] + ", " + num_jug_equipo[i] + ", " + liga[i] + ")");
            }
            //jugadores
            String nombre_jugador[] = {"Cristiano", "Luka", "Eder", "Thibout", "Raphael", "Frenkie", "Jules", "Iñaki", "Jack", "Kevin", "Ruben", "Eder", "Lionel", "Marco", "Presnel", "Gianluigi", ""};
            String apellido_jugador[] = {"Ronaldo", "Modric", "Militao", "Courtois", "Dias", "de Jong", "Koundé", "Peña", "Grealish", "de Bruyne", "Dias", "Moraes", "Messi", "Verratti", "Kimpembe", "Donnarumma"};
            String posición[] = {"DEL", "MED", "DEF", "POR", "DEL", "MED", "DEF", "POR", "DEL", "MED", "DEF", "POR", "DEL", "MED", "DEF", "POR"};
            int dorsal[] = {7, 10, 3, 1, 22, 21, 23, 13, 10, 17, 3, 31, 30, 6, 3, 99};
            int equipo[] = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4};
            int seleccion[] = {9, 7, 3, 4, 3, 6, 2, 10, 5, 4, 9, 3, 1, 8, 2, 8};
            for (int i = 0; i < nombre_equipo.length; i++){
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}