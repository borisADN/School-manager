package org.example.school_manager.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conx = DriverManager.getConnection("jdbc:mysql://localhost/school_manager", "root", "");
            return conx;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

