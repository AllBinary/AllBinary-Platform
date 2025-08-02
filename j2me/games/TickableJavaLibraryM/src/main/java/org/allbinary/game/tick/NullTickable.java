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
package org.allbinary.game.tick;

import org.allbinary.layer.AllBinaryLayerManager;

public class NullTickable implements TickableInterface
{
    private static final NullTickable SINGLETON = new NullTickable();
    
    public static final NullTickable getInstance()
    {
        return SINGLETON;
    }
    
    @Override
    public void processTick(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
        
    }

    private final String NAME = "NulLTickable";
    @Override
    public String getName()
    {
        return NAME;
    }
}
