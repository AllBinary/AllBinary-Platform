/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.input.form;

import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.game.layer.unit.UnitLayer;
import org.allbinary.game.layer.unit.UnitWaypointBehavior;
import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class AssignWaypointsUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final AssignWaypointsUtil instance = new AssignWaypointsUtil();
    
    public static AssignWaypointsUtil getInstance()
    {
        return instance;
    }
    
    private final RTSLayerEvent WAYPOINT_EVENT = new RTSLayerEvent(null);
    
    public void set(
            UnitLayer unitLayer, 
            AdvancedRTSGameLayer ownerAdvancedRTSGameLayer)
    {
        try
        {
        BasicArrayList list = 
            ownerAdvancedRTSGameLayer.getWaypointBehavior().getOwnedWaypointList();

        UnitWaypointBehavior unitWaypointBehavior = 
            (UnitWaypointBehavior) unitLayer.getWaypointBehavior();

        for (int index = list.size() - 1; index >= 0; index--)
        {
            RTSLayer waypointLayer = (RTSLayer) list.get(index);
            
            WAYPOINT_EVENT.setRtsLayer(waypointLayer);
            unitWaypointBehavior.onWaypointEvent(WAYPOINT_EVENT);
        }

        RTSLayer waypointLayer = PrimaryWaypointHelper.getInstance().getWaypointLayer();

        if (waypointLayer != null)
        {
            WAYPOINT_EVENT.setRtsLayer(waypointLayer);
            unitWaypointBehavior.onWaypointEvent(WAYPOINT_EVENT);
        }
        
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "set", e);
        }
    }
}
