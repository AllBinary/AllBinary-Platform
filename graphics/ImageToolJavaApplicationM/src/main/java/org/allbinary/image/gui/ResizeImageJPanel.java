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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 *
 * @author User
 */
public class ResizeImageJPanel extends JPanel
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

        this.jComboBox1.setModel(new DefaultComboBoxModel<>(numberStringArray));

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
                final LogUtil logUtil = LogUtil.getInstance();
                try {
                    final ImageUtil imageUtil = ImageUtil.getInstance();
                    final Integer percent = Integer.valueOf((String) ResizeImageJPanel.this.jComboBox1.getSelectedItem());
                    final Float percentAsFloat = Float.parseFloat(ResizeImageJPanel.this.floatPercentJTextField.getText());

                    final ImageProcessorInput imageProcessorInput = ResizeImageJPanel.this.getImageProcessorInput();
                    final File[] files = imageProcessorInput.getFiles();
                    
                    BufferedImage[] generatedBufferedImageArray = null;
                    if(percentAsFloat.intValue() != -1) {
                        generatedBufferedImageArray = 
                                imageUtil.createBufferedImage2(
                                        imageProcessorInput.getBufferedImageArray(), percentAsFloat, true);
                    } else if(percent.intValue() != -1) {
                        generatedBufferedImageArray = 
                                imageUtil.createBufferedImageForResize(
                                        imageProcessorInput.getBufferedImageArray(), percent, true);
                    } else {
                        final Integer width = Integer.valueOf((String) ResizeImageJPanel.this.jTextField1.getText());
                        final Integer height = Integer.valueOf((String) ResizeImageJPanel.this.jTextField2.getText());
                        generatedBufferedImageArray = 
                                imageUtil.createBufferedImage3(
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
                final LogUtil logUtil = LogUtil.getInstance();
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
                final LogUtil logUtil = LogUtil.getInstance();
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
                final LogUtil logUtil = LogUtil.getInstance();
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
        return this.imageProcessorInput;
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

        this.jLabel1 = new JLabel();
        this.jComboBox1 = new JComboBox<>();
        this.aboveJButton = new JButton();
        this.jTextField1 = new JTextField();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jTextField2 = new JTextField();
        this.updateJButton = new JButton();
        this.floatPercentJTextField = new JTextField();
        this.adjustFor16AboveJButton = new JButton();
        this.adjustFor16BelowJButton = new JButton();

        this.jLabel1.setText("Percent:");

        this.jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        this.aboveJButton.setText("Process");
        this.aboveJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                aboveJButtonActionPerformed(evt);
            }
        });

        this.jLabel2.setText("Width:");

        this.jLabel3.setText("Height:");

        this.updateJButton.setText("Update");
        this.updateJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateJButtonActionPerformed(evt);
            }
        });

        this.floatPercentJTextField.setText("-1.000000");
        this.floatPercentJTextField.setMinimumSize(new Dimension(120, 22));

        this.adjustFor16AboveJButton.setText("Adjust for 16 Above");
        this.adjustFor16AboveJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                adjustFor16AboveJButtonActionPerformed(evt);
            }
        });

        this.adjustFor16BelowJButton.setText("Adjust for 16 Below");
        this.adjustFor16BelowJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                adjustFor16BelowJButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(floatPercentJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(adjustFor16AboveJButton)
                    .addComponent(adjustFor16BelowJButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(updateJButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aboveJButton)
                .addGap(0, 250, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(floatPercentJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(adjustFor16AboveJButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(adjustFor16BelowJButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(aboveJButton)
                    .addComponent(updateJButton))
                .addContainerGap(175, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void aboveJButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_aboveJButtonActionPerformed
        this.process();
    }//GEN-LAST:event_aboveJButtonActionPerformed

    private void updateJButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_updateJButtonActionPerformed
        this.update();
    }//GEN-LAST:event_updateJButtonActionPerformed

    private void adjustFor16AboveJButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_adjustFor16AboveJButtonActionPerformed
        this.updateFor16Above();
    }//GEN-LAST:event_adjustFor16AboveJButtonActionPerformed

    private void adjustFor16BelowJButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_adjustFor16BelowJButtonActionPerformed
        this.updateFor16Below();
    }//GEN-LAST:event_adjustFor16BelowJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton aboveJButton;
    private JButton adjustFor16AboveJButton;
    private JButton adjustFor16BelowJButton;
    private JTextField floatPercentJTextField;
    private JComboBox<String> jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JButton updateJButton;
    // End of variables declaration//GEN-END:variables
}
