/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espol.tesis.simulation.ui;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 *
 * @author José Luis
 */
public class MyMenuBar extends JMenuBar{
    JMenuItem menuFile;

    public MyMenuBar() {
        initComponents();
    }
    
    
    public void initComponents(){
        menuFile = new JMenuItem("File");
        add(menuFile);
        menuFile = new JMenuItem("Help");
        add(menuFile);
    }
}
