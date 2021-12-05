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

import android.graphics.Color;
import android.graphics.Matrix;
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

    public void rotate(final Image image, final Image originalImage, final Matrix matrix) {
        image.getBitmap().eraseColor(Color.TRANSPARENT);
        image.getCanvas().concat(matrix);
        image.getGraphics().drawImage(originalImage, 0, 0, 0);
    }
}
