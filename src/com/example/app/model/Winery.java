package com.example.app.model;

public class Winery {
    
    private int id;
    private String wineryName;
    private String address;
    private String contactName;
    private String phoneNo;
    private String email;
    private String webAddress;
    //private int wineId;
    
    public Winery(int id, String wn, String a, String cn,String pn, String e,String wa ) {
        this.id = id;
        this.wineryName = wn;
        this.address = a;
        this.contactName = cn;
        this.phoneNo = pn;
        this.email = e;
        this.webAddress = wa;
        
    }
    
    public Winery(String wn, String a,String cn, String pn, String e, String wa){
        this(-1,wn,a,cn,pn,e,wa);
    }

    public int getWineryId(){
        return id;
    }
    public String getWineryName(){
        return wineryName;
    }
    public String getAddress(){
        return address;
    }
    public String getContactName(){
        return contactName;
    }
    public String getPhoneNo(){
        return phoneNo;
    }
    public String getEmail(){
        return email;
    }
    public String getWebAddress(){
        return webAddress;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setWineryName(String wineryName){
        this.wineryName=wineryName;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setContactName(String contactName){
        this.contactName=contactName;
    }
    public void setPhoneNo(String phoneNo){
        this.phoneNo=phoneNo;
    }    
    public void setEmail(String email){
        this.email=email;
    }
    public void setWebAddress(String webAddress){
        this.webAddress=webAddress;
    }

    /*public int getWineId() {
        return wineId;
    }

    public void setWineId(int wineId) {
        this.wineId = wineId;
    }*/
}
