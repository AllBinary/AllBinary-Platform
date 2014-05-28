/*
 *Copyright (c) 2006 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: September 23, 2006
 *
 *
 *Modified By         When       ?
 *
 */

package org.allbinary.media.image;

import java.awt.image.BufferedImage;
import javax.microedition.lcdui.Image;

import org.microemu.device.j2se.J2SEImmutableImage;
import org.microemu.device.j2se.J2SEMutableImage;

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

    public Image createRotatedImage(Image originalImage, int rotationInDegrees)
            throws Exception
    {
        //PreLogUtil.put("originalImage: " + originalImage + " rotationInDegrees: " + rotationInDegrees, this, "createRotatedImage");
        Image image = ImageCreationUtil.getInstance().getInstance(
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
