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
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final Waypoint2SelectedLogHelper instance = new Waypoint2SelectedLogHelper();

    /**
     * @return the instance
     */
    public static Waypoint2LogHelper getInstance() {
        return Waypoint2SelectedLogHelper.instance;
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
    
    @Override
    public void initRange(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int closeRange, final int sensorRange) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.CLOSE_RANGE);
        stringBuffer.appendint(closeRange);
        stringBuffer.append(this.SENSOR_RANGE);
        stringBuffer.appendint(sensorRange);

        this.logUtil.putF(stringBuffer.toString(), this, "selected: initRange");

    }

    @Override
    public void processPossibleTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase, final PathFindingLayerInterface layerInterface,
        final int anotherTargetDistance, final boolean isShorterThanCurrentTargetDistance, final boolean isCurrentTargetDestroyed) {
        
        if (waypointBehaviorBase.isWaypointListEmptyOrOnlyTargets() && waypointBehaviorBase.isInSensorRange((CollidableDestroyableDamageableLayer) layerInterface, anotherTargetDistance)) {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
            stringBuffer.append(this.ONLY_TARGETS);
            stringBuffer.appendboolean(waypointBehaviorBase.isWaypointListEmptyOrOnlyTargets());
            stringBuffer.append(this.SHORTER_THAN_CURRENT_TARGET_DISTANCE);
            stringBuffer.appendboolean(isShorterThanCurrentTargetDistance);
            stringBuffer.append(this.CURRENT_TARGET_DESTROYED);
            stringBuffer.appendboolean(isCurrentTargetDestroyed);
            stringBuffer.append(this.CURRENT_TARGET_NOT_AVAILABLE);
            stringBuffer.appendboolean((waypointBehaviorBase.getCurrentTargetLayerInterface() == null));

            this.logUtil.putF(stringBuffer.toString(), this, PROCESS_POSSIBLE_TARGET);
        }

    }

    @Override
    public void processSetTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase, final PathFindingLayerInterface layerInterface, final int anotherTargetDistance) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(waypointBehaviorBase.getCurrentTargetingStateString());
        stringBuffer.append(this.ENEMY_POSSIBLE_TARGET);
        stringBuffer.append(layerInterface.getName());
        stringBuffer.append(" anotherTargetDistance: ");
        stringBuffer.appendint(anotherTargetDistance);

        this.logUtil.putF(stringBuffer.toString(), this, "selected: processPossibleTarget2");

    }

    @Override
    public void processPossibleTargetCloser(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase, final PathFindingLayerInterface layerInterface, final int anotherTargetDistance) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(waypointBehaviorBase.getCurrentTargetingStateString());
        stringBuffer.append(this.ENEMY_POSSIBLE_TARGET);
        stringBuffer.append(layerInterface.getName());
        stringBuffer.append(" anotherTargetDistance: ");
        stringBuffer.appendint(anotherTargetDistance);

        this.logUtil.putF(stringBuffer.toString(), this, "selected: processPossibleTarget3");

    }

    @Override
    public void setTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase, final PathFindingLayerInterface layerInterface, final int anotherTargetDistance) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(waypointBehaviorBase.getName());
        stringBuffer.append(" targeting: ");
        stringBuffer.append(layerInterface.getName());
        stringBuffer.append(" at: ");
        stringBuffer.appendint(anotherTargetDistance);
        stringBuffer.append(" instead of: ");
        stringBuffer.append(waypointBehaviorBase.getCurrentTargetingStateString());

        this.logUtil.putF(stringBuffer.toString(), this, "selected: setTarget");

    }
    
    @Override
    public void setTargetPath(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        this.logUtil.putF( "Target Destroyed", this, "selected: setTargetPath");
    }

    @Override
    public void setTargetPathIgnoreNewPath(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" Set Path To Target: ");
        stringBuffer.append(waypointBehaviorBase.getCurrentTargetLayerInterface().getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(this.stringUtil.toString(waypointBehaviorBase.getCurrentTargetLayerInterface()));

        this.logUtil.putF(stringBuffer.toString(), this, "selected: setTargetPath");

    }

    @Override
    public void processWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase, final PathFindingLayerInterface targetLayer, final int size) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" Size: ");
        stringBuffer.appendint(size);
        stringBuffer.append(" Waypoint: ");
        stringBuffer.append(targetLayer.getName());
        stringBuffer.append(" Target: ");
        stringBuffer.append(this.stringUtil.toString(waypointBehaviorBase.getCurrentTargetLayerInterface()));
        stringBuffer.append(" isTrackingWaypoint: ");
        stringBuffer.appendboolean(waypointBehaviorBase.isTrackingWaypoint());
        stringBuffer.append(" WaypointOverridesAttacking: ");
        stringBuffer.appendboolean(waypointBehaviorBase.isWaypointOverridesAttacking());

        this.logUtil.putF(stringBuffer.toString(), this, "selected: processWaypoint");

    }

    @Override
    public void processWaypointTracked(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase waypointBehaviorBase) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" currentPathGeographicMapCellPosition: ");
        stringBuffer.append(this.stringUtil.toString(waypointBehaviorBase.getCurrentPathGeographicMapCellPosition()));
        stringBuffer.append(" nextUnvisitedPathGeographicMapCellPosition: ");
        stringBuffer.append(this.stringUtil.toString(waypointBehaviorBase.getNextUnvisitedPathGeographicMapCellPosition()));
        stringBuffer.append(" Tracked: ");
        stringBuffer.append(this.stringUtil.toString(waypointBehaviorBase.getCurrentGeographicMapCellHistory().getTracked()));

        this.logUtil.putF(stringBuffer.toString(), this, "selected: processWaypoint");

    }

    @Override
    public void processWaypointTrackedVisit(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final GeographicMapCellPosition geographicMapCellPosition) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" Visited: ");
        stringBuffer.append(this.stringUtil.toString(geographicMapCellPosition));

        this.logUtil.putF(stringBuffer.toString(), this, "selected: processWaypoint");
        
    }

    @Override
    public void processWaypointTrackedWithoutProgress(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final String reason) {
        this.logUtil.putF(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(reason).toString(), this, "turnTo");
    }
    
    @Override
    public void wander(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        this.logUtil.putF(associatedAdvancedRTSGameLayer.getName(), this, "wander");
    }

    @Override
    public void targetDestroyed(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        this.logUtil.putF( new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" Target Destroyed").toString(), this, "selected: processTargeting");
    }
    
    @Override
    public void processTargeting(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int dx, final int dy) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);        
        stringBuffer.append(this.positionStrings.DX_LABEL);
        stringBuffer.appendint(dx);
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(this.positionStrings.DY_LABEL);
        stringBuffer.appendint(dy);

        this.logUtil.putF(stringBuffer.toString(), this, "selected: processTargeting");
        
    }

    @Override
    public void processTargetingNonWayPoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final int dx, final int dy) {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(" Targeting Non Waypoint ");
        stringBuffer.append(this.positionStrings.DX_LABEL);
        stringBuffer.appendint(dx);
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(this.positionStrings.DY_LABEL);
        stringBuffer.appendint(dy);

        this.logUtil.putF(stringBuffer.toString(), this, "selected: processTargeting");

    }

    @Override
    public void removeWaypoint(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final PathFindingLayerInterface waypointLayer, final String reason) {
        
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(unitWaypointBehavior2.getName());
        stringBuffer.append(" remove: ");
        stringBuffer.append(waypointLayer.getName());
        stringBuffer.append(" because: ");
        stringBuffer.append(reason);

        this.logUtil.putF(stringBuffer.toString(), this, "selected: removeWaypoint");

    }
    
    @Override
    public void removeWaypointList(final PathFindingLayerInterface associatedAdvancedRTSGameLayer, final WaypointBehaviorBase unitWaypointBehavior2, final BasicArrayList list) {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(associatedAdvancedRTSGameLayer.getName());
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(unitWaypointBehavior2.getName());
        stringBuffer.append(" Waypoints: ");
        stringBuffer.append(this.stringUtil.toString(list));

        this.logUtil.putF(
            stringBuffer.toString(), this, "selected: removeWaypoint");
    }
    

    @Override
    public void removeWaypointClear(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        this.logUtil.putF( new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" Clearing removed waypoint").toString(), this, "selected: removeWaypoint");
    }

    @Override
    public void clearTarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        this.logUtil.putF(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" Cleared Target").toString(), this, "selected: clearTarget");
    }

    @Override
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

            this.logUtil.putF(stringBuffer.toString(), this, "selected: onWaypointEvent");
        
    }

    @Override
    public void targetMovedSoRetarget(final PathFindingLayerInterface associatedAdvancedRTSGameLayer) {
        this.logUtil.putF(new StringMaker().append(associatedAdvancedRTSGameLayer.getName()).append(" - target moved so retargeting").toString(), this, "turnTo");
    }
    
}
