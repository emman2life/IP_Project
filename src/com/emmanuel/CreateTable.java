package com.emmanuel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:sqlite/TodoList.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS todos (\n"
                + " id integer PRIMARY KEY,\n"
                + " title text NOT NULL,\n"
                + " description text NOT NULL,\n"
                + " date text NOT NULL,\n"
                + " isDone integer\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
