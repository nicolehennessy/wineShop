package com.example.app.model;

public class Wine {

    private int id;
    private String name;
    private int yearMade;
    private String type;
    private double tempurature;
    private String description;
    private int wineryId;

    public Wine(String n, int y, String ty, double t, String d, int wId) {
        this(-1, n, y, ty, t, d, wId);
    }

    public Wine(int id, String n, int y, String ty, double t, String d, int wId) {
        this.id = id;
        this.name = n;
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
