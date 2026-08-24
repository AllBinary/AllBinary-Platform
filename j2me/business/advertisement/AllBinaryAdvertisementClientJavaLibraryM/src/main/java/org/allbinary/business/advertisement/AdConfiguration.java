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
package org.allbinary.business.advertisement;

import jsinterop.annotations.JsType;

import org.allbinary.direction.Direction;
import org.allbinary.game.state.GameState;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class AdConfiguration
{
    @JsProperty
    public final String INNERACTIVE;
    @JsProperty
    public final String LEADBOLT;
    @JsProperty
    public final String ADMOB;
    
    @JsConstructor
    public AdConfiguration(final Object[] advertIdArray)
    {
        this.INNERACTIVE = (String) advertIdArray[0];
        this.ADMOB = (String) advertIdArray[1];
        this.LEADBOLT = (String) advertIdArray[2];
    }
    
    @JsMethod
    public Direction[] getValidAdSpots()
    {
        return new Direction[0];
    }
    
    //Special in gamestate based processing
    @JsMethod
    public void process(final GameState gameState)
    {
    }
    
    //Special Demo processing
    @JsMethod
    public void processDemo(final int state)
    {
    }

    @JsMethod
    public void setShowAds()
    {
    }
    
    @JsMethod
    public void setToggleAds(final boolean showAds)
    {
    }

    @JsMethod
    public boolean isShowAds()
    {
        return true;
    }
}
