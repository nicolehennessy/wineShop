package com.example.app.model;

public class Winery {
    
    private int id;
    private String wineryName;
    private String address;
    private String contactName;
    private int phoneNo;
    private String email;
    private String webAddress;
    
    public Winery(int id, String wn, String a, String cn,int pn, String e,String wa ) {
        this.id = id;
        this.wineryName = wn;
        this.address = a;
        this.contactName = cn;
        this.phoneNo = pn;
        this.email = e;
        this.webAddress = wa;
        
    }
    
    public Winery(String wn, String a,String cn,String e,String wa){
        this(-1,wn,a,cn,000000000,e,wa);
    }
    
    public int getId(){
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
    public int getPhoneNo(){
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
    public void setPhoneNo(int phoneNo){
        this.phoneNo=phoneNo;
    }    
    public void setEmail(String email){
        this.email=email;
    }
    public void setWebAddress(String webAddress){
        this.webAddress=webAddress;
    }
}
