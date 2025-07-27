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
import org.allbinary.logic.string.StringMaker;


import java.util.Hashtable;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.PathAnimation;
import org.allbinary.game.layer.SteeringVisitor;
import org.allbinary.game.layer.RTSLayer2SelectedLogHelper;
import org.allbinary.game.layer.RTSLayerSelectedLogHelper;
import org.allbinary.game.layer.RTSLayerLogHelper;
import org.allbinary.game.input.form.VisibleCellPositionsSingleton;
import org.allbinary.game.input.form.WaypointRTSFormInput;
import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.CaptionResources;
import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.game.layer.RTSLayerHudPaintable;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.layer.SelectionHudPaintable;  
import org.allbinary.game.layer.SensorActionFactory;
import org.allbinary.game.layer.building.BuildingLayer;
import org.allbinary.game.layer.building.event.BuildingEventHandler;
import org.allbinary.game.layer.building.event.BuildingEventListenerInterface;
import org.allbinary.game.layer.waypoint.event.WaypointEventHandlerFactory;
import org.allbinary.media.audio.AttackSound;
import org.allbinary.util.BasicArrayList;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.NullIndexedAnimationFactory;
import org.allbinary.animation.NullRotationAnimationFactory;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.RotationAnimation;
import org.allbinary.animation.caption.CaptionAnimationHelper;
import org.allbinary.animation.caption.CaptionAnimationHelperBase;
import org.allbinary.animation.vector.AdjustedCircleAnimation;
import org.allbinary.direction.Direction;
import org.allbinary.game.combat.damage.DamageFloaters;
import org.allbinary.game.combat.damage.PtsDamageFloaters;
import org.allbinary.game.combat.weapon.WeaponProperties;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.game.health.Health;
import org.allbinary.game.health.HealthBar;
import org.allbinary.game.health.HealthBarTwodAnimation;
import org.allbinary.game.identification.Group;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.event.GameKeyEventUtil;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.layer.LinePathRelativeAnimation;
import org.allbinary.game.layer.PathFindingLayerInterface;
import org.allbinary.game.layer.RTSLayer2LogHelper;
import org.allbinary.game.layer.TiledLayerUtil;
import org.allbinary.game.layer.VehicleFrictionProperties;
import org.allbinary.game.layer.VehicleProperties;
import org.allbinary.game.layer.capital.event.CapitalEvent;
import org.allbinary.game.layer.capital.event.CapitalEventHandlerFactory;
import org.allbinary.game.layer.geographic.map.LayerPartialCellPositionsUtil;
import org.allbinary.game.layer.special.SpecialDownGameInputProcessor;
import org.allbinary.game.layer.special.SpecialFireGameInputProcessor;
import org.allbinary.game.layer.special.SpecialLeftGameInputProcessor;
import org.allbinary.game.layer.special.SpecialRightGameInputProcessor;
import org.allbinary.game.layer.special.SpecialUpGameInputProcessor;
import org.allbinary.game.layer.waypoint.MultipassNoCacheWaypoint;
import org.allbinary.game.layer.waypoint.NoCacheWaypoint;
import org.allbinary.game.layer.waypoint.WaypointBase;
import org.allbinary.game.part.weapon.BasicWeaponPart;
import org.allbinary.game.part.weapon.SalvoInterface;
import org.allbinary.game.physics.velocity.VelocityProperties;
import org.allbinary.game.tracking.TrackingEvent;
import org.allbinary.game.tracking.TrackingEventHandler;
import org.allbinary.game.tracking.TrackingEventListenerInterface;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.Layer;
import org.allbinary.layer.LayerInterfaceFactoryInterface;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.math.FrameUtil;
import org.allbinary.math.LayerDistanceUtil;
import org.allbinary.media.audio.SecondaryPlayerQueueFactory;
import org.allbinary.media.audio.Sound;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionHistory;
import org.allbinary.view.ViewPosition;
import org.allbinary.weapon.media.audio.ExplosionBasicSound;
import org.allbinary.game.multiplayer.layer.RemoteInfo;
import org.allbinary.game.view.TileLayerPositionIntoViewPosition;
import org.allbinary.math.NamedAngle;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMap;
import org.allbinary.string.CommonPhoneStrings;

/**
 * 
 * @author user
 */
