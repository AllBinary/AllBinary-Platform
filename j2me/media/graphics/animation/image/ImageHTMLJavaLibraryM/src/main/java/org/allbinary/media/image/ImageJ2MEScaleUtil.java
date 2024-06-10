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

    private static final ImageJ2MEScaleUtil instance = new ImageJ2MEScaleUtil();

    /**
     * @return the instance
     */
    public static ImageJ2MEScaleUtil getInstance() {
        return instance;
    }
    
    private ImageJ2MEScaleUtil() {
        
    }

    public Image[] scale(Image[] images, int width, int height)
            throws Exception
    {
        Image[] scaledImages = new Image[images.length];

        for(int index = images.length; --index >= 0;)
        {
            scaledImages[index] = this.scale(images[index], width, height);
        }

        return scaledImages;
    }

    public Image scale(Image image, int width, int height)
            throws Exception
    {
        /*
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();
        int[] originalData = new int[image.getWidth() * image.getHeight()];

        image.getRGB(originalData, 0, image.getWidth(), 0,0, image.getWidth(), image.getHeight());

        int[] scaledData = new int[width * height];

        int dx;
        int dy;
        
        int heightRatioFactor = (8 * sourceHeight) / height;
        int widthRatioFactor = (8 * sourceWidth) / width;

        int scaledIndex = scaledData.length - 1;
        
        for(int index = height; --index >= 0;)
        {
            dy = (index * heightRatioFactor) >> 3;
            
            for(int index2 = width; --index2 >= 0;)
            {
                dx = (index2 * widthRatioFactor) >> 3;
                
                scaledData[scaledIndex--] = originalData[(sourceWidth * dy) + dx];
            }
        }
        */

        //Image scaledImage = Image.createImage(width, height);
        //scaledImage.getGraphics().drawRegion(image, 0, 0, width, height, 0, 0, 0, 0);

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

        return image;
    }
}
