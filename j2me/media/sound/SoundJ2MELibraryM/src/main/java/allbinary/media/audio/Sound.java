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
package allbinary.media.audio;

import javax.microedition.media.Player;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.PreLogUtil;

public class Sound implements SoundInterface, Runnable
{
    private String resource;
    protected Player player;
    
    protected Sound(String resource)
    {
        this.resource = resource;
    }
    
    public String getResource()
    {
        return this.resource;
    }
    
    public Player getPlayer()
    {
        return this.player;
    }

    public void init() throws Exception
    {
    }

    public int getDuration()
    {
           return -1;
    }

    public void run()
    {
        try
        {
            this.player.start();
        }
        catch (Exception e)
        {
            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN);
        }
    }
}
