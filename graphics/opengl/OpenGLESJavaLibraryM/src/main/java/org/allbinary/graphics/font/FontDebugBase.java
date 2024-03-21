/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.allbinary.graphics.font;

import javax.microedition.lcdui.Font;

/**
 *
 * @author User
 */
public class FontDebugBase {
    
    private static final FontDebugBase instance = new FontDebugBase();

    /**
     * @return the instance
     */
    public static FontDebugBase getInstance() {
        return instance;
    }
    
    public final String SET_FONT = "setFont";
        
    public void debugDpi(final int densityDpi, final StringBuilder stringBuffer) {
        
    }
    
    public void debugDimension(final Font currentFont, final float convertedTextSize, final int longestDimensionTotalPixels, final StringBuilder stringBuffer) {
        
    }
}
