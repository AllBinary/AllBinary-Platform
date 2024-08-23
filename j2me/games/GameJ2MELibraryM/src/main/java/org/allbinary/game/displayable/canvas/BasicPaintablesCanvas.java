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
package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.paint.ColorFillBasePaintable;
import org.allbinary.game.paint.ColorFillPaintableFactory;
import org.allbinary.graphics.paint.Paintable;

public class BasicPaintablesCanvas extends GameCommandCanvas
{
    private final Paintable[] paintableArray;
    private final ColorFillBasePaintable colorFillPaintable;

    public BasicPaintablesCanvas(final CommandListener cmdListener, final String name, 
            final AllBinaryGameLayerManager allBinaryGameLayerManager, final Paintable[] paintableArray)
            throws Exception
    {

        super(cmdListener, name,
                allBinaryGameLayerManager.getBackgroundBasicColor(),
                allBinaryGameLayerManager.getForegroundBasicColor());

        this.paintableArray = paintableArray;
        
        this.colorFillPaintable = 
            ColorFillPaintableFactory.getInstance(
                    allBinaryGameLayerManager.getBackgroundBasicColor(), false);
    }

    /*
    public BasicPaintablesCanvas(Paintable[] paintableArray)
            throws Exception
    {   
        this.paintableArray = paintableArray;
    }
    */

    public void paint(final Graphics graphics)
    {
        this.colorFillPaintable.paint(graphics);
    
        graphics.setColor(this.foregroundColor);

        for(int index = this.paintableArray.length - 1; index >= 0; index--)
        {
            this.paintableArray[index].paint(graphics);
        }

        super.paint(graphics);
    }
}