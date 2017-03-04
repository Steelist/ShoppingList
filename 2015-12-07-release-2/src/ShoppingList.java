/*
 * ShoppingList is a group of classes that work together to make a working
 * shopping list.
 */
package ShoppingList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Adds items to the shopping list and prints them.
 * 
 * @author      Aleksi Hella
 * @version     12.07.2016
 * @since       1.8
 */
public class ShoppingList {
    MyLinkedList<Object> list;
    
    /**
     * Constructs the shopping list.
     */
    public ShoppingList() {
        list = new MyLinkedList<>();
    }

    /**
     * Splits the input into pieces and adds the item and its count to the
     * linked list.
     * 
     * @param input Users input.
     */
    public void addItems(String input) {
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
    }
    
    /**
     * Shows the items currently in the shopping list in the given order.
     */
    public void showItems() {
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }
    
    /**
     * Saves the file with the given name.
     * 
     * @param fileName Name for the file that user has given.
     * @throws IOException Throws exception if error occurs.
     */
    public void saveTextFile(String fileName) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName + ".txt"), "UTF-8"))) {
            for (int i = list.size() - 1; i >= 0; i--) {
                writer.write((String) list.get(i) + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Reads a file with the given name and turns the content into
     * a MyLinkedList type list.
     * 
     * @param fileName Given name for the file to open.
     * @return The MyLinkedList that was created by reading the file.
     * @throws IOException Throws exception if error occurs.
     */
    public MyLinkedList openTextFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
                addItems(line);
                line = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return list;
    }
}