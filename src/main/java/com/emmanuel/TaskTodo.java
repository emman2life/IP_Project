package com.emmanuel;

import java.text.SimpleDateFormat;
import java.util.*;

public class TaskTodo {
    private String title;
    private String project;

    private Date dueDate;
    private Boolean status;
    private int id;

    /**
     * Todo class constructor
     *
     * @param title
     * @param project
     * @param dueDate
     * @param status
     */
    public TaskTodo(String title, String project, Date dueDate, boolean status) {
        this.title = title;
        this.project = project;

        this.status = status;
        this.dueDate = dueDate;
    }

    /**
     * This method is format output of each task the way it will look when display
     *
     * @return
     */
    @Override
    public String toString() {
        // status boolean value in the class
        //here is converted text Pending and Completed for readability
        String status = "Pending";
        if (this.status)
            status = "Completed";
        //this convert date object to string
        String date = dateToString();

        String strTask = "Project name: "+project+","+" Task: "+title + " " + date + " " + status;
        return strTask;
    }

    /**
     * This is the method that helps us convert date object to string
     *
     * @return
     */
    private String dateToString() {

        String pattern = "y-M-d";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(dueDate);
        return date;
    }

    //get id
    public int getId() {
        return id;
    }
    //set id
    public void setId(int id) {
        this.id = id;
    }
    public void markedAsDone(TaskTodo todo){

    }
}
