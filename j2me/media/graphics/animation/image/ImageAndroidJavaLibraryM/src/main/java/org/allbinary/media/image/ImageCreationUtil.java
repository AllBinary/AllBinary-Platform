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

import org.allbinary.image.GameFeatureImageCacheFactory;

public class ImageCreationUtil
{
    private static final ImageCreationUtil instance = new ImageCreationUtil();

    public static ImageCreationUtil getInstance()
    {
        return instance;
    }
    
    private ImageCreationUtil()
    {
        
    }
    
    public Image getInstance(int width, int height) throws Exception
    {
        Image image = GameFeatureImageCacheFactory.getInstance().get(
                    this.getClass().getName(), width, height);

        return image;
    }
}
