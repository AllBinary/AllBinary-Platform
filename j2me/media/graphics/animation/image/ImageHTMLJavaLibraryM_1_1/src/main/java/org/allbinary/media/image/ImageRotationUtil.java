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
    private static final ImageRotationUtil instance = new ImageRotationUtil();
    
    public static ImageRotationUtil getInstance()
    {
        return instance;
    }
    
    private ImageRotationUtil()
    {
    }

    public Image rotateImage(final Image originalImage, final Image image, final int totalAngle) {
        if (image.isMutable())
        {
            final PlaynMutableImage htmlImage = (PlaynMutableImage) image;
            final CanvasImage canvasImage = (CanvasImage) htmlImage.getImage();
            final CanvasSurface canvasSurface = htmlImage.getCanvasSurface(canvasImage);
            
            canvasSurface.translate(originalImage.getWidth() / 2, originalImage.getHeight() / 2);
            return this.rotateImage(originalImage, image, canvasSurface, totalAngle);
        } else {
            return null;
        }
    }
    
    public Image rotateImageClear(final Image originalImage, final Image image, final CanvasSurface canvasSurface, final int totalAngle) {
        if (image.isMutable())
        {
            canvasSurface.save();
            canvasSurface.translate(-originalImage.getWidth() / 2, -originalImage.getHeight() / 2);
            canvasSurface.clear();
            canvasSurface.translate(originalImage.getWidth() / 2, originalImage.getHeight() / 2);
            
            return this.rotateImage(originalImage, image, canvasSurface, totalAngle);
        } else {
            return null;
        }
    }
    
    public Image rotateImage(final Image originalImage, final Image image, final CanvasSurface canvasSurface, final int totalAngle) {
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
            
            canvasSurface.rotate((float) Math.toRadians(totalAngle));
            canvasSurface.drawImage(originalPlayNImage, -originalPlayNImage.width() / 2, -originalPlayNImage.height() / 2);
            canvasSurface.restore();

            return image;
        }
        else
        {
            return null;
        }        
    }

    public Image createRotatedImage(final Image originalImage, final int rotationInDegrees)
            throws Exception
    {
        //PreLogUtil.put("originalImage: " + originalImage + " rotationInDegrees: " + rotationInDegrees, this, "createRotatedImage");
        final Image image = ImageCreationUtil.getInstance().getInstance(
                originalImage.getWidth() , originalImage.getHeight());
        final Image rotatedImage = this.rotateImage(originalImage, image, rotationInDegrees);
        if(rotatedImage == null) {
            throw new Exception("Not Mutable");
        }
        return rotatedImage;
    }
}
