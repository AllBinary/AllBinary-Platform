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

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.input.event.GameKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEventUtil;
import org.allbinary.game.score.displayable.HighScoresCanvas;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.util.BasicArrayList;

public class HighScoresCanvasLevelChangeInputProcessor 
    extends HighScoresCanvasInputProcessor implements UpdateMyFontInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final DisplayInfoSingleton displayInfoSingleton = 
            DisplayInfoSingleton.getInstance();
    
    // Accelerate or Right Turn Left Turn or Reverse
    // private final String INSTRUCTIONS =
    // "(Up = Next Track, Down = Previous Track)";
    private final String INSTRUCTIONS = "(Right = Next Track, Left = Previous Track)";

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
    
    // private final String INSTRUCTIONS = "(Change Tracks With Game Controls)";
    
    private int anchor = Anchor.TOP_LEFT;

    private int fontHeight = 0;

    public HighScoresCanvasLevelChangeInputProcessor(final HighScoresCanvas highScoresCanvas)
    {
        super(highScoresCanvas);
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.fontHeight = font.getHeight();
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
        
    
    @Override
    public void open()
    {
        //BasicMotionGesturesHandler.getInstance().addListener(this);
        GameKeyEventHandler.getInstance().addListener(this);
    }
    
    @Override
    public void close()
    {
        //BasicMotionGesturesHandler.getInstance().removeListener(this);
        GameKeyEventHandler.getInstance().removeListener(this);
    }

    @Override
    public synchronized void update()
    {
        final BasicArrayList list = this.getGameKeyEventList();

        final int size = list.size();
        for (int index = 0; index < size; index++)
        {
            final Object object = list.objectArray[index];
            final int key = GameKeyEventUtil.getKey(object);

            //PreLogUtil.put(commonStrings.START_LABEL).append(key, this, this.commonStrings.UPDATE);

            // if(gameKey == GameKey.UP || gameKey == GameKey.RIGHT)
            if (key == Canvas.RIGHT)
            {
                this.getHighScoresCanvas().getGameInfo().nextGameLevel();
            }
            else
            // if(gameKey == GameKey.DOWN || gameKey == GameKey.LEFT)
            if (key == Canvas.LEFT)
            {
                this.getHighScoresCanvas().getGameInfo().previousGameLevel();
            }
            this.getHighScoresCanvas().executeUpdate();
        }
        list.clear();
    }
    
    @Override
    public void paint(Graphics graphics)
    {
        this.myFontProcessor.process(graphics);
        
        //int width = graphics.getClipWidth();
        final int width = this.displayInfoSingleton.getLastWidth();   

        final int topScoresWidth = (graphics.getFont().stringWidth(this.INSTRUCTIONS) >> 1);

        graphics.drawString(this.INSTRUCTIONS, (width >> 1) - topScoresWidth,
                this.fontHeight * 2, this.anchor);
    }
}
