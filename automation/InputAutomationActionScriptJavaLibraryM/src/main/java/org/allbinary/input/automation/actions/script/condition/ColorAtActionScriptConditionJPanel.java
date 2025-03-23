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

import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.media.image.analysis.ColorRangeInterface;

/**
 *
 * @author  USER
 */
public class ColorAtActionScriptConditionJPanel
    extends javax.swing.JPanel
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private ColorAtActionScriptConditionInterface colorAtActionScriptConditionInterface;
    
    /** Creates new form ColorAtActionJPanel */
    public ColorAtActionScriptConditionJPanel(
        ColorAtActionScriptConditionInterface colorAtActionScriptConditionInterface)
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR));
        
        initComponents();
        
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
    
    public javax.swing.JTextField getColorAtXJTextField()
    {
        return colorAtXJTextField;
    }
    
    public void setColorAtXJTextField(javax.swing.JTextField colorAtXJTextField)
    {
        this.colorAtXJTextField = colorAtXJTextField;
    }
    
    public javax.swing.JTextField getColorAtYJTextField()
    {
        return colorAtYJTextField;
    }
    
    public void setColorAtYJTextField(javax.swing.JTextField colorAtYJTextField)
    {
        this.colorAtYJTextField = colorAtYJTextField;
    }
    
    public javax.swing.JTextField getMaxBlueJTextField()
    {
        return maxBlueJTextField;
    }
    
    public void setMaxBlueJTextField(javax.swing.JTextField maxBlueJTextField)
    {
        this.maxBlueJTextField = maxBlueJTextField;
    }
    
    public javax.swing.JTextField getMaxGreenJTextField()
    {
        return maxGreenJTextField;
    }
    
    public void setMaxGreenJTextField(javax.swing.JTextField maxGreenJTextField)
    {
        this.maxGreenJTextField = maxGreenJTextField;
    }
    
    public javax.swing.JTextField getMaxRedJTextField()
    {
        return maxRedJTextField;
    }
    
    public void setMaxRedJTextField(javax.swing.JTextField maxRedJTextField)
    {
        this.maxRedJTextField = maxRedJTextField;
    }
    
    public javax.swing.JTextField getMinBlueJTextField()
    {
        return minBlueJTextField;
    }
    
    public void setMinBlueJTextField(javax.swing.JTextField minBlueJTextField)
    {
        this.minBlueJTextField = minBlueJTextField;
    }
    
    public javax.swing.JTextField getMinGreenJTextField()
    {
        return minGreenJTextField;
    }
    
    public void setMinGreenJTextField(javax.swing.JTextField minGreenJTextField)
    {
        this.minGreenJTextField = minGreenJTextField;
    }
    
    public javax.swing.JTextField getMinRedJTextField()
    {
        return minRedJTextField;
    }
    
    public void setMinRedJTextField(javax.swing.JTextField minRedJTextField)
    {
        this.minRedJTextField = minRedJTextField;
    }
    
    public javax.swing.JLabel getYJLabel()
    {
        return yJLabel;
    }
    
    public void setYJLabel(javax.swing.JLabel yJLabel)
    {
        this.yJLabel = yJLabel;
    }
    
    public javax.swing.JDialog getColorAtActionJDialog()
    {
        return colorAtActionJDialog;
    }
    
    public void setColorAtActionJDialog(javax.swing.JDialog colorAtActionJDialog)
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
        colorAtActionJDialog = new javax.swing.JDialog();
        titleJLabel = new javax.swing.JLabel();
        locationJLabel = new javax.swing.JLabel();
        colorAtXJTextField = new javax.swing.JTextField();
        xJLabel = new javax.swing.JLabel();
        yJLabel = new javax.swing.JLabel();
        colorAtYJTextField = new javax.swing.JTextField();
        colorRangeJLabel = new javax.swing.JLabel();
        redJLabel = new javax.swing.JLabel();
        minJLabel = new javax.swing.JLabel();
        maxJLabel = new javax.swing.JLabel();
        greenJLabel = new javax.swing.JLabel();
        blueJLabel = new javax.swing.JLabel();
        minRedJTextField = new javax.swing.JTextField();
        maxRedJTextField = new javax.swing.JTextField();
        maxGreenJTextField = new javax.swing.JTextField();
        minBlueJTextField = new javax.swing.JTextField();
        maxBlueJTextField = new javax.swing.JTextField();
        okJButton = new javax.swing.JButton();
        minGreenJTextField = new javax.swing.JTextField();
        colorAtActionJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        colorAtActionJDialog.setMinimumSize(new java.awt.Dimension(350, 250));
        titleJLabel.setText("              Color At Action Options            ");

        locationJLabel.setText("Location:");

        xJLabel.setText("X:");

        yJLabel.setText("Y:");

        colorRangeJLabel.setText("Color Range");

        redJLabel.setText("Red:");

        minJLabel.setText("Minimum");

        maxJLabel.setText("Maximum");

        greenJLabel.setText("Green:");

        blueJLabel.setText("Blue:");

        okJButton.setText("OK");
        okJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                okJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout colorAtActionJDialogLayout = new javax.swing.GroupLayout(colorAtActionJDialog.getContentPane());
        colorAtActionJDialog.getContentPane().setLayout(colorAtActionJDialogLayout);
        colorAtActionJDialogLayout.setHorizontalGroup(
            colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(okJButton)
                            .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                                .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(blueJLabel)
                                            .addComponent(greenJLabel)
                                            .addComponent(redJLabel)
                                            .addComponent(colorRangeJLabel))
                                        .addGap(29, 29, 29))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, colorAtActionJDialogLayout.createSequentialGroup()
                                        .addComponent(locationJLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(xJLabel)
                                        .addGap(18, 18, 18)))
                                .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(minBlueJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(minGreenJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(minJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(minRedJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(colorAtXJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                                .addGap(16, 16, 16)))
                        .addGap(6, 6, 6)
                        .addComponent(yJLabel)
                        .addGap(24, 24, 24)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maxJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, colorAtActionJDialogLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(maxBlueJTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(maxRedJTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                    .addComponent(maxGreenJTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(colorAtYJTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, colorAtActionJDialogLayout.createSequentialGroup()
                        .addComponent(titleJLabel)
                        .addGap(33, 33, 33))))
        );
        colorAtActionJDialogLayout.setVerticalGroup(
            colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xJLabel)
                            .addComponent(locationJLabel)
                            .addComponent(colorAtXJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minJLabel)
                            .addComponent(colorRangeJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minRedJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(redJLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(greenJLabel)
                            .addComponent(minGreenJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(colorAtActionJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(blueJLabel)
                            .addComponent(minBlueJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(okJButton))
                    .addGroup(colorAtActionJDialogLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(colorAtYJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxRedJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxGreenJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxBlueJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        setPreferredSize(new java.awt.Dimension(100, 0));
        colorAtActionJButton.setText("Edit");
        colorAtActionJButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                colorAtActionJButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Color At:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(colorAtActionJButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(colorAtActionJButton))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void okJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okJButtonActionPerformed
    {//GEN-HEADEREND:event_okJButtonActionPerformed
// TODO add your handling code here:
        this.update();
        this.getColorAtActionJDialog().setVisible(false);
    }//GEN-LAST:event_okJButtonActionPerformed
    
    private void colorAtActionJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_colorAtActionJButtonActionPerformed
    {//GEN-HEADEREND:event_colorAtActionJButtonActionPerformed
// TODO add your handling code here:
        this.getColorAtActionJDialog().setVisible(true);
    }//GEN-LAST:event_colorAtActionJButtonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel blueJLabel;
    private javax.swing.JButton colorAtActionJButton;
    private javax.swing.JDialog colorAtActionJDialog;
    private javax.swing.JTextField colorAtXJTextField;
    private javax.swing.JTextField colorAtYJTextField;
    private javax.swing.JLabel colorRangeJLabel;
    private javax.swing.JLabel greenJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel locationJLabel;
    private javax.swing.JTextField maxBlueJTextField;
    private javax.swing.JTextField maxGreenJTextField;
    private javax.swing.JLabel maxJLabel;
    private javax.swing.JTextField maxRedJTextField;
    private javax.swing.JTextField minBlueJTextField;
    private javax.swing.JTextField minGreenJTextField;
    private javax.swing.JLabel minJLabel;
    private javax.swing.JTextField minRedJTextField;
    private javax.swing.JButton okJButton;
    private javax.swing.JLabel redJLabel;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JLabel xJLabel;
    private javax.swing.JLabel yJLabel;
    // End of variables declaration//GEN-END:variables
    
}
