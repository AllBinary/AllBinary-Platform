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
package org.allbinary.image.gui;

import org.allbinary.media.image.ImageProcessorInput;
import org.allbinary.media.image.ImageProcessorInputCompositeInterface;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.allbinary.string.CommonStrings;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.media.image.ImageJ2SERotationUtil;
import org.allbinary.media.image.ImageStrings;

public class RotationSpriteImageJPanel extends javax.swing.JPanel
        implements ImageProcessorInputCompositeInterface {

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final ImageStrings imageStrings = ImageStrings.getInstance();

    private ImageProcessorInput imageProcessorInput;

    private BufferedImage result;

    public RotationSpriteImageJPanel(ImageProcessorInput imageProcessorInput) 
       throws Exception 
    {
        super();

        initComponents();
        this.imageProcessorInput = imageProcessorInput;

    //setUI((PanelUI)UIManager.getUI(this));
    }

    public void process() {
        new Thread() {

            public void run() {
                try {
                    BufferedImage generatedBufferedImageArray[];

                    ImageProcessorInput imageProcessorInput =
                    RotationSpriteImageJPanel.this.getImageProcessorInput();
                    BufferedImage[] bufferedImageArray = 
                       imageProcessorInput.getBufferedImageArray();
                    
                    for (int index = 0; index < bufferedImageArray.length; index++) {
                        Integer totalFrames = Integer.valueOf(
                                (String) RotationSpriteImageJPanel.this.totalFramesJComboBox.getSelectedItem());
                        Integer totalAngle = Integer.valueOf(
                                (String) RotationSpriteImageJPanel.this.totalAngleJComboBox.getSelectedItem());
                        generatedBufferedImageArray = ImageJ2SERotationUtil.getInstance().getRotatedImages(
                                bufferedImageArray[index], totalFrames, totalAngle);

                        RotationSpriteImageJPanel.this.result =
                                ImageJ2SERotationUtil.getInstance().createSpriteImage(
                                generatedBufferedImageArray);

                        RotationSpriteImageJPanel.this.getParent().repaint();

                        File file = imageProcessorInput.getFiles()[index];
                        if (!RotationSpriteImageJPanel.this.writeOverOriginalJCheckBox.isSelected()) {
                            
                            String filePath = file.getAbsolutePath();
                            final int extensionIndex = filePath.indexOf(imageStrings.PNG_EXTENSION);
                            filePath = new StringMaker().append(filePath.substring(0, extensionIndex)).append(CommonSeps.getInstance().UNDERSCORE).append("sprite").append(imageStrings.PNG_EXTENSION).toString();
                            LogUtil.put(LogFactory.getInstance("New File Path: " + filePath, this, StringUtil.getInstance().EMPTY_STRING));
                            
                            file = new File(filePath);
                        }

                        boolean isWritten =
                                ImageIO.write((RenderedImage) RotationSpriteImageJPanel.this.result, imageStrings.PNG, file);

                        LogUtil.put(LogFactory.getInstance("File: " + file + " Wrote: " + isWritten, this, ""));

                    }

                } catch (Exception e) {
                    LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
                }
            }
        }.start();
    }
    /*
    public void paint(Graphics graphics) {
    LogUtil.put(LogFactory.getInstance(commonStrings.START, this, canvasStrings.PAINT));
     */
    //graphics.setColor(BasicColors.BLUE.toColor());
    //graphics.fillRect(0, 0, getWidth(),getHeight());
      /*
    int columnIndex = 0;
    int rowIndex = 0;
    for(int index = 0; index < bufferedImageArray.length; index++)
    {
    if(index/9 != 0 && index % 9 == 0)
    {
    rowIndex ++;
    columnIndex = 0;
    }
    graphics.drawImage(this.bufferedImageArray[index],
    bufferedImageArray[index].getWidth(null) * columnIndex, 
    bufferedImageArray[index].getHeight(null) * rowIndex, 
    this.bufferedImageArray[index].getWidth(null),
    this.bufferedImageArray[index].getHeight(null), null);
    columnIndex++;
    }
     */
    /*
    this.jPanel1.update(graphics);
    }
     */

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jPanel1 = new javax.swing.JPanel(){
         public void paint(Graphics graphics)
         {
            if(RotationSpriteImageJPanel.this.result != null)
            {
               graphics.drawImage(RotationSpriteImageJPanel.this.result, 0, 0,
                  RotationSpriteImageJPanel.this.result.getWidth(null), RotationSpriteImageJPanel.this.result.getHeight(null), null);
            }
         }
      }
      ;
      jPanel2 = new javax.swing.JPanel();
      totalFramesJComboBox = new javax.swing.JComboBox();
      jLabel1 = new javax.swing.JLabel();
      generateJButton = new javax.swing.JButton();
      totalAngleJComboBox = new javax.swing.JComboBox();
      jLabel3 = new javax.swing.JLabel();
      writeOverOriginalJCheckBox = new javax.swing.JCheckBox();
      jPanel3 = new javax.swing.JPanel();
      jLabel2 = new javax.swing.JLabel();

      org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 436, Short.MAX_VALUE)
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(0, 227, Short.MAX_VALUE)
      );

      totalFramesJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "4", "9", "18", "20", "36" }));

      jLabel1.setText("Total Frames:");

      generateJButton.setText("Generate");
      generateJButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            generateJButtonActionPerformed(evt);
         }
      });

      totalAngleJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "360", "90" }));
      totalAngleJComboBox.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            totalAngleJComboBoxActionPerformed(evt);
         }
      });

      jLabel3.setText("Total Angle:");

      //writeOverOriginalJCheckBox.setSelected(true);
      writeOverOriginalJCheckBox.setText("Write Over Original");

      org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
      jPanel2.setLayout(jPanel2Layout);
      jPanel2Layout.setHorizontalGroup(
         jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jPanel2Layout.createSequentialGroup()
            .add(8, 8, 8)
            .add(jLabel1)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(totalFramesJComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jLabel3)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(totalAngleJComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(writeOverOriginalJCheckBox)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(generateJButton)
            .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      jPanel2Layout.setVerticalGroup(
         jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(jLabel1)
            .add(totalFramesJComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(generateJButton)
            .add(totalAngleJComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jLabel3)
            .add(writeOverOriginalJCheckBox))
      );

      jLabel2.setText("Results:");

      org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
      jPanel3.setLayout(jPanel3Layout);
      jPanel3Layout.setHorizontalGroup(
         jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jPanel3Layout.createSequentialGroup()
            .add(jLabel2)
            .addContainerGap(377, Short.MAX_VALUE))
      );
      jPanel3Layout.setVerticalGroup(
         jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(jLabel2)
      );

      org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
         .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
   }// </editor-fold>//GEN-END:initComponents
    private void generateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateJButtonActionPerformed
        // TODO add your handling code here:
        this.process();
}//GEN-LAST:event_generateJButtonActionPerformed

private void totalAngleJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalAngleJComboBoxActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_totalAngleJComboBoxActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton generateJButton;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JComboBox totalAngleJComboBox;
   private javax.swing.JComboBox totalFramesJComboBox;
   private javax.swing.JCheckBox writeOverOriginalJCheckBox;
   // End of variables declaration//GEN-END:variables

   public ImageProcessorInput getImageProcessorInput()
   {
      return imageProcessorInput;
   }

   public void setImageProcessorInput(ImageProcessorInput imageProcessorInput)
   {
      this.imageProcessorInput = imageProcessorInput;
   }

}
