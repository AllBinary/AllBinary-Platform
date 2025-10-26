/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer.unit;

import org.allbinary.game.layer.SensorAction;
import org.allbinary.game.layer.SensorActionFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.game.input.form.PrimaryWaypointHelper;
import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.game.layer.WaypointBehaviorBase;
import org.allbinary.game.layer.building.BuildingLayer;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.game.layer.waypoint.event.WaypointEventListenerInterface;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.SimpleGeographicMapCellPositionFactory;
import org.allbinary.time.TimeDelayHelper;

public class UnitWaypointBehavior 
    extends WaypointBehaviorBase
    implements WaypointEventListenerInterface
{
    private static final String PATHING = "Pathing";
    
    protected CommonSeps commonSeps = CommonSeps.getInstance();
    
    private int longWeaponRange = 0;
    
    private SensorAction sensorAction = SensorActionFactory.getInstance().ATTACK;

    private final TimeDelayHelper completeTimeDelayHelper;
    
    protected final GeographicMapCellHistory currentGeographicMapCellHistoryP;
    
    private GeographicMapCellPosition lastPathGeographicMapCellPosition = SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
    private GeographicMapCellPosition currentPathGeographicMapCellPosition = SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;

    private final CollidableDestroyableDamageableLayer FAKE_WAYPOINT_LAYER;
    
    protected final BasicArrayList targetList;

    private final BasicArrayList possibleTargetList;
    
    protected final UnitLayer associatedAdvancedRTSGameLayer;
    
    private boolean moving = false;
    private boolean movingFromStopped = false;
    protected BasicArrayList waypointPathsListP = BasicArrayListUtil.getInstance().getImmutableInstance();
    
    private int currentTargetDistance = Integer.MAX_VALUE;
    protected CollidableDestroyableDamageableLayer currentTargetLayerInterfaceP = CollidableDestroyableDamageableLayer.NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER;
    
    private boolean trackingWaypoint;
    
    protected UnitWaypointBehavior(
            UnitLayer associatedAdvancedRTSGameLayer,
            CollidableDestroyableDamageableLayer fakeWaypoint) 
    throws Exception
    {
        this.associatedAdvancedRTSGameLayer = associatedAdvancedRTSGameLayer;
                
        this.completeTimeDelayHelper = new TimeDelayHelper(30000);
        
        this.targetList = new BasicArrayList();

        this.possibleTargetList = new BasicArrayList();

        this.setWaypointPathsList(BasicArrayListUtil.getInstance().getImmutableInstance());

        this.currentGeographicMapCellHistoryP = new GeographicMapCellHistory();

        this.FAKE_WAYPOINT_LAYER = fakeWaypoint;
    }
 
    protected void initRange(final int weaponRange)
    {
        this.longWeaponRange = weaponRange / 2;
    }

    @Override
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }
    
    @Override
    public void onWaypointEvent(final RTSLayerEvent event) throws Exception
    {
        final AdvancedRTSGameLayer advancedRTSGameLayer =
            (AdvancedRTSGameLayer) event.getRtsLayer();

        this.associatedAdvancedRTSGameLayer.waypointLogHelperP.onWaypointEvent(this.associatedAdvancedRTSGameLayer, advancedRTSGameLayer);
        
        // If primary waypoint or both from parent or User/AI input
        // advancedRTSGameLayer.getParentLayer() == null for buildings
        if (this.associatedAdvancedRTSGameLayer.isSelected())
        {
            this.addWaypointFromUser(advancedRTSGameLayer);
        }
        else
            if(advancedRTSGameLayer.shouldAddWaypointFromBuilding())
            {
                this.addWaypointFromBuilding(advancedRTSGameLayer);
            }
    }

    protected void addWaypointFromUser(final AdvancedRTSGameLayer advancedRTSGameLayer)
    throws Exception
    {
    }

    private void addWaypointFromBuilding(final AdvancedRTSGameLayer advancedRTSGameLayer)
    throws Exception
    {
        if (advancedRTSGameLayer == PrimaryWaypointHelper.getInstance().getWaypointLayer()
                || advancedRTSGameLayer.getParentLayer() == associatedAdvancedRTSGameLayer
                        .getParentLayer())
        {
            if (!this.targetList.contains(advancedRTSGameLayer))
            {
                this.associatedAdvancedRTSGameLayer.waypointLogHelperP.addWaypointFromBuilding(this.associatedAdvancedRTSGameLayer, advancedRTSGameLayer);
                
                if (advancedRTSGameLayer.isDestroyed())
                {
                    throw new Exception("Trying to add a dead: " + advancedRTSGameLayer);
                }

                this.targetList.add(advancedRTSGameLayer);

                this.associatedAdvancedRTSGameLayer.waypointLogHelperP.addWaypointFromBuilding(this.associatedAdvancedRTSGameLayer, advancedRTSGameLayer, this.targetList);
            }
        }
    }

    @Override
    public boolean isWaypointListEmptyOrOnlyTargets()
    {
        return false;
    }
    
    public boolean insertWaypoint(final int index, final CollidableDestroyableDamageableLayer rtsLayer)
    throws Exception
    {
        if (this.canInsertWaypoint(index, rtsLayer))
        {
            this.associatedAdvancedRTSGameLayer.waypointLogHelperP.insertWaypoint(this.associatedAdvancedRTSGameLayer, index, rtsLayer, this.getName());

            if(rtsLayer.isDestroyed())
            {
                throw new Exception("Trying to add a dead: " + rtsLayer);
            }
            
            this.targetList.add(index, rtsLayer);

            this.associatedAdvancedRTSGameLayer.waypointLogHelperP.insertWaypoint(this.associatedAdvancedRTSGameLayer, index, rtsLayer, this.getName(), this.targetList);

            return true;
        }
        return false;
    }
    
    protected void move()
    {
        if (this.isMoving())
        {
            this.setMovingFromStopped(false);
        } else
        {
            this.setMovingFromStopped(true);
        }
        this.setMoving(false);
    }
    
    protected void setRandomGeographicMapCellHistory(final BasicArrayList pathsList) throws Exception
    {
        final int size = pathsList.size();
        
        this.associatedAdvancedRTSGameLayer.waypointLogHelperP.setRandomGeographicMapCellHistory(this.associatedAdvancedRTSGameLayer, pathsList);

        if (size > 0)
        {
            final BasicArrayList geographicMapCellPositionBasicArrayList =
                (BasicArrayList) BasicArrayListUtil.getInstance().getRandom(pathsList);

            this.setGeographicMapCellHistoryPath(geographicMapCellPositionBasicArrayList);
        }
    }
    
    protected void setGeographicMapCellHistoryPath(
            final BasicArrayList geographicMapCellPositionBasicArrayList)
            throws Exception
    {
        this.lastPathGeographicMapCellPosition = SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
        
        if(this.associatedAdvancedRTSGameLayer.showMoreCaptionStates)
        {
            this.associatedAdvancedRTSGameLayer.getCaptionAnimationHelper().update(
                    PATHING, BasicColorFactory.getInstance().GREEN);
        }
        
        this.currentGeographicMapCellHistoryP.init();

        this.associatedAdvancedRTSGameLayer.init(this.currentGeographicMapCellHistoryP,
                geographicMapCellPositionBasicArrayList);

        this.setTrackingWaypoint(true);
        this.getCompleteTimeDelayHelper().setStartTime();
    }
        
    protected boolean canInsertWaypoint(final int index, final CollidableDestroyableDamageableLayer rtsLayer)
    {
        if (this.targetList.size() > 4)
        {
            //logUtil.put(
            //  this.getName() + " has Too Many Waypoints of: " +
            //rtsLayer.getName(), this, "insertWaypoint");
        }
        else if (this.targetList.contains(rtsLayer))
        {
            //logUtil.put(
            //  this.getName() + " Already Contains Same Waypoint: " +
            //rtsLayer.getName(), this, "insertWaypoint");
        }
        else
        {
            return true;
        }
        return false;
    }
    
    public void addBuildingChase(
            final AllBinaryLayer allbinaryLayer, final GeographicMapCellPosition cellPosition)
    throws Exception
    {
    }
    
    protected void moveAwayFromBuilding(final BuildingLayer buildingLayer) throws Exception
    {
        final GeographicMapCellPosition geographicMapCellPosition = 
            this.associatedAdvancedRTSGameLayer.getCurrentGeographicMapCellPosition();

        final BasicArrayList list = buildingLayer.geographicMapCellPositionAreaBase
                .getOccupyingGeographicMapCellPositionList();

        if (list.contains(geographicMapCellPosition))
        {

            if (this.insertWaypoint(0, this.FAKE_WAYPOINT_LAYER))
            {
                this.associatedAdvancedRTSGameLayer.waypointLogHelperP.moveAwayFromBuilding(this.associatedAdvancedRTSGameLayer);

                this.setCurrentTargetLayerInterface((CollidableDestroyableDamageableLayer) this.FAKE_WAYPOINT_LAYER);

                final BasicArrayList pathsList = buildingLayer
                        .getMoveOutOfBuildAreaPath(geographicMapCellPosition);

                // if (pathsList != null)
                // {
                this.associatedAdvancedRTSGameLayer.setClosestGeographicMapCellHistory(pathsList);
                // }
            }
        }
    }

    private final TimeDelayHelper repeatedToLong = new TimeDelayHelper(22000);
    
    @Override
    public boolean needToMove()
    {
        this.associatedAdvancedRTSGameLayer.waypointLogHelperP.needToMove(this.associatedAdvancedRTSGameLayer, this);
        
        if(this.isTrackingWaypoint() ||
                this.sensorAction == SensorActionFactory.getInstance().EVADE ||
                (this.currentTargetLayerInterfaceP != null &&
                this.getCurrentTargetDistance() >= this.longWeaponRange +
                this.currentTargetLayerInterfaceP.getHalfHeight()))
        {
            repeatedToLong.setStartTime();
            return true;
        }
        
        if(repeatedToLong.isTime())
        {
            final String message = "Repeating too long: " + this.getMovementLogicAsString();
            ForcedLogUtil.log(message, this.associatedAdvancedRTSGameLayer);
        }
        
        return false;
    }

    @Override
    public String getMovementLogicAsString() {

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("isTrackingWaypoint: ");
        stringBuffer.append(this.isTrackingWaypoint());
        stringBuffer.append(" sensorAction: ");
        stringBuffer.append(this.sensorAction.name);
        stringBuffer.append(" getCurrentTargetLayerInterface: ");
        stringBuffer.append(StringUtil.getInstance().toString(this.currentTargetLayerInterfaceP));

        if (this.currentTargetLayerInterfaceP != null) {
            stringBuffer.append(" Target Range: ");
            stringBuffer.append(this.getCurrentTargetDistance());
            stringBuffer.append(" >= ");
            stringBuffer.append(this.longWeaponRange + this.currentTargetLayerInterfaceP.getHalfHeight());
        }
        return stringBuffer.toString();

    }
    
    public boolean isMovingFromStopped()
    {
        return this.movingFromStopped;
    }
    
    /**
     * @param movingFromStopped the movingFromStopped to set
     */
    protected void setMovingFromStopped(boolean movingFromStopped)
    {
        this.movingFromStopped = movingFromStopped;
    }

    @Override
    public void setWaypointPathsList(BasicArrayList waypointPathsList)
    {
        this.waypointPathsListP = waypointPathsList;
    }

    @Override
    public BasicArrayList getWaypointPathsList()
    {
        return waypointPathsListP;
    }

    /**
     * @return the moving
     */
    protected boolean isMoving()
    {
        return moving;
    }

    /**
     * @param moving the moving to set
     */
    protected void setMoving(boolean moving)
    {
        this.moving = moving;
    }
    
    /**
     * @return the trackingWaypoint
     */
    @Override
    public boolean isTrackingWaypoint()
    {
        return trackingWaypoint;
    }
    
    protected BasicArrayList getPossibleTargetList()
    {
        return possibleTargetList;
    }

    protected void setLastPathGeographicMapCellPosition(GeographicMapCellPosition lastPathGeographicMapCellPosition)
    {
        this.lastPathGeographicMapCellPosition = lastPathGeographicMapCellPosition;
    }

    protected GeographicMapCellPosition getLastPathGeographicMapCellPosition()
    {
        return lastPathGeographicMapCellPosition;
    }

    
    protected void setCurrentPathGeographicMapCellPosition(
            GeographicMapCellPosition currentPathGeographicMapCellPosition)
    {
        this.currentPathGeographicMapCellPosition = currentPathGeographicMapCellPosition;
    }

    @Override
    public GeographicMapCellPosition getCurrentPathGeographicMapCellPosition()
    {
        return currentPathGeographicMapCellPosition;
    }
    
    protected void setSensorAction(SensorAction sensorAction)
    {
        this.sensorAction = sensorAction;
    }

    protected SensorAction getSensorAction()
    {
        return sensorAction;
    }
    
    /**
     * @return the waypointList
     */
//    protected BasicArrayList getTargetList()
//    {
//        return targetList;
//    }

    protected void setCurrentTargetLayerInterface(CollidableDestroyableDamageableLayer currentTargetLayerInterface)
    {
        this.currentTargetLayerInterfaceP = currentTargetLayerInterface;
    }

    @Override
    public CollidableDestroyableDamageableLayer getCurrentTargetLayerInterface()
    {
        return currentTargetLayerInterfaceP;
    }

    protected void setCurrentTargetDistance(int currentTargetDistance)
    {
        this.currentTargetDistance = currentTargetDistance;
    }

    protected int getCurrentTargetDistance()
    {
        return currentTargetDistance;
    }

    protected TimeDelayHelper getCompleteTimeDelayHelper()
    {
        return completeTimeDelayHelper;
    }

    @Override
    public GeographicMapCellHistory getCurrentGeographicMapCellHistory()
    {
        return currentGeographicMapCellHistoryP;
    }

    protected void setTrackingWaypoint(boolean trackingWaypoint)
    {
        this.trackingWaypoint = trackingWaypoint;
    }
}
