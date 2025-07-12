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

import java.awt.image.BufferedImage;

import javax.microedition.lcdui.Image;

import org.allbinary.logic.communication.log.PreLogUtil;
import org.microemu.device.j2se.J2SEImmutableImage;
import org.microemu.device.j2se.J2SEMutableImage;

public class ImageRotationUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final ImageRotationUtil instance = new ImageRotationUtil();
    
    public static ImageRotationUtil getInstance()
    {
        return instance;
    }
    
    private final ImageJ2SERotationUtil imageJ2SERotationUtil = ImageJ2SERotationUtil.getInstance();
    
    private ImageRotationUtil()
    {
    }

    public void rotateImage(final Image originalImage, final Image image, final int totalAngle) {
        
        if (image.isMutable())
        {
            //PreLogUtil.put("3a", this, "createRotatedImage");
            final J2SEMutableImage j2seImage = (J2SEMutableImage) image;
            final BufferedImage newBufferedImage = (BufferedImage) j2seImage.getImage();
             //PreLogUtil.put("4", this, "createRotatedImage");
            
            java.awt.Image originalAwtImage = null;
            if(originalImage.isMutable())
            {
                //PreLogUtil.put("3a", this, "createRotatedImage");
                J2SEMutableImage originalJ2SEImage = (J2SEMutableImage) originalImage;
                originalAwtImage = (java.awt.Image) originalJ2SEImage.getImage();
                //PreLogUtil.put("4", this, "createRotatedImage");
            }
            else
            {
                //PreLogUtil.put("3b", this, "createRotatedImage");
                final J2SEImmutableImage originalJ2SEImage = (J2SEImmutableImage) originalImage;
                originalAwtImage = (java.awt.Image) originalJ2SEImage.getImage();
                //PreLogUtil.put("4b", this, "createRotatedImage");
            }
            
            imageJ2SERotationUtil.rotateImage(originalAwtImage, newBufferedImage, totalAngle);
        } else {
            PreLogUtil.put("Not Mutable", this, "getRotatedImage", new Exception());
        }
    }
            
    public Image createRotatedImage(Image originalImage, int rotationInDegrees)
            throws Exception
    {
        //PreLogUtil.put("originalImage: " + originalImage + " rotationInDegrees: " + rotationInDegrees, this, "createRotatedImage");
        final Image image = ImageCreationUtil.getInstance().getInstance(
                originalImage.getWidth() , originalImage.getHeight());

        if (image.isMutable())
        {
            java.awt.Image originalAwtImage = null;
            if(originalImage.isMutable())
            {
                //PreLogUtil.put("3a", this, "createRotatedImage");
                J2SEMutableImage originalJ2SEImage = (J2SEMutableImage) originalImage;
                originalAwtImage = (java.awt.Image) originalJ2SEImage.getImage();
                //PreLogUtil.put("4", this, "createRotatedImage");
            }
            else
            {
                //PreLogUtil.put("3b", this, "createRotatedImage");
                J2SEImmutableImage originalJ2SEImage = (J2SEImmutableImage) originalImage;
                originalAwtImage = (java.awt.Image) originalJ2SEImage.getImage();
                //PreLogUtil.put("4b", this, "createRotatedImage");
            }

            J2SEMutableImage j2seImage = (J2SEMutableImage) image;            
            BufferedImage newBufferedImage = (BufferedImage) j2seImage.getImage();

            BufferedImage bufferedImage = 
                    ImageJ2SERotationUtil.getInstance()
                            .getRotatedImage(originalAwtImage, newBufferedImage, rotationInDegrees);

            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }
}
