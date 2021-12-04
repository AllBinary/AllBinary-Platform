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

public class ImageCopyUtil
{
    private static final ImageCopyUtil instance = new ImageCopyUtil();
    
    public static ImageCopyUtil getInstance()
    {
        return instance;
    }
    
    private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();

    private ImageCopyUtil()
    {
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    // String resource,
    public Image createImage(Image originalImage)
            throws Exception
    {
        final Image image = imageCreationUtil.getInstance(
                originalImage.getWidth() , originalImage.getHeight());

        if (image.isMutable())
        {
            image.getGraphics().drawImage(originalImage, 0, 0, anchor);
            
            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }

    public Image createImage(Image originalImage, float canvasScale)
            throws Exception
    {
        final int newWidth = (int) (originalImage.getWidth() * canvasScale);
        final int newHeight = (int) (originalImage.getHeight() * canvasScale);
        
        final Image image = imageCreationUtil.getInstance(newWidth, newHeight);

        if (image.isMutable())
        {
            image.getGraphics().drawImage(originalImage, newWidth - originalImage.getWidth(), newHeight - originalImage.getHeight(), anchor);
            
            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }
}
