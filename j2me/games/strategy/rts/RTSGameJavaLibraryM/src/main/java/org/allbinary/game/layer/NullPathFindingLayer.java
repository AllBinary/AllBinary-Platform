/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

import org.allbinary.animation.caption.CaptionAnimationHelperBase;
import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.waypoint.Waypoint2LogHelper;
import org.allbinary.game.layer.waypoint.WaypointLogHelper;
import org.allbinary.game.layer.waypoint.WaypointRunnableLogHelper;
import org.allbinary.game.tracking.TrackingEvent;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class NullPathFindingLayer implements PathFindingLayerInterface {
    
    public static final NullPathFindingLayer NULL_PATH_FINDING_LAYER = new NullPathFindingLayer();
    
    @Override
    public String getName() {
        throw new RuntimeException();
    }
    
    @Override
    public BasicArrayList getEndGeographicMapCellPositionList() throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public boolean shouldHandleStartSameAsEnd() {
        throw new RuntimeException();
    }
    
    @Override
    public GeographicMapCellPositionAreaBase getGeographicMapCellPositionArea() {
        throw new RuntimeException();
    }
    
    @Override
    public SelectionHudPaintable getHudPaintable() {
        throw new RuntimeException();
    }
 
    @Override
    public void handleCost(PathFindingLayerInterface ownerLayer) throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public PathFindingLayerInterface getParentLayer() {
        throw new RuntimeException();
    }

    @Override
    public RTSLayer2LogHelper getRTSLayer2LogHelper() {
        throw new RuntimeException();
    }

    @Override
    public void setTarget(final PathFindingLayerInterface targetGameLayer) throws Exception {
        throw new RuntimeException();
    }

    @Override
    public void init(final GeographicMapCellHistory geographicMapCellHistory,
        final BasicArrayList geographicMapCellPositionBasicArrayList) throws Exception {
        throw new RuntimeException();
    }

    @Override
    public BasicArrayList getMoveOutOfBuildAreaPath(final GeographicMapCellPosition geographicMapCellPosition) {
        throw new RuntimeException();
    }
    
    @Override
    public void setClosestGeographicMapCellHistory(final BasicArrayList pathsList) throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public void teleportTo(final GeographicMapCellPosition geographicMapCellPosition) {
        throw new RuntimeException();
    }
    
    @Override
    public void setLoad(int resource) throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public BasicArrayList getSurroundingGeographicMapCellPositionList() throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public boolean isSelected() {
        throw new RuntimeException();
    }
    
    @Override
    public boolean shouldAddWaypointFromBuilding() {
        throw new RuntimeException();
    }
    
    @Override
    public boolean isShowMoreCaptionStates() {
        throw new RuntimeException();
    }
    
    @Override
    public void trackTo(final String reason) throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public void trackTo(final int dx, final int dy) throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public boolean buildingChase(final AllBinaryLayer allbinaryLayer, final GeographicMapCellPosition cellPosition) throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public TrackingEvent getTrackingEvent() {
        throw new RuntimeException();
    }
    
    @Override
    public void allStop() {
        throw new RuntimeException();
    }
    
    @Override
    public boolean isWaypointListEmptyOrOnlyTargets() {
        throw new RuntimeException();
    }
    
    @Override
    public GeographicMapCellPosition getCurrentGeographicMapCellPosition() throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public GeographicMapCellPosition getTopLeftGeographicMapCellPosition() throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public CaptionAnimationHelperBase getCaptionAnimationHelper() {
        throw new RuntimeException();
    }
    
    @Override
    public WaypointBehaviorBase getWaypointBehavior() {
        throw new RuntimeException();
    }

    @Override
    public WaypointLogHelper getWaypointLogHelper() {
        throw new RuntimeException();
    }
    
    @Override
    public Waypoint2LogHelper getWaypoint2LogHelper() {
        throw new RuntimeException();
    }
    
    @Override
    public WaypointRunnableLogHelper getWaypointRunnableLogHelper() {
        throw new RuntimeException();
    }
    
    @Override
    public Group[] getGroupInterface() {
        throw new RuntimeException();
    }
    
    @Override
    public boolean isDestroyed() throws Exception {
        throw new RuntimeException();
    }
    
    @Override
    public int getSourceId() {
        throw new RuntimeException();
    }

}
