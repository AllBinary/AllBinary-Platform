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

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.paint.ProcessPaintable;

public class GameCanvasPaintHelper extends ProcessPaintable
{
    private AllBinaryGameCanvas gameCanvas;

    public GameCanvasPaintHelper(AllBinaryGameCanvas gameCanvas)
    {
        this.gameCanvas = gameCanvas;
    }

    @Override
    public void process()
    {
    }

    @Override
    public void paint(Graphics graphics)
    {
        this.gameCanvas.draw(graphics);
        //this.gameCanvas.setDisplayed(true);
    }
}
