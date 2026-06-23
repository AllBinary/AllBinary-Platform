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
package org.allbinary.graphics.draw;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.AndroidUtil;
import org.allbinary.J2MEUtil;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.opengles.OpenGLFeatureUtil;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author user
 */
public class DrawVerticalStringUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final DrawVerticalStringUtil instance = new DrawVerticalStringUtil();

    public static DrawVerticalStringUtil getInstance()
    {
        return DrawVerticalStringUtil.instance;
    }    

    private final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;
     
    private int anchor = Anchor.TOP_LEFT;
    private int charHeight;
    private int offsetY;
    private int[] offsetXArray = NullUtil.getInstance().NULL_INT_ARRAY;
    
    public void updateMeasurement(final Graphics graphics, final String string) {
        final OpenGLFeatureUtil openGLFeatureUtil = OpenGLFeatureUtil.getInstance();

        final Font font = graphics.getFont();
        this.charHeight = font.getHeight();
        
        this.offsetY = 0;
        if(J2MEUtil.isHTML()) {
            this.charHeight += 1;
        } else if(openGLFeatureUtil.isAnyThreed()) {
            this.charHeight += 2;
            if(AndroidUtil.isAndroid()) {
            } else {
                this.offsetY = 2 + (this.charHeight * 2 / 3);
            }
        }
        
        final int size = string.length();
        char aChar;
        int[] offsetXArray = new int[size];
        for (int index = size - 1; index >= 0; index--)
        {
            aChar = string.charAt(index);

            if(openGLFeatureUtil.isAnyThreed()) {
                offsetXArray[index] = font.charWidth(aChar) / 2;
            }
        }
        this.offsetXArray = offsetXArray;
        
    }
    
    public void paintVerticle(final Graphics graphics, final String string, final int x, final int y, final int anchor)
    {
        final int size = string.length();
        char aChar;
        for (int index = size - 1; index >= 0; index--)
        {
            aChar = string.charAt(index);
            
            graphics.drawChar(aChar, x + this.offsetXArray[index], y + (this.charHeight * index) + this.offsetY, anchor);
        }
    }
    
    public void drawCenterString(final Graphics graphics, final String string, final int offset, int length, int x, int y)
    {
        final int width = (graphics.getFont().substringWidth(string, offset, length) >> 1);
        //int width = (graphics.getFont().stringWidth(string) >> 1);
        //width = (width * length) / string.length();

        try
        {
        graphics.drawSubstring(
                string, offset, length,
                x - width,
                y,
                this.anchor);
        }
        catch(Exception e)
        {
            PreLogUtil.put(new StringMaker().append("Exception: stringLength: ").appendint(string.length()).append(" offset: ").appendint(offset).append(" currentLength: ").appendint(length).toString(), this, "drawCenterString");
        }
    }

    public void drawCenterStrings(final Graphics graphics, final String[] stringArray, final int maxWidth, final int x, final int y)
    {
        
        int extraLines = 0;

        for (int index = 0; index < stringArray.length; index++)
        {
            String string = stringArray[index];

            if (string != this.EMPTY_STRING)
            {
                int width = graphics.getFont().stringWidth(string);

                int minTotalLines = 1;
                if (width > maxWidth)
                {
                    minTotalLines = (width / maxWidth) + 1;
                }

                int linePortion = string.length() / minTotalLines;
                int offset = 0;

                int currentLength = linePortion;

                //for (int lineIndex = 0; lineIndex < minTotalLines; lineIndex++)
                
                int size = string.length();
                while(offset < size)
                {
                    //|| lineIndex == minTotalLines - 1
                    if(offset + currentLength > size)
                    {
                        currentLength = size - offset;
                    }

                    if(offset + currentLength != size)
                    {
                        while (currentLength > 0 && stringArray[index].charAt(offset + currentLength) != ' ')
                        {
                            currentLength--;
                        }

                        //If no spaces after tab size then stick with limit
                        if (currentLength <= 4)
                        {
                            currentLength = linePortion;
                        }
                    }

                    //PreLogUtil.put("stringLength: ").append(string.length()).append(" offset: ").append(offset).append(" currentLength: ").append(currentLength, graphics, "drawCenterStrings");

                    this.drawCenterString(graphics,
                            string, offset, currentLength, 
                            x, y + ((index + extraLines++) * this.charHeight));

                    offset = offset + currentLength;

                    currentLength = linePortion;

                    /*
                    while(offset < size && stringArray[index].charAt(offset) == ' ')
                    {
                        offset++;
                    }
                     */
                }
                extraLines--;
            }
        }
    }
}
