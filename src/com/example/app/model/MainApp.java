package com.example.app.model;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
        int opt;
        do {
            System.out.println("1. Create new Wine");
            System.out.println("2. Delete existing Wine");
            System.out.println("3. Edit existing Wine");
            System.out.println("4. View all Wines");
            System.out.println();
            System.out.println("5. Create new Winery");
            System.out.println("6. Delete existing Winery");
            System.out.println("7. Edit existing Winery");
            System.out.println("8. View all Winerys");
            System.out.println();
            System.out.println("9. Exit");
            System.out.println();

            System.out.print("Enter option: ");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

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
                    viewWines(model);
                    break;
                }
                
                case 5: {
                    System.out.println("Creating ry.");
                    createWinery(keyboard, model);
                    break;
                }
                case 6: {
                    System.out.println("Deleting winery.");
                    deleteWinery(keyboard, model);
                    break;
                }
                case 7: {
                    System.out.println("Editing winery.");
                    editWinery(keyboard, model);
                    break;
                }
                case 8: {
                    System.out.println("Viewing winery.");
                    viewWinerys(model);
                    break;
                    
                }
            }
        } while (opt != 5);
        System.out.println("Goodbye");
    }

    private static void createWine(Scanner keyboard, Model model) {
        Wine w = readWine(keyboard);
        if (model.addWine(w)) {
            System.out.println("Wine added to database.");
        } else {
            System.out.println("Wine NOT added to database.");
        }
        System.out.println();
    }

    private static void deleteWine(Scanner keyboard, Model model){
        System.out.print("Enter the name of wine you want to delete: ");
        int id = Integer.parseInt(keyboard.nextLine());
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
    
    private static void editWine(Scanner keyboard, Model model){
        System.out.print("Enter the Wine ID of the wine you want to edit: ");
        int id = Integer.parseInt(keyboard.nextLine());
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
    
    private static void viewWines(Model model) {
        List<Wine> wines = model.getWines();

        System.out.println();
        if (wines.isEmpty()) {
            System.out.println("There are no wines in the database");
        } else {
            System.out.printf("%10s %20s %20s %15s %22s %20s %10s\n", "WineID", "Name", "YearMade", "Type", "Temperature", "Description", "Winery");
            for (Wine wi : wines) {
                System.out.printf("%10s %20s %20d %15s %22f %20s %10d\n",
                        wi.getId(),
                        wi.getName(),
                        wi.getYearMade(),
                        wi.getType(),
                        wi.getTempurature(),
                        wi.getDescription(),
                        wi.getWineryId());
            }
        }
        System.out.println();

    }

    private static Wine readWine(Scanner keyb) {
        String name, type, description;
        int yearMade, wineryId;
        double tempurature;
        String line;

        name = getString(keyb, "Enter name of wine: ");
        line = getString(keyb, "Enter year of wine: ");
        yearMade = Integer.parseInt(line);
        type = getString(keyb, "Enter type of wine: ");
        line = getString(keyb, "Enter serving tempurature of wine: ");
        tempurature = Double.parseDouble(line);
        description = getString(keyb, "Enter description of wine: ");
        line = getString(keyb, "Enter winery id where wine was made: ");
        wineryId = Integer.parseInt(line);

        Wine w
                = new Wine(name, yearMade, type,
                        tempurature, description, wineryId);

        return w;
    }

    private static void editWineDetails(Scanner keyboard, Wine w) {
        String name, type, description;
        int yearMade, wineryId;
        double tempurature;
        String line1, line2, line3; 
        
        name = getString(keyboard, "Enter name of wine[" + w.getName() + "]: ");
        line1 = getString(keyboard, "Enter year of wine[" + w.getYearMade() + "]: ");
        type = getString(keyboard, "Enter type of wine[" + w.getType() + "]: ");
        line2 = getString(keyboard, "Enter serving tempurature of wine[" + w.getTempurature() + "]: ");
        description = getString(keyboard, "Enter description of wine[" + w.getDescription() + "]: ");
        line3 = getString(keyboard, "Enter winery id where the wine was made [" + w.getWineryId()+ "]: ");
        wineryId = Integer.parseInt(line3);
        
        if (name.length() !=0){
            w.setName(name);
        }
        if (line1.length() !=0){
            yearMade = Integer.parseInt(line1);
            w.setYearMade(yearMade);
        }
        if (type.length() !=0){
            w.setType(type);
        }
        if (line2.length() !=0){
            tempurature = Integer.parseInt(line2);
            w.setTempurature(tempurature);
        }
        if (description.length() !=0){
            w.setDescription(description);
        }
        if (line3.length() !=0){
            wineryId = Integer.parseInt(line3);
            w.setWineryId(wineryId);
        }
        
    }
    
    private static void createWinery(Scanner keyboard, Model model) {
        Winery wy = readWinery(keyboard);
        if (model.addWinery(wy)) {
            System.out.println("Winery added to database.");
        } else {
            System.out.println("Winery NOT added to database.");
        }
        System.out.println();
    }

    private static void deleteWinery(Scanner keyboard, Model model){
        System.out.print("Enter the name of winery you want to delete: ");
        int id = Integer.parseInt(keyboard.nextLine());
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
    
    private static void editWinery(Scanner keyboard, Model model){
        System.out.print("Enter the Winery ID of the winery you want to edit: ");
        int id = Integer.parseInt(keyboard.nextLine());
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
            System.out.printf("%10s %20s %20s %20s %15s %60s %60s %10s\n", "WineryId", "Winery Name", "Address", "Contact Name", "Phone No", "Email","Web Address", "Wine");
            for (Winery wy : winerys) {
                System.out.printf("%10s %20s %20s %20s %15s %60s %60s %10s\n",
                        wy.getWineryId(),
                        wy.getWineryName(),
                        wy.getAddress(),
                        wy.getContactName(),
                        wy.getPhoneNo(),
                        wy.getEmail(),
                        wy.getWebAddress(),
                        wy.getWineId()
                );
            }
        }
        System.out.println();

    }

    private static Winery readWinery(Scanner keyb) {
        String wineryName, address, contactName, phoneNo, email, webAddress;
        int wineId;
        String line;

        wineryName = getString(keyb, "Enter name of winery: ");        
        address = getString(keyb, "Enter address of winery: ");
        contactName = getString(keyb, "Enter contact name of winery owner: ");
        phoneNo = getString(keyb, "Enter description of winery: ");
        email = getString(keyb, "Enter email of winery: ");
        webAddress = getString(keyb, "Enter webAddress of winery: ");
        line = getString(keyb, "Enter wine id where winery was ordered from: ");
        wineId = Integer.parseInt(line);
        
        
        Winery wy
                = new Winery(
                        wineryName, 
                        address , 
                        contactName,
                        phoneNo, email, webAddress, wineId
                );

        
        return wy;
    }

    private static void editWineryDetails(Scanner keyboard, Winery wy) {
        String wineryName,address,contactName,phoneNo,email,webAddress;
        int wineId;
        String line1; 
        
        wineryName = getString(keyboard, "Enter name of winery[" + wy.getWineryName() + "]: ");        
        address = getString(keyboard, "Enter address of winery[" + wy.getAddress() + "]:" );
        contactName = getString(keyboard, "Enter contact name of winery owner[" + wy.getContactName() + "]: ");
        phoneNo = getString(keyboard, "Enter description of winery[" + wy.getPhoneNo() + "]:  ");
        email = getString(keyboard, "Enter email of winery[" + wy.getEmail() + "]:  ");
        webAddress = getString(keyboard, "Enter webAddress of winery[" + wy.getWineId() + "]: ");
        line1 = getString(keyboard, "Enter wine id where winery was ordered from: ");
        wineId = Integer.parseInt(line1);

        
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
        if (line1.length() !=0){
            wineId = Integer.parseInt(line1);
            wy.setWineId(wineId);
        }
        
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }
}
