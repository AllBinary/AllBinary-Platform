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
package org.allbinary.game.layer.building;

import java.util.Hashtable;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.NullIndexedAnimationFactory;
import org.allbinary.game.identification.GroupFactory;
import org.allbinary.game.input.form.NullRTSFormInputFactory;
import org.allbinary.game.input.form.RTSFormInput;
import org.allbinary.game.input.form.VisibleCellPositionsSingleton;
import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.AdvancedRTSPlayerLayerInterface;
import org.allbinary.game.layer.AdvancedRTSProperties;
import org.allbinary.game.layer.CollidableRTSBehavior;
import org.allbinary.game.layer.RTSLayerUtil;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.layer.SelectionHudPaintable;
import org.allbinary.game.layer.waypoint.Waypoint;
import org.allbinary.game.view.TileLayerPositionIntoViewPosition;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.RotationAnimationInterfaceCompositeInterface;
import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.game.combat.damage.DamageFloaters;
import org.allbinary.game.health.Health;
import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.GeographicMapCellPositionAreaBase;
import org.allbinary.game.layer.NullPathFindingLayer;
import org.allbinary.game.tracking.TrackingEvent;
import org.allbinary.game.tracking.TrackingEventHandler;
import org.allbinary.game.tracking.TrackingEventListenerInterface;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.media.audio.SecondaryPlayerQueueFactory;
import org.allbinary.media.audio.SelectSound;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapDirectionUtil;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionHistory;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.util.BasicArrayListS;
import org.allbinary.weapon.media.audio.ExplosionBasicSound;
import org.allbinary.game.multiplayer.layer.RemoteInfo;

