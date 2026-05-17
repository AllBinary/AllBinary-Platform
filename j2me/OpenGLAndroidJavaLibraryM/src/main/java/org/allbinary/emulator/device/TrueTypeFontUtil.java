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

import java.io.File;
import java.io.FileOutputStream;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.image.PreResourceImageUtil;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonStrings;
import org.microemu.android.device.AndroidImageInterface;
import org.microemu.android.device.AndroidImmutableImage;

//TTF
public class TrueTypeFontUtil extends TrueTypeFontUtilBase {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final TrueTypeFontUtil instance = new TrueTypeFontUtil();

    public static TrueTypeFontUtil getInstance() {
        return TrueTypeFontUtil.instance;
    }

    protected final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();
    
    private final int realFontSize = 18;
    
    public OpenGLESImage fontImage = OpenGLESImage.NULL_OPENGL_IMAGE;
    
    private TrueTypeFontUtil() {
        super(1);
    }
    
//    private Image getFontBitmap(final String filename, final int fontSize, final int cellSize, final BasicColor basicColor) {
//        try {
//            if(this.fontImage == null) {
//                final CanvasStrings canvasStrings = CanvasStrings.getInstance();
//                final Image image = Image.createImage(ResourceUtil.getInstance().getResourceAsStream(canvasStrings.FONT_ATLAS));
//                image.setName(canvasStrings.FONT_ATLAS);
//                this.fontImage = (OpenGLESImage) this.preResourceImageUtil.encapsulate(image);
//                return this.fontImage;
//            } else {
//                return this.fontImage;
//            }
//        } catch(Exception e) {
//                final CommonStrings commonStrings = CommonStrings.getInstance();
//                PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.EXCEPTION, e);
//            throw new RuntimeException();
//        }
//    }

    //This is only called from OpenGLES on Android via OpenGLESStrings
    public void saveFontAtlasAsFile() {
        try {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            final File file = ResourceUtil.getInstance().getContext().getFilesDir();
            final String path = file.getAbsolutePath() + CanvasStrings.getInstance().FONT_ATLAS;
            this.logUtil.putF(path, this, commonStrings.CONSTRUCTOR);
            final FileOutputStream fos = new FileOutputStream(path);
            final Bitmap bitmap = ((AndroidImageInterface) this.fontImage.openGLBitmap.getImage()).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            PreLogUtil.putOE(commonStrings.EXCEPTION, this, commonStrings.EXCEPTION, e);
        }
    }

