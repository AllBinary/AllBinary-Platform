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

import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.score.displayable.HighScoresCanvas;
import org.allbinary.util.BasicArrayList;

public class HighScoresCanvasInputProcessor extends PlayerGameInput
{
    private final HighScoresCanvas highScoresCanvas;
    
    public HighScoresCanvasInputProcessor(HighScoresCanvas highScoresCanvas)
    {
        super(new BasicArrayList(), -1);

        this.highScoresCanvas = highScoresCanvas;
    }

    public void open()
    {
        
    }
    
    public void close()
    {
        
    }
    
    @Override
    public synchronized void onPressGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        super.onPressGameKeyEvent(gameKeyEvent);
        
        this.update();
    }
    
    @Override
    public synchronized void onDownGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        super.onDownGameKeyEvent(gameKeyEvent);
        
        this.update();
    }

    @Override
    public synchronized void onUpGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        super.onUpGameKeyEvent(gameKeyEvent);
        
        this.update();
    }
    
    public void paint(Graphics graphics)
    {
        
    }

    public HighScoresCanvas getHighScoresCanvas()
    {
        return highScoresCanvas;
    }
}
