/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.media.image;

import javax.microedition.lcdui.Image;

/**
 *
 * @author user
 */
public class ImageJ2MEScaleUtil {

    public Image[] scale(final Image[] images, final int width, final int height)
            throws Exception
    {
        final Image[] scaledImages = new Image[images.length];

        for(int index = images.length; --index >= 0;)
        {
            scaledImages[index] = this.scale(images[index], width, height);
        }

        return scaledImages;
    }

    public Image scale(final Image image, final int width, final int height)
            throws Exception
    {
        final int sourceWidth = image.getWidth();
        final int sourceHeight = image.getHeight();
        final int[] originalData = new int[image.getWidth() * image.getHeight()];

        image.getRGB(originalData, 0, image.getWidth(), 0,0, image.getWidth(), image.getHeight());

        final int[] scaledData = new int[width * height];
        
        final int heightRatioFactor = (8 * sourceHeight) / height;
        final int widthRatioFactor = (8 * sourceWidth) / width;

        int scaledIndex = scaledData.length - 1;

        int dx;
        int dy;
        
        for(int index = height; --index >= 0;)
        {
            dy = (index * heightRatioFactor) >> 3;
            
            for(int index2 = width; --index2 >= 0;)
            {
                dx = (index2 * widthRatioFactor) >> 3;
                
                scaledData[scaledIndex--] = originalData[(sourceWidth * dy) + dx];
                     //& 0xFF00FF;
            }
        }

        //final Image scaledImage = Image.createRGBImage(originalData, sourceWidth, sourceHeight, true);
        final Image scaledImage = Image.createRGBImage(scaledData, width, height, true);

        /*
        Image scaledImage = GameFeatureImageCacheFactory.getInstance().get(this, width, height);
        Graphics graphics = scaledImage.getGraphics();

        for(int index = sourceWidth; --index >= 0;)
        {
            for(int index2 = sourceHeight; --index2 >= 0;)
            {
                graphics.setClip(index, index2, 1, 1);

                dx = index * sourceWidth / width;
                dy = index2 * sourceHeight / height;

                graphics.drawImage(image, index - dx, index2 - dy, 0);
            }
        }
        */

        return scaledImage;
    }
}
