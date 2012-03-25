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
package allbinary.graphics.form;

import javax.microedition.lcdui.Graphics;

import allbinary.graphics.paint.Paintable;

public class FormPaintable extends Paintable
{
    private final PaintableForm form;
    
    public FormPaintable(PaintableForm form)
    {
        this.form = form;
    }
    
    public void paint(Graphics graphics)
    {
        this.form.paint(graphics);
    }

    public PaintableForm getForm()
    {
        return form;
    }
}
