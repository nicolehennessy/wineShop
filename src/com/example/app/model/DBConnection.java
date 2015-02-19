package com.example.app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection sConnection;
    // This class represents my database
    //This is a new comment
    public static Connection getInstance() throws ClassNotFoundException, SQLException {
        String host, db, user, password;
        
        //This is a new comment
        host = "daneel";
        db = "N00131965";
        user = "N00131965";
        password = "N00131965";
        
        if (sConnection == null || sConnection.isClosed()) {
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver");
            sConnection = DriverManager.getConnection(url, user, password);
        }

        return sConnection;
    }
}
