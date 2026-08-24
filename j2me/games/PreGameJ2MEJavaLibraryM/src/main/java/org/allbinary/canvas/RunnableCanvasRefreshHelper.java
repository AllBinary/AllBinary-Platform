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
package org.allbinary.canvas;

import jsinterop.annotations.JsType;

import org.allbinary.graphics.displayable.MyCanvas;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class RunnableCanvasRefreshHelper extends Processor
{
    private final MyCanvas runnableCanvas;

    @JsConstructor
    public RunnableCanvasRefreshHelper(MyCanvas runnableCanvas)
    {
        this.runnableCanvas = runnableCanvas;
    }
    
    @Override
    @JsMethod
    public void process()
    {
        //System.out.println("TWB:RunnableCanvasRefreshHelper:process:repaint");
        this.runnableCanvas.repaint();
    }
}
