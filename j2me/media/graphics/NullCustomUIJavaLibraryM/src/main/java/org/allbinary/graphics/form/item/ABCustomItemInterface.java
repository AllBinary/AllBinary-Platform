package org.allbinary.graphics.form.item;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;
import jsinterop.annotations.JsMethod;


@JsType
public interface ABCustomItemInterface
{
    @JsMethod
    int getMinimumWidth();
    @JsMethod
    int getMinimumHeight();
    @JsMethod
    String getLabel();
    @JsMethod
    void paintXY(Graphics graphics, int x, int y);
    @JsMethod
    void paintUnselected(Graphics graphics, int x, int y);
}
