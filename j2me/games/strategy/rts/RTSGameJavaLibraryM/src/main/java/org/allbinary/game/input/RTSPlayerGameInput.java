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

package org.allbinary.game.input;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.input.form.RTSFormInput;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSLayerInfoPaintable;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.util.BasicArrayList;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.InputFeatureFactory;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.form.NullRTSFormInputFactory;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.layer.NullRTSLayer;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.media.audio.SecondaryPlayerQueueFactory;
import org.allbinary.media.audio.SelectSound;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;
import org.allbinary.string.CommonLabels;

/**
 * 
 * @author user
 */
public class RTSPlayerGameInput extends PlayerGameInput
{

    protected final GameInputProcessor[] inputProcessorArray = 
        new GameInputProcessor[InputFactory.getInstance().MAX];
    protected final GameInputProcessor[] removeInputProcessorArray = 
        new GameInputProcessor[InputFactory.getInstance().MAX];
    
    private final BasicArrayList inputList;
    private final boolean isSingleKeyProcessing = 
        Features.getInstance().isFeature(InputFeatureFactory.getInstance().SINGLE_KEY_REPEAT_PRESS) || 
        Features.getInstance().isFeature(InputFeatureFactory.getInstance().SINGLE_KEY_PRESS);
    
    private final AllBinaryGameCanvas gameCanvas;
    private final BasicArrayList motionGestureInputList = new BasicArrayList();
    private final ScrollMapPlayerGameInput scrollPlayerGameInput;
    private final SelectedRTSLayersPlayerGameInput selectedRTSLayerPlayerGameInput;
    private final RTSLayerInfoPaintable towerInfoPaintable;
    private final RTSPlayerLayerInterface rtsPlayerLayerInterface;
    private RTSFormInput selectedRtsFormInput = NullRTSFormInputFactory.getInstance();

    private final LayerPositionFinderInterface layerPositionFinderInterface;

    //private LayerInterfaceFactoryInterface lastLayerInterfaceFactoryInterface;
    public RTSPlayerGameInput(final AllBinaryGameCanvas gameCanvas,
            final BasicArrayList inputList, final int playerInputId,
            final RTSLayerInfoPaintable towerInfoPaintable,
            final RTSPlayerLayerInterface rtsPlayerLayerInterface,
            final LayerPositionFinderInterface layerPositionFinderInterface,
            final SelectRTSLayerVisitorFactoryInterface selectRTSLayerVisitorFactoryInterface)
    {
        super(inputList, playerInputId);
        
        this.initInputProcessors();
        
        this.gameCanvas = gameCanvas;
        this.inputList = inputList;

        this.towerInfoPaintable = towerInfoPaintable;

        this.rtsPlayerLayerInterface = rtsPlayerLayerInterface;

        this.selectedRTSLayerPlayerGameInput = 
            new SelectedRTSLayersPlayerGameInput(            
                this.getRTSLayerInfoPaintable(), 
                this.getRtsPlayerLayerInterface(), 
                this.inputList, playerInputId,
                selectRTSLayerVisitorFactoryInterface
                );

        if (this.rtsPlayerLayerInterface != NullRTSLayer.NULL_RTS_LAYER)
        {
            this.setSelectedRtsFormInput(
                this.rtsPlayerLayerInterface.getRTSFormInput());
        }

        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) gameCanvas.getLayerManager();
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        this.scrollPlayerGameInput = new ScrollMapPlayerGameInput(geographicMapInterface, this.inputList, playerInputId);
        
        // this.currentPlayerGameInput = this.scrollPlayerGameInput;

