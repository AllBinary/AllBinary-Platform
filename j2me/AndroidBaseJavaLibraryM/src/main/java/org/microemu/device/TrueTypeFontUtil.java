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
package org.microemu.device;

import javax.microedition.lcdui.Image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import org.allbinary.graphics.color.BasicColor;

public class TrueTypeFontUtil
{
    private static final TrueTypeFontUtil instance = new TrueTypeFontUtil();

    public static TrueTypeFontUtil getInstance()
    {
        return instance;
    }

    //Include special characters 2 times handles the Android Studio issue.
    //public final String pattern = " 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.?!$%`¬\"£^&*()_+-=[]{};'#:@~,/<>\\|®©";
    public final String pattern = " 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.?!$%`¬¬\"££^&*()_+-=[]{};'#:@~,/<>\\|®®©©";

    private final int[] _characterWidth = new int[pattern.length()];
    private final char[] characterArray = new char[1];
    private final int size = pattern.length();
    private final int lastCapIndex = pattern.indexOf('Z');

    public int getAsTextureSize(int textureSize)
    {
        if(textureSize < 64)
        {
            textureSize = 64;
        }
        else
            if(textureSize < 128)
            {
                textureSize = 128;
            }
            else
                if(textureSize < 256)
                {
                    textureSize = 256;
                }
                else
                    if(textureSize < 512)
                    {
                        textureSize = 512;
                    }
                    else
                        if(textureSize < 1024)
                        {
                            textureSize = 1024;
                        }

        return textureSize;
    }

    public Image getFontBitmap(String filename, int fontSize, int cellSize, int cellsPerRow, BasicColor basicColor)
    {
        final int cellsPerRow2 = cellsPerRow * 2;
        final int cellsPerRow3 = cellsPerRow * 3;
        final int cellsPerRow4 = cellsPerRow * 4;
        final int cellsPerRow5 = cellsPerRow * 5;
        final int cellsPerRow6 = cellsPerRow * 6;
        final int cellsPerRow7 = cellsPerRow * 7;

        final Typeface typeface = Typeface.DEFAULT;
        // Typeface.createFromAsset(
        // ResourceUtil.getInstance().getContext().getAssets(),
        // filename);

        //Must make bitmap as texture for GL so it must be as a texture size. 
        final int textureSize = this.getAsTextureSize(cellsPerRow * cellSize);

        final Bitmap bitmap = Bitmap.createBitmap(
                //cellsPerRow * cellSize, 8 * cellSize,
                textureSize, textureSize, 
                Bitmap.Config.ARGB_8888);
        // AndroidBitmapConfigUtil.get());

        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setTextSize(fontSize);
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
        for (int index = 0; index < size; index++)
        {
            characterArray[0] = pattern.charAt(index);
            paint.getTextBounds(characterArray, 0, 1, bounds);
            _characterWidth[index] = bounds.right;
            if (bounds.bottom - bounds.top > biggestHeight)
                biggestHeight = bounds.bottom - bounds.top;
            x = (index % cellsPerRow) * cellSize;
            x += (cellSize >> 1);
            x -= (_characterWidth[index] >> 1);
            y = 0;
            if (index >= cellsPerRow)
                y += cellSize;
            if (index >= cellsPerRow2)
                y += cellSize;
            if (index >= cellsPerRow3)
                y += cellSize;
            if (index >= cellsPerRow4)
                y += cellSize;
            if (index >= cellsPerRow5)
                y += cellSize;
            if (index >= cellsPerRow6)
                y += cellSize;
            if (index >= cellsPerRow7)
                y += cellSize;
            y += cellSize;
            y -= (cellSize >> 2);
            canvas.drawText(characterArray, 0, 1, x, y, paint);
        }
        canvas.save();

        return new Image(bitmap);
    }

    public int[] getFontWidths(String filename, int fontSize)
    {
        Typeface typeface = Typeface.DEFAULT;
        // Typeface.createFromAsset(
        // ResourceUtil.getInstance().getContext().getAssets(),
        // filename);

        Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setTextSize(fontSize);
        paint.setARGB(255, 255, 255, 255);

        Rect bounds = new Rect();
        for (int index = 0; index < size; index++)
        {
            characterArray[0] = pattern.charAt(index);
            paint.getTextBounds(characterArray, 0, 1, bounds);

            if (index < lastCapIndex)
            {
                if (characterArray[0] == '1')
                {
                    _characterWidth[index] = bounds.right + 3;
                } else if (characterArray[0] == 'J' || characterArray[0] == 'V' || 
                        characterArray[0] == '2' || characterArray[0] == '9' || characterArray[0] == 'B' || 
                        characterArray[0] == 'H' || characterArray[0] == 'I' || characterArray[0] == 'N' || 
                        characterArray[0] == 'S' || characterArray[0] == 'U'
                        )
                {
                    _characterWidth[index] = bounds.right + 1;
                } else if (characterArray[0] == '4' || characterArray[0] == 'C'
                        || characterArray[0] == 'M' || characterArray[0] == 'O')
                {
                    _characterWidth[index] = bounds.right - 1;
                } else if (characterArray[0] == 'A' || characterArray[0] == 'T'
                        || characterArray[0] == 'W')
                {
                    _characterWidth[index] = bounds.right - 3;
                } else if (characterArray[0] == 'm')
                {
                    _characterWidth[index] = bounds.right - 4;
                } else
                {
                    _characterWidth[index] = bounds.right;
                }

            } else
            {
                if (characterArray[0] == 'l' || characterArray[0] == 'i' || 
                        characterArray[0] == 'j' || characterArray[0] == '.' || 
                        characterArray[0] == '!' || characterArray[0] == '|')
                {
                    _characterWidth[index] = bounds.right + 6;
                } else if (characterArray[0] == 'f' || characterArray[0] == 't'
                        || characterArray[0] == 'u' || characterArray[0] == 'v' || 
                        characterArray[0] == 'r')
                {
                    _characterWidth[index] = bounds.right + 1;
                } else if (characterArray[0] == 'a' || characterArray[0] == 'b' || 
                        characterArray[0] == 'g')
                {
                    _characterWidth[index] = bounds.right - 1;
                } else if (characterArray[0] == 'y')
                {
                    _characterWidth[index] = bounds.right - 2;
                } else if (characterArray[0] == 'm')
                {
                    _characterWidth[index] = bounds.right - 6;
                } else
                {
                    _characterWidth[index] = bounds.right;
                }
            }
        }

        _characterWidth[0] = (fontSize >> 1) - 2;

        return _characterWidth;
    }
}