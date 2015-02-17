package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WineTableGateway {
    private static final String TABLE_NAME = "wine";
    private static final String COLUMN_WINEID = "wineID";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_YEARMADE = "yearMade";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_TEMPURATURE = "tempurature";
    private static final String COLUMN_DESCRIPTION = "description";

    private Connection mConnection;

    public WineTableGateway(Connection connection) {
        mConnection = connection;
    }

    public int insertWine(String n, int y, String ty, double t,  String d) throws SQLException {
        String query;       // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        int wineID = -1;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_YEARMADE + ", " +
                COLUMN_TYPE + ", " +
                COLUMN_TEMPURATURE + ", " +
                COLUMN_DESCRIPTION +
                ") VALUES (?, ?, ?, ?, ?)";

        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, n);
        stmt.setInt(2, y);
        stmt.setString(3, ty);
        stmt.setDouble(4, t);        
        stmt.setString(5, d);

        //  execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row and create a Wine object to return
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            wineID = keys.getInt(1);
        }

        // return the Wine assigned by the database
        return wineID;
    }
    
    public boolean deleteWine(int wineID)throws SQLException{
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_WINEID + " = ? ";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1,wineID);
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
    
    public List<Wine> getWines() throws SQLException {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Wine> wines;   // the java.util.List containing the Wine objects created for each row
                                        // in the result of the query the id of a programmer

        String  name, type, description;
        int wineID, yearMade; 
        double tempurature;

        Wine p;                   // a Wine object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        // iterate through the result set, extracting the data from each row
        // and storing it in a Wine object, which is inserted into an initially
        // empty ArrayList
        wines = new ArrayList<Wine>();
        while (rs.next()) {
            wineID = rs.getInt(COLUMN_WINEID);
            name = rs.getString(COLUMN_NAME);
            yearMade = rs.getInt(COLUMN_YEARMADE);
            type = rs.getString(COLUMN_TYPE);
            tempurature = rs.getDouble(COLUMN_TEMPURATURE);
            description = rs.getString(COLUMN_DESCRIPTION);

            p = new Wine(wineID, name, yearMade,  type, tempurature, description);
            wines.add(p);
        }

        // return the list of Wine objects retrieved
        return wines;
    }

    boolean updateWine(Wine p) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_NAME + " = ?,  " +
                COLUMN_YEARMADE + " = ?, " +
                COLUMN_TYPE + " = ?, " +
                COLUMN_TEMPURATURE + " = ?, " +
                COLUMN_DESCRIPTION + " = ?, " +
                "WHERE" + COLUMN_WINEID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, p.getName());
        stmt.setInt(2, p.getYearMade());
        stmt.setString(3, p.getType());
        stmt.setDouble(4, p.getTempurature());
        stmt.setString(5, p.getDescription());
        
        numRowsAffected = stmt.executeUpdate();
        
        return(numRowsAffected == 1);
    }

}
