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
package org.allbinary.game.tracking;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.util.event.AllBinaryEventCircularPool;

public class TrackingEventCircularStaticPool 
{
    private static TrackingEventCircularStaticPool instance = 
        new TrackingEventCircularStaticPool();
    
    public static TrackingEventCircularStaticPool getInstance()
    {
        return TrackingEventCircularStaticPool.instance;
    }

    private AllBinaryEventCircularPool EVENT_POOL = 
        new AllBinaryEventCircularPool(20);
    
    public void init()
    {
        this.EVENT_POOL.init(new TrackingEventFactory());
    }
    
    public synchronized TrackingEvent getNextInstance(AllBinaryLayer layerInterface)
          throws Exception
    {
    	TrackingEvent trackingEvent = (TrackingEvent) this.EVENT_POOL.getNextInstance();

    	trackingEvent.setLayerInterfaceForCircularStaticPool(layerInterface);

        return trackingEvent;
    }
}
