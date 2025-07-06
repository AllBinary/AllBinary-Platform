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
package org.allbinary.game.layer;

import org.allbinary.math.AngleInfo;
import org.allbinary.math.NamedAngle;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class RTSLayerLogHelper {
    
    protected static final RTSLayerLogHelper instance = new RTSLayerLogHelper();

    /**
     * @return the instance
     */
    public static RTSLayerLogHelper getInstance() {
        return instance;
    }
    
    public void setClosestGeographicMapCellHistory(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final BasicArrayList pathsList) {
        
    }

    public void trackTo(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellPosition nextUnvisitedPathGeographicMapCellPosition, final int dx, final int dy, final String reason) {
        
    }

    public void turnTo(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int dx, final int dy, final AngleInfo angleInfo, final int angle, final NamedAngle movementAngle, final boolean evading, int targetAngle) {
        
    }

    public void doneMoving(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {

    }

    public void closeEnough(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {

    }
    
    public void movingLeft(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {

    }
    
    public void movingRight(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        
    }
    
    public void movingUp(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {

    }

    public void movingDown(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {

    }

    public void currentMoveEnded(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        
    }
    
    public void evade(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {

    }
    
    public void rotateLeft(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {

    }
    
    public void rotateRight(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {

    }

    public void handle(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final NamedAngle movementAngle) {

    }
    
    public void noRotation(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {

    }    

    public void notOnPath(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellHistory geographicMapCellHistory, final GeographicMapCellPosition currentGeographicMapCellPosition, final BasicArrayList pathList) {
    
    }
    
}
