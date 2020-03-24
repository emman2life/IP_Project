package com.emmanuel;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Todo {
    private String title;
    private String todoDescription;
    private Date dateCreated;
    private Date taskDate;
    private Boolean isDone;
    private int id;

    /**
     * Todo class constructor
     *
     * @param title
     * @param todoDescription
     * @param taskDate
     * @param isDone
     */
    public Todo(String title, String todoDescription, Date taskDate, boolean isDone) {
        this.title = title;
        this.todoDescription = todoDescription;
        this.dateCreated = Calendar.getInstance().getTime();
        this.isDone = isDone;
        this.taskDate = taskDate;
    }
    //get isDone
    public Boolean getDone() {
        return isDone;
    }
    //set isDone
    public void setDone(Boolean done) {
        isDone = done;
    }
    //get todoDescription
    public String getTodoDescription() {
        return todoDescription;
    }
    //set todoDescription
    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }
    //get title
    public String getTitle() {
        return title;
    }
    //set title
    public void setTitle(String title) {
        this.title = title;
    }
    //get dateCreated
    public Date getDate() {
        return dateCreated;
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
        if (isDone)
            status = "Completed";
        //this convert date object to string
        String date = dateToString();

        String strTask = title + " " + date + " " + status;
        return strTask;
    }

    /**
     * This is the method that helps us convert date object to string
     *
     * @return
     */
    public String dateToString() {

        String pattern = "y-M-d";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(taskDate);
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
}
