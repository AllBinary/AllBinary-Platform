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
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class WaypointLogHelper {
    
    protected static final WaypointLogHelper instance = new WaypointLogHelper();

    /**
     * @return the instance
     */
    public static WaypointLogHelper getInstance() {
        return instance;
    }
    
    public void onWaypointEvent(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final PathFindingLayerInterface advancedRTSGameLayer) {
        
    }

    public void addWaypointFromBuilding(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final PathFindingLayerInterface advancedRTSGameLayer) {
    
    }    

    public void addWaypointFromBuilding(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final PathFindingLayerInterface advancedRTSGameLayer, final BasicArrayList list) {
        
    }
    
    public void insertWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int index, final CollidableDestroyableDamageableLayer rtsLayer, final String waypointName) {
        
    }

    public void insertWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int index, final CollidableDestroyableDamageableLayer rtsLayer, final String waypointName, final BasicArrayList list) {
        
    }
    
    public void setRandomGeographicMapCellHistory(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final BasicArrayList pathsList) {
        
    }
    
    public void moveAwayFromBuilding(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        
    }
    
    public void needToMove(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior) {

    }

    public void setRandomGeographicMapCellHistory(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        
    }
    
    public void setCurrentPathGeographicMapCellPosition(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellPosition existingCurrentPathGeographicMapCellPosition, final GeographicMapCellPosition currentPathGeographicMapCellPosition) {

    }

    public void setNextUnvisitedPathGeographicMapCellPosition(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellPosition existingNextUnvisitedPathGeographicMapCellPosition, final GeographicMapCellPosition nextUnvisitedPathGeographicMapCellPosition) {

    }        

}
