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
package org.allbinary.game.midlet;

import org.allbinary.logic.util.event.AllBinaryEventObject;

public class DemoGameMidletEvent extends AllBinaryEventObject
{
    private DemoGameMidletState demoGameMidletState;

    public DemoGameMidletEvent(Object object,
            DemoGameMidletState demoGameMidletState)
    {
        super(object);

        this.demoGameMidletState = demoGameMidletState;
    }

    public void init(Object object)
    {
        this.setSource(object);
    }

    public void setDemoGameMidletState(DemoGameMidletState demoGameMidletState)
    {
        this.demoGameMidletState = demoGameMidletState;
    }

    public DemoGameMidletState getDemoGameMidletState()
    {
        return demoGameMidletState;
    }
}
