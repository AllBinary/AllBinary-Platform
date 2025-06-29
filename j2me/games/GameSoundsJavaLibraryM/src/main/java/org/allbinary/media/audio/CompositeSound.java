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

import org.allbinary.media.audio.AllBinaryMediaManager;
import org.allbinary.media.audio.PlayerComposite;
import org.allbinary.media.audio.Sound;

public class CompositeSound extends Sound
{    
    protected CompositeSound(String resource)
    {
        super(resource);
    }

    public void init() throws Exception
    {
        //player = AllBinaryMediaManager.createPlayer(this.getResource());
        
        player = new PlayerComposite(
                AllBinaryMediaManager.createPlayer(this.getResource()));
    }
    /*
    if(this.playerComposite.getMediaTime() == 0)
    {
       if(this.playerComposite.setMediaTime(500) == -1)
       {
           LogUtil.put(LogFactory.getInstance("Unable To Set Media Time", this, commonStrings.INIT));
       }
       LogUtil.put(LogFactory.getInstance("Set MediaTime: " + this.playerComposite.getMediaTime(), this, commonStrings.INIT));
    }
     */

}
