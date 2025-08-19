package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.string.StringUtil;

public class CustomItem
{
    public static final CustomItem NULL_CUSTOM_ITEM = new CustomItem(
        StringUtil.getInstance().EMPTY_STRING, BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE);
    
    protected CustomItem(String label, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        
    }
    
    public boolean isFocusable()
    {
        return false;
    }
    
    public String getLabel() {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    public void setLabel(String label)
    {
    }

    public int getHeight() {
        return 0;
    }

    public void setHeight(int height) {
    }

    public float getValue() {
        return 0.0f;
    }

    public void setValue(float value) {
    }

    public int getMinimumWidth()
    {
        return 0;
    }

    public int getMinimumHeight()
    {
        return 0;
    }
    
}
