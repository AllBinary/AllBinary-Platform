
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

import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.game.layer.waypoint.WaypointBase;
import org.allbinary.util.BasicArrayList;
import org.allbinary.game.tick.TickableInterface;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

public class WaypointBehaviorBase implements TickableInterface
{
    private final BasicArrayList ownedWaypointList;

    protected boolean waypointOverridesAttacking = true;
    
    private WaypointBase waypoint;
        
    public WaypointBehaviorBase()
    {
        //TWB - Currently 0ne but should change if that increases
        this.ownedWaypointList = new BasicArrayList(1);
    }

    public String getName()
    {
        return this.getClass().getName();
    }

    public boolean isRunning() {
        return false;
    }
    
    public void processTick(final AllBinaryLayerManager allBinaryLayerManager)
    throws Exception
    {
        
    }
    
    /**
     * @return the ownedWaypointList
     */
    public BasicArrayList getOwnedWaypointList()
    {
        return ownedWaypointList;
    }
    
    /**
     * @return the waypoint
     */
    public WaypointBase getWaypoint()
    {
        return waypoint;
    }
    
    /**
     * @param waypoint the waypoint to set
     */
    public void setWaypoint(final WaypointBase waypoint)
    {
        this.waypoint = waypoint;
    }
    
    public BasicArrayList getSteeringVisitorList()
    {
        return steeringVisitorList;
    }

    public String getMovementLogicAsString() {
        throw new RuntimeException();
        //return null;
    }
    
    public boolean isWaypointListEmptyOrOnlyTargets() {
        throw new RuntimeException();
        //return false;
    }
    
    public boolean isInSensorRange(final CollidableDestroyableDamageableLayer layerInterface, final int targetDistance)
    {
        throw new RuntimeException();
        //return false;
    }
    
    public CollidableDestroyableDamageableLayer getCurrentTargetLayerInterface() {
        throw new RuntimeException();
    }

    public String getCurrentTargetingStateString() {
        throw new RuntimeException();
    }
    
    public boolean isTrackingWaypoint() {
        throw new RuntimeException();
        //return false;
    }
    
    public GeographicMapCellPosition getCurrentPathGeographicMapCellPosition()
    {
        throw new RuntimeException();
    }
    
    public GeographicMapCellHistory getCurrentGeographicMapCellHistory()
    {
        throw new RuntimeException();
    }
    
    public boolean isWaypointOverridesAttacking() {
        return waypointOverridesAttacking;
    }

    
    public GeographicMapCellPosition getNextUnvisitedPathGeographicMapCellPosition() {
        throw new RuntimeException();
    }
    
    public boolean needToMove() {
        throw new RuntimeException();
        //return false;
    }

    public void setWaypointPathsList(final BasicArrayList waypointPathsList) {
        throw new RuntimeException();
    }

    public BasicArrayList getWaypointPathsList()
    {
        throw new RuntimeException();
    }

    public void setTarget(final PathFindingLayerInterface layerInterface)
        throws Exception
    {
        throw new RuntimeException();
    }
    
    public void setTarget(final PathFindingLayerInterface layerInterface, final int anotherTargetDistance)
        throws Exception
    {
        throw new RuntimeException();
    }

    public void clearTarget() throws Exception {
        throw new RuntimeException();
    }

    public void updatePathOnTargetMove(final String reason) throws Exception {
        
    }
    
    //If colliding with a game object then don't try to turn since in steering mode
    private final BasicArrayList steeringVisitorList = new BasicArrayList();
}
