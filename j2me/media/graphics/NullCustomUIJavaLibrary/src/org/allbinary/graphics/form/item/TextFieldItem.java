package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Canvas;

import abcs.logic.basic.string.StringUtil;
import allbinary.graphics.color.BasicColor;
import allbinary.logic.basic.util.visitor.Visitor;

public class TextFieldItem extends TextItem
{
    public TextFieldItem(Canvas canvas, Visitor visitor, String label, String value, int maxSize, int layout, String altText, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, layout, altText, backgroundBasicColor, foregroundBasicColor);
    }
    
    public String getLabel() {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    public void setLabel(String label) {
    }

    public String getString() {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    public void setString(String string) {
    }
}
