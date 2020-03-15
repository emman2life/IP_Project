package com.emmanuel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertRecord {
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


    public void insert(String title, String des, String date, int isDone) {
        String sql = "INSERT INTO todos(title, description, date, isDone) VALUES(?,?,?,?)";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, des);
            pstmt.setString(3, date);
            pstmt.setInt(4, isDone);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
