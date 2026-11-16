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

import javax.swing.*;

import org.allbinary.input.automation.robot.InputRobot;
import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.TempInputRobotNames;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonPhoneStrings;
import org.allbinary.string.CommonStrings;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 *
 * @author  USER
 */
public class MouseActionScriptInputJPanel
      extends JPanel
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private MouseActionScriptInputInterface mouseActionScriptInputInterface;
   
   /** Creates new form ColorAtActionJPanel */
   public MouseActionScriptInputJPanel(
         final MouseActionScriptInputInterface mouseActionScriptInputInterface)
         throws Exception
   {
      this.logUtil.putF(CommonLabels.getInstance().START + mouseActionScriptInputInterface, this, this.commonStrings.CONSTRUCTOR);
      
      this.initComponents();
      
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
      
      this.logUtil.putF("Button State: " +
            this.mouseActionScriptInputInterface.getButtonClicks(), this, "update");
      
      if(this.button1JCheckBox.isSelected())
      {
         if((this.mouseActionScriptInputInterface.getButtonClicks() &
               InputEvent.BUTTON1_MASK) == 0)
         {
            this.mouseActionScriptInputInterface.setButtonClicks(
                  this.mouseActionScriptInputInterface.getButtonClicks() |
                  InputEvent.BUTTON1_MASK);
            this.logUtil.putF("Button 1 Selected: " +
                  this.mouseActionScriptInputInterface.getButtonClicks(), this, "update");
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
            this.logUtil.putF("Button 1 Deselected: " +
                  this.mouseActionScriptInputInterface.getButtonClicks(), this, "update");
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
            this.logUtil.putF("Button 2 Selected: " +
                  this.mouseActionScriptInputInterface.getButtonClicks(), this, "update");
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
            this.logUtil.putF("Button 2 Deselected: " +
                  this.mouseActionScriptInputInterface.getButtonClicks(), this, "update");
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
   
   public JDialog getMouseActionJDialog()
   {
      return this.mouseActionJDialog;
   }
   
   public void setMouseActionJDialog(JDialog mouseActionJDialog)
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
        this.mouseActionJDialog = new JDialog();
        this.titleJLabel = new JLabel();
        this.okJButton = new JButton();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.button1JCheckBox = new JCheckBox();
        this.button2JCheckBox = new JCheckBox();
        this.jLabel4 = new JLabel();
        this.mouseXJTextField = new JTextField();
        this.jLabel5 = new JLabel();
        this.mouseYJTextField = new JTextField();
        this.inputTypeJLabel = new JLabel();
        this.inputAutomationTypeJComboBox = new JComboBox();
        this.jLabel6 = new JLabel();
        this.mouseDelayJTextField = new JTextField();
        this.mouseActionJButton = new JButton();
        this.jLabel1 = new JLabel();

        this.mouseActionJDialog.setMinimumSize(new Dimension(275, 185));
        this.titleJLabel.setText("Mouse Input Options");

        this.okJButton.setText("OK");
        this.okJButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                okJButtonActionPerformed(evt);
            }
        });

        this.jLabel2.setText("Buttons:");

        this.jLabel3.setText("Move To:");

        this.button1JCheckBox.setText(CommonPhoneStrings.getInstance().ONE);
        this.button1JCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.button1JCheckBox.setMargin(new Insets(0, 0, 0, 0));

        this.button2JCheckBox.setText(CommonPhoneStrings.getInstance().TWO);
        this.button2JCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.button2JCheckBox.setMargin(new Insets(0, 0, 0, 0));
        this.button2JCheckBox.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                button2JCheckBoxActionPerformed(evt);
            }
        });

        this.jLabel4.setText("x:");

        this.mouseXJTextField.setText(CommonPhoneStrings.getInstance().ZERO);

        this.jLabel5.setText("y:");

        this.mouseYJTextField.setText(CommonPhoneStrings.getInstance().ZERO);

        this.inputTypeJLabel.setText("Input Type:");

        this.jLabel6.setText("Delay:");

        this.mouseDelayJTextField.setText("100");

        GroupLayout mouseActionJDialogLayout = new GroupLayout(this.mouseActionJDialog.getContentPane());
        this.mouseActionJDialog.getContentPane().setLayout(mouseActionJDialogLayout);
        mouseActionJDialogLayout.setHorizontalGroup(
            mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(inputTypeJLabel)
                    .addGroup(GroupLayout.Alignment.TRAILING, mouseActionJDialogLayout.createSequentialGroup()
                        .addGroup(mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, mouseActionJDialogLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(25, 25, 25))
                            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                .addGroup(mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(mouseDelayJTextField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                .addComponent(titleJLabel)
                                .addGap(45, 45, 45))
                            .addGroup(GroupLayout.Alignment.TRAILING, mouseActionJDialogLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputAutomationTypeJComboBox, 0, 154, Short.MAX_VALUE))
                            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                .addComponent(button1JCheckBox)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button2JCheckBox))
                            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                .addGroup(mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(okJButton))
                                    .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                                        .addComponent(mouseXJTextField, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mouseYJTextField, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        mouseActionJDialogLayout.setVerticalGroup(
            mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mouseActionJDialogLayout.createSequentialGroup()
                .addComponent(titleJLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(inputTypeJLabel)
                    .addComponent(inputAutomationTypeJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                    .addGroup(mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(button1JCheckBox)
                        .addComponent(button2JCheckBox)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(mouseDelayJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mouseActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(mouseYJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(mouseXJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okJButton)
                .addContainerGap())
        );

        setMinimumSize(new Dimension(100, 0));
        this.mouseActionJButton.setText("Edit");
        this.mouseActionJButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                mouseActionJButtonActionPerformed(evt);
            }
        });

        this.jLabel1.setText("Mouse:");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(mouseActionJButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(mouseActionJButton))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void button2JCheckBoxActionPerformed(ActionEvent evt)//GEN-FIRST:event_button2JCheckBoxActionPerformed
    {//GEN-HEADEREND:event_button2JCheckBoxActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_button2JCheckBoxActionPerformed
    
    private void okJButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_okJButtonActionPerformed
    {//GEN-HEADEREND:event_okJButtonActionPerformed
// TODO add your handling code here:
       try
       {
          this.update();
          this.getMouseActionJDialog().setVisible(false);
       }
       catch(Exception e)
       {
          this.logUtil.put(this.commonStrings.EXCEPTION, this, "okButtonActionPerformed", e);
       }
    }//GEN-LAST:event_okJButtonActionPerformed
    
    private void mouseActionJButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_mouseActionJButtonActionPerformed
    {//GEN-HEADEREND:event_mouseActionJButtonActionPerformed
// TODO add your handling code here:
       this.getMouseActionJDialog().setVisible(true);
    }//GEN-LAST:event_mouseActionJButtonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JCheckBox button1JCheckBox;
    private JCheckBox button2JCheckBox;
    private JComboBox inputAutomationTypeJComboBox;
    private JLabel inputTypeJLabel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JButton mouseActionJButton;
    private JDialog mouseActionJDialog;
    private JTextField mouseDelayJTextField;
    private JTextField mouseXJTextField;
    private JTextField mouseYJTextField;
    private JButton okJButton;
    private JLabel titleJLabel;
    // End of variables declaration//GEN-END:variables
    
}
