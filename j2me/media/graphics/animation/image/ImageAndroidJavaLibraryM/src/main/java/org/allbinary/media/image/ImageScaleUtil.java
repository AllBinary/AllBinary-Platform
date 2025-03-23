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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

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

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    //private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();
    
    private int anchor = Anchor.TOP_LEFT;
    
    private final BasicArrayList imageBasicArrayList = new BasicArrayList();
    private final BasicArrayList scaledImageBasicArrayList = new BasicArrayList();
    private final BasicArrayList scaleXBasicArrayList = new BasicArrayList();
    private final BasicArrayList scaleYBasicArrayList = new BasicArrayList();

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
        final Bitmap originalBitmap = originalImage.getBitmap();

        //LogUtil.put(LogFactory.getInstance(": " + scaleNominatorX + " / " + scaleDenominatorX + " = " + scaleX, this, commonStrings.CREATE_IMAGE));

        final int width = (int) (originalBitmap.getWidth() * scaleX);
        final int height = (int) (originalBitmap.getHeight() * scaleY);

        //if(width % 2 != 0 && 3d) throw new Exception because it is really a texture
        

        final int index = imageBasicArrayList.indexOf(originalImage);
        boolean alreadyAvailable = false;
        if (index >= 0) {
            int scaleX2 = ((Float) this.scaleXBasicArrayList.get(index)).intValue();
            int scaleY2 = ((Float) this.scaleYBasicArrayList.get(index)).intValue();
            if(scaleX2 == scaleX && scaleY2 == scaleY) {
                alreadyAvailable = true;
            }
        }
        
        if (alreadyAvailable) {
            //LogUtil.put(LogFactory.getInstance("Using existing scaled image at: " + index, this, commonStrings.CREATE_IMAGE));
            return (Image) this.scaledImageBasicArrayList.get(index);
        } else {
            final Image scaledImage = this.getScaledImage(imageCache, originalImage, scaleX, scaleY, width, height, cached);
            imageBasicArrayList.add(originalImage);
            scaledImageBasicArrayList.add(scaledImage);
            this.scaleXBasicArrayList.add(scaleX);
            this.scaleYBasicArrayList.add(scaleY);
            return scaledImage;
        }
        
    }
    
    private Image getScaledImage(final ImageCache imageCache, final Image originalImage, 
            final float scaleX, final float scaleY, final int width, final int height, final boolean cached) throws Exception {
        
        Image image = null;
        
        if(cached)
        {
            image = imageCache.get(this.getClass().getName(), width, height);
        }
        else
        {
          //TWB - Image Create
            //image = Image.createImage(width, height);
            image = imageCache.get(commonStrings.CREATE_IMAGE, width, height);
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
