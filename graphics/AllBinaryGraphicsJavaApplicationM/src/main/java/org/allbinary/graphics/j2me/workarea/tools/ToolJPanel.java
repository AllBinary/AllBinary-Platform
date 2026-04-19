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

public class ToolJPanel extends javax.swing.JPanel
{   
   public ToolJPanel()
   {
      initComponents();
   }
      
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lineJButton = new javax.swing.JButton();
        this.rectJButton = new javax.swing.JButton();
        this.arcJButton = new javax.swing.JButton();
        this.stringJButton = new javax.swing.JButton();
        this.charJButton = new javax.swing.JButton();
        this.roundRectJButton = new javax.swing.JButton();
        this.imageJButton = new javax.swing.JButton();
        this.fillArcJButton = new javax.swing.JButton();
        this.fillRectJButton = new javax.swing.JButton();
        this.fillRoundRectJButton = new javax.swing.JButton();
        this.dottedLineJButton = new javax.swing.JButton();
        this.dottedRectButton = new javax.swing.JButton();
        this.dottedArcJButton = new javax.swing.JButton();
        this.dottedFillRectJButton = new javax.swing.JButton();
        this.dottedFillArcJButton = new javax.swing.JButton();
        this.dottedFillRountRectJButton = new javax.swing.JButton();
        this.colorJPanel = new javax.swing.JPanel();
        this.colorJList = new javax.swing.JList();

        setLayout(new java.awt.GridLayout(17, 1));

