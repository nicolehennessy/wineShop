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
            System.out.println("5. Exit");
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
            }
        } while (opt != 5);
        System.out.println("Goodbye");
    }

    private static void createWine(Scanner keyboard, Model model) {
        Wine p = readWine(keyboard);
        if (model.addWine(p)) {
            System.out.println("Wine added to database.");
        } else {
            System.out.println("Wine NOT added to database.");
        }
        System.out.println();
    }

    private static void deleteWine(Scanner keyboard, Model model){
        System.out.print("Enter the name of wine you want to delete: ");
        int wineID = Integer.parseInt(keyboard.nextLine());
        Wine p;
        
        p = model.findWineByWineID(wineID);
        if (p != null){
            if (model.removeWine(p)){
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
        int wineID = Integer.parseInt(keyboard.nextLine());
        Wine p;
        
        p = model.findWineByWineID(wineID);
        if (p != null){
            editWineDetails(keyboard, p);
            if (model.updateWine(p)){
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
            System.out.printf("%10s %20s %20d %15s %22f %20s\n", "WineID", "Name", "YearMade", "Type", "Temperature", "Description");
            for (Wine wi : wines) {
                System.out.printf("%10s %20s %20d %15s %22f %20s\n",
                        wi.getWineID(),
                        wi.getName(),
                        wi.getYearMade(),
                        wi.getType(),
                        wi.getTempurature(),
                        wi.getDescription());
            }
        }
        System.out.println();

    }

    private static Wine readWine(Scanner keyb) {
        String name, type, description;
        int yearMade;
        double tempurature;
        String line;

        name = getString(keyb, "Enter name of wine: ");
        line = getString(keyb, "Enter year of wine: ");
        yearMade = Integer.parseInt(line);
        type = getString(keyb, "Enter type of wine: ");
        line = getString(keyb, "Enter serving tempurature of wine: ");
        tempurature = Double.parseDouble(line);
        description = getString(keyb, "Enter description of wine: ");

        Wine p
                = new Wine(name, yearMade, type,
                        tempurature, description);

        return p;
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private static void editWineDetails(Scanner keyboard, Wine p) {
        String name, type, description;
        int yearMade;
        double tempurature;
        String line1, line2; 
        
        name = getString(keyboard, "Enter name of wine[" + p.getName() + "]: ");
        line1 = getString(keyboard, "Enter year of wine[" + p.getYearMade() + "]: ");
        yearMade = Integer.parseInt(line1);
        type = getString(keyboard, "Enter type of wine[" + p.getType() + "]: ");
        line2 = getString(keyboard, "Enter serving tempurature of wine[" + p.getTempurature() + "]: ");
        tempurature = Double.parseDouble(line2);
        description = getString(keyboard, "Enter description of wine[" + p.getDescription() + "]: ");
        
        if (name.length() !=0){
            p.setName(name);
        }
        if (line1.length() !=0){
            yearMade = Integer.parseInt(line1);
            p.setYearMade(yearMade);
        }
        if (type.length() !=0){
            p.setType(type);
        }
        if (line2.length() !=0){
            tempurature = Integer.parseInt(line2);
            p.setTempurature(tempurature);
        }
        if (description.length() !=0){
            p.setDescription(description);
        }
    }
}