        this.layerPositionFinderInterface = layerPositionFinderInterface;
    }

    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {
        
        this.selectedRTSLayerPlayerGameInput.setAllBinaryGameLayerManager(allBinaryGameLayerManager);

        if(this.selectedRtsFormInput != NullRTSFormInputFactory.getInstance()) {
            this.selectedRtsFormInput.setAllBinaryGameLayerManager(allBinaryGameLayerManager);
        }
    }
    
    public void onDisplayChangeEvent(DisplayChangeEvent displayChangeEvent)
    {
        try
        {
            logUtil.put(commonStrings.START, this, "onDisplayChangeEvent");

            this.getRTSLayerInfoPaintable().update();
        }
        catch(Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "onDisplayChangeEvent", e);
        }
    }

    protected void left()
    throws Exception
    {
        this.rtsPlayerLayerInterface.getCurrentScrollSelectionForm().processInput(Canvas.LEFT);
    }

    protected void right()
    throws Exception
    {
        this.rtsPlayerLayerInterface.getCurrentScrollSelectionForm().processInput(Canvas.RIGHT);
    }

    public void initInputProcessors()
    {     
        this.inputProcessorArray[Canvas.LEFT] = new RTSPlayerLeftGameInputProcessor(this);
        this.inputProcessorArray[Canvas.KEY_NUM0] = this.inputProcessorArray[Canvas.LEFT];
        
        this.inputProcessorArray[Canvas.RIGHT] = new RTSPlayerRightGameInputProcessor(this);        
        this.inputProcessorArray[Canvas.KEY_POUND] = this.inputProcessorArray[Canvas.RIGHT];

        this.removeInputProcessorArray[Canvas.KEY_NUM1] = new RTSPlayerFireGameInputProcessor(this);
        
        this.removeInputProcessorArray[Canvas.KEY_NUM3] = 
            this.removeInputProcessorArray[Canvas.KEY_NUM1];

        this.removeInputProcessorArray[Canvas.KEY_NUM3] = 
            this.removeInputProcessorArray[Canvas.KEY_NUM1];

        this.removeInputProcessorArray[Canvas.LEFT] = 
            this.removeInputProcessorArray[Canvas.KEY_NUM1];

        this.removeInputProcessorArray[Canvas.RIGHT] = 
            this.removeInputProcessorArray[Canvas.KEY_NUM1];

        this.removeInputProcessorArray[Canvas.KEY_NUM0] = 
            this.removeInputProcessorArray[Canvas.KEY_NUM1];

        this.removeInputProcessorArray[Canvas.KEY_POUND] = 
            this.removeInputProcessorArray[Canvas.KEY_NUM1];

        GameInputProcessorUtil.init(this.inputProcessorArray);
        GameInputProcessorUtil.init(this.removeInputProcessorArray);
    }

    public void processInput(AllBinaryLayerManager layerManager)
        throws Exception
    {
        try
        {
            this.processMotionInput(layerManager);

            int size = inputList.size();
            int key = 0;

            for (int index = 0; index < size; index++)
            {
                GameKeyEvent gameKeyEvent = 
                    (GameKeyEvent) inputList.get(index);
                key = gameKeyEvent.getKey();

                this.getScrollPlayerGameInput().processInput(key);
                this.getSelectedBuildingPlayerGameInput().processInput(key);
                // this.currentPlayerGameInput.processInput(key);

                this.inputProcessorArray[key].process(layerManager, gameKeyEvent);

                this.removeInputProcessorArray[key].process(layerManager, gameKeyEvent);
            }

            if (isIsSingleKeyProcessing())
            {
                this.clear();
            }
            else
            {
                this.update();
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, gameInputStrings.PROCESS_INPUT, e);
        }
    }

    public void processMotionInput(final AllBinaryLayerManager layerManager) throws Exception
    {
    }

    protected void select(final MotionGestureEvent motionGestureEvent) throws Exception
    {
        final GPoint point = motionGestureEvent.getCurrentPoint();

        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) gameCanvas.getLayerManager();
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        final AllBinaryTiledLayer allBinaryTiledLayer = 
            geographicMapInterface.getAllBinaryTiledLayer();

        final int x = point.getX() + allBinaryTiledLayer.getXP();
        final int y = point.getY() + allBinaryTiledLayer.getYP();

        //final SpacialStrings commonLabels = SpacialStrings.getInstance();
        //logUtil.put(
        //      "CellPosition Selection: point x: ").append(point.getX() +
          //    " y: ").append(point.getY()).append(" x: ").append(x).append(" y: ").append(y).append(
        //commonLabels).append(allBinaryTiledLayer.getWidth() +
        //commonLabels).append(allBinaryTiledLayer.getHeight(), this, "select");

        GeographicMapCellPosition geographicMapCellPosition =
            geographicMapInterface.getCellPositionAtNoThrow(x, y);
        
        if (geographicMapCellPosition != null)
        {
            SecondaryPlayerQueueFactory.getInstance().add(
                SelectSound.getInstance());

            AllBinaryLayer layer = this.layerPositionFinderInterface.getLayerInterface(geographicMapCellPosition);

            if (layer == AllBinaryLayer.NULL_ALLBINARY_LAYER)
            {
                layer = CollidableDestroyableDamageableLayer.NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER;
            }
            else
            {
                geographicMapCellPosition =
                    geographicMapInterface.getCellPositionAt(
                    layer.getXP(), layer.getYP());
            }            
            
            final CollidableDestroyableDamageableLayer foundRTSLayer = (CollidableDestroyableDamageableLayer) layer;
            // If this position has a tower then set that tower
            // otherwise set to null
            this.setSelectedRTSLayer(foundRTSLayer, geographicMapCellPosition);
        }
        else
        {
            final CommonLabels commonLabels = CommonLabels.getInstance();
            logUtil.put(new StringMaker().append("Off Of Map -").append(commonLabels.WIDTH_LABEL).append(allBinaryTiledLayer.getWidth()).append(commonLabels.HEIGHT_LABEL).append(allBinaryTiledLayer.getHeight()).toString(), this, "select");
        }
    }

    public void setSelectedRTSLayer(CollidableDestroyableDamageableLayer rtsLayer, GeographicMapCellPosition geographicMapCellPosition) throws Exception
    {
        //this.setSelectedRtsLayer(rtsLayer);
        this.getSelectedBuildingPlayerGameInput().setSelectedRTSLayer(rtsLayer);
    }

    public void paint(Graphics graphics)
    {
        final GeographicMapCellPosition geographicMapCellPosition = 
            this.getSelectedRtsFormInput().getSelectedGeographicCellPosition();

        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) gameCanvas.getLayerManager();
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        final AllBinaryTiledLayer allBinaryTiledLayer = 
            geographicMapInterface.getAllBinaryTiledLayer();

        graphics.setColor(BasicColorFactory.getInstance().GREEN.intValue());

        BasicArrayList list = 
            this.getSelectedBuildingPlayerGameInput().getPaintSelectedRTSLayersList();

        int width = 0;
        int height = 0;

        if (list.size() > 0)
        {
            for (int index = list.size() - 1; index >= 0; index--)
            {
                RTSLayer rtsLayer = (RTSLayer) list.get(index);

                width = rtsLayer.getWidth();
                height = rtsLayer.getHeight();

                graphics.drawRect(
                        rtsLayer.getXP() - allBinaryTiledLayer.getXP(), 
                        rtsLayer.getYP() - allBinaryTiledLayer.getYP(), 
                        width, height);
            }
        } else if (geographicMapCellPosition != null)
        {
            GPoint point = geographicMapCellPosition.getPoint();

            width = allBinaryTiledLayer.getCellWidth();
            height = allBinaryTiledLayer.getCellHeight();

            graphics.drawRect(
                    point.getX() - allBinaryTiledLayer.getXP(), 
                    point.getY() - allBinaryTiledLayer.getYP(), 
                    width, height);
        }        
    }
    
    public void updatePaintable()
    {
    }

    public SelectedRTSLayersPlayerGameInput getSelectedBuildingPlayerGameInput()
    {
        return selectedRTSLayerPlayerGameInput;
    }

    /**
     * @return the selectedRtsFormInput
     */
    public RTSFormInput getSelectedRtsFormInput()
    {
        return this.selectedRtsFormInput;
    }

    /**
     * @param selectedRtsFormInput the selectedRtsFormInput to set
     */
    public void setSelectedRtsFormInput(RTSFormInput selectedRtsFormInput)
    {
        logUtil.put(new StringMaker().append("RTSFormInput: ").append(StringUtil.getInstance().toString(selectedRtsFormInput)).toString(), this, "setSelectedRtsFormInput");
        this.selectedRtsFormInput = selectedRtsFormInput;
    }

    /**
     * @return the rtsPlayerLayerInterface
     */
    public RTSPlayerLayerInterface getRtsPlayerLayerInterface()
    {
        return rtsPlayerLayerInterface;
    }

    /**
     * @return the towerInfoPaintable
     */
    public RTSLayerInfoPaintable getRTSLayerInfoPaintable()
    {
        return towerInfoPaintable;
    }

    /**
     * @return the motionGestureInputList
     */
    public BasicArrayList getMotionGestureInputList()
    {
        return motionGestureInputList;
    }

    /**
     * @return the gameCanvas
     */
    protected AllBinaryGameCanvas getGameCanvas()
    {
        return gameCanvas;
    }

    /**
     * @return the isSingleKeyProcessing
     */
    public boolean isIsSingleKeyProcessing()
    {
        return isSingleKeyProcessing;
    }

    public ScrollMapPlayerGameInput getScrollPlayerGameInput()
    {
        return scrollPlayerGameInput;
    }
}
