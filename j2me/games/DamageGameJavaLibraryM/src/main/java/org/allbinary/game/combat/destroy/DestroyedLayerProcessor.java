/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.game.combat.destroy;

import org.allbinary.util.BasicArrayList;

import org.allbinary.game.combat.destroy.event.DestroyEventCircularStaticPool;
import org.allbinary.game.combat.destroy.event.DestroyedEvent;
import org.allbinary.game.combat.destroy.event.DestroyedEventHandler;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.BasicLayerProcessor;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;

public class DestroyedLayerProcessor extends BasicLayerProcessor
{
    private static BasicLayerProcessor DESTROYED_LAYER_PROCESSOR;

    private DestroyedLayerProcessor()
    {
    }

    public static void init()
    {
        DESTROYED_LAYER_PROCESSOR = new DestroyedLayerProcessor();
    }

    public static BasicLayerProcessor getInstance()
    {
        return DESTROYED_LAYER_PROCESSOR;
    }

    private final String LAYER_LABEL = "layerInterface: ";
    
    public void process(AllBinaryLayerManager allBinaryLayerManager)
        throws Exception
    {
        BasicArrayList list = this.getList();
        int size = list.size();

        final DestroyedEventHandler destroyedEventHandler = DestroyedEventHandler.getInstance();
        final DestroyEventCircularStaticPool destroyEventCircularStaticPool =
            DestroyEventCircularStaticPool.getInstance();
        
        //final StringBuilder stringBuilder = new StringBuilder();
        //LogUtil.put(LogFactory.getInstance(stringBuilder.append("Removing: ").append(size).append(" left: ").append(allBinaryLayerManager.getSize()).toString(), this, commonStrings.PROCESS));

        //GroupLayerManagerListener.getInstance().log();
        AllBinaryLayer layerInterface;
        DestroyedEvent destroyedEvent;
        for (int index = 0; index < size; index++)
        {
            // no physics here - just destroy them
            layerInterface = (AllBinaryLayer) list.objectArray[index];

            if(layerInterface != null) {
                //stringBuilder.delete(0, stringBuilder.length());
                //LogUtil.put(LogFactory.getInstance(stringBuilder.append(LAYER_LABEL).append(layerInterface).toString(), this, commonStrings.PROCESS));

                //stringBuilder.delete(0, stringBuilder.length());
                //LogUtil.put(LogFactory.getInstance(stringBuilder.append("Processing: ").append(allBinaryLayerManager.getSize()).toString(), this, commonStrings.PROCESS));
                allBinaryLayerManager.remove(layerInterface);

                //stringBuilder.delete(0, stringBuilder.length());
                //LogUtil.put(LogFactory.getInstance(stringBuilder.append("After: ").append(allBinaryLayerManager.getSize()).toString(), this, commonStrings.PROCESS));
                destroyedEvent = destroyEventCircularStaticPool.getInstance(layerInterface);

                // Notify Listeners of Destroyed Layer
                destroyedEventHandler.fireEvent(destroyedEvent);
            } else {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                final StringMaker stringBuilder = new StringMaker();
                //stringBuilder.delete(0, stringBuilder.length());
                LogUtil.put(LogFactory.getInstance(stringBuilder.append(LAYER_LABEL).append(layerInterface).toString(), this, commonStrings.PROCESS, new Exception()));
            }
            
        }
        list.clear();
    }
}
