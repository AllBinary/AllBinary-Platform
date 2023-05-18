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

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.color.BasicColor;

/**
 *
 * @author User
 */
public class ImageModifierUtil {
    
    public static ImageModifierUtil getInstance() {
        return new ImageModifierUtil();
    }

    //private final String SET_ALPHA = "setAlpha: ";
    //private final String SET_COLOR = "setBasicColor: ";
    
    public final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    
    public void setBasicColor(final BasicColor basicColor) {
        
        //LogUtil.put(LogFactory.getInstance(new StringBuilder().append(SET_COLOR).append(basicColor.toString()).toString(), this, CommonStrings.getInstance().PROCESS));

        paint.setColorFilter(new PorterDuffColorFilter(basicColor.intValue(), PorterDuff.Mode.SRC_IN));
        //paint.setColorFilter(new LightingColorFilter(basicColor.intValue(), 0));

//        final float[] colorFloatMatrix = {
//            0, (float) basicColor.intValue()/255, 0, 0, 0,
//            0, 0, 0f, 0, 0,
//            0, 0, 0, 0f, 0,
//            0, 0, 0, 1f, 0};
//
//        final ColorMatrix colorMatrix = new ColorMatrix();
//        colorMatrix.setSaturation(0f);
//        colorMatrix.set(colorFloatMatrix);
//        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        //BlendModeColorFilter
        
    }
    
    public void setAlpha(final Image originalImage, final Image alphaImage, final int alpha) {
        if(alpha != paint.getAlpha()) {
            paint.setAlpha(alpha);
            //LogUtil.put(LogFactory.getInstance(new StringBuilder().append(SET_ALPHA).append(alpha).append('/').append(alpha).toString(), this, CommonStrings.getInstance().PROCESS));
        }
    }
}