public class UnitLayer extends AdvancedRTSGameLayer implements
    BuildingEventListenerInterface, TrackingEventListenerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //private final short ANGLE_INCREMENT = 10;

    private final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
    
    private final LayerPartialCellPositionsUtil layerPartialCellPositionsUtil = LayerPartialCellPositionsUtil.getInstance();
    private final LayerDistanceUtil layerDistanceUtil = LayerDistanceUtil.getInstance();
    private final AngleFactory angleFactory = AngleFactory.getInstance();
    
    //Does real path intersect with a building or unit
    //If so then change direction to go around
    //So set a dx dy that is still in

    private final short maxResourceLoad;
    
    private final VehicleProperties vehicleProperties;
    //private int repair;
    protected BasicDecimal accelerationBasicDecimal = new BasicDecimal(1600);
    protected BasicDecimal decelerationBasicDecimal = new BasicDecimal(-1000);
    
    private final TrackingEvent trackingEvent;
    private final DamageFloaters damageFloaters;
    private final Paintable damageFloatersPaintableInterface;
    private final Paintable healthBar;

    private final Sound moveSoundInterface;

    private final RotationAnimation initResourceAnimation;
    private IndexedAnimation resourceAnimation = 
        (IndexedAnimation) NullIndexedAnimationFactory.getFactoryInstance().getInstance(0);

    private final PathAnimation initPathAnimation;
    private Animation pathAnimation = NullAnimationFactory.getFactoryInstance().getInstance(0);

    private final CaptionAnimationHelperBase captionAnimationHelper = 
        new CaptionAnimationHelper(
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(
                        CaptionResources.getInstance().RESOURCE).getInstance(0),
                        -23, -25, 6, 0);

    private final RotationAnimation decalAnimation;

    private final LayerInterfaceFactoryInterface waypointLayerInterfaceFactoryInterface;
    
    private static final BasicArrayList partialPositionList = new BasicArrayList(4);

    private short resourceLoad = 0;
    
    private int weaponRange;
    
    protected RTSLayerLogHelper rtsLogHelper = RTSLayerLogHelper.getInstance();
    
    protected RotationAnimation rotationAnimationInterface;

    private NamedAngle movementAngle = this.angleFactory.NOT_ANGLE;
    private GeographicMapCellPosition steeringInsideGeographicMapCellPosition;

    protected UnitLayer(
            final RemoteInfo remoteInfo,
            final AdvancedRTSGameLayer parentLayer,
            final Group[] groupInterface,
            final String rootName,
            final String name,
            final VehicleProperties vehicleProperties,
            final Health healthInterface,
            final Integer maxLoad,
            final Sound moveSoundInterface,
            final LayerInterfaceFactoryInterface waypointLayerInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface emptyAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface baseAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface buildAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface verticleBuildAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface decalAnimationInterfaceFactoryInterface,
            final ProceduralAnimationInterfaceFactoryInterface proceduralAnimationInterfaceFactoryInterface,            
            final Rectangle rectangle, 
            final Direction direction, 
            final int x, final int y, final int z) throws Exception
    {
        this(remoteInfo, parentLayer, groupInterface, rootName, name, vehicleProperties, 
                healthInterface, maxLoad, moveSoundInterface, waypointLayerInterfaceFactoryInterface, 
                animationInterfaceFactoryInterface, emptyAnimationInterfaceFactoryInterface, 
                baseAnimationInterfaceFactoryInterface, buildAnimationInterfaceFactoryInterface, 
                verticleBuildAnimationInterfaceFactoryInterface, 
                decalAnimationInterfaceFactoryInterface,
                proceduralAnimationInterfaceFactoryInterface, 
                rectangle, direction, x, y, z, new TileLayerPositionIntoViewPosition());
    }
    
    protected UnitLayer(
            final RemoteInfo remoteInfo,
            final AdvancedRTSGameLayer parentLayer,
            final Group[] groupInterface,
            final String rootName,
            final String name,
            final VehicleProperties vehicleProperties,
            final Health healthInterface,
            final Integer maxLoad,
            final Sound moveSoundInterface,
            final LayerInterfaceFactoryInterface waypointLayerInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface emptyAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface baseAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface buildAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface verticleBuildAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface decalAnimationInterfaceFactoryInterface,
            final ProceduralAnimationInterfaceFactoryInterface proceduralAnimationInterfaceFactoryInterface,
            final Rectangle rectangle, 
            final Direction direction, 
            final int x, final int y, final int z,
            final ViewPosition viewPosition) throws Exception
    {
        this(remoteInfo, parentLayer, groupInterface, rootName, name, vehicleProperties, 
                healthInterface, maxLoad, moveSoundInterface, waypointLayerInterfaceFactoryInterface, 
                animationInterfaceFactoryInterface, emptyAnimationInterfaceFactoryInterface, 
                baseAnimationInterfaceFactoryInterface, buildAnimationInterfaceFactoryInterface, 
                verticleBuildAnimationInterfaceFactoryInterface, 
                decalAnimationInterfaceFactoryInterface,
                NullRotationAnimationFactory.getFactoryInstance(),
                proceduralAnimationInterfaceFactoryInterface, 
                rectangle, direction, x, y, z, viewPosition);
    }
    
    protected UnitLayer(
            final RemoteInfo remoteInfo,
            final AdvancedRTSGameLayer parentLayer,
            final Group[] groupInterface,
            final String rootName,
            final String name,
            final VehicleProperties vehicleProperties,
            final Health healthInterface,
            final Integer maxLoad,
            final Sound moveSoundInterface,
            final LayerInterfaceFactoryInterface waypointLayerInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface emptyAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface baseAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface buildAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface verticleBuildAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface decalAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface resourceAnimationInterfaceFactoryInterface,
            final ProceduralAnimationInterfaceFactoryInterface proceduralAnimationInterfaceFactoryInterface,            
            final Rectangle rectangle, 
            final Direction direction, 
            final int x, final int y, final int z) throws Exception
    {
        this(remoteInfo, parentLayer, groupInterface, rootName, name, vehicleProperties, 
                healthInterface, maxLoad, moveSoundInterface, waypointLayerInterfaceFactoryInterface, 
                animationInterfaceFactoryInterface, emptyAnimationInterfaceFactoryInterface, 
                baseAnimationInterfaceFactoryInterface, buildAnimationInterfaceFactoryInterface, 
                verticleBuildAnimationInterfaceFactoryInterface, 
                decalAnimationInterfaceFactoryInterface,
                resourceAnimationInterfaceFactoryInterface,
                proceduralAnimationInterfaceFactoryInterface, 
                rectangle, direction, x, y, z, new TileLayerPositionIntoViewPosition());
    }
    
    protected UnitLayer(
            final RemoteInfo remoteInfo,
            final AdvancedRTSGameLayer parentLayer,
            final Group[] groupInterface,
            final String rootName,
            final String name,
            final VehicleProperties vehicleProperties,
            final Health healthInterface,
            final Integer maxResourceLoad,
            final Sound moveSoundInterface,
            final LayerInterfaceFactoryInterface waypointLayerInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface emptyAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface baseAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface buildAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface verticleBuildAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface decalAnimationInterfaceFactoryInterface,
            final AnimationInterfaceFactoryInterface resourceAnimationInterfaceFactoryInterface,
            final ProceduralAnimationInterfaceFactoryInterface proceduralAnimationInterfaceFactoryInterface,
            final Rectangle rectangle, 
            final Direction direction, 
            final int x, final int y, final int z,
            final ViewPosition viewPosition) throws Exception
        {

        super(remoteInfo,
                parentLayer, 
                groupInterface, 
                rootName, name, 
                healthInterface,
                new WaypointRTSFormInput(groupInterface, true),
            //NullRTSFormInputFactory.getInstance(),
            animationInterfaceFactoryInterface,
            emptyAnimationInterfaceFactoryInterface, baseAnimationInterfaceFactoryInterface,
            buildAnimationInterfaceFactoryInterface,
            verticleBuildAnimationInterfaceFactoryInterface,
            proceduralAnimationInterfaceFactoryInterface, rectangle, 
            x, y, viewPosition);

        this.setCollidableInferface(new CollidableUnitBehavior(this, true));        

        this.waypointLayerInterfaceFactoryInterface = waypointLayerInterfaceFactoryInterface;
        
        this.maxResourceLoad = maxResourceLoad.shortValue(); 
        this.moveSoundInterface = moveSoundInterface;

        if (Features.getInstance().isFeature(
                GameFeatureFactory.getInstance().DAMAGE_FLOATERS))
        {
            this.damageFloatersPaintableInterface = this.damageFloaters =
                new PtsDamageFloaters(this);
        }
        else
        {
            this.damageFloatersPaintableInterface = NullPaintable.getInstance();
            this.damageFloaters = new DamageFloaters();
        }

        if (Features.getInstance().isFeature(
                GameFeatureFactory.getInstance().HEALTH_BARS))
        {
            this.healthBar = new HealthBar(this, this.getHealthInterface(),
                new HealthBarTwodAnimation((AllBinaryLayer) this, BasicHudFactory.getInstance().BOTTOMLEFT), 
                    -1);
        }
        else
        {
            this.healthBar = NullPaintable.getInstance();
        }

        //logUtil.put("Direction: " + direction, this, commonStrings.CONSTRUCTOR);

        this.decalAnimation = (RotationAnimation)
            decalAnimationInterfaceFactoryInterface.getInstance(0);

        this.initResourceAnimation = (RotationAnimation)
            resourceAnimationInterfaceFactoryInterface.getInstance(0);

        this.initResourceAnimation.setFrame(direction);
        this.decalAnimation.setFrame(direction);
        this.rotationAnimationInterface = (RotationAnimation)
                this.indexedButShouldBeRotationAnimationInterface;

        this.rotationAnimationInterface.setFrame(direction);

        this.setMaxLevel(12);

        this.vehicleProperties = vehicleProperties;

        //this.repair = UnitTechnologySingletonFactory.getInstance().getRepair();

        this.trackingEvent = new TrackingEvent(this);

        this.initPathAnimation = new PathAnimation(this, LinePathRelativeAnimation.getInstance());
    }

    /*
    protected UnitLayer()
        throws Exception
    {
        super();

this.setCollidableInferface(new CollidableUnitBehavior(this, true));

        this.moveSoundInterface = null;
        
        this.vehicleProperties = null;

        this.trackingEvent = null;

        this.damageFloaters = null;
        this.damageFloatersPaintableInterface = null;
        this.healthBar = null;
    }
    */

    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);
        
        this.initPathAnimation.setAllBinaryGameLayerManager(allBinaryGameLayerManager);
    }
    
    public void updateWaypointBehavior(final BasicGeographicMap geographicMapInterface) throws Exception {
        
        final Hashtable hashtable = new Hashtable();
        hashtable.put(Group.ID, this.getGroupInterface());
        hashtable.put(Layer.ID, this);
        hashtable.put(AllBinaryGameLayerManager.ID, allBinaryGameLayerManager);
        
        this.setWaypointBehavior(
                new UnitWaypointBehavior2(
                        this, 
                        (AdvancedRTSGameLayer) 
                        waypointLayerInterfaceFactoryInterface.getInstance(
                                hashtable, x, y, z))
                );

        final Features features = Features.getInstance();
        final boolean isHTML = features.isDefault(HTMLFeatureFactory.getInstance().HTML);
        
        final WaypointBase waypoint = 
            isHTML ? new MultipassNoCacheWaypoint(this, AttackSound.getInstance()) : 
            new NoCacheWaypoint(this, AttackSound.getInstance());
        this.getWaypointBehavior().setWaypoint(waypoint);
        
        super.updateWaypointBehavior(geographicMapInterface);
        
        this.initRangeHack();
    }
    
    public void construct(RTSPlayerLayerInterface rtsPlayerLayerInterface)
    throws Exception
    {
        super.construct(rtsPlayerLayerInterface);        

        TrackingEventHandler.getInstance().addListener(this);

        WaypointEventHandlerFactory.getInstance(
            this.getGroupInterface()[0]).addListener(this.getUnitWaypointBehavior());

        BuildingEventHandler.getInstance().addListener(this);

        this.getUnitWaypointBehavior().setCurrentPathGeographicMapCellPosition(
                this.getCurrentGeographicMapCellPosition());
        this.updateSensorGeographicMapCellPositionList();
        this.getUnitWaypointBehavior().setLastPathGeographicMapCellPosition(
                this.getUnitWaypointBehavior().getCurrentPathGeographicMapCellPosition());
    }

    private final BasicArrayList sensorGeographicMapCellPositionList = 
        new BasicArrayList();

    public void updateSensorGeographicMapCellPositionList()
    throws Exception
    {
        if(VisibleCellPositionsSingleton.getInstance().shouldProcess())
        {
            //UnitWaypointBehavior unitWaypointBehavior = 
            //  (UnitWaypointBehavior) this.getWaypointBehavior();
            
            final GeographicMapCellPosition currentGeographicMapCellPosition =
                this.getCurrentGeographicMapCellPosition();
            //unitWaypointBehavior.getCurrentPathGeographicMapCellPosition()
            
            this.sensorGeographicMapCellPositionList.clear();
            
            this.sensorGeographicMapCellPositionList.add(
                    currentGeographicMapCellPosition);

            final int sensorRange = weaponRange * SENSOR_RANGE_MULTIPLIER;

            final GeographicMapCompositeInterface geographicMapCompositeInterface
                = (GeographicMapCompositeInterface) this.allBinaryGameLayerManager;
            final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
            
            final AllBinaryTiledLayer tiledLayer = 
                geographicMapInterface.getAllBinaryTiledLayer();

            final int totalCells = (sensorRange/tiledLayer.getCellHeight()) / 2;

            
            final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory = 
                geographicMapInterface.getGeographicMapCellPositionFactory();
            
            final int column = currentGeographicMapCellPosition.getColumn();
            final int row = currentGeographicMapCellPosition.getRow();

            int lastColumn = column + totalCells;
            int lastRow = row + totalCells;

            if(lastColumn > tiledLayer.getColumns())
            {
                lastColumn = tiledLayer.getColumns();
            }

            if(lastRow > tiledLayer.getRows())
            {
                lastRow = tiledLayer.getRows();
            }

            int firstColumn = column - totalCells;
            int firstRow = row - totalCells;

            if(firstColumn < 0)
            {
                firstColumn = 0;
            }

            if(firstRow < 0)
            {
                firstRow = 0;
            }

            /*
            final StringMaker stringBuffer = new StringMaker();
            
            stringBuffer.append("totalCells: ");
            stringBuffer.append(totalCells);
            stringBuffer.append(" lastColumn: ");
            stringBuffer.append(lastColumn);
            stringBuffer.append(" lastRow: ");
            stringBuffer.append(lastRow);
            stringBuffer.append(" firstColumn: ");
            stringBuffer.append(firstColumn);
            stringBuffer.append(" firstRow: ");
            stringBuffer.append(firstRow);
            */

            //logUtil.put(stringBuffer.toString(), this, "updateSensorGeographicMapCellPositionList");
            //PreLogUtil.put(stringBuffer.toString(), this, "updateSensorGeographicMapCellPositionList");
            
            for(int index = lastColumn - 1; index >= firstColumn; index--)
            {
                for(int index2 = lastRow - 1; index2 >= firstRow; index2--)
                {
                    final GeographicMapCellPosition geographicMapCellPosition = 
                        geographicMapCellPositionFactory.getInstance(index, index2);

                    if(!this.sensorGeographicMapCellPositionList.contains(geographicMapCellPosition))
                    {
                        this.sensorGeographicMapCellPositionList.add(geographicMapCellPosition);
                    }
                }
            }
        }
    }
    
    public BasicArrayList getSensorGeographicMapCellPositionList()
    {
        return this.sensorGeographicMapCellPositionList;
    }

    public void select()
    {
        this.pathAnimation = this.initPathAnimation;
        super.select();
    }

    public void deselect()
    {
        this.pathAnimation = NullAnimationFactory.getFactoryInstance().getInstance(0);
        super.deselect();
    }
    
    @Override
    protected void setSelected(boolean selected)
    {
        super.setSelected(selected);
        
        if (selected) {
            if (this.debug) {
                this.rtsLogHelper = RTSLayerSelectedLogHelper.getInstance();
            }
            this.rtsLayer2LogHelper = RTSLayer2SelectedLogHelper.getInstance();
        } else {
            if (this.debug) {
                this.rtsLogHelper = RTSLayerLogHelper.getInstance();
            }
            this.rtsLayer2LogHelper = RTSLayer2LogHelper.getInstance();
        }
    }
    
    public void setClosestGeographicMapCellHistory(final BasicArrayList pathsList)
        throws Exception
    {
        
        this.rtsLogHelper.setClosestGeographicMapCellHistory(this, pathsList);
        
        int closestIndex = -1;
        int shortestDistance = Integer.MAX_VALUE;
        int currentDistance = Integer.MAX_VALUE;

        for (int index = pathsList.size() - 1; index >= 0; index--)
        {
            final BasicArrayList geographicMapCellPositionBasicArrayList =
                (BasicArrayList) pathsList.get(index);

            final GeographicMapCellPosition geographicMapCellPosition =
                (GeographicMapCellPosition)
                geographicMapCellPositionBasicArrayList.get(
                geographicMapCellPositionBasicArrayList.size() - 1);

            currentDistance = layerDistanceUtil.getDistance(this, geographicMapCellPosition.getMidPoint());

            if (currentDistance < shortestDistance)
            {
                shortestDistance = currentDistance;
                closestIndex = index;
            }
        }

        if (closestIndex >= 0)
        {
            final BasicArrayList geographicMapCellPositionBasicArrayList =
                (BasicArrayList) pathsList.get(closestIndex);

            final GeographicMapCellPosition geographicMapCellPosition =
                (GeographicMapCellPosition)
                geographicMapCellPositionBasicArrayList.get(0);

            this.teleportTo(geographicMapCellPosition);

            //this.setGeographicMapCellHistoryPath(geographicMapCellPositionBasicArrayList);
        }
    }
    
    public void init(final GeographicMapCellHistory geographicMapCellHistory,
        final BasicArrayList geographicMapCellPositionBasicArrayList) throws Exception
    {

        // logUtil.put("Adding " + size + " path nodes for Tracking", this, commonStrings.INIT);

        geographicMapCellHistory.track(geographicMapCellPositionBasicArrayList);

        //logUtil.put("geographicMapCellHistory: " + geographicMapCellHistory.getTracked().toString(), this, commonStrings.INIT);
    }

    public void onMovement(final TrackingEvent trackingEvent)
    {
        try
        {
            final AdvancedRTSGameLayer layerInterface =
                    (AdvancedRTSGameLayer) trackingEvent.getLayerInterface();

                /*
                if(this.isSelected())
                {
                    logUtil.put("Possible Target: " + layerInterface.getName(), this, "selected: onMovement");
                }
                */

                if (layerInterface.getGroupInterface()[0] != this.getGroupInterface()[0])
                {
                    this.getUnitWaypointBehavior().getPossibleTargetList().add(layerInterface);
                    //this.onEnemyMovement(layerInterface);
                    layerInterface.onMovementFound(this.getTrackingEvent());
                }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "onMovement", e);
        }
    }

    public void onMovementFound(final TrackingEvent trackingEvent)
        throws Exception
    {
        final AdvancedRTSGameLayer layerInterface =
            (AdvancedRTSGameLayer) trackingEvent.getLayerInterface();

        /*
        if(this.isSelected())
        {
            logUtil.put("Possible Target: " + layerInterface.getName(), this, "selected: onMovementFound");
        }
        */
        
        if (layerInterface.getGroupInterface()[0] != this.getGroupInterface()[0])
        {
            this.getUnitWaypointBehavior().getPossibleTargetList().add(layerInterface);
            //this.onEnemyMovement(layerInterface);
        }
    }
    
    private static final int SENSOR_RANGE_MULTIPLIER = 6;

    public void initRangeHack()
    {
        final BasicWeaponPart basicWeaponPart = (BasicWeaponPart) this.getPartInterfaceArray()[0];

        final WeaponProperties weaponProperties = basicWeaponPart.getWeaponProperties();

        this.weaponRange = weaponProperties.getRange();
        
        this.initRangeAnimation =
            //new CircleAnimation(
            new AdjustedCircleAnimation(
                    weaponRange, weaponRange, this.getWidth(), this.basicColorFactory.GREEN);

        final int sensorRange = weaponRange * SENSOR_RANGE_MULTIPLIER;
        
        this.initSensorRangeAnimation =
            new AdjustedCircleAnimation(
                    sensorRange, sensorRange, this.getWidth(), this.basicColorFactory.RED);

        this.getUnitWaypointBehavior().initRange(weaponRange);

        this.fireTimeHelper.delay = ((int) weaponProperties.getReloadTime());

        //Show ranges for all debugging ranges
        //this.select();
    }

    public void processBuiltTick(final AllBinaryLayerManager allBinaryLayerManager)
        throws Exception
    {
        if (!this.getHealthInterface().isAlive())
        {
            if (this.isReadyForExplosion())
            {
                final int currentFrame = this.destroyAnimationInterface.getFrame();
                final int size = this.destroyAnimationInterface.getSize() - 1;

                // logUtil.put("Explosion - Processing: " + currentFrame + "==" + size, this, "processTick");

                if (currentFrame == size)
                // if (!this.delayMovementTimeHelper.isTime())
                {
                    // logUtil.put("Explosion - End", this, "processTick");
                    if (!this.getHealthInterface().isAlive())
                    {
                        this.setDestroyed(true);
                        // this.setRotationAnimationInterface((RotationAnimationInterface)
                        // this.getRotationAnimationInterface());
                    }
                }
                else
                // if(this.delayMovementTimeHelper.isTime())
                {
                    // show next frame in explosion
                    this.destroyAnimationInterface.nextFrame();
                }
            }
            else
            {
                // logUtil.put("Explosion - Begin", this, "processTick");

                this.setAnimationInterface(this.destroyAnimationInterface);

                // this.getVehicleProperties().getVelocityProperties().zero();

                SecondaryPlayerQueueFactory.getInstance().add(
                    ExplosionBasicSound.getInstance());

                this.shakeListener.onSmallShakeEvent();
                vibration.vibrate(duration, 0, 0);

                // this.delayMovementTimeHelper.setStartTime();
                this.setReadyForExplosion(true);
            }
        }
        else
        {
            super.processBuiltTick(allBinaryLayerManager);
        }
        
        this.captionAnimationHelper.tick();
        this.getUnitWaypointBehavior().processTick(allBinaryLayerManager);
    }

    public void teleportTo(
        final GeographicMapCellPosition geographicMapCellPosition)
    {
        final GPoint point = geographicMapCellPosition.getMidPoint();

        this.setPosition(
            point.getX() - this.getHalfWidth(),
            point.getY() - this.getHalfHeight(),
            this.z);
    }

    public GeographicMapCellPosition getCurrentGeographicMapCellPosition()
    throws Exception
    {
        final GeographicMapCompositeInterface geographicMapCompositeInterface
            = (GeographicMapCompositeInterface) this.allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        final GeographicMapCellPosition geographicMapCellPosition =
            geographicMapInterface.getCellPositionAt(
            this.x + this.getHalfWidth(),
            this.y + this.getHalfHeight());

        //This should never happen remove when bug is found
        final RaceTrackGeographicMap raceTrackGeographicMap =
            (RaceTrackGeographicMap) geographicMapInterface;
        
        if(!raceTrackGeographicMap.isValid(geographicMapCellPosition))
        {
            throw new Exception("Position is not really on the map: " + geographicMapCellPosition);
        }

        return geographicMapCellPosition;
    }

    public void fire(final AllBinaryLayerManager layerManager, final GameKeyEvent gameKeyEvent)
    throws Exception
    {
        //logUtil.put("fireAll", this, "fire");

        if (this.fireTimeHelper.isTime())
        {
            //logUtil.put("fire", this, gameInputStrings.PROCESS_INPUT);

            this.fireAll(layerManager);
        }
        else
        {
            this.reload();
        }
    }

    public void left()
        throws Exception
    {
        this.initResourceAnimation.previousRotation();
        this.decalAnimation.previousRotation();
        this.rotationAnimationInterface.previousRotation();
    }

    public void right()
        throws Exception
    {
        /*
        if(this.isChase())
        {
            logUtil.put("Turning while chasing", this, "right");
        }
        */
        
        this.initResourceAnimation.nextRotation();
        this.decalAnimation.nextRotation();
        this.rotationAnimationInterface.nextRotation();
    }
    
    public void down()
    {
        final VelocityProperties velocityProperties =
            this.getVehicleProperties().getVelocityProperties();

        if (!velocityProperties.isOverXYMaxForwardVelocity())
        {
            this.accelerate(this.decelerationBasicDecimal);
        }
    }

    //accelerate
    public void up()
    {
        final VelocityProperties velocityProperties = this.getVehicleProperties().getVelocityProperties();

        if (!velocityProperties.isOverXYMaxForwardVelocity())
        {
            this.accelerate(this.accelerationBasicDecimal);
        }
    }
    
    public void initInputProcessors()
    {
        this.inputProcessorArray[Canvas.RIGHT] = new SpecialRightGameInputProcessor(this);
        this.inputProcessorArray[Canvas.LEFT] = new SpecialLeftGameInputProcessor(this);
        this.inputProcessorArray[Canvas.KEY_NUM0] = new SpecialFireGameInputProcessor(this);

        this.inputProcessorArray[Canvas.KEY_POUND] = this.inputProcessorArray[Canvas.KEY_NUM0];

        this.inputProcessorArray[Canvas.DOWN] = new SpecialDownGameInputProcessor(this);
        this.inputProcessorArray[Canvas.UP] = new SpecialUpGameInputProcessor(this);
        
        super.initInputProcessors();
    }
        
    public void processInput(AllBinaryLayerManager layerManager)
        throws Exception
    {
        final BasicArrayList list = this.getGameKeyEventList();
        final int size = list.size();
        for (int index = 0; index < size; index++)
        {
            Object object = list.get(index);
            int key = GameKeyEventUtil.getKey(object);

            this.inputProcessorArray[key].process(layerManager, (GameKeyEvent) null);
        }
        list.clear();

        this.groundFriction();
        
        this.move();
    }

    public void accelerate(final BasicDecimal accelerate)
    {
        this.getVehicleProperties().getVelocityProperties().addVelocity(
                accelerate.getUnscaled(), this.rotationAnimationInterface.getAngleInfo().getAngle(), (short) 90);
    }

    protected void fireAll(final AllBinaryLayerManager layerManager) throws Exception
    {
        final AngleInfo angleInfo = this.rotationAnimationInterface.getAngleInfo();
        final short angle = (short) (angleInfo.getAngle() + this.slightAngle);

        hashtable.put(SmallIntegerSingletonFactory.getInstance().getInstance(1), 
                SmallIntegerSingletonFactory.getInstance().getInstance(AngleFactory.getInstance().getInstance(angle).getValue()));

        ((SalvoInterface) this.getPartInterfaceArray()[0]).process(layerManager, angle, (short) 90);

        /*
         * PartInterface[] partInterfaceArray = this.getPartInterfaceArray();
         * 
         * for (int index = partInterfaceArray.length - 1; index >= 0; index--)
         * { ((SalvoInterface) partInterfaceArray[index]).process( layerManager,
         * angle); }
         */
    }

    public void downgrade()
    {
        if (getLevel() > 1)
        {
            super.downgrade();
            //this.select();
        }
    }

    public void upgrade()
    {
        super.upgrade();
        this.initRangeHack();
    }

    protected void groundFriction()
    {
        final VehicleFrictionProperties vehicleFrictionProperties = this.getVehicleProperties().getVehicleFrictionProperties();

        this.getVehicleProperties().getVehicleFrictionProperties().friction(
            this.getVehicleProperties().getVelocityProperties(),
            vehicleFrictionProperties.getTireFrictionNominator());
    }

    @Override
    public void trackTo(final String reason)
        throws Exception
    {
        final GeographicMapCellPosition nextUnvisitedPathGeographicMapCellPosition = this.waypointBehaviorBase.getNextUnvisitedPathGeographicMapCellPosition();
        final GPoint point = nextUnvisitedPathGeographicMapCellPosition.getMidPoint();

        final int dx = (this.getXP() + this.getHalfWidth()) - point.getX();
        final int dy = (this.getYP() + this.getHalfHeight()) - point.getY();

        this.rtsLogHelper.trackTo(this, nextUnvisitedPathGeographicMapCellPosition, dx, dy, reason);
        
        this.trackTo(dx, dy);
    }
    
    //private final NoDecimalTrigTable noDecimalTrigTable = NoDecimalTrigTable.getInstance();
    
    @Override
    public void trackTo(final int dx, final int dy)
    throws Exception
    {
        //Change from real target to fake location to deal with
        //other game objects (building and units)
        
        /*
        Direction direction = 
        GeographicMapDirectionUtil.getDirectionFromCellPositionToAdjacentCellPosition(
        this.lastPathGeographicMapCellPosition,
        this.currentPathGeographicMapCellPosition);

        //turning left or right
        if (direction == Direction.LEFT || direction == Direction.RIGHT)
        {
        int angle = this.getRotationAnimationInterface().getAngleInfo().getAngle();
        }
        else
        //turning up or down
        if (direction == Direction.UP || direction == Direction.DOWN)
        {
        }
         */

        /*
        layerPartialCellPositionsUtil.getAll(
            this.geographicMapInterface,
            this, partialPositionList);

        int targetAngle = 270 - NoDecimalTrigTable.antiTan(dx, dy);

        if (partialPositionList.contains(
            this.getUnitWaypointBehavior().getLastPathGeographicMapCellPosition()))
        {
            if (this.getUnitWaypointBehavior().isWaypointListEmptyOrOnlyTargets())
            {
                int angleOfTarget = FrameUtil.adjustAngleToFrameAngle(targetAngle);
                int angle = this.getRotationAnimationInterface().getAngleInfo().getAngle();

                PreLogUtil.put("angleOfTarget: " + angleOfTarget + " angle: " + angle, this, "turnTo");
            }
            
            this.turnTo(targetAngle);
            this.fireOrMove();
        }
        else
        {
            this.trackTo(targetAngle);
        }
        */
        
        //final int angleOfTarget = 270 - noDecimalTrigTable.antiTan(dx, dy);
        //final int angleOfTarget = -90 - noDecimalTrigTable.antiTan(dx, dy);
        final int angleOfTarget = 0;
        this.trackTo(dx, dy, angleOfTarget);
    }

    private boolean turnTo(final int dx, final int dy, int targetAngle)
    throws Exception
    {
        // int angleOfTarget = NoDecimalTrigTable.antiTan(dx, dy);

        final GeographicMapCellPosition nextUnvisitedPathGeographicMapCellPosition = this.waypointBehaviorBase.getNextUnvisitedPathGeographicMapCellPosition();
        
        boolean evading = false;
        
        // Run until out of sensor range
        if (this.getUnitWaypointBehavior().getSensorAction() == SensorActionFactory.getInstance().EVADE)
        {
            this.rtsLogHelper.evade(this);
            
            evading = true;
            targetAngle += 180;
        }

        //final int angleOfTarget = FrameUtil.getInstance().adjustAngleToFrameAngle(targetAngle);
        //final int angleOfTarget2 = angleOfTarget;
        //final int angleOfTarget2 = angleOfTarget / 10 * 10;
        //final int angleOfTarget2 = AngleFactory.getInstance().getClosestDirection(angleOfTarget).getValue();
        final AngleInfo angleInfo = this.rotationAnimationInterface.getAngleInfo();
        final int angle = FrameUtil.getInstance().adjustAngleToFrameAngle(angleInfo.getAngle() - 270);
        //final int angle = angleInfo.getAngle();

        //if (this.getUnitWaypointBehavior().isWaypointListEmptyOrOnlyTargets())
        
        this.rtsLogHelper.turnTo(this, dx, dy, angleInfo, angle, movementAngle, evading, targetAngle);

        final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
        
        //int deltaAngle = closestDirectionAngle.getValue() - angle;
//        int deltaAngle = angleOfTarget2 - angle;
//        int absoluteDeltaAngle = Math.abs(deltaAngle);
          //absoluteDeltaAngle == 0 || 
          //() || 
        if(dx == 0 && dy == 0) {
            
            this.rtsLogHelper.doneMoving(this);
            
            return true;
        } else if(this.movementAngle.getValue() == angle) {

            if(dx > 0 && this.movementAngle == this.angleFactory.LEFT) {
                this.rtsLogHelper.movingLeft(this);
                return false;
            }
            if(dx < 0 && this.movementAngle == this.angleFactory.RIGHT) {
                this.rtsLogHelper.movingRight(this);
                return false;
            }
            if(dy > 0 && this.movementAngle == this.angleFactory.UP) {
                this.rtsLogHelper.movingUp(this);
                return false;
            }
            if(dy < 0 && this.movementAngle == this.angleFactory.DOWN) {
                this.rtsLogHelper.movingDown(this);
                return false;
            }

            this.rtsLogHelper.currentMoveEnded(this);
            
            if(this.movementAngle == this.angleFactory.LEFT || 
                this.movementAngle == this.angleFactory.RIGHT) {
                this.handleDeltalY(dx, dy);
            } else if(this.movementAngle == this.angleFactory.UP || 
                this.movementAngle == this.angleFactory.DOWN) {
                this.handleDeltalX(dx, dy);
            }
            
            return true;
        //} else if(absoluteDeltaAngle < ANGLE_INCREMENT) {
            
        /*
        if (this.slightAngle > ANGLE_INCREMENT)
        {
            if(this.isSelected()) {
                logUtil.put(new StringBuilder().append("steering - slightAngle: ").append(this.slightAngle).toString(), this, "trackTo:trackTo");
            }

            if (angleOfTarget - angle < 0)
            {
                this.getGameKeyEventList().add(
                    gameKeyEventFactory.getInstance(this, Canvas.RIGHT));
            }
            else
            {
                this.getGameKeyEventList().add(
                    gameKeyEventFactory.getInstance(this, Canvas.LEFT));
            }
            return true;
        }
        else if (this.slightAngle < -ANGLE_INCREMENT)
        {
            if(this.isSelected()) {
                logUtil.put(new StringBuilder().append("steering - slightAngle: ").append(this.slightAngle).toString(), this, "trackTo:trackTo");
            }

            if (angle - angleOfTarget < 0)
            {
                this.getGameKeyEventList().add(
                    gameKeyEventFactory.getInstance(this, Canvas.LEFT));
                // this.turretAnimationInterface.previousFrame();
            }
            else
            {
                this.getGameKeyEventList().add(
                    gameKeyEventFactory.getInstance(this, Canvas.RIGHT));
                // this.turretAnimationInterface.nextFrame();
            }
            return true;
        }
        else
        {
            return false;
        }
        */

//            AngleInfo angleInfo2 = this.rotationAnimationInterface.getAngleInfo();
//            int angle2 = angleInfo2.getAngle();
//            int delta = angleOfTarget2 - angle2;
//            
//            logUtil.put("steering - angleOfTarget: " + angleOfTarget2, this, TRACKTO_TURNTO);
//            
//            while(delta != 0) {
//                if(delta > 0) this.rotationAnimationInterface.nextRotation();
//                if(delta < 0) this.rotationAnimationInterface.previousRotation();
//                
//                angleInfo2 = this.rotationAnimationInterface.getAngleInfo();
//                angle2 = angleInfo2.getAngle();
//                delta = angleOfTarget2 - angle2;
//                logUtil.put("steering - angle2: " + angle2, this, TRACKTO_TURNTO);
//            }

            //return false;
        } else {
            //this.slightAngle = angleOfTarget - angle;
     
            if(nextUnvisitedPathGeographicMapCellPosition != null) {
                
                if(this.steeringInsideGeographicMapCellPosition != nextUnvisitedPathGeographicMapCellPosition) {
                    
                    if (Math.abs(dx) > Math.abs(dy) && dy != 0) {
                        this.handleDeltalY(dx, dy);
                    } else if (dx != 0) {
                        this.handleDeltalX(dx, dy);
                    } else {
                        this.handleDeltalY(dx, dy);
                        //throw new RuntimeException();
                    }
                    
                }

                int deltaAngle2 = this.movementAngle.getValue() - angle;
                if (deltaAngle2 > 0) {
                    this.rtsLogHelper.rotateRight(this);
                    this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.RIGHT));
                } else {
                    this.rtsLogHelper.rotateLeft(this);
                    this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.LEFT));
                }
                
                return true;
                
            } else {
                this.rtsLogHelper.noRotation(this);
            }
            
            return false;
        }

    }

    private void handleDeltalX(final int dx, final int dy) {
        final GeographicMapCellPosition nextUnvisitedPathGeographicMapCellPosition = this.waypointBehaviorBase.getNextUnvisitedPathGeographicMapCellPosition();
        if (dx > 0) {
            this.movementAngle = this.angleFactory.LEFT;
            this.steeringInsideGeographicMapCellPosition = nextUnvisitedPathGeographicMapCellPosition;
            
        } else {
            this.movementAngle = this.angleFactory.RIGHT;
            this.steeringInsideGeographicMapCellPosition = nextUnvisitedPathGeographicMapCellPosition;
            
        }
        
        this.rtsLogHelper.handle(this, this.movementAngle);
    }
    
    private void handleDeltalY(final int dx, final int dy) {
        final GeographicMapCellPosition nextUnvisitedPathGeographicMapCellPosition = this.waypointBehaviorBase.getNextUnvisitedPathGeographicMapCellPosition();
        if (dy > 0) {
            this.movementAngle = this.angleFactory.UP;
            this.steeringInsideGeographicMapCellPosition = nextUnvisitedPathGeographicMapCellPosition;
            
        } else {
            this.movementAngle = this.angleFactory.DOWN;
            this.steeringInsideGeographicMapCellPosition = nextUnvisitedPathGeographicMapCellPosition;
            
        }
        
        this.rtsLogHelper.handle(this, this.movementAngle);
    }

    private void trackTo(final int dx, final int dy, final int targetAngle)
        throws Exception
    {
        //If colliding with a game object then don't try to turn since in chase mode
        final BasicArrayList list = this.getUnitWaypointBehavior().getSteeringVisitorList();

        if(list.size() > 0)
        {
            //logUtil.put("Chasing", this, "trackTo");

            for(int index = list.size() - 1; index >= 0; index--)
            {
                final SteeringVisitor steeringVisitor = (SteeringVisitor) list.get(index);
                
                final Object object = steeringVisitor.visit(null);
                
                if(object == null)
                {
                    list.remove(index);
                }
            }

            this.fireOrMove();
        }
        else if(!this.turnTo(dx, dy, targetAngle))
        {
            this.fireOrMove();
        }
    }

    private static final String MOVE = "Moving";
    
    protected void fireOrMove()
        throws Exception
    {
        //logUtil.put("Move/Attack: " +  " trackingWaypoint: " + this.trackingWaypoint + " sensorAction: " + this.sensorAction + " currentTargetDistance >= longWeaponRange " + this.currentTargetDistance + ">=" + this.longWeaponRange , this, "trackTo");

        final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
        
        // Move if going to waypoint, evading, or towards target
        if (this.getUnitWaypointBehavior().needToMove())
        {
            this.rtsLayer2LogHelper.steeringUp(this);

            if(this.showMoreCaptionStates && !this.captionAnimationHelper.isShowing())
            {
                this.captionAnimationHelper.update(MOVE, this.basicColorFactory.GREEN);
            }

            this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.UP));
        }
        else
        {
            this.captionAnimationHelper.update(CommonPhoneStrings.getInstance().FIRE, this.basicColorFactory.RED);
            
            // int anotherTargetDistance = DistanceUtil.getDistance(this, this.currentTargetLayerInterface);

            this.rtsLayer2LogHelper.steeringFireOrStop(this);
            
            //logUtil.put(
            //  "Attacking: " + this.currentTargetLayerInterface.getName() +
            //" anotherTargetDistance: " + anotherTargetDistance +
            //" Range: " + this.currentTargetDistance, this, "trackTo");

            // logUtil.put(TrackingEventHandler.getInstance().toString(), this, "processTargeting");

            // logUtil.put("Attacking: " +
            // this.currentTargetLayerInterface.getName() + " X: " +
            // this.currentTargetLayerInterface.getXP() + " ? " + this.x +
            // " Y: " + this.currentTargetLayerInterface.getYP() + " ? " +
            // this.y, this, "processTargeting");
            // logUtil.put("Attacking: " +
            // this.currentTargetLayerInterface.getName() + " at Range: " +
            // this.currentTargetDistance + ">=" + this.longWeaponRange,
            // this, "processTargeting");
            this.allStop();
            this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.KEY_NUM0));
            TrackingEventHandler.getInstance().fireEvent(this.getTrackingEvent());
        }
    }

    public void move()
    {
        try {
            final VelocityProperties velocityProperties
                    = this.getVehicleProperties().getVelocityProperties();

            final long velocityXScaled
                    = velocityProperties.getVelocityXBasicDecimal().getScaled();

            final long velocityYScaled
                    = velocityProperties.getVelocityYBasicDecimal().getScaled();

            this.getUnitWaypointBehavior().move();

            if (velocityXScaled != 0 || velocityYScaled != 0) {
                this.getUnitWaypointBehavior().setMoving(true);

                final GeographicMapCompositeInterface geographicMapCompositeInterface
                    = (GeographicMapCompositeInterface) this.allBinaryGameLayerManager;
                final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
                
                layerPartialCellPositionsUtil.getAll(
                        geographicMapInterface,
                        this, (int) velocityXScaled, (int) velocityYScaled,
                        getPartialpositionlist());

                //is building in path
                final GeographicMapCellPosition cellPosition = (GeographicMapCellPosition) DropCellPositionHistory.getInstance().getCellPositionWithDrop(
                        getPartialpositionlist());

                if (cellPosition == null) {
                    final AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
                    final TiledLayerUtil tiledLayerUtil = TiledLayerUtil.getInstance();

                    int x = this.x + (int) velocityXScaled;
                    int y = this.y + (int) velocityYScaled;

                    x = tiledLayerUtil.keepOnMapX(tiledLayer, x, this.getWidth());
                    y = tiledLayerUtil.keepOnMapY(tiledLayer, y, this.getHeight());

                    this.setPosition(x, y, this.z);
                } else {
                    final AllBinaryLayer allbinaryLayer
                            = DropCellPositionHistory.getInstance().getLayerInterface(cellPosition);

                    this.getUnitWaypointBehavior().setMovingFromStopped(false);
                    this.getUnitWaypointBehavior().addBuildingChase(allbinaryLayer, cellPosition);
                }
            }

            if (this.getUnitWaypointBehavior().isMoving()) {
                // logUtil.put("moving", this, "move");
                TrackingEventHandler.getInstance().fireEvent(this.getTrackingEvent());
                // this.onViewPositionChangeEvent();
                // TiledLayerUtil.keepOnMap(this.geographicMapInterface.getAllBinaryTiledLayer(),
                // this, 0, 0);
                // this.updateCurrentGeographicMapCellPosition();
            } else {
                this.getUnitWaypointBehavior().setMovingFromStopped(false);
            }

            if (this.getUnitWaypointBehavior().isMovingFromStopped() && this.isVisible()) {
                SecondaryPlayerQueueFactory.getInstance().add(
                        this.moveSoundInterface);
            }
        } catch(Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, "move", e);
        }
    }

    public void allStop()
    {
        final VelocityProperties velocityProperties = 
            this.getVehicleProperties().getVelocityProperties();

        velocityProperties.getVelocityXBasicDecimal().set(0);
        velocityProperties.getVelocityYBasicDecimal().set(0);
    }

    public void paint(Graphics graphics)
    {
        if (this.isVisible())
        {
            // this.baseAnimationInterface.paint(graphics, viewX, viewY);
            // this.getAnimationInterface().paint(graphics, viewX, viewY);
            super.paint(graphics);

            final ViewPosition viewPosition = this.getViewPosition();
            int viewX = viewPosition.getX();
            int viewY = viewPosition.getY();

            this.decalAnimation.paint(graphics, viewX, viewY);
            
            this.rangeAnimation.paint(graphics, viewX, viewY);
            this.sensorRangeAnimation.paint(graphics, viewX, viewY);

            this.damageFloatersPaintableInterface.paint(graphics);
            this.healthBar.paint(graphics);

            this.captionAnimationHelper.paint(graphics, viewX, viewY);

            this.pathAnimation.paint(graphics, viewX, viewY);

            this.resourceAnimation.paint(graphics, viewX, viewY);
        }
    }

    public void onBuildingEvent(RTSLayerEvent event)
        throws Exception
    {
        BuildingLayer buildingLayer = (BuildingLayer) event.getRtsLayer();

        this.getUnitWaypointBehavior().moveAwayFromBuilding(buildingLayer);
    }

    /**
     * @return the vehicleProperties
     */
    public VehicleProperties getVehicleProperties()
    {
        return vehicleProperties;
    }

    public void damage(final int damage, final int damageType) throws Exception
    {
        super.damage(damage, damageType);

        this.damageFloaters.add(damage);

        if (damage > 0)
        // logUtil.put("Recieving Damage: " + damage,
        // this, "damage");
        {
            this.getHealthInterface().damage(damage);
        }
    }

    public int getDamage(final int damageType) throws Exception
    {
        return 0;
    }

    public void setDestroyed(final boolean destroyed)
        throws Exception
    {
        logUtil.put(commonStrings.START, this, "setDestroyed");
        super.setDestroyed(destroyed);

        if (this.isDestroyed())
        {
            WaypointEventHandlerFactory.getInstance(
                this.getGroupInterface()[0]).removeListener(this.getUnitWaypointBehavior());
            TrackingEventHandler.getInstance().removeListener(this);
            BuildingEventHandler.getInstance().removeListener(this);

            if (!this.getHealthInterface().isAlive())
            {
                final int damage = this.getHealthInterface().getMaxHealth();
                if (damage > 10)
                {
                    SecondaryPlayerQueueFactory.getInstance().add(
                        ExplosionBasicSound.getInstance());

                    if (damage < 100)
                    {
                        this.shakeListener.onSmallShakeEvent();
                        vibration.vibrate(duration, 0, 0);
                    }
                    else if (damage < 1000)
                    {
                        this.shakeListener.onMediumShakeEvent();
                        vibration.vibrate(duration * 2, 0, 0);
                    }
                    else if (damage < 3000)
                    {
                        this.shakeListener.onLargeShakeEvent();
                        vibration.vibrate(duration * 4, 0, 0);
                    }
                }
            }
        }
    }

    /**
     * @return the resource
     */
    public int getLoad()
    {
        return resourceLoad;
    }

    public void clearResourceAnimation()
    throws Exception
    {
        this.resourceAnimation = (IndexedAnimation) 
            NullIndexedAnimationFactory.getFactoryInstance().getInstance(0);
    }

    /**
     * @param resource
     *            the resource to set
     */
    public void setLoad(short resource)
    throws Exception
    {
        if(resource > 0)
        {
            this.resourceAnimation = this.initResourceAnimation;
        }
        else
        {
            //Drop load... I could have yet another animation showing the load being dropped
            this.clearResourceAnimation();
        }
        this.resourceLoad = resource;
    }

    /**
     * @param resource
     *            the resource add
     */
    public void addLoad(int resource)
    {
        this.resourceLoad += resource;
    }

    private final CapitalEvent CAPITAL_EVENT = new CapitalEvent(this);
    
    public void handleCost(PathFindingLayerInterface ownerLayer) throws Exception {
        if (this.getLoad() > 0) {
            //Add Capital
            CAPITAL_EVENT.setValue(this.getLoad());
            CapitalEventHandlerFactory.getInstance(
                ownerLayer.getGroupInterface()[0]).fireEvent(CAPITAL_EVENT);
            this.setLoad((short) 0);
        }
    }
    
    public SelectionHudPaintable createHudPaintable()
    {
        final RTSLayerHudPaintable rtsLayerHudPaintable = 
            RTSLayerHudPaintable.getInstance();
        
        rtsLayerHudPaintable.setBasicColor(this.allBinaryGameLayerManager.getForegroundBasicColor());
        rtsLayerHudPaintable.setRtsLayer(this);
        
        return rtsLayerHudPaintable;
    }

    public SelectionHudPaintable getHudPaintable()
    {
        final RTSLayerHudPaintable rtsLayerHudPaintable = 
            RTSLayerHudPaintable.getInstance();
        
        return rtsLayerHudPaintable;
    }
    
    public int getType()
    {
        return getStaticType();
    }

    public static int getStaticType()
    {
        return 1;
    }
    
    protected UnitWaypointBehavior getUnitWaypointBehavior()
    {
        return (UnitWaypointBehavior) this.getWaypointBehavior();
    }

    public TrackingEvent getTrackingEvent()
    {
        return trackingEvent;
    }

    public CaptionAnimationHelperBase getCaptionAnimationHelper()
    {
        return captionAnimationHelper;
    }
    
    public boolean isSelfUpgradeable()
    {
        return false;
    }

    public short getMaxResourceLoad()
    {
        return maxResourceLoad;
    }

    public static BasicArrayList getPartialpositionlist()
    {
        return partialPositionList;
    }
}
