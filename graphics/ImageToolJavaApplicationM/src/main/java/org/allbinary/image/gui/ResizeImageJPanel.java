/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.file.FileWrapperUtil;
import org.allbinary.media.image.ImagePersistanceUtil;
import org.allbinary.media.image.ImageProcessorInput;
import org.allbinary.media.image.ImageProcessorInputCompositeInterface;
import org.allbinary.media.image.ImageUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class ResizeImageJPanel extends javax.swing.JPanel
        implements ImageProcessorInputCompositeInterface {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private ImageProcessorInput imageProcessorInput;
    
    public ResizeImageJPanel(final ImageProcessorInput imageProcessorInput) throws Exception {
        super();

        initComponents();

        this.imageProcessorInput = imageProcessorInput;

        String[] numberStringArray = new String[101];
        numberStringArray[0] = Integer.toString(-1);
        for (int index = 1; index < 100; index++) {
            numberStringArray[index] = Integer.toString(index);
        }

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(numberStringArray));

        final Raster araster = this.imageProcessorInput.getBufferedImageArray()[0].getAlphaRaster();
        if (araster == null) {
            System.out.println("there is no Alpha channel!!!!!!!!!");

        } else {
            System.out.println("Alpha channel found !");
        }

    //setUI((PanelUI)UIManager.getUI(this));
    }

    public void process() {
        new Thread() {

            @Override
            public void run() {
                try {
                    final ImageUtil imageUtil = ImageUtil.getInstance();
                    final Integer percent = Integer.valueOf((String) ResizeImageJPanel.this.jComboBox1.getSelectedItem());
                    final Float percentAsFloat = Float.parseFloat(ResizeImageJPanel.this.floatPercentJTextField.getText());

                    final ImageProcessorInput imageProcessorInput = ResizeImageJPanel.this.getImageProcessorInput();
                    final File[] files = imageProcessorInput.getFiles();
                    
                    BufferedImage[] generatedBufferedImageArray = null;
                    if(percentAsFloat.intValue() != -1) {
                        generatedBufferedImageArray = 
                                imageUtil.createBufferedImage(
                                        imageProcessorInput.getBufferedImageArray(), percentAsFloat, true);
                    } else if(percent.intValue() != -1) {
                        generatedBufferedImageArray = 
                                imageUtil.createBufferedImage(
                                        imageProcessorInput.getBufferedImageArray(), percent, true);
                    } else {
                        final Integer width = Integer.valueOf((String) ResizeImageJPanel.this.jTextField1.getText());
                        final Integer height = Integer.valueOf((String) ResizeImageJPanel.this.jTextField2.getText());
                        generatedBufferedImageArray = 
                                imageUtil.createBufferedImage(
                                        imageProcessorInput.getBufferedImageArray(), width, height, true);
                    }

                    final Raster araster = generatedBufferedImageArray[0].getAlphaRaster();
                    if (araster == null) {
                        System.out.println("No Alpha Channel In Result");

                    } else {
                        System.out.println("Found Alpha Channel In Result");
                    }

                    final ImagePersistanceUtil imagePersistanceUtil = ImagePersistanceUtil.getInstance();

                    for (int index = 0; index < generatedBufferedImageArray.length; index++) {

                        imagePersistanceUtil.saveWithBatik(FileWrapperUtil.wrapFile(files[index]), generatedBufferedImageArray[index]);
                    }

                    ResizeImageJPanel.this.getParent().repaint();

                } catch (Exception e) {
                    logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
                }
            }
        }.start();
    }

    public void update() {
        new Thread() {

            @Override
            public void run() {
                try {
                    final ImageProcessorInput imageProcessorInput = ResizeImageJPanel.this.getImageProcessorInput();
                    
                    final int size = imageProcessorInput.getBufferedImageArray().length;
                    final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();
                    BufferedImage bufferedImage;
                    for(int index = 0; index < size; index++) {
                        bufferedImage = bufferedImageArray[0];
                        ResizeImageJPanel.this.jTextField1.setText(Integer.toString(bufferedImage.getWidth()));
                        ResizeImageJPanel.this.jTextField2.setText(Integer.toString(bufferedImage.getHeight()));
                    }

                    ResizeImageJPanel.this.getParent().repaint();

                } catch (Exception e) {
                    logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
                }
            }
        }.start();
    }

    public void updateFor16Above() {
        new Thread() {

            @Override
            public void run() {
                try {
                    final ImageProcessorInput imageProcessorInput = ResizeImageJPanel.this.getImageProcessorInput();
                    
                    final int size = imageProcessorInput.getBufferedImageArray().length;
                    final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();
                    BufferedImage bufferedImage;
                    for(int index = 0; index < size; index++) {
                        bufferedImage = bufferedImageArray[0];
                        ResizeImageJPanel.this.jTextField1.setText(Integer.toString(bufferedImage.getWidth() / 16 * 16));
                        ResizeImageJPanel.this.jTextField2.setText(Integer.toString(bufferedImage.getHeight() / 16 * 16));
                    }

                    ResizeImageJPanel.this.getParent().repaint();

                } catch (Exception e) {
                    logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
                }
            }
        }.start();
    }

    public void updateFor16Below() {
        new Thread() {

            @Override
            public void run() {
                try {
                    final ImageProcessorInput imageProcessorInput = ResizeImageJPanel.this.getImageProcessorInput();
                    
                    final int size = imageProcessorInput.getBufferedImageArray().length;
                    final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();
                    BufferedImage bufferedImage;
                    for(int index = 0; index < size; index++) {
                        bufferedImage = bufferedImageArray[0];
                        ResizeImageJPanel.this.jTextField1.setText(Integer.toString(((bufferedImage.getWidth() / 16) + 1) * 16));
                        ResizeImageJPanel.this.jTextField2.setText(Integer.toString(((bufferedImage.getHeight() / 16) + 1) * 16));
                    }

                    ResizeImageJPanel.this.getParent().repaint();

                } catch (Exception e) {
                    logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
                }
            }
        }.start();
    }
    
    public ImageProcessorInput getImageProcessorInput() {
        return imageProcessorInput;
    }

    public void setImageProcessorInput(ImageProcessorInput imageProcessorInput) {
        this.imageProcessorInput = imageProcessorInput;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        aboveJButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        updateJButton = new javax.swing.JButton();
        floatPercentJTextField = new javax.swing.JTextField();
        adjustFor16AboveJButton = new javax.swing.JButton();
        adjustFor16BelowJButton = new javax.swing.JButton();

        jLabel1.setText("Percent:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        aboveJButton.setText("Process");
        aboveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboveJButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Width:");

        jLabel3.setText("Height:");

        updateJButton.setText("Update");
        updateJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateJButtonActionPerformed(evt);
            }
        });

        floatPercentJTextField.setText("-1.000000");
        floatPercentJTextField.setMinimumSize(new java.awt.Dimension(120, 22));

        adjustFor16AboveJButton.setText("Adjust for 16 Above");
        adjustFor16AboveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjustFor16AboveJButtonActionPerformed(evt);
            }
        });

        adjustFor16BelowJButton.setText("Adjust for 16 Below");
        adjustFor16BelowJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjustFor16BelowJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(floatPercentJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adjustFor16AboveJButton)
                    .addComponent(adjustFor16BelowJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(updateJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aboveJButton)
                .addGap(0, 250, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(floatPercentJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(adjustFor16AboveJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adjustFor16BelowJButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aboveJButton)
                    .addComponent(updateJButton))
                .addContainerGap(175, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void aboveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboveJButtonActionPerformed
        this.process();
    }//GEN-LAST:event_aboveJButtonActionPerformed

    private void updateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateJButtonActionPerformed
        this.update();
    }//GEN-LAST:event_updateJButtonActionPerformed

    private void adjustFor16AboveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjustFor16AboveJButtonActionPerformed
        this.updateFor16Above();
    }//GEN-LAST:event_adjustFor16AboveJButtonActionPerformed

    private void adjustFor16BelowJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjustFor16BelowJButtonActionPerformed
        this.updateFor16Below();
    }//GEN-LAST:event_adjustFor16BelowJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboveJButton;
    private javax.swing.JButton adjustFor16AboveJButton;
    private javax.swing.JButton adjustFor16BelowJButton;
    private javax.swing.JTextField floatPercentJTextField;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton updateJButton;
    // End of variables declaration//GEN-END:variables
}
