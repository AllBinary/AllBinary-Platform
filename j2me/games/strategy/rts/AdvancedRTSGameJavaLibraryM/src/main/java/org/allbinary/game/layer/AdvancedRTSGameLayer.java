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
package org.allbinary.game.layer;

import javax.microedition.lcdui.Canvas;

import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.animation.transition.shake.NoShakeAnimationListener;
import org.allbinary.animation.transition.shake.ShakeAnimationListener;
import org.allbinary.animation.transition.shake.ShakeAnimationListenerFactory;
import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.game.GameTypeFactory;
import org.allbinary.game.combat.destroy.event.DestroyedEvent;
import org.allbinary.game.combat.destroy.event.DestroyedEventHandler;
import org.allbinary.game.combat.destroy.event.DestroyedEventListenerInterface;
import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.game.health.Health;
import org.allbinary.game.identification.Group;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.form.RTSFormInput;
import org.allbinary.game.layer.building.event.BuildingEventHandler;
import org.allbinary.game.layer.unit.UnitLayer;
import org.allbinary.game.layer.waypoint.Waypoint2LogHelper;
import org.allbinary.game.layer.waypoint.Waypoint2SelectedLogHelper;
import org.allbinary.game.layer.waypoint.WaypointBase;
import org.allbinary.game.layer.waypoint.WaypointLogHelper;
import org.allbinary.game.layer.waypoint.WaypointRunnableLogHelper;
import org.allbinary.game.layer.waypoint.WaypointRunnableSelectedLogHelper;
import org.allbinary.game.layer.waypoint.WaypointSelectedLogHelper;
import org.allbinary.game.multiplayer.layer.RemoteInfo;
import org.allbinary.game.tick.NullTickable;
import org.allbinary.game.tick.TickableInterface;
import org.allbinary.game.view.TileLayerPositionIntoViewPosition;
import org.allbinary.graphics.Rectangle;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.math.AngleInfo;
import org.allbinary.math.FrameUtil;
import org.allbinary.media.AllBinaryNoVibration;
import org.allbinary.media.AllBinaryVibration;
import org.allbinary.media.AllBinaryVibrationME;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;
import org.allbinary.media.graphics.geography.map.GeographicMapDirectionUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.view.ViewPosition;

/**
 *
 * @author user
 */
