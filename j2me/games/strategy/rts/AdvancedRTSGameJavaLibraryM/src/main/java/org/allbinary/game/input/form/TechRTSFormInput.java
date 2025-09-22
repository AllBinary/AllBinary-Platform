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

import org.allbinary.game.layer.RTSGameStrings;
import org.allbinary.game.layer.RTSInterface;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.RTSPlayerLayerInterface;
import org.allbinary.game.layer.TechnologyRTSInterfaceImageItem;
import org.allbinary.game.layer.capital.Capital;
import org.allbinary.game.rts.technology.event.TechEventHandler;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.media.audio.BuildingSound;

import org.allbinary.string.CommonStrings;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.audio.ErrorSound;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;

/**
 *
 * @author user
 */
public class TechRTSFormInput extends RTSFormInput
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final AllBinaryEventObject EVENT = 
        new AllBinaryEventObject(this);

    protected final GameNotificationEvent noMoneyGameNotificationEvent;
    
    public TechRTSFormInput(
            final Group[] groupInterface)
    {
        super(groupInterface);        

        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();

        this.noMoneyGameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    RTSGameStrings.getInstance().NO_MONEY,
                    SmallIntegerSingletonFactory.getInstance().getInstance(2),
                    basicColorFactory.WHITE,
                    BooleanFactory.getInstance().FALSE);                
        
    }
    
    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {

        super.setAllBinaryGameLayerManager(allBinaryGameLayerManager);

        final GeographicMapCompositeInterface geographicMapCompositeInterface
            = (GeographicMapCompositeInterface) allBinaryGameLayerManager;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        this.noMoneyGameNotificationEvent.setBasicColorP(geographicMapInterface.getForegroundBasicColor());

    }
     
    public void process(
        RTSLayer associatedRtsLayer,
        RTSPlayerLayerInterface rtsPlayerLayerInterface,
        AllBinaryLayerManager layerManager, 
        CustomItem item, int itemIndex)
        throws Exception
    {
        super.process(layerManager);
        
        final CommonStrings commonStrings = CommonStrings.getInstance();
        
        TechnologyRTSInterfaceImageItem technologyRTSInterfaceImageItem = 
            (TechnologyRTSInterfaceImageItem) item;

        RTSInterface rtsInterface = technologyRTSInterfaceImageItem.getRtsInterface();

        logUtil.put("isUpgradeable: " + rtsInterface.isUpgradeable(), this, commonStrings.PROCESS);

        if (rtsInterface.isUpgradeable())
        {
            int cost = rtsInterface.getUpgradeCost();
            Capital capital = rtsPlayerLayerInterface.getCapital();

            logUtil.put(cost + "<=" + capital.getTotalMoney(), this, commonStrings.PROCESS);

            if (cost <= capital.getTotalMoney())
            {
                rtsPlayerLayerInterface.add(
                    BuildingSound.getInstance());

                capital.removeMoney(cost);

                rtsInterface.upgrade();

                technologyRTSInterfaceImageItem.update();
                
                //Items must be updated for changes including but not limited to cost
                TechEventHandler.getInstance().fireEvent(EVENT);
            }
            else
            {
                rtsPlayerLayerInterface.add(ErrorSound.getInstance());

                if(!rtsPlayerLayerInterface.implmentsArtificialIntelligenceCompositeInterface())
                {
                    GameNotificationEventHandler.getInstance().fireEvent(
                            noMoneyGameNotificationEvent);
                }
            }

        }
    }

}
