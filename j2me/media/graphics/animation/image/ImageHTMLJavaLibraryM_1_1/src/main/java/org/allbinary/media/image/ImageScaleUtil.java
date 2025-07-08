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
import org.allbinary.logic.communication.log.PreLogUtil;
import org.microemu.device.playn.PlaynImmutableImage;
import org.microemu.device.playn.PlaynMutableImage;
import playn.core.CanvasImage;
import playn.core.CanvasSurface;

public class ImageScaleUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final ImageScaleUtil instance = new ImageScaleUtil();

    public static ImageScaleUtil getInstance()
    {
        return instance;
    }

    //private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();
    
    //private int anchor = Anchor.TOP_LEFT;
    
    private ImageScaleUtil()
    {
    }

    public Image createImage(final ImageCache imageCache, final Image originalImage,
        final float scaleNominatorX, final float scaleDenominatorX,
        final float scaleNominatorY, final float scaleDenominatorY, final boolean cached)
        throws Exception {

        return this.createImage(imageCache, originalImage, scaleNominatorX, scaleDenominatorX, scaleNominatorY, scaleDenominatorY, cached, true);

    }
    
    //private int anchor = Anchor.TOP_LEFT;
    public Image createImage(final ImageCache imageCache, final Image originalImage,
        final float scaleNominatorX, final float scaleDenominatorX,
        final float scaleNominatorY, final float scaleDenominatorY, final boolean cached, final boolean mutable)
        throws Exception {

        final float scaleX = scaleNominatorX / scaleDenominatorX;
        final float scaleY = scaleNominatorY / scaleDenominatorY;
        
        return this.createImage(imageCache, originalImage, scaleX, scaleY, cached);
    }

    public Image createImage(final ImageCache imageCache, final Image originalImage, 
            final float scaleX, final float scaleY, final boolean cached) 
    throws Exception
    {
        return this.createImage(originalImage, scaleX, scaleY);
    }    

    public Image createImage(final Image originalImage, final float scaleX, final float scaleY) 
    throws Exception
    {
        //PreLogUtil.put("originalImage: " + originalImage + " scaleX: " + scaleX + " scaleY: " + scaleY, this, "createImage");
        //PreLogUtil.put("originalImage: " + originalImage.getWidth() + " " + originalImage.getHeight(), this, "createImage");
        final Image image = ImageCreationUtil.getInstance().getInstance(
                (int) (originalImage.getWidth() * scaleX), (int) (originalImage.getHeight() * scaleY));
        
        //PreLogUtil.put("Image: " + image.getWidth() + " " + image.getHeight(), this, "createImage");
        
        if (image.isMutable())
        {
            playn.core.Image originalPlayNImage = null;
            if(originalImage.isMutable())
            {
                //PreLogUtil.put("3a", this, "createImage");
                final PlaynMutableImage originalHTMLImage = (PlaynMutableImage) originalImage;
                originalPlayNImage = (playn.core.Image) originalHTMLImage.getImage();
                //PreLogUtil.put("4", this, "createImage");
            }
            else
            {
                //PreLogUtil.put("3b", this, "createImage");
                final PlaynImmutableImage originalHTMLImage = (PlaynImmutableImage) originalImage;
                originalPlayNImage = (playn.core.Image) originalHTMLImage.getImage();
                //PreLogUtil.put("4b", this, "createImage");
            }

            final PlaynMutableImage htmlImage = (PlaynMutableImage) image;
            final CanvasImage canvasImage = (CanvasImage) htmlImage.getImage();
            final CanvasSurface canvasSurface = htmlImage.getCanvasSurface(canvasImage);
            //canvasSurface.translate(-3, 3);
            //canvasSurface.drawImage(originalPlayNImage, 0, 0, image.getWidth() + 3, image.getHeight(), 0, 0, originalImage.getWidth(), originalImage.getHeight());
            canvasSurface.drawImage(originalPlayNImage, 0, 0, image.getWidth(), image.getHeight(), 0, 0, originalImage.getWidth(), originalImage.getHeight());
            
            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }

}
