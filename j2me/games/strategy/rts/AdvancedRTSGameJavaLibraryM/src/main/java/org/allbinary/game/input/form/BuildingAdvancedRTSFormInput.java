/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 */
package org.allbinary.game.input.form;

import org.allbinary.game.layer.AdvancedPlayerOwnedRTSLayers;
import org.allbinary.game.layer.AdvancedRTSPlayerLayerInterface;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.layer.building.event.BuildingEventHandler;
import org.allbinary.game.layer.building.event.LocalPlayerBuildingEventHandler;
import org.allbinary.game.layer.waypoint.WaypointCellPositionHistory;

import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.audio.ErrorSound;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionHistory;

/**
 *
 * @author user
 */
public class BuildingAdvancedRTSFormInput
    extends BuildingRTSFormInput
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected final GameNotificationEvent atLeastOneHouseGameNotificationEvent;
    
    public BuildingAdvancedRTSFormInput(
        final Group[] groupInterface,
        final boolean isUnitProducer) 
    throws Exception
    {
        super(groupInterface, isUnitProducer);
        
        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
        
        this.atLeastOneHouseGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    "Build At Least One House First",
                    SmallIntegerSingletonFactory.getInstance().getInstance(2),
                    basicColorFactory.WHITE,
                    BooleanFactory.getInstance().FALSE);
    }

    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);
        
        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        this.atLeastOneHouseGameNotificationEvent.setBasicColor(geographicMapInterface.getForegroundBasicColor());
    }
    
    public boolean isPositionBlocked()
    {
        if(DropCellPositionHistory.getInstance().isCellPositionWithDrop(
            this.getSelectedGeographicCellPosition()) ||
            WaypointCellPositionHistory.getInstance().isCellPositionWithDrop(
            this.getSelectedGeographicCellPosition())
            )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    protected boolean isBuildAttemptValid(
            RTSPlayerLayerInterface rtsPlayerLayerInterface, 
            RTSLayer layerInterface) 
        throws Exception
    {
        AdvancedRTSPlayerLayerInterface advancedRTSPlayerLayerInterface = 
            (AdvancedRTSPlayerLayerInterface) rtsPlayerLayerInterface;
        
        AdvancedPlayerOwnedRTSLayers advancedPlayerOwnedRTSLayers = 
            advancedRTSPlayerLayerInterface.getAdvancedPlayerOwnedRTSLayers();
        
        int totalHouses = advancedPlayerOwnedRTSLayers.getHouseList().size();
        
        //PreLogUtil.put("Total Houses: " + totalHouses + " " + (layerInterface.getType() != HouseBuildingLayer.getStaticType()), this, "isBuildAttemptValid");

        if(totalHouses < 1 && layerInterface.getName().indexOf(" House") < 0)
        {
            rtsPlayerLayerInterface.add(ErrorSound.getInstance());

            if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
            {
                GameNotificationEventHandler.getInstance().fireEvent(
                        atLeastOneHouseGameNotificationEvent);
            }

            return false;
        }

        return super.isBuildAttemptValid(
                rtsPlayerLayerInterface, layerInterface);
    }

    private final RTSLayerEvent BUILD_BUILDING_RTS_LAYER_EVENT = new RTSLayerEvent(null);
    
    protected void add(
            RTSPlayerLayerInterface rtsPlayerLayerInterface,
        AllBinaryLayerManager layerManager, RTSLayer layerInterface)
        throws Exception
    {
        //layerInterface
        BUILD_BUILDING_RTS_LAYER_EVENT.setRtsLayer(layerInterface);

        BuildingEventHandler.getInstance().fireEvent(BUILD_BUILDING_RTS_LAYER_EVENT);
        
        if(((AdvancedRTSPlayerLayerInterface) rtsPlayerLayerInterface).isLocalPlayer())
        {
            LocalPlayerBuildingEventHandler.getInstance().fireEvent(BUILD_BUILDING_RTS_LAYER_EVENT);
        }

        super.add(
            rtsPlayerLayerInterface,
            layerManager,
            layerInterface);
    }
}
