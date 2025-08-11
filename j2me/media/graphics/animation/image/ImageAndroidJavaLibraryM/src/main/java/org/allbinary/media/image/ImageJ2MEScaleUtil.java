/*
* AllBinary Open License Version 1
* Copyright (c) 2023 AllBinary
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

/**
 *
 * @author user
 */
public class ImageJ2MEScaleUtil {

    private static final ImageJ2MEScaleUtil instance = new ImageJ2MEScaleUtil();

    /**
     * @return the instance
     */
    public static ImageJ2MEScaleUtil getInstance() {
        return instance;
    }
    
    private ImageJ2MEScaleUtil() {
        
    }

    public Image[] scale(Image[] images, int width, int height) throws Exception
    {
        for(int index = 0; index < images.length; index++)
        {
            Image image = images[index];
            
            images[index] = ImageScaleUtil.getInstance().createImage(
                    GameFeatureImageCacheFactory.getInstance(),
                    image,
                    (float) width / 2, (float) image.getWidth(),
                    (float) height, (float) image.getHeight(), false);
        }        
        return images;
    }

    public Image scale(Image image, int width, int height) throws Exception
    {
        return ImageScaleUtil.getInstance().createImage(
                GameFeatureImageCacheFactory.getInstance(),
                image,
                (float) width, (float) image.getWidth(),
                (float) height, (float) image.getHeight(), false);
    }
}
