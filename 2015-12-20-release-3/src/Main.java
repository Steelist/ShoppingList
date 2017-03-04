/*
 * ShoppingList is a group of classes that work together to make a working
 * shopping list.
 */
package ShoppingList;

import java.util.Scanner;

/**
 * Runs the shopping list application.
 * 
 * @author      Aleksi Hella
 * @version     12.20.2016
 * @since       1.8
 */
public class Main {
    
    /**
     * Gives tasks to other classes to create the shopping list application.
     * 
     * @param args Command line parameters. Not used.
     * @throws java.lang.Exception If file cannot be opened or saved.
     */
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        ShoppingList list = new ShoppingList();
        Manual man = new Manual();
        GUI gui;
        String input = "empty", inputName = "empty";
        
        System.out.println("Would you like a GUI? (Type anything else than "
                + "'yes' to continue with CLI.)");
        input = scan.nextLine();
        
        if (input.equalsIgnoreCase("yes")) {
            gui = new GUI();
            gui.visible();
        } else {
            System.out.println("SHOPPING LIST \nTampere University "
                    + "of Applied Sciences");

            while (true) {
                System.out.println("Give shopping list (example:"
                        + " 1 milk;2 tomato;3 carrot;)");
                input = scan.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.exit(0);
                } else if (input.equalsIgnoreCase("remove")) {
                    System.out.print("Item to be removed: ");
                    inputName = scan.nextLine();
                    list.deleteItem(inputName);
                    System.out.println("");
                    System.out.println(list.showItems());
                } else if (input.equalsIgnoreCase("clear")) {
                    list.clearItems();
                    System.out.println("");
                } else if (input.equalsIgnoreCase("save")) {
                    list.saveTextFile();
                    System.out.println("");
                } else if (input.equalsIgnoreCase("open")) {
                    list.openTextFile();
                    System.out.println(list.showItems());
                } else if (input.equalsIgnoreCase("man") ||
                        input.equalsIgnoreCase("manual") ||
                        input.equalsIgnoreCase("help")) {
                    man.printManual();
                } else {
                    list.addItems(input);
                    System.out.println("Your Shopping List now:");
                    System.out.println(list.showItems());
                }
            }
        }
    }
}
