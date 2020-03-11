package com.emmanuel;

import jdk.dynalink.beans.StaticClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TodoHandler {
    static final private String FILENAME = "TodoList.txt";

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
            Todo todo = new Todo(strTitle,strDescription, date);
            if(status.equals("Completed"))todo.setDone(true);
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
}
