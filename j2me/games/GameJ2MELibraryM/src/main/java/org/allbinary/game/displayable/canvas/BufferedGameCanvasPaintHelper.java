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
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.paint.ProcessPaintable;
import org.allbinary.util.CircularIndexUtil;

public class BufferedGameCanvasPaintHelper extends ProcessPaintable
{
    private AllBinaryGameCanvas gameCanvas;

    // Buffering Magic
    private final static int MAX_IMAGES = 4;
    private final Image offScreenImage[] = new Image[MAX_IMAGES];
    private Image previousImage;

    private final CircularIndexUtil circularIndexUtil = 
        CircularIndexUtil.getInstance(MAX_IMAGES);
    private final CircularIndexUtil drawCircularIndexUtil = 
        CircularIndexUtil.getInstance(MAX_IMAGES - 1, MAX_IMAGES);
    
    public BufferedGameCanvasPaintHelper(AllBinaryGameCanvas gameCanvas)
    throws Exception
    {
        throw new Exception("No Longer Used");
        
        /*
        this.gameCanvas = gameCanvas;

        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

        for (int index = 0; index < MAX_IMAGES; index++)
        {
            this.offScreenImage[index] = GameFeatureImageCacheFactory.getInstance().get(
                    this, displayInfo.getLastWidth(), displayInfo.getLastHeight());

            this.gameCanvas.clear(this.offScreenImage[index].getGraphics());
        }

        this.previousImage = this.offScreenImage[0];
        */
    }

    public void process()
    {
        this.gameCanvas.draw(this.offScreenImage[this.circularIndexUtil.getIndex()].getGraphics());

        this.circularIndexUtil.next();
        this.drawCircularIndexUtil.next();
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        this.previousImage = this.offScreenImage[this.drawCircularIndexUtil.getIndex()];
        
        graphics.drawImage(this.previousImage, 0, 0, anchor);
        
        //this.gameCanvas.setDisplayed(true);
    }
}
