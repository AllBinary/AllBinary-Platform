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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.game.layer.AdvancedPlayerOwnedRTSLayers;
import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.AdvancedRTSPlayerLayerInterface;
import org.allbinary.game.layer.RTSGameStrings;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.layer.capital.Capital;
import org.allbinary.game.layer.unit.UnitLayer;
import org.allbinary.game.layer.waypoint.WorkWaypoint;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.media.audio.BuildingSound;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.GeographicMapCellPositionAreaBase;
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
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;

/**
 * 
 * @author user
 */
public class UnitRTSFormInput extends RTSFormInput
{

    public static final Integer DECAL_ID = 
        SmallIntegerSingletonFactory.getInstance().getInstance(23);

    protected final GameNotificationEvent noMoneyGameNotificationEvent;
    protected final GameNotificationEvent newUnitGameNotificationEvent;
    
    public UnitRTSFormInput(final Group[] groupInterface)
    {
        super(groupInterface);
        
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();

        this.noMoneyGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    RTSGameStrings.getInstance().NO_MONEY,
                    smallIntegerSingletonFactory.getInstance(2),
                    basicColorFactory.WHITE,
                    BooleanFactory.getInstance().FALSE);
        
        this.newUnitGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    RTSGameStrings.getInstance().NEW_UNIT,
                    smallIntegerSingletonFactory.getInstance(2),
                    basicColorFactory.WHITE,
                    BooleanFactory.getInstance().FALSE);        
        
        //Sets the default working load
        this.getHashtable().put(WorkWaypoint.ID, 
                smallIntegerSingletonFactory.getInstance(50));
    }

    @Override
    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);
        
        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        this.noMoneyGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.newUnitGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());

    }
    
    public void process(final RTSLayer associatedRtsLayer,
            final RTSPlayerLayerInterface rtsPlayerLayerInterface, 
            final AllBinaryLayerManager layerManager,
            final CustomItem item, final int itemIndex) throws Exception
    {   
        super.process(layerManager);
   
        final GeographicMapCellPositionAreaBase geographicMapCellPositionArea = 
            associatedRtsLayer.geographicMapCellPositionAreaBase;
        
        final GeographicMapCellPosition geographicMapCellPosition = geographicMapCellPositionArea
                .getNextSurroundingGeographicMapCellPosition();

        this.getHashtable().put(Layer.ID, associatedRtsLayer);

        this.getHashtable().put(UnitRTSFormInput.DECAL_ID, 
                ((AdvancedRTSPlayerLayerInterface) rtsPlayerLayerInterface).getDecalBasicColor());
        
        if(this.newUnconstructedRTSLayerInterfaceArray[itemIndex] == CollidableDestroyableDamageableLayer.NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER)
        {
            this.newUnconstructedRTSLayerInterfaceArray[itemIndex] = 
                this.getInstance(layerManager, item, geographicMapCellPosition);
        }
        else
        {
            //update area and position            
            GPoint cellPoint = geographicMapCellPosition.getPoint();

            final RTSLayer rtsLayer = (RTSLayer) this.newUnconstructedRTSLayerInterfaceArray[itemIndex];
            rtsLayer.setPosition(
                    cellPoint.getX(), cellPoint.getY(), 
                    rtsLayer.getZP());

            final GeographicMapCompositeInterface geographicMapCompositeInterface
                = (GeographicMapCompositeInterface) rtsLayer.allBinaryGameLayerManagerP;
            final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
            
            rtsLayer.geographicMapCellPositionAreaBase.update(geographicMapInterface);
        }

        // Move unit to center
        final GPoint cellPoint = geographicMapCellPosition.getMidPoint();

        final RTSLayer rtsLayer = (RTSLayer) this.newUnconstructedRTSLayerInterfaceArray[itemIndex];
        rtsLayer.setPosition(
                cellPoint.getX() - rtsLayer.getHalfWidth(), 
                cellPoint.getY() - rtsLayer.getHalfHeight(),
                rtsLayer.getZP());

        //this.newUnconstructedRTSLayerInterface.geographicMapCellPositionArea.update();
        
        this.attemptBuild(associatedRtsLayer, rtsPlayerLayerInterface, layerManager,
                (RTSLayer) rtsLayer, itemIndex);
    }

    private void attemptBuild(final RTSLayer associatedRtsLayer,
            final RTSPlayerLayerInterface rtsPlayerLayerInterface, 
            final AllBinaryLayerManager layerManager,
            final RTSLayer layerInterface, final int itemIndex) throws Exception
    {
        final int cost = layerInterface.getCost();

        final Capital capital = rtsPlayerLayerInterface.getCapital();

        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Trying to Build: ");
        stringBuffer.append(layerInterface.getName());
        stringBuffer.append(" for: $");
        stringBuffer.append(cost);
        stringBuffer.append(" with ");
        stringBuffer.append(capital.getTotalMoney());

        logUtil.put(stringBuffer.toString(), this, "attemptBuild");        

        if (cost <= capital.getTotalMoney())
        {
            layerInterface.construct(rtsPlayerLayerInterface);
            
            this.newUnconstructedRTSLayerInterfaceArray[itemIndex] = CollidableDestroyableDamageableLayer.NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER;

            rtsPlayerLayerInterface.add(BuildingSound.getInstance());

            capital.removeMoney(cost);

            // Add Building waypoint to new unit
            AssignWaypointsUtil.getInstance().set(
                    (UnitLayer) layerInterface,
                    (AdvancedRTSGameLayer) associatedRtsLayer);

            // layerManager.append(layerInterface);
            layerManager.append(layerInterface, PlayersSingletonFactory.total);

            final AdvancedRTSPlayerLayerInterface advancedRTSPlayerLayerInterface = 
                (AdvancedRTSPlayerLayerInterface) rtsPlayerLayerInterface;
            
            final AdvancedPlayerOwnedRTSLayers advancedPlayerOwnedRTSLayers =
                advancedRTSPlayerLayerInterface.getAdvancedPlayerOwnedRTSLayers();
            
            advancedPlayerOwnedRTSLayers.addUnit(layerInterface);

            if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
            {
                GameNotificationEventHandler.getInstance().fireEvent(
                        newUnitGameNotificationEvent);
            }

        } else
        {
            // Should probably replace this with not throwing it away
            //layerInterface.setDestroyed(true);

            rtsPlayerLayerInterface.add(ErrorSound.getInstance());

            if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
            {
                GameNotificationEventHandler.getInstance().fireEvent(
                        noMoneyGameNotificationEvent);
            }
        }
    }
}
