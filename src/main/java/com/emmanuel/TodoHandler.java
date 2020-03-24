package com.emmanuel;

//import jdk.dynalink.beans.StaticClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TodoHandler {

    /**
     * Get the connection to the database
     * @return
     */
    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:sqlite/TodoList.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            //Message if the database is not able to connected
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * The todo status isDone  is save as int  0 and 1 in the database
     * this method count how many 1 is in in isDone column  and return the value
     * @return
     */
    public static int completedTask() {

        int count = 0;

        // sql query to select todos table
        String sql = "SELECT * FROM todos ORDER BY title";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
            //get isDone int value from database and store it in a variable
                int done = rs.getInt("isDone");
                //add done variable value to count 0 or 1

                count += done == 1 ? 1 : 0;

            }
        } catch (SQLException e) {
            // Failed message if not able to connect
            System.out.println(e.getMessage());
        }
        return count;
    }

    /**
     * this method help query database and take sql query as input and return list of todos if successful
     * @param sqlQuery sql query
     * @return
     */
    private  static ArrayList<Todo> selectWithSqlQuery(String sqlQuery){
        // variable that will be returned
        ArrayList<Todo> todoList = new ArrayList<Todo>();

        // sql query to select todos table



        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            // loop through the result set
            while (rs.next()) {
                boolean isDone = false;
                //get isDone int value from database and store it in a variable
                int done = rs.getInt("isDone");
                if (done == 1) isDone = true;


                SimpleDateFormat fDate = new SimpleDateFormat("y-M-d");
                Date date = new Date();

                try {
                    date = fDate.parse(rs.getString("date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Todo todo = new Todo(rs.getString("title"), rs.getString("description"), date, isDone);
                todo.setId(rs.getInt("id"));
                todoList.add(todo);
                //System.out.println(rs.getInt("id") +  "\t" );

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return todoList;
    }

    /**
     * This call a method to query database and return todos by project name
     * @return
     */
    public static ArrayList<Todo> selectAllTaskByProject() {


        // sql query to select todos table

        String sql = "SELECT * FROM todos ORDER BY title";
        ArrayList<Todo> todoList = selectWithSqlQuery(sql);
        return todoList;
    }

    /**
     * This call a method to query database and return todos by project name
     * @return
     */
    public static ArrayList<Todo> selectAllTaskByDate() {


        String sql = "SELECT * FROM todos ORDER BY date";

        ArrayList<Todo> todoList = selectWithSqlQuery(sql);
        return todoList;

    }

    /**
     * This method insert a new todo into database table with following parameter
     * @param title
     * @param des
     * @param date
     * @param isDone
     * @return
     */
    public static String insert(String title, String des, String date, int isDone) {
        String sql = "INSERT INTO todos(title, description, date, isDone) VALUES(?,?,?,?)";
        String msg = "";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, des);
            pstmt.setString(3, date);
            pstmt.setInt(4, isDone);
            pstmt.executeUpdate();
            msg = "Inserted into the database successfully";
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return msg;
    }

    /**
     * This method updates already existing todo record in database
     * @param title
     * @param des
     * @param date
     * @param done
     * @param id
     * @return
     */
    public static String update(String title, String des, String date, int done, int id) {
        String msg = "";

        String sql = "UPDATE todos SET title=?,"
                + "description = ?,"
                + "date = ?,"
                + "isDone = ?"
                + "WHERE id = ?";


        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, des);
            pstmt.setString(3, date);
            pstmt.setInt(4, done);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return msg;
    }
}
