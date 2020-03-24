package com.emmanuel;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class create an sqlite database using jdbc
 */
public class Create {
    /**
     * this method will create a database with the parameter as the name of the database
     * this is run once method and should be called only if the is no existing database or new db
     * @param fileName
     */
    public static void createNewDatabase(String fileName) {
        // the sqlite url + chosen DB name
        String url = "jdbc:sqlite:sqlite/" + fileName;
// create database
        try {

            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}
