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
package allbinary.image;

import javax.microedition.lcdui.Image;

import allbinary.graphics.Anchor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

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

    private int anchor = Anchor.TOP_LEFT;

    public Image createImage(ImageCache imageCache, Image originalImage,
            float scaleNominatorX, float scaleDenominatorX, 
            float scaleNominatorY, float scaleDenominatorY, boolean cached) 
    throws Exception
    {
        float scaleX = scaleNominatorX / scaleDenominatorX;
        float scaleY = scaleNominatorY / scaleDenominatorY;
        
        return this.createImage(imageCache, originalImage, scaleX, scaleY, cached);
    }
    
    public Image createImage(ImageCache imageCache, Image originalImage, 
            float scaleX, float scaleY, boolean cached) 
    throws Exception
    {
        Bitmap originalBitmap = originalImage.getBitmap();

        //LogUtil.put(LogFactory.getInstance(": " + scaleNominatorX + " / " + scaleDenominatorX + " = " + scaleX, 
          //      "ImageScaleUtil", "createImage"));

        int width = (int) (originalBitmap.getWidth() * scaleX);
        int height = (int) (originalBitmap.getHeight() * scaleY);

        //if(width % 2 != 0 && 3d) throw new Exception because it is really a texture
        
        Image image = null;
        
        if(cached)
        {
            image = imageCache.get(
                    getInstance().getClass().getName(), width, height);
        }
        else
        {
          //TWB - Image Create
            image = Image.createImage(width, height);
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
    
    public void scale(Image image, Matrix matrix, float scaleX, float scaleY)
    {
        matrix.setScale(scaleX, scaleY);

        Canvas canvas = image.getCanvas();
        canvas.concat(matrix);
    }
}
