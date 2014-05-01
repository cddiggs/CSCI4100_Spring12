import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class GUIMenu {
	
	
    JTextArea output;
    JScrollPane scrollPane;
 
    /** creates the main menu bar*/
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu, menu2, submenu2;
        JMenuItem menuItem, menuItem2;
 
        /** Create the menu bar*/
        menuBar = new JMenuBar();
        /** File Menu */
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
         
        /** Build the New Items submenu within File.*/
        submenu = new JMenu("New");
        submenu.setMnemonic(KeyEvent.VK_N);
 
        /**a group of JMenuItems for the "New" Submenu*/
 
        menuItem = new JMenuItem("PDF Test");
        menuItem.setToolTipText("Create a new Test");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {try {
				GUI_PDFtest frame = new GUI_PDFtest();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
            }
        });
        submenu.add(menuItem);
        /** Menu item to create a new HTML test */
        menuItem = new JMenuItem("HTML Test");
        menuItem.setToolTipText("Create new HTML Test");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {try {
				GUI_HTMLtest frame = new GUI_HTMLtest();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
            }
        });
        submenu.add(menuItem);
        file.add(submenu);
        /** Menu item to create a Jeopardy Board */
        //TODO The object for the GUI Jeopardy Board needs to be created
        menuItem = new JMenuItem("Jeopardy Board");
        submenu.add(menuItem);
        file.add(submenu);
        
        /** Exit Option */
        JMenuItem eMenuItem = new JMenuItem("Exit");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        file.add(eMenuItem);
        //Add
        menuBar.add(file);
 
        /** Build the Editor Menu */
        menu2 = new JMenu("Editor");
        menu2.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu2);
 
        /**JMenuItems for the Editor menu2
        //Database section*/
        submenu2 = new JMenu("Database");
        submenu2.setMnemonic(KeyEvent.VK_N);
 
        menuItem2 = new JMenuItem("Add");
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {try {
				GUI_AddDatabase frame = new GUI_AddDatabase();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
            }
        });
        submenu2.add(menuItem2);
 
        menuItem2 = new JMenuItem("Remove");
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {try {
				GUI_RemoveDatabase frame = new GUI_RemoveDatabase();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
            }
        });
        submenu2.add(menuItem2);
        menu2.add(submenu2);
        /**Questions Section*/
        submenu2 = new JMenu("Questions");
        submenu2.setMnemonic(KeyEvent.VK_N);
 
        //TODO The object for GUI Add question needs to be created
        menuItem2 = new JMenuItem("Add");
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu2.add(menuItem2);
 
        //TODO the object for GUI Remove question needs to be created
        menuItem2 = new JMenuItem("Remove");
        submenu2.add(menuItem2);
        menu2.add(submenu2);
        
        //TODO the object for GUI edit question needs to be created
        menuItem2 = new JMenuItem("Edit");
        submenu2.add(menuItem2);
        menu2.add(submenu2);
        /**Creates the Menu bar on the main GUI window*/
        return menuBar;
    }
 
    public Container createContentPane() {
        /**Create the content in progress.*/
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
 
        /**Create a scrolled text area.*/
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);
 
        /**Add the text area to the content pane.*/
        contentPane.add(scrollPane, BorderLayout.CENTER);
 
        return contentPane;
    }
 
    /**
     * Create the GUI and show it.*/
    static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Test Generation Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        GUIMenu demo = new GUIMenu();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
 
        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Create and Show the GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}