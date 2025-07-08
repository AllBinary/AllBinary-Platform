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
package org.allbinary.game.score;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.ColorChangeEvent;
import org.allbinary.graphics.color.ColorChangeListener;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class HighScoresPaintable 
extends Paintable
implements ColorChangeListener
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final DisplayInfoSingleton displayInfoSingleton = 
            DisplayInfoSingleton.getInstance();
    
    private BasicColor basicColor = BasicColorFactory.getInstance().WHITE;

    private HighScores highScores = NullHighScoresSingletonFactory.getInstance();

    public HighScoresPaintable()
    {
    }
    
    public void onEvent(AllBinaryEventObject eventObject)
    {
        //logUtil.put("Color Change Event: " + basicColor.getName(), this, "onEvent");
        this.basicColor = ((ColorChangeEvent) eventObject).getBasicColor();
    }    

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        final int charHeight = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
        
        //int width = graphics.getClipWidth();
        //int height = graphics.getClipHeight();
        int width = this.displayInfoSingleton.getLastWidth();
        int height = this.displayInfoSingleton.getLastHeight();

        graphics.setColor(getBasicColor().intValue());

        String heading = this.highScores.getHeading();

        int topScoresWidth = (graphics.getFont().stringWidth(heading) >> 1);
        graphics.drawString(heading, (width >> 1) - topScoresWidth, charHeight, anchor);

        graphics.drawString(this.highScores.getColumnOneHeading(), 10,
                charHeight * 3, anchor);

        String columnTwoHeading = this.highScores.getColumnTwoHeading();
        int columnTwoHeadingWidth = graphics.getFont().stringWidth(columnTwoHeading);
        graphics.drawString(columnTwoHeading, width - 10 - columnTwoHeadingWidth,
                charHeight * 3, anchor);

        int index = 4;

        int largestSecondColumnWidth = columnTwoHeadingWidth;

        BasicArrayList list = this.highScores.getList();

        int size = list.size();
        int vectorIndex = 0;
        while (vectorIndex < size
                && charHeight * index < height
                        - (charHeight * 2))
        {
            HighScore highScore = (HighScore) list.objectArray[vectorIndex];
            int nextScoreWidth = graphics.getFont()
                    .stringWidth(highScore.getScoreString());
            if (nextScoreWidth > largestSecondColumnWidth)
            {
                largestSecondColumnWidth = nextScoreWidth;
            }
            vectorIndex++;
        }

        vectorIndex = 0;
        while (vectorIndex < size
                && charHeight * index < height
                        - (charHeight * 2))
        {
            HighScore highScore = (HighScore) list.objectArray[vectorIndex];
            // this.highScoresHashTable.get(enumeration.nextElement());

            graphics.drawString(highScore.getName(), 10, 
                    charHeight * index, anchor);

            graphics.drawString(highScore.getScoreString(), 
                    width - 10 - largestSecondColumnWidth, 
                    charHeight * index, anchor);

            index++;
            vectorIndex++;
        }
    }

    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    public BasicColor getBasicColor()
    {
        return basicColor;
    }

    public void setHighScores(HighScores highScores)
    {
        this.highScores = highScores;
    }
}
