package com.emmanuel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlitConnect {
    private static Connection con;
    private static boolean hasData = false;
        /**
    Connect to
    a todo
    database
     */

    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "sqlite/Todo.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     */

}
