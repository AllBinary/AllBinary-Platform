/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: August 27, 2006
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.game.tick;

import allbinary.layer.AllBinaryLayerManager;

public class NullTickable implements TickableInterface
{
    private static final NullTickable SINGLETON = new NullTickable();
    
    public static final NullTickable getInstance()
    {
        return SINGLETON;
    }
    
    public void processTick(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
        
    }

    private final String NAME = "NulLTickable";
    public String getName()
    {
        return NAME;
    }
}
