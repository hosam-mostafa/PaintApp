/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.Vector;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.undo.UndoManager;

/**
 *
 * @author hp
 */
public class MyPanel extends JPanel {
  
    String [] colors_choices={"Red", "Green", "Blue"};
    String [] shapes_choices={"Rectangle", "Oval", "Line"};
    JComboBox<String> colors;
    
    JComboBox<String> shapes;
    String colorChosen, shapeChosen;
    int startx, starty, endx, endy;
    
    private Vector<Plot> plots;
    JCheckBox dottedCheck;
    boolean dotted;
    JCheckBox filledCheck;
    boolean filledd;
    JButton freehand;
    public JButton clearAll;
    boolean clearall=false;
    JButton eraser;
    boolean eraseItem=false;
   
    public MyPanel() {
        this.setBackground(Color.WHITE);
        colors= new JComboBox<String>(colors_choices);
        
        shapes= new JComboBox<String>(shapes_choices);
        dottedCheck=new JCheckBox("Dotted");
        filledCheck=new JCheckBox("Filled Shape");
        freehand=new JButton("Free Hand");
        eraser=new JButton("Eraser");
        clearAll=new JButton("Clear All");
        this.add(colors);
        
        this.add(shapes);
        this.add(dottedCheck);
        this.add(filledCheck);
        this.add(freehand);
        this.add(eraser);
        this.add(clearAll);
        dotted=false;
        filledd=false;
        
        
        colorChosen = (String) colors.getItemAt(0);
        shapeChosen = (String) shapes.getItemAt(0);
        plots=new Vector<Plot>();
        startx=starty=endx=endy=0;
        
        colors.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
    {
                colorChosen = (String) colors.getSelectedItem();
        
    }
});
        shapes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
    {
                shapeChosen = (String) shapes.getSelectedItem();
        
    }
});
        this.setFocusable(true);
        
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {}

            @Override
            public void mousePressed(MouseEvent me) {
                
                    startx=me.getX();
                    
                    starty=me.getY();
                    
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
               
               if(!shapeChosen.equals("hand") || shapeChosen.equals("er")){
               endx=me.getX();
               endy=me.getY();
               
               addPlot(shapeChosen,colorChosen,startx,starty,endx,endy,dotted,filledd);
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) {}

            @Override
            public void mouseExited(MouseEvent me) {}
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent me) {
                if(shapeChosen.equals("hand") || shapeChosen.equals("er")){
         
                  endx = me.getX();
                  endy = me.getY();
                  startx = endx;
                  starty = endy;
                  
                  repaint();
                  addPlot(shapeChosen,colorChosen,startx,starty,endx,endy,dotted,filledd);
                         
            }
                else{
                 endx=me.getX();
                 endy=me.getY();
                 updateUI();
        }
            }
            @Override
            public void mouseMoved(MouseEvent me) {}
        });
        dottedCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(ie.getStateChange()==1){
                    dotted=true;
                    
                }
                else{
                    dotted =false;
                    
                }
            }
        });
        
        filledCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(ie.getStateChange() == 1)filledd=true;
                else filledd=false;
            }
        });
        
        freehand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                shapeChosen="hand";
                
            }
        });
        
        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                 shapeChosen="er";
                 plots.setSize(0);
                 plots.clear();
                 repaint();
                
                 
            }
            
        });
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                shapeChosen="er";
            }
        });
            
        
        
        
       
    }
    public void addPlot(String type,String color, int x1, int y1, int x2, int y2, boolean dotss, boolean fills){
        
        plots.add(new Plot(type, color, x1, y1, x2, y2,dotss,fills));
    }
    
    public void paintall(Graphics g,String colorall, String shapesall,int x,int x2,int y, int y2, boolean dot,boolean fill){
        if(colorChosen!=null && shapeChosen!=null){
        switch(colorall){
                case "Red":
                    g.setColor(Color.RED);
                    break;
                case "Green":
                    g.setColor(Color.GREEN);
                    break;
                case "Blue":
                    g.setColor(Color.BLUE);
                    break;
                default:
                    break;
            }
            if(!dot){
            switch(shapesall){
                case"Rectangle":
                    int px = Math.min(x,x2);
                    int py = Math.min(y,y2);
                    int pw=Math.abs(x-x2);//width
                    int ph=Math.abs(y-y2);//height
                    if(fill)g.fillRect(px, py, pw, ph);
                    else g.drawRect(px, py, pw, ph);
                    break;
                case"Oval":
                    int pxo = Math.min(x,x2);
                    int pyo = Math.min(y,y2);
                    int pwo=Math.abs(x-x2);
                    int pho=Math.abs(y-y2);
                    if(fill)g.fillOval(pxo, pyo, pwo, pho);
                    else g.drawOval(pxo, pyo, pwo, pho);
                    break;
                case"Line":
                    g.drawLine(x, y, x2, y2);
                    break;
                case"hand":
                    g.fillOval(x, y, 10, 10);
                    
                    break;
                case "er":
                    g.setColor(Color.WHITE);
                    g.fillOval(x, y, 10, 10);
                    break;
                default:
                    break;
            }}
            else{
                drawDashedLine(g, x, y, x2, y2,shapesall);
            }
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        
        for(Plot p:plots){
            p.draw(g);
        }
        
               
            paintall(g, colorChosen, shapeChosen, startx, endx, starty, endy,dotted,filledd);
            
       
        
        
    }
   
    
    
    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2, String shapesall){

  // Create a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

  // Set the stroke of the copy, not the original 
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                                  0, new float[]{9}, 0);
        g2d.setStroke(dashed);

  // Draw to the copy
        switch(shapesall){
                case"Rectangle":
                    int px = Math.min(x1,x2);
                    int py = Math.min(y1,y2);
                    int pw=Math.abs(x1-x2);
                    int ph=Math.abs(y1-y2);
                    g2d.drawRect(px, py, pw, ph);
                    break;
                case"Oval":
                    int pxo = Math.min(x1,x2);
                    int pyo = Math.min(y1,y2);
                    int pwo=Math.abs(x1-x2);
                    int pho=Math.abs(y1-y2);
                    g2d.drawOval(pxo, pyo, pwo, pho);
                    break;
                case"Line":
                    g2d.drawLine(x1, y1, x2, y2);
                    break;
                case"hand":
                    
                    g.fillOval(x1, y1, 10, 10);
                    
                    break; 
                case "er":
                    g.setColor(Color.WHITE);
                    g.fillOval(x1, y1, 10, 10);
                    break;
                default:
                    break;
            }

  // Get rid of the copy
        g2d.dispose();
}
    
   public class Plot
{   
    private String type;
    private String color;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private boolean dots;
    private boolean filled;

    public Plot(String type, String color, int startX, int startY, int endX, int endY, boolean dots, boolean filled){
        this.type=type;
        this.color=color;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX ;
        this.endY = endY ;
        this.dots=dots;
        this.filled=filled;
    }
    public void draw(Graphics g){
              
            paintall(g, color, type, startX, endX, startY, endY,dots,filled);
      
        }
      
    
    
}
   
        
   
}
