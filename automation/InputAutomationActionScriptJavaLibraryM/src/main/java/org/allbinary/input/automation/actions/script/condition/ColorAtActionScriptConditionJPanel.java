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
package org.allbinary.input.automation.actions.script.condition;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.media.image.analysis.ColorRangeInterface;
import org.allbinary.string.CommonStrings;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 *
 * @author  USER
 */
public class ColorAtActionScriptConditionJPanel
    extends JPanel
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private ColorAtActionScriptConditionInterface colorAtActionScriptConditionInterface;
    
    /** Creates new form ColorAtActionJPanel */
    public ColorAtActionScriptConditionJPanel(
        ColorAtActionScriptConditionInterface colorAtActionScriptConditionInterface)
    {
        this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
        
        this.initComponents();
        
        this.colorAtActionScriptConditionInterface = colorAtActionScriptConditionInterface;
        
        this.set();
    }
    
    private void set()
    {
        ColorRangeInterface colorRangeInterface = 
            this.colorAtActionScriptConditionInterface.getColorRangeInterface();
        
        this.getMinRedJTextField().setText(
              Integer.toString(colorRangeInterface.getMinRed()));
        this.getMaxRedJTextField().setText(
            Integer.toString(colorRangeInterface.getMaxRed()));
        
        this.getMinGreenJTextField().setText(
            Integer.toString(colorRangeInterface.getMinGreen()));
        this.getMaxGreenJTextField().setText(
            Integer.toString(colorRangeInterface.getMaxGreen()));
        
        this.getMinBlueJTextField().setText(
            Integer.toString(colorRangeInterface.getMinBlue()));
        this.getMaxBlueJTextField().setText(
            Integer.toString(colorRangeInterface.getMaxBlue()));
        
        this.getColorAtXJTextField().setText(
            Integer.toString(this.colorAtActionScriptConditionInterface.getPoint().x));
        
        this.getColorAtYJTextField().setText(
            Integer.toString(this.colorAtActionScriptConditionInterface.getPoint().y));
        
        this.colorAtActionScriptConditionInterface.log();
    }
    
    private void update()
    {
        ColorRangeInterface colorRangeInterface = 
            this.colorAtActionScriptConditionInterface.getColorRangeInterface();
        
        colorRangeInterface.setMinRed(
            Integer.valueOf(this.getMinRedJTextField().getText()).intValue());
        colorRangeInterface.setMaxRed(
            Integer.valueOf(this.getMaxRedJTextField().getText()).intValue());
        
        colorRangeInterface.setMinGreen(
            Integer.valueOf(this.getMinGreenJTextField().getText()).intValue());
        colorRangeInterface.setMaxGreen(
            Integer.valueOf(this.getMaxGreenJTextField().getText()).intValue());
        
        colorRangeInterface.setMinBlue(
            Integer.valueOf(this.getMinBlueJTextField().getText()).intValue());
        colorRangeInterface.setMaxBlue(
            Integer.valueOf(this.getMaxBlueJTextField().getText()).intValue());
        
        String xString = this.getColorAtXJTextField().getText();
        if(!StringValidationUtil.getInstance().isEmpty(xString) &&
            StringValidationUtil.getInstance().isNumber(xString))
        {
            this.colorAtActionScriptConditionInterface.getPoint().x = Integer.parseInt(xString);
        }

        String yString = this.getColorAtYJTextField().getText();
        if(!StringValidationUtil.getInstance().isEmpty(yString) &&
            StringValidationUtil.getInstance().isNumber(yString))
        {
            this.colorAtActionScriptConditionInterface.getPoint().y = Integer.parseInt(xString);
        }

        this.colorAtActionScriptConditionInterface.log();
    }
    
    public JTextField getColorAtXJTextField()
    {
        return this.colorAtXJTextField;
    }
    
    public void setColorAtXJTextField(JTextField colorAtXJTextField)
    {
        this.colorAtXJTextField = colorAtXJTextField;
    }
    
    public JTextField getColorAtYJTextField()
    {
        return this.colorAtYJTextField;
    }
    
    public void setColorAtYJTextField(JTextField colorAtYJTextField)
    {
        this.colorAtYJTextField = colorAtYJTextField;
    }
    
    public JTextField getMaxBlueJTextField()
    {
        return this.maxBlueJTextField;
    }
    
    public void setMaxBlueJTextField(JTextField maxBlueJTextField)
    {
        this.maxBlueJTextField = maxBlueJTextField;
    }
    
    public JTextField getMaxGreenJTextField()
    {
        return this.maxGreenJTextField;
    }
    
    public void setMaxGreenJTextField(JTextField maxGreenJTextField)
    {
        this.maxGreenJTextField = maxGreenJTextField;
    }
    
    public JTextField getMaxRedJTextField()
    {
        return this.maxRedJTextField;
    }
    
    public void setMaxRedJTextField(JTextField maxRedJTextField)
    {
        this.maxRedJTextField = maxRedJTextField;
    }
    
    public JTextField getMinBlueJTextField()
    {
        return this.minBlueJTextField;
    }
    
    public void setMinBlueJTextField(JTextField minBlueJTextField)
    {
        this.minBlueJTextField = minBlueJTextField;
    }
    
    public JTextField getMinGreenJTextField()
    {
        return this.minGreenJTextField;
    }
    
    public void setMinGreenJTextField(JTextField minGreenJTextField)
    {
        this.minGreenJTextField = minGreenJTextField;
    }
    
    public JTextField getMinRedJTextField()
    {
        return this.minRedJTextField;
    }
    
    public void setMinRedJTextField(JTextField minRedJTextField)
    {
        this.minRedJTextField = minRedJTextField;
    }
    
    public JLabel getYJLabel()
    {
        return this.yJLabel;
    }
    
    public void setYJLabel(JLabel yJLabel)
    {
        this.yJLabel = yJLabel;
    }
    
    public JDialog getColorAtActionJDialog()
    {
        return this.colorAtActionJDialog;
    }
    
    public void setColorAtActionJDialog(JDialog colorAtActionJDialog)
    {
        this.colorAtActionJDialog = colorAtActionJDialog;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        this.colorAtActionJDialog = new JDialog();
        this.titleJLabel = new JLabel();
        this.locationJLabel = new JLabel();
        this.colorAtXJTextField = new JTextField();
        this.xJLabel = new JLabel();
        this.yJLabel = new JLabel();
        this.colorAtYJTextField = new JTextField();
        this.colorRangeJLabel = new JLabel();
        this.redJLabel = new JLabel();
        this.minJLabel = new JLabel();
        this.maxJLabel = new JLabel();
        this.greenJLabel = new JLabel();
        this.blueJLabel = new JLabel();
        this.minRedJTextField = new JTextField();
        this.maxRedJTextField = new JTextField();
        this.maxGreenJTextField = new JTextField();
        this.minBlueJTextField = new JTextField();
        this.maxBlueJTextField = new JTextField();
        this.okJButton = new JButton();
        this.minGreenJTextField = new JTextField();
        this.colorAtActionJButton = new JButton();
        this.jLabel1 = new JLabel();

        this.colorAtActionJDialog.setMinimumSize(new Dimension(350, 250));
        this.titleJLabel.setText("              Color At Action Options            ");

        this.locationJLabel.setText("Location:");

        this.xJLabel.setText("X:");

        this.yJLabel.setText("Y:");

        this.colorRangeJLabel.setText("Color Range");

        this.redJLabel.setText("Red:");

        this.minJLabel.setText("Minimum");

        this.maxJLabel.setText("Maximum");

        this.greenJLabel.setText("Green:");

        this.blueJLabel.setText("Blue:");

        this.okJButton.setText("OK");
        this.okJButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                okJButtonActionPerformed(evt);
            }
        });

        GroupLayout colorAtActionJDialogLayout = new GroupLayout(this.colorAtActionJDialog.getContentPane());
        this.colorAtActionJDialog.getContentPane().setLayout(colorAtActionJDialogLayout);
        colorAtActionJDialogLayout.setHorizontalGroup(
            colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(okJButton)
                            .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                                .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(blueJLabel)
                                            .addComponent(greenJLabel)
                                            .addComponent(redJLabel)
                                            .addComponent(colorRangeJLabel))
                                        .addGap(29, 29, 29))
                                    .addGroup(GroupLayout.Alignment.TRAILING, colorAtActionJDialogLayout.createSequentialGroup()
                                        .addComponent(locationJLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(xJLabel)
                                        .addGap(18, 18, 18)))
                                .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(minBlueJTextField, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(minGreenJTextField, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(minJLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(minRedJTextField, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(colorAtXJTextField, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                                .addGap(16, 16, 16)))
                        .addGap(6, 6, 6)
                        .addComponent(yJLabel)
                        .addGap(24, 24, 24)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(maxJLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.LEADING, colorAtActionJDialogLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(maxBlueJTextField, GroupLayout.Alignment.TRAILING)
                                    .addComponent(maxRedJTextField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                    .addComponent(maxGreenJTextField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
                            .addComponent(colorAtYJTextField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                        .addGap(22, 22, 22))
                    .addGroup(GroupLayout.Alignment.TRAILING, colorAtActionJDialogLayout.createSequentialGroup()
                        .addComponent(titleJLabel)
                        .addGap(33, 33, 33))))
        );
        colorAtActionJDialogLayout.setVerticalGroup(
            colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleJLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(xJLabel)
                            .addComponent(locationJLabel)
                            .addComponent(colorAtXJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(yJLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(minJLabel)
                            .addComponent(colorRangeJLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(minRedJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(redJLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(greenJLabel)
                            .addComponent(minGreenJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(blueJLabel)
                            .addComponent(minBlueJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(okJButton))
                    .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(colorAtYJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxJLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxRedJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxGreenJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxBlueJTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        setPreferredSize(new Dimension(100, 0));
        this.colorAtActionJButton.setText("Edit");
        this.colorAtActionJButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                colorAtActionJButtonActionPerformed(evt);
            }
        });

        this.jLabel1.setText("Color At:");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(colorAtActionJButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(colorAtActionJButton))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void okJButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_okJButtonActionPerformed
    {//GEN-HEADEREND:event_okJButtonActionPerformed
// TODO add your handling code here:
        this.update();
        this.getColorAtActionJDialog().setVisible(false);
    }//GEN-LAST:event_okJButtonActionPerformed
    
    private void colorAtActionJButtonActionPerformed(ActionEvent evt)//GEN-FIRST:event_colorAtActionJButtonActionPerformed
    {//GEN-HEADEREND:event_colorAtActionJButtonActionPerformed
// TODO add your handling code here:
        this.getColorAtActionJDialog().setVisible(true);
    }//GEN-LAST:event_colorAtActionJButtonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel blueJLabel;
    private JButton colorAtActionJButton;
    private JDialog colorAtActionJDialog;
    private JTextField colorAtXJTextField;
    private JTextField colorAtYJTextField;
    private JLabel colorRangeJLabel;
    private JLabel greenJLabel;
    private JLabel jLabel1;
    private JLabel locationJLabel;
    private JTextField maxBlueJTextField;
    private JTextField maxGreenJTextField;
    private JLabel maxJLabel;
    private JTextField maxRedJTextField;
    private JTextField minBlueJTextField;
    private JTextField minGreenJTextField;
    private JLabel minJLabel;
    private JTextField minRedJTextField;
    private JButton okJButton;
    private JLabel redJLabel;
    private JLabel titleJLabel;
    private JLabel xJLabel;
    private JLabel yJLabel;
    // End of variables declaration//GEN-END:variables
    
}
