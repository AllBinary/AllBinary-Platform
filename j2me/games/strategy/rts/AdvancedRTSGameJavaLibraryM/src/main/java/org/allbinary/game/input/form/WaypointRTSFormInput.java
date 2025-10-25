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
package org.allbinary.game.input.form;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.game.input.RTSPlayerGameInput;
import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.AdvancedRTSPlayerLayerInterface;
import org.allbinary.game.layer.RTSGameStrings;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.game.layer.RTSLayerUtil;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.layer.capital.Capital;
import org.allbinary.game.layer.waypoint.WaypointCellPositionHistory;
import org.allbinary.game.layer.waypoint.WaypointLayer;
import org.allbinary.game.layer.waypoint.event.WaypointEventHandlerFactory;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.util.BasicArrayList;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.layer.GeographicMapCellPositionAreaBase;
import org.allbinary.game.layer.NullRTSLayer;
import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.Layer;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.audio.ErrorSound;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionHistory;
import org.allbinary.media.graphics.geography.map.racetrack.RaceTrackGeographicMapCellTypeFactory;

/**
 *
 * @author user
 */
public class WaypointRTSFormInput extends RTSFormInput
{

    private final RTSLayerEvent WAYPOINT_EVENT = new RTSLayerEvent(NullRTSLayer.NULL_RTS_LAYER);

    protected final GameNotificationEvent dragToSpotGameNotificationEvent;
    protected final GameNotificationEvent spotTakenGameNotificationEvent;
    protected final GameNotificationEvent buildingCollisionGameNotificationEvent;
    protected final GameNotificationEvent roadCollisionGameNotificationEvent;
    protected final GameNotificationEvent newWaypointGameNotificationEvent;

    protected final GameNotificationEvent noMoneyGameNotificationEvent;
    
    private boolean isPrimaryWaypointCreator;
    
    public WaypointRTSFormInput(
        final Group[] groupInterface,
        final boolean isPrimaryWaypointCreator)
    {
        super(groupInterface);

        this.isPrimaryWaypointCreator = isPrimaryWaypointCreator;
        
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
        
        this.dragToSpotGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    RTSGameStrings.getInstance().DRAG_TO_SPOT,
                    smallIntegerSingletonFactory.getInstance(2),
                    basicColorFactory.WHITE,
                    BooleanFactory.getInstance().FALSE);

