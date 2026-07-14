package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.visitor.Visitor;

public class ABTextFieldItem extends ABTextItem
{
    private Font font;
    
    public ABTextFieldItem(Canvas canvas, Visitor visitor, String label, String value, int maxSize, int layout, String altText,
                           final Font font, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, layout, altText, backgroundBasicColor, foregroundBasicColor);
        
        this.font = font;
    }
    
    @Override
    public String getLabel() {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    @Override
    public void setLabel(String label) {
    }

    public String getString() {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    public void setString(String string) {
    }
    
    public void setFocus(boolean state)
    {
    }
    
    public int getFontHeight() {
        return this.font.getHeight();
    }
    
}
