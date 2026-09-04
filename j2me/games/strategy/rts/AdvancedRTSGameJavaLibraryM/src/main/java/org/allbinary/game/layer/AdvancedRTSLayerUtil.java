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

import org.allbinary.game.layer.waypoint.WorkWaypoint;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.graphics.geography.map.racetrack.BaseRaceTrackGeographicMap;
import org.allbinary.util.ABHashtable;

public class AdvancedRTSLayerUtil
{
    private static final AdvancedRTSLayerUtil instance = new AdvancedRTSLayerUtil();
    
    public static AdvancedRTSLayerUtil getInstance()
    {
        return AdvancedRTSLayerUtil.instance;
    }
    
    public final ABHashtable createFakeRTSLayerHashtable(final BaseRaceTrackGeographicMap baseRaceTrackGeographicMap)
    {
        final ABHashtable hashtable = RTSLayerUtil.getInstance().createFakeRTSLayerHashtable(baseRaceTrackGeographicMap);
        
        hashtable.put(WorkWaypoint.ID, 
                SmallIntegerSingletonFactory.getInstance().getAt(50));

        return hashtable;
    }
}
