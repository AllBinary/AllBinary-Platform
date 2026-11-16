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

import javax.swing.*;

import org.allbinary.input.KeySingletonFactory;
import org.allbinary.input.automation.robot.InputRobot;
import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.TempInputRobotNames;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonPhoneStrings;
import org.allbinary.string.CommonStrings;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

/**
 *
 * @author  USER
 */
public class KeyboardActionScriptInputJPanel extends JPanel
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private KeyboardActionScriptInputInterface keyActionScriptInputInterface;
   
   /** Creates new form ColorAtActionJPanel */
   public KeyboardActionScriptInputJPanel(KeyboardActionScriptInputInterface keyActionScriptInputInterface)
   throws Exception
   {
      this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
      
      this.initComponents();
      
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
   
   public JDialog getKeyActionJDialog()
   {
      return this.keyActionJDialog;
   }
   
   public void setKeyActionJDialog(JDialog keyActionJDialog)
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
      this.keyActionJDialog = new JDialog();
      this.titleJLabel = new JLabel();
      this.addKeyJLabel = new JLabel();
      this.keyNameJComboBox = new JComboBox();
      this.timeJTextField = new JTextField();
      this.holdTimeJLabel = new JLabel();
      this.inputAutomationTypeJComboBox = new JComboBox();
      this.inputTypeJLabel = new JLabel();
      this.delayJTextField = new JTextField();
      this.delayBetweenJLabel = new JLabel();
      this.testJLabel = new JLabel();
      this.textJTextField = new JTextField();
      this.pressJRadioButton = new JRadioButton();
      this.releaseJRadioButton = new JRadioButton();
      this.normalJRadioButton = new JRadioButton();
      this.simultaneousJRadioButton = new JRadioButton();
      this.sequenceJRadioButton = new JRadioButton();
      this.jPanel1 = new JPanel();
      this.okJButton = new JButton();
      this.keyIntegerJComboBox = new JComboBox();
      this.keyActionJButton = new JButton();
      this.jLabel1 = new JLabel();

      this.keyActionJDialog.setMinimumSize(new Dimension(255, 280));
      this.titleJLabel.setHorizontalAlignment(SwingConstants.CENTER);
      this.titleJLabel.setText("Keyboard Input Options");

      this.addKeyJLabel.setText("Add Key:");

      this.keyNameJComboBox.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            keyNameJComboBoxActionPerformed(evt);
         }
      });

      this.timeJTextField.setText(CommonPhoneStrings.getInstance().ZERO);

      this.holdTimeJLabel.setText("Hold Time (ms):");

      this.inputTypeJLabel.setText("Input Type:");

      this.delayJTextField.setText(CommonPhoneStrings.getInstance().ZERO);

      this.delayBetweenJLabel.setText("Delay Between Keys (ms):");

      this.testJLabel.setText("Text:");

      this.textJTextField.setText(CommonPhoneStrings.getInstance().ZERO);

      this.pressJRadioButton.setText("Press");
      this.pressJRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      this.pressJRadioButton.setMargin(new Insets(0, 0, 0, 0));
      this.pressJRadioButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            pressJRadioButtonActionPerformed(evt);
         }
      });

      this.releaseJRadioButton.setText("Release");
      this.releaseJRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      this.releaseJRadioButton.setMargin(new Insets(0, 0, 0, 0));
      this.releaseJRadioButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            releaseJRadioButtonActionPerformed(evt);
         }
      });

      this.normalJRadioButton.setSelected(true);
      this.normalJRadioButton.setText("Normal");
      this.normalJRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      this.normalJRadioButton.setMargin(new Insets(0, 0, 0, 0));
      this.normalJRadioButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            normalJRadioButtonActionPerformed(evt);
         }
      });

      this.simultaneousJRadioButton.setSelected(true);
      this.simultaneousJRadioButton.setText("Simultaneous");
      this.simultaneousJRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      this.simultaneousJRadioButton.setMargin(new Insets(0, 0, 0, 0));
      this.simultaneousJRadioButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            simultaneousJRadioButtonActionPerformed(evt);
         }
      });

      this.sequenceJRadioButton.setText("Sequence");
      this.sequenceJRadioButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      this.sequenceJRadioButton.setMargin(new Insets(0, 0, 0, 0));
      this.sequenceJRadioButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            sequenceJRadioButtonActionPerformed(evt);
         }
      });

      this.okJButton.setText("OK");
      this.okJButton.setHorizontalTextPosition(SwingConstants.CENTER);
      this.okJButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            okJButtonActionPerformed(evt);
         }
      });

      this.jPanel1.add(this.okJButton);

      this.keyIntegerJComboBox.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            keyIntegerJComboBoxActionPerformed(evt);
         }
      });

      GroupLayout keyActionJDialogLayout = new GroupLayout(this.keyActionJDialog.getContentPane());
      this.keyActionJDialog.getContentPane().setLayout(keyActionJDialogLayout);
      keyActionJDialogLayout.setHorizontalGroup(
         keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
         .addGroup(keyActionJDialogLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
               .addGroup(keyActionJDialogLayout.createSequentialGroup()
                  .addGroup(keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                     .addComponent(addKeyJLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(inputTypeJLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                     .addComponent(inputAutomationTypeJComboBox, 0, 148, Short.MAX_VALUE)
                     .addComponent(textJTextField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                     .addGroup(keyActionJDialogLayout.createSequentialGroup()
                        .addComponent(keyNameJComboBox, 0, 101, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keyIntegerJComboBox, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
               .addComponent(testJLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
               .addGroup(keyActionJDialogLayout.createSequentialGroup()
                  .addComponent(normalJRadioButton)
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(pressJRadioButton)
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(releaseJRadioButton)
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, GroupLayout.PREFERRED_SIZE))
               .addGroup(GroupLayout.Alignment.TRAILING, keyActionJDialogLayout.createSequentialGroup()
                  .addComponent(holdTimeJLabel)
                  .addGap(22, 22, 22)
                  .addComponent(timeJTextField, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
               .addGroup(keyActionJDialogLayout.createSequentialGroup()
                  .addComponent(delayBetweenJLabel)
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                  .addComponent(delayJTextField, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
               .addGroup(keyActionJDialogLayout.createSequentialGroup()
                  .addComponent(simultaneousJRadioButton)
                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(sequenceJRadioButton)))
            .addContainerGap())
         .addComponent(titleJLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
         .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
      );
      keyActionJDialogLayout.setVerticalGroup(
         keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
         .addGroup(keyActionJDialogLayout.createSequentialGroup()
            .addComponent(titleJLabel)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
               .addComponent(inputTypeJLabel)
               .addComponent(inputAutomationTypeJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
               .addComponent(addKeyJLabel)
               .addComponent(keyIntegerJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
               .addComponent(keyNameJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
               .addComponent(testJLabel)
               .addComponent(textJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
               .addComponent(pressJRadioButton)
               .addComponent(normalJRadioButton)
               .addComponent(releaseJRadioButton))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
               .addComponent(holdTimeJLabel)
               .addComponent(timeJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
               .addComponent(delayBetweenJLabel)
               .addComponent(delayJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(keyActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
               .addComponent(simultaneousJRadioButton)
               .addComponent(sequenceJRadioButton))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
      );

      setMinimumSize(new Dimension(100, 0));
      this.keyActionJButton.setText("Edit");
      this.keyActionJButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            keyActionJButtonActionPerformed(evt);
         }
      });

      this.jLabel1.setText("Key:");

      GroupLayout layout = new GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
            .addComponent(keyActionJButton))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(GroupLayout.Alignment.LEADING)
         .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
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
   
   private void keyIntegerJComboBoxActionPerformed(ActionEvent evt)//GEN-FIRST:event_keyIntegerJComboBoxActionPerformed
   {//GEN-HEADEREND:event_keyIntegerJComboBoxActionPerformed
      
      this.keyIntegerJComboBox.getSelectedIndex();
      this.keyNameJComboBox.setSelectedIndex(this.keyIntegerJComboBox.getSelectedIndex());
      
   }//GEN-LAST:event_keyIntegerJComboBoxActionPerformed
   
   private void sequenceJRadioButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_sequenceJRadioButtonActionPerformed
   {//GEN-HEADEREND:event_sequenceJRadioButtonActionPerformed
      if(this.sequenceJRadioButton.isSelected())
      {
         this.delayJTextField.setText(CommonPhoneStrings.getInstance().ONE);
         this.simultaneousJRadioButton.setSelected(false);
      }
   }//GEN-LAST:event_sequenceJRadioButtonActionPerformed
   
   private void simultaneousJRadioButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_simultaneousJRadioButtonActionPerformed
   {//GEN-HEADEREND:event_simultaneousJRadioButtonActionPerformed
      if(this.simultaneousJRadioButton.isSelected())
      {
         this.delayJTextField.setText(CommonPhoneStrings.getInstance().ZERO);
         this.sequenceJRadioButton.setSelected(false);
      }
   }//GEN-LAST:event_simultaneousJRadioButtonActionPerformed
   
   private void keyNameJComboBoxActionPerformed(ActionEvent evt)//GEN-FIRST:event_keyNameJComboBoxActionPerformed
   {//GEN-HEADEREND:event_keyNameJComboBoxActionPerformed
         Integer integer = this.getSelectedKey();
         this.prependText(integer);
         this.keyIntegerJComboBox.setSelectedIndex(this.keyNameJComboBox.getSelectedIndex());
   }//GEN-LAST:event_keyNameJComboBoxActionPerformed
   
    private void okJButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_okJButtonActionPerformed
    {//GEN-HEADEREND:event_okJButtonActionPerformed
       
       try
       {
          this.update();
          this.getKeyActionJDialog().setVisible(false);
       }
       catch(Exception e)
       {
          this.logUtil.put(this.commonStrings.EXCEPTION, this, "okButtonActionPerformed", e);
       }
       
    }//GEN-LAST:event_okJButtonActionPerformed
    
    private void keyActionJButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_keyActionJButtonActionPerformed
    {//GEN-HEADEREND:event_keyActionJButtonActionPerformed
       this.getKeyActionJDialog().setVisible(true);
    }//GEN-LAST:event_keyActionJButtonActionPerformed
    
    private void normalJRadioButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_normalJRadioButtonActionPerformed
       
       if(this.normalJRadioButton.isSelected())
       {
          this.keyActionScriptInputInterface.setNormal();
          this.pressJRadioButton.setSelected(false);
          this.releaseJRadioButton.setSelected(false);
       }
//GEN-LAST:event_normalJRadioButtonActionPerformed
    }
    
    private void releaseJRadioButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_releaseJRadioButtonActionPerformed
       
       if(this.releaseJRadioButton.isSelected())
       {
          this.keyActionScriptInputInterface.setRelease(true);
          this.keyActionScriptInputInterface.setPress(false);
          this.pressJRadioButton.setSelected(false);
          this.normalJRadioButton.setSelected(false);
       }
       
    }//GEN-LAST:event_releaseJRadioButtonActionPerformed
    
    private void pressJRadioButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_pressJRadioButtonActionPerformed
       
       if(this.pressJRadioButton.isSelected())
       {
          this.keyActionScriptInputInterface.setPress(true);
          this.keyActionScriptInputInterface.setRelease(false);
          this.releaseJRadioButton.setSelected(false);
          this.normalJRadioButton.setSelected(false);
       }
       
    }//GEN-LAST:event_pressJRadioButtonActionPerformed
    
    
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private JLabel addKeyJLabel;
   private JLabel delayBetweenJLabel;
   private JTextField delayJTextField;
   private JLabel holdTimeJLabel;
   private JComboBox inputAutomationTypeJComboBox;
   private JLabel inputTypeJLabel;
   private JLabel jLabel1;
   private JPanel jPanel1;
   private JButton keyActionJButton;
   private JDialog keyActionJDialog;
   private JComboBox keyIntegerJComboBox;
   private JComboBox keyNameJComboBox;
   private JRadioButton normalJRadioButton;
   private JButton okJButton;
   private JRadioButton pressJRadioButton;
   private JRadioButton releaseJRadioButton;
   private JRadioButton sequenceJRadioButton;
   private JRadioButton simultaneousJRadioButton;
   private JLabel testJLabel;
   private JTextField textJTextField;
   private JTextField timeJTextField;
   private JLabel titleJLabel;
   // End of variables declaration//GEN-END:variables
   
}
