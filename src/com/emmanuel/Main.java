package com.emmanuel;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Todo todo = new Todo("Renew my passport","Call the embassy an book a time for passport" );
        String msg =TodoHandler.addTodo(todo.toString());
        System.out.println(msg);
    }


}
