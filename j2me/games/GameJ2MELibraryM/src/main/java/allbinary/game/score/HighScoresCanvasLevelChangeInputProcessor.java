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
package allbinary.game.score;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import allbinary.game.input.event.GameKeyEventHandler;
import allbinary.game.input.event.GameKeyEventUtil;
import allbinary.game.score.displayable.HighScoresCanvas;
import allbinary.graphics.Anchor;
import allbinary.graphics.font.MyFont;

public class HighScoresCanvasLevelChangeInputProcessor extends
        HighScoresCanvasInputProcessor
{
    public HighScoresCanvasLevelChangeInputProcessor(
            HighScoresCanvas highScoresCanvas)
    {
        super(highScoresCanvas);
    }

    public void open()
    {
        //BasicMotionGesturesHandler.getInstance().addListener(this);
        GameKeyEventHandler.getInstance().addListener(this);
    }
    
    public void close()
    {
        //BasicMotionGesturesHandler.getInstance().removeListener(this);
        GameKeyEventHandler.getInstance().removeListener(this);
    }
    
    public synchronized void update()
    {
        BasicArrayList list = this.getGameKeyEventList();

        int size = list.size();
        for (int index = 0; index < size; index++)
        {
            Object object = list.objectArray[index];
            int key = GameKeyEventUtil.getKey(object);

            //PreLogUtil.put(CommonStrings.getInstance().START_LABEL + key, this, CommonStrings.getInstance().UPDATE);

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

    // Accelerate or Right Turn Left Turn or Reverse
    // private final String INSTRUCTIONS =
    // "(Up = Next Track, Down = Previous Track)";
    private final String INSTRUCTIONS = "(Right = Next Track, Left = Previous Track)";

    // private final String INSTRUCTIONS = "(Change Tracks With Game Controls)";

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        int width = graphics.getClipWidth();

        int topScoresWidth = (graphics.getFont().stringWidth(INSTRUCTIONS) >> 1);

        graphics.drawString(INSTRUCTIONS, (width >> 1) - topScoresWidth,
                MyFont.getInstance().DEFAULT_CHAR_HEIGHT * 2, anchor);
    }
}
