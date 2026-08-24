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
package org.allbinary.game.displayable.canvas;

import jsinterop.annotations.JsType;

import org.allbinary.canvas.Processor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class GameProcessor extends Processor
{
    private AllBinaryGameCanvas gameCanvas;
    
    @JsConstructor
    public GameProcessor(AllBinaryGameCanvas gameCanvas)
    {
        this.gameCanvas = gameCanvas;
    }
  
    @Override
    @JsMethod
    public void process() throws Exception
    {
        this.gameCanvas.processPlayingGame();
    }
}
