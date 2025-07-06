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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.math.PositionStrings;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class Waypoint2SelectedLogHelper extends Waypoint2LogHelper {

    protected static final Waypoint2SelectedLogHelper instance = new Waypoint2SelectedLogHelper();

    /**
     * @return the instance
     */
    public static Waypoint2LogHelper getInstance() {
        return instance;
    }

    //private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final CommonSeps commonSeps = CommonSeps.getInstance();
    private final PositionStrings positionStrings = PositionStrings.getInstance();
    private final StringUtil stringUtil = StringUtil.getInstance();

    private final String ENEMY_POSSIBLE_TARGET = " Enemy Possible Target: ";
    private final String CLOSE_RANGE = "closeRange: ";
    private final String SENSOR_RANGE = " sensorRange: ";
    private final String ONLY_TARGETS = " Only Targets: ";
    private final String SHORTER_THAN_CURRENT_TARGET_DISTANCE = " isShorterThanCurrentTargetDistance: ";
    private final String CURRENT_TARGET_DESTROYED = " isCurrentTargetDestroyed: ";
    private final String CURRENT_TARGET_NOT_AVAILABLE = " isCurrentTargetNotAvailable: ";
    private final String PROCESS_POSSIBLE_TARGET = "selected: processPossibleTarget1";
    
    //@Override
    public void initRange(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int closeRange, final int sensorRange) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(CLOSE_RANGE);
        stringBuffer.append(closeRange);
        stringBuffer.append(SENSOR_RANGE);
        stringBuffer.append(sensorRange);

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: initRange"));

    }

    //@Override
    public void processPossibleTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase, final PathFindingLayerInterface layerInterface,
        final int anotherTargetDistance, final boolean isShorterThanCurrentTargetDistance, final boolean isCurrentTargetDestroyed) {
        
        if (waypointBehaviorBase.isWaypointListEmptyOrOnlyTargets() && waypointBehaviorBase.isInSensorRange((CollidableDestroyableDamageableLayer) layerInterface, anotherTargetDistance)) {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
            stringBuffer.append(ONLY_TARGETS);
            stringBuffer.append(waypointBehaviorBase.isWaypointListEmptyOrOnlyTargets());
            stringBuffer.append(SHORTER_THAN_CURRENT_TARGET_DISTANCE);
            stringBuffer.append(isShorterThanCurrentTargetDistance);
            stringBuffer.append(CURRENT_TARGET_DESTROYED);
            stringBuffer.append(isCurrentTargetDestroyed);
            stringBuffer.append(CURRENT_TARGET_NOT_AVAILABLE);
            stringBuffer.append((waypointBehaviorBase.getCurrentTargetLayerInterface() == null));

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, PROCESS_POSSIBLE_TARGET));
        }

    }

    //@Override
    public void processPossibleTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase, final PathFindingLayerInterface layerInterface, final int anotherTargetDistance) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(waypointBehaviorBase.getCurrentTargetingStateString());
        stringBuffer.append(ENEMY_POSSIBLE_TARGET);
        stringBuffer.append(layerInterface.getName());
        stringBuffer.append(" anotherTargetDistance: ");
        stringBuffer.append(anotherTargetDistance);

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: processPossibleTarget2"));

    }

    //@Override
    public void processPossibleTargetCloser(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase, final PathFindingLayerInterface layerInterface, final int anotherTargetDistance) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(waypointBehaviorBase.getCurrentTargetingStateString());
        stringBuffer.append(ENEMY_POSSIBLE_TARGET);
        stringBuffer.append(layerInterface.getName());
        stringBuffer.append(" anotherTargetDistance: ");
        stringBuffer.append(anotherTargetDistance);

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: processPossibleTarget3"));

    }

    //@Override
    public void setTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase, final PathFindingLayerInterface layerInterface, final int anotherTargetDistance) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(waypointBehaviorBase.getName());
        stringBuffer.append(" targeting: ");
        stringBuffer.append(layerInterface.getName());
        stringBuffer.append(" at: ");
        stringBuffer.append(anotherTargetDistance);
        stringBuffer.append(" instead of: ");
        stringBuffer.append(waypointBehaviorBase.getCurrentTargetingStateString());

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: setTarget"));

    }
    
    //@Override
    public void setTargetPath(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        LogUtil.put(LogFactory.getInstance( "Target Destroyed", this, "selected: setTargetPath"));
    }

    //@Override
    public void setTargetPath(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" Set Path To Target: ");
        stringBuffer.append(waypointBehaviorBase.getCurrentTargetLayerInterface().getName());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(stringUtil.toString(waypointBehaviorBase.getCurrentTargetLayerInterface()));

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: setTargetPath"));

    }

    //@Override
    public void processWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase, final PathFindingLayerInterface targetLayer, final int size) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" Size: ");
        stringBuffer.append(size);
        stringBuffer.append(" Waypoint: ");
        stringBuffer.append(targetLayer.getName());
        stringBuffer.append(" Target: ");
        stringBuffer.append(stringUtil.toString(waypointBehaviorBase.getCurrentTargetLayerInterface()));
        stringBuffer.append(" isTrackingWaypoint: ");
        stringBuffer.append(waypointBehaviorBase.isTrackingWaypoint());
        stringBuffer.append(" WaypointOverridesAttacking: ");
        stringBuffer.append(waypointBehaviorBase.isWaypointOverridesAttacking());

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: processWaypoint"));

    }

    //@Override
    public void processWaypointTracked(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" currentPathGeographicMapCellPosition: ");
        stringBuffer.append(stringUtil.toString(waypointBehaviorBase.getCurrentPathGeographicMapCellPosition()));
        stringBuffer.append(" nextUnvisitedPathGeographicMapCellPosition: ");
        stringBuffer.append(stringUtil.toString(waypointBehaviorBase.getNextUnvisitedPathGeographicMapCellPosition()));
        stringBuffer.append(" Tracked: ");
        stringBuffer.append(stringUtil.toString(waypointBehaviorBase.getCurrentGeographicMapCellHistory().getTracked()));

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: processWaypoint"));

    }

    //@Override
    public void processWaypointTracked(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellPosition geographicMapCellPosition) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" Visited: ");
        stringBuffer.append(stringUtil.toString(geographicMapCellPosition));

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: processWaypoint"));
        
    }

    //@Override
    public void processWaypointTrackedWithoutProgress(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final String reason) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(reason).toString(), this, "turnTo"));
    }
    
    //@Override
    public void wander(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        LogUtil.put(LogFactory.getInstance(associatedAdvancedRTSGameLayer.getName(), this, "wander"));
    }

    //@Override
    public void targetDestroyed(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        LogUtil.put(LogFactory.getInstance( new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" Target Destroyed").toString(), this, "selected: processTargeting"));
    }
    
    //@Override
    public void processTargeting(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int dx, final int dy) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);        
        stringBuffer.append(positionStrings.DX_LABEL);
        stringBuffer.append(dx);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.DY_LABEL);
        stringBuffer.append(dy);

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: processTargeting"));
        
    }

    //@Override
    public void processTargetingNonWayPoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int dx, final int dy) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" Targeting Non Waypoint ");
        stringBuffer.append(positionStrings.DX_LABEL);
        stringBuffer.append(dx);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(positionStrings.DY_LABEL);
        stringBuffer.append(dy);

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: processTargeting"));

    }

    //@Override
    public void removeWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final PathFindingLayerInterface waypointLayer, final String reason) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(unitWaypointBehavior2.getName());
        stringBuffer.append(" remove: ");
        stringBuffer.append(waypointLayer.getName());
        stringBuffer.append(" because: ");
        stringBuffer.append(reason);

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "selected: removeWaypoint"));

    }
    
    //@Override
    public void removeWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final BasicArrayList list) {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(unitWaypointBehavior2.getName());
        stringBuffer.append(" Waypoints: ");
        stringBuffer.append(stringUtil.toString(list));

        LogUtil.put(LogFactory.getInstance(
            stringBuffer.toString(), this, "selected: removeWaypoint"));
    }
    

    //@Override
    public void removeWaypointClear(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        LogUtil.put(LogFactory.getInstance( new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" Clearing removed waypoint").toString(), this, "selected: removeWaypoint"));
    }

    //@Override
    public void clearTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" Cleared Target").toString(), this, "selected: clearTarget"));
    }

    //@Override
    public void addWaypointFromUser(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final PathFindingLayerInterface advancedRTSGameLayer) {
     
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
//            stringBuffer.append(" Selected: ");
//            stringBuffer.append(associatedAdvancedRTSGameLayer.isSelected());
            stringBuffer.append(" Adding Waypoint to Selected: ");
            stringBuffer.append(advancedRTSGameLayer.getName());

            if (advancedRTSGameLayer.getParentLayer() != null)
            {
                stringBuffer.append(" With Parent: ");
                stringBuffer.append(advancedRTSGameLayer.getParentLayer().getName());
            }

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this,"selected: onWaypointEvent"));
        
    }

    //@Override
    public void targetMovedSoRetarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" - target moved so retargeting").toString(), this, "turnTo"));
    }
    
}
