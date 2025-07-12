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
package org.allbinary.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.paint.PaintableInterface;

public class PaintableToImageUtil
{
    private final static PaintableToImageUtil instance = new PaintableToImageUtil();
    
    public static Image getImage(PaintableInterface paintableInterface, int width, int height)
    throws Exception
    {
        Image image = GameFeatureImageCacheFactory.getInstance().get(
                instance.getClass().getName(), width, height);

        Graphics graphics = image.getGraphics();

        paintableInterface.paint(graphics);

        return image;
    }
}
