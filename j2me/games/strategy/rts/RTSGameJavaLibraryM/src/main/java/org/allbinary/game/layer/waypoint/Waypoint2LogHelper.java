/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
import org.allbinary.game.layer.WaypointBehaviorBase;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class Waypoint2LogHelper {
    
    protected static final Waypoint2LogHelper instance = new Waypoint2LogHelper();

    /**
     * @return the instance
     */
    public static Waypoint2LogHelper getInstance() {
        return instance;
    }

    public void initRange(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int closeRange, final int sensorRange) {
        
    }
    
    public void processPossibleTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final PathFindingLayerInterface layerInterface, 
        final int anotherTargetDistance, final boolean isShorterThanCurrentTargetDistance, final boolean isCurrentTargetDestroyed) {
        
    }

    public void processPossibleTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final PathFindingLayerInterface layerInterface, final int anotherTargetDistance) {
        
    }
    
    public void processPossibleTargetCloser(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final PathFindingLayerInterface layerInterface, final int anotherTargetDistance) {
        
    }

    public void setTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final PathFindingLayerInterface layerInterface, final int anotherTargetDistance) {
        
    }
    
    public void setTargetPath(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        
    }
    
    public void setTargetPath(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2) {
        
    }
    
    public void processWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final PathFindingLayerInterface targetLayer, final int size) {
        
    }

    public void processWaypointTracked(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2) {
    
    }
    
    public void processWaypointTracked(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellPosition geographicMapCellPosition) {
        
    }
    
    public void processWaypointTrackedWithoutProgress(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final String reason) {
        
    }
    
    public void wander(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        
    }

    public void targetDestroyed(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
    
    }
    
    public void processTargeting(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int dx, final int dy) {
        
    }

    public void processTargetingNonWayPoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int dx, final int dy) {
        
    }
 
    public void removeWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final PathFindingLayerInterface waypointLayer, final String reason) {
        
    }

    public void removeWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final BasicArrayList list) {
    
    }
    
    public void removeWaypointClear(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        
    }
    
    public void clearTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
            
    }

    public void addWaypointFromUser(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final PathFindingLayerInterface advancedRTSGameLayer) {
        
    }

    public void targetMovedSoRetarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        
    }
    
}
