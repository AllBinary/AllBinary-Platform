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

import javax.microedition.lcdui.Image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import java.io.File;
import java.io.FileOutputStream;

import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.image.PreResourceImageUtil;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class TrueTypeFontUtil {

    private static final TrueTypeFontUtil instance = new TrueTypeFontUtil();

    public static TrueTypeFontUtil getInstance() {
        return instance;
    }

    private final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();

    //Include special characters 2 times handles the Android Studio issue.
    //public final String pattern = " 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.?!$%`¬\"£^&*()_+-=[]{};'#:@~,/<>\\|®©";
    //public final String pattern = " 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.?!$%`¬¬\"££^&*()_+-=[]{};'#:@~,/<>\\|®®©©";
    public final String pattern = " 0123456789AB   CDEFGHIJKLMNO   PQRSTUVWXYZab   cdefghijklmno   pqrstuvwxyz.?   !$%`¬¬\"££^&*(   )_+-=[]{};'#:   @~,/<>\\|®®©©";

    public final short[] charArray
        = //new short[255];
        {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x6f, 0x50, 0x56, 0x6b, 0x51, 0x52, 0x5a, 0x6a, 0x5c, 0x60, 0x5b, 0x62, 0x72, 0x63, 0x4b, 0x73, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xa, 0x6c, 0x69, 0x74, 0x64, 0x75, 0x4c, 0x70, 0xb, 0xc, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x1b, 0x1c, 0x20, 0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x2a, 0x65, 0x76, 0x66, 0x59, 0x61, 0x53, 0x2b, 0x2c, 0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3a, 0x3b, 0x3c, 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x67, 0x77, 0x68, 0x71, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x58, 0x0, 0x0, 0x0, 0x0, 0x0, 0x7b, 0x0, 0x0, 0x55, 0x0, 0x79, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0};

    private final int[] _characterWidth = new int[pattern.length()];
    private final char[] characterArray = new char[1];
    private final int size = pattern.length();
    private final int lastCapIndex = pattern.indexOf('Z');

    public javax.microedition.lcdui.Font currentFont
        = javax.microedition.lcdui.Font.getDefaultFont();

    private final int realFontSize = 18;

    public final int CELLS_PER_ROW = 16; //13;
    public final int fontSize = 20 + 6; //currentFont.getSize() + 6;
    public final int baseCharWidth = fontSize + 6;
    public final int cellSize = fontSize + 6;// * 3 >> 1;
    public final int textureSize = this.getAsTextureSize(CELLS_PER_ROW * cellSize);
    public final int actualCellsPerRow = textureSize / cellSize;
    //public final int extraCellsPerRow = actualCellsPerRow - CELLS_PER_ROW;

    public OpenGLESImage fontImage;

    private TrueTypeFontUtil() {
//        LogUtil.put(LogFactory.getInstance(new StringMaker()
//            .append(" fontSize: ").append(fontSize)
//            .append(" cellSize: ").append(cellSize)
//            .append(" textureSize: ").append(textureSize)
//            .append(" actualCellsPerRow: ").append(actualCellsPerRow)
//            //.append(" extraCellsPerRow: ").append(extraCellsPerRow)
//            .toString(), this, CommonStrings.getInstance().CONSTRUCTOR));

//        final StringMaker stringMaker = new StringMaker();
//        for(short index = 0; index < this.pattern.length(); index++) {
//            //stringMaker.append('0').append('x').append(Integer.toHexString(this.pattern.charAt(index))).append(',');
//            charArray[this.pattern.charAt(index)] = index;
//        }
//        
//        for(short index = 0; index < this.charArray.length; index++) {
//            stringMaker.append('0').append('x').append(Integer.toHexString(charArray[index])).append(',');
//        }
//        
//        LogUtil.put(LogFactory.getInstance(stringMaker.toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
    }

    public int getAsTextureSize(int textureSize)
    {
        if(textureSize <= 64)
        {
            textureSize = 64;
        }
        else
            if(textureSize <= 128)
            {
                textureSize = 128;
            }
            else
                if(textureSize <= 256)
                {
                    textureSize = 256;
                }
                else
                    if(textureSize <= 512)
                    {
                        textureSize = 512;
                    }
                    else
                        if(textureSize <= 1024)
                        {
                            textureSize = 1024;
                        }

        return textureSize;
    }

    public int getCellSize(int cellSize)
    {
        if(cellSize <= 16)
        {
            cellSize = 16;
        }
        else
            if(cellSize <= 32)
            {
                cellSize = 32;
            }
            else
                if(cellSize <= 64)
                {
                    cellSize = 64;
                }
                else
                    if(cellSize <= 128)
                    {
                        cellSize = 128;
                    }
                else
                    if(cellSize <= 256)
                    {
                        cellSize = 256;
                    }

        return cellSize;
    }
    
    public Image getFontBitmap(final String filename, final int fontSize, final int cellSize, final BasicColor basicColor) {
        try {
            if(this.fontImage == null) {
                final CanvasStrings canvasStrings = CanvasStrings.getInstance();
                final Image image = Image.createImage(ResourceUtil.getInstance().getResourceAsStream(canvasStrings.FONT_ATLAS));
                image.setName(canvasStrings.FONT_ATLAS);
                this.fontImage = (OpenGLESImage) this.preResourceImageUtil.encapsulate(image);
                return this.fontImage;
            } else {
                return this.fontImage;
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //This is only called from OpenGLES on Android via OpenGLESStrings
    public void saveFontAtlasAsFile() {
        try {
            final File file = ResourceUtil.getInstance().getContext().getFilesDir();
            final String path = file.getAbsolutePath() + CanvasStrings.getInstance().FONT_ATLAS;
            LogUtil.put(LogFactory.getInstance(path, this, CommonStrings.getInstance().CONSTRUCTOR));
            final FileOutputStream fos = new FileOutputStream(path);
            final Bitmap bitmap = this.fontImage.openGLBitmap.getImage().getBitmap();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getFontBitmap(final String filename, final int cellSize, final BasicColor basicColor) {
        if (this.fontImage == null) {
            final int cellsPerRow2 = CELLS_PER_ROW * 2;
            final int cellsPerRow3 = CELLS_PER_ROW * 3;
            final int cellsPerRow4 = CELLS_PER_ROW * 4;
            final int cellsPerRow5 = CELLS_PER_ROW * 5;
            final int cellsPerRow6 = CELLS_PER_ROW * 6;
            final int cellsPerRow7 = CELLS_PER_ROW * 7;

            final Typeface typeface = Typeface.DEFAULT;
                //Typeface.createFromAsset(ResourceUtil.getInstance().getContext().getAssets(), filename);

            //Must make bitmap as texture for GL so it must be as a texture size.
            final int textureSize = this.getAsTextureSize(CELLS_PER_ROW * cellSize);

            final Bitmap bitmap = Bitmap.createBitmap(
                //cellsPerRow * cellSize, 8 * cellSize,
                textureSize, textureSize,
                Bitmap.Config.ARGB_8888);
            // AndroidBitmapConfigUtil.get());

            final Canvas canvas = new Canvas(bitmap);
            final Paint paint = new Paint();
            paint.setTypeface(typeface);
            paint.setTextSize(realFontSize);
            //paint.setARGB((int) (basicColor.getAlphaComponent() * 255),
            //(int) (basicColor.getRedComponent() * 255),
            //(int) (basicColor.getGreenComponent() * 255),
            //(int) (basicColor.getBlueComponent() * 255));
            //paint.setARGB((int) basicColor.alpha,
            //(int) basicColor.red,
            //(int) basicColor.green,
            //(int) basicColor.blue);
            paint.setAlpha(basicColor.alpha);
            paint.setColor(basicColor.intValue());

            int biggestHeight = 0;
            final Rect bounds = new Rect();
            int x;
            int y;
            for (int index = 0; index < size; index++) {
                characterArray[0] = pattern.charAt(index);
                paint.getTextBounds(characterArray, 0, 1, bounds);
                _characterWidth[index] = bounds.right;
                if (bounds.bottom - bounds.top > biggestHeight) {
                    biggestHeight = bounds.bottom - bounds.top;
                }
                x = (index % CELLS_PER_ROW) * cellSize;
                x += (cellSize >> 1);
                x -= (_characterWidth[index] >> 1);
                y = 0;
                if (index >= CELLS_PER_ROW) {
                    y += cellSize;
                }
                if (index >= cellsPerRow2) {
                    y += cellSize;
                }
                if (index >= cellsPerRow3) {
                    y += cellSize;
                }
                if (index >= cellsPerRow4) {
                    y += cellSize;
                }
                if (index >= cellsPerRow5) {
                    y += cellSize;
                }
                if (index >= cellsPerRow6) {
                    y += cellSize;
                }
                if (index >= cellsPerRow7) {
                    y += cellSize;
                }
                y += cellSize;
                y -= (cellSize >> 2);
                canvas.drawText(characterArray, 0, 1, x - 3, y - 6, paint);
            }
            canvas.save();

            final Image image = new Image(bitmap);
            //final String FONT_ATLAS = "font_atlas";
            //image.setName(FONT_ATLAS);
            this.fontImage = (OpenGLESImage) this.preResourceImageUtil.encapsulate(image);
            return this.fontImage;

        } else {
            return this.fontImage;
        }
    }

    public int[] getFontWidths(final String filename, final int fontSize) {
        final Typeface typeface = Typeface.DEFAULT;
        //Typeface.createFromAsset(ResourceUtil.getInstance().getContext().getAssets(), filename);

        final Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setTextSize(fontSize);
        paint.setARGB(255, 255, 255, 255);

        final Rect bounds = new Rect();
        for (int index = 0; index < size; index++) {
            characterArray[0] = pattern.charAt(index);
            paint.getTextBounds(characterArray, 0, 1, bounds);

            if (index < lastCapIndex) {
                if (characterArray[0] == '1') {
                    _characterWidth[index] = bounds.right + 3;
                } else if (characterArray[0] == 'J' || characterArray[0] == 'V'
                    || characterArray[0] == '2' || characterArray[0] == '9'
                    || characterArray[0] == 'H' || characterArray[0] == 'I' || characterArray[0] == 'N'
                    || characterArray[0] == 'U') {
                    _characterWidth[index] = bounds.right + 1;
                } else if (characterArray[0] == '4' || characterArray[0] == 'C' || characterArray[0] == 'E'
                        || characterArray[0] == 'O') {
                    _characterWidth[index] = bounds.right - 2;
                } else if (characterArray[0] == 'G'
                        || characterArray[0] == 'T'
                        || characterArray[0] == 'W') {
                    _characterWidth[index] = bounds.right - 3;
                } else if (characterArray[0] == 'A' || characterArray[0] == 'Q' || characterArray[0] == 'R') {
                    _characterWidth[index] = bounds.right - 5;
                } else if (characterArray[0] == 'M') {
                    _characterWidth[index] = bounds.right - 6;
                } else if (characterArray[0] == 'm') {
                    _characterWidth[index] = bounds.right - 8;
                } else {
                    _characterWidth[index] = bounds.right;
                }

            } else {
                if (characterArray[0] == ' ') {
                    _characterWidth[index] = bounds.right + 10;
                } else if (characterArray[0] == 'l' || characterArray[0] == 'i'
                    || characterArray[0] == 'j' || characterArray[0] == '.'
                    || characterArray[0] == '!' || characterArray[0] == '|') {
                    _characterWidth[index] = bounds.right + 6;
                } else if (characterArray[0] == 'f' || characterArray[0] == 't'
                        || characterArray[0] == 'u' || characterArray[0] == 'v') {
                    _characterWidth[index] = bounds.right + 1;
                } else if (characterArray[0] == 'r') {
                    _characterWidth[index] = bounds.right + 2;
                } else if (characterArray[0] == 'a' || characterArray[0] == 'b'
                        || characterArray[0] == 'g'
                        || characterArray[0] == 'u') {
                    _characterWidth[index] = bounds.right - 1;
                } else if (characterArray[0] == 'o' || characterArray[0] == 'e') {
                    _characterWidth[index] = bounds.right - 2;
                } else if (characterArray[0] == 'm') {
                    _characterWidth[index] = bounds.right - 7;
                } else {
                    _characterWidth[index] = bounds.right;
                }
            }
        }

        _characterWidth[0] = (fontSize >> 1) - 2;

        return _characterWidth;
    }
}
