/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.graphics.j2me.dialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.allbinary.graphics.j2me.GraphicsException;
import org.allbinary.graphics.j2me.MyFrame;
import org.allbinary.graphics.j2me.workarea.WorkAreaJPanel;
import org.allbinary.graphics.j2me.workarea.WorkAreaJPanelInterface;

public class NewWorkAreaJDialog extends javax.swing.JDialog
{
   private MyFrame parent;
   private Dimension dimension;
   private JTextField xSizeJTextField;
   private JTextField ySizeJTextField;
   private JTextField nameJTextField;
   
   public NewWorkAreaJDialog(MyFrame parent, boolean modal, Dimension dimension, String newName) throws Exception
   {
      super((java.awt.Frame) parent, modal);
      try
      {
         initComponents();
                  
         this.parent = parent;
         this.xSizeJTextField = new JTextField("12");
         this.ySizeJTextField = new JTextField("12");
         this.nameJTextField = new JTextField(newName);
         this.dimension = dimension;
         
         JButton submitButton = new JButton("Ok");
         submitButton.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent evt)
            {
               try
               {
                  disposeNewDialog();
               }
               catch(Exception e)
               {
                  //throw new GraphicsException("Error Creating New Canvas");
               }
            }
         });
         
         this.setSize(150,100);
         this.getContentPane().setLayout(new GridLayout(5,2));
         this.getContentPane().add(new JLabel("Please enter"));
         this.getContentPane().add(new JLabel(" the size."));
         this.getContentPane().add(new JLabel("Name:"));
         this.getContentPane().add(nameJTextField);
         this.getContentPane().add(new JLabel("X: "));
         this.getContentPane().add(xSizeJTextField);
         this.getContentPane().add(new JLabel("Y: "));
         this.getContentPane().add(ySizeJTextField);
         this.getContentPane().add(submitButton);
         this.show();
      }
      catch(Exception e)
      {
         throw e;
      }
   }
   
   private void disposeNewDialog() throws Exception
   {
      try
      {         
         Integer canvasWidth = new Integer(xSizeJTextField.getText());
         Integer canvasHeight = new Integer(ySizeJTextField.getText());
                  
         String newName = this.nameJTextField.getText();
         
         if(newName == null) throw new GraphicsException("No Name", this, "disposeNewDialog");
                           
         WorkAreaJPanel workAreaJPanel = new WorkAreaJPanel(
            newName,this.dimension, canvasWidth.intValue(),canvasHeight.intValue());
         
         workAreaJPanel.setLayout(new GridLayout(1,1));
                           
         parent.add((WorkAreaJPanelInterface) workAreaJPanel);
                  
         this.dispose();
      }
      catch(Exception e)
      {
         throw e;
      }
   }
   
   private void initComponents()//GEN-BEGIN:initComponents
   {

      addWindowListener(new java.awt.event.WindowAdapter()
      {
         public void windowClosing(java.awt.event.WindowEvent evt)
         {
            closeDialog(evt);
         }
      });

      pack();
   }//GEN-END:initComponents
   
   /** Closes the dialog */
   private void closeDialog(java.awt.event.WindowEvent evt)
    {//GEN-FIRST:event_closeDialog
       setVisible(false);
       dispose();
    }//GEN-LAST:event_closeDialog
   
   public static void main(String args[])
   {
      try
      {
         new NewWorkAreaJDialog(new MyFrame(), true,new Dimension(100,100), "testing").show();
      }
      catch(Exception e)
      {
      }
   }
   
   // Variables declaration - do not modify//GEN-BEGIN:variables
   // End of variables declaration//GEN-END:variables
   
}
