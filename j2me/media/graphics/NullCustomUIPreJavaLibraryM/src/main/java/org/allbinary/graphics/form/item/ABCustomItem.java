package org.allbinary.graphics.form.item;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Screen;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;


@JsType
public class ABCustomItem
{
    public static Object NULL_CUSTOM_ITEM = NullUtil.getInstance().NULL_OBJECT;
    
    @JsMethod
    public static ABCustomItem getNullInstance() {
        
        if(ABCustomItem.NULL_CUSTOM_ITEM == NullUtil.getInstance().NULL_OBJECT) {
            ABCustomItem.NULL_CUSTOM_ITEM = new ABCustomItem(StringUtil.getInstance().EMPTY_STRING, BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE);
        }
        
        return (ABCustomItem) ABCustomItem.NULL_CUSTOM_ITEM;
    }
    
    @JsConstructor
    protected ABCustomItem(String label, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
    }
    
    @JsMethod
    public void setOwner(Screen owner)
    {
    }
    
    @JsMethod
    public void paintXY(Graphics graphics, int x, int y)
    {
        
    }

    @JsMethod
    public void paintUnselected(Graphics graphics, int x, int y) {

    }

    @JsMethod
    public boolean isFocusable()
    {
        return false;
    }
    
    @JsMethod
    public String getLabel() {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    @JsMethod
    public void setLabel(String label)
    {
    }

    @JsMethod
    public int getHeight() {
        return 0;
    }

    @JsMethod
    public void setHeight(int height) {
    }

    @JsMethod
    public float getValue() {
        return 0.0f;
    }

    @JsMethod
    public void setValue(float value) {
    }

    @JsMethod
    public int getMinimumWidth()
    {
        return 0;
    }

    @JsMethod
    public int getMinimumHeight()
    {
        return 0;
    }
    
    @JsMethod
    public void preMeasurement(final Graphics graphics) {
        
    }

}
