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

import org.allbinary.game.input.form.RTSFormInput;
import org.allbinary.game.input.form.VisibleCellPositionsSingleton;
import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.AdvancedRTSPlayerLayerInterface;
import org.allbinary.game.layer.CollidableRTSBehavior;
import org.allbinary.game.layer.GeographicMapCellPositionArea;
import org.allbinary.game.layer.RTSLayerUtil;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.layer.SelectionHudPaintable;
import org.allbinary.game.layer.waypoint.Waypoint;
import org.allbinary.util.BasicArrayList;
import org.allbinary.string.CommonStrings;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.RotationAnimationInterfaceCompositeInterface;
import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.game.combat.damage.DamageFloaters;
import org.allbinary.game.combat.damage.PtsDamageFloaters;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.game.health.Health;
import org.allbinary.game.health.HealthBar;
import org.allbinary.game.health.HealthBarTwodAnimation;
import org.allbinary.game.identification.Group;
import org.allbinary.game.tracking.TrackingEvent;
import org.allbinary.game.tracking.TrackingEventHandler;
import org.allbinary.game.tracking.TrackingEventListenerInterface;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.media.audio.SecondaryPlayerQueueFactory;
import org.allbinary.media.audio.SelectSound;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapDirectionUtil;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionHistory;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.weapon.media.audio.ExplosionBasicSound;
import org.allbinary.game.multiplayer.layer.RemoteInfo;

