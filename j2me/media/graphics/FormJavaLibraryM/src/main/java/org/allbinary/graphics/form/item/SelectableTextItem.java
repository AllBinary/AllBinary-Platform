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
package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;

public class SelectableTextItem 
extends TextItem 
{
    public SelectableTextItem(String label, int layout, String altText, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, layout, altText, backgroundBasicColor, foregroundBasicColor);
    }

    public boolean isFocusable()
    {
        return true;
    }
    
    @Override
    public void paint(Graphics graphics, int x, int y)
    {
        StringComponent stringComponent = this.getLabelStringComponent();
        
        if(stringComponent != null)
        {
            graphics.setColor(stringComponent.getForegroundBasicColor().intValue());
        }

        graphics.drawString(this.getLabel(), x + 2, y, 0);
    }
    
}
