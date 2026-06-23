package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Screen;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.string.StringUtil;

public class ABCustomItem
{
    public static final ABCustomItem NULL_CUSTOM_ITEM = new ABCustomItem(
        StringUtil.getInstance().EMPTY_STRING, BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE);
    
    protected ABCustomItem(String label, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
    }
    
    public void setOwner(Screen owner)
    {
    }
    
    public void paintXY(Graphics graphics, int x, int y)
    {
        
    }

    public void paintUnselected(Graphics graphics, int x, int y) {

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
    
    public void preMeasurement(final Graphics graphics) {
        
    }

}
