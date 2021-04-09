package com.productos.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase para crear y obtener una única instancia de coneccion
 * a la base de datos mediante el patrón singleton.
 * @autor James Tovar Rodriguez
 * @email u20172163883@usco.edu.co
 */
public class ConnectionDB {

    private static Connection connection;

    private ConnectionDB() {};

    /**
     * Este método retorna la instancia de coneccion, si no existe la retorna.
     * @return Connection
     */
    public static Connection getConnection() {

        if(connection == null){

            try {
                final String HOST = "localhost";
                final String USER = "root";
                final String PASS = "20172163883";
                final String DATABASE = "EmpresaDB";

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection("jdbc:mysql://"+HOST+":3306/"
                        +DATABASE+"?useSSL=false",USER,PASS);

                System.out.println("==== SE CREÓ UNA CONEXION ====");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return connection;
    }
}
