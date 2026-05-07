package org.allbinary.graphics.font;

import javax.microedition.lcdui.Font;

import org.allbinary.logic.string.StringMaker;

/**
 *
 * @author User
 */
//OpenGLESBaseJ2MEJavaLibrary
public class FontDebugBase {
    
    private static final FontDebugBase instanceBase = new FontDebugBase();

    /**
     * @return the instance
     */
    public static FontDebugBase getInstance() {
        return FontDebugBase.instanceBase;
    }
    
    public final String SET_FONT = "setFont";
        
    public void debugMetrics(final Font currentFont, final float convertedTextSize, final Object displayMetrics, final StringMaker stringBuffer) {
        
    }
    
    public void debugDpi(final int densityDpi, final StringMaker stringBuffer) {
        
    }
    
    public void debugDimension(final Font currentFont, final float convertedTextSize, final int longestDimensionTotalPixels, final StringMaker stringBuffer) {
        
    }
}
