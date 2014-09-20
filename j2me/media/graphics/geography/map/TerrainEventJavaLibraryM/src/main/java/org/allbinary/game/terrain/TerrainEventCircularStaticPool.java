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
package org.allbinary.game.terrain;

import org.allbinary.logic.basic.util.event.AllBinaryEventCircularPool;

public class TerrainEventCircularStaticPool
{
    private static final TerrainEventCircularStaticPool instance = new TerrainEventCircularStaticPool();

    public static TerrainEventCircularStaticPool getInstance()
    {
        return instance;
    }

    private AllBinaryEventCircularPool EVENT_POOL = new AllBinaryEventCircularPool(20);
    
    public void init()
    {
        EVENT_POOL.init(new TerrainEventFactory());
    }
    
    public synchronized TerrainEvent getInstance(BasicTerrainInfo basicTerrainInfo)
          throws Exception
    {
    	TerrainEvent trackingEvent = (TerrainEvent) EVENT_POOL.getNextInstance();
    	trackingEvent.setBasicTerrainInfoForCircularStaticPool(basicTerrainInfo);

        return trackingEvent;
    }       
}
