/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Usuario
 */
public class MainPanel extends JPanel{
    private static JFrame myFrame; 
    private JEditorPane     editorPane;
    private JTabbedPane     tabbedPane;
    private Integer         simulations;
    private static JFrame   frame;
    
    public MainPanel() {
        simulations = 0;
        tabbedPane = new JTabbedPane();
        tabbedPane.add("Sellers",new JPanel());
        tabbedPane.add("Trading",new JPanel());
        tabbedPane.add("Scheduled Jobs",new JPanel());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setOneTouchExpandable(true);
        //splitPane.setLeftComponent(new JScrollPane(treePane));
        splitPane.setRightComponent(tabbedPane);
        //add(new ToolBar(this),BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        setPreferredSize(new Dimension(950, 520));
    }
    
    
    public static void main(String[] args){
        createAndShowGUI();
    }
    
    /**
     * The main thread to run the GUI
     */
    private static void createAndShowGUI()
    {
        myFrame = new JFrame("TheBookofLeaves");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setJMenuBar(new MyMenuBar());
        myFrame.pack();
        myFrame.setVisible(true);
    }
}