public class BuildingLayer
    extends AdvancedRTSGameLayer
    implements RotationAnimationInterfaceCompositeInterface,
    TrackingEventListenerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
                null,
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
            x, y);

        this.setCollidableInferface(new CollidableRTSBehavior(this, true));
        
        this.getWaypointBehavior().setWaypoint(new Waypoint(this, SelectSound.getInstance()));
        
        if (Features.getInstance().isFeature(GameFeatureFactory.getInstance().DAMAGE_FLOATERS))
        {
            this.damageFloatersPaintableInterface = this.damageFloaters =
                new PtsDamageFloaters(this);
        }
        else
        {
            this.damageFloatersPaintableInterface = NullPaintable.getInstance();
            this.damageFloaters = new DamageFloaters();
        }

        if (Features.getInstance().isFeature(GameFeatureFactory.getInstance().HEALTH_BARS))
        {
            this.healthBar = new HealthBar(this, this.getHealthInterface(),
                new HealthBarTwodAnimation((AllBinaryLayer) this, BasicHudFactory.getInstance().BOTTOMLEFT), -1);
        }
        else
        {
            this.healthBar = NullPaintable.getInstance();
        }

        this.pathsHashtable = new Hashtable();

        this.setMaxLevel(30);

        this.setProductivity(1);
        this.setEfficiency(this.calculateEfficiency());

        this.efficiencyPerLevel = 10000 / this.getMaxLevel() + 10000 % this.getMaxLevel();
        this.efficiency = this.efficiencyPerLevel;

        this.generateMoveOutOfBuildAreaPaths();

        this.trackingEvent = new TrackingEvent(this);
    }

    //used to simulate cost
    public BuildingLayer()
        throws Exception
    {
        super();

        this.setCollidableInferface(new CollidableRTSBehavior(this, true));
        
        this.getWaypointBehavior().setWaypoint(new Waypoint(this, SelectSound.getInstance()));
        
        this.trackingEvent = null;
        this.damageFloaters = null;

        this.damageFloatersPaintableInterface = null;

        this.healthBar = null;

        this.pathsHashtable = null;
    }

    protected boolean local;

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
    
    public void construct(final RTSPlayerLayerInterface rtsPlayerLayerInterface)
    throws Exception
    {
        super.construct(rtsPlayerLayerInterface);

        TrackingEventHandler.getInstance().addListener(this);
    }

    public void onMovement(final TrackingEvent trackingEvent)
    {
        try
        {
            final AdvancedRTSGameLayer layerInterface = (AdvancedRTSGameLayer) trackingEvent.getLayerInterface();

            //logUtil.put("Possible Target: " + layerInterface.getName(), this, "onMovement");

            if (layerInterface.getGroupInterface()[0] != this.getGroupInterface()[0])
            {
                //logUtil.put("Enemy Possible Target: " + layerInterface.getName(), this, "onMovement");

                layerInterface.onMovementFound(this.trackingEvent);
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "onMovement", e);
        }        
    }

    protected final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(3000);

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

                //logUtil.put("Explosion - Processing: " + currentFrame + "==" + size, this, "processTick");

                if (currentFrame == size && !this.timeDelayHelper.isTime())
                {
                    //logUtil.put("Explosion - End", this, "processTick");

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
                // logUtil.put("Explosion - Begin",
                // this, "processTick");

                this.setAnimationInterface(this.destroyAnimationInterface);

                //this.getVehicleProperties().getVelocityProperties().zero();

                SecondaryPlayerQueueFactory.getInstance().add(
                    ExplosionBasicSound.getInstance());

                this.shakeListener.onSmallShakeEvent();
                vibration.vibrate(duration, 0, 0);

                this.timeDelayHelper.setStartTime();
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

    public int getCost()
    {
        final int total = RTSLayerUtil.getInstance().getCostExponential(this.getLevel() * this.getBuildingLevelCost());

        return total;
    }

    public int getDowngradeCost()
    {
        final int downgradeCost = RTSLayerUtil.getInstance().getCostExponential((this.getLevel() - 1) * getBuildingLevelCost());

        logUtil.put("Cost: " + downgradeCost, this, "getDowngradeCost");

        return downgradeCost * 9 / 10;
    }

    public int getUpgradeCost()
    {
        final int upgradeCost = RTSLayerUtil.getInstance().getCostExponential(
                (this.getLevel() + 1) * getBuildingLevelCost());

        //logUtil.put("Cost: " + upgradeCost, this, "getUpgradeCost");

        return upgradeCost;
    }

    public void downgrade()
    {
        super.downgrade();

        this.setProductivity(this.getProductivity() - 1);
        this.setEfficiency(this.getEfficiency() - this.calculateEfficiency());

        this.getHealthInterface().setMaxHealth(this.getHealthInterface().getMaxHealth() -
            ((this.getLevel() + 1) * 100));
    }

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
            this.geographicMapCellPositionArea.getOccupyingGeographicMapCellPositionList();
        final BasicArrayList surroundList =
            this.geographicMapCellPositionArea.getSurroundingGeographicMapCellPositionList();

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
            pathsList = new BasicArrayList();

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
                    list = new BasicArrayList(1);
                    //list.add(occupyGeographicMapCellPosition);
                    list.add(surroundGeographicMapCellPosition);
                    pathsList.add(list);
                }
            }
            this.pathsHashtable.put(occupyGeographicMapCellPosition, pathsList);
        }

    }

    public BasicArrayList getMoveOutOfBuildAreaPath(
        final GeographicMapCellPosition geographicMapCellPosition)
    {
        final BasicArrayList pathsList = (BasicArrayList) this.pathsHashtable.get(geographicMapCellPosition);

        return pathsList;
    }

    public BasicArrayList getEndGeographicMapCellPositionList() {
        return this.geographicMapCellPositionArea.getSurroundingGeographicMapCellPositionList();
    }

    public boolean shouldHandleStartSameAsEnd() {
        return false;
    }
    
    public void paint(final Graphics graphics)
    {
        if (this.isVisible())
        {
            super.paint(graphics);

            this.damageFloatersPaintableInterface.paint(graphics);
            this.healthBar.paint(graphics);
        }
    }

    public void damage(final int damage, final int damageType) throws Exception
    {
        super.damage(damage, damageType);

        this.damageFloaters.add(damage);

        //logUtil.put("Recieving Damage: " + damage, this, "damage");
        if (damage > 0)
        this.getHealthInterface().damage(damage);
    }

    public int getDamage(int damageType) throws Exception
    {
        return 0;
    }

    public void addVisibility()
    {
        final GeographicMapCellPositionArea geographicMapCellPositionArea = 
            this.geographicMapCellPositionArea;
        
        final BasicArrayList occupyList = 
            geographicMapCellPositionArea.getOccupyingGeographicMapCellPositionList();
        
        VisibleCellPositionsSingleton.getInstance().addStationaryCellPositions(occupyList);
        
        final BasicArrayList surroundList = 
            geographicMapCellPositionArea.getSurroundingGeographicMapCellPositionList();
        
        VisibleCellPositionsSingleton.getInstance().addStationaryCellPositions(surroundList);
    }
    
    public void removeVisibility()
    {
        final BasicArrayList occupyList = this.geographicMapCellPositionArea
                .getOccupyingGeographicMapCellPositionList();

        VisibleCellPositionsSingleton.getInstance().removeStationaryCellPositions(occupyList);

        final BasicArrayList surroundList = this.geographicMapCellPositionArea
                .getSurroundingGeographicMapCellPositionList();

        VisibleCellPositionsSingleton.getInstance().removeStationaryCellPositions(surroundList);
    }

    public void setDestroyed(boolean destroyed)
        throws Exception
    {
        super.setDestroyed(destroyed);

        if (this.isDestroyed())
        {
            DropCellPositionHistory.getInstance().remove(this);
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
    logUtil.put("Move Away From Building", this, "collideUnit");
    collidableInterface.moveAwayFromBuilding(this);

    //1. Unit started path before building built or was pushed into building
    //if(collidableInterface.isTrackingWaypoint())
    //{
    //Try to get unit to get new path to waypoint
    //logUtil.put("Building force Clearing Target", this, "collideUnit");
    //  collidableInterface.moveAwayFromBuilding(this);
    //}
    //else
    //2. Unit Chasing or was pushed into building
    //{
    //    collidableInterface.moveAwayFromBuilding(this);
    //}
    }
     */
    
    public SelectionHudPaintable createHudPaintable()
    {
        final BuildingInfoHudPaintable buildingInfoHudPaintable = 
            BuildingInfoHudPaintable.getInstance();
        
        buildingInfoHudPaintable.setBasicColorP(this.allBinaryGameLayerManagerP.getForegroundBasicColor());
        buildingInfoHudPaintable.setRtsLayer(this);
        
        return buildingInfoHudPaintable;
    }

    public SelectionHudPaintable getHudPaintable()
    {
        final BuildingInfoHudPaintable buildingInfoHudPaintable = 
            BuildingInfoHudPaintable.getInstance();
        
        return buildingInfoHudPaintable;
    }

    public int getType()
    {
        return BuildingLayer.getStaticType();
    }

    public static int getStaticType()
    {
        return 2;
    }
}
