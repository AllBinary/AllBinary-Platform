/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
        final Image scaledImage = ImageCopyUtil.getInstance().createImage(image, width, height);

        return scaledImage;
    }
}
