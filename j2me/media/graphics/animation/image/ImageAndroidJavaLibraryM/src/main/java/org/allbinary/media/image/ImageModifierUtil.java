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
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author User
 */
public class ImageModifierUtil {
    
    private final String SET_ALPHA = "setAlpha: ";
    
    public final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    
    public void setAlpha(final int alpha) {
        if(alpha != paint.getAlpha()) {
            paint.setAlpha(alpha);
            //LogUtil.put(LogFactory.getInstance(new StringBuilder().append(SET_ALPHA).append(alpha).append('/').append(alpha).toString(), this, CommonStrings.getInstance().PROCESS));
        }
    }
}
