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
import org.allbinary.string.CommonPhoneStrings;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class ImageUnifierJPanel extends JPanel
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
      this.initComponents();

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
         this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.INIT, e);
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

      double averageRatio = this.imagesRatioUtil.getAverage(
         tempBufferedImageArray, totalImages);

      String averageRatioString = new Double(averageRatio).toString();

      if (averageRatioString.length() > 4)
      {
         averageRatioString = averageRatioString.substring(0, 4);
      }

      this.avgRatioJTextField.setText(averageRatioString);

      //new BasicTextJDialog("Your image ratio average does not match your selected").setVisible(false);

      this.fudgeItJButton.setEnabled(false);
      if (!this.imagesRatioUtil.isEqual(tempBufferedImageArray, totalImages))
      {
         //new BasicTextJDialog("Your images have different ratios").setVisible(false);
         if (this.isImageFillIn)
         {
            this.fudgeItJButton.setEnabled(true);
         }
      }

      this.updateImage(tempBufferedImageArray);
      
      }
      catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "updateImage", e);
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

         final double averageRatio = this.imagesRatioUtil.getAverage(
            tempBufferedImageArray, totalImages);

         final BufferedImage[] fudgedBufferedImageArray =
            imagesRatioUtil.fudge(
            tempBufferedImageArray,
            totalImages, averageRatio);

         this.updateImage(fudgedBufferedImageArray);
      }
      catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "updateImageWithFudgedImages", e);
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

         this.imageUnifierProperties.setRows(new Integer(this.rowsJTextField.getText()));
         this.imageUnifierProperties.setColumns(new Integer(this.columnsJTextField.getText()));
         final ImageUnifierCell imageUnifierCell = new ImageUnifierCell(
            new Integer(this.cellWidthJTextField.getText()),
            new Integer(this.cellHeightJTextField.getText()));
         this.imageUnifierProperties.setImageUnifierCell(imageUnifierCell);

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

        final CommonPhoneStrings commonPhoneStrings = CommonPhoneStrings.getInstance();
        
        this.jScrollPane1 = new JScrollPane();
        this.imageJList = new JList();
        this.jScrollPane2 = new JScrollPane();
        this.jPanel1 = new JPanel();
        this.jButton1 = new JButton();
        this.upJButton = new JButton();
        this.downJButton = new JButton();
        this.fudgeItJButton = new JButton();
        this.jLabel2 = new JLabel();
        this.columnsJTextField = new JTextField();
        this.jLabel1 = new JLabel();
        this.rowsJTextField = new JTextField();
        this.jLabel3 = new JLabel();
        this.cellWidthJTextField = new JTextField();
        this.jLabel4 = new JLabel();
        this.cellHeightJTextField = new JTextField();
        this.jLabel5 = new JLabel();
        this.cellRatioJTextField = new JTextField();
        this.jLabel6 = new JLabel();
        this.avgRatioJTextField = new JTextField();
        this.jButton2 = new JButton();
        this.jButtonOrder = new JButton();

        setMinimumSize(new Dimension(500, 375));
        setPreferredSize(new Dimension(0, 0));

        this.imageJList.setMinimumSize(new Dimension(50, 0));
        this.jScrollPane1.setViewportView(this.imageJList);

        this.jButton1.setText("Save");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        this.upJButton.setText("Up");
        this.upJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                upJButtonActionPerformed(evt);
            }
        });

        this.downJButton.setText("Down");
        this.downJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                downJButtonActionPerformed(evt);
            }
        });

        this.fudgeItJButton.setText("Fudge It!");
        this.fudgeItJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fudgeItJButtonActionPerformed(evt);
            }
        });

        this.jLabel2.setText("Columns:");

        this.columnsJTextField.setText(commonPhoneStrings.TWO);
        this.columnsJTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                columnsJTextFieldActionPerformed(evt);
            }
        });
        this.columnsJTextField.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                columnsJTextFieldPropertyChange(evt);
            }
        });
        this.columnsJTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                columnsJTextFieldKeyPressed(evt);
            }
            public void keyTyped(KeyEvent evt) {
                columnsJTextFieldKeyTyped(evt);
            }
        });

        this.jLabel1.setText("Rows:");

        this.rowsJTextField.setText(commonPhoneStrings.TWO);
        this.rowsJTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rowsJTextFieldActionPerformed(evt);
            }
        });

        this.jLabel3.setText("Cell Width:");

        this.cellWidthJTextField.setText("200");
        this.cellWidthJTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cellWidthJTextFieldActionPerformed(evt);
            }
        });

        this.jLabel4.setText("Cell Height:");

        this.cellHeightJTextField.setText("150");
        this.cellHeightJTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cellHeightJTextFieldActionPerformed(evt);
            }
        });

        this.jLabel5.setText("Cell Ratio:");

        this.cellRatioJTextField.setEditable(false);

        this.jLabel6.setText("Avg Image Ratio:");

        this.avgRatioJTextField.setEditable(false);

        this.jButton2.setText(this.commonStrings.UPDATE);
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        this.jButtonOrder.setText("Order");
        this.jButtonOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonOrderActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(upJButton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(downJButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                    .addComponent(fudgeItJButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 99, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 96, GroupLayout.PREFERRED_SIZE))
                    .addComponent(avgRatioJTextField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jLabel6, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(cellRatioJTextField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jLabel5, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(cellHeightJTextField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(cellWidthJTextField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(jButton2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(rowsJTextField, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(columnsJTextField, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                .addGap(1, 1, 1))))
                    .addComponent(jButtonOrder, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(upJButton)
                    .addComponent(downJButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOrder)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fudgeItJButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(columnsJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rowsJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cellWidthJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cellHeightJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cellRatioJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel6)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(avgRatioJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, GroupLayout.Alignment.LEADING, 0, 445, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
       this.updateOnPropertiesChange();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void columnsJTextFieldKeyPressed(KeyEvent evt)//GEN-FIRST:event_columnsJTextFieldKeyPressed
    {//GEN-HEADEREND:event_columnsJTextFieldKeyPressed
    }//GEN-LAST:event_columnsJTextFieldKeyPressed

    private void columnsJTextFieldKeyTyped(KeyEvent evt)//GEN-FIRST:event_columnsJTextFieldKeyTyped
    {//GEN-HEADEREND:event_columnsJTextFieldKeyTyped
    }//GEN-LAST:event_columnsJTextFieldKeyTyped

    private void columnsJTextFieldPropertyChange(PropertyChangeEvent evt)//GEN-FIRST:event_columnsJTextFieldPropertyChange
    {//GEN-HEADEREND:event_columnsJTextFieldPropertyChange
    }//GEN-LAST:event_columnsJTextFieldPropertyChange

    private void fudgeItJButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_fudgeItJButtonActionPerformed
    {//GEN-HEADEREND:event_fudgeItJButtonActionPerformed
       this.updateImageWithFudgedImages();
    }//GEN-LAST:event_fudgeItJButtonActionPerformed

    private void cellHeightJTextFieldActionPerformed(ActionEvent evt)//GEN-FIRST:event_cellHeightJTextFieldActionPerformed
    {//GEN-HEADEREND:event_cellHeightJTextFieldActionPerformed
       this.updateOnPropertiesChange();
    }//GEN-LAST:event_cellHeightJTextFieldActionPerformed

    private void cellWidthJTextFieldActionPerformed(ActionEvent evt)//GEN-FIRST:event_cellWidthJTextFieldActionPerformed
    {//GEN-HEADEREND:event_cellWidthJTextFieldActionPerformed
       this.updateOnPropertiesChange();
    }//GEN-LAST:event_cellWidthJTextFieldActionPerformed

    private void columnsJTextFieldActionPerformed(ActionEvent evt)//GEN-FIRST:event_columnsJTextFieldActionPerformed
    {//GEN-HEADEREND:event_columnsJTextFieldActionPerformed
       this.updateOnPropertiesChange();
    }//GEN-LAST:event_columnsJTextFieldActionPerformed

    private void rowsJTextFieldActionPerformed(ActionEvent evt)//GEN-FIRST:event_rowsJTextFieldActionPerformed
    {//GEN-HEADEREND:event_rowsJTextFieldActionPerformed
       this.updateOnPropertiesChange();
    }//GEN-LAST:event_rowsJTextFieldActionPerformed

    private void jButton1ActionPerformed(ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
// TODO add your handling code here:
      try
      {
          final File[] fileArray = this.imageProcessorInput.getFiles();
          
          final File file = fileArray[0];
          String filePath = file.getAbsolutePath();

          final int extensionIndex = filePath.indexOf(this.imageStrings.PNG_EXTENSION);

          filePath = new StringMaker().append(filePath.substring(0, extensionIndex)).append(CommonSeps.getInstance().UNDERSCORE).appendint(this.imageUnifierProperties.getColumns()).append("_By_").appendint(this.imageUnifierProperties.getRows()).append("_Unified").append(this.imageStrings.PNG_EXTENSION).toString();

          this.logUtil.putF("New File Path: " + filePath, this, StringUtil.getInstance().EMPTY_STRING);
          
          final File outputFile = new File(filePath);
          ImagePersistanceUtil.getInstance().saveWithBatik(FileWrapperUtil.wrapFile(outputFile), this.result);
      }
      catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "jButton1ActionPerformed", e);
      }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void downJButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_downJButtonActionPerformed
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

    private void upJButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_upJButtonActionPerformed
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

    private void jButtonOrderActionPerformed(ActionEvent evt)//GEN-FIRST:event_jButtonOrderActionPerformed
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
    private JTextField avgRatioJTextField;
    private JTextField cellHeightJTextField;
    private JTextField cellRatioJTextField;
    private JTextField cellWidthJTextField;
    private JTextField columnsJTextField;
    private JButton downJButton;
    private JButton fudgeItJButton;
    private JList imageJList;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButtonOrder;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextField rowsJTextField;
    private JButton upJButton;
    // End of variables declaration//GEN-END:variables
   public ImageProcessorInput getImageProcessorInput()
   {
      return this.imageProcessorInput;
   }

   public void setImageProcessorInput(ImageProcessorInput imageProcessorInput)
   {
      this.imageProcessorInput = imageProcessorInput;
      this.init();
   }
}
