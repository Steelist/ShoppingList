/*
 * ShoppingList is a group of classes that work together to make a working
 * shopping list.
 */
package ShoppingList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Chooses the file locations for opening and saving a file.
 * 
 * @author      Aleksi Hella
 * @version     12.20.2016
 * @since       1.8
 */
public class FileChooser extends JFrame {
    
    /**
     * These are the file's filename and directory's path.
     * 
     * After using the FileChooser the user picks the filename and directory
     * path and they are saved to these two variables.
     */
    private String filename = "", dir = "";
    
    /**
     * Let's the user pick files with the JFileChooser when used.
     */
    private JFileChooser fc;

    /**
     * Lets the user pick a file.
     * 
     * Saves the chosen file's filename and path to the filename and dir
     * variables. Cancels selection if user decides to cancel.
     * 
     * @return Chosen file's filename and path.
     */
    public String openFile() {
        fc = new JFileChooser();
        int rVal = fc.showOpenDialog(FileChooser.this);
        
        if (rVal == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile().getName();
            dir = fc.getCurrentDirectory().toString();
        }
        
        if (rVal == JFileChooser.CANCEL_OPTION) {
            fc.cancelSelection();
        }

        return dir + "\\" + filename;
    }

    /**
     * Let's user pick a directory and filename.
     * 
     * Saves the name and path to the chosen directory. Name and path are saved
     * to the filename and dir variables. Cancels selection if user decides
     * to cancel.
     * 
     * @return Chosen filename and path.
     */
    public String saveFile() {
        fc = new JFileChooser();
        int rVal = fc.showSaveDialog(FileChooser.this);
        
        if (rVal == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile().getName();
            dir = fc.getCurrentDirectory().toString();
        }
        
        if (rVal == JFileChooser.CANCEL_OPTION) {
            fc.cancelSelection();
        }
        
        return dir + "\\" + filename;
    }
}
