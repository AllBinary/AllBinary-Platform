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
package org.allbinary.game.combat.destroy.event;

import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.util.event.AllBinaryEventCircularPool;

public class DestroyEventCircularStaticPool {
    
    private static final DestroyEventCircularStaticPool instance = 
        new DestroyEventCircularStaticPool();
    
    public static DestroyEventCircularStaticPool getInstance()
    {
        return instance;
    }
    
    private AllBinaryEventCircularPool EVENT_POOL = 
        new AllBinaryEventCircularPool(20);
    
    private DestroyEventCircularStaticPool()
    {
        
    }
    
    public void init(AllBinaryGameCanvas combatGameCanvas)
    {
        EVENT_POOL.init(new DestroyEventFactory(combatGameCanvas));
    }
    
    public DestroyedEvent getInstance(AllBinaryLayer layerInterface)
          throws Exception
    {
    	DestroyedEvent destroyedEvent = 
    	    (DestroyedEvent) EVENT_POOL.getNextInstance();

    	destroyedEvent.setLayerInterfaceForCircularStaticPool(layerInterface);

        return destroyedEvent;
    }
}
