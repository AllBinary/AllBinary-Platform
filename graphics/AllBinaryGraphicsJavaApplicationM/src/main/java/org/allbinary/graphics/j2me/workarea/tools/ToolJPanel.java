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
package org.allbinary.graphics.j2me.workarea.tools;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

public class ToolJPanel extends JPanel
{   
   public ToolJPanel()
   {
      this.initComponents();
   }
      
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lineJButton = new JButton();
        this.rectJButton = new JButton();
        this.arcJButton = new JButton();
        this.stringJButton = new JButton();
        this.charJButton = new JButton();
        this.roundRectJButton = new JButton();
        this.imageJButton = new JButton();
        this.fillArcJButton = new JButton();
        this.fillRectJButton = new JButton();
        this.fillRoundRectJButton = new JButton();
        this.dottedLineJButton = new JButton();
        this.dottedRectButton = new JButton();
        this.dottedArcJButton = new JButton();
        this.dottedFillRectJButton = new JButton();
        this.dottedFillArcJButton = new JButton();
        this.dottedFillRountRectJButton = new JButton();
        this.colorJPanel = new JPanel();
        this.colorJList = new JList();

        this.setLayout(new GridLayout(17, 1));

        lineJButton.setFont(new Font("Dialog", 0, 10)); // NOI18N
        lineJButton.setText(LinesGraphicItem.getStaticName());
        lineJButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                lineJButtonMouseClicked(evt);
            }
        });
        add(this.lineJButton);

        this.rectJButton.setFont(new Font("Dialog", 0, 10));
        this.rectJButton.setText("Rect");
        this.rectJButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                rectJButtonMouseClicked(evt);
            }
        });
        add(this.rectJButton);

        this.arcJButton.setFont(new Font("Dialog", 0, 10));
        this.arcJButton.setText("Arc");
        this.arcJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                arcJButtonMousePressed(evt);
            }
        });
        add(this.arcJButton);

        this.stringJButton.setFont(new Font("Dialog", 0, 10));
        this.stringJButton.setText("String");
        this.stringJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                stringJButtonMousePressed(evt);
            }
        });
        add(this.stringJButton);

        this.charJButton.setFont(new Font("Dialog", 0, 10));
        this.charJButton.setText("Char");
        this.charJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                charJButtonMousePressed(evt);
            }
        });
        add(this.charJButton);

        this.roundRectJButton.setFont(new Font("Dialog", 0, 10));
        this.roundRectJButton.setText("Round Rect");
        this.roundRectJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                roundRectJButtonMousePressed(evt);
            }
        });
        add(this.roundRectJButton);

        this.imageJButton.setFont(new Font("Dialog", 0, 10));
        this.imageJButton.setText("Image");
        this.imageJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                imageJButtonMousePressed(evt);
            }
        });
        add(this.imageJButton);

        this.fillArcJButton.setFont(new Font("Dialog", 0, 10));
        this.fillArcJButton.setText("Fill Arc");
        this.fillArcJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                fillArcJButtonMousePressed(evt);
            }
        });
        add(this.fillArcJButton);

        this.fillRectJButton.setFont(new Font("Dialog", 0, 10));
        this.fillRectJButton.setText("Fill Rect");
        this.fillRectJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                fillRectJButtonMousePressed(evt);
            }
        });
        add(this.fillRectJButton);

        this.fillRoundRectJButton.setFont(new Font("Dialog", 0, 10));
        this.fillRoundRectJButton.setText("Fill Rnd Rect");
        this.fillRoundRectJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                fillRoundRectJButtonMousePressed(evt);
            }
        });
        add(this.fillRoundRectJButton);

        this.dottedLineJButton.setFont(new Font("Dialog", 0, 10));
        this.dottedLineJButton.setText("Dot Line");
        this.dottedLineJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                dottedLineJButtonMousePressed(evt);
            }
        });
        add(this.dottedLineJButton);

        this.dottedRectButton.setFont(new Font("Dialog", 0, 10));
        this.dottedRectButton.setText("Dot Rect");
        this.dottedRectButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                dottedRectButtonMousePressed(evt);
            }
        });
        add(this.dottedRectButton);

        this.dottedArcJButton.setFont(new Font("Dialog", 0, 10));
        this.dottedArcJButton.setText("Dot Arc");
        this.dottedArcJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                dottedArcJButtonMousePressed(evt);
            }
        });
        add(this.dottedArcJButton);

        this.dottedFillRectJButton.setFont(new Font("Dialog", 0, 10));
        this.dottedFillRectJButton.setText("Dot Fill Rect");
        this.dottedFillRectJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                dottedFillRectJButtonMousePressed(evt);
            }
        });
        add(this.dottedFillRectJButton);

        this.dottedFillArcJButton.setFont(new Font("Dialog", 0, 10));
        this.dottedFillArcJButton.setText("Dot Fill Arc");
        this.dottedFillArcJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                dottedFillArcJButtonMousePressed(evt);
            }
        });
        add(this.dottedFillArcJButton);

        this.dottedFillRountRectJButton.setFont(new Font("Dialog", 0, 10));
        this.dottedFillRountRectJButton.setText("Dot Fill Rnd Rect");
        this.dottedFillRountRectJButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                dottedFillRountRectJButtonMousePressed(evt);
            }
        });
        add(this.dottedFillRountRectJButton);

        this.colorJPanel.setLayout(new GridLayout(1, 1));
        this.colorJPanel.add(this.colorJList);

        add(this.colorJPanel);
    }// </editor-fold>//GEN-END:initComponents

   private void dottedFillRountRectJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_dottedFillRountRectJButtonMousePressed
   {//GEN-HEADEREND:event_dottedFillRountRectJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedFillRountRectJButton.getText());
   }//GEN-LAST:event_dottedFillRountRectJButtonMousePressed

   private void dottedFillArcJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_dottedFillArcJButtonMousePressed
   {//GEN-HEADEREND:event_dottedFillArcJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedFillArcJButton.getText());
   }//GEN-LAST:event_dottedFillArcJButtonMousePressed

   private void dottedFillRectJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_dottedFillRectJButtonMousePressed
   {//GEN-HEADEREND:event_dottedFillRectJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedFillRectJButton.getText());
   }//GEN-LAST:event_dottedFillRectJButtonMousePressed

   private void dottedArcJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_dottedArcJButtonMousePressed
   {//GEN-HEADEREND:event_dottedArcJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedArcJButton.getText());
   }//GEN-LAST:event_dottedArcJButtonMousePressed

   private void dottedRectButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_dottedRectButtonMousePressed
   {//GEN-HEADEREND:event_dottedRectButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedRectButton.getText());
   }//GEN-LAST:event_dottedRectButtonMousePressed

   private void dottedLineJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_dottedLineJButtonMousePressed
   {//GEN-HEADEREND:event_dottedLineJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedLineJButton.getText());
   }//GEN-LAST:event_dottedLineJButtonMousePressed

   private void fillRoundRectJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_fillRoundRectJButtonMousePressed
   {//GEN-HEADEREND:event_fillRoundRectJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.fillRoundRectJButton.getText());
   }//GEN-LAST:event_fillRoundRectJButtonMousePressed

   private void fillRectJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_fillRectJButtonMousePressed
   {//GEN-HEADEREND:event_fillRectJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.fillRectJButton.getText());
   }//GEN-LAST:event_fillRectJButtonMousePressed

   private void fillArcJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_fillArcJButtonMousePressed
   {//GEN-HEADEREND:event_fillArcJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.fillArcJButton.getText());
   }//GEN-LAST:event_fillArcJButtonMousePressed

   private void imageJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_imageJButtonMousePressed
   {//GEN-HEADEREND:event_imageJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.imageJButton.getText());
   }//GEN-LAST:event_imageJButtonMousePressed

   private void roundRectJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_roundRectJButtonMousePressed
   {//GEN-HEADEREND:event_roundRectJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.roundRectJButton.getText());
   }//GEN-LAST:event_roundRectJButtonMousePressed

   private void charJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_charJButtonMousePressed
   {//GEN-HEADEREND:event_charJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.charJButton.getText());
   }//GEN-LAST:event_charJButtonMousePressed

   private void stringJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_stringJButtonMousePressed
   {//GEN-HEADEREND:event_stringJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.stringJButton.getText());
   }//GEN-LAST:event_stringJButtonMousePressed

   private void arcJButtonMousePressed(MouseEvent evt)//GEN-FIRST:event_arcJButtonMousePressed
   {//GEN-HEADEREND:event_arcJButtonMousePressed
      
      ToolFactory.getInstance().setSelectedTool(this.arcJButton.getText());
   }//GEN-LAST:event_arcJButtonMousePressed

   private void rectJButtonMouseClicked(MouseEvent evt)//GEN-FIRST:event_rectJButtonMouseClicked
   {//GEN-HEADEREND:event_rectJButtonMouseClicked
      ToolFactory.getInstance().setSelectedTool(this.rectJButton.getText());
   }//GEN-LAST:event_rectJButtonMouseClicked

   private void lineJButtonMouseClicked(MouseEvent evt)//GEN-FIRST:event_lineJButtonMouseClicked
   {//GEN-HEADEREND:event_lineJButtonMouseClicked
      ToolFactory.getInstance().setSelectedTool(this.lineJButton.getText());
   }//GEN-LAST:event_lineJButtonMouseClicked
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton arcJButton;
    private JButton charJButton;
    private JList colorJList;
    private JPanel colorJPanel;
    private JButton dottedArcJButton;
    private JButton dottedFillArcJButton;
    private JButton dottedFillRectJButton;
    private JButton dottedFillRountRectJButton;
    private JButton dottedLineJButton;
    private JButton dottedRectButton;
    private JButton fillArcJButton;
    private JButton fillRectJButton;
    private JButton fillRoundRectJButton;
    private JButton imageJButton;
    private JButton lineJButton;
    private JButton rectJButton;
    private JButton roundRectJButton;
    private JButton stringJButton;
    // End of variables declaration//GEN-END:variables
   
}
