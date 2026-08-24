package org.allbinary.graphics.form.item;

import jsinterop.annotations.JsType;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class ABStringComponent
{
    @JsProperty
    public static final ABStringComponent NULL_STRING_COMPONENT = new ABStringComponent(BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE);
    
    @JsMethod
    public BasicColor getBackgroundBasicColor() {
        return BasicColorFactory.getInstance().WHITE;
    }

    @JsConstructor
    public ABStringComponent(final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor) {
    }

    @JsMethod
    public BasicColor getForegroundBasicColor() {
        return BasicColorFactory.getInstance().WHITE;
    }

    @JsMethod
    public void setForegroundBasicColor(BasicColor foregroundBasicColor) {
    }
}
