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
package org.allbinary.layer;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonLabels;

import org.allbinary.layer.event.LayerManagerEvent;
import org.allbinary.layer.event.LayerManagerEventHandler;

public class AllBinaryLayerManager extends LayerManager
{
    //private final CommonLabels commonLabels = CommonLabels.getInstance();
    
    private final LayerManagerEventHandler layerManagerEventHandler = 
        LayerManagerEventHandler.getInstance();
    
    private final LayerManagerEvent createLayerManagerEvent = new LayerManagerEvent(this, this.layerManagerEventHandler.CREATE);
    private final LayerManagerEvent deleteLayerManagerEvent = new LayerManagerEvent(this, this.layerManagerEventHandler.DELETE);
    private LayerProcessor[] basicLayerProcessorArray = new LayerProcessor[0];

    protected AllBinaryLayerManager()
    {
        //super(LayerManagerLogging.getInstance());
    }

    // private int damageIndex = -1;
    protected LayerProcessor[] getLayerProcessorArray()
    {
        return basicLayerProcessorArray;
    }

    // , int damageIndex
    public void setLayerProcessorArray(final LayerProcessor[] layerProcessorArray)
    {
        this.basicLayerProcessorArray = layerProcessorArray;
        // this.damageIndex = damageIndex;
        //this.log();
    }

    public final void log()
    {
        //LogUtil.put(LogFactory.getInstance(this.commonLabels.TOTAL_LABEL + this.getSize(), this, "log"));

        int size = this.basicLayerProcessorArray.length;
        for (int index = 0; index < size; index++)
        {
            LayerProcessor layerProcessorInterface = basicLayerProcessorArray[index];

            //LogUtil.put(LogFactory.getInstance(layerProcessorInterface.getClass().getName() + CommonSeps.getInstance().SPACE + this.commonLabels.TOTAL_LABEL +
            //    layerProcessorInterface.getLayerManager().getSize(), this, "log"));
        }
    }
    
    public void append(final AllBinaryLayer layerInterface) throws Exception
    {
        if(!this.contains(layerInterface))
        {
            this.appendProcessors(layerInterface);

            super.append(layerInterface);

          //PreLogUtil.put("Append: " + layerInterface + " Size: " + this.getSize(), this, "append");
        }
    }

    public void append(final AllBinaryLayer layerInterface, final int index) throws Exception
    {
        if(!this.contains(layerInterface))
        {
            this.appendProcessors(layerInterface);

            super.append(layerInterface, index);
        }
    }

    private void appendProcessors(final AllBinaryLayer layerInterface)
        throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("Append: " + layerInterface, this, "appendProcessors"));
        this.createLayerManagerEvent.setLayerInterface(layerInterface);
        layerManagerEventHandler.fireEvent(this.createLayerManagerEvent);

        LayerProcessor layerProcessorInterface;

        //LogUtil.put(LogFactory.getInstance("Total BasicLayerProcessors: " + this.basicLayerProcessorArray.length, this, "appendProcessors"));
        for (int index = this.basicLayerProcessorArray.length; --index >= 0;)
        {
            layerProcessorInterface = basicLayerProcessorArray[index];

            //LogUtil.put(LogFactory.getInstance("Processing BasicLayerProcessor: " + layerProcessorInterface.getClass().getName(), this, "appendProcessors"));

            if (layerProcessorInterface.isProcessorLayer(layerInterface))
            {
                //LogUtil.put(LogFactory.getInstance("Appending BasicLayerProcessor: " + layerProcessorInterface.getClass().getName(), this, "appendProcessors"));
                layerProcessorInterface.getLayerManager().append(layerInterface);
            }
        }
    }

    public synchronized void remove(final AllBinaryLayer layerInterface)
        throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("Remove: " + layerInterface, this, "remove"));
        
        deleteLayerManagerEvent.setLayerInterface(layerInterface);
        layerManagerEventHandler.fireDeleteEvent(this.deleteLayerManagerEvent);

        LayerProcessor layerProcessorInterface;

        for (int index = this.basicLayerProcessorArray.length; --index >= 0;)
        {
            layerProcessorInterface = basicLayerProcessorArray[index];

            //LogUtil.put(LogFactory.getInstance("Processing BasicLayerProcessor: " + layerProcessorInterface.getClass().getName(), this, "processGame"));

            layerProcessorInterface.getLayerManager().remove(layerInterface);
        }

        super.remove(layerInterface);
        
        //PreLogUtil.put("Remove: " + layerInterface + " Size: " + this.getSize(), this, "remove");
    }

    public void process() throws Exception
    {
        
        LayerProcessor layerProcessorInterface;
        
        int size = this.basicLayerProcessorArray.length;
        
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START + size, this, CommonStrings.getInstance().PROCESS));
        
        for (int index = 0; index < size; index++)
        {
            layerProcessorInterface = basicLayerProcessorArray[index];

            //LogUtil.put(LogFactory.getInstance(layerProcessorInterface.getClass().getName() + CommonSeps.getInstance().SPACE + CommonLabels.getInstance().TOTAL_LABEL + 
            //        layerProcessorInterface.getLayerManager().getSize(), this, CommonStrings.getInstance().PROCESS));

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
            layerProcessorInterface.getLayerManager().cleanup();
        }

        super.cleanup();
    }
}
