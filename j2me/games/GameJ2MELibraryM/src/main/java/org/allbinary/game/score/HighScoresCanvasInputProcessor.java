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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.score.displayable.HighScoresCanvas;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class HighScoresCanvasInputProcessor extends PlayerGameInput
{
    private final HighScoresCanvas highScoresCanvas;
    
    @JsConstructor
    public HighScoresCanvasInputProcessor(HighScoresCanvas highScoresCanvas)
    {
        super(new BasicArrayListD(), new BasicArrayListD(), -1);

        this.highScoresCanvas = highScoresCanvas;
    }

    @JsMethod
    public void open()
    {
        
    }
    
    @JsMethod
    public void close()
    {
        
    }
    
    @Override
    @JsMethod
    public synchronized void onPressGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        super.onPressGameKeyEvent(gameKeyEvent);
        
        this.update();
    }
    
    @Override
    @JsMethod
    public synchronized void onDownGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        super.onDownGameKeyEvent(gameKeyEvent);
        
        this.update();
    }

    @Override
    @JsMethod
    public synchronized void onUpGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        super.onUpGameKeyEvent(gameKeyEvent);
        
        this.update();
    }
    
    @JsMethod
    public void paint(Graphics graphics)
    {
        
    }

    @JsMethod
    public HighScoresCanvas getHighScoresCanvas()
    {
        return this.highScoresCanvas;
    }
}
