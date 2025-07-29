package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.visitor.Visitor;

public class TextFieldItem extends TextItem
{
    public TextFieldItem(Canvas canvas, Visitor visitor, String label, String value, int maxSize, int layout, String altText, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor) {
        this(canvas, visitor, label, value, maxSize, layout, altText, Font.getDefaultFont(),
            backgroundBasicColor, foregroundBasicColor);
    }

    public TextFieldItem(Canvas canvas, Visitor visitor, String label, String value, int maxSize, int layout, String altText, 
            final Font font, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, layout, altText, backgroundBasicColor, foregroundBasicColor);
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
}
