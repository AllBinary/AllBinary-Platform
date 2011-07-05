/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: Sep 29, 2007, 7:09:17 AM
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.game.terrain;

import allbinary.logic.basic.util.event.AllBinaryEventCircularPool;

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
