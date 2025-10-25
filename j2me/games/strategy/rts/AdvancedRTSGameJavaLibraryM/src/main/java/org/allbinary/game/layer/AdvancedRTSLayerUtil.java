/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 */
package org.allbinary.game.layer;

import java.util.Hashtable;

import org.allbinary.game.layer.waypoint.WorkWaypoint;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.graphics.geography.map.racetrack.BaseRaceTrackGeographicMap;

public class AdvancedRTSLayerUtil
{
    private static final AdvancedRTSLayerUtil instance = new AdvancedRTSLayerUtil();
    
    public static AdvancedRTSLayerUtil getInstance()
    {
        return instance;
    }
    
    public final Hashtable createFakeRTSLayerHashtable(final BaseRaceTrackGeographicMap baseRaceTrackGeographicMap)
    {
        final Hashtable hashtable = RTSLayerUtil.getInstance().createFakeRTSLayerHashtable(baseRaceTrackGeographicMap);
        
        hashtable.put(WorkWaypoint.ID, 
                SmallIntegerSingletonFactory.getInstance().getInstance(50));

        return hashtable;
    }
}
