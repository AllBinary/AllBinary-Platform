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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.ColorChangeEvent;
import org.allbinary.graphics.color.ColorChangeListener;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.util.BasicArrayList;

public class HighScoresPaintable 
extends Paintable
implements ColorChangeListener, UpdateMyFontInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
    
    private BasicColor basicColor = BasicColorFactory.getInstance().WHITE;

    private HighScores highScores = NullHighScoresSingletonFactory.getInstance();

    private int anchor = Anchor.TOP_LEFT;
    private int charHeight;

    public HighScoresPaintable()
    {
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.charHeight = font.getHeight();
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    @Override    
    public void onEvent(AllBinaryEventObject eventObject)
    {
        //this.logUtil.putF("Color Change Event: " + basicColor.getName(), this, "onEvent");
        final ColorChangeEvent colorChangeEvent = (ColorChangeEvent) eventObject;
        this.basicColor = colorChangeEvent.getBasicColorP();
    }

    @Override
    public void paint(Graphics graphics)
    {
        this.myFontProcessor.process(graphics);
        
        //int width = graphics.getClipWidth();
        //int height = graphics.getClipHeight();
        final int width = this.displayInfoSingleton.getLastWidth();
        final int height = this.displayInfoSingleton.getLastHeight();

        graphics.setColor(this.getBasicColorP().intValue());

        String heading = this.highScores.getHeading();

        final int topScoresWidth = (graphics.getFont().stringWidth(heading) >> 1);
        final int charHeight = this.charHeight;
        graphics.drawString(heading, (width >> 1) - topScoresWidth, charHeight, this.anchor);

        graphics.drawString(this.highScores.getColumnOneHeading(), 10, charHeight * 3, this.anchor);

        String columnTwoHeading = this.highScores.getColumnTwoHeading();
        final int columnTwoHeadingWidth = graphics.getFont().stringWidth(columnTwoHeading);
        graphics.drawString(columnTwoHeading, width - 10 - columnTwoHeadingWidth,
                charHeight * 3, this.anchor);

        int index = 4;

        int largestSecondColumnWidth = columnTwoHeadingWidth;

        BasicArrayList list = this.highScores.getList();

        int size = list.size();
        int vectorIndex = 0;
        HighScore highScore;
        while (vectorIndex < size && charHeight * index < height - (charHeight * 2))
        {
            highScore = (HighScore) list.objectArray[vectorIndex];
            int nextScoreWidth = graphics.getFont().stringWidth(highScore.getScoreString());
            if (nextScoreWidth > largestSecondColumnWidth)
            {
                largestSecondColumnWidth = nextScoreWidth;
            }
            vectorIndex++;
        }

        vectorIndex = 0;
        while (vectorIndex < size && charHeight * index < height - (charHeight * 2))
        {
            highScore = (HighScore) list.objectArray[vectorIndex];
            // this.highScoresHashTable.get(enumeration.nextElement());

            graphics.drawString(highScore.getName(), 10, 
                    charHeight * index, this.anchor);

            graphics.drawString(highScore.getScoreString(), 
                    width - 10 - largestSecondColumnWidth, 
                    charHeight * index, this.anchor);

            index++;
            vectorIndex++;
        }
    }

    @Override
    public void setBasicColorP(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    public BasicColor getBasicColorP()
    {
        return this.basicColor;
    }

    public void setHighScores(HighScores highScores)
    {
        this.highScores = highScores;
    }
}
