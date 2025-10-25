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
package org.allbinary.game.layer.waypoint;

import org.allbinary.game.layer.PathFindingLayerInterface;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.geological.resources.GeologicalGeographicMapCellPosition;
import org.allbinary.game.layer.geological.resources.GeologicalResource;
import org.allbinary.game.layer.unit.UnitLayer;
import org.allbinary.game.layer.unit.UnitWaypointBehavior;
import org.allbinary.media.audio.WorkSound;
import org.allbinary.util.BasicArrayList;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

/**
 *
 * @author user
 */
public class WorkWaypoint extends Waypoint
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public static final Integer ID = 
        SmallIntegerSingletonFactory.getInstance().getInstance(25);
    
    public WorkWaypoint(final PathFindingLayerInterface ownerLayer)
        throws Exception
    {
        super(ownerLayer, WorkSound.getInstance());
    }

    public void visit(final UnitLayer unitLayer)
        throws Exception
    {
        //Only visit waypoint if in same group
        if(unitLayer.getGroupInterface()[0] != this.ownerLayer.getGroupInterface()[0])
        {
            return;
        }

        final int size = this.getConnectedWaypointList().size();

        if (size > 0)
        {
            //Remove Resources
            final BasicArrayList occupyList =
                this.ownerLayer.getGeographicMapCellPositionArea().getOccupyingGeographicMapCellPositionList();

            boolean outOfResources = true;
            
            GeologicalGeographicMapCellPosition geologicalGeographicMapCellPosition;
            GeologicalResource geologicalResource;
            
            for (int index = occupyList.size(); --index >= 0;)
            {
                geologicalGeographicMapCellPosition =
                    (GeologicalGeographicMapCellPosition) occupyList.get(index);

                geologicalResource = geologicalGeographicMapCellPosition.getGeologicalResource();
                
                if (geologicalResource.getTotal() >= 1)
                {
                  //logUtil.put(unitLayer.getName() + " loaded resource from " + geologicalGeographicMapCellPosition.toString(), this, "visit");
                    
                    outOfResources = false;
                    geologicalResource.remove(unitLayer.getMaxResourceLoad());
                    unitLayer.setLoad(unitLayer.getMaxResourceLoad());
                }
            }

            //Only update if it is shown
            final WaypointInfoHudPaintable waypointInfoHudPaintable = 
                (WaypointInfoHudPaintable) this.ownerLayer.getHudPaintable();
            
            if(this.ownerLayer == waypointInfoHudPaintable.getRtsLayer())
            {
                waypointInfoHudPaintable.updateSelectionInfo();
            }

            //Stop if our of resources
            if(!outOfResources)
            {
                //logUtil.put("Returning: " + unitLayer.getName(), this, "visit");

                final UnitWaypointBehavior unitWaypointBehavior = 
                    (UnitWaypointBehavior) unitLayer.getWaypointBehavior();

                //Remove dead waypoints
                RTSLayer rtsLayer;
                while(this.getConnectedWaypointList().size() > 0)
                {
                    rtsLayer = (RTSLayer) this.getConnectedWaypointList().get(0);
                    
                    if(rtsLayer.isDestroyed())
                    {
                        this.getConnectedWaypointList().remove(rtsLayer);
                    }
                    else
                    {
                        unitWaypointBehavior.insertWaypoint(0, rtsLayer);
                        break;
                    }
                }
            }
        }

        /*
        //Future imple may select from a number of waypoints
        for(int index = 0; index < size; index++)
        {
        this.getWaypointList().add(list.get(index));
        }
         */
    }

    @Override
    public int getType()
    {
        return 2;
    }
}
