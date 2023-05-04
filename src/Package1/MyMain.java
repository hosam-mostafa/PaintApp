/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package1;

import Package2.MyPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author hp
 */
public class MyMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame f=new JFrame();
        f.setTitle("Paint");
        f.setSize(900,700);
        MyPanel p=new MyPanel();
        f.setContentPane(p);
        f.setVisible(true);
        
    }
    
}
