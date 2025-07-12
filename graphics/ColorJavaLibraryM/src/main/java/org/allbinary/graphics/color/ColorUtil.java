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
package org.allbinary.graphics.color;

import java.awt.*;

/**
 *
 * @author User
 */
public class ColorUtil {

    private static final ColorUtil instance = new ColorUtil();
    
    /**
     * @return the instance
     */
    public static ColorUtil getInstance() {
        return instance;
    }
    
    public boolean isGreyishOrWhiteLike(final Color color) {
        
        //Grey/Black and Pastel/White do not have large deltas between RGB
        if(Math.abs(color.getRed() - color.getGreen()) > 20 || 
                Math.abs(color.getRed() - color.getBlue()) > 20 ||
                Math.abs(color.getGreen() - color.getBlue()) > 20) {
            return false;
        }

        return true;
    }

    public boolean isDark(final Color color) {
        
        if(color.getRed() < 0x77 && color.getRed() < 0x77 && color.getRed() < 0x77) {
            return true;
        }

        return false;
    }
    
}
