/*
 * ShoppingList is a group of classes that work together to make a working
 * shopping list.
 */
package ShoppingList;

/**
 * Is the linked list where items are added, searched and removed from.
 * 
 * @author      Aleksi Hella
 * @version     11.29.2016
 * @param <T>   The type of the linked list.
 * @since       1.8
 */
public class MyLinkedList<T> {
    
    /**
     * Holds the main pieces for a linked list to work. Each element has a link
     * to the next one and each one contains a String type item and its int 
     * type quantity.
     * 
     * @param <T> The type of the element.
     */
    private class Element<T> {
        Element<T> next;
        String content;
        int quantity;
    }
     
    private Element<T> first;
    private int size;
    
    /**
     * Adds an item to the linked list and links it to the previous one if not
     * the first one. Adds plus one to the size attribute.
     * 
     * @param count Item's quantity.
     * @param item Item's name.
     */
    public void add(int count, String item) {
 
        if (first == null) {
            first = new Element<>();
            first.next = null;
            first.content = item;
            first.quantity = count;
        } else {
            Element<T> newElement = new Element<>();
            newElement.content = item;
            newElement.quantity = count;
            newElement.next = first;
            first = newElement;
        }
         
        size++;
    }

    /**
     * Empties the linked list and sets the size attribute to 0.
     */
    public void clear() {
        first = null;
        size = 0;
    }

    /**
     * Gives the quantity and the name of an item in the given index location.
     * 
     * @param index Index number to get the item from.
     * @return Quantity and name of the wanted item.
     */
    public Object get(int index) {
        Element<T> temp = first;
         
        if (index < 0 || index >= size) {
            return null;
        } else {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
             
            return temp.quantity + " " + temp.content;
        }
    }

    /**
     * Checks for duplicate item names and if finds one adds the given amount
     * to the existing item's quantity and return true. If it doesn't find a 
     * duplicate then just returns false.
     * 
     * @param item Item name to check.
     * @param count Item quantity to add to the existing one if found.
     * @return True if duplicate is found and false if isn't.
     */
    public boolean checkAndAdd(String item, int count) {
        Element<T> temp = first;
        boolean duplicate = false;
        
        for (int i = 0; i < size; i++) {
            if (temp.content.equals(item)) {
                temp.quantity += count;
                duplicate = true;
                break;
            } else {
                temp = temp.next;
            }
        }
        
        return duplicate;
    }

    /**
     * Returns true if the list is empty and false if the list isn't empty.
     * Added for later use.
     * 
     * @return True if the list is empty and false if it isn't.
     */
    public boolean isEmpty() {
        if (first == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes the element from the index location. Added for later use.
     * 
     * @param index Index of the item wanted to be removed.
     * @return The item that was removed.
     */
    public Object remove(int index) {
        Element<T> temp = first;
        String removed;
         
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            removed = first.content;
            first = first.next;
             
            size--;
            return removed;
        } else if (index == size) {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
             
            removed = temp.next.content;
            temp.next = null;
             
            size--;
            return removed;
        } else {
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
 
            removed = temp.next.content;
            temp.next = temp.next.next;
             
            size--;
            return removed;
        }
    }

    /**
     * Removes the given object from the list. Added for later use.
     * 
     * @param o Is the given object's name.
     * @return True if the object was removed and false if it wasn't.
     */
    public boolean remove(Object o) {
        boolean removed = false;
        Element<T> temp = first;
         
        for (int i = 0; i < size; i++) {
            if (o.equals(temp.content)) {
                remove(i);
                removed = true;
                break;
            } else if (i != size) {
                temp = temp.next;
            }
        }
         
        return removed;
    }

    /**
     * Returns the size of the linked list.
     * 
     * @return The size of the linked list.
     */
    public int size() {
        return size;
    }
}