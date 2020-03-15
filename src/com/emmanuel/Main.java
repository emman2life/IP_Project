package com.emmanuel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.emmanuel.Create.createNewDatabase;
import static com.emmanuel.CreateTable.createNewTable;
import static com.emmanuel.SqlitConnect.connect;

public class Main {

    public static void main(String[] args) {
        //connect();
        //createNewDatabase("TodoList.db");
        //createNewTable();
        //InsertRecord app = new InsertRecord();
// insert three new rows
        //app.insert("Call Amar", "Call Amar to tell him about ", "2020-3-18",0);
       //app.insert("Help Patrick", "With the program ", "2020-3-16",0);
        //app.insert("Write C# program", "Identifying the most frequent used word in a text ", "2020-3-16",0);
       //SelectRecords app = new SelectRecords();
        //selectAll();


        displayMenu();


    }



    public static void addNewTask() {
        String pattern = "YYYY-dd-MM";
        Scanner in = new Scanner(System.in);
        System.out.println("Enter title");
        String title = in.nextLine();

        System.out.println("Enter task description");
        String description = in.nextLine();

        System.out.println("Enter date in this format yyyy-MM-DD");
        String dateStr = in.nextLine();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("y-M-d");
        boolean done = false;



        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String msg = TodoHandler.insert(title, description, dateStr, 0);

        System.out.println(msg);
    }
    public static void editTask() {
        String pattern = "YYYY-dd-MM";
        Scanner in = new Scanner(System.in);
        System.out.println("Enter title");
        String title = in.nextLine();

        System.out.println("Enter task description");
        String description = in.nextLine();

        System.out.println("Enter date in this format yyyy-MM-DD");
        String dateStr = in.nextLine();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("y-M-d");
        boolean done = false;



        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String msg = TodoHandler.update(title, description, dateStr, 0, 2);

        System.out.println(msg);
    }

    public static void displayMenu() {
        ArrayList<Todo> allTask = TodoHandler.selectAllTask();
        int totalTasks = allTask.size();
        Scanner in = new Scanner(System.in);
        System.out.println(">> Welcome to ToDoLy\n" +
                ">> You have "+totalTasks+ " tasks todo and Y tasks are done!\n" +
                ">> Pick an option:\n" +
                ">> (1) Show Task List (by date or project)\n" +
                ">> (2) Add New Task\n" +
                ">> (3) Edit Task (update, mark as done, remove)\n" +
                ">> (4) Save and Quit\n" +
                ">> \n" +
                "");
        try {
            int opt = in.nextInt();

        switch (opt) {
            case 1:
                ArrayList<Todo> todoList = TodoHandler.selectAllTaskByDate();
                for(int i=0;i<todoList.size();i++)
                {
                    Todo todo = todoList.get(i);
                    System.out.println("ID"+(i+1)+" "+ todo);
                }

                displayMenu();
                break;
            case 2:
                addNewTask();
                displayMenu();
                break;
            case 3:
                editTask();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.err.println("You choice was invalid pls pick any of the options below and try again");
                displayMenu();
                break;
        }
        }
        catch(InputMismatchException e){
            System.err.println("Only an integer value is accepted thanks");
            displayMenu();
        }
    }

}
