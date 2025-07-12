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

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.microedition.lcdui.Image;

import org.allbinary.image.ImageCache;
import org.microemu.device.j2se.J2SEImmutableImage;
import org.microemu.device.j2se.J2SEMutableImage;

public class ImageScaleUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final ImageScaleUtil instance = new ImageScaleUtil();

    public static ImageScaleUtil getInstance()
    {
        return instance;
    }

    private final ImageJ2SEUtil imageJ2SEUtil = ImageJ2SEUtil.getInstance();
    private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();
    
    private ImageScaleUtil()
    {
    }

    //private int anchor = Anchor.TOP_LEFT;

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
        
        final int width = originalImage.getWidth();
        final int height = originalImage.getHeight();

        final float scaleX = scaleNominatorX / scaleDenominatorX;
        final float scaleY = scaleNominatorY / scaleDenominatorY;

        Image image;        
        if(cached)
        {
            image = imageCache.get(this.getClass().getName(), (int) (width * scaleX), (int) (height * scaleY));
        }
        else
        {
            //TWB - Image Create
            //image = Image.createImage(width, height);
            image = imageCache.get("createImage", (int) (width * scaleX), (int) (height * scaleY));
        }
        
        this.scale(originalImage, image, scaleX, scaleY);
        return image;
        //throw new RuntimeException("Image Scaling is not supported by J2SE with this call yet");
    }


    public void scale(final Image originalImage, final Image[] originalImageArray, final Image[] ximageToShowArray, final int unused, final float scaleX, final float scaleY, final float maxScaleX, final float maxScaleY) throws Exception {
        //Set the max image size needed.
        if (maxScaleX * originalImage.getWidth() > originalImageArray[0].getWidth()
            || maxScaleY * originalImage.getHeight() > originalImageArray[0].getHeight()) {
            //logUtil.put(new StringMaker().append("scale canvas: ").append(maxScaleX).toString(), this, this.commonStrings.UPDATE);
            originalImageArray[0] = this.imageCreationUtil.createImage(originalImage.getWidth(), originalImage.getHeight(), maxScaleX, maxScaleY);
        }

        //Set the new original image to the current scale
        //logUtil.put(new StringMaker().append("scaleX: ").append(scaleX).append("scaleY: ").append(scaleY).toString(), this, this.commonStrings.UPDATE);
        this.scale(originalImage, originalImageArray[0], scaleX, scaleY);
    }    
 
    public void scale(final Image originalImage, final Image newMaxSizeImage, final float scaleX, final float scaleY) {
    
        BufferedImage bufferedImage;
        //java.awt.Image newBufferedImage;
        if(originalImage.isMutable()) {
            final J2SEMutableImage j2seImage = (J2SEMutableImage) originalImage;
            bufferedImage = (BufferedImage) j2seImage.getImage();
        } else {
            final J2SEImmutableImage j2seImage = (J2SEImmutableImage) originalImage;
            //sun.awt.image.ToolkitImage cannot be cast to class java.awt.image.BufferedImage
            bufferedImage = (BufferedImage) j2seImage.getImage();
        }

        BufferedImage newBufferedImage;
        //java.awt.Image newBufferedImage;
        if(newMaxSizeImage.isMutable()) {
            final J2SEMutableImage j2seImage = (J2SEMutableImage) newMaxSizeImage;
            newBufferedImage = (BufferedImage) j2seImage.getImage();
        } else {
            final J2SEImmutableImage j2seImage = (J2SEImmutableImage) newMaxSizeImage;
            //sun.awt.image.ToolkitImage cannot be cast to class java.awt.image.BufferedImage
            newBufferedImage = (BufferedImage) j2seImage.getImage();
        }
        
        final AffineTransform at = AffineTransform.getScaleInstance(scaleX, scaleY);

        final Graphics2D g = newBufferedImage.createGraphics();
        g.setBackground(imageJ2SEUtil.TRANSPARENT_COLOR);
        g.clearRect(0, 0, newBufferedImage.getWidth(), newBufferedImage.getHeight());
        g.drawRenderedImage(bufferedImage, at);
        
    }
}
