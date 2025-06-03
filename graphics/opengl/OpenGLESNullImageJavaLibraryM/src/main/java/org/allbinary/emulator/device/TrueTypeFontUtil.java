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
package org.allbinary.emulator.device;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.image.opengles.OpenGLESImage;

public class TrueTypeFontUtil extends TrueTypeFontUtilBase {

    private static final TrueTypeFontUtil instance = new TrueTypeFontUtil();

    public static TrueTypeFontUtil getInstance() {
        return instance;
    }

    public OpenGLESImage fontImage;
    
    private TrueTypeFontUtil() {
        super(1, 1f);
    }

    public int getAsTextureSize(int textureSize)
    {
        throw new RuntimeException();
    }

    public int getCellSize(int cellSize)
    {
        throw new RuntimeException();
    }
    
    public Image getFontBitmap(final String filename, final int fontSize, final int cellSize, final BasicColor basicColor) {
        throw new RuntimeException();
    }

    //This is only called from OpenGLES on Android via OpenGLESStrings
    public void saveFontAtlasAsFile() {

    }

    public Image getFontBitmap(final GL10 gl, final String filename, final int cellSize, final BasicColor basicColor) {
        throw new RuntimeException();    
    }

    public int[] getFontWidths(final String filename, final int fontSize) {
        throw new RuntimeException();
    }
}
