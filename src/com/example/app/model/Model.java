package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private List<Wine> wines;
    private WineTableGateway gateway;

    private Model() {

        try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new WineTableGateway(conn);
            
            this.wines = this.gateway.getWines();
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Wine> getWines() {
        return new ArrayList<Wine>(this.wines);
    }

    public boolean addWine(Wine p) {
        boolean result = false;
        try{
            int wineID = this.gateway.insertWine(p.getName(),p.getYearMade(),p.getType(),p.getTempurature(),p.getDescription());
            if (wineID != -1){
                p.setWineID(wineID);
                this.wines.add(p);
                result = true;
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE,null,ex);
        }
        return result;
    }
        
    public boolean removeWine(Wine p) {
        boolean removed = false;
        
        try{
            removed = this.gateway.deleteWine(p.getWineID());
            if (removed){
                removed = this.wines.remove(p);
            } 
        }

        catch(SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE,null,ex);
        }
        return removed;
    }

    public Wine findWineByWineID(int wineID) {
        Wine p = null;
        int i = 0;
        boolean found = false;
        while (i < this.wines.size() && !found) {
            p = this.wines.get(i);
            if (p.getWineID() == wineID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            p = null;
        }
        return p;
    }

    boolean updateWine(Wine p) {
        boolean updated = false;
        
        try{
            updated = this.gateway.updateWine(p);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }
}
