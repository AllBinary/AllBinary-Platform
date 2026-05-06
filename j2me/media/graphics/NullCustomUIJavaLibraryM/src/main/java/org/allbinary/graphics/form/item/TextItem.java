package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;

public class TextItem 
extends CustomCustomItem 
implements CustomItemInterface
{
    public TextItem(String label, int layout, String altText, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, backgroundBasicColor, foregroundBasicColor);
    }

    public void keyPressed(int keyCode)
    {
        
    }

    @Override
    public void paintUnselected(Graphics graphics, int x, int y)
    {
        
    }

    public ABStringComponent getLabelStringComponent()
    {
        return ABStringComponent.NULL_STRING_COMPONENT;
    }
}
