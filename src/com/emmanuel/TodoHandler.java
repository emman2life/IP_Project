package com.emmanuel;

import jdk.dynalink.beans.StaticClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TodoHandler {
    public static String addTodo(String todo)
    {
        String strMsg = "";


        try(FileWriter fw = new FileWriter("myTodo.txt", true);
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

        return null;
    }
}
