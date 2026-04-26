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
import javax.microedition.lcdui.NullCanvas;

import org.allbinary.graphics.Anchor;
import org.allbinary.image.ImageCache;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.microemu.android.device.AndroidImageInterface;

public class ImageScaleUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
    
    private final BasicArrayList imageBasicArrayList = new BasicArrayListD();
    private final BasicArrayList scaledImageBasicArrayList = new BasicArrayListD();
    private final BasicArrayList scaleXBasicArrayList = new BasicArrayListD();
    private final BasicArrayList scaleYBasicArrayList = new BasicArrayListD();

    public Image createImage2(final ImageCache imageCache, final Image originalImage,
                              final float scaleNominatorX, final float scaleDenominatorX,
                              final float scaleNominatorY, final float scaleDenominatorY, final boolean cached)
        throws Exception {

        return this.createImage3(imageCache, originalImage, scaleNominatorX, scaleDenominatorX, scaleNominatorY, scaleDenominatorY, cached, true);

    }
    
    //private int anchor = Anchor.TOP_LEFT;
    public Image createImage3(final ImageCache imageCache, final Image originalImage,
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
        final AndroidImageInterface originalAndroidImage = (AndroidImageInterface) originalImage;
        final Bitmap originalBitmap = originalAndroidImage.getBitmap();

        //this.logUtil.putF(CommonLabels.getInstance().COLON_SEP + scaleNominatorX + " / " + scaleDenominatorX + " = " + scaleX, this, commonStrings.CREATE_IMAGE);

        final int width = (int) (originalBitmap.getWidth() * scaleX);
        final int height = (int) (originalBitmap.getHeight() * scaleY);

        //if(width % 2 != 0 && 3d) throw new Exception because it is really a texture
        

        final int index = this.imageBasicArrayList.indexOf(originalImage);
        boolean alreadyAvailable = false;
        if (index >= 0) {
            final Float scaleX2Float = (Float) this.scaleXBasicArrayList.get(index);
            final float scaleX2 = scaleX2Float.floatValue();
            final Float scaleY2Float = (Float) this.scaleYBasicArrayList.get(index);
            final float scaleY2 = scaleY2Float.floatValue();
            if(scaleX2 == scaleX && scaleY2 == scaleY) {
                alreadyAvailable = true;
            }
        }
        
        if (alreadyAvailable) {
            //this.logUtil.putF("Using existing scaled image at: " + index, this, commonStrings.CREATE_IMAGE);
            return (Image) this.scaledImageBasicArrayList.get(index);
        } else {
            final Image scaledImage = this.getScaledImage(imageCache, originalImage, scaleX, scaleY, width, height, cached);
            this.imageBasicArrayList.add(originalImage);
            this.scaledImageBasicArrayList.add(scaledImage);
            this.scaleXBasicArrayList.add(scaleX);
            this.scaleYBasicArrayList.add(scaleY);
            return scaledImage;
        }
        
    }
    
    private Image getScaledImage(final ImageCache imageCache, final Image originalImage, 
            final float scaleX, final float scaleY, final int width, final int height, final boolean cached) throws Exception {
        
        Image image = NullCanvas.NULL_IMAGE;
        
        if(cached)
        {
            image = imageCache.get(this.getClass().getName(), width, height);
        }
        else
        {
          //TWB - Image Create
            //image = Image.createImage(width, height);
            image = imageCache.get(this.commonStrings.CREATE_IMAGE, width, height);
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

        final Canvas canvas = ((AndroidImageInterface) image).getCanvas();
        canvas.concat(matrix);
    }
}
