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
package allbinary.game.displayable.canvas;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.paint.ColorFillPaintable;
import allbinary.game.paint.ColorFillPaintableFactory;
import allbinary.graphics.paint.Paintable;

public class BasicPaintablesCanvas extends GameCommandCanvas
{
    private final Paintable[] paintableArray;
    private final ColorFillPaintable colorFillPaintable;

    public BasicPaintablesCanvas(CommandListener cmdListener, 
            AllBinaryGameLayerManager allBinaryGameLayerManager, Paintable[] paintableArray)
            throws Exception
    {

        super(cmdListener,
                allBinaryGameLayerManager.getBackgroundBasicColor(),
                allBinaryGameLayerManager.getForegroundBasicColor());

        this.paintableArray = paintableArray;
        
        this.colorFillPaintable = 
            ColorFillPaintableFactory.getInstance(
                    allBinaryGameLayerManager.getBackgroundBasicColor());
    }

    /*
    public BasicPaintablesCanvas(Paintable[] paintableArray)
            throws Exception
    {   
        this.paintableArray = paintableArray;
    }
    */

    public void paint(Graphics graphics)
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