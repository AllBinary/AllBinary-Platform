/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.media.image;

import javax.microedition.lcdui.Image;

import org.allbinary.image.GameFeatureImageCacheFactory;

/**
 *
 * @author user
 */
public class ImageJ2MEScaleUtil {

    public Image[] scale(Image[] images, int width, int height) throws Exception
    {
        for(int index = 0; index < images.length; index++)
        {
            Image image = images[index];
            
            images[index] = ImageScaleUtil.getInstance().createImage(
                    GameFeatureImageCacheFactory.getInstance(),
                    image,
                    width / 2, image.getWidth(),
                    height, image.getHeight(), false);
        }        
        return images;
    }

    public Image scale(Image image, int width, int height) throws Exception
    {
        return ImageScaleUtil.getInstance().createImage(
                GameFeatureImageCacheFactory.getInstance(),
                image,
                width, image.getWidth(),
                height, image.getHeight(), false);
    }
}
