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
    static final private String FILENAME = "TodoList.txt";
    private static Connection connect() {
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

    public static String addTodoToFile(String todo)
    {
        String strMsg = "";


        try(FileWriter fw = new FileWriter(FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(todo);
            strMsg = "New task add successfully";
        } catch (IOException e) {
            strMsg = "Unable to add task";
        }

        return strMsg;
    }


    public static ArrayList<Todo> getTodoList(){
        ArrayList<String> stringArrayList = readFromFile();
        ArrayList<Todo> todoList = new ArrayList<Todo>();
        for (String str: stringArrayList){
            String[] strArr = str.split(":");
            String strTitle = strArr[0];
            String strDescription = strArr[1];
            String strDate = strArr[2];
            String status = strArr[3];


            SimpleDateFormat fDate = new SimpleDateFormat("y-M-d");
           Date date = new Date();

            try {
                date = fDate.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            boolean done = false;
            if(status.equals("Completed"))done=true;
            Todo todo = new Todo(strTitle,strDescription, date, done);

            todoList.add(todo);
        }

        return todoList;
    }

    public static ArrayList<String> readFromFile(){
        ArrayList<String> stringArrayList = new ArrayList<String>();
        try {
            File myObj = new File(FILENAME);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if(line.equals(""))continue;
               stringArrayList.add(line);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return stringArrayList;

    }

    public static void updateTodo(Todo todo) {

    }
    public static int completedTask(){

int count=0;


        String sql = "SELECT * FROM todos ORDER BY title";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {

                int done = rs.getInt("isDone");
                count += done==1?1:0;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    public static ArrayList<Todo> selectAllTaskByProject(){
        ArrayList<Todo> todoList = new ArrayList<Todo>();



        String sql = "SELECT * FROM todos ORDER BY title";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                boolean isDone = false;
                int done = rs.getInt("isDone");
                if(done == 1)isDone=true;


                SimpleDateFormat fDate = new SimpleDateFormat("y-M-d");
                Date date = new Date();

                try {
                    date = fDate.parse(rs.getString("date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Todo todo = new Todo(rs.getString("title"),rs.getString("description"), date, isDone);
                todo.setId(rs.getInt("id"));
                todoList.add(todo);
                //System.out.println(rs.getInt("id") +  "\t" );

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return todoList;
    }


    public static ArrayList<Todo> selectAllTaskByDate(){
        ArrayList<Todo> todoList = new ArrayList<Todo>();



        String sql = "SELECT * FROM todos ORDER BY date";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                boolean isDone = false;
                int done = rs.getInt("isDone");
                if(done == 1)isDone=true;


                SimpleDateFormat fDate = new SimpleDateFormat("y-M-d");
                Date date = new Date();

                try {
                    date = fDate.parse(rs.getString("date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Todo todo = new Todo(rs.getString("title"),rs.getString("description"), date, isDone);
                todo.setId(rs.getInt("id"));
                todoList.add(todo);
                //System.out.println(rs.getInt("id") +  "\t" );

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return todoList;
    }
    public static String insert(String title, String des, String date, int isDone) {
        String sql = "INSERT INTO todos(title, description, date, isDone) VALUES(?,?,?,?)";
        String msg = "";
        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, des);
            pstmt.setString(3, date);
            pstmt.setInt(4, isDone);
            pstmt.executeUpdate();
            msg = "Inserted into the database successfully";
        } catch (SQLException e) {
            msg=e.getMessage();
        }
        return msg;
    }
    public static String update(String title, String des, String date, int done,  int id) {
        String msg = "";

        String sql = "UPDATE todos SET title=?,"
                + "description = ?,"
                + "date = ?,"
                + "isDone = ?"
                + "WHERE id = ?";


        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, des);
            pstmt.setString(3, date);
            pstmt.setInt(4, done);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            msg=e.getMessage();
        }
        return msg;
    }
}
