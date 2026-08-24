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
package org.allbinary.media.audio;

import jsinterop.annotations.JsType;

import javax.microedition.media.Player;

import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class Sound implements SoundInterface, Runnable
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private String resource;
    @JsProperty
    protected Player player = NoPlayer.NO_PLAYER;
    
    @JsConstructor
    protected Sound(String resource)
    {
        this.resource = resource;
    }
    
    @Override
    @JsMethod
    public String getResource()
    {
        return this.resource;
    }
    
    @Override
    @JsMethod
    public Player getPlayerP()
    {
        return this.player;
    }

    @JsMethod
    public void setPlayerP(final Player player)
    {
        this.player = player;
    }
    
    @Override
    @JsMethod
    public void init() throws Exception
    {
    }

    @JsMethod
    public int getDuration()
    {
           return -1;
    }

    @Override
    @JsMethod
    public void run()
    {
        try
        {
            this.player.start();
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN);
        }
    }
}
