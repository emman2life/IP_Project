package com.emmanuel;
import java.io.*;
import java.util.*;
public class Todo {
    private String title;
    private String todoDescription;
    private Date date;
    private Boolean isDone;

    public Todo(String title, String todoDescription) {
        this.title = title;
        this.todoDescription = todoDescription;
        this.date = Calendar.getInstance().getTime();
        this.isDone = false;
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
        return date;
    }

    @Override
    public String toString() {
        String status = "Pending";
        if(isDone)
            status = "Completed";
        String str = title+":"+todoDescription+":"+date.toString();
        return str;
    }


}
