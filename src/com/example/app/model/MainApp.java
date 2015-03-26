package com.example.app.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    
    private static final int NAME_ORDER = 1;
    private static final int TEMPURATURE_ORDER = 2;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model;
        int opt = 12;
        do {
            try{
                model  = Model.getInstance();
                System.out.println("1. Create new Wine");
                System.out.println("2. Delete existing Wine");
                System.out.println("3. Edit existing Wine");
                System.out.println("4. View all Wines");
                System.out.println("5. View all Wines Order by Tempurature");
                System.out.println("6. View a single Wine");
                System.out.println();
                System.out.println("7. Create new Winery");
                System.out.println("8. Delete existing Winery");
                System.out.println("9. Edit existing Winery");
                System.out.println("10. View all Winerys");
                System.out.println("11. View a single Winery");
                System.out.println();
                System.out.println("12. Exit");
                System.out.println();

                opt = getInt(keyboard, "Enter option: " , 12);

                System.out.println("You chose option " + opt);

                switch (opt) {
                    case 1: {
                        System.out.println("Creating wine.");
                        createWine(keyboard, model);
                        break;
                    }
                    case 2: {
                        System.out.println("Deleting wine.");
                        deleteWine(keyboard, model);
                        break;
                    }
                    case 3: {
                        System.out.println("Editing wine.");
                        editWine(keyboard, model);
                        break;
                    }
                    case 4: {
                        System.out.println("Viewing wine.");
                        viewWines(model, NAME_ORDER);
                        break;
                    }
                    case 5: {
                        System.out.println("Viewing wine Order by tempurature.");
                        viewWines(model, TEMPURATURE_ORDER);
                        break;
                    }
                     case 6: {
                        System.out.println("Viewing a single wine.");
                        viewWine(keyboard,model);
                        break;
                    }

                    case 7: {
                        System.out.println("Creating winery.");
                        createWinery(keyboard, model);
                        break;
                    }
                    case 8: {
                        System.out.println("Deleting winery.");
                        deleteWinery(keyboard, model);
                        break;
                    }
                    case 9: {
                        System.out.println("Editing winery.");
                        editWinery(keyboard, model);
                        break;
                    }
                    case 10: {
                        System.out.println("Viewing winery.");
                        viewWinerys(model);
                        break;

                    }
                    case 11: {
                        System.out.println("Viewing a single winery.");
                        viewWinery(keyboard, model);
                        break;

                    }
                }
            }
            catch (DataAccessException e){
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();
            }
        } 
        while (opt != 12);
    }

    private static void createWine(Scanner keyboard, Model model) throws DataAccessException {
        Wine w = readWine(keyboard);
        if (model.addWine(w)) {
            System.out.println("Wine added to database.");
        } else {
            System.out.println("Wine NOT added to database.");
        }
        System.out.println();
    }

    private static void deleteWine(Scanner keyboard, Model model) throws DataAccessException{
        int id = getInt(keyboard, "Enter the id of wine you want to delete: " ,-1);
        Wine w;
        
        w = model.findWineById(id);
        if (w != null){
            if (model.removeWine(w)){
                System.out.print("Wine deleted.");
            }
            else{
                System.out.print("Wine NOT deleted.");
            }
        }
        else{
            System.out.print("Wine NOT found.");   
        }
        
    }
    
    private static void viewWine(Scanner keyboard, Model model) throws DataAccessException{
        int id = getInt(keyboard, "Enter the id of wine you want to view: " ,-1);
        Wine w;
        
        w = model.findWineById(id);
        if (w != null){
            System.out.println();
            System.out.println();            
            System.out.println("Name            : " + w.getName());
            System.out.println("YearMade        : " + w.getYearMade());
            System.out.println("Type            : " + w.getType());
            System.out.println("Tempurature     : " + w.getTempurature());
            System.out.println("Description     : " + w.getDescription());
            System.out.println("Winery ID       : " + w.getWineryId());
            System.out.println();
            System.out.println();
        }
        else{
            System.out.print("Wine NOT found.");   
        }
        
    }
    
    private static void editWine(Scanner keyboard, Model model) throws DataAccessException{
        int id = getInt(keyboard, "Enter the name of wine you want to edit: ", -1);
        Wine w;

        
        w = model.findWineById(id);
        if (w != null){
            editWineDetails(keyboard, w);
            if (model.updateWine(w)){
                System.out.print("Wine Updated.");
            }
            else{
                System.out.print("Wine NOT Updated.");
            }
        }
        else{
            System.out.print("Wine NOT found.");   
        }
    }
    
    private static void viewWines(Model model, int order) {
        List<Wine> wines = model.getWines();
        System.out.println();
        if (wines.isEmpty()) {
            System.out.println("There are no wines in the database");
        }
        else {
            if(order == NAME_ORDER){
                Collections.sort(wines);
            }
            else if(order == TEMPURATURE_ORDER){
                Comparator<Wine> cmptr = new WineTempuratureComparator();
                Collections.sort(wines, cmptr);
            }
            
            displayWines(wines, model);
        }
        System.out.println();

    }

    private static Wine readWine(Scanner keyb) 
    {
        String wineryName, type, description;
        int yearMade, wineryId;
        double tempurature;
        String line;

        wineryName = getString(keyb, "Enter name of wine: ");
        yearMade = getInt(keyb, "Enter year of wine: ",0);
        type = getString(keyb, "Enter type of wine: ");
        tempurature = getDouble(keyb, "Enter serving tempurature of wine: ",0);
        description = getString(keyb, "Enter description of wine: ");
        wineryId = getInt(keyb, "Enter winery id where wine was made: ", -1);

        Wine w
                = new Wine(wineryName, yearMade, type,
                        tempurature, description, wineryId);

        return w;
    }

    private static void editWineDetails(Scanner keyboard, Wine w) {
        String wineryName, type, description;
        int yearMade, wineryId;
        double tempurature;
        
        wineryName = getString(keyboard, "Enter name of wine[" + w.getName() + "]: ");
        yearMade = getInt(keyboard, "Enter year of wine[" + w.getYearMade() + "]: " , 0);
        type = getString(keyboard, "Enter type of wine[" + w.getType() + "]: ");
        tempurature = getDouble(keyboard, "Enter serving tempurature of wine[" + w.getTempurature() + "]: ", 0);
        description = getString(keyboard, "Enter description of wine[" + w.getDescription() + "]: ");
        wineryId = getInt(keyboard, "Enter winery id where the wine was made [" + w.getWineryId()+ "]: " ,-1);
        
        if (wineryName.length() !=0){
            w.setName(wineryName);
        }
        if (yearMade !=w.getYearMade()){
           w.setYearMade(yearMade);
        }
        if (type.length() !=0){
            w.setType(type);
        }
        if (tempurature !=w.getTempurature()){
            w.setTempurature(tempurature);
        }
        if (description.length() !=0){
            w.setDescription(description);
        }
        if (wineryId !=w.getWineryId()){
            w.setWineryId(wineryId);
        }
        
    }
    
    private static void displayWines(List<Wine> wines, Model model){
        System.out.printf("%10s %20s %20s %15s %22s %20s %20s\n", 
                "WineID", "Name", "YearMade", "Type", "Temperature", "Description", "Winery");
        for (Wine wi : wines) {
            Winery wy = model.findWineryById(wi.getWineryId());
            System.out.printf("%10s %20s %20d %15s %22f %20s %20s\n",
                    wi.getId(),
                    wi.getName(),
                    wi.getYearMade(),
                    wi.getType(),
                    wi.getTempurature(),
                    wi.getDescription(),
                    (wy !=null) ? wy.getWineryName() :"NULL" );
        }
    }
    
    private static void createWinery(Scanner keyboard, Model model) throws DataAccessException {
        Winery wy = readWinery(keyboard);
        if (model.addWinery(wy)) {
            System.out.println("Winery added to database.");
        } else {
            System.out.println("Winery NOT added to database.");
        }
        System.out.println();
    }

    private static void deleteWinery(Scanner keyboard, Model model) throws DataAccessException{
        int id = getInt(keyboard, "Enter the id of winery you want to delete: ", -1);
        Winery wy;
        
        wy = model.findWineryById(id);
        if (wy != null){
            if (model.removeWinery(wy)){
                System.out.print("Winery deleted.");
            }
            else{
                System.out.print("Winery NOT deleted.");
            }
        }
        else{
            System.out.print("Winery NOT found.");   
        }
        
    }
    
    private static void editWinery(Scanner keyboard, Model model) throws DataAccessException{
        int id = getInt(keyboard, "Enter the id of winery you want to edit: ",-1);
        Winery wy;
        
        wy = model.findWineryById(id);
        if (wy != null){
            editWineryDetails(keyboard, wy);
            if (model.updateWinery(wy)){
                System.out.print("Winery Updated.");
            }
            else{
                System.out.print("Winery NOT Updated.");
            }
        }
        else{
            System.out.print("Winery NOT found.");   
        }
    }
    
    private static void viewWinerys(Model model) {
        List<Winery> winerys = model.getWinerys();

        System.out.println();
        if (winerys.isEmpty()) {
            System.out.println("There are no winerys in the database");
        } else {
            System.out.printf("%10s %20s %20s %20s %15s %60s %60s\n", "WineryId", "Winery Name", "Address", "Contact Name", "Phone No", "Email","Web Address");
            for (Winery wy : winerys) {
                System.out.printf("%10s %20s %20s %20s %15s %60s %60s\n",
                        wy.getWineryId(),
                        wy.getWineryName(),
                        wy.getAddress(),
                        wy.getContactName(),
                        wy.getPhoneNo(),
                        wy.getEmail(),
                        wy.getWebAddress()
                );
            }
        }
        System.out.println();

    }
    
    private static void viewWinery(Scanner keyboard, Model model) throws DataAccessException{
        int id = getInt(keyboard, "Enter the id of winery you want to view: " ,-1);
        Winery wy;
        
        wy = model.findWineryById(id);
        if (wy != null){
            System.out.println();
            System.out.println();            
            System.out.println("Name                    : " + wy.getWineryName());
            System.out.println("Address                 : " + wy.getAddress());
            System.out.println("Contact Name            : " + wy.getContactName());
            System.out.println("Phone No                : " + wy.getPhoneNo());
            System.out.println("Email                   : " + wy.getEmail());
            System.out.println("Web Address             : " + wy.getWebAddress());
            System.out.println();
            
            List<Wine> wineryList = model.getWinesByWineryId(wy.getWineryId());
            if(wineryList.isEmpty()){
                System.out.println("This winery has no wines.");
            }
            else{
                System.out.println("This winery has the following wines.");
                System.out.println();
                displayWines(wineryList, model);
            }
            System.out.println();
        }
        else{
            System.out.print("Winery NOT found.");   
        }
        
    }
    
    private static Winery readWinery(Scanner keyb) {
        String wineryName, address, contactName, phoneNo, email, webAddress;

        wineryName = getString(keyb, "Enter name of winery: ");        
        address = getString(keyb, "Enter address of winery: ");
        contactName = getString(keyb, "Enter contact name of winery owner: ");
        phoneNo = getString(keyb, "Enter description of winery: ");
        email = getString(keyb, "Enter email of winery: ");
        webAddress = getString(keyb, "Enter webAddress of winery: ");
        
        Winery wy = new Winery(wineryName,address,contactName,phoneNo, email, webAddress);
        
        return wy;
    }

    private static void editWineryDetails(Scanner keyboard, Winery wy) {
        String wineryName,address,contactName,phoneNo,email,webAddress;
        
        wineryName = getString(keyboard, "Enter name of winery[" + wy.getWineryName() + "]: ");        
        address = getString(keyboard, "Enter address of winery[" + wy.getAddress() + "]:" );
        contactName = getString(keyboard, "Enter contact name of winery owner[" + wy.getContactName() + "]: ");
        phoneNo = getString(keyboard, "Enter description of winery[" + wy.getPhoneNo() + "]:  ");
        email = getString(keyboard, "Enter email of winery[" + wy.getEmail() + "]:  ");
        webAddress = getString(keyboard, "Enter webAddress of winery[" + wy.getWebAddress() + "]: ");
        
        if (wineryName.length() !=0){
            wy.setWineryName(wineryName);
        }
        
        if (address.length() !=0){
            wy.setAddress(address);
        }
        if (contactName.length() !=0){
            wy.setContactName(contactName);
        }
        if (phoneNo.length() !=0){
            wy.setPhoneNo(phoneNo);
        }
        if (email.length() !=0){
            wy.setEmail(email);
        }
        if (webAddress.length() !=0){
            wy.setWebAddress(webAddress);
        }
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }
    
    private static int getInt(Scanner keyboard, String prompt , int defaultValue){
        int opt = defaultValue;
        boolean finished = false;
        
        do{
            try {
                System.out.print(prompt);
                String line = keyboard.nextLine();
                if(line.length() > 0){
                    opt = Integer.parseInt(line);
                }
                
                finished = true;
            }
            catch (NumberFormatException e){
                System.out.println("Exception: " + e.getMessage());
            }
        }
        while(!finished);
        
        return opt;
    }
    
    private static double getDouble(Scanner keyboard, String prompt , double defaultValue){
        double opt = defaultValue;
        boolean finished = false;
        
        do{
            try {
                System.out.print(prompt);
                String line = keyboard.nextLine();
                if(line.length() > 0){
                    opt = Double.parseDouble(line);
                }
                
                finished = true;
            }
            catch (NumberFormatException e){
                System.out.println("Exception: " + e.getMessage());
            }
        }
        while(!finished);
        
        return opt;
    }
      
}
