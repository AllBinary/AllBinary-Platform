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

import org.allbinary.animation.caption.CaptionAnimationHelperBase;
import org.allbinary.game.layer.WaypointBehaviorBase;
import org.allbinary.game.tracking.TrackingEvent;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public interface WaypointLayerInterface {

    void init(final GeographicMapCellHistory geographicMapCellHistory,
        final BasicArrayList geographicMapCellPositionBasicArrayList) throws Exception;

    BasicArrayList getMoveOutOfBuildAreaPath(final GeographicMapCellPosition geographicMapCellPosition);
    
    void setClosestGeographicMapCellHistory(final BasicArrayList pathsList) throws Exception;
    
    void teleportTo(final GeographicMapCellPosition geographicMapCellPosition);
    
    void setLoad(int resource) throws Exception;
    
    BasicArrayList getSurroundingGeographicMapCellPositionList() throws Exception;
    
    boolean isSelected();
    
    boolean shouldAddWaypointFromBuilding();
    
    boolean isShowMoreCaptionStates();
    
    void trackTo(final String reason) throws Exception;
    void trackTo(final int dx, final int dy) throws Exception;
    
    boolean buildingChase(final AllBinaryLayer allbinaryLayer, final GeographicMapCellPosition cellPosition) throws Exception;
    
    TrackingEvent getTrackingEvent();
    
    void allStop();
    
    boolean isWaypointListEmptyOrOnlyTargets();
    
    GeographicMapCellPosition getCurrentGeographicMapCellPosition() throws Exception;
    GeographicMapCellPosition getTopLeftGeographicMapCellPosition() throws Exception;
    
    CaptionAnimationHelperBase getCaptionAnimationHelper();
    
    WaypointBehaviorBase getWaypointBehavior();

    WaypointLogHelper getWaypointLogHelper();
    Waypoint2LogHelper getWaypoint2LogHelper();
    
    WaypointRunnableLogHelper getWaypointRunnableLogHelper();
    
}
