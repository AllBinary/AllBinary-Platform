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

public class CompositeSound extends Sound
{
    //protected final LogUtil logUtil = LogUtil.getInstance();
    
    protected CompositeSound(String resource)
    {
        super(resource);
    }

    public void init() throws Exception
    {
        //player = AllBinaryMediaManager.createPlayer(this.getResource());
        
        this.setPlayer(new PlayerComposite(
                AllBinaryMediaManager.createPlayer(this.getResource()))); 
    }
    /*
    if(this.playerComposite.getMediaTime() == 0)
    {
       if(this.playerComposite.setMediaTime(500) == -1)
       {
           logUtil.put("Unable To Set Media Time", this, commonStrings.INIT);
       }
       logUtil.put("Set MediaTime: " + this.playerComposite.getMediaTime(), this, commonStrings.INIT);
    }
     */

}
