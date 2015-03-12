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
    private static final String COLUMN_WINEID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_YEARMADE = "yearMade";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_TEMPURATURE = "tempurature";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_WINERY_ID = "winery_id";

    private Connection mConnection;

    public WineTableGateway(Connection connection) {
        mConnection = connection;
    }

    public int insertWine(String n, int y, String ty, double t,  String d, int wId) throws SQLException {
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
                COLUMN_DESCRIPTION + ", " +
                COLUMN_WINERY_ID +
                ") VALUES (?, ?, ?, ?, ?, ?)";

        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, n);
        stmt.setInt(2, y);
        stmt.setString(3, ty);
        stmt.setDouble(4, t);        
        stmt.setString(5, d);
        if (wId == -1) {
            stmt.setNull(6, java.sql.Types.INTEGER);
        }
        else {
            stmt.setInt(6, wId);
        }

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
        int wineID, yearMade, wineryId; 
        double tempurature;

        Wine w;                   // a Wine object created from a row in the result of the query

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
            wineryId = rs.getInt(COLUMN_WINERY_ID);
            if (rs.wasNull()) {
                wineryId = -1;
            }

            w = new Wine(wineID, name, yearMade,  type, tempurature, description, wineryId);
            wines.add(w);
        }

        // return the list of Wine objects retrieved
        return wines;
    }

    boolean updateWine(Wine w) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_NAME + " = ?,  " +
                COLUMN_YEARMADE + " = ?, " +
                COLUMN_TYPE + " = ?, " +
                COLUMN_TEMPURATURE + " = ?, " +
                COLUMN_DESCRIPTION + " = ?, " +
                COLUMN_WINERY_ID + " = ? " +
                " WHERE " + COLUMN_WINEID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, w.getWineryName());
        stmt.setInt(2, w.getYearMade());
        stmt.setString(3, w.getType());
        stmt.setDouble(4, w.getTempurature());
        stmt.setString(5, w.getDescription());
        if (w.getWineryId() == -1) {
            stmt.setNull(6, java.sql.Types.INTEGER);
        }
        else {
            stmt.setInt(6, w.getWineryId());
        }
        stmt.setInt(7, w.getId());
        
        numRowsAffected = stmt.executeUpdate();
        
        return(numRowsAffected == 1);
    }

}