        this.spotTakenGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    RTSGameStrings.getInstance().SPOT_TAKEN,
                    smallIntegerSingletonFactory.getInstance(2),
                    basicColorFactory.WHITE,
                    BooleanFactory.getInstance().FALSE);

        this.buildingCollisionGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    RTSGameStrings.getInstance().BUILDING_COLLISION,
                    smallIntegerSingletonFactory.getInstance(2),
                    basicColorFactory.WHITE,
                    BooleanFactory.getInstance().FALSE);

        this.roadCollisionGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    RTSGameStrings.getInstance().ROAD_COLLISION, 
                    smallIntegerSingletonFactory.getInstance(2),
                    basicColorFactory.WHITE,
                    BooleanFactory.getInstance().FALSE);

        this.newWaypointGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    RTSGameStrings.getInstance().NEW_WAYPOINT,  
                    smallIntegerSingletonFactory.getInstance(2),
                    basicColorFactory.WHITE,
                    BooleanFactory.getInstance().FALSE);
        
        this.noMoneyGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    RTSGameStrings.getInstance().NO_MONEY,
                    smallIntegerSingletonFactory.getInstance(2),
                    basicColorFactory.WHITE,
                    BooleanFactory.getInstance().FALSE);
        
    }

    @Override
    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {
    
        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);
        
        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        this.dragToSpotGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.spotTakenGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.buildingCollisionGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.roadCollisionGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.newWaypointGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.noMoneyGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        
    }
    
    public void process(
        final RTSLayer associatedRtsLayer,
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final AllBinaryLayerManager layerManager, final GPoint point)
        throws Exception
    {

        super.process(layerManager);
        
        if (this.isStickyItemSelected() ||
            associatedRtsLayer == null)
        {
            if (this.isStickyItemSelected() &&
                associatedRtsLayer == null)
            {

                final GeographicMapCompositeInterface geographicMapCompositeInterface
                    = (GeographicMapCompositeInterface) layerManager;
                final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];

                final AllBinaryTiledLayer allBinaryTiledLayer =
                    geographicMapInterface.getAllBinaryTiledLayer();

                int x = point.getX() + allBinaryTiledLayer.getXP();
                int y = point.getY() + allBinaryTiledLayer.getYP();

                final GeographicMapCellPosition geographicMapCellPosition =
                    geographicMapInterface.getCellPositionAt(x, y);

                this.process(
                    rtsPlayerLayerInterface,
                    layerManager,
                    geographicMapCellPosition);
            }
            else
            {
                if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
                {
                    GameNotificationEventHandler.getInstance().fireEvent(
                            dragToSpotGameNotificationEvent);
                }                
            }

        }
        else
        {
            if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
            {
                GameNotificationEventHandler.getInstance().fireEvent(
                        spotTakenGameNotificationEvent);
            }
        }
    }

    private void process(
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final AllBinaryLayerManager layerManager,
        final GeographicMapCellPosition geographicMapCellPosition)
        throws Exception
    {
        int itemIndex = this.getSelectedStickyItemIndex();
        if(this.newUnconstructedRTSLayerInterfaceArray[itemIndex] == CollidableDestroyableDamageableLayer.NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER)
        {
            this.newUnconstructedRTSLayerInterfaceArray[itemIndex] = 
                this.getInstance(layerManager, this.getSelectedStickyItem(), geographicMapCellPosition);
        }
        else
        {
            //update area and position            
            //GPoint cellPoint = geographicMapCellPosition.getMidPoint();
            final GPoint cellPoint = geographicMapCellPosition.getPoint();

            final RTSLayer rtsLayer = (RTSLayer) this.newUnconstructedRTSLayerInterfaceArray[itemIndex];
            rtsLayer.setPosition(cellPoint.getX(), cellPoint.getY(),rtsLayer.getZP());

            final GeographicMapCompositeInterface geographicMapCompositeInterface
                = (GeographicMapCompositeInterface) rtsLayer.allBinaryGameLayerManagerP;
            final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
            
            rtsLayer.geographicMapCellPositionAreaBase.update(geographicMapInterface);
        }
        
        this.attemptBuild(
            rtsPlayerLayerInterface,
            layerManager,
            (RTSLayer) this.newUnconstructedRTSLayerInterfaceArray[itemIndex], itemIndex);
    }
    private RTSLayer stickyAssociatedRtsLayer;

    public void processSticky(
        final RTSLayer associatedRtsLayer,
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final AllBinaryLayerManager layerManager, CustomItem item, int index)
        throws Exception
    {
        logUtil.put(
            "Set Sticking Item: " + item, this, "processSticky");

        this.setSelectedStickyItem(item);
        this.setSelectedStickyItemIndex(index);
        this.setStickyItemSelected(true);
        this.stickyAssociatedRtsLayer = associatedRtsLayer;
        this.getHashtable().put(Layer.ID, associatedRtsLayer);
    }
    
    protected boolean attemptBuild(
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final AllBinaryLayerManager layerManager, final RTSLayer layerInterface, final int itemIndex) 
    throws Exception
    {
        logUtil.put("Layer: " + layerInterface, this, "attemptBuild");

        if(layerInterface == null) {
            logUtil.put("Layer was null", this, "attemptBuild", new Exception());
            return false;
        }
        
        GeographicMapCellPositionAreaBase geographicMapCellPositionArea =
            layerInterface.geographicMapCellPositionAreaBase;

        BasicArrayList list =
            geographicMapCellPositionArea.getOccupyingGeographicMapCellPositionList();

        //logUtil.put("List: " + list, this, "attemptBuild");

        if (DropCellPositionHistory.getInstance().anyCellPositionWithDrop(list) ||
            WaypointCellPositionHistory.getInstance().anyCellPositionWithDrop(list))
        {
            rtsPlayerLayerInterface.add(ErrorSound.getInstance());

            if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
            {
                GameNotificationEventHandler.getInstance().fireEvent(
                        buildingCollisionGameNotificationEvent);
            }

            return false;
        }
        /*
        if (DropCellPositionHistory.getInstance().anyCellPositionWithDrop(
        geographicMapCellPositionArea.getSurroundingGeographicMapCellPositionList()))
        {
        rtsPlayerLayerInterface.add(ErrorSound.getInstance());

        rtsPlayerLayerInterface.getGameNotificationHud().add(
        RTSGameStrings.STRUCTURE_TO_CLOSE, smallIntegerSingletonFactory.getInstance(2),
        this.geographicMapInterface.getForegroundBasicColor(), Boolean.FALSE);
        return;
        }
         */
        /*
        else
        {
        if (this.isSurroundingCellsOffMap(layerInterface))
        {
        rtsPlayerLayerInterface.add(ErrorSound.getInstance());

        rtsPlayerLayerInterface.getGameNotificationHud().add(
        RTSGameStrings.MAP_EDGE, smallIntegerSingletonFactory.getInstance(2),
        this.geographicMapInterface.getForegroundBasicColor(), Boolean.FALSE);
        return;
        }
        }
         */

        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) layerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];

        final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
            (RaceTrackGeographicMapCellTypeFactory) geographicMapInterface.getGeographicMapCellTypeFactory();
        
        GeographicMapCellType geographicMapCellType;
        
        for (int index = list.size(); --index >= 0;)
        {
            geographicMapCellType = geographicMapInterface.getCellTypeAt(
                (GeographicMapCellPosition) list.get(index));
            
            if (raceTrackGeographicMapCellTypeFactory.isPath(geographicMapCellType))
            {
                rtsPlayerLayerInterface.add(ErrorSound.getInstance());

                if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
                {
                    GameNotificationEventHandler.getInstance().fireEvent(
                            roadCollisionGameNotificationEvent);
                }

                return false;
            }
        }

        int cost = layerInterface.getCost();

        Capital capital = rtsPlayerLayerInterface.getCapital();

        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Trying to Build: ");
        stringBuffer.append(layerInterface.getName());
        stringBuffer.append(" for: $");
        stringBuffer.append(cost);
        stringBuffer.append(" with ");
        stringBuffer.append(capital.getTotalMoney());

        logUtil.put(
                stringBuffer.toString(), this, "attemptBuild");

        if (cost <= capital.getTotalMoney())
        {
            layerInterface.construct(rtsPlayerLayerInterface);
            
            newUnconstructedRTSLayerInterfaceArray[itemIndex] = CollidableDestroyableDamageableLayer.NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER;

            capital.removeMoney(cost);

            WaypointCellPositionHistory.getInstance().add(list, layerInterface);

            final GeographicMapCellPosition geographicMapCellPosition =
                geographicMapInterface.getCellPositionAt(
                layerInterface.getXP(), layerInterface.getYP());

            final WaypointLayer waypointLayer = (WaypointLayer) layerInterface;

            this.addWayPoint(waypointLayer);

            final RTSPlayerGameInput rtsPlayerGameInput = (RTSPlayerGameInput) 
            rtsPlayerLayerInterface.getPlayerGameInput();

            rtsPlayerGameInput.setSelectedRTSLayer(
                layerInterface, geographicMapCellPosition);

            final AdvancedRTSPlayerLayerInterface advancedRTSPlayerLayerInterface = 
                (AdvancedRTSPlayerLayerInterface) rtsPlayerLayerInterface;

            advancedRTSPlayerLayerInterface.getAdvancedPlayerOwnedRTSLayers().addWaypoint(layerInterface);

            //Don't add/show it if it is a throw away waypoint
            //if(this.stickyAssociatedRtsLayer.getType() != UnitLayer.getStaticType())
            layerManager.append(layerInterface);

            rtsPlayerLayerInterface.add(
                    ((AdvancedRTSGameLayer) waypointLayer
                            ).getWaypointBehavior().getWaypoint().getSound());

            if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
            {
                GameNotificationEventHandler.getInstance().fireEvent(
                        newWaypointGameNotificationEvent);
            }

            return true;
        }
        else
        {
            rtsPlayerLayerInterface.add(ErrorSound.getInstance());

            if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
            {
                GameNotificationEventHandler.getInstance().fireEvent(
                        noMoneyGameNotificationEvent);
            }

            return false;
        }
    }

    /*
    private boolean isSurroundingCellsOffMap(RTSLayer layerInterface)
    {
        GeographicMapCellPositionArea geographicMapCellPositionArea =
            layerInterface.geographicMapCellPositionArea;

        BasicArrayList occupyList =
            geographicMapCellPositionArea.getOccupyingGeographicMapCellPositionList();

        BasicArrayList surroundList =
            geographicMapCellPositionArea.getSurroundingGeographicMapCellPositionList();

        //surround should be 4*sqrt(occupy) + 4 or 4*side + 4
        //Table
        //sides cells surround
        //1 1 8
        //2 4 12
        //3 9 16
        //4 16 20

        int occupySize = occupyList.size();
        int surroundSize = surroundList.size();

        logUtil.put(
            "occupySize: " + occupySize +
            " surroundSize: " + surroundSize +
            " surroundList: " + surroundList,
            this, "isSurroundingCellsOffMap");

        boolean isSurroundOffMap = false;
        if (occupySize == 1 && surroundSize != 8)
        {
            isSurroundOffMap = true;
        }
        else if (occupySize == 4 && surroundSize != 12)
        {
            isSurroundOffMap = true;
        }

        if (occupySize == 0 || isSurroundOffMap)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    */

    private void addWayPoint(final WaypointLayer layerInterface)
        throws Exception
    {
        //Currently only one waypoint owned per layer
        final BasicArrayList list =
            ((AdvancedRTSGameLayer) stickyAssociatedRtsLayer).getWaypointBehavior().getOwnedWaypointList();

        RTSLayerUtil.getInstance().destroyAndClear(list);

        //Add new waypoint to owner
        list.add(layerInterface);

        if (this.isPrimaryWaypointCreator)
        {
            PrimaryWaypointHelper.getInstance().setWaypointLayer(
                layerInterface);
        }

        this.WAYPOINT_EVENT.setRtsLayer(layerInterface);

        WaypointEventHandlerFactory.getInstance(
            layerInterface.getGroupInterface()[0]).fireEvent(
            this.WAYPOINT_EVENT);

    }
}
