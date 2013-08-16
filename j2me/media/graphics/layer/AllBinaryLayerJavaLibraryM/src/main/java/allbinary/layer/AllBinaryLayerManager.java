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
package allbinary.layer;

import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.layer.event.LayerManagerEvent;
import allbinary.layer.event.LayerManagerEventHandler;

public class AllBinaryLayerManager extends LayerInterfaceManager
{
    private final LayerManagerEventHandler layerManagerEventHandler = 
        LayerManagerEventHandler.getInstance();
    
    private final LayerManagerEvent createLayerManagerEvent = new LayerManagerEvent(this, this.layerManagerEventHandler.CREATE);
    private final LayerManagerEvent deleteLayerManagerEvent = new LayerManagerEvent(this, this.layerManagerEventHandler.DELETE);
    private LayerProcessor[] basicLayerProcessorArray = new LayerProcessor[0];

    protected AllBinaryLayerManager()
    {
    }

    // private int damageIndex = -1;
    protected LayerProcessor[] getLayerProcessorArray()
    {
        return basicLayerProcessorArray;
    }

    // , int damageIndex
    public void setLayerProcessorArray(LayerProcessor[] layerProcessorArray)
    {
        this.basicLayerProcessorArray = layerProcessorArray;
        // this.damageIndex = damageIndex;
    }

    public final void log()
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().TOTAL_LABEL + this.getSize(), this, "log"));

        int size = this.basicLayerProcessorArray.length;
        for (int index = 0; index < size; index++)
        {
            LayerProcessor layerProcessorInterface = basicLayerProcessorArray[index];

            LogUtil.put(LogFactory.getInstance(layerProcessorInterface.getClass().getName() + CommonSeps.getInstance().SPACE + CommonStrings.getInstance().TOTAL_LABEL +
            layerProcessorInterface.getLayerInterfaceManager().getSize(), this, "log"));
        }
    }

    public void append(AllBinaryLayer layerInterface) throws Exception
    {
        if(!this.contains(layerInterface))
        {
            this.appendProcessors(layerInterface);

            super.append(layerInterface);

          //PreLogUtil.put("Append: " + layerInterface + " Size: " + this.getSize(), this, "append");
        }
    }

    public void append(AllBinaryLayer layerInterface, int index) throws Exception
    {
        if(!this.contains(layerInterface))
        {
            this.appendProcessors(layerInterface);

            super.append(layerInterface, index);
        }
    }

    private void appendProcessors(AllBinaryLayer layerInterface)
        throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("Append: " + layerInterface, this, "appendProcessors"));
        this.createLayerManagerEvent.setLayerInterface(layerInterface);
        layerManagerEventHandler.fireEvent(this.createLayerManagerEvent);

        LayerProcessor layerProcessorInterface;

        for (int index = this.basicLayerProcessorArray.length; --index >= 0;)
        {
            layerProcessorInterface = basicLayerProcessorArray[index];

            // LogUtil.put(LogFactory.getInstance("Processing BasicLayerProcessor: " +
            // layerProcessorInterface.getClass().getName(), this, "appendProcessors"));

            if (layerProcessorInterface.isProcessorLayer(layerInterface))
            {
                layerProcessorInterface.getLayerInterfaceManager().append(layerInterface);
            }
        }
    }

    public synchronized void remove(AllBinaryLayer layerInterface)
        throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("Remove: " + layerInterface, this, "remove"));

        deleteLayerManagerEvent.setLayerInterface(layerInterface);
        layerManagerEventHandler.fireDeleteEvent(this.deleteLayerManagerEvent);

        LayerProcessor layerProcessorInterface;

        for (int index = this.basicLayerProcessorArray.length; --index >= 0;)
        {
            layerProcessorInterface = basicLayerProcessorArray[index];

            // LogUtil.put(LogFactory.getInstance("Processing BasicLayerProcessor: " + layerProcessorInterface.getClass().getName(), this, "processGame"));

            layerProcessorInterface.getLayerInterfaceManager().remove(layerInterface);
        }

        super.remove(layerInterface);
        
        //PreLogUtil.put("Remove: " + layerInterface + " Size: " + this.getSize(), this, "remove");
    }

    public void process() throws Exception
    {
        LayerProcessor layerProcessorInterface;
        
        int size = this.basicLayerProcessorArray.length;
        for (int index = 0; index < size; index++)
        {
            layerProcessorInterface = basicLayerProcessorArray[index];

            //LogUtil.put(LogFactory.getInstance(layerProcessorInterface.getClass().getName() + CommonStrings.getInstance().SPACE + CommonStrings.getInstance().TOTAL_LABEL +
            // layerProcessorInterface.getLayerInterfaceManager().getSize(), this, CommonStrings.getInstance().PROCESS));

            // if(index != damageIndex)
            layerProcessorInterface.process(this);
            // else
            // {
            // LogUtil.put(LogFactory.getInstance("Processing BasicLayerProcessor: " +
            // layerProcessorInterface.getClass().getName(), this, CommonStrings.getInstance().PROCESS));
            // layerProcessorInterface.processConcurrent(this);
            // }
        }
    }

    public void cleanup() throws Exception
    {
         //PreLogUtil.put(new Integer(this.getSize()).toString(), this, "cleanup");
        LayerProcessor layerProcessorInterface;

        int size = this.basicLayerProcessorArray.length;
        for (int index = 0; index < size; index++)
        {
            layerProcessorInterface = basicLayerProcessorArray[index];
            layerProcessorInterface.getLayerInterfaceManager().cleanup();
        }

        super.cleanup();
    }
}
