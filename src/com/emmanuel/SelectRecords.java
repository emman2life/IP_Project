package com.emmanuel;

import java.sql.*;

public class SelectRecords {
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:sqlite/TodoList.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void selectAll(){
        String sql = "SELECT * FROM todos";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("title") + "\t" +
                        rs.getString("description") + "\t" +
                        rs.getString("date") + "\t" +
                        rs.getInt("isDone"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
