/*
 * ShoppingList is a group of classes that work together to make a working
 * shopping list.
 */
package ShoppingList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

/**
 * Adds items to the shopping list and prints them.
 * 
 * @author      Aleksi Hella
 * @version     12.20.2016
 * @since       1.8
 */
public class ShoppingList {
    
    /**
     * Is the linked list where all the items are stored.
     */
    private MyLinkedList<Object> list;
    
    /**
     * Let's the user pick files with the JFileChooser when used.
     */
    private FileChooser fc;
    
    /**
     * Constructs the shopping list.
     */
    public ShoppingList() {
        list = new MyLinkedList<>();
    }

    /**
     * Splits the input into pieces and adds the item to the linked list.
     * 
     * @param input Users input.
     */
    public void addItems(String input) {
        try {
            String[] objects = input.split(";| ");
            int[] count = new int[objects.length / 2];
            String[] item = new String[objects.length / 2];
            int arrNum = 0;
            int arrNum2 = 0;

            for (int i = 0; i < objects.length; i++) {         
                if (i % 2 == 0) {
                    count[arrNum] = Integer.parseInt(objects[i]);
                    arrNum++;
                } else {
                    item[arrNum2] = objects[i];
                    arrNum2++;
                }
            }

            for (int i = 0; i < count.length; i++) {
                if (list.checkAndAdd(item[i], count[i]) == false) {
                    list.add(count[i], item[i]);
                }
            }
        } catch (Exception e) {
            System.out.print("Input type invalid.");
        }
    }
    
    /**
     * Deletes a wanted item from the list.
     * 
     * Notifies the user if the list is already empty or if the item
     * could not be found.
     * 
     * @param input Item's name that is getting deleted.
     */
    public void deleteItem(String input) {
        boolean deleted;
        
        deleted = list.remove(input);
        
        if (deleted == true) {
            System.out.print("Item deleted.");
        } else {
            System.out.print("Could not find the item.");
        }
    }
    
    /**
     * Clears all items in the linked list.
     * 
     * If the list is already empty then notifies the user.
     */
    public void clearItems() {
        
        if (list.isEmpty()) {
            System.out.print("The shopping list is empty.");
        } else {
            list.clear();

            System.out.print("List cleared.");
        }
    }
    
    /**
     * Shows the items currently in the shopping list in the given order.
     * 
     * @return The items in the shopping list.
     */
    public String showItems() {
        String items = "";
        
        for (int i = list.size() - 1; i >= 0; i--) {
            items += list.get(i) + "\n";
        }
        
        return items;
    }
    
    /**
     * Saves the file by choosing the location and name via FileChooser.
     * 
     * Gets all the information from the linked list for the writer to write
     * in the file that was chosen.
     * 
     * @throws Exception Throws exception if error occurs.
     */
    public void saveTextFile() throws Exception {
        fc = new FileChooser();

        try {
            Writer output = null;
            File file = new File(fc.saveFile());
            output = new BufferedWriter(new FileWriter(file));

            for (int i = list.size() - 1; i >= 0; i--) {
                output.append((String) list.get(i) + "\n");
            }

            output.close();
            System.out.println("File has been written.");
        } catch (Exception e) {
            System.out.println("Could not create file.");
        }
    }
    
    /**
     * Reads a file with the name that FileChooser gets.
     * 
     * Turns the content into a linked list and saves the content to the
     * current list.
     * 
     * @return The linked list that was created by reading a file.
     * @throws Exception Throws exception if error occurs.
     */
    public MyLinkedList openTextFile() throws Exception {
        fc = new FileChooser();
        list.clear();
        
        try (BufferedReader br = new BufferedReader(new FileReader
            (fc.openFile()))) {
            String line = br.readLine();
            
            while (line != null) {
                addItems(line);
                line = br.readLine();
            }
            
            System.out.println("File was opened.");
        } catch (Exception e) {
            System.out.println("Could not open file.");
        }
        
        return list;
    }
}
