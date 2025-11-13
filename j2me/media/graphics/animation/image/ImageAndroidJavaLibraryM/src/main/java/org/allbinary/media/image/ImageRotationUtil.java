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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import org.microemu.android.device.AndroidImageInterface;

public class ImageRotationUtil
{
    private static final ImageRotationUtil instance = new ImageRotationUtil();
    
    public static ImageRotationUtil getInstance()
    {
        return instance;
    }
    
    private final Matrix matrix = new Matrix();
    
    private ImageRotationUtil()
    {
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    // String resource,
    public Image createRotatedImage(Image originalImage, int rotationInDegrees)
            throws Exception
    {
        final Image image = ImageCreationUtil.getInstance().getInstance(
                originalImage.getWidth() , originalImage.getHeight());

        if (image.isMutable())
        {

            //Bitmap originalBitmap = originalImage.getBitmap();
            final Bitmap bitmap = ((AndroidImageInterface) image).getBitmap();

            final int width = bitmap.getWidth();
            final int height = bitmap.getHeight();

            /*
             * 
             * int[] intArray = new int[width * height];
             * originalBitmap.getPixels(intArray, 0, width, 0, 0, width,
             * height); bitmap.setPixels(intArray, 0, width, 0, 0, width,
             * height);
             */

            /*
             * for (int index = 0; index < width; index++) { for (int index2 =
             * 0; index2 < height; index2++) {
             * 
             * int pixel = originalBitmap.getPixel(index, index2);
             * bitmap.setPixel(index, index2, pixel);
             *  } }
             */

            //image.getGraphics().drawRect(0, 0, width, height);
            
            matrix.setRotate((float) rotationInDegrees, (float) (width >> 1), (float) (height >> 1));
            final Canvas canvas = ((AndroidImageInterface) image).getCanvas();
            //canvas.save();
            
            canvas.concat(matrix);

            image.getGraphics().drawImage(originalImage, 0, 0, anchor);

            //canvas.restore();
            
            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }
}
