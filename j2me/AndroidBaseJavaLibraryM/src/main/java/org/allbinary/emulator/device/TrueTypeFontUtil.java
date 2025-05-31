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

public class TrueTypeFontUtil extends TrueTypeFontUtilBase {

    private static final TrueTypeFontUtil instance = new TrueTypeFontUtil();

    public static TrueTypeFontUtil getInstance() {
        return instance;
    }

    protected final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();
    
    private final int realFontSize = 18;
    
    public OpenGLESImage fontImage;
    
    private TrueTypeFontUtil() {
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
