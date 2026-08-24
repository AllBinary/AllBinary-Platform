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
import jsinterop.annotations.JsMethod;


@JsType
public class BaseSoundsFactory 
implements SoundsFactoryInterface
{
    private boolean initialized;

    @Override    
    @JsMethod
    public void init() 
    {
    }

    @Override
    @JsMethod
    public Sound[] getSoundInterfaceArray() 
    throws Exception 
    {
        return new Sound[0];
    }

    @Override
    @JsMethod
    public void setInitialized(boolean initialized)
    {
        this.initialized = initialized;
    }

    @Override
    @JsMethod
    public boolean isInitialized()
    {
        return this.initialized;
    }
}
