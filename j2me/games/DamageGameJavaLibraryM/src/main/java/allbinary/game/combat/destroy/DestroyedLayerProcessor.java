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
package allbinary.game.combat.destroy;

import org.allbinary.util.BasicArrayList;

import allbinary.game.combat.destroy.event.DestroyEventCircularStaticPool;
import allbinary.game.combat.destroy.event.DestroyedEvent;
import allbinary.game.combat.destroy.event.DestroyedEventHandler;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.layer.BasicLayerProcessor;

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

    public void process(AllBinaryLayerManager allBinaryLayerManager)
        throws Exception
    {
        BasicArrayList list = this.getList();
        int size = list.size();

        DestroyEventCircularStaticPool destroyEventCircularStaticPool =
            DestroyEventCircularStaticPool.getInstance();
        
        //LogUtil.put(LogFactory.getInstance("Removing: " + size + " left: " + allBinaryLayerManager.getSize(), this, CommonStrings.getInstance().PROCESS));
        //GroupLayerManagerListener.getInstance().log();
        for (int index = 0; index < size; index++)
        {
            // no physics here - just destroy them
            AllBinaryLayer layerInterface = (AllBinaryLayer) list.objectArray[index];

            // LogUtil.put(LogFactory.getInstance("Processing: " + allBinaryLayerManager.getSize(), this, CommonStrings.getInstance().PROCESS));

            allBinaryLayerManager.remove(layerInterface);
            // LogUtil.put(LogFactory.getInstance("After: " + allBinaryLayerManager.getSize(), this, CommonStrings.getInstance().PROCESS));

            DestroyedEvent destroyedEvent = 
                destroyEventCircularStaticPool.getInstance(layerInterface);

            // Notify Listeners of Destroyed Layer
            DestroyedEventHandler.getInstance().fireEvent(destroyedEvent);
        }
        list.clear();
    }
}
