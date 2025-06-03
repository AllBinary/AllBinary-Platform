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

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.opengles.OpenGLFeatureUtil;
import org.allbinary.logic.string.StringMaker;

/**
 *
 * @author user
 */
public class DrawStringUtil
{
    private static final DrawStringUtil instance = new DrawStringUtil();

    public static DrawStringUtil getInstance()
    {
        return instance;
    }    

    private final MyFont myFont = MyFont.getInstance();
    
    public void paintVerticle(final Graphics graphics, final String string, final int x, final int y, final int anchor)
    {
        final OpenGLFeatureUtil openGLFeatureUtil = OpenGLFeatureUtil.getInstance();

        int charHeight = myFont.DEFAULT_CHAR_HEIGHT;
        
        final Features features = Features.getInstance();
        final boolean isHTML = features.isDefault(HTMLFeatureFactory.getInstance().HTML);
        if(isHTML) {
            charHeight += 1;
        } else if(openGLFeatureUtil.isAnyThreed()) {
            charHeight += 2;
        }

        int size = string.length();
        int offsetX = 0;
        char aChar;
        for (int index = size - 1; index >= 0; index--)
        {
            aChar = string.charAt(index);

            if(openGLFeatureUtil.isAnyThreed()) {
                offsetX = myFont.charWidth(aChar) / 2;
            }
            
            graphics.drawChar(aChar,
                    x + offsetX, y + (charHeight * index), anchor);
        }
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void drawCenterString(
            final Graphics graphics, final String string, final int offset, int length, int x, int y)
    {
        int width = (graphics.getFont().substringWidth(string, offset, length) >> 1);
        //int width = (graphics.getFont().stringWidth(string) >> 1);
        //width = (width * length) / string.length();

        try
        {
        graphics.drawSubstring(
                string, offset, length,
                x - width,
                y,
                anchor);
        }
        catch(Exception e)
        {
            PreLogUtil.put(new StringMaker().append("Exception: stringLength: ").append(string.length()).append(" offset: ").append(offset).append(" currentLength: ").append(length).toString(), this, "drawCenterString");
        }
    }
    
    private final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;

    public void drawCenterStrings(
            final Graphics graphics, final String[] stringArray, final int maxWidth, final int x, final int y)
    {
        final int charHeight = this.myFont.DEFAULT_CHAR_HEIGHT;
        
        int extraLines = 0;

        for (int index = 0; index < stringArray.length; index++)
        {
            String string = stringArray[index];

            if (string != EMPTY_STRING)
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
                            x, y + ((index + extraLines++) * charHeight));

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
