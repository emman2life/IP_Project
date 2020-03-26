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
        //CreateTable.createNewTable();
        //InsertRecord app = new InsertRecord();

        displayMenu();


    }

    public static void addNewTask() {
        String pattern = "YYYY-dd-MM";
        Scanner in = new Scanner(System.in);
        System.out.println("Enter title");
        String title = in.nextLine();

        System.out.println("Enter Project");
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
        Scanner ind = new Scanner(System.in);

        System.out.println("#########################################################");
        System.out.println("Enter the last digits of the project ID you wish to edit");
        int id = ind.nextInt();


        System.out.println("Enter title");
        String title = in.nextLine();

        System.out.println("Enter project");
        String description = in.nextLine();

        System.out.println("Enter date in this format yyyy-MM-DD");
        String dateStr = in.nextLine();
        System.out.println("Enter 0 if pending or 1 if completed");
        int done = in.nextInt();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("y-M-d");



        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String msg = TodoHandler.update(title, description, dateStr, done, id);

        System.out.println(msg);
    }

    public static void displayMenu() {
        ArrayList<Todo> allTask = TodoHandler.selectAllTaskByProject();
        int totalTasks = allTask.size();

        int completed = TodoHandler.completedTask();
        Scanner in = new Scanner(System.in);
        System.out.println(">>***************************************\n" +
                ">> Welcome to ToDoLy\n" +
                ">> You have "+totalTasks+ " tasks todo and "+ completed+" tasks are done!\n" +
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
                System.out.println(">> Pick an option:\n" +
                        ">> (1) Show Task List by project)\n" +
                        ">> (2)Show Task List by date\n" );
                int opt2 = in.nextInt();
                switch (opt2){
                    case 1:
                        listTaskByProject();
                        break;
                    case 2:
                        listTaskByDate();
                        break;

                    default:
                        System.err.println("You choice was invalid pls pick any of the options below and try again");
                        displayMenu();
                        break;
                }



                displayMenu();
                break;
            case 2:
                addNewTask();
                displayMenu();
                break;
            case 3:
                listTaskByProject();
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
    private static void listTaskByProject(){
        ArrayList<Todo> todoList = TodoHandler.selectAllTaskByProject();
        for(int i=0;i<todoList.size();i++)
        {
            Todo todo = todoList.get(i);
            System.out.println("ID"+(todo.getId())+" "+ todo);
        }
    }
    private static void listTaskByDate(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter date in this format yyyy-MM-DD");
        String dateStr = in.nextLine();
        ArrayList<Todo> todoList = TodoHandler.selectTaskByDate(dateStr);

        for(int i=0;i<todoList.size();i++)
        {
            Todo todo = todoList.get(i);
            System.out.println("ID"+(todo.getId())+" "+ todo);
        }
    }

}
