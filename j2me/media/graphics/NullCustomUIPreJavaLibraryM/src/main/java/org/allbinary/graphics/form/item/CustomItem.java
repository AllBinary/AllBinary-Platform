package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Graphics;

import abcs.logic.basic.string.StringUtil;
import allbinary.graphics.color.BasicColor;

public class CustomItem
{
    protected CustomItem(String label, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        
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
        return 0;
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