public class BuildingLayer
    extends AdvancedRTSGameLayer
    implements RotationAnimationInterfaceCompositeInterface,
    TrackingEventListenerInterface
{
    public static BuildingLayer createSimulated() throws Exception {
        final AnimationInterfaceFactoryInterface nullAnimationInterfaceFactoryInterface = NullAnimationFactory.getFactoryInstance();
        final AnimationInterfaceFactoryInterface nullIndexedAnimationInterfaceFactoryInterface = NullIndexedAnimationFactory.getFactoryInstance();

        return new BuildingLayer(RemoteInfo.REMOTE_INFO,
                SimulatedBuildingPropertiesFactory.getInstance(),
                AdvancedRTSProperties.createPropertiesSimulated(),
                //NullPathFindingLayer.NULL_PATH_FINDING_LAYER,
                GroupFactory.getInstance().NULL_GROUP_ARRAY,
                StringUtil.getInstance().EMPTY_STRING,
                StringUtil.getInstance().EMPTY_STRING,
                Health.NULL_HEALTH,
                NullRTSFormInputFactory.getInstance(),
                nullAnimationInterfaceFactoryInterface,
                nullIndexedAnimationInterfaceFactoryInterface,
                nullAnimationInterfaceFactoryInterface,
                nullAnimationInterfaceFactoryInterface,
                nullIndexedAnimationInterfaceFactoryInterface,
                NullIndexedAnimationFactory.getFactoryInstance(),
                RectangleFactory.SINGLETON,
                0, 0
                //new TileLayerPositionIntoViewPosition()
        );

    }

    private int buildingLevelCost;
    private int productivity;
    private int efficiency;
    private int efficiencyPerLevel;
    private final TrackingEvent trackingEvent;

    private final DamageFloaters damageFloaters;
    private final Paintable damageFloatersPaintableInterface;
    private final Paintable healthBar;

    private final Hashtable pathsHashtable;

    public BuildingLayer(
            final RemoteInfo remoteInfo,
            final BuildingPropertiesFactory buildingPropertiesFactory,
            final AdvancedRTSProperties advancedRTSProperties,
        final Group[] groupInterface,
        final String rootName,
        final String name,
        final Health healthInterface,
        final RTSFormInput rtsFormInput,
        final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface emptyAnimationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface baseAnimationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface buildAnimationInterfaceFactoryInterface,
        final AnimationInterfaceFactoryInterface verticleBuildAnimationInterfaceFactoryInterface,
        final ProceduralAnimationInterfaceFactoryInterface proceduralAnimationInterfaceFactoryInterface,
        final Rectangle rectangle,
        final int x, final int y)
        throws Exception
    {
        super(remoteInfo,
                NullPathFindingLayer.NULL_PATH_FINDING_LAYER,
                advancedRTSProperties,
            groupInterface, 
            rootName, name, 
            healthInterface,
            rtsFormInput,
            animationInterfaceFactoryInterface,
            emptyAnimationInterfaceFactoryInterface,
            baseAnimationInterfaceFactoryInterface,
            buildAnimationInterfaceFactoryInterface,
            verticleBuildAnimationInterfaceFactoryInterface,
            proceduralAnimationInterfaceFactoryInterface,
            rectangle,
            x, y, new TileLayerPositionIntoViewPosition());

        this.setCollidableInferface(new CollidableRTSBehavior(this, true));
        
        this.getWaypointBehavior().setWaypoint(new Waypoint(this, SelectSound.getInstance()));

        this.damageFloaters = buildingPropertiesFactory.getDamageFloaters(this);
        this.damageFloatersPaintableInterface = buildingPropertiesFactory.damageFloatersPaintableInterface;

        this.healthBar = buildingPropertiesFactory.getHealthBar(this);

        this.pathsHashtable = buildingPropertiesFactory.getHashtable();

        this.setMaxLevel(30);

        this.setProductivity(1);
        this.setEfficiency(this.calculateEfficiency());

        this.efficiencyPerLevel = buildingPropertiesFactory.getEfficiencyPerLevel(this);
        this.efficiency = this.efficiencyPerLevel;

        this.generateMoveOutOfBuildAreaPaths();

        this.trackingEvent = buildingPropertiesFactory.getTrackingEvent(this);
    }

    protected boolean local;

    @Override
    protected void initVisibility(
            final RTSPlayerLayerInterface rtsPlayerLayerInterface)
    {
        final AdvancedRTSPlayerLayerInterface advancedRTSPlayerLayerInterface = 
            (AdvancedRTSPlayerLayerInterface) rtsPlayerLayerInterface;

        if (advancedRTSPlayerLayerInterface.isLocalPlayer())
        {
            this.local = true;
            this.addVisibility();
        } else
        {
            this.local = false;
        }
        
        super.initVisibility(rtsPlayerLayerInterface);
    }
    
    @Override
    public void construct(final RTSPlayerLayerInterface rtsPlayerLayerInterface)
    throws Exception
    {
        super.construct(rtsPlayerLayerInterface);

        TrackingEventHandler.getInstance().addListener(this);
    }

    @Override
    public void onMovement(final TrackingEvent trackingEvent)
    {
        try
        {
            final AdvancedRTSGameLayer layerInterface = (AdvancedRTSGameLayer) trackingEvent.getLayerInterface();

            //this.logUtil.putF("Possible Target: " + layerInterface.getName(), this, "onMovement");

            if (layerInterface.getGroupInterface()[0] != this.getGroupInterface()[0])
            {
                //this.logUtil.putF("Enemy Possible Target: " + layerInterface.getName(), this, "onMovement");

                layerInterface.onMovementFound(this.trackingEvent);
            }
        }
        catch (Exception e)
        {
            this.logUtil.put(commonStrings.EXCEPTION, this, "onMovement", e);
        }        
    }

    protected final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(3000);

    @Override
    public void processBuiltTick(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {
        if(this.getHealthInterface().isDamaged())
        {
            this.setAnimationInterface(this.destroyAnimationInterface);
        }
        /*
        else
        {
            this.setAnimationInterface(this.indexedAnimationInterface);
        }
        */

        if (!this.getHealthInterface().isAlive())
        {
            if (this.isReadyForExplosion())
            {
                final int currentFrame = this.destroyAnimationInterface.getFrame();
                final int size = this.destroyAnimationInterface.getSize() - 1;

                //this.logUtil.putF("Explosion - Processing: " + currentFrame + "==" + size, this, "processTick");

                if (currentFrame == size && !this.timeDelayHelper.isTimeTNT())
                {
                    //this.logUtil.putF("Explosion - End", this, "processTick");

                    if (!this.getHealthInterface().isAlive())
                    {
                        this.setDestroyed(true);
                    }

                }
                else
                {
                    // show next frame in explosion
                    this.destroyAnimationInterface.nextFrame();
                }
            }
            else
            {
                // this.logUtil.putF("Explosion - Begin", // this, "processTick");

                this.setAnimationInterface(this.destroyAnimationInterface);

                //this.getVehicleProperties().getVelocityProperties().zero();

                SecondaryPlayerQueueFactory.getInstance().add(
                    ExplosionBasicSound.getInstance());

                this.shakeListener.onSmallShakeEvent();
                vibration.vibrate(duration, 0, 0);

                this.timeDelayHelper.setStartTimeTNT();
                this.setReadyForExplosion(true);
            }
        }
        else
        {
            super.processBuiltTick(allBinaryLayerManager);
        }

        this.indexedButShouldBeRotationAnimationInterface.nextFrame();
    }

    private final int calculateEfficiency()
    {
        return this.efficiencyPerLevel;
    }

    @Override
    public int getCost()
    {
        final long total = RTSLayerUtil.getInstance().getCostExponential((long) (this.getLevel() * this.getBuildingLevelCost()));

        return (int) total;
    }

    @Override
    public int getDowngradeCost()
    {
        final long downgradeCost = RTSLayerUtil.getInstance().getCostExponential((long) ((this.getLevel() - 1) * getBuildingLevelCost()));

        this.logUtil.putF("Cost: " + downgradeCost, this, "getDowngradeCost");

        return (int) downgradeCost * 9 / 10;
    }

    @Override
    public int getUpgradeCost()
    {
        final long upgradeCost = RTSLayerUtil.getInstance().getCostExponential((long) ((this.getLevel() + 1) * getBuildingLevelCost()));

        //this.logUtil.putF("Cost: " + upgradeCost, this, "getUpgradeCost");

        return (int) upgradeCost;
    }

    @Override
    public void downgrade()
    {
        super.downgrade();

        this.setProductivity(this.getProductivity() - 1);
        this.setEfficiency(this.getEfficiency() - this.calculateEfficiency());

        this.getHealthInterface().setMaxHealth(this.getHealthInterface().getMaxHealth() -
            ((this.getLevel() + 1) * 100));
    }

    @Override
    public void upgrade()
    {
        super.upgrade();

        this.setProductivity(this.getProductivity() + 1);
        this.setEfficiency(this.getEfficiency() + this.calculateEfficiency());

        this.getHealthInterface().setMaxHealth(this.getHealthInterface().getMaxHealth() +
            (this.getLevel() * 100));
    }

    /**
     * @return the buildingLevelCost
     */
    private int getBuildingLevelCost()
    {
        return buildingLevelCost;
    }

    /**
     * @param buildingLevelCost the buildingLevelCost to set
     */
    protected void setBuildingLevelCost(int buildingLevelCost)
    {
        this.buildingLevelCost = buildingLevelCost;
    }

    /**
     * @return the productivity
     */
    public int getProductivity()
    {
        return productivity;
    }

    /**
     * @param productivity the productivity to set
     */
    public void setProductivity(int productivity)
    {
        this.productivity = productivity;
    }

    /**
     * @return the efficiency
     */
    public int getEfficiency()
    {
        return efficiency;
    }

    /**
     * @param efficiency the efficiency to set
     */
    public void setEfficiency(int efficiency)
    {
        this.efficiency = efficiency;
    }

    private void generateMoveOutOfBuildAreaPaths()
        throws Exception
    {
        final BasicArrayList occupyList =
            this.geographicMapCellPositionAreaBase.getOccupyingGeographicMapCellPositionList();
        final BasicArrayList surroundList =
            this.geographicMapCellPositionAreaBase.getSurroundingGeographicMapCellPositionList();

        final Direction NO_DIRECTION = DirectionFactory.getInstance().NO_DIRECTION;
        
        //int size = 8;
        //if(occupyList.size() > 1)
        //{
        //  size = 3;
        //}

        final GeographicMapDirectionUtil geographicMapDirectionUtil = GeographicMapDirectionUtil.getInstance();

        BasicArrayList pathsList;
        GeographicMapCellPosition occupyGeographicMapCellPosition;
        GeographicMapCellPosition surroundGeographicMapCellPosition;
        BasicArrayList list;
        for (int index2 = occupyList.size() - 1; index2 >= 0; index2--)
        {
            pathsList = new BasicArrayListD();

            occupyGeographicMapCellPosition =
                (GeographicMapCellPosition) occupyList.get(index2);
            
            for (int index = surroundList.size() - 1; index >= 0; index--)
            {
                surroundGeographicMapCellPosition =
                    (GeographicMapCellPosition) surroundList.get(index);

                //If bordered with
                if (geographicMapDirectionUtil.getEightDirectionFromCellPositionToAdjacentCellPosition(
                    surroundGeographicMapCellPosition, 
                    occupyGeographicMapCellPosition) != NO_DIRECTION)
                {
                    list = new BasicArrayListS(1);
                    //list.add(occupyGeographicMapCellPosition);
                    list.add(surroundGeographicMapCellPosition);
                    pathsList.add(list);
                }
            }
            this.pathsHashtable.put(occupyGeographicMapCellPosition, pathsList);
        }

    }

    @Override
    public BasicArrayList getMoveOutOfBuildAreaPath(
        final GeographicMapCellPosition geographicMapCellPosition)
    {
        final BasicArrayList pathsList = (BasicArrayList) this.pathsHashtable.get(geographicMapCellPosition);

        return pathsList;
    }

    @Override
    public BasicArrayList getEndGeographicMapCellPositionList() {
        return this.geographicMapCellPositionAreaBase.getSurroundingGeographicMapCellPositionList();
    }

    @Override
    public boolean shouldHandleStartSameAsEnd() {
        return false;
    }
    
    @Override
    public void paint(final Graphics graphics)
    {
        if (this.isVisible())
        {
            super.paint(graphics);

            this.damageFloatersPaintableInterface.paint(graphics);
            this.healthBar.paint(graphics);
        }
    }

    @Override
    public void damage(final int damage, final int damageType) throws Exception
    {
        super.damage(damage, damageType);

        this.damageFloaters.add(damage);

        //this.logUtil.putF("Recieving Damage: " + damage, this, "damage");
        if (damage > 0)
        this.getHealthInterface().damage(damage);
    }

    @Override
    public int getDamage(int damageType) throws Exception
    {
        return 0;
    }

    public void addVisibility()
    {
        final GeographicMapCellPositionAreaBase geographicMapCellPositionArea = 
            this.geographicMapCellPositionAreaBase;
        
        final BasicArrayList occupyList = 
            geographicMapCellPositionArea.getOccupyingGeographicMapCellPositionList();
        
        VisibleCellPositionsSingleton.getInstance().addStationaryCellPositions(occupyList);
        
        final BasicArrayList surroundList = 
            geographicMapCellPositionArea.getSurroundingGeographicMapCellPositionList();
        
        VisibleCellPositionsSingleton.getInstance().addStationaryCellPositions(surroundList);
    }
    
    public void removeVisibility()
    {
        final BasicArrayList occupyList = this.geographicMapCellPositionAreaBase
                .getOccupyingGeographicMapCellPositionList();

        VisibleCellPositionsSingleton.getInstance().removeStationaryCellPositions(occupyList);

        final BasicArrayList surroundList = this.geographicMapCellPositionAreaBase
                .getSurroundingGeographicMapCellPositionList();

        VisibleCellPositionsSingleton.getInstance().removeStationaryCellPositions(surroundList);
    }

    @Override
    public void setDestroyed(boolean destroyed)
        throws Exception
    {
        super.setDestroyed(destroyed);

        if (this.isDestroyed())
        {
            DropCellPositionHistory.getInstance().removeAll(this);
            TrackingEventHandler.getInstance().removeListener(this);
            
            if (this.local)
            {
                this.removeVisibility();
            }

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

    /*
    protected void collideNone(AllBinaryCollidableLayer collidableInterface) throws Exception
    {
    AdvancedRTSGameLayer rtsLayer = (AdvancedRTSGameLayer) collidableInterface;
    if (rtsLayer.getType() == UnitLayer.getStaticType())
    {
    this.collideUnit((UnitLayer) rtsLayer);
    }
    }
     */
    //Repath units around building
    //This will happen if:
    //1. Unit started path before building built
    //2. Unit Chasing
    //3. Pushed into building by another unit
    /*
    protected void collideUnit(UnitLayer collidableInterface) throws Exception
    {
    this.logUtil.putF("Move Away From Building", this, "collideUnit");
    collidableInterface.moveAwayFromBuilding(this);

    //1. Unit started path before building built or was pushed into building
    //if(collidableInterface.isTrackingWaypoint())
    //{
    //Try to get unit to get new path to waypoint
    //this.logUtil.putF("Building force Clearing Target", this, "collideUnit");
    //  collidableInterface.moveAwayFromBuilding(this);
    //}
    //else
    //2. Unit Chasing or was pushed into building
    //{
    //    collidableInterface.moveAwayFromBuilding(this);
    //}
    }
     */
    
    @Override
    public SelectionHudPaintable createHudPaintable()
    {
        final BuildingInfoHudPaintable buildingInfoHudPaintable = 
            BuildingInfoHudPaintable.getInstance();
        
        buildingInfoHudPaintable.setBasicColorP(this.allBinaryGameLayerManagerP.getForegroundBasicColor());
        buildingInfoHudPaintable.setRtsLayer(this);
        
        return buildingInfoHudPaintable;
    }

    @Override
    public SelectionHudPaintable getHudPaintable()
    {
        final BuildingInfoHudPaintable buildingInfoHudPaintable = 
            BuildingInfoHudPaintable.getInstance();
        
        return buildingInfoHudPaintable;
    }

    @Override
    public int getType()
    {
        return BuildingLayer.getStaticType();
    }

    public static int getStaticType()
    {
        return 2;
    }
}
