package com.emmanuel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    /**
     * this method will create a table on the database TodoList.db already created
     * this is run once method and should be called only if the is no existing table or new table
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:sqlite/TodoList.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS todos (\n"
                + " id integer PRIMARY KEY,\n"
                + " title text NOT NULL,\n"
                + " project text NOT NULL,\n"
                + " date text NOT NULL,\n"
                + " isDone integer\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            // display message if there was something wrong when create table
            System.out.println(e.getMessage());
        }
    }
    public static void renameTableField() {
        // SQLite connection string
        String url = "jdbc:sqlite:sqlite/TodoList.db";

        // SQL statement for creating a new table

        String sql = "Drop TABLE employees ";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            // display message if there was something wrong when create table
            System.out.println(e.getMessage());
        }
    }
}