        lineJButton.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lineJButton.setText(LinesGraphicItem.getStaticName());
        lineJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lineJButtonMouseClicked(evt);
            }
        });
        add(this.lineJButton);

        this.rectJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.rectJButton.setText("Rect");
        rectJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rectJButtonMouseClicked(evt);
            }
        });
        add(this.rectJButton);

        this.arcJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.arcJButton.setText("Arc");
        arcJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                arcJButtonMousePressed(evt);
            }
        });
        add(this.arcJButton);

        this.stringJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.stringJButton.setText("String");
        stringJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stringJButtonMousePressed(evt);
            }
        });
        add(this.stringJButton);

        this.charJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.charJButton.setText("Char");
        charJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                charJButtonMousePressed(evt);
            }
        });
        add(this.charJButton);

        this.roundRectJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.roundRectJButton.setText("Round Rect");
        roundRectJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                roundRectJButtonMousePressed(evt);
            }
        });
        add(this.roundRectJButton);

        this.imageJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.imageJButton.setText("Image");
        imageJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imageJButtonMousePressed(evt);
            }
        });
        add(this.imageJButton);

        this.fillArcJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.fillArcJButton.setText("Fill Arc");
        fillArcJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fillArcJButtonMousePressed(evt);
            }
        });
        add(this.fillArcJButton);

        this.fillRectJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.fillRectJButton.setText("Fill Rect");
        fillRectJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fillRectJButtonMousePressed(evt);
            }
        });
        add(this.fillRectJButton);

        this.fillRoundRectJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.fillRoundRectJButton.setText("Fill Rnd Rect");
        fillRoundRectJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fillRoundRectJButtonMousePressed(evt);
            }
        });
        add(this.fillRoundRectJButton);

        this.dottedLineJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.dottedLineJButton.setText("Dot Line");
        dottedLineJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dottedLineJButtonMousePressed(evt);
            }
        });
        add(this.dottedLineJButton);

        this.dottedRectButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.dottedRectButton.setText("Dot Rect");
        dottedRectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dottedRectButtonMousePressed(evt);
            }
        });
        add(this.dottedRectButton);

        this.dottedArcJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.dottedArcJButton.setText("Dot Arc");
        dottedArcJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dottedArcJButtonMousePressed(evt);
            }
        });
        add(this.dottedArcJButton);

        this.dottedFillRectJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.dottedFillRectJButton.setText("Dot Fill Rect");
        dottedFillRectJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dottedFillRectJButtonMousePressed(evt);
            }
        });
        add(this.dottedFillRectJButton);

        this.dottedFillArcJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.dottedFillArcJButton.setText("Dot Fill Arc");
        dottedFillArcJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dottedFillArcJButtonMousePressed(evt);
            }
        });
        add(this.dottedFillArcJButton);

        this.dottedFillRountRectJButton.setFont(new java.awt.Font("Dialog", 0, 10));
        this.dottedFillRountRectJButton.setText("Dot Fill Rnd Rect");
        dottedFillRountRectJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dottedFillRountRectJButtonMousePressed(evt);
            }
        });
        add(this.dottedFillRountRectJButton);

        this.colorJPanel.setLayout(new java.awt.GridLayout(1, 1));
        this.colorJPanel.add(this.colorJList);

        add(this.colorJPanel);
    }// </editor-fold>//GEN-END:initComponents

   private void dottedFillRountRectJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_dottedFillRountRectJButtonMousePressed
   {//GEN-HEADEREND:event_dottedFillRountRectJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedFillRountRectJButton.getText());
   }//GEN-LAST:event_dottedFillRountRectJButtonMousePressed

   private void dottedFillArcJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_dottedFillArcJButtonMousePressed
   {//GEN-HEADEREND:event_dottedFillArcJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedFillArcJButton.getText());
   }//GEN-LAST:event_dottedFillArcJButtonMousePressed

   private void dottedFillRectJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_dottedFillRectJButtonMousePressed
   {//GEN-HEADEREND:event_dottedFillRectJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedFillRectJButton.getText());
   }//GEN-LAST:event_dottedFillRectJButtonMousePressed

   private void dottedArcJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_dottedArcJButtonMousePressed
   {//GEN-HEADEREND:event_dottedArcJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedArcJButton.getText());
   }//GEN-LAST:event_dottedArcJButtonMousePressed

   private void dottedRectButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_dottedRectButtonMousePressed
   {//GEN-HEADEREND:event_dottedRectButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedRectButton.getText());
   }//GEN-LAST:event_dottedRectButtonMousePressed

   private void dottedLineJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_dottedLineJButtonMousePressed
   {//GEN-HEADEREND:event_dottedLineJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.dottedLineJButton.getText());
   }//GEN-LAST:event_dottedLineJButtonMousePressed

   private void fillRoundRectJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_fillRoundRectJButtonMousePressed
   {//GEN-HEADEREND:event_fillRoundRectJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.fillRoundRectJButton.getText());
   }//GEN-LAST:event_fillRoundRectJButtonMousePressed

   private void fillRectJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_fillRectJButtonMousePressed
   {//GEN-HEADEREND:event_fillRectJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.fillRectJButton.getText());
   }//GEN-LAST:event_fillRectJButtonMousePressed

   private void fillArcJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_fillArcJButtonMousePressed
   {//GEN-HEADEREND:event_fillArcJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.fillArcJButton.getText());
   }//GEN-LAST:event_fillArcJButtonMousePressed

   private void imageJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_imageJButtonMousePressed
   {//GEN-HEADEREND:event_imageJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.imageJButton.getText());
   }//GEN-LAST:event_imageJButtonMousePressed

   private void roundRectJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_roundRectJButtonMousePressed
   {//GEN-HEADEREND:event_roundRectJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.roundRectJButton.getText());
   }//GEN-LAST:event_roundRectJButtonMousePressed

   private void charJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_charJButtonMousePressed
   {//GEN-HEADEREND:event_charJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.charJButton.getText());
   }//GEN-LAST:event_charJButtonMousePressed

   private void stringJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_stringJButtonMousePressed
   {//GEN-HEADEREND:event_stringJButtonMousePressed
      ToolFactory.getInstance().setSelectedTool(this.stringJButton.getText());
   }//GEN-LAST:event_stringJButtonMousePressed

   private void arcJButtonMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_arcJButtonMousePressed
   {//GEN-HEADEREND:event_arcJButtonMousePressed
      
      ToolFactory.getInstance().setSelectedTool(this.arcJButton.getText());
   }//GEN-LAST:event_arcJButtonMousePressed

   private void rectJButtonMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_rectJButtonMouseClicked
   {//GEN-HEADEREND:event_rectJButtonMouseClicked
      ToolFactory.getInstance().setSelectedTool(this.rectJButton.getText());
   }//GEN-LAST:event_rectJButtonMouseClicked

   private void lineJButtonMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lineJButtonMouseClicked
   {//GEN-HEADEREND:event_lineJButtonMouseClicked
      ToolFactory.getInstance().setSelectedTool(this.lineJButton.getText());
   }//GEN-LAST:event_lineJButtonMouseClicked
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton arcJButton;
    private javax.swing.JButton charJButton;
    private javax.swing.JList colorJList;
    private javax.swing.JPanel colorJPanel;
    private javax.swing.JButton dottedArcJButton;
    private javax.swing.JButton dottedFillArcJButton;
    private javax.swing.JButton dottedFillRectJButton;
    private javax.swing.JButton dottedFillRountRectJButton;
    private javax.swing.JButton dottedLineJButton;
    private javax.swing.JButton dottedRectButton;
    private javax.swing.JButton fillArcJButton;
    private javax.swing.JButton fillRectJButton;
    private javax.swing.JButton fillRoundRectJButton;
    private javax.swing.JButton imageJButton;
    private javax.swing.JButton lineJButton;
    private javax.swing.JButton rectJButton;
    private javax.swing.JButton roundRectJButton;
    private javax.swing.JButton stringJButton;
    // End of variables declaration//GEN-END:variables
   
}
