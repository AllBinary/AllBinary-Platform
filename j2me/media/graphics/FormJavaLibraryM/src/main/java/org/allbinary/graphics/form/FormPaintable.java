/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.graphics.form;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.paint.Paintable;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class FormPaintable extends Paintable
{
    private final PaintableForm form;
    
    @JsConstructor
    public FormPaintable(PaintableForm form)
    {
        this.form = form;
    }

    @Override    
    @JsMethod
    public void paint(Graphics graphics)
    {
        this.form.paint(graphics);
    }

    @JsMethod
    public PaintableForm getForm()
    {
        return this.form;
    }
}
