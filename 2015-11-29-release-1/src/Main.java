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
 * @version     11.29.2016
 * @since       1.8
 */
public class Main {
    
    /**
     * Gives tasks to other classes to produce a working view of the shopping
     * list.
     * 
     * @param args Command line parameters. Not used.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ShoppingList list = new ShoppingList();
        String input = "empty";
        
        System.out.println("SHOPPING LIST \nTampere University "
                + "of Applied Sciences");
        
        while (true) {
            System.out.println("Give shopping list (example:"
                    + " 1 milk;2 tomato;3 carrot;)");
            input = scan.nextLine();
            
            if (input.equals("exit")) {
                break;
            } else {
                list.addItems(input);
                System.out.println("Your Shopping List now:");
                list.showItems();
                System.out.println("");
            }
        }
    }
}
