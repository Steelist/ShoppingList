/*
 * ShoppingList is a group of classes that work together to make a working
 * shopping list.
 */
package ShoppingList;

/**
 * Runs the manual for the shopping list application.
 * 
 * @author      Aleksi Hella
 * @version     12.20.2016
 * @since       1.8
 */
public class Manual {
    
    /**
     * Prints the manual for the command line interface users to see.
     */
    public void printManual() {
        System.out.println("Open - Opens a text file.");
        System.out.println("Save - Saves a text file of the list.");
        System.out.println("Remove - Removes a given item.");
        System.out.println("Clear - Clears the list.");
        System.out.println("Help - Displays all available commands.");
        System.out.println("Exit - Exits the application.");
        System.out.println("");
    }
}
