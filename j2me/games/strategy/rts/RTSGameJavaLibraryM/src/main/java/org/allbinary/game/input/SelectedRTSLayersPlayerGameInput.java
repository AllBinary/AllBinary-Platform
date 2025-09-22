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

import org.allbinary.game.layer.RTSGameStrings;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSLayerInfoPaintable;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.layer.capital.Capital;
import org.allbinary.media.audio.DowngradeSound;
import org.allbinary.media.audio.UpgradeSound;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.InputFeatureFactory;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.util.visitor.Visitor;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.audio.ErrorSound;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;

/**
 * 
 * @author user
 */
public class SelectedRTSLayersPlayerGameInput extends PlayerGameInput
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final GameInputProcessor[] inputProcessorArray = 
        new GameInputProcessor[InputFactory.getInstance().MAX];
    
    private final BasicArrayList list;
    protected final boolean isSingleKeyProcessing = 
        Features.getInstance().isFeature(InputFeatureFactory.getInstance().SINGLE_KEY_REPEAT_PRESS)
            || Features.getInstance().isFeature(InputFeatureFactory.getInstance().SINGLE_KEY_PRESS);
    
    private BasicArrayList selectedRTSLayersList = new BasicArrayList();
    private BasicArrayList preSelectedRTSLayersList = new BasicArrayList();
    private BasicArrayList paintSelectedRTSLayersList = BasicArrayListUtil.getInstance().getImmutableInstance();

    private RTSPlayerLayerInterface rtsPlayerLayerInterface;

    private final Visitor selectRTSLayerVisitorInterface;

    private final GameNotificationEvent upgradeGameNotificationEvent;
    private final GameNotificationEvent noMoneyGameNotificationEvent;
    private final GameNotificationEvent downgradeGameNotificationEvent;

    public SelectedRTSLayersPlayerGameInput(
            RTSLayerInfoPaintable towerInfoPaintable,
            RTSPlayerLayerInterface rtsPlayerLayerInterface,
            BasicArrayList list, int playerInputId, 
            SelectRTSLayerVisitorFactoryInterface selectRTSLayerVisitorFactoryInterface)
    {
        super(list, playerInputId);

        this.initInputProcessors();

        this.rtsPlayerLayerInterface = rtsPlayerLayerInterface;
        this.list = list;

        this.selectRTSLayerVisitorInterface = 
            selectRTSLayerVisitorFactoryInterface.getInstance(this);

        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
        
        this.upgradeGameNotificationEvent =
        new GameNotificationEvent(
            this,
            RTSGameStrings.getInstance().UPGRADE,
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

        this.downgradeGameNotificationEvent =
        new GameNotificationEvent(
            this,
            RTSGameStrings.getInstance().DOWNGRADE,
            smallIntegerSingletonFactory.getInstance(2),
            basicColorFactory.PINK,
            BooleanFactory.getInstance().FALSE);
    }

    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        final GeographicMapCompositeInterface geographicMapCompositeInterface = 
            (GeographicMapCompositeInterface) allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];

        this.upgradeGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.noMoneyGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        this.downgradeGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());
        
    }
    
    public boolean isSelected(RTSLayer rtsLayer)
    {
        if(this.getSelectedBasicArrayList().contains(rtsLayer))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
        
    protected void upgrade()
    throws Exception
    {
        boolean anyChanged = false;
        
        for (int index = this.getSelectedBasicArrayList().size() - 1; index >= 0; index--)
        {
            RTSLayer rtsLayer = (RTSLayer) this.getSelectedBasicArrayList().get(index);

            if (rtsLayer.isUpgradeable())
            {
                Capital capital = this.rtsPlayerLayerInterface.getCapital();

                int upgradeCost = rtsLayer.getUpgradeCost();
                if (upgradeCost <= capital.getTotalMoney())
                {
                    anyChanged = true;

                    rtsPlayerLayerInterface.add(UpgradeSound.getInstance());

                    rtsLayer.upgrade();

                    capital.removeMoney(upgradeCost);

                    // this.towerInfoPaintable.updateRTSLayerInfo(this.selectedLayer);

                    if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
                    {
                        GameNotificationEventHandler.getInstance().fireEvent(
                                upgradeGameNotificationEvent);
                    }
                    
                } else
                {
                    rtsPlayerLayerInterface.add(ErrorSound.getInstance());

                    if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
                    {
                        //Not enough money
                        GameNotificationEventHandler.getInstance().fireEvent(
                                noMoneyGameNotificationEvent);
                    }
                }
            }
        }

        //Since a set of layers could be selected just update when all are changed
        if(anyChanged)
        {
            ((RTSPlayerGameInput) this.rtsPlayerLayerInterface.getPlayerGameInput()).updatePaintable();
        }
    }

    protected void downgrade()
    throws Exception
    {
        boolean anyChanged = false;
        
        for (int index = this.getSelectedBasicArrayList().size(); --index >= 0;)
        {
            RTSLayer rtsLayer = (RTSLayer) this.getSelectedBasicArrayList().get(index);

            if (rtsLayer.isDowngradeable())
            {
                anyChanged = true;
                
                rtsPlayerLayerInterface.add(DowngradeSound.getInstance());

                int downgradeCost = rtsLayer.getDowngradeCost();

                rtsLayer.downgrade();

                Capital capital = this.rtsPlayerLayerInterface.getCapital();

                capital.addMoney(downgradeCost);

                // this.towerInfoPaintable.updateRTSLayerInfo(this.selectedLayer);

                if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
                {
                    GameNotificationEventHandler.getInstance().fireEvent(
                            downgradeGameNotificationEvent);
                }
            }
        }
        
        //Since a set of layers could be selected just update when all are changed
        if(anyChanged)
        {
            ((RTSPlayerGameInput) this.rtsPlayerLayerInterface.getPlayerGameInput()).updatePaintable();
        }
    }

    public void initInputProcessors()
    {     
        this.inputProcessorArray[Canvas.KEY_NUM1] = 
            new SelectedRTSLayersPlayerUpgradeGameInputProcessor(this);
        this.inputProcessorArray[Canvas.KEY_NUM3] = 
            new SelectedRTSLayersPlayerDowngradeGameInputProcessor(this);

        GameInputProcessorUtil.init(this.inputProcessorArray);
    }

    public void processInput(int key)
    throws Exception
    {
        if (this.getSelectedBasicArrayList() != null)
        {
            this.inputProcessorArray[key].process(null, (GameKeyEvent) null);
        }
    }
    
    public void processInput(AllBinaryLayerManager layerManager)
        throws Exception
    {
        try
        {
            int size = list.size();
            int key = 0;

            for (int index = 0; index < size; index++)
            {
                GameKeyEvent gameKeyEvent = (GameKeyEvent) list.get(index);
                key = gameKeyEvent.getKey();

                this.processInput(key);
            }

            if (isSingleKeyProcessing)
            {
                this.clear();
            } else
            {
                this.update();
            }

        } catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, gameInputStrings.PROCESS_INPUT, e);
        }
    }

    /**
     * @return the selectedRTSLayer
     */
    public BasicArrayList getSelectedBasicArrayList()
    {
        return selectedRTSLayersList;
    }

    /**
     * @param selectedRTSLayer
     *            the selectedRTSLayer to set
     */
    public void addSelectedRTSLayer(RTSLayer selectedLayer)
    {
        this.paintSelectedRTSLayersList = BasicArrayListUtil.getInstance().getImmutableInstance();
        
        if(selectedLayer == null)
        {
            this.deselectAll();
            this.selectedRTSLayersList.clear();
        }
        else
        {
            if(!this.selectedRTSLayersList.contains(selectedLayer))
            {
                this.selectedRTSLayersList.add(selectedLayer);
            }
        }
        
        this.paintSelectedRTSLayersList = this.selectedRTSLayersList;
    }
    
    public void setSelectedRTSLayer(RTSLayer selectedLayer)
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("Selected Layer: ");
        if(selectedLayer != null)
        {
            stringBuffer.append(selectedLayer.getName());
        }
         
        logUtil.put(
                stringBuffer.toString(), this, "setSelectedRTSLayer");

        this.paintSelectedRTSLayersList = BasicArrayListUtil.getInstance().getImmutableInstance();
        
        this.selectRTSLayerVisitorInterface.visit(selectedLayer);

        this.deselectAll();

        //Save existing selection until a new selection is available
        //Could be better if list if never deselected until a new command occurs instead        
        if(this.selectedRTSLayersList.size() > 0)
        {
            this.getPreSelectedRTSLayersList().clear();
            final BasicArrayList tempList = this.getPreSelectedRTSLayersList();
            this.preSelectedRTSLayersList = this.selectedRTSLayersList;
            this.selectedRTSLayersList = tempList;
            
            logUtil.put(new StringMaker().append("Preselected: ").append(this.preSelectedRTSLayersList.toString()).toString(), this, "setSelectedRTSLayer");
        }
        
        //this.selectedRTSLayersList.clear();

        if(selectedLayer != null)
        {
            this.selectedRTSLayersList.add(selectedLayer);
        }
        
        this.paintSelectedRTSLayersList = this.selectedRTSLayersList;
    }

    public RTSLayer getLastSelectedRtsLayer()
    {
        if(this.isAnyRTSLayerSelected())
        {
            return (RTSLayer) this.getSelectedBasicArrayList().get(this.selectedRTSLayersList.size() - 1);
        }
        else
        {
            return null;
        }
    }

    public void selectAllPreselected()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("Select all Preselected: ");
        stringBuffer.append(this.preSelectedRTSLayersList.toString());

        logUtil.put(
                stringBuffer.toString(), this, "selectAllPreselected");

        for(int index = this.preSelectedRTSLayersList.size() - 1; index >= 0; index--)
        {
            RTSLayer rtsLayer = (RTSLayer) this.preSelectedRTSLayersList.get(index);
            rtsLayer.select();
        }
    }
    
    public void deselectAllPreselected()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("Deselect all Preselected: ");
        stringBuffer.append(this.preSelectedRTSLayersList.toString());

        logUtil.put(
                stringBuffer.toString(), this, "deselectAllPreselected");

        for(int index = this.preSelectedRTSLayersList.size() - 1; index >= 0; index--)
        {
            RTSLayer rtsLayer = (RTSLayer) this.preSelectedRTSLayersList.get(index);
            rtsLayer.deselect();
        }

        this.preSelectedRTSLayersList.clear();
    }
    
    public void deselectAll()
    {
        for(int index = this.getSelectedBasicArrayList().size() - 1; index >= 0; index--)
        {
            RTSLayer rtsLayer = (RTSLayer) this.getSelectedBasicArrayList().get(index);
            rtsLayer.deselect();
        }
    }
    
    public boolean isAnyRTSLayerSelected()
    {
        if (this.getSelectedBasicArrayList().size() == 0)
        {
            return false;
        }
        return true;
    }

    public BasicArrayList getPaintSelectedRTSLayersList()
    {
        return paintSelectedRTSLayersList;
    }

    public BasicArrayList getPreSelectedRTSLayersList()
    {
        return preSelectedRTSLayersList;
    }
}
