package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static  Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    List<Wine> wines;
    List<Winery> winerys;
    WineTableGateway wineGateway;
    WineryTableGateway wineryGateway;

    private Model() {

        try {
            Connection conn = DBConnection.getInstance();
            this.wineGateway = new WineTableGateway(conn);
            this.wineryGateway = new WineryTableGateway(conn);
            
            this.wines = this.wineGateway.getWines();
            this.winerys = this.wineryGateway.getWinerys();
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

    public boolean addWine(Wine w) {
        boolean result = false;
        try{
            int id = this.wineGateway.insertWine(
                    w.getWineryName(),w.getYearMade(),w.getType(),w.getTempurature(),w.getDescription(),w.getWineryId());
            if (id != -1){
                w.setId(id);
                this.wines.add(w);
                result = true;
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE,null,ex);
        }
        return result;
    }
        
    public boolean removeWine(Wine w) {
        boolean removed = false;
        
        try{
            removed = this.wineGateway.deleteWine(w.getId());
            if (removed){
                removed = this.wines.remove(w);
            } 
        }

        catch(SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE,null,ex);
        }
        return removed;
    }

    public Wine findWineById(int id) {
        Wine w = null;
        int i = 0;
        boolean found = false;
        while (i < this.wines.size() && !found) {
            w = this.wines.get(i);
            if (w.getId() == id) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            w = null;
        }
        return w;
    }

    boolean updateWine(Wine w) {
        boolean updated = false;
        
        try{
            updated = this.wineGateway.updateWine(w);
        }
        catch (SQLException ex){
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }
    public boolean addWinery(Winery wy) {
        boolean result = false;
        try {
            int id = this.wineryGateway.insertWinery(wy.getWineryName(), wy.getAddress(), wy.getContactName(), wy.getPhoneNo(), wy.getEmail(), wy.getWebAddress());
            if (id != -1) {
                wy.setId(id);
                this.winerys.add(wy);
                result = true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean removeWinery(Winery wy) {
        boolean removed = false;

        try {
            removed = this.wineryGateway.deleteWinery(wy.getWineryId());
            if (removed) {
                removed = this.winerys.remove(wy);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return removed;
    }

    public List<Winery> getWinerys() {
        return this.winerys;
    }

    Winery findWineryById(int id) {
        Winery wy = null;
        int i = 0;
        boolean found = false;
        while (i < this.winerys.size() && !found) {
            wy = this.winerys.get(i);
            if (wy.getWineryId() == id) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            wy = null;
        }
        return wy;
    }

    boolean updateWinery(Winery wy) {
        boolean updated = false;

        try {
            updated = this.wineryGateway.updateWinery(wy);
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return updated;
    }
}
