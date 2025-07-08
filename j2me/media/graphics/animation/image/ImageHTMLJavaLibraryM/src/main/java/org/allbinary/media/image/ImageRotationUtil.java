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

import org.microemu.device.playn.PlaynImmutableImage;
import org.microemu.device.playn.PlaynMutableImage;
import playn.core.CanvasImage;
import playn.core.CanvasSurface;

public class ImageRotationUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final ImageRotationUtil instance = new ImageRotationUtil();
    
    public static ImageRotationUtil getInstance()
    {
        return instance;
    }
    
    private ImageRotationUtil()
    {
    }

    public Image createRotatedImage(final Image originalImage, final int rotationInDegrees)
            throws Exception
    {
        //PreLogUtil.put("originalImage: " + originalImage + " rotationInDegrees: " + rotationInDegrees, this, "createRotatedImage");
        final Image image = ImageCreationUtil.getInstance().getInstance(
                originalImage.getWidth() , originalImage.getHeight());

        if (image.isMutable())
        {
            playn.core.Image originalPlayNImage = null;
            if(originalImage.isMutable())
            {
                //PreLogUtil.put("3a", this, "createRotatedImage");
                final PlaynMutableImage originalHTMLImage = (PlaynMutableImage) originalImage;
                originalPlayNImage = (playn.core.Image) originalHTMLImage.getImage();
                //PreLogUtil.put("4", this, "createRotatedImage");
            }
            else
            {
                //PreLogUtil.put("3b", this, "createRotatedImage");
                final PlaynImmutableImage originalHTMLImage = (PlaynImmutableImage) originalImage;
                originalPlayNImage = (playn.core.Image) originalHTMLImage.getImage();
                //PreLogUtil.put("4b", this, "createRotatedImage");
            }

            final PlaynMutableImage htmlImage = (PlaynMutableImage) image;
            final CanvasImage canvasImage = (CanvasImage) htmlImage.getImage();
            final CanvasSurface canvasSurface = htmlImage.getCanvasSurface(canvasImage);
            
            canvasSurface.translate(originalPlayNImage.width() / 2, originalPlayNImage.height() / 2);
            canvasSurface.rotate((float) Math.toRadians(rotationInDegrees));
            canvasSurface.drawImage(originalPlayNImage, -originalPlayNImage.width() / 2, -originalPlayNImage.height() / 2);

            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }
}
