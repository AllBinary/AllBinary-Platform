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
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final ImageCreationUtil instance = new ImageCreationUtil();

    public static ImageCreationUtil getInstance()
    {
        return instance;
    }
    
    private ImageCreationUtil()
    {
        
    }
    
    public Image getInstance(final int width, final int height) throws Exception
    {
        final Image image = GameFeatureImageCacheFactory.getInstance().get(this.getClass().getName(), width, height);
        return image;
    }
    
    public Image createImage(final int width, final int height, final float maxScaleX, final float maxScaleY) 
    throws Exception
    {
        //logUtil.put(": " + scaleNominatorX + " / " + scaleDenominatorX + " = " + scaleX, this, "createImage");
        final Image image = GameFeatureImageCacheFactory.getInstance().get(this.getClass().getName(), (int) (width * maxScaleX) + 1, (int) (height * maxScaleY) + 1);
        return image;
    }
    
}
