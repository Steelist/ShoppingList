/*
 * ShoppingList is a group of classes that work together to make a working
 * shopping list.
 */
package ShoppingList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Runs the shopping list application.
 * 
 * @author      Aleksi Hella
 * @version     12.07.2016
 * @since       1.8
 */
public class Main {
    
    /**
     * Gives tasks to other classes to produce a working view of the shopping
     * list.
     * 
     * @param args Command line parameters. Not used.
     * @throws java.io.IOException Throws exception if error occurs.
     */
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        ShoppingList list = new ShoppingList();
        String input = "empty";
        String fileName = "empty";
        
        System.out.println("SHOPPING LIST \nTampere University "
                + "of Applied Sciences");
        
        while (true) {
            System.out.println("Give shopping list (example:"
                    + " 1 milk;2 tomato;3 carrot;)");
            input = scan.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            } else if (input.equalsIgnoreCase("save")) {
                System.out.print("File name (No .txt suffix needed)");
                fileName = scan.nextLine();
                list.saveTextFile(fileName);
                System.out.println("");
            } else if (input.equalsIgnoreCase("open")) {
                System.out.print("File name (example: ShoppingList.txt) ");
                fileName = scan.nextLine();
                list.openTextFile(fileName);
                System.out.println("");
            } else if (input.equalsIgnoreCase("man") ||
                    input.equalsIgnoreCase("manual") ||
                    input.equalsIgnoreCase("help")) {
                System.out.println("Open - Opens a text file.");
                System.out.println("Save - Saves a text file.");
                System.out.println("Help - Displays all available commands.");
                System.out.println("Exit - Exits the application.");
                System.out.println("");
            } else {
                list.addItems(input);
                System.out.println("Your Shopping List now:");
                list.showItems();
                System.out.println("");
            }
        }
    }
}
