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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class WaypointSelectedLogHelper extends WaypointLogHelper {
    protected final LogUtil logUtil = LogUtil.getInstance();

 
    private static final WaypointSelectedLogHelper instance = new WaypointSelectedLogHelper();

    /**
     * @return the instance
     */
    public static WaypointSelectedLogHelper getInstance() {
        return instance;
    }

    private final CommonSeps commonSeps = CommonSeps.getInstance();
    private final StringUtil stringUtil = StringUtil.getInstance();
    
    @Override
    public void onWaypointEvent(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final PathFindingLayerInterface advancedRTSGameLayer) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
//        stringBuffer.append(" Selected: ");
//        stringBuffer.append(associatedAdvancedRTSGameLayer.isSelected());
        stringBuffer.append(" onWaypointEvent: ");
        stringBuffer.append(advancedRTSGameLayer.getName());

        if (advancedRTSGameLayer.getParentLayer() != null) {
            stringBuffer.append(" With Parent: ");
            stringBuffer.append(advancedRTSGameLayer.getParentLayer().getName());
        }

        logUtil.put(stringBuffer.toString(), this, "onWaypointEvent");
        
    }
    
    @Override
    public void addWaypointFromBuilding(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final PathFindingLayerInterface advancedRTSGameLayer) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
//        stringBuffer.append(" Selected: ");
//        stringBuffer.append(associatedAdvancedRTSGameLayer.isSelected());
        stringBuffer.append(" Adding Waypoint: ");
        stringBuffer.append(advancedRTSGameLayer.getName());

        if (advancedRTSGameLayer.getParentLayer() != null) {
            stringBuffer.append(" With Parent: ");
            stringBuffer.append(advancedRTSGameLayer.getParentLayer().getName());
        }

        logUtil.put(stringBuffer.toString(), this, "selected: onWaypointEvent");
        
    }

    @Override
    public void addWaypointFromBuilding(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final PathFindingLayerInterface advancedRTSGameLayer, final BasicArrayList list) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" Waypoints: ");
        stringBuffer.append(stringUtil.toString(list));

        logUtil.put(stringBuffer.toString(), this, "selected: onWaypointEvent");
        
    }
 
    @Override
    public void insertWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int index, CollidableDestroyableDamageableLayer rtsLayer, final String waypointName) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(waypointName);
        stringBuffer.append(" Insert: ");
        stringBuffer.append(rtsLayer.getName());
        stringBuffer.append(" for Waypoint: ");

        logUtil.put(stringBuffer.toString(), this, "selected: insertWaypoint");

    }
    
    @Override
    public void insertWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int index, final CollidableDestroyableDamageableLayer rtsLayer, final String waypointName, final BasicArrayList list) {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(waypointName);
        stringBuffer.append(" Insert: ");
        stringBuffer.append(rtsLayer.getName());
        stringBuffer.append(" Waypoints: ");
        stringBuffer.append(stringUtil.toString(list));

        logUtil.put(stringBuffer.toString(), this, "selected: insertWaypoint");
    }
    
    @Override
    public void setRandomGeographicMapCellHistory(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final BasicArrayList pathsList) {

        final int size = pathsList.size();
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(CommonLabels.getInstance().START);
        stringBuffer.append(size);
        stringBuffer.append(" -> ");
        stringBuffer.append(stringUtil.toString(pathsList));

        logUtil.put(stringBuffer.toString(), this, "selected: setRandomGeographicMapCellHistory");

    }

    @Override
    public void moveAwayFromBuilding(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" Trying to move away from building").toString(), this, "selected: moveAwayFromBuilding");
    }    
    
    @Override
    public void needToMove(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(unitWaypointBehavior.getMovementLogicAsString()).toString(), this, "needToMove");
    }

    @Override
    public void setRandomGeographicMapCellHistory(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" pathsList is null").toString(), this, "setRandomGeographicMapCellHistory");
    }

    @Override
    public void setCurrentPathGeographicMapCellPosition(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellPosition existingCurrentPathGeographicMapCellPosition, final GeographicMapCellPosition currentPathGeographicMapCellPosition) {
        if(existingCurrentPathGeographicMapCellPosition == null) logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" currentPathGeographicMapCellPosition: ").append(stringUtil.toString(currentPathGeographicMapCellPosition)).toString(), this, "setCurrentPathGeographicMapCellPosition");
    }

    @Override
    public void setNextUnvisitedPathGeographicMapCellPosition(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellPosition existingNextUnvisitedPathGeographicMapCellPosition, final GeographicMapCellPosition nextUnvisitedPathGeographicMapCellPosition) {
        if(existingNextUnvisitedPathGeographicMapCellPosition != nextUnvisitedPathGeographicMapCellPosition) logUtil.put(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" nextUnvisitedPathGeographicMapCellPosition: ").append(stringUtil.toString(nextUnvisitedPathGeographicMapCellPosition)).toString(), this, "setNextUnvisitedPathGeographicMapCellPosition");
    }        

}
