package com.emmanuel;

//import jdk.dynalink.beans.StaticClass;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
     * The task status isDone  is save as int  0 and 1 in the database
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
    public static ArrayList<TaskTodo> selectTaskByDate(String date) {


        // sql query to select todos table
        String sql = "SELECT * FROM todos WHERE date LIKE '%"+date+"%'";


        ArrayList<TaskTodo> todoList = selectWithSqlQuery(sql);
        return todoList;
    }

    /**
     * this method help query database and take sql query as input and return list of todos if successful
     * @param sqlQuery sql query
     * @return
     */
    private  static ArrayList<TaskTodo> selectWithSqlQuery(String sqlQuery){
        // variable that will be returned
        ArrayList<TaskTodo> todoList = new ArrayList<TaskTodo>();

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

                TaskTodo todo = new TaskTodo(rs.getString("title"), rs.getString("project"), date, isDone);
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
    public static ArrayList<TaskTodo> selectAllTaskByProject() {


        // sql query to select todos table

        String sql = "SELECT * FROM todos ORDER BY project";
        ArrayList<TaskTodo> todoList = selectWithSqlQuery(sql);
        return todoList;
    }

    /**
     * This call a method to query database and return todos by project name
     * @return
     */
    public static ArrayList<TaskTodo> selectAllTaskByDate() {


        String sql = "SELECT * FROM todos ORDER BY date";

        ArrayList<TaskTodo> todoList = selectWithSqlQuery(sql);
        return todoList;

    }

    /**
     * This method insert a new todo into database table with following parameter
     * @param title
     * @param project
     * @param date
     * @param isDone
     * @return
     */
    public static String insert(String title, String project, String date, int isDone) {
        String sql = "INSERT INTO todos(title, project, date, isDone) VALUES(?,?,?,?)";
        String msg = "";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, project);
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
     * @param project
     * @param date
     * @param done
     * @param id
     * @return
     */
    public static String update(String title, String project, String date, int done, int id) {
        String msg = "";

        String sql = "UPDATE todos SET title=?,"
                + "project = ?,"
                + "date = ?,"
                + "isDone = ?"
                + "WHERE id = ?";


        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, project);
            pstmt.setString(3, date);
            pstmt.setInt(4, done);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return msg;
    }
    public static String markAsCompleted(int done, int id) {
        String msg = "";

        String sql = "UPDATE todos SET isDone = ?"
                + "WHERE id= ?";


        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, done);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            msg = "Task successfully Updated";
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return msg;
    }
    public static String removeTask(int id){
        String msg="";
        String sql = "DELETE FROM todos WHERE id = ?";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
             pstmt.executeUpdate();
             msg = "Task successfully deleted";
        } catch (SQLException e) {
            msg = e.getMessage();
        }
        return msg;
    }

}
