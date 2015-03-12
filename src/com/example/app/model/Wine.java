package com.example.app.model;

public class Wine {

    private int id;
    private String wineryName;
    private int yearMade;
    private String type;
    private double tempurature;
    private String description;
    private int wineryId;

    public Wine(String wn, int y, String ty, double t, String d, int wId) {
        this(-1, wn, y, ty, t, d, wId);
    }

    public Wine(int id, String wn, int y, String ty, double t, String d, int wId) {
        this.id = id;
        this.wineryName = wn;
        this.yearMade = y;
        this.type = ty;
        this.tempurature = t;
        this.description = d;
        this.wineryId = wId;
    }

    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getWineryName() {
        return wineryName;
    }

    public void setWineryName(String wineryName) {
        this.wineryName = wineryName;
    }

    /**
     * @return the year
     */
    public int getYearMade() {
        return yearMade;
    }

    /**
     * @param yearMade the year to set
     */
    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the temp
     */
    public double getTempurature() {
        return tempurature;
    }

    /**
     * @param tempurature the temp to set
     */
    public void setTempurature(double tempurature) {
        this.tempurature = tempurature;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public int getWineryId() {
        return wineryId;
    }

    public void setWineryId(int wineryId) {
        this.wineryId = wineryId;
    }



}
