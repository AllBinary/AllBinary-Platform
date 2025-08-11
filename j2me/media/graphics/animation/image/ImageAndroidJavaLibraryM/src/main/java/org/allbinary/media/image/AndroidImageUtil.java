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
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import javax.microedition.lcdui.Image;

/**
 *
 * @author User
 */
public class AndroidImageUtil {

    private static final AndroidImageUtil instance = new AndroidImageUtil();

    /**
     * @return the instance
     */
    public static AndroidImageUtil getInstance() {
        return instance;
    }

    public void paint(final Bitmap bitmap, final Bitmap originalBitmap, final Paint paint) {
        bitmap.eraseColor(Color.TRANSPARENT);
        final Canvas canvas = new android.graphics.Canvas();
        canvas.setBitmap(bitmap);
        canvas.drawBitmap(originalBitmap, 0.0f, 0.0f, paint);
    }
    
    public void paint(final Image image, final Image originalImage, final Paint paint) {
        image.getBitmap().eraseColor(Color.TRANSPARENT);
        final Canvas canvas = image.getCanvas();
        canvas.drawBitmap(originalImage.getBitmap(), 0.0f, 0.0f, paint);
    }
    
    public void rotate(final Image image, final Image originalImage, final Matrix matrix, final Paint paint) {
        image.getBitmap().eraseColor(Color.TRANSPARENT);
        final Canvas canvas = image.getCanvas();
        canvas.concat(matrix);
        canvas.drawBitmap(originalImage.getBitmap(), 0.0f, 0.0f, paint);
    }

    public void rotate(final Image image, final Image originalImage, final Matrix matrix) {
        image.getBitmap().eraseColor(Color.TRANSPARENT);
        final Canvas canvas = image.getCanvas();
        canvas.concat(matrix);
        image.getGraphics().drawImage(originalImage, 0, 0, 0);
    }
}
