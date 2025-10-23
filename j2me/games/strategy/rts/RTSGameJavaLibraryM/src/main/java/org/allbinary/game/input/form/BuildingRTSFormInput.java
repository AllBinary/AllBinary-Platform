/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

import org.allbinary.game.input.RTSPlayerGameInput;
import org.allbinary.game.layer.DemoLockedWithCostLayerInterfaceFactoryInterface;
import org.allbinary.game.layer.GeographicMapCellPositionAreaBase;
import org.allbinary.game.layer.RTSGameStrings;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.layer.capital.Capital;
import org.allbinary.game.layer.item.LayerInterfaceFactoryImageItem;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.media.audio.BuildingSound;
import org.allbinary.util.BasicArrayList;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.system.security.licensing.LockedFeatureNotificationUtil;
import org.allbinary.logic.system.security.licensing.LockedUtil;
import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.StringUtil;
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
public class BuildingRTSFormInput extends RTSFormInput
{

    private boolean isUnitProducer;

    private final GameNotificationEvent buildOnPathGameNotificationEvent;
    private final GameNotificationEvent spotTakenGameNotificationEvent;
    private final GameNotificationEvent selectBuildSpotGameNotificationEvent;
    private final GameNotificationEvent roadCollisionGameNotificationEvent;
    private final GameNotificationEvent noMoneyGameNotificationEvent;
    private final GameNotificationEvent buildingCollisionGameNotificationEvent;
    private final GameNotificationEvent structureToCloseCollisionGameNotificationEvent;
    private final GameNotificationEvent buildingGameNotificationEvent;
    private final GameNotificationEvent mapEdgeGameNotificationEvent;

    private final DropCellPositionHistory dropCellPositionHistory = DropCellPositionHistory.getInstance();
    public BuildingRTSFormInput(
        final Group[] groupInterface,
        final boolean isUnitProducer)
    throws Exception
    {
        super(groupInterface);

        this.isUnitProducer = isUnitProducer;

        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();

        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
        
        this.buildOnPathGameNotificationEvent =
                new GameNotificationEvent(
                this,
                RTSGameStrings.getInstance().BUILD_ON_PATH,
                smallIntegerSingletonFactory.getInstance(2),
                basicColorFactory.PINK,
                BooleanFactory.getInstance().FALSE);

        this.spotTakenGameNotificationEvent =
                new GameNotificationEvent(
                this,
                RTSGameStrings.getInstance().SPOT_TAKEN,
                smallIntegerSingletonFactory.getInstance(2),
                basicColorFactory.PINK,
                BooleanFactory.getInstance().FALSE);

        this.selectBuildSpotGameNotificationEvent =
                new GameNotificationEvent(
                this,
                RTSGameStrings.getInstance().SELECT_BUILD_SPOT,
                smallIntegerSingletonFactory.getInstance(2),
                basicColorFactory.PINK,
                BooleanFactory.getInstance().FALSE);

        this.roadCollisionGameNotificationEvent =
                new GameNotificationEvent(
                this,
                RTSGameStrings.getInstance().ROAD_COLLISION,
                smallIntegerSingletonFactory.getInstance(2),
                basicColorFactory.PINK,
                BooleanFactory.getInstance().FALSE);

        this.noMoneyGameNotificationEvent =
                new GameNotificationEvent(
                this,
                RTSGameStrings.getInstance().NO_MONEY,
                smallIntegerSingletonFactory.getInstance(2),
                basicColorFactory.PINK,
                BooleanFactory.getInstance().FALSE);

        this.buildingCollisionGameNotificationEvent =
                new GameNotificationEvent(
                this,
                RTSGameStrings.getInstance().BUILDING_COLLISION,
                smallIntegerSingletonFactory.getInstance(2),
                basicColorFactory.PINK,
                BooleanFactory.getInstance().FALSE);

        this.structureToCloseCollisionGameNotificationEvent =
                new GameNotificationEvent(
                this,
                RTSGameStrings.getInstance().STRUCTURE_TO_CLOSE,
                smallIntegerSingletonFactory.getInstance(2),
                basicColorFactory.PINK,
                BooleanFactory.getInstance().FALSE);

        this.buildingGameNotificationEvent =
                new GameNotificationEvent(
                this,
                RTSGameStrings.getInstance().BUILDING,
                smallIntegerSingletonFactory.getInstance(2),
                basicColorFactory.PINK,
                BooleanFactory.getInstance().FALSE);

        this.mapEdgeGameNotificationEvent =
                new GameNotificationEvent(
                this,
                RTSGameStrings.getInstance().MAP_EDGE,
                smallIntegerSingletonFactory.getInstance(2),
                basicColorFactory.PINK,
                BooleanFactory.getInstance().FALSE);
        
    }

