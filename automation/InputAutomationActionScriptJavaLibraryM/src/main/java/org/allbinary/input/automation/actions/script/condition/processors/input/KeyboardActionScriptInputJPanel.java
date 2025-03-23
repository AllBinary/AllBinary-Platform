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
package org.allbinary.input.automation.actions.script.condition.processors.input;

import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ComboBoxModel;

import org.allbinary.input.KeySingletonFactory;
import org.allbinary.input.automation.robot.InputRobot;
import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.input.automation.robot.TempInputRobotNames;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author  USER
 */
public class KeyboardActionScriptInputJPanel extends javax.swing.JPanel
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private KeyboardActionScriptInputInterface keyActionScriptInputInterface;
   
   /** Creates new form ColorAtActionJPanel */
   public KeyboardActionScriptInputJPanel(KeyboardActionScriptInputInterface keyActionScriptInputInterface)
   throws Exception
   {
      LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR));
      
      initComponents();
      
      this.keyActionScriptInputInterface = keyActionScriptInputInterface;
      
      DefaultComboBoxModel defaultComboBoxModel =
            new DefaultComboBoxModel();
      defaultComboBoxModel.addElement(TempInputRobotNames.DX_NAME);
      defaultComboBoxModel.addElement(InputRobot.NAME);
      
      this.inputAutomationTypeJComboBox.setModel(defaultComboBoxModel);
      //InputAutomationTypeDefaultComboBoxModelFactory.getInstance());
      this.keyNameJComboBox.setModel(
            KeyNameDefaultComboBoxModelFactory.getInstance());
      this.keyIntegerJComboBox.setModel(
            KeyCharDefaultComboBoxModelFactory.getInstance());
      
      if(this.keyActionScriptInputInterface.isNormal())
      {
         this.normalJRadioButton.setSelected(true);
         this.pressJRadioButton.setSelected(false);
         this.releaseJRadioButton.setSelected(false);
      }
      else
         if(this.keyActionScriptInputInterface.isPress())
         {
         this.normalJRadioButton.setSelected(false);
         this.pressJRadioButton.setSelected(true);
         this.releaseJRadioButton.setSelected(false);
         }
         else
            if(this.keyActionScriptInputInterface.isRelease())
            {
         this.normalJRadioButton.setSelected(false);
         this.pressJRadioButton.setSelected(false);
         this.releaseJRadioButton.setSelected(true);
            }
      
      this.set();
   }
   
   private void set()
   {
      ComboBoxModel inputTypeComboBoxModel =
            this.inputAutomationTypeJComboBox.getModel();
      
      inputTypeComboBoxModel.setSelectedItem(
            this.keyActionScriptInputInterface.getInputRobotInterface().getName());
      
      this.textJTextField.setText(this.keyActionScriptInputInterface.getText());
      
      this.delayJTextField.setText(Integer.toString(this.keyActionScriptInputInterface.getDelayBetweenKeys()));
      
      this.timeJTextField.setText(Integer.toString(this.keyActionScriptInputInterface.getTime()));
      
      this.keyActionScriptInputInterface.log();
   }
   
   private void updateInputType() throws Exception
   {
      ComboBoxModel comboBoxModel = this.inputAutomationTypeJComboBox.getModel();
      String selectedItem = (String) comboBoxModel.getSelectedItem();
      if (!StringValidationUtil.getInstance().isEmpty(selectedItem))
      {
         this.keyActionScriptInputInterface.setInputRobotInterface(
               InputRobotFactory.getInstance().get(selectedItem));
      }
   }
   
   private Integer getSelectedKey()
   {
      ComboBoxModel comboBoxModel = this.keyNameJComboBox.getModel();
      String selectedItem = (String) comboBoxModel.getSelectedItem();
      if (!StringValidationUtil.getInstance().isEmpty(selectedItem))
      {
         return (Integer) KeySingletonFactory.getHashtable().get(selectedItem);
      }
      return null;
   }
   
   private void updateKeys()
   {
      String text = this.textJTextField.getText();
      this.keyActionScriptInputInterface.setText(text);
   }
   
   private void update() throws Exception
   {
      this.updateInputType();
      
      this.updateKeys();
      
      this.keyActionScriptInputInterface.setTime(
            Integer.valueOf(this.timeJTextField.getText()).intValue());
      
      this.keyActionScriptInputInterface.log();
   }
   
   public javax.swing.JDialog getKeyActionJDialog()
   {
      return keyActionJDialog;
   }
   
   public void setKeyActionJDialog(javax.swing.JDialog keyActionJDialog)
   {
      this.keyActionJDialog = keyActionJDialog;
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
   private void initComponents()
   {
      keyActionJDialog = new javax.swing.JDialog();
      titleJLabel = new javax.swing.JLabel();
      addKeyJLabel = new javax.swing.JLabel();
      keyNameJComboBox = new javax.swing.JComboBox();
      timeJTextField = new javax.swing.JTextField();
      holdTimeJLabel = new javax.swing.JLabel();
      inputAutomationTypeJComboBox = new javax.swing.JComboBox();
      inputTypeJLabel = new javax.swing.JLabel();
      delayJTextField = new javax.swing.JTextField();
      delayBetweenJLabel = new javax.swing.JLabel();
      testJLabel = new javax.swing.JLabel();
      textJTextField = new javax.swing.JTextField();
      pressJRadioButton = new javax.swing.JRadioButton();
      releaseJRadioButton = new javax.swing.JRadioButton();
      normalJRadioButton = new javax.swing.JRadioButton();
      simultaneousJRadioButton = new javax.swing.JRadioButton();
      sequenceJRadioButton = new javax.swing.JRadioButton();
      jPanel1 = new javax.swing.JPanel();
      okJButton = new javax.swing.JButton();
      keyIntegerJComboBox = new javax.swing.JComboBox();
      keyActionJButton = new javax.swing.JButton();
      jLabel1 = new javax.swing.JLabel();

      keyActionJDialog.setMinimumSize(new java.awt.Dimension(255, 280));
      titleJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      titleJLabel.setText("Keyboard Input Options");

      addKeyJLabel.setText("Add Key:");

      keyNameJComboBox.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            keyNameJComboBoxActionPerformed(evt);
         }
      });

      timeJTextField.setText("0");

      holdTimeJLabel.setText("Hold Time (ms):");

      inputTypeJLabel.setText("Input Type:");

      delayJTextField.setText("0");

      delayBetweenJLabel.setText("Delay Between Keys (ms):");

      testJLabel.setText("Text:");

      textJTextField.setText("0");

      pressJRadioButton.setText("Press");
      pressJRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
      pressJRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
      pressJRadioButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            pressJRadioButtonActionPerformed(evt);
         }
      });

      releaseJRadioButton.setText("Release");
      releaseJRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
      releaseJRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
      releaseJRadioButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            releaseJRadioButtonActionPerformed(evt);
         }
      });

      normalJRadioButton.setSelected(true);
      normalJRadioButton.setText("Normal");
      normalJRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
      normalJRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
      normalJRadioButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            normalJRadioButtonActionPerformed(evt);
         }
      });

      simultaneousJRadioButton.setSelected(true);
      simultaneousJRadioButton.setText("Simultaneous");
      simultaneousJRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
      simultaneousJRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
      simultaneousJRadioButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            simultaneousJRadioButtonActionPerformed(evt);
         }
      });

      sequenceJRadioButton.setText("Sequence");
      sequenceJRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
      sequenceJRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
      sequenceJRadioButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            sequenceJRadioButtonActionPerformed(evt);
         }
      });

      okJButton.setText("OK");
      okJButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
      okJButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            okJButtonActionPerformed(evt);
         }
      });

      jPanel1.add(okJButton);

      keyIntegerJComboBox.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            keyIntegerJComboBoxActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout keyActionJDialogLayout = new javax.swing.GroupLayout(keyActionJDialog.getContentPane());
      keyActionJDialog.getContentPane().setLayout(keyActionJDialogLayout);
      keyActionJDialogLayout.setHorizontalGroup(
         keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(keyActionJDialogLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(keyActionJDialogLayout.createSequentialGroup()
                  .addGroup(keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                     .addComponent(addKeyJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(inputTypeJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(inputAutomationTypeJComboBox, 0, 148, Short.MAX_VALUE)
                     .addComponent(textJTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                     .addGroup(keyActionJDialogLayout.createSequentialGroup()
                        .addComponent(keyNameJComboBox, 0, 101, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keyIntegerJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
               .addComponent(testJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(keyActionJDialogLayout.createSequentialGroup()
                  .addComponent(normalJRadioButton)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(pressJRadioButton)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(releaseJRadioButton)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, keyActionJDialogLayout.createSequentialGroup()
                  .addComponent(holdTimeJLabel)
                  .addGap(22, 22, 22)
                  .addComponent(timeJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
               .addGroup(keyActionJDialogLayout.createSequentialGroup()
                  .addComponent(delayBetweenJLabel)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                  .addComponent(delayJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(keyActionJDialogLayout.createSequentialGroup()
                  .addComponent(simultaneousJRadioButton)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(sequenceJRadioButton)))
            .addContainerGap())
         .addComponent(titleJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
         .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
      );
      keyActionJDialogLayout.setVerticalGroup(
         keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(keyActionJDialogLayout.createSequentialGroup()
            .addComponent(titleJLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(inputTypeJLabel)
               .addComponent(inputAutomationTypeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(addKeyJLabel)
               .addComponent(keyIntegerJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(keyNameJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(testJLabel)
               .addComponent(textJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(pressJRadioButton)
               .addComponent(normalJRadioButton)
               .addComponent(releaseJRadioButton))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(holdTimeJLabel)
               .addComponent(timeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(delayBetweenJLabel)
               .addComponent(delayJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(simultaneousJRadioButton)
               .addComponent(sequenceJRadioButton))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
      );

      setMinimumSize(new java.awt.Dimension(100, 0));
      keyActionJButton.setText("Edit");
      keyActionJButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            keyActionJButtonActionPerformed(evt);
         }
      });

      jLabel1.setText("Key:");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
            .addComponent(keyActionJButton))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel1)
            .addComponent(keyActionJButton))
      );
   }// </editor-fold>//GEN-END:initComponents
   
   private void prependText(Integer integer)
   {
      if(integer != null)
      {
         String newKey = "&#" + integer.toString() + ";";
         this.textJTextField.setText(newKey + this.textJTextField.getText());
      }
   }
   
   private void keyIntegerJComboBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_keyIntegerJComboBoxActionPerformed
   {//GEN-HEADEREND:event_keyIntegerJComboBoxActionPerformed
      
      this.keyIntegerJComboBox.getSelectedIndex();
      this.keyNameJComboBox.setSelectedIndex(this.keyIntegerJComboBox.getSelectedIndex());
      
   }//GEN-LAST:event_keyIntegerJComboBoxActionPerformed
   
   private void sequenceJRadioButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_sequenceJRadioButtonActionPerformed
   {//GEN-HEADEREND:event_sequenceJRadioButtonActionPerformed
      if(this.sequenceJRadioButton.isSelected())
      {
         this.delayJTextField.setText("1");
         this.simultaneousJRadioButton.setSelected(false);
      }
   }//GEN-LAST:event_sequenceJRadioButtonActionPerformed
   
   private void simultaneousJRadioButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_simultaneousJRadioButtonActionPerformed
   {//GEN-HEADEREND:event_simultaneousJRadioButtonActionPerformed
      if(this.simultaneousJRadioButton.isSelected())
      {
         this.delayJTextField.setText("0");
         this.sequenceJRadioButton.setSelected(false);
      }
   }//GEN-LAST:event_simultaneousJRadioButtonActionPerformed
   
   private void keyNameJComboBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_keyNameJComboBoxActionPerformed
   {//GEN-HEADEREND:event_keyNameJComboBoxActionPerformed
         Integer integer = getSelectedKey();
         this.prependText(integer);
         this.keyIntegerJComboBox.setSelectedIndex(this.keyNameJComboBox.getSelectedIndex());
   }//GEN-LAST:event_keyNameJComboBoxActionPerformed
   
    private void okJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okJButtonActionPerformed
    {//GEN-HEADEREND:event_okJButtonActionPerformed
       
       try
       {
          this.update();
          this.getKeyActionJDialog().setVisible(false);
       }
       catch(Exception e)
       {
          LogUtil.put(LogFactory.getInstance("Exception", this, "okButtonActionPerformed", e));
       }
       
    }//GEN-LAST:event_okJButtonActionPerformed
    
    private void keyActionJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_keyActionJButtonActionPerformed
    {//GEN-HEADEREND:event_keyActionJButtonActionPerformed
       this.getKeyActionJDialog().setVisible(true);
    }//GEN-LAST:event_keyActionJButtonActionPerformed
    
    private void normalJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalJRadioButtonActionPerformed
       
       if(this.normalJRadioButton.isSelected())
       {
          this.keyActionScriptInputInterface.setNormal();
          this.pressJRadioButton.setSelected(false);
          this.releaseJRadioButton.setSelected(false);
       }
//GEN-LAST:event_normalJRadioButtonActionPerformed
    }
    
    private void releaseJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseJRadioButtonActionPerformed
       
       if(this.releaseJRadioButton.isSelected())
       {
          this.keyActionScriptInputInterface.setRelease(true);
          this.keyActionScriptInputInterface.setPress(false);
          this.pressJRadioButton.setSelected(false);
          this.normalJRadioButton.setSelected(false);
       }
       
    }//GEN-LAST:event_releaseJRadioButtonActionPerformed
    
    private void pressJRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pressJRadioButtonActionPerformed
       
       if(this.pressJRadioButton.isSelected())
       {
          this.keyActionScriptInputInterface.setPress(true);
          this.keyActionScriptInputInterface.setRelease(false);
          this.releaseJRadioButton.setSelected(false);
          this.normalJRadioButton.setSelected(false);
       }
       
    }//GEN-LAST:event_pressJRadioButtonActionPerformed
    
    
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JLabel addKeyJLabel;
   private javax.swing.JLabel delayBetweenJLabel;
   private javax.swing.JTextField delayJTextField;
   private javax.swing.JLabel holdTimeJLabel;
   private javax.swing.JComboBox inputAutomationTypeJComboBox;
   private javax.swing.JLabel inputTypeJLabel;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JButton keyActionJButton;
   private javax.swing.JDialog keyActionJDialog;
   private javax.swing.JComboBox keyIntegerJComboBox;
   private javax.swing.JComboBox keyNameJComboBox;
   private javax.swing.JRadioButton normalJRadioButton;
   private javax.swing.JButton okJButton;
   private javax.swing.JRadioButton pressJRadioButton;
   private javax.swing.JRadioButton releaseJRadioButton;
   private javax.swing.JRadioButton sequenceJRadioButton;
   private javax.swing.JRadioButton simultaneousJRadioButton;
   private javax.swing.JLabel testJLabel;
   private javax.swing.JTextField textJTextField;
   private javax.swing.JTextField timeJTextField;
   private javax.swing.JLabel titleJLabel;
   // End of variables declaration//GEN-END:variables
   
}
