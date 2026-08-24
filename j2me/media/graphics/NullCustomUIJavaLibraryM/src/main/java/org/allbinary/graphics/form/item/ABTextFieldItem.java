package org.allbinary.graphics.form.item;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.visitor.Visitor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class ABTextFieldItem extends ABTextItem
{
    private Font font;
    
    @JsConstructor
    public ABTextFieldItem(Canvas canvas, Visitor visitor, String label, String value, int maxSize, int layout, String altText,
                           final Font font, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, layout, altText, backgroundBasicColor, foregroundBasicColor);
        
        this.font = font;
    }
    
    @Override
    @JsMethod
    public String getLabel() {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    @Override
    @JsMethod
    public void setLabel(String label) {
    }

    @JsMethod
    public String getString() {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    @JsMethod
    public void setString(String string) {
    }
    
    @JsMethod
    public void setFocus(boolean state)
    {
    }
    
    @JsMethod
    public int getFontHeight() {
        return this.font.getHeight();
    }

    @JsMethod
    public void setBackgroundBasicColorP(final BasicColor basicColor)
    {

    }

    @JsMethod
    public void setForegroundBasicColorP(final BasicColor basicColor)
    {

    }
    
}
