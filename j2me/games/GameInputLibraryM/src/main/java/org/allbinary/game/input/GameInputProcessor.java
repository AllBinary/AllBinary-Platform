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
package org.allbinary.game.input;

import jsinterop.annotations.JsType;

import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.layer.AllBinaryLayerManager;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class GameInputProcessor
{
    private static final GameInputProcessor instance = new GameInputProcessor();

    @JsMethod
    public static GameInputProcessor getInstance()
    {
        return GameInputProcessor.instance;
    }
    
    @JsConstructor
    protected GameInputProcessor()
    {
        
    }

    @JsMethod
    public void processEvent(final AllBinaryLayerManager allbinaryLayerManager, final GameKeyEvent gameKeyEvent)
    throws Exception
    {
        
    }

    @JsMethod
    public void processReleasedEvent(final AllBinaryLayerManager allbinaryLayerManager, final GameKeyEvent gameKeyEvent)
    throws Exception
    {
        
    }
    
    @JsMethod
    public void processAnalog(final AllBinaryLayerManager allbinaryLayerManager, final GameKeyEvent gameKeyEvent, final int analogValue)
    //AnalogLocationInput analogLocationInput
    throws Exception
    {
        
    }
    
    @JsMethod
    public void process(final AllBinaryLayerManager allbinaryLayerManager, final Integer keyAsInteger) 
    throws Exception
    {
        
    }

    @JsMethod
    public void processReleased(final AllBinaryLayerManager allbinaryLayerManager, final Integer keyAsInteger) 
    throws Exception
    {
        
    }
    
}
