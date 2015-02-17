package com.example.app.model;

public class Wine {

    private int wineID;
    private String name;
    private int yearMade;
    private String type;
    private double tempurature;
    private String description;

    public Wine(String n, int y, String ty, double t, String d) {
        this(-1, n, y, ty, t, d);
    }

    public Wine(int id, String n, int y, String ty, double t, String d) {
        this.wineID = id;
        this.name = n;
        this.yearMade = y;
        this.type = ty;
        this.tempurature = t;
        this.description = d;
        
    }

    public int getWineID(){
        return wineID;
    }
    
    public void setWineID(int wineID){
        this.wineID = wineID;
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



}
