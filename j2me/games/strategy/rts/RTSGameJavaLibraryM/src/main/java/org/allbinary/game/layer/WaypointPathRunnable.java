/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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
package org.allbinary.game.layer;


import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

/**
 *
 * @author user
 */
public class WaypointPathRunnable extends WaypointPathRunnableBase
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    public WaypointPathRunnable()
    {
    }
        
    @Override
    public void run()
    {
        try
        {
            //this.logUtil.putF("", this, "run");
                
            this.pathFindingLayer.getWaypointRunnableLogHelper().start(this.pathFindingLayer);

            final WaypointBehaviorBase waypointBehavior = 
                this.pathFindingLayer.getWaypointBehavior();
            
            final GeographicMapCellPosition geographicMapCellPosition = 
                this.pathFindingLayer.getCurrentGeographicMapCellPosition();
            
            if (geographicMapCellPosition == null) {
                throw new Exception("Should never be running here");
            }
            
            waypointBehavior.setWaypointPathsList(
                this.targetPathFindingLayer.getWaypointBehavior().getWaypoint().getPathsListRunnable(
                    geographicMapCellPosition)
                );

            this.pathFindingLayer.getWaypointRunnableLogHelper().end(this.pathFindingLayer);
            
            //this.actuallyRunning = false;
            
            Thread.sleep((long) 15 + (this.priorityP * 2));
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            //this.logUtil.put(commonStrings.EXCEPTION, this, "run", e);
            this.setRunning(false);
        }
    }
 
    @Override
    public boolean isDone() {
        return true;
    }

}
