/*
 * ShoppingList is a group of classes that work together to make a working
 * shopping list.
 */
package ShoppingList;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Runs the graphical user interface for the shopping list application.
 * 
 * @author      Aleksi Hella
 * @version     12.20.2016
 * @since       1.8
 */
public class GUI extends JFrame {
    
    /**
     * Is the shopping list object to work with the shopping list items.
     */
    private ShoppingList list = new ShoppingList();
    
    /**
     * Is the button for adding items.
     */
    private JButton buttonAdd;
    
    /**
     * Is the button for clearing all items.
     */
    private JButton buttonClear;
    
    /**
     * Is the button for removing a single item from the list.
     */
    private JButton buttonRemove;
    
    /**
     * Is the field where user types all the items.
     */
    private JTextField itemInputField;
    
    /**
     * Is the area where all the shopping list items are shown.
     */
    private JTextArea itemOutputArea;
    
    /**
     * Let's the viewing area of items to be scrolled if needed (long input).
     */
    private JScrollPane scrollingPanel;
    
    /**
     * Is the menu bar for the menu buttons.
     */
    private JMenuBar menuBar;
    
    /**
     * Is the button for opening a file.
     */
    private JMenu menuOpen;
    
    /**
     * Is the button for saving a file.
     */
    private JMenu menuSave;
    
    /**
     * Calls the initialization for the components.
     */
    public GUI() {
        initComponents();
    }
    
    /**
     * Initializes components and sets them in their places via Group Layout.
     * 
     * @param evt Action event.
     */
    private void initComponents() {
        itemInputField = new JTextField();
        scrollingPanel = new JScrollPane();
        itemOutputArea = new JTextArea();
        buttonAdd = new JButton();
        buttonRemove = new JButton();
        buttonClear = new JButton();
        menuBar = new JMenuBar();
        menuSave = new JMenu();
        menuOpen = new JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        itemInputField.setText("List item here (1 milk;2 bread...)");
        itemInputField.addFocusListener(new FocusAdapter() {
            
            /**
             * Adds focus listener to the input field.
             * 
             * Performs the given method when the field gains focus.
             * 
             * @param evt Action event, clicking the field.
             */
            public void focusGained(FocusEvent evt) {
                itemInputFieldFocusGained(evt);
            }
        });

        itemOutputArea.setEditable(false);
        itemOutputArea.setColumns(20);
        itemOutputArea.setRows(5);
        scrollingPanel.setViewportView(itemOutputArea);

        buttonAdd.setText("Add");
        buttonRemove.setText("Remove");
        buttonClear.setText("Clear");
        
        buttonAdd.addActionListener((ActionEvent evt) -> {
            buttonAddActionPerformed(evt);
        });

        buttonRemove.addActionListener((ActionEvent evt) -> {
            buttonRemoveActionPerformed(evt);
        });

        buttonClear.addActionListener((ActionEvent evt) -> {
            buttonClearActionPerformed(evt);
        });

        menuSave.setText("Save");
        menuOpen.setText("Open");
        
        menuSave.addMouseListener(new MouseAdapter() {
            
            /**
             * Adds mouse listener to the menuSave button.
             * 
             * Performs the given method when the button is clicked.
             * 
             * @param evt Action event, clicking the button.
             */
            public void mouseClicked(MouseEvent evt) {
                menuSaveMouseClicked(evt);
            }
        });
        menuBar.add(menuSave);
        
        menuOpen.addMouseListener(new MouseAdapter() {
            
            /**
             * Adds mouse listener to the menuOpen button.
             * 
             * Performs the given method when the button is clicked.
             * 
             * @param evt Action event, clicking the button.
             */
            public void mouseClicked(MouseEvent evt) {
                menuOpenMouseClicked(evt);
            }
        });
        menuBar.add(menuOpen);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(
                GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                    .addComponent(scrollingPanel)
                    .addComponent(itemInputField, GroupLayout.PREFERRED_SIZE,
                            240, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(buttonAdd, GroupLayout.PREFERRED_SIZE,
                                82, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.TRAILING,
                            layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(
                                GroupLayout.Alignment.LEADING)
                            .addComponent(buttonRemove, 
                                    GroupLayout.Alignment.TRAILING, 
                                    GroupLayout.PREFERRED_SIZE, 82, 
                                    GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonClear, 
                                    GroupLayout.Alignment.TRAILING, 
                                    GroupLayout.PREFERRED_SIZE, 82, 
                                    GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(
                GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAdd)
                    .addComponent(itemInputField, GroupLayout.PREFERRED_SIZE,
                            40, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(buttonRemove)
                        .addGap(18, 18, 18)
                        .addComponent(buttonClear)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, 
                                Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, 
                            layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                14, Short.MAX_VALUE)
                        .addComponent(scrollingPanel, 
                                GroupLayout.PREFERRED_SIZE,204, 
                                GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }                     

    /**
     * Adds items written on the input field to the linked list.
     * 
     * Shows the added items as the button is clicked. Also changes the input
     * field's text to a template text.
     * 
     * @param evt Action event, clicking the button.
     */
    private void buttonAddActionPerformed(ActionEvent evt) {
        list.addItems(itemInputField.getText());
        
        itemOutputArea.setText(list.showItems());
        itemInputField.setText("List item here (1 milk;2 bread...)");
    }                                         

    /**
     * Removes an item written in the input field from the linked list.
     * 
     * Shows what's left as the button is clicked. Also changes the input
     * field's text to a template text.
     * 
     * @param evt Action event, clicking the button.
     */
    private void buttonRemoveActionPerformed(ActionEvent evt) {
        list.deleteItem(itemInputField.getText());
        
        itemOutputArea.setText(list.showItems());
        itemInputField.setText("List item here (1 milk;2 bread...)");
    }                                            

    /**
     * Clears the whole linked list.
     * 
     * Updates the view as the button is clicked. Also changes the input
     * field's text to a template text.
     * 
     * @param evt Action event, clicking the button.
     */
    private void buttonClearActionPerformed(ActionEvent evt) {
        list.clearItems();
        
        itemOutputArea.setText(list.showItems());
        itemInputField.setText("List item here (1 milk;2 bread...)");
    }                                           

    /**
     * Removes the text from the input field.
     * 
     * If the field had not gained focus yet and the template text was in place
     * the text is removed so the user can start writing right away.
     * 
     * @param evt Action event, clicking the field.
     */
    private void itemInputFieldFocusGained(FocusEvent evt) {
        if (itemInputField.getText().equals("List item here "
                + "(1 milk;2 bread...)")) {
            itemInputField.setText("");
        }
    }                                          

    /**
     * Opens the FileChooser and let's the user pick a file to open.
     * 
     * Updates the view to match the opened file's content.
     * 
     * @param evt Action event, clicking the button.
     */
    private void menuOpenMouseClicked(MouseEvent evt) {
        try {
            list.openTextFile();
            
            itemOutputArea.setText(list.showItems());
            itemInputField.setText("List item here (1 milk;2 bread...)");
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                     

    /**
     * Opens the FileChooser and let's the user decide the filename.
     * 
     * After deciding the filename saves the file to the chosen directory with
     * the chosen filename.
     * 
     * @param evt Action event, clicking the button.
     */
    private void menuSaveMouseClicked(MouseEvent evt) {
        try {
            list.saveTextFile();
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                     

    /**
     * Sets the GUI visible after all other GUI elements are in place.
     */
    public void visible() {
        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }               
}
