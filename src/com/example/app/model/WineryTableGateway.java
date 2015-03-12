package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WineryTableGateway {
    
    private Connection mConnection;
    
    private static final String TABLE_NAME = "winery";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WINERYNAME = "wineryName";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CONTACTNAME = "contactName";
    private static final String COLUMN_PHONENO = "phoneNo";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_WEBADDRESS = "webAddress";
    
    public WineryTableGateway(Connection connection){
        mConnection = connection;
    }
    public int insertWinery(String wn,String a, String cn, String pn, String e, String wa) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        int id = -1;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_WINERYNAME + ", " +
                COLUMN_ADDRESS + ", " +
                COLUMN_CONTACTNAME + ", " +
                COLUMN_PHONENO + ", " +
                COLUMN_EMAIL + ", " +
                COLUMN_WEBADDRESS +
                ") VALUES (?, ?, ? ,? ,?,?)";

        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, wn);
        stmt.setString(2, a);
        stmt.setString(3, cn);
        stmt.setString(4, pn);
        stmt.setString(5, e);
        stmt.setString(6, wa);
        

        // execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            id = keys.getInt(1);
        }

        // return the id assigned to the row in the database
        return id;
    }
    
    public boolean deleteWinery(int id) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // the required SQL DELETE statement with place holders for the id of the row to be remove from the database
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";

        // create a PreparedStatement object to execute the query and insert the id into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);

        // execute the query
        numRowsAffected = stmt.executeUpdate();

        // return the true if one and only one row was deleted from the database
        return (numRowsAffected == 1);
    }
    
    public List<Winery> getWinerys() throws SQLException {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Winery> winerys;         // the java.util.List containing the Winery objects created for each row
                                        // in the result of the query the id of a winery

        String wineryName, address, contactName, phone, email, webAddress;
        int id;
        Winery wy;                   // a Winery object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        // iterate through the result set, extracting the data from each row
        // and storing it in a Winery object, which is inserted into an initially
        // empty ArrayList
        winerys = new ArrayList<Winery>();
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            wineryName = rs.getString(COLUMN_WINERYNAME);
            address = rs.getString(COLUMN_ADDRESS);
            contactName = rs.getString(COLUMN_CONTACTNAME);
            phone = rs.getString(COLUMN_PHONENO);
            email = rs.getString(COLUMN_EMAIL);
            webAddress = rs.getString(COLUMN_WEBADDRESS);
            

            wy = new Winery(id, wineryName, address, contactName, phone, email, webAddress);
            winerys.add(wy);
        }

        // return the list of Winery objects retrieved
        return winerys;
    }
    
    boolean updateWinery(Winery wy) throws SQLException{
        String query;               //the query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        
        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET " +
              COLUMN_WINERYNAME + " = ?, " +  
              COLUMN_ADDRESS + " = ?, " + 
              COLUMN_CONTACTNAME + " = ?, " + 
              COLUMN_PHONENO + " = ?, " + 
              COLUMN_EMAIL + " = ?, " + 
              COLUMN_WEBADDRESS + " = ? " + 
              " WHERE " + COLUMN_ID + " = ?";
        
        // create a PreparedStatement object to execute the query and insert the new values into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, wy.getWineryName());
        stmt.setString(2,wy.getAddress());
        stmt.setString(3, wy.getContactName());
        stmt.setString(4, wy.getPhoneNo());
        stmt.setString(5, wy.getEmail());
        stmt.setString(6, wy.getWebAddress());
        stmt.setInt(7, wy.getWineryId());
        
        //execute query
        numRowsAffected = stmt.executeUpdate();
        
        //return true if only one row is updated
        return (numRowsAffected == 1);
    }
}