    @Override
    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);
    
        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        this.buildOnPathGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.spotTakenGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.selectBuildSpotGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.roadCollisionGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.noMoneyGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.buildingCollisionGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.structureToCloseCollisionGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.buildingGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.mapEdgeGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        
    }

    @Override
    public void process(
        final CollidableDestroyableDamageableLayer associatedRtsLayer,
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final AllBinaryLayerManager layerManager, final CustomItem item, final int itemIndex)
        throws Exception
    {
        super.process(layerManager);
        
        this.buildFromMotionInput(
            rtsPlayerLayerInterface, layerManager, item, itemIndex);
    }

    public boolean isPositionBlocked()
    {
        return dropCellPositionHistory.isCellPositionWithDrop(
                this.getSelectedGeographicCellPosition());
    }

    private boolean isDemoLocked(final CustomItem item)
    {
        final LayerInterfaceFactoryImageItem factoryItem =
            (LayerInterfaceFactoryImageItem) item;

        final DemoLockedWithCostLayerInterfaceFactoryInterface layerInterfaceFactoryInterface =
            (DemoLockedWithCostLayerInterfaceFactoryInterface) factoryItem
                .getLayerInterfaceFactoryInterface();

        if (LockedUtil.getInstance().isLockedFeature() &&
                layerInterfaceFactoryInterface.isDemoLocked())
        {
            return true;
        } else
        {
            return false;
        }
    }

    public void buildFromMotionInput(
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final AllBinaryLayerManager layerManager, final CustomItem item, final int itemIndex)
        throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(commonStrings.START, this, "buildFromMotionInput");

        if(layerManager == null) {
            throw new RuntimeException();
        }
        
        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) layerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        if(this.isDemoLocked(item))
        {
            rtsPlayerLayerInterface.add(ErrorSound.getInstance());

            LockedFeatureNotificationUtil.getInstance().fire(
                    geographicMapInterface.getForegroundBasicColor());

            return;
        }

        // If no tower is selected try to build if tower is selected
        final GeographicMapCellPosition geographicMapCellPosition =
            this.getSelectedGeographicCellPosition();
        if (geographicMapCellPosition != null)
        {
            // if (this.modifySelectedPlayerGameInput.getSelectedLayer() ==
            // null)
            if (!this.isPositionBlocked())
            {
                final GeographicMapCellType geographicMapCellType = geographicMapInterface.getCellTypeAt(geographicMapCellPosition);

                final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
                    (RaceTrackGeographicMapCellTypeFactory) geographicMapInterface.getGeographicMapCellTypeFactory();
                
                if (!raceTrackGeographicMapCellTypeFactory.isPath(geographicMapCellType))
                {
                    if(this.newUnconstructedRTSLayerInterfaceArray[itemIndex] == CollidableDestroyableDamageableLayer.NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER)
                    {
                        this.newUnconstructedRTSLayerInterfaceArray[itemIndex] =
                            this.getInstance(layerManager, item, geographicMapCellPosition);
                    }
                    else
                    {
                        final RTSLayer rtsLayer = (RTSLayer) this.newUnconstructedRTSLayerInterfaceArray[itemIndex];
                        
                        //update area and position
                        final GPoint cellPoint = geographicMapCellPosition.getPoint();

                        rtsLayer.setPosition(cellPoint.getX(), cellPoint.getY(), cellPoint.getZ());

                        rtsLayer.geographicMapCellPositionAreaBase.update(geographicMapInterface);
                    }

                    final RTSLayer rtsLayer = (RTSLayer) this.newUnconstructedRTSLayerInterfaceArray[itemIndex];
                    
                    this.attemptBuild(rtsPlayerLayerInterface, layerManager,rtsLayer, itemIndex);
                }
                else
                {
                    rtsPlayerLayerInterface.add(ErrorSound.getInstance());

                    if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
                    {
                        GameNotificationEventHandler.getInstance().fireEvent(
                                buildOnPathGameNotificationEvent);
                    }
                }

            }
            else
            {
                rtsPlayerLayerInterface.add(ErrorSound.getInstance());

                if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
                {
                    GameNotificationEventHandler.getInstance().fireEvent(
                            spotTakenGameNotificationEvent);
                }
            }
        }
        else
        {
            rtsPlayerLayerInterface.add(ErrorSound.getInstance());

            if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
            {
                GameNotificationEventHandler.getInstance().fireEvent(
                        selectBuildSpotGameNotificationEvent);
            }
        }
    }

    protected boolean attemptBuild(
            final RTSPlayerLayerInterface rtsPlayerLayerInterface,
            final AllBinaryLayerManager layerManager,
            final RTSLayer layerInterface, final int itemIndex)
    throws Exception
    {
        logUtil.put(new StringMaker().append("Layer: ").append(StringUtil.getInstance().toString(layerInterface)).toString(), this, "attemptBuild");

        final GeographicMapCellPositionAreaBase geographicMapCellPositionArea =
            layerInterface.geographicMapCellPositionAreaBase;

        final BasicArrayList occupyList =
            geographicMapCellPositionArea.getOccupyingGeographicMapCellPositionList();

        //logUtil.put("List: ").append(list, this, "attemptBuild");

        if(!this.isBuildAttemptValid(rtsPlayerLayerInterface, layerInterface))
        {
            return false;
        }

        GeographicMapCellType geographicMapCellType;
        for (int index = occupyList.size() - 1; index >= 0; index--)
        {
            final GeographicMapCompositeInterface geographicMapCompositeInterface = (GeographicMapCompositeInterface) layerManager;
            final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
            
            geographicMapCellType =
                geographicMapInterface.getCellTypeAt(
                (GeographicMapCellPosition) occupyList.get(index));

            final RaceTrackGeographicMapCellTypeFactory raceTrackGeographicMapCellTypeFactory = 
                (RaceTrackGeographicMapCellTypeFactory) geographicMapInterface.getGeographicMapCellTypeFactory();
            
            if(raceTrackGeographicMapCellTypeFactory.isPath(geographicMapCellType))
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

            newUnconstructedRTSLayerInterfaceArray[itemIndex] = CollidableDestroyableDamageableLayer.NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER;

            capital.removeMoney(cost);

            this.add(
                rtsPlayerLayerInterface,
                layerManager,
                layerInterface);

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

    protected boolean isBuildAttemptValid(
            final RTSPlayerLayerInterface rtsPlayerLayerInterface,
            final RTSLayer layerInterface) throws Exception
    {
        final GeographicMapCellPositionAreaBase geographicMapCellPositionArea =
            layerInterface.geographicMapCellPositionAreaBase;

        final BasicArrayList occupyList =
            geographicMapCellPositionArea.getOccupyingGeographicMapCellPositionList();

        if (dropCellPositionHistory.anyCellPositionWithDrop(occupyList))
        {
            rtsPlayerLayerInterface.add(ErrorSound.getInstance());

            if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
            {
                GameNotificationEventHandler.getInstance().fireEvent(
                        buildingCollisionGameNotificationEvent);
            }

            return false;
        }
        if (this.isUnitProducer &&
            this.dropCellPositionHistory.anyCellPositionWithDrop(
            geographicMapCellPositionArea.getSurroundingGeographicMapCellPositionList()))
        {
            rtsPlayerLayerInterface.add(ErrorSound.getInstance());

            if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
            {
                GameNotificationEventHandler.getInstance().fireEvent(
                        structureToCloseCollisionGameNotificationEvent);
            }

            return false;
        }
        else
        {
            if (this.isSurroundingCellsOffMap(layerInterface))
            {
                rtsPlayerLayerInterface.add(ErrorSound.getInstance());

                if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
                {
                    GameNotificationEventHandler.getInstance().fireEvent(
                            mapEdgeGameNotificationEvent);
                }

                return false;
            }
        }
        return true;
    }

    protected void add(
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final AllBinaryLayerManager layerManager, final RTSLayer layerInterface)
    throws Exception
    {
        final GeographicMapCellPositionAreaBase geographicMapCellPositionArea =
            layerInterface.geographicMapCellPositionAreaBase;

        final BasicArrayList occupyList =
            geographicMapCellPositionArea.getOccupyingGeographicMapCellPositionList();

        dropCellPositionHistory.add(occupyList, layerInterface);

        final RTSPlayerGameInput rtsPlayerGameInput = (RTSPlayerGameInput)
            rtsPlayerLayerInterface.getPlayerGameInput();

        rtsPlayerGameInput.setSelectedRTSLayer(
            layerInterface, this.getSelectedGeographicCellPosition());

        layerManager.append(layerInterface);

        rtsPlayerLayerInterface.add(BuildingSound.getInstance());

        if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
        {
            GameNotificationEventHandler.getInstance().fireEvent(
                    buildingGameNotificationEvent);
        }
    }

    private boolean isSurroundingCellsOffMap(RTSLayer layerInterface)
    {
        GeographicMapCellPositionAreaBase geographicMapCellPositionArea =
            layerInterface.geographicMapCellPositionAreaBase;

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

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("occupySize: ");
        stringBuffer.append(occupySize);
        stringBuffer.append(" surroundSize: ");
        stringBuffer.append(surroundSize);
        stringBuffer.append(" surroundList: ");
        stringBuffer.append(StringUtil.getInstance().toString(surroundList));

        logUtil.put(stringBuffer.toString(),
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
}
