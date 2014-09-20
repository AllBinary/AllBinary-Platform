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
    
    private ImageCopyUtil()
    {
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    // String resource,
    public Image createImage(Image originalImage)
            throws Exception
    {
        Image image = ImageCreationUtil.getInstance().getInstance(
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
}
