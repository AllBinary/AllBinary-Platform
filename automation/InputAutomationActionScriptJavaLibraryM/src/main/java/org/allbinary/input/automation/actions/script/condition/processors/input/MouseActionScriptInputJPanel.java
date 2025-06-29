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

import java.awt.event.InputEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import org.allbinary.input.automation.robot.InputRobot;
import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.TempInputRobotNames;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringValidationUtil;

/**
 *
 * @author  USER
 */
public class MouseActionScriptInputJPanel
      extends javax.swing.JPanel
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private MouseActionScriptInputInterface mouseActionScriptInputInterface;
   
   /** Creates new form ColorAtActionJPanel */
   public MouseActionScriptInputJPanel(
         final MouseActionScriptInputInterface mouseActionScriptInputInterface)
         throws Exception
   {
      LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + mouseActionScriptInputInterface, this, commonStrings.CONSTRUCTOR));
      
      initComponents();
      
      this.mouseActionScriptInputInterface = mouseActionScriptInputInterface;
      
      final DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
      defaultComboBoxModel.addElement(TempInputRobotNames.LOW_NAME);
      defaultComboBoxModel.addElement(TempInputRobotNames.SYS_NAME);
      defaultComboBoxModel.addElement(InputRobot.NAME);
      
      this.inputAutomationTypeJComboBox.setModel(
            defaultComboBoxModel);
      //InputAutomationTypeDefaultComboBoxModelFactory.getInstance());
      
      this.set();
   }
   
   private void set()
   {
      final ComboBoxModel inputTypeComboBoxModel = this.inputAutomationTypeJComboBox.getModel();
      
      inputTypeComboBoxModel.setSelectedItem(this.mouseActionScriptInputInterface.getInputRobotInterface().getName());
      
      if((this.mouseActionScriptInputInterface.getButtonClicks() &
            InputEvent.BUTTON1_MASK) != 0)
      {
         this.button1JCheckBox.setSelected(true);
      }
      else
      {
         this.button1JCheckBox.setSelected(false);
      }
      
      if((this.mouseActionScriptInputInterface.getButtonClicks() &
            InputEvent.BUTTON2_MASK) != 0)
      {
         this.button2JCheckBox.setSelected(true);
      }
      else
      {
         this.button2JCheckBox.setSelected(false);
      }
      
      this.mouseDelayJTextField.setText(
            Integer.toString(this.mouseActionScriptInputInterface.getTime()));
      
      this.mouseXJTextField.setText(
            Integer.toString(this.mouseActionScriptInputInterface.getPoint().x));
      
      this.mouseYJTextField.setText(
            Integer.toString(this.mouseActionScriptInputInterface.getPoint().y));
      
      this.mouseActionScriptInputInterface.log();
   }
   
   private void updateInputType()
   throws Exception
   {
      final ComboBoxModel comboBoxModel = this.inputAutomationTypeJComboBox.getModel();
      final String selectedItem = (String)
      comboBoxModel.getSelectedItem();
      if(!StringValidationUtil.getInstance().isEmpty(selectedItem))
      {
         this.mouseActionScriptInputInterface.setInputRobotInterface(
               InputRobotFactory.getInstance().get(selectedItem));
      }
   }
   
   private void update()
   throws Exception
   {
      this.updateInputType();
      
      this.mouseActionScriptInputInterface.setTime(
            Integer.valueOf(this.mouseDelayJTextField.getText()));
      
      LogUtil.put(LogFactory.getInstance("Button State: " +
            this.mouseActionScriptInputInterface.getButtonClicks(),
            this, "update"));
      
      if(this.button1JCheckBox.isSelected())
      {
         if((this.mouseActionScriptInputInterface.getButtonClicks() &
               InputEvent.BUTTON1_MASK) == 0)
         {
            this.mouseActionScriptInputInterface.setButtonClicks(
                  this.mouseActionScriptInputInterface.getButtonClicks() |
                  InputEvent.BUTTON1_MASK);
            LogUtil.put(LogFactory.getInstance("Button 1 Selected: " +
                  this.mouseActionScriptInputInterface.getButtonClicks(),
                  this, "update"));
         }
      }
      else
      {
         if((this.mouseActionScriptInputInterface.getButtonClicks() &
               InputEvent.BUTTON1_MASK) != 0)
         {
            this.mouseActionScriptInputInterface.setButtonClicks(
                  this.mouseActionScriptInputInterface.getButtonClicks() ^
                  InputEvent.BUTTON1_MASK);
            LogUtil.put(LogFactory.getInstance("Button 1 Deselected: " +
                  this.mouseActionScriptInputInterface.getButtonClicks(),
                  this, "update"));
         }
      }
      
      if(this.button2JCheckBox.isSelected())
      {
         if((this.mouseActionScriptInputInterface.getButtonClicks() &
               InputEvent.BUTTON2_MASK) == 0)
         {
            this.mouseActionScriptInputInterface.setButtonClicks(
                  this.mouseActionScriptInputInterface.getButtonClicks() |
                  InputEvent.BUTTON2_MASK);
            LogUtil.put(LogFactory.getInstance("Button 2 Selected: " +
                  this.mouseActionScriptInputInterface.getButtonClicks(),
                  this, "update"));
         }
      }
      else
      {
         if((this.mouseActionScriptInputInterface.getButtonClicks() &
               InputEvent.BUTTON2_MASK) != 0)
         {
            this.mouseActionScriptInputInterface.setButtonClicks(
                  this.mouseActionScriptInputInterface.getButtonClicks() ^
                  InputEvent.BUTTON2_MASK);
            LogUtil.put(LogFactory.getInstance("Button 2 Deselected: " +
                  this.mouseActionScriptInputInterface.getButtonClicks(),
                  this, "update"));
         }
      }
      
      String mouseXString = this.mouseXJTextField.getText();
      if(!StringValidationUtil.getInstance().isEmpty(mouseXString) &&
            StringValidationUtil.getInstance().isNumber(mouseXString))
      {
         this.mouseActionScriptInputInterface.getPoint().x = Integer.parseInt(mouseXString);
      }
      
      String mouseYString = this.mouseYJTextField.getText();
      if(!StringValidationUtil.getInstance().isEmpty(mouseYString) &&
            StringValidationUtil.getInstance().isNumber(mouseYString))
      {
         this.mouseActionScriptInputInterface.getPoint().y = Integer.parseInt(mouseYString);
      }
      
      this.mouseActionScriptInputInterface.log();
   }
   
   public javax.swing.JDialog getMouseActionJDialog()
   {
      return mouseActionJDialog;
   }
   
   public void setMouseActionJDialog(javax.swing.JDialog mouseActionJDialog)
   {
      this.mouseActionJDialog = mouseActionJDialog;
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        mouseActionJDialog = new javax.swing.JDialog();
        titleJLabel = new javax.swing.JLabel();
        okJButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        button1JCheckBox = new javax.swing.JCheckBox();
        button2JCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        mouseXJTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        mouseYJTextField = new javax.swing.JTextField();
        inputTypeJLabel = new javax.swing.JLabel();
        inputAutomationTypeJComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        mouseDelayJTextField = new javax.swing.JTextField();
        mouseActionJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        mouseActionJDialog.setMinimumSize(new java.awt.Dimension(275, 185));
        titleJLabel.setText("Mouse Input Options");

        okJButton.setText("OK");
        okJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                okJButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Buttons:");

        jLabel3.setText("Move To:");

        button1JCheckBox.setText("1");
        button1JCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button1JCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        button2JCheckBox.setText("2");
        button2JCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button2JCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        button2JCheckBox.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                button2JCheckBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("x:");

        mouseXJTextField.setText("0");

        jLabel5.setText("y:");

        mouseYJTextField.setText("0");

        inputTypeJLabel.setText("Input Type:");

        jLabel6.setText("Delay:");

        mouseDelayJTextField.setText("100");

        javax.swing.GroupLayout mouseActionJDialogLayout = new javax.swing.GroupLayout(mouseActionJDialog.getContentPane());
        mouseActionJDialog.getContentPane().setLayout(mouseActionJDialogLayout);
        mouseActionJDialogLayout.setHorizontalGroup(
            mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputTypeJLabel)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mouseActionJDialogLayout.createSequentialGroup()
                        .addGroup(mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mouseActionJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(25, 25, 25))
                            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                .addGroup(mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mouseDelayJTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                .addComponent(titleJLabel)
                                .addGap(45, 45, 45))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mouseActionJDialogLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputAutomationTypeJComboBox, 0, 154, Short.MAX_VALUE))
                            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                .addComponent(button1JCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button2JCheckBox))
                            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                .addGroup(mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(okJButton))
                                    .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                                        .addComponent(mouseXJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mouseYJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        mouseActionJDialogLayout.setVerticalGroup(
            mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                .addComponent(titleJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputTypeJLabel)
                    .addComponent(inputAutomationTypeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(button1JCheckBox)
                        .addComponent(button2JCheckBox)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(mouseDelayJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mouseActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(mouseYJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mouseXJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okJButton)
                .addContainerGap())
        );

        setMinimumSize(new java.awt.Dimension(100, 0));
        mouseActionJButton.setText("Edit");
        mouseActionJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mouseActionJButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Mouse:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(mouseActionJButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(mouseActionJButton))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void button2JCheckBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_button2JCheckBoxActionPerformed
    {//GEN-HEADEREND:event_button2JCheckBoxActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_button2JCheckBoxActionPerformed
    
    private void okJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okJButtonActionPerformed
    {//GEN-HEADEREND:event_okJButtonActionPerformed
// TODO add your handling code here:
       try
       {
          this.update();
          this.getMouseActionJDialog().setVisible(false);
       }
       catch(Exception e)
       {
          LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "okButtonActionPerformed", e));
       }
    }//GEN-LAST:event_okJButtonActionPerformed
    
    private void mouseActionJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mouseActionJButtonActionPerformed
    {//GEN-HEADEREND:event_mouseActionJButtonActionPerformed
// TODO add your handling code here:
       this.getMouseActionJDialog().setVisible(true);
    }//GEN-LAST:event_mouseActionJButtonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox button1JCheckBox;
    private javax.swing.JCheckBox button2JCheckBox;
    private javax.swing.JComboBox inputAutomationTypeJComboBox;
    private javax.swing.JLabel inputTypeJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton mouseActionJButton;
    private javax.swing.JDialog mouseActionJDialog;
    private javax.swing.JTextField mouseDelayJTextField;
    private javax.swing.JTextField mouseXJTextField;
    private javax.swing.JTextField mouseYJTextField;
    private javax.swing.JButton okJButton;
    private javax.swing.JLabel titleJLabel;
    // End of variables declaration//GEN-END:variables
    
}
