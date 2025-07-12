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

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.file.FileWrapperUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.media.image.ImagePersistanceUtil;
import org.allbinary.media.image.ImageProcessorInput;
import org.allbinary.media.image.ImageProcessorInputCompositeInterface;
import org.allbinary.media.image.ImageStrings;
import org.allbinary.media.image.ImageUnifierCell;
import org.allbinary.media.image.ImageUnifierProperties;
import org.allbinary.media.image.ImageUnifierUtil;
import org.allbinary.media.image.ImageUtil;
import org.allbinary.media.image.ImagesRatioUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;

public class ImageUnifierJPanel extends javax.swing.JPanel
   implements ImageProcessorInputCompositeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final ImageStrings imageStrings = ImageStrings.getInstance();
    private final ImagesRatioUtil imagesRatioUtil = ImagesRatioUtil.getInstance();
    
   private ImageProcessorInput imageProcessorInput;
   private ImageIconUnique[] icon;
   private ImageUnifierProperties imageUnifierProperties;
   private BufferedImage result;
   private boolean isImageFillIn = true;

   public ImageUnifierJPanel(final ImageProcessorInput imageProcessorInput)
      throws Exception
   {
      initComponents();

      this.setImageProcessorInput(imageProcessorInput);
   }

   public void init()
   {
      try
      {
         final ImageProcessorInput imageProcessorInput = this.getImageProcessorInput();
         
         final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();

         this.icon = new ImageIconUnique[bufferedImageArray.length];

         final DefaultListModel defaultListModel = new DefaultListModel();

         for (int index = 0; index < bufferedImageArray.length; index++)
         {
            final double width = bufferedImageArray[index].getWidth();
            final double height = bufferedImageArray[index].getHeight();
            final int newWidth = 52;
            final double oldRatio = width / height;
            final int newHeight = (int) (newWidth / oldRatio);

            final BufferedImage iconBufferedImage =
               ImageUtil.getInstance().createBufferedImage(
               bufferedImageArray[index],
               newWidth, newHeight, true);

            this.icon[index] = new ImageIconUnique(iconBufferedImage, index);
            defaultListModel.addElement(this.icon[index]);
         }

         this.imageJList.setModel(defaultListModel);
         this.updateOnPropertiesChange();

      }
      catch (Exception e)
      {
         logUtil.put(commonStrings.EXCEPTION, this, this.commonStrings.INIT, e);
      }

   }

   private void updateImage()
   {
      try {

      final BufferedImage[] bufferedImageArray =
         this.getImageProcessorInput().getBufferedImageArray();

      final BufferedImage[] tempBufferedImageArray =
         new BufferedImage[bufferedImageArray.length];

      for (int index = 0; index < bufferedImageArray.length; index++)
      {
         final ImageIconUnique indexedImageIcon = (ImageIconUnique) 
            this.imageJList.getModel().getElementAt(index);

         final BufferedImage bufferedImage =
               ImageUtil.getInstance().createBufferedImage(
               bufferedImageArray[indexedImageIcon.getId()],
               this.imageUnifierProperties.getImageUnifierCell().getWidth(), 
               this.imageUnifierProperties.getImageUnifierCell().getHeight(), false);
         
         tempBufferedImageArray[index] = bufferedImage;
      }

      int totalImages =
         this.imageUnifierProperties.getRows() *
         this.imageUnifierProperties.getColumns();

      double averageRatio = imagesRatioUtil.getAverage(
         tempBufferedImageArray, totalImages);

      String averageRatioString = new Double(averageRatio).toString();

      if (averageRatioString.length() > 4)
      {
         averageRatioString = averageRatioString.substring(0, 4);
      }

      this.avgRatioJTextField.setText(averageRatioString);

      //new BasicTextJDialog("Your image ratio average does not match your selected").setVisible(false);

      this.fudgeItJButton.setEnabled(false);
      if (!imagesRatioUtil.isEqual(tempBufferedImageArray, totalImages))
      {
         //new BasicTextJDialog("Your images have different ratios").setVisible(false);
         if (isImageFillIn)
         {
            this.fudgeItJButton.setEnabled(true);
         }
      }

      this.updateImage(tempBufferedImageArray);
      
      }
      catch (Exception e)
      {
         logUtil.put(commonStrings.EXCEPTION, this, "updateImage", e);
      }

   }

   private void updateImageWithFudgedImages()
   {
      try
      {
         final BufferedImage[] bufferedImageArray =
            this.getImageProcessorInput().getBufferedImageArray();

         final BufferedImage[] tempBufferedImageArray =
            new BufferedImage[bufferedImageArray.length];

         for (int index = 0; index < bufferedImageArray.length; index++)
         {
            final ImageIconUnique indexedImageIcon = (ImageIconUnique) 
               this.imageJList.getModel().getElementAt(index);

            final BufferedImage bufferedImage =
               ImageUtil.getInstance().createBufferedImage(
               bufferedImageArray[indexedImageIcon.getId()],
               this.imageUnifierProperties.getImageUnifierCell().getWidth(), 
               this.imageUnifierProperties.getImageUnifierCell().getHeight(), false);
         
            tempBufferedImageArray[index] = bufferedImage;
         }

         final int totalImages =
            this.imageUnifierProperties.getRows() *
            this.imageUnifierProperties.getColumns();

         final double averageRatio = imagesRatioUtil.getAverage(
            tempBufferedImageArray, totalImages);

         final BufferedImage[] fudgedBufferedImageArray =
            imagesRatioUtil.fudge(
            tempBufferedImageArray,
            totalImages, averageRatio);

         this.updateImage(fudgedBufferedImageArray);
      }
      catch (Exception e)
      {
         logUtil.put(commonStrings.EXCEPTION, this, "updateImageWithFudgedImages", e);
      }
   }

   private void updateImage(final BufferedImage[] tempBufferedImageArray)
   {
      this.result = ImageUnifierUtil.getInstance().getImage(
         tempBufferedImageArray, this.imageUnifierProperties);

      final Icon image = new ImageIcon(this.result);
      final JLabel label = new JLabel(image);
      this.jScrollPane2.getViewport().add(label);

      this.updateUI();
   }

   private void updateOnPropertiesChange()
   {
      if (this.rowsJTextField.getText().length() > 0 &&
         this.columnsJTextField.getText().length() > 0 &&
         this.cellWidthJTextField.getText().length() > 0 &&
         this.cellHeightJTextField.getText().length() > 0)
      {
         this.imageUnifierProperties = new ImageUnifierProperties();

         imageUnifierProperties.setRows(new Integer(this.rowsJTextField.getText()));
         imageUnifierProperties.setColumns(new Integer(this.columnsJTextField.getText()));
         final ImageUnifierCell imageUnifierCell = new ImageUnifierCell(
            new Integer(this.cellWidthJTextField.getText()),
            new Integer(this.cellHeightJTextField.getText()));
         imageUnifierProperties.setImageUnifierCell(imageUnifierCell);

         final double cellRatio = ((double) imageUnifierCell.getWidth() / imageUnifierCell.getHeight());

         String cellRatioString = new Double(cellRatio).toString();
         if (cellRatioString.length() > 4)
         {
            cellRatioString = cellRatioString.substring(0, 4);
         }

         this.cellRatioJTextField.setText(cellRatioString);

         this.updateImage();
      }
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        imageJList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        upJButton = new javax.swing.JButton();
        downJButton = new javax.swing.JButton();
        fudgeItJButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        columnsJTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rowsJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cellWidthJTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cellHeightJTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cellRatioJTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        avgRatioJTextField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButtonOrder = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(500, 375));
        setPreferredSize(new java.awt.Dimension(0, 0));

        imageJList.setMinimumSize(new java.awt.Dimension(50, 0));
        jScrollPane1.setViewportView(imageJList);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        upJButton.setText("Up");
        upJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upJButtonActionPerformed(evt);
            }
        });

        downJButton.setText("Down");
        downJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downJButtonActionPerformed(evt);
            }
        });

        fudgeItJButton.setText("Fudge It!");
        fudgeItJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fudgeItJButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Columns:");

        columnsJTextField.setText("2");
        columnsJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                columnsJTextFieldActionPerformed(evt);
            }
        });
        columnsJTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                columnsJTextFieldPropertyChange(evt);
            }
        });
        columnsJTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                columnsJTextFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                columnsJTextFieldKeyTyped(evt);
            }
        });

        jLabel1.setText("Rows:");

        rowsJTextField.setText("2");
        rowsJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rowsJTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Cell Width:");

        cellWidthJTextField.setText("200");
        cellWidthJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cellWidthJTextFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Cell Height:");

        cellHeightJTextField.setText("150");
        cellHeightJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cellHeightJTextFieldActionPerformed(evt);
            }
        });

        jLabel5.setText("Cell Ratio:");

        cellRatioJTextField.setEditable(false);

        jLabel6.setText("Avg Image Ratio:");

        avgRatioJTextField.setEditable(false);

        jButton2.setText(commonStrings.UPDATE);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonOrder.setText("Order");
        jButtonOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(upJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(downJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fudgeItJButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(avgRatioJTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(cellRatioJTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(cellHeightJTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(cellWidthJTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rowsJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(columnsJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                .addGap(1, 1, 1))))
                    .addComponent(jButtonOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upJButton)
                    .addComponent(downJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOrder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fudgeItJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(columnsJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rowsJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cellWidthJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cellHeightJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cellRatioJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(avgRatioJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, 0, 445, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
       this.updateOnPropertiesChange();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void columnsJTextFieldKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_columnsJTextFieldKeyPressed
    {//GEN-HEADEREND:event_columnsJTextFieldKeyPressed
    }//GEN-LAST:event_columnsJTextFieldKeyPressed

    private void columnsJTextFieldKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_columnsJTextFieldKeyTyped
    {//GEN-HEADEREND:event_columnsJTextFieldKeyTyped
    }//GEN-LAST:event_columnsJTextFieldKeyTyped

    private void columnsJTextFieldPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_columnsJTextFieldPropertyChange
    {//GEN-HEADEREND:event_columnsJTextFieldPropertyChange
    }//GEN-LAST:event_columnsJTextFieldPropertyChange

    private void fudgeItJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_fudgeItJButtonActionPerformed
    {//GEN-HEADEREND:event_fudgeItJButtonActionPerformed
       this.updateImageWithFudgedImages();
    }//GEN-LAST:event_fudgeItJButtonActionPerformed

    private void cellHeightJTextFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cellHeightJTextFieldActionPerformed
    {//GEN-HEADEREND:event_cellHeightJTextFieldActionPerformed
       this.updateOnPropertiesChange();
    }//GEN-LAST:event_cellHeightJTextFieldActionPerformed

    private void cellWidthJTextFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cellWidthJTextFieldActionPerformed
    {//GEN-HEADEREND:event_cellWidthJTextFieldActionPerformed
       this.updateOnPropertiesChange();
    }//GEN-LAST:event_cellWidthJTextFieldActionPerformed

    private void columnsJTextFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_columnsJTextFieldActionPerformed
    {//GEN-HEADEREND:event_columnsJTextFieldActionPerformed
       this.updateOnPropertiesChange();
    }//GEN-LAST:event_columnsJTextFieldActionPerformed

    private void rowsJTextFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_rowsJTextFieldActionPerformed
    {//GEN-HEADEREND:event_rowsJTextFieldActionPerformed
       this.updateOnPropertiesChange();
    }//GEN-LAST:event_rowsJTextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
// TODO add your handling code here:
      try
      {
          final File[] fileArray = this.imageProcessorInput.getFiles();
          
          final File file = fileArray[0];
          String filePath = file.getAbsolutePath();

          final int extensionIndex = filePath.indexOf(imageStrings.PNG_EXTENSION);

          filePath = new StringMaker().append(filePath.substring(0, extensionIndex)).append(CommonSeps.getInstance().UNDERSCORE).append(this.imageUnifierProperties.getColumns()).append("_By_").append(this.imageUnifierProperties.getRows()).append("_Unified").append(imageStrings.PNG_EXTENSION).toString();

          logUtil.put("New File Path: " + filePath, this, StringUtil.getInstance().EMPTY_STRING);
          
          final File outputFile = new File(filePath);
          ImagePersistanceUtil.getInstance().saveWithBatik(FileWrapperUtil.wrapFile(outputFile), this.result);
      }
      catch (Exception e)
      {
         logUtil.put(commonStrings.EXCEPTION, this, "jButton1ActionPerformed", e);
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void downJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_downJButtonActionPerformed
    {//GEN-HEADEREND:event_downJButtonActionPerformed
// TODO add your handling code here:

       int index = this.imageJList.getSelectedIndex();
       DefaultListModel defaultListModel = (DefaultListModel) this.imageJList.getModel();

       if (index >= 0 && index + 1 < defaultListModel.size())
       {
          ImageIconUnique indexedImageIcon = (ImageIconUnique) defaultListModel.remove(index);
          defaultListModel.insertElementAt(indexedImageIcon, index + 1);
          this.imageJList.setSelectedIndex(index + 1);

          this.updateImage();
       }

    }//GEN-LAST:event_downJButtonActionPerformed

    private void upJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_upJButtonActionPerformed
    {//GEN-HEADEREND:event_upJButtonActionPerformed
// TODO add your handling code here:

       int index = this.imageJList.getSelectedIndex();

       if (index > 0)
       {
          DefaultListModel defaultListModel = (DefaultListModel) this.imageJList.getModel();
          ImageIconUnique indexedImageIcon = (ImageIconUnique) defaultListModel.remove(index);
          defaultListModel.insertElementAt(indexedImageIcon, index - 1);
          this.imageJList.setSelectedIndex(index - 1);
          this.updateImage();
       }

    }//GEN-LAST:event_upJButtonActionPerformed

    private void jButtonOrderActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonOrderActionPerformed
    {//GEN-HEADEREND:event_jButtonOrderActionPerformed

        DefaultListModel defaultListModel = (DefaultListModel) this.imageJList.getModel();

        int size = defaultListModel.size();
        int last = size - 1;
        for(int index = last; index >= 0; index--)
        {
            ImageIconUnique indexedImageIcon =
                (ImageIconUnique) defaultListModel.remove(last);
            defaultListModel.insertElementAt(indexedImageIcon, last - index);
        }

        if(size > 0)
        {
            this.imageJList.setSelectedIndex(0);
            this.updateImage();
        }
    }//GEN-LAST:event_jButtonOrderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField avgRatioJTextField;
    private javax.swing.JTextField cellHeightJTextField;
    private javax.swing.JTextField cellRatioJTextField;
    private javax.swing.JTextField cellWidthJTextField;
    private javax.swing.JTextField columnsJTextField;
    private javax.swing.JButton downJButton;
    private javax.swing.JButton fudgeItJButton;
    private javax.swing.JList imageJList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField rowsJTextField;
    private javax.swing.JButton upJButton;
    // End of variables declaration//GEN-END:variables
   public ImageProcessorInput getImageProcessorInput()
   {
      return imageProcessorInput;
   }

   public void setImageProcessorInput(ImageProcessorInput imageProcessorInput)
   {
      this.imageProcessorInput = imageProcessorInput;
      this.init();
   }
}
