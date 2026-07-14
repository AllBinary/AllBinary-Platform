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
package org.allbinary.animation.text;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Font;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.form.item.CustomTextBox;

/**
 *
 * @author User
 */
public class CustomTextBox2 extends CustomTextBox 
{        
    public CustomTextBox2(final CommandListener cmdListener, final String label, final String text, 
        final int maxSize, final int constraints, final Font font, 
        final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
        throws Exception
    {
        super(cmdListener, label, text, maxSize, constraints, font, backgroundBasicColor, foregroundBasicColor);
        
        this.getTextFieldItem().setFocus(true);
        
    }

}