public class AdvancedRTSGameLayer extends RTSLayer
    implements DestroyedEventListenerInterface
{

    protected final ShakeAnimationListener shakeListener;
    protected final AllBinaryVibrationME vibration;
    protected final int duration;
    
    private PathFindingLayerInterface parentLayer = NullPathFindingLayer.NULL_PATH_FINDING_LAYER;
    //WaypointBehaviorBase
    protected TickableInterface waypointBehaviorBase = NullTickable.getInstance();
        
    public AdvancedRTSGameLayer(
            final RemoteInfo remoteInfo,
        final PathFindingLayerInterface parentLayer,
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
        this(
            remoteInfo,
            parentLayer,
                //StringUtil.getInstance().EMPTY_STRING, -1,
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
            rectangle, x, y, new TileLayerPositionIntoViewPosition());

    }
        
    public AdvancedRTSGameLayer(
            final RemoteInfo remoteInfo,
        final PathFindingLayerInterface parentLayer,
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
        final int x, final int y,
        final ViewPosition viewPosition)
        throws Exception
    {
        super(
                remoteInfo,
                //StringUtil.getInstance().EMPTY_STRING, -1,
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
            rectangle, x, y, viewPosition);

        this.setVisible(false);
        
        this.setWaypointBehavior(new WaypointBehaviorBase());

        this.shakeListener =
            ShakeAnimationListenerFactory.getInstance();

        this.vibration = AllBinaryVibration.getInstance();

        this.duration =
            GameConfigurationCentral.getInstance().VIBRATION.getValue().intValue() * 100;

        this.setParentLayer(parentLayer);
    }

    //used to simulate cost
    public AdvancedRTSGameLayer()
        throws Exception
    {
        super(RemoteInfo.REMOTE_INFO);

        this.setWaypointBehavior(new WaypointBehaviorBase());
        
        this.shakeListener = NoShakeAnimationListener.NO_SHAKE_ANIMATION_LISTENER;

        this.vibration = AllBinaryNoVibration.NO_VIBRATION;

        this.duration = 0;

        this.setParentLayer(NullPathFindingLayer.NULL_PATH_FINDING_LAYER);
    }

    @Override
    public void updateWaypointBehavior(final BasicGeographicMap geographicMapInterface) throws Exception {
        super.updateWaypointBehavior(geographicMapInterface);
        this.getWaypointBehavior().getWaypoint().setAllBinaryGameLayerManager(this.allBinaryGameLayerManagerP);
    }
    
    protected void initVisibility(
            RTSPlayerLayerInterface rtsPlayerLayerInterface)
    {
        AdvancedRTSPlayerLayerInterface advancedRTSPlayerLayerInterface = 
            (AdvancedRTSPlayerLayerInterface) rtsPlayerLayerInterface;

        if (advancedRTSPlayerLayerInterface.isLocalPlayer() || 
                advancedRTSPlayerLayerInterface.getGameType() == GameTypeFactory.getInstance() .BOT)
        {
            this.setVisible(true);
        } else
        {
            this.setVisible(false);
        }
    }

    @Override
    public void construct(RTSPlayerLayerInterface rtsPlayerLayerInterface)
        throws Exception
    {
        super.construct(rtsPlayerLayerInterface);

        this.initVisibility(rtsPlayerLayerInterface);
        
        BuildingEventHandler.getInstance().addListener(
                this.getWaypointBehavior().getWaypoint());
    }

    @Override
    public void setDestroyed(boolean destroyed)
        throws Exception
    {
        super.setDestroyed(destroyed);
        
        if (this.isDestroyed())
        {
            BuildingEventHandler.getInstance().removeListener(
                    this.getWaypointBehavior().getWaypoint());
            BuildingEventHandler.getInstance().removeListener(this);
            DestroyedEventHandler.getInstance().addListener(this);

            //Destroy owned waypoints as well
            RTSLayerUtil.getInstance().destroyAndClear(
                    this.getWaypointBehavior().getOwnedWaypointList());
        }
    }

    /**
     * @return the parentLayer
     */
    @Override
    public PathFindingLayerInterface getParentLayer()
    {
        return parentLayer;
    }

    public void setParentLayer(PathFindingLayerInterface parentLayer)
    {
        this.parentLayer = parentLayer;
    }

    @Override
    public WaypointBehaviorBase getWaypointBehavior()
    {
        return (WaypointBehaviorBase) this.waypointBehaviorBase;
    }

    protected void setWaypointBehavior(WaypointBehaviorBase unitWaypointHelper)
    {
        this.waypointBehaviorBase = unitWaypointHelper;
    }

    @Override
    public WaypointLogHelper getWaypointLogHelper() {
        return this.waypointLogHelperP;
    }

    @Override
    public Waypoint2LogHelper getWaypoint2LogHelper() {
        return this.waypoint2LogHelperP;
    }   
    
    @Override
    public WaypointRunnableLogHelper getWaypointRunnableLogHelper() {
        return this.waypointRunnableLogHelperP;
    }   

    @Override
    public boolean shouldAddWaypointFromBuilding() {
        if (this.parentLayer != NullPathFindingLayer.NULL_PATH_FINDING_LAYER) {
            final AdvancedRTSGameLayer parentAdvancedRTSGameLayer = (AdvancedRTSGameLayer) this.parentLayer;
            if(parentAdvancedRTSGameLayer.getType() != UnitLayer.getStaticType()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isWaypointListEmptyOrOnlyTargets() {
        if (this.getType() != UnitLayer.getStaticType()) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean buildingChase(final AllBinaryLayer allbinaryLayer, final GeographicMapCellPosition cellPosition)
    throws Exception
    {
        final GeographicMapCellPosition geographicMapCellPosition = 
            this.getCurrentGeographicMapCellPosition();
        //this.currentPathGeographicMapCellPosition
        
        final Direction buildingDirection = 
            GeographicMapDirectionUtil.getInstance().getDirectionFromCellPositionToAdjacentCellPosition(
                geographicMapCellPosition, cellPosition);

        final AngleInfo angleInfo = this.getRotationAnimationInterface().getAngleInfoP();
        
        final int angle = FrameUtil.getInstance().adjustAngleToFrameAngle(angleInfo.getAngle() + 90);
        
        final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
        
        if(buildingDirection == DirectionFactory.getInstance().UP)
        {
            this.rtsLayer2LogHelper.buildingAbove(this);
            
            //needs to go left or right
            
            //Probably going left
            //if(Math.abs(angle - Angle.RIGHT.getValue()) > Math.abs(angle - Angle.LEFT.getValue()))
            if(angle > 180 && angle < 360)
            {
                this.rtsLayer2LogHelper.buildingChaseLeft(this, angle);
                
                this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.LEFT));
                
                //if(angle < 225)
                if(angle <= 190)
                {
                    return true;
                }
            }
            else
              //Probably going right
            {
                this.rtsLayer2LogHelper.buildingChaseRight(this, angle);
                
                this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.RIGHT));
                
                //if(angle > 135)
                if(angle >= 170)
                {
                    return true;
                }
            }
        }
        else
            if(buildingDirection == DirectionFactory.getInstance().DOWN)
        {
            this.rtsLayer2LogHelper.buildingDown(this);
                //needs to go left or right

                //Probably going left
                if(angle > 180 && angle < 360)
                {
                    this.rtsLayer2LogHelper.buildingChaseLeft(this, angle);
                    
                    this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.RIGHT));
                    
                    if(angle > 315)
                    //if(angle >= 350)
                    {
                        return true;
                    }                
                    
                }
                else
                {
                    this.rtsLayer2LogHelper.buildingChaseRight(this, angle);
                    
                    this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.LEFT));
                    
                    if(angle < 45)
                    //if(angle <= 10)
                    {
                        return true;
                    }                
                    
                }
        }
            else
                if(buildingDirection == DirectionFactory.getInstance().LEFT)
            {
                this.rtsLayer2LogHelper.buildingLeft();
                  //needs to go up or down
                    
                    //Probably going down
                    if(angle > 90 && angle < 270)
                    {
                        this.rtsLayer2LogHelper.buildingChaseDown(this, angle);
                        
                        this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.LEFT));
                        
                        if(angle < 135)
                        //if(angle <= 100)
                        {
                            return true;
                        }
                    }
                    else
                      //Probably going up
                    {
                        this.rtsLayer2LogHelper.buildingChaseUp(this, angle);
                        
                        this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.RIGHT));
                        
                        if(angle > 45)
                        //if(angle >= 80)
                        {
                            return true;
                        }
                    }
            }
                else
                    if(buildingDirection == DirectionFactory.getInstance().RIGHT)
                {
                    this.rtsLayer2LogHelper.buildingRight();
                        
                      //needs to go up or down

                        //Probably going down
                        if(angle > 90 && angle < 270)
                        {
                            this.rtsLayer2LogHelper.buildingChaseDown(this, angle);
                            
                            this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.RIGHT));

                            if(angle > 225)
                            //if(angle >= 260)
                            {
                                return true;
                            }
                            
                        }
                        else
                          //Probably going up
                        {
                            this.rtsLayer2LogHelper.buildingChaseUp(this, angle);
                            
                            this.getGameKeyEventList().add(gameKeyEventFactory.getInstance(this, Canvas.LEFT));
                            
                            if(angle < 315 && angle > 180)
                            //if(angle <= 280 && angle > 180)
                            {
                                return true;
                            }
                        }
                }
                    else
                    {
                        return true;
                    }
        
        return false;
        
        /*
        
        if(this.isSelected())
        {
        logUtil.put(
                geographicMapCellPosition.toString() + " and " +  
                " path: " + this.getCurrentGeographicMapCellHistory().getTracked().toString(), 
                this, "buildingChase");
        }
        
        if (geographicMapCellPosition != SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION)
        {
            Direction pathDirection = DirectionFactory.getInstance().NO_DIRECTION;

            BasicArrayList list = this.getCurrentGeographicMapCellHistory().getTracked();
            
            int size = list.size();
            
            int geographicMapCellPositionIndex = list.indexOf(geographicMapCellPosition);

            if(geographicMapCellPositionIndex == -1)
            {
                //If path has not started
                if(this.getCurrentGeographicMapCellHistory().getFirstUnvisitedIndex() == 0 && size > 1)
                {
                    geographicMapCellPositionIndex = 0;
                    
                    pathDirection = GeographicMapDirectionUtil
                        .getEightDirectionFromCellPositionToCellPosition(
                                geographicMapCellPosition,
                            (GeographicMapCellPosition) list.get(1));
                }
                //Well this means that the path already started but not on path so give up
                else
                {
                    //this.getCurrentGeographicMapCellHistory().getFirstUnvisited();
                    if(this.isSelected())
                    {
                        logUtil.put("Not handled", this, "buildingChase");
                    }
                    
                    return;
                }
            }
            else
            if(geographicMapCellPositionIndex > 0)
            {
                geographicMapCellPositionIndex--;
            }

            int index = 0;
            
            while((pathDirection == DirectionFactory.getInstance().NO_DIRECTION ||
                    pathDirection == DirectionFactory.getInstance().UP || 
                    pathDirection == DirectionFactory.getInstance().DOWN ||
                    pathDirection == DirectionFactory.getInstance().LEFT ||
                    pathDirection == DirectionFactory.getInstance().RIGHT) &&
                    geographicMapCellPositionIndex + index + 2 < size)
            {
                pathDirection = GeographicMapDirectionUtil
                    .getEightDirectionFromCellPositionToCellPosition(
                            (GeographicMapCellPosition) list.get(geographicMapCellPositionIndex + index),
                            (GeographicMapCellPosition) list.get(geographicMapCellPositionIndex + index + 2));

                index++;
            }

            if(this.isSelected())
            {
            logUtil.put(
                    "Direction Around Building path: " + pathDirection.toString(), 
                    this, "buildingChase");
            }
            
            if (pathDirection == DirectionFactory.getInstance().DOWN_LEFT
                    || pathDirection == DirectionFactory.getInstance().DOWN_RIGHT)
            {
                if(this.isSelected())
                {
                logUtil.put(
                        "Moving Around Building to the Right", this, "buildingChase");
                }
                this
                        .getGameKeyEventList()
                        .add(gameKeyEventFactory.getInstance(
                                this, Canvas.RIGHT));

            } else if (pathDirection == DirectionFactory.getInstance().UP_LEFT
                    || pathDirection == DirectionFactory.getInstance().UP_RIGHT)
            {
                if(this.isSelected())
                {                
                logUtil.put(
                        "Moving Around Building to the Left", this, "buildingChase");
                }

                this
                .getGameKeyEventList()
                .add(gameKeyEventFactory.getInstance(
                        this, Canvas.LEFT));
            } else if (pathDirection == DirectionFactory.getInstance().LEFT)
            {
                this
                .getGameKeyEventList()
                .add(gameKeyEventFactory.getInstance(
                        this, Canvas.RIGHT));
                
            } else if (pathDirection == DirectionFactory.getInstance().RIGHT)
            {
                this
                .getGameKeyEventList()
                .add(gameKeyEventFactory.getInstance(
                        this, Canvas.LEFT));
            }
        
        }
        */
    }
    
    @Override
    public BasicArrayList getSurroundingGeographicMapCellPositionList() throws Exception {
        final GeographicMapCompositeInterface geographicMapCompositeInterface
            = (GeographicMapCompositeInterface) this.allBinaryGameLayerManagerP;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];

        this.geographicMapCellPositionAreaBase.update(geographicMapInterface);

        return this.geographicMapCellPositionAreaBase.getSurroundingGeographicMapCellPositionList();
    }
    
    @Override
    protected void setSelected(boolean selected)
    {
        super.setSelected(selected);
        
        if(this.debug) {
            if(selected) {
                this.waypointLogHelperP = WaypointSelectedLogHelper.getInstance();
                this.waypoint2LogHelperP = Waypoint2SelectedLogHelper.getInstance();
                this.waypointRunnableLogHelperP = WaypointRunnableSelectedLogHelper.getInstance();
            } else {
                this.waypointLogHelperP = WaypointLogHelper.getInstance();
                this.waypoint2LogHelperP = Waypoint2LogHelper.getInstance();
                this.waypointRunnableLogHelperP = WaypointRunnableLogHelper.getInstance();
            }
        }
    }
    
    @Override
    public void onDestroyed(DestroyedEvent destroyedEvent)
    {
        try
        {
            AllBinaryLayer layerInterface = destroyedEvent.getLayerInterface();
            if (layerInterface == this)
            {
                DestroyedEventHandler.getInstance().removeListener(this);
                
                WaypointBase waypoint = (WaypointBase) this.getWaypointBehavior().getWaypoint();
                
                waypoint.reset();
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "onDestroyed", e);
        }
    }
}