    public Image getFontBitmapGL(final GL10 gl, final String filename, final int cellSize, final BasicColor basicColor) {
        if (this.fontImage == OpenGLESImage.NULL_OPENGL_IMAGE) {
            final int cellsPerRow2 = this.CELLS_PER_ROW * 2;
            final int cellsPerRow3 = this.CELLS_PER_ROW * 3;
            final int cellsPerRow4 = this.CELLS_PER_ROW * 4;
            final int cellsPerRow5 = this.CELLS_PER_ROW * 5;
            final int cellsPerRow6 = this.CELLS_PER_ROW * 6;
            final int cellsPerRow7 = this.CELLS_PER_ROW * 7;

            final Typeface typeface = Typeface.DEFAULT;
                //Typeface.createFromAsset(ResourceUtil.getInstance().getContext().getAssets(), filename);

            //Must make bitmap as texture for GL so it must be as a texture size.
            final int textureSize = this.getAsTextureSize(this.CELLS_PER_ROW * cellSize);

            final Bitmap bitmap = Bitmap.createBitmap(
                //cellsPerRow * cellSize, 8 * cellSize,
                textureSize, textureSize,
                Bitmap.Config.ARGB_8888);
            // AndroidBitmapConfigUtil.get());

            final Canvas canvas = new Canvas(bitmap);
            final Paint paint = new Paint();
            paint.setTypeface(typeface);
            paint.setTextSize((float) this.realFontSize);
            //paint.setARGB((int) (basicColor.getAlphaComponent() * 255),
            //(int) (basicColor.getRedComponent() * 255),
            //(int) (basicColor.getGreenComponent() * 255),
            //(int) (basicColor.getBlueComponent() * 255));
            //paint.setARGB((int) basicColor.alpha,
            //(int) basicColor.red,
            //(int) basicColor.green,
            //(int) basicColor.blue);
            paint.setAlpha((int) basicColor.alpha);
            paint.setColor(basicColor.intValue());

            int biggestHeight = 0;
            final Rect bounds = new Rect();
            int x;
            int y;
            for (int index = 0; index < this.size; index++) {
                this.characterArray[0] = this.pattern.charAt(index);
                paint.getTextBounds(this.characterArray, 0, 1, bounds);
                this._characterWidth[index] = bounds.right;
                if (bounds.bottom - bounds.top > biggestHeight) {
                    biggestHeight = bounds.bottom - bounds.top;
                }
                x = (index % this.CELLS_PER_ROW) * cellSize;
                x += (cellSize >> 1);
                x -= (this._characterWidth[index] >> 1);
                y = 0;
                if (index >= this.CELLS_PER_ROW) {
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
                canvas.drawText(this.characterArray, 0, 1, (float) x - 3, (float) y - 6, paint);
            }
            canvas.save();

            final Image image = AndroidImmutableImage.create(bitmap);
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
        paint.setTextSize((float) fontSize);
        paint.setARGB(255, 255, 255, 255);

        final Rect bounds = new Rect();
        for (int index = 0; index < this.size; index++) {
            this.characterArray[0] = this.pattern.charAt(index);
            paint.getTextBounds(this.characterArray, 0, 1, bounds);

            if (index < this.lastCapIndex) {
                if (this.characterArray[0] == '1') {
                    this._characterWidth[index] = bounds.right + 3;
                } else if (this.characterArray[0] == 'J' || this.characterArray[0] == 'V'
                    || this.characterArray[0] == '2' || this.characterArray[0] == '9'
                    || this.characterArray[0] == 'I' || this.characterArray[0] == 'N'
                    || this.characterArray[0] == 'U') {
                    this._characterWidth[index] = bounds.right + 1;
                } else if (this.characterArray[0] == '4' || this.characterArray[0] == 'C' || this.characterArray[0] == 'E'
                        || this.characterArray[0] == 'O') {
                    this._characterWidth[index] = bounds.right - 2;
                } else if (this.characterArray[0] == 'B'
                        || this.characterArray[0] == 'D'
                        || this.characterArray[0] == 'G'
                        || this.characterArray[0] == 'H'
                        || this.characterArray[0] == 'T'
                        || this.characterArray[0] == 'W') {
                    this._characterWidth[index] = bounds.right - 3;
                } else if (this.characterArray[0] == 'A' || this.characterArray[0] == 'Q' || this.characterArray[0] == 'R') {
                    this._characterWidth[index] = bounds.right - 5;
                } else if (this.characterArray[0] == 'M') {
                    this._characterWidth[index] = bounds.right - 6;
                } else if (this.characterArray[0] == 'm') {
                    this._characterWidth[index] = bounds.right - 8;
                } else {
                    this._characterWidth[index] = bounds.right;
                }

            } else {
                if (this.characterArray[0] == ' ') {
                    this._characterWidth[index] = bounds.right + 10;
                } else if (this.characterArray[0] == 'l' || this.characterArray[0] == 'i'
                    || this.characterArray[0] == 'j' || this.characterArray[0] == '.'
                    || this.characterArray[0] == '!' || this.characterArray[0] == '|') {
                    this._characterWidth[index] = bounds.right + 6;
                } else if (this.characterArray[0] == 'f' || this.characterArray[0] == 't'
                        || this.characterArray[0] == 'u' || this.characterArray[0] == 'v') {
                    this._characterWidth[index] = bounds.right + 1;
                } else if (this.characterArray[0] == 'r') {
                    this._characterWidth[index] = bounds.right + 2;
                } else if (this.characterArray[0] == 'a' || this.characterArray[0] == 'b'
                        || this.characterArray[0] == 'g'
                        || this.characterArray[0] == 'u') {
                    this._characterWidth[index] = bounds.right - 1;
                } else if (this.characterArray[0] == 'o' || this.characterArray[0] == 'e') {
                    this._characterWidth[index] = bounds.right - 2;
                } else if (this.characterArray[0] == 'm') {
                    this._characterWidth[index] = bounds.right - 7;
                } else {
                    this._characterWidth[index] = bounds.right;
                }
            }
        }

        this._characterWidth[0] = (fontSize >> 1) - 2;

        return this._characterWidth;
    }
}
