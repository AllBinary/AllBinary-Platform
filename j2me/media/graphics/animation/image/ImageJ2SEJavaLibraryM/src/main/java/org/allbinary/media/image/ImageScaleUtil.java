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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.microedition.lcdui.Image;
import org.allbinary.image.ImageCache;
import org.microemu.device.j2se.J2SEImmutableImage;
import org.microemu.device.j2se.J2SEMutableImage;

public class ImageScaleUtil
{
    private static final ImageScaleUtil instance = new ImageScaleUtil();

    public static ImageScaleUtil getInstance()
    {
        return instance;
    }

    private final ImageJ2SEUtil imageJ2SEUtil = ImageJ2SEUtil.getInstance();
    
    private ImageScaleUtil()
    {
    }

    //private int anchor = Anchor.TOP_LEFT;

    public Image createImage(final ImageCache imageCache, final Image originalImage,
            final float scaleNominatorX, final float scaleDenominatorX, 
            final float scaleNominatorY, final float scaleDenominatorY, final boolean cached) 
    throws Exception
    {
        //float scaleX = scaleNominatorX / scaleDenominatorX;
        //float scaleY = scaleNominatorY / scaleDenominatorY;
        
        //return this.createImage(imageCache, originalImage, scaleX, scaleY, cached);
        throw new RuntimeException("Image Scaling is not supported by J2SE with this call yet");
    }

 
    public void scale(final Image image, final Image maxSizeImage, final float scaleX, final float scaleY) {

        BufferedImage bufferedImage;
        //java.awt.Image newBufferedImage;
        if(image.isMutable()) {
            final J2SEMutableImage j2seImage = (J2SEMutableImage) image;
            bufferedImage = (BufferedImage) j2seImage.getImage();
        } else {
            final J2SEImmutableImage j2seImage = (J2SEImmutableImage) image;
            //sun.awt.image.ToolkitImage cannot be cast to class java.awt.image.BufferedImage
            bufferedImage = (BufferedImage) j2seImage.getImage();                        
        }

        BufferedImage newBufferedImage;
        //java.awt.Image newBufferedImage;
        if(maxSizeImage.isMutable()) {
            final J2SEMutableImage j2seImage = (J2SEMutableImage) maxSizeImage;
            newBufferedImage = (BufferedImage) j2seImage.getImage();
        } else {
            final J2SEImmutableImage j2seImage = (J2SEImmutableImage) maxSizeImage;
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
