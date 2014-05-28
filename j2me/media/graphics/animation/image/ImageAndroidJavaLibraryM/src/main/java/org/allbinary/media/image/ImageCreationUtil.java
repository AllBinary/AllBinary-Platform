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
