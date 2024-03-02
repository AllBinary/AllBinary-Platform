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
package org.allbinary.media.image;

import javax.microedition.lcdui.Image;

import org.allbinary.graphics.Anchor;
import org.allbinary.image.ImageCache;

public class ImageScaleUtil
{
    private static final ImageScaleUtil instance = new ImageScaleUtil();

    public static ImageScaleUtil getInstance()
    {
        return instance;
    }

    private ImageScaleUtil()
    {
    }

    private int anchor = Anchor.TOP_LEFT;

    public Image createImage(final ImageCache imageCache, final Image originalImage,
            final float scaleNominatorX, final float scaleDenominatorX, 
            final float scaleNominatorY, final float scaleDenominatorY, final boolean cached) 
    throws Exception
    {
        //float scaleX = scaleNominatorX / scaleDenominatorX;
        //float scaleY = scaleNominatorY / scaleDenominatorY;
        
        //return this.createImage(imageCache, originalImage, scaleX, scaleY, cached);
        return originalImage;
        //throw new RuntimeException("Image Scaling is not supported by J2ME");
    }
    
//    public Image createImage(final ImageCache imageCache, final Image originalImage, 
//            final float scaleX, final float scaleY, final boolean cached) 
//    throws Exception
//    {
//    }
//    
//    public void scale(final Image image, final Matrix matrix, final float scaleX, final float scaleY)
//    {
//    }
}
