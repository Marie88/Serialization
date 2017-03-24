
package hw10;

import java.io.*;
import java.awt.*;
import java.awt.Panel;
import javax.swing.JTextArea;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HW10  {
   
    public static void main(String[] args) {
        
        Frame f = new Frame("Person");
        Panel p1 = new Panel();
        Panel p2 = new Panel();
        Panel p3 = new Panel();
        Panel p4= new Panel();
        Panel p5=new Panel();
        Panel p6=new Panel();
        Panel p7=new Panel();
         f.setLayout(new GridLayout(10,50));
        
        
        JTextArea n1=new JTextArea(" Name: ");
        JTextArea n2=new JTextArea(" Surname: ");
        JTextArea n3=new JTextArea(" Gender ");
        JTextArea n4=new JTextArea(" Age: ");
        JTextArea n5=new JTextArea(" City: ");
        JTextArea n6=new JTextArea(" Country: ");
        
        TextField t1= new TextField(10);
        TextField t2=new TextField(10);
        TextField t3= new TextField(3);
        TextField t4=new TextField(10);
        CheckboxGroup g =new CheckboxGroup();
         JScrollBar bar= new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 100);
        bar.setBounds(0, 0, 0, 120);
        
        
        class Frame2 implements AdjustmentListener{
            @Override
                public void adjustmentValueChanged(AdjustmentEvent arg0) {
        
                                int value=bar.getValue();
                                t3.setText(Integer.toString(value));
		
	}
    
}
        Frame2 al=new Frame2();
        
        bar.addAdjustmentListener(al);
        
        
        Choice countryChoice=new Choice();
        countryChoice.add("Romania");
        countryChoice.add("UK");
        countryChoice.add("France");
        countryChoice.add("Italy"); 
        
        
        Button b1=new Button("Add");
        Button b2=new Button("ShowAll");
        
        p1.add(n1); p1.add(t1);
        p2.add(n2);p2.add(t2);
        p3.add(n3); p3.add(new Checkbox("M",g,true));p3.add(new Checkbox("F",g,true));
        p4.add(n4);p4.add(bar);p4.add(t3);
        p5.add(n5);p5.add(t4);
        p6.add(n6);p6.add(countryChoice);
        p7.add(b1);p7.add(b2);
        
        f.add(p1);
        f.add(p2);
        f.add(p3);
        f.add(p4);
        f.add(p5);
        f.add(p6);
        f.add(p7);
        f.pack();
        f.show();
           
        f.addWindowListener(new WindowAdapter() {

        @Override
            public void windowClosing(WindowEvent e) {
		System.exit(0);
            }
	});
       
        
        b1.addActionListener(new ActionListener() {
                  @Override
                       public void actionPerformed(ActionEvent event) {
                           Person p= new Person(t1.getText(),t2.getText(),g.getSelectedCheckbox().getLabel(),t3.getText(),t4.getText(),countryChoice.getSelectedItem());
                       try {
                          FileOutputStream fos=new FileOutputStream(new File("persons.dat"),true);
                      } catch (FileNotFoundException ex) {
                          System.out.println("De unde fisier daca n-ai");
                      }
                      try {
                          writeToFile(p);
                      } catch (IOException ex) {
                          Logger.getLogger(HW10.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     
                      
                     }
		});
        
      b2.addActionListener(new ActionListener() {
                  @Override 
                       public void actionPerformed(ActionEvent event) {
                           FileInputStream fi;
                      try {
                          fi = new FileInputStream("persons.dat");}
                          catch (FileNotFoundException ex) {System.out.println();}
                       
                      try {
                          
                           while(true){
                             
                                  try {
                                 fi = new FileInputStream("persons.dat");
                                      try {
                                          readToFile();
                                      } catch (ClassNotFoundException ex) {
                                          Logger.getLogger(HW10.class.getName()).log(Level.SEVERE, null, ex);
                                      }
}
                                 catch (FileNotFoundException ex) {System.out.println();}
                                  
                                  //System.out.println(readToFile());                           //Person p=(Person) ooi;
                                  //System.out.println(p.getName());
                              
                                  
                              
                            }
                      } catch (IOException ex) {
                          Logger.getLogger(HW10.class.getName()).log(Level.SEVERE, null, ex);
                      }
                       
            
        }
                     
                      
                     });
		} 

     private static void writeToFile(Person p) throws IOException{
              ObjectOutputStream obj= new ObjectOutputStream(new FileOutputStream("persons.dat",true));
              obj.writeObject(p.toString());
    }
     
    private static void readToFile() throws IOException, ClassNotFoundException{
            FileInputStream fi=new FileInputStream("persons.dat");
        ObjectInputStream ooi=new ObjectInputStream(fi);
        while(true){
            try{
            Person p=(Person) ooi.readObject();
            System.out.println(p.getName());
            }
            catch(EOFException e) {break;}
        }
           
    }
    
}  

