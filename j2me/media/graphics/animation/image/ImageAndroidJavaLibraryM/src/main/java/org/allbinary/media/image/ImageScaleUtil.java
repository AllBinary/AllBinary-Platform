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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import javax.microedition.lcdui.Image;

import org.allbinary.graphics.Anchor;
import org.allbinary.image.ImageCache;

public class ImageScaleUtil
{
    private static final ImageScaleUtil instance = new ImageScaleUtil();

    public static ImageScaleUtil getInstance()
    {
        return instance;
    }

    private ImageScaleUtil()
    {
    }

    //private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();
    
    private int anchor = Anchor.TOP_LEFT;

    public Image createImage(final ImageCache imageCache, final Image originalImage,
        final float scaleNominatorX, final float scaleDenominatorX,
        final float scaleNominatorY, final float scaleDenominatorY, final boolean cached)
        throws Exception {

        return this.createImage(imageCache, originalImage, scaleNominatorX, scaleDenominatorX, scaleNominatorY, scaleDenominatorY, cached, true);

    }
    
    //private int anchor = Anchor.TOP_LEFT;
    public Image createImage(final ImageCache imageCache, final Image originalImage,
        final float scaleNominatorX, final float scaleDenominatorX,
        final float scaleNominatorY, final float scaleDenominatorY, final boolean cached, final boolean mutable)
        throws Exception {
        
        float scaleX = scaleNominatorX / scaleDenominatorX;
        float scaleY = scaleNominatorY / scaleDenominatorY;
        
        return this.createImage(imageCache, originalImage, scaleX, scaleY, cached);
    }
    
    public Image createImage(final ImageCache imageCache, final Image originalImage, 
            final float scaleX, final float scaleY, final boolean cached) 
    throws Exception
    {
        Bitmap originalBitmap = originalImage.getBitmap();

        //LogUtil.put(LogFactory.getInstance(": " + scaleNominatorX + " / " + scaleDenominatorX + " = " + scaleX, 
          //      "ImageScaleUtil", "createImage"));

        final int width = (int) (originalBitmap.getWidth() * scaleX);
        final int height = (int) (originalBitmap.getHeight() * scaleY);

        //if(width % 2 != 0 && 3d) throw new Exception because it is really a texture
        
        Image image = null;
        
        if(cached)
        {
            image = imageCache.get(
                    this.getClass().getName(), width, height);
        }
        else
        {
          //TWB - Image Create
            //image = Image.createImage(width, height);
            image = imageCache.get("createImage", width, height);
        }

        if (image.isMutable())
        {
            final Matrix matrix = new Matrix();
            this.scale(image, matrix, scaleX, scaleY);

            image.getGraphics().drawImage(originalImage, 0, 0, anchor);

            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }
    
    private void scale(final Image image, final Matrix matrix, final float scaleX, final float scaleY)
    {
        matrix.setScale(scaleX, scaleY);

        final Canvas canvas = image.getCanvas();
        canvas.concat(matrix);
    }
}
