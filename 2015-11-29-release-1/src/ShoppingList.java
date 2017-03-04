/*
 * ShoppingList is a group of classes that work together to make a working
 * shopping list.
 */
package ShoppingList;

/**
 * Adds items to the shopping list and prints them.
 * 
 * @author      Aleksi Hella
 * @version     11.29.2016
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
}