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
package allbinary.game;

import allbinary.time.TimeDelayHelper;

public class Intermission implements IntermissionInterface
{
    private boolean enabled;

    private final TimeDelayHelper timeElapsedHelper = new TimeDelayHelper(0);

    private IntermissionEnableListenerInterface enableListener = NullIntermissionEnableListener.getInstance();
    
    public Intermission()
    {
    }

    public void setListener(IntermissionEnableListenerInterface enableListener)
    {
        this.enableListener = enableListener;
    }
    
    public TimeDelayHelper getTimeDelayHelper()
    {
        return timeElapsedHelper;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;

        if (this.isEnabled())
        {
            this.getTimeDelayHelper().setStartTime();
        }
        
        this.enableListener.notify(enabled);
    }

    public boolean isEnabled()
    {
        return enabled;
    }
}
