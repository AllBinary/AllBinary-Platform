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

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.input.form.RTSFormInput;
import org.allbinary.game.layer.MultiSelectPaintable;
import org.allbinary.game.layer.RTSGameStrings;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSLayerInfoPaintable;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.input.motion.button.EndLevelNoBuildingSelectedTouchButtonsBuilder;
import org.allbinary.input.motion.button.EndLevelTouchButtonsBuilder;
import org.allbinary.input.motion.button.NoBuildingSelectedTouchButtonsBuilder;
import org.allbinary.input.motion.button.RTSTouchButtonsBuilder;
import org.allbinary.media.audio.SelectBuildingSound;
import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.game.state.GameState;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.paint.NullInitUpdatePaintable;
import org.allbinary.input.motion.button.TouchButtonsBuilderFactory;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.input.motion.gesture.observer.BaseMotionGestureEventListener;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.string.CommonLabels;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.math.RectangleCollisionUtil;
import org.allbinary.media.audio.ErrorSound;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;

/**
 * 
 * @author user
 */
public class HumanRTSPlayerGameInput 
extends RTSPlayerGameInput
implements BaseMotionGestureEventListener
{

    private boolean isDragging = false;

    //private final GameInputStrings gameInputStrings = GameInputStrings.getInstance();
    private final RectangleCollisionUtil rectangleCollisionUtil = RectangleCollisionUtil.getInstance();

    private final MotionGestureInput PRESSED = TouchMotionGestureFactory.getInstance().PRESSED;
    private final MotionGestureInput RELEASED = TouchMotionGestureFactory.getInstance().RELEASED;

    private final TouchButtonsBuilderFactory touchButtonsBuilderFactory;

    private final GameNotificationEvent notYoursGameNotificationEvent;
    
    public HumanRTSPlayerGameInput(
            final AllBinaryGameCanvas gameCanvas,
            final BasicGeographicMap geographicMapInterface, 
            final BasicArrayList inputList, final int playerInputId, 
            final RTSLayerInfoPaintable towerInfoPaintable,
            final RTSPlayerLayerInterface rtsPlayerLayerInterface,
            final LayerPositionFinderInterface layerPositionFinderInterface,
            final SelectRTSLayerVisitorFactoryInterface selectRTSLayerVisitorFactoryInterface
        )
    {
        this(gameCanvas,
                inputList, playerInputId, towerInfoPaintable, 
                rtsPlayerLayerInterface, layerPositionFinderInterface, 
                selectRTSLayerVisitorFactoryInterface, null);
    }
    
    public HumanRTSPlayerGameInput(
        final AllBinaryGameCanvas gameCanvas,
        final BasicArrayList inputList, final int playerInputId, 
        final RTSLayerInfoPaintable towerInfoPaintable,
        final RTSPlayerLayerInterface rtsPlayerLayerInterface,
        final LayerPositionFinderInterface layerPositionFinderInterface,
        final SelectRTSLayerVisitorFactoryInterface selectRTSLayerVisitorFactoryInterface,
        final TouchButtonsBuilderFactory touchButtonsBuilderFactory
        )
    {
        super(
            gameCanvas,
            inputList, playerInputId, 
            towerInfoPaintable,
            rtsPlayerLayerInterface,
            layerPositionFinderInterface,
            selectRTSLayerVisitorFactoryInterface);
    
        this.touchButtonsBuilderFactory = touchButtonsBuilderFactory;
        
        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
        
        this.notYoursGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    RTSGameStrings.getInstance().NOT_YOURS,
                    SmallIntegerSingletonFactory.getInstance().getInstance(2),
                    basicColorFactory.PINK,
                    BooleanFactory.getInstance().FALSE);            
        
        BasicMotionGesturesHandler.getInstance().addListener(this);
    }
    
    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        this.notYoursGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        
    }
    
    private boolean processDraggingMotionInput(
            AllBinaryLayerManager layerManager) 
        throws Exception
    {
        MotionGestureEvent motionGestureEvent = (MotionGestureEvent) 
            this.getMotionGestureInputList().get(
                    this.getMotionGestureInputList().size() - 1);

        MotionGestureInput motionGestureInput = motionGestureEvent.getMotionGesture();

        if (motionGestureInput == PRESSED)
        {
            GPoint point = motionGestureEvent.getCurrentPoint();

            if (this.getRtsPlayerLayerInterface().getCurrentScrollSelectionForm().isInForm(point))
            {                
                isDragging = this.getSelectedRtsFormInput().processSticky(
                        this.getSelectedRtsLayer(), this.getRtsPlayerLayerInterface(),
                        layerManager, point);                
            }
        } else if (motionGestureInput == RELEASED)
        {
            GPoint point = motionGestureEvent.getCurrentPoint();

            if (this.getRtsPlayerLayerInterface().getCurrentScrollSelectionForm().isInForm(point))
            {
                this.getSelectedRtsFormInput().process(
                        this.getSelectedRtsLayer(),
                        this.getRtsPlayerLayerInterface(),
                        layerManager, point);

            } else if (this.getSelectedRtsFormInput().isStickyItemSelected())
            {
                RTSFormInput previousRtsFormInput = this.getSelectedRtsFormInput();

                this.select(motionGestureEvent);

                this.getSelectedBuildingPlayerGameInput().selectAllPreselected();

                previousRtsFormInput.process(
                        this.getSelectedRtsLayer(),
                        this.getRtsPlayerLayerInterface(), 
                        layerManager, point);

                this.getSelectedBuildingPlayerGameInput().deselectAllPreselected();

            } else
            {
                this.select(motionGestureEvent);
            }

            this.getSelectedRtsFormInput().setStickyItemSelected(false);
            this.getSelectedRtsFormInput().setSelectedStickyItem(null);
            this.getSelectedRtsFormInput().setSelectedStickyItemIndex(-1);

            return true;
        }
        return false;
    }

    private GPoint startPoint = PointFactory.getInstance().ZERO_ZERO;
    private GPoint endPoint = PointFactory.getInstance().ZERO_ZERO;

    final String POSSIBLE = "Possible: ";
    final String AT = "At: ";
    final String METHOD = "makeSelection";
    final String ADDING = "Adding: ";
    final String SPACE = CommonSeps.getInstance().SPACE;
    
    private void makeSelection()
    {        
        BasicArrayList rtsLayerList = 
            this.getRtsPlayerLayerInterface().getPlayerOwnedRTSLayers().rtsLayerList;
        
        int rectX1 = this.startPoint.getX();
        int rectY1 = this.startPoint.getY();
        int rectX2 = this.endPoint.getX();
        int rectY2 = this.endPoint.getY();

        if(rectX1 > rectX2)
        {
            rectX2 = rectX1;
            rectX1 = this.endPoint.getX();
        }

        if(rectY1 > rectY2)
        {
            rectY2 = rectY1;
            rectY1 = this.endPoint.getY();
        }

        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append(AT);
        stringBuffer.append(rectX1);
        stringBuffer.append(SPACE);
        stringBuffer.append(rectY1);
        stringBuffer.append(SPACE);
        stringBuffer.append(rectX2);
        stringBuffer.append(SPACE);
        stringBuffer.append(rectY2);

        logUtil.put(stringBuffer.toString(), this, METHOD);
        
        RTSLayer rtsLayer;
        
        for(int index = rtsLayerList.size() - 1; index >= 0; index--)
        {
            rtsLayer = (RTSLayer) rtsLayerList.get(index);

            stringBuffer.delete(0, stringBuffer.length());
            
            stringBuffer.append(POSSIBLE);
            stringBuffer.append(rtsLayer.getName());
            stringBuffer.append(SPACE);
            stringBuffer.append(AT);
            stringBuffer.append((rtsLayer.getViewPosition().getX() + rtsLayer.getHalfWidth()));
            stringBuffer.append(SPACE);
            stringBuffer.append((rtsLayer.getViewPosition().getY() + rtsLayer.getHalfHeight()));

            logUtil.put(stringBuffer.toString(), this, METHOD);

            if(rectangleCollisionUtil.isInside(
                    rectX1, rectY1, rectX2, rectY2, 
                    rtsLayer.getViewPosition().getX() + rtsLayer.getHalfWidth(), 
                    rtsLayer.getViewPosition().getY() + rtsLayer.getHalfHeight()))
            {
                logUtil.put(new StringMaker().append(ADDING).append(rtsLayer.getName()).toString(), this, METHOD);
                
                rtsLayer.select();
                this.getSelectedBuildingPlayerGameInput().addSelectedRTSLayer(rtsLayer);                
            }
        }
        
        //hmm - well I need to make a group form... 
        //How will that work?
        //Simple menus must be combined and when an item occurs for 1 or 
        //more selected layers then that action must be processed for each
        //this.updateForm(rtsLayer);
        
        //for now update on the last selected item
        RTSLayer lastRTSLayer = 
            this.getSelectedBuildingPlayerGameInput().getLastSelectedRtsLayer();
        
        if(lastRTSLayer != null && lastRTSLayer.getScrollSelectionForm() != null)
        {
            this.updateForm(
                    lastRTSLayer.getScrollSelectionForm(), 
                    lastRTSLayer.getRTSFormInput());
            this.setSelectedRtsLayer(lastRTSLayer);
        }
        
        this.updatePaintable();
    }
    
    private void processSelectionBoxMotionInput(
            AllBinaryLayerManager layerManager) 
        throws Exception
    {
        for(int index = this.getMotionGestureInputList().size() - 1; index >= 0; index--)
        {
            MotionGestureEvent motionGestureEvent = (MotionGestureEvent) 
                this.getMotionGestureInputList().get(index);
            
            MotionGestureInput motionGestureInput = 
                motionGestureEvent.getMotionGesture();
            
            GPoint point = motionGestureEvent.getCurrentPoint();
            
            if (motionGestureInput == TouchMotionGestureFactory.getInstance().PRESSED)
            {
                //Start selection box
                this.startPoint = point;
                this.endPoint = point;
            } 
            else 
                if (motionGestureInput == TouchMotionGestureFactory.getInstance().RELEASED)
                {
                    this.endPoint = point;
                    
                    //Make selection
                    this.makeSelection();
                    
                    this.startPoint = PointFactory.getInstance().ZERO_ZERO;
                    this.endPoint = PointFactory.getInstance().ZERO_ZERO;
                }
                else
                    {
                        //Adjust selection box
                    this.endPoint = point;
                    }
        }
    }
    
    public void processMotionInput(AllBinaryLayerManager layerManager) throws Exception
    {
        //logUtil.put(commonStrings.START, this, gameInputStrings.PROCESS_MOTION_INPUT);

        if (this.getMotionGestureInputList().size() > 0)
        {
            boolean endDrag = this.processDraggingMotionInput(layerManager);

            //If Not dragging item then go ahead and create selection box
            if(!isDragging)
            {
                this.processSelectionBoxMotionInput(layerManager);
            }

            if (endDrag)
            {
                isDragging = false;
            }
        }

        getMotionGestureInputList().clear();
    }

    /**
     * @param selectedRtsFormInput the selectedRtsFormInput to set
     */
    public void setSelectedRtsFormInput(RTSFormInput selectedRtsFormInput)
    {
        final LogUtil logUtil = LogUtil.getInstance();
        logUtil.put(new StringMaker().append(CommonLabels.getInstance().START).append(StringUtil.getInstance().toString(selectedRtsFormInput)).toString(), this, "setSelectedRtsFormInput");
        
        super.setSelectedRtsFormInput(selectedRtsFormInput);
        this.setSelectedRtsLayer(null);
    }

    private void updateForm(RTSLayer rtsLayer)
    {
        ScrollSelectionForm scrollSelectionForm = rtsLayer.getScrollSelectionForm();

        //logUtil.put(
          //      CommonLabels.getInstance().START).append(rtsLayer.getName()).append(" form: ").append(scrollSelectionForm, this, "updateForm");
        
        // Waypoints are the only games objects still without menus currently
        if (scrollSelectionForm != null
                && this.getRtsPlayerLayerInterface().getGroupInterface()[0] == rtsLayer.getGroupInterface()[0])
        {
            this.updateForm(scrollSelectionForm, rtsLayer.getRTSFormInput());
            this.getSelectedBuildingPlayerGameInput().setSelectedRTSLayer(null);
            this.setSelectedRtsLayer(rtsLayer);
        } else
        {
            this.getRtsPlayerLayerInterface().setCurrentScrollSelectionForm(
                    this.getRtsPlayerLayerInterface().getBuildingScrollSelectionForm());
            // this.rtsPlayerLayerInterface.getRTSFormInput().setSelectedGeographicCellPosition(
            // geographicMapCellPosition);

            this.setSelectedRtsFormInput(
                    this.getRtsPlayerLayerInterface().getRTSFormInput());
            this.getSelectedBuildingPlayerGameInput().setSelectedRTSLayer(null);
        }
    }

    private void updateForm(
            ScrollSelectionForm scrollSelectionForm, 
            RTSFormInput rtfFormInput)
    {
        // Waypoints are the only games objects still without menus
        this.getRtsPlayerLayerInterface().setCurrentScrollSelectionForm(scrollSelectionForm);
        // rtsLayer.getRTSFormInput().setSelectedGeographicCellPosition(geographicMapCellPosition);
        this.setSelectedRtsFormInput(rtfFormInput);
    }
        
    public void setSelectedRTSLayer(
            RTSLayer rtsLayer, GeographicMapCellPosition geographicMapCellPosition) 
            throws Exception
    {
        if (rtsLayer != null && 
                this.getRtsPlayerLayerInterface().getGroupInterface()[0] != rtsLayer.getGroupInterface()[0])
        {
            this.getRtsPlayerLayerInterface().add(ErrorSound.getInstance());

            GameNotificationEventHandler.getInstance().fireEvent(
                    notYoursGameNotificationEvent);

            return;
        }

        //deselect all?

        AllBinaryGameCanvas gameCanvas = this.getGameCanvas();
        
        if (rtsLayer != null)
        {
            this.getRtsPlayerLayerInterface().add(
                    SelectBuildingSound.getInstance());

            rtsLayer.select();

            this.updateForm(rtsLayer);

            if (gameCanvas.getGameState() == GameState.PLAYING_GAME_STATE)
            {
                if(rtsLayer.isSelfUpgradeable())
                {
                    gameCanvas.updateCurrentTouchInputFactory(
                            new RTSTouchButtonsBuilder());
                }
                else
                {
                    gameCanvas.updateCurrentTouchInputFactory(
                            new NoBuildingSelectedTouchButtonsBuilder());
                }
            }
            else
            {
                EndLevelTouchButtonsBuilder endLevelTouchButtonsBuilder = 
                    new EndLevelTouchButtonsBuilder();
                
                if(this.touchButtonsBuilderFactory != null)
                {
                    endLevelTouchButtonsBuilder.add(
                            this.touchButtonsBuilderFactory.getInstance());
                }
                
                gameCanvas.updateCurrentTouchInputFactory(
                        endLevelTouchButtonsBuilder);
            }
        }
        else
        {
            logUtil.put("Set Player Default Form", this, "setSelectedRTSLayer");

            this.getRtsPlayerLayerInterface().setCurrentScrollSelectionForm(
                this.getRtsPlayerLayerInterface().getBuildingScrollSelectionForm());
            //this.rtsPlayerLayerInterface.getRTSFormInput().setSelectedGeographicCellPosition(
            //   geographicMapCellPosition);

            this.setSelectedRtsFormInput(
                    this.getRtsPlayerLayerInterface().getRTSFormInput());
            this.getSelectedBuildingPlayerGameInput().setSelectedRTSLayer(null);

            if (gameCanvas.getGameState() == GameState.PLAYING_GAME_STATE)
            {
                gameCanvas.updateCurrentTouchInputFactory(
                        new NoBuildingSelectedTouchButtonsBuilder());
            }
            else
            {
                EndLevelNoBuildingSelectedTouchButtonsBuilder endLevelNoBuildingSelectedTouchButtonsBuilder = 
                    new EndLevelNoBuildingSelectedTouchButtonsBuilder();
                
                if(this.touchButtonsBuilderFactory != null)
                {
                    endLevelNoBuildingSelectedTouchButtonsBuilder.add(
                            this.touchButtonsBuilderFactory.getInstance());
                }

                gameCanvas.updateCurrentTouchInputFactory(
                        endLevelNoBuildingSelectedTouchButtonsBuilder);
            }
        }

        this.getSelectedRtsFormInput().setSelectedGeographicCellPosition(
                geographicMapCellPosition);

        this.getSelectedBuildingPlayerGameInput().setSelectedRTSLayer(rtsLayer);

        this.updatePaintable();
    }
    
    private final MultiSelectPaintable multiSelectPaintable = new MultiSelectPaintable();
    
    public void updatePaintable()
    {
        final BasicArrayList list = 
            this.getSelectedBuildingPlayerGameInput().getSelectedBasicArrayList();

        if(list.size() > 1)
        {
            final GeographicMapCompositeInterface geographicMapCompositeInterface
                = (GeographicMapCompositeInterface) this.getGameCanvas();
            final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
            
            multiSelectPaintable.setBasicColorP(
                    geographicMapInterface.getForegroundBasicColor());
            multiSelectPaintable.update(list);

            this.getRTSLayerInfoPaintable().updateRTSLayerInfo(
                    multiSelectPaintable);
        }
        else
            if(list.size() == 1)
            {
                RTSLayer rtsLayer = (RTSLayer) list.get(0);
                
                this.getRTSLayerInfoPaintable().updateRTSLayerInfo(
                        rtsLayer.createHudPaintable());
            }
            else
        {
            this.getRTSLayerInfoPaintable().updateRTSLayerInfo(
                    NullInitUpdatePaintable.getInstance());
        }
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics);

        if(this.startPoint != PointFactory.getInstance().ZERO_ZERO &&
           this.endPoint != PointFactory.getInstance().ZERO_ZERO)
        {
            graphics.setColor(BasicColorFactory.getInstance().RED.intValue());

            int rectX1 = this.startPoint.getX();
            int rectY1 = this.startPoint.getY();
            int rectX2 = this.endPoint.getX();
            int rectY2 = this.endPoint.getY();

            if (rectX1 > rectX2)
            {
                rectX2 = rectX1;
                rectX1 = this.endPoint.getX();
            }

            if (rectY1 > rectY2)
            {
                rectY2 = rectY1;
                rectY1 = this.endPoint.getY();
            }

            graphics.drawRect(rectX1, rectY1, 
                    rectX2 - rectX1, rectY2 - rectY1);
        }
    }

    public void onMotionGestureEvent(MotionGestureEvent motionGestureEvent)
    {
        //logUtil.put("motionGestureEvent: ").append(motionGestureEvent, this, "onMotionGestureEvent");

        getMotionGestureInputList().add(motionGestureEvent);
    }

    private RTSLayer selectedRtsLayer;
    /**
     * @return the selectedRtsLayer
     */
    private RTSLayer getSelectedRtsLayer()
    {
        return selectedRtsLayer;
    }

    /**
     * @param selectedRtsLayer the selectedRtsLayer to set
     */
    private void setSelectedRtsLayer(RTSLayer selectedRtsLayer)
    {
        this.selectedRtsLayer = selectedRtsLayer;
    }
    
}
