package org.allbinary.graphics.form.item;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class ABTextItem
extends CustomCustomItem 
implements ABCustomItemInterface
{
    @JsConstructor
    public ABTextItem(String label, int layout, String altText,
                      BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, backgroundBasicColor, foregroundBasicColor);
    }

    @JsMethod
    public void keyPressed(int keyCode)
    {
        
    }

    @Override
    @JsMethod
    public void paintUnselected(Graphics graphics, int x, int y)
    {
        
    }

    @JsMethod
    public ABStringComponent getLabelStringComponent()
    {
        return ABStringComponent.NULL_STRING_COMPONENT;
    }
}
