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

    public Todo(String title, String todoDescription, Date taskDate) {
        this.title = title;
        this.todoDescription = todoDescription;
        this.dateCreated = Calendar.getInstance().getTime();
        this.isDone = false;
        this.taskDate = taskDate;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return dateCreated;
    }

    @Override
    public String toString() {
        String status = "Pending";
        if(isDone)
            status = "Completed";

        String pattern = "y-M-d";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(taskDate);

        String str = title+" "+date+" "+status;
        return str;
    }
    public String writeToFile() {
        String status = "Pending";
        if(isDone)
            status = "Completed";

        String pattern = "y-M-d";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(taskDate);

        String str = title+":"+todoDescription+":"+date+":"+status;
        return str;
    }


}
