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

import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonLabels;

import org.allbinary.layer.event.LayerManagerEvent;
import org.allbinary.layer.event.LayerManagerEventHandler;

public class AllBinaryLayerManager extends LayerManager
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
        //logUtil.put(this.commonLabels.TOTAL_LABEL + this.getSize(), this, "log");

        int size = this.basicLayerProcessorArray.length;
        for (int index = 0; index < size; index++)
        {
            LayerProcessor layerProcessorInterface = basicLayerProcessorArray[index];

            //logUtil.put(layerProcessorInterface.getClass().getName() + CommonSeps.getInstance().SPACE + this.commonLabels.TOTAL_LABEL +
            //    layerProcessorInterface.getLayerManager().getSize(), this, "log");
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
        //logUtil.put("Append: " + layerInterface, this, "appendProcessors");
        this.createLayerManagerEvent.setLayerInterface(layerInterface);
        layerManagerEventHandler.fireEvent(this.createLayerManagerEvent);

        LayerProcessor layerProcessorInterface;

        //logUtil.put("Total BasicLayerProcessors: " + this.basicLayerProcessorArray.length, this, "appendProcessors");
        for (int index = this.basicLayerProcessorArray.length; --index >= 0;)
        {
            layerProcessorInterface = basicLayerProcessorArray[index];

            //logUtil.put("Processing BasicLayerProcessor: " + layerProcessorInterface.getClass().getName(), this, "appendProcessors");

            if (layerProcessorInterface.isProcessorLayer(layerInterface))
            {
                //logUtil.put("Appending BasicLayerProcessor: " + layerProcessorInterface.getClass().getName(), this, "appendProcessors");
                layerProcessorInterface.getLayerManager().append(layerInterface);
            }
        }
    }

    public synchronized void remove(final AllBinaryLayer layerInterface)
        throws Exception
    {
        //logUtil.put("Remove: " + layerInterface, this, "remove");
        
        deleteLayerManagerEvent.setLayerInterface(layerInterface);
        layerManagerEventHandler.fireDeleteEvent(this.deleteLayerManagerEvent);

        LayerProcessor layerProcessorInterface;

        for (int index = this.basicLayerProcessorArray.length; --index >= 0;)
        {
            layerProcessorInterface = basicLayerProcessorArray[index];

            //logUtil.put("Processing BasicLayerProcessor: " + layerProcessorInterface.getClass().getName(), this, "processGame");

            layerProcessorInterface.getLayerManager().remove(layerInterface);
        }

        super.remove(layerInterface);
        
        //PreLogUtil.put("Remove: " + layerInterface + " Size: " + this.getSize(), this, "remove");
    }

    public void process() throws Exception
    {
        
        LayerProcessor layerProcessorInterface;
        
        int size = this.basicLayerProcessorArray.length;
        
        //logUtil.put(commonStrings.START + size, this, commonStrings.PROCESS);
        
        for (int index = 0; index < size; index++)
        {
            layerProcessorInterface = basicLayerProcessorArray[index];

            //logUtil.put(layerProcessorInterface.getClass().getName() + CommonSeps.getInstance().SPACE + CommonLabels.getInstance().TOTAL_LABEL + 
            //        layerProcessorInterface.getLayerManager().getSize(), this, commonStrings.PROCESS);

            // if(index != damageIndex)
            layerProcessorInterface.process(this);
            // else
            // {
            // logUtil.put("Processing BasicLayerProcessor: " +
            // layerProcessorInterface.getClass().getName(), this, commonStrings.PROCESS);
            // layerProcessorInterface.processConcurrent(this);
            // }
        }
    }

    public void cleanup() throws Exception
    {
         //PreLogUtil.put(new Integer(this.getSize()).toString(), this, this.commonStrings.CLEANUP);
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
