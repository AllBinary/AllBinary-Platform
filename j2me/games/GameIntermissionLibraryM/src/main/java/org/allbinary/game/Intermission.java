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
package org.allbinary.game;

import jsinterop.annotations.JsType;

import org.allbinary.time.TimeDelayHelper;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class Intermission implements IntermissionInterface
{
    private boolean enabled;

    private final TimeDelayHelper timeElapsedHelper = new TimeDelayHelper(0);

    private IntermissionEnableListenerInterface intermissionEnableListener = NullIntermissionEnableListener.getInstance();
    
    @JsConstructor
    public Intermission()
    {
    }

    @Override
    @JsMethod
    public void setListener(IntermissionEnableListenerInterface enableListener)
    {
        this.intermissionEnableListener = enableListener;
    }
    
    @Override
    @JsMethod
    public TimeDelayHelper getTimeDelayHelper()
    {
        return this.timeElapsedHelper;
    }

    @Override
    @JsMethod
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;

        if (this.isEnabled())
        {
            this.getTimeDelayHelper().setStartTimeTNT();
        }
        
        this.intermissionEnableListener.notifyIntermission(enabled);
    }

    @Override
    @JsMethod
    public boolean isEnabled()
    {
        return this.enabled;
    }

}
