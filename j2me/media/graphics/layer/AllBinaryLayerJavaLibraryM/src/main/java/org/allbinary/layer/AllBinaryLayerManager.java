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
        super(LayerManagerNoDebug.getInstance());
        //super(LayerManagerLogging.getInstance());
    }

    // private int damageIndex = -1;
    protected LayerProcessor[] getLayerProcessorArray()
    {
        return this.basicLayerProcessorArray;
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
        //this.logUtil.putF(this.commonLabels.TOTAL_LABEL + this.getSize(), this, "log");

        int size = this.basicLayerProcessorArray.length;
        for (int index = 0; index < size; index++)
        {
            LayerProcessor layerProcessorInterface = this.basicLayerProcessorArray[index];

            //this.logUtil.putF(layerProcessorInterface.getClass().getName() + CommonSeps.getInstance().SPACE + this.commonLabels.TOTAL_LABEL +
            //    layerProcessorInterface.getLayerManager().getSize(), this, "log");
        }
    }
    
    @Override
    public void append(final AllBinaryLayer layerInterface) throws Exception
    {
        if(!this.contains(layerInterface))
        {
            this.appendProcessors(layerInterface);

            super.append(layerInterface);

          //PrelogUtil.putF("Append: " + layerInterface + " Size: " + this.getSize(), this, "append");
        }
    }

    @Override
    public void appendAt(final AllBinaryLayer layerInterface, final int index) throws Exception
    {
        if(!this.contains(layerInterface))
        {
            this.appendProcessors(layerInterface);

            super.appendAt(layerInterface, index);
        }
    }

    private void appendProcessors(final AllBinaryLayer layerInterface)
        throws Exception
    {
        //this.logUtil.putF("Append: " + layerInterface, this, "appendProcessors");
        this.createLayerManagerEvent.setLayerInterface(layerInterface);
        this.layerManagerEventHandler.fireEvent(this.createLayerManagerEvent);

        LayerProcessor layerProcessorInterface;

        //this.logUtil.putF("Total BasicLayerProcessors: " + this.basicLayerProcessorArray.length, this, "appendProcessors");
        for (int index = this.basicLayerProcessorArray.length; --index >= 0;)
        {
            layerProcessorInterface = this.basicLayerProcessorArray[index];

            //this.logUtil.putF("Processing BasicLayerProcessor: " + layerProcessorInterface.getClass().getName(), this, "appendProcessors");

            if (layerProcessorInterface.isProcessorLayer(layerInterface))
            {
                //this.logUtil.putF("Appending BasicLayerProcessor: " + layerProcessorInterface.getClass().getName(), this, "appendProcessors");
                layerProcessorInterface.getLayerManager().append(layerInterface);
            }
        }
    }

    @Override
    public synchronized void remove(final AllBinaryLayer layerInterface)
        throws Exception
    {
        //this.logUtil.putF("Remove: " + layerInterface, this, "remove");
        
        this.deleteLayerManagerEvent.setLayerInterface(layerInterface);
        this.layerManagerEventHandler.fireDeleteEvent(this.deleteLayerManagerEvent);

        LayerProcessor layerProcessorInterface;

        for (int index = this.basicLayerProcessorArray.length; --index >= 0;)
        {
            layerProcessorInterface = this.basicLayerProcessorArray[index];

            //this.logUtil.putF("Processing BasicLayerProcessor: " + layerProcessorInterface.getClass().getName(), this, "processGame");

            layerProcessorInterface.getLayerManager().remove(layerInterface);
        }

        super.remove(layerInterface);
        
        //PrelogUtil.putF("Remove: " + layerInterface + " Size: " + this.getSize(), this, "remove");
    }

    public void process() throws Exception
    {
        
        LayerProcessor layerProcessorInterface;
        
        int size = this.basicLayerProcessorArray.length;
        
        //this.logUtil.putF(commonStrings.START + size, this, commonStrings.PROCESS);
        
        for (int index = 0; index < size; index++)
        {
            layerProcessorInterface = this.basicLayerProcessorArray[index];

            //this.logUtil.putF(layerProcessorInterface.getClass().getName() + CommonSeps.getInstance().SPACE + CommonLabels.getInstance().TOTAL_LABEL + 
            //        layerProcessorInterface.getLayerManager().getSize(), this, commonStrings.PROCESS);

            // if(index != damageIndex)
            layerProcessorInterface.process(this);
            // else
            // {
            // this.logUtil.putF("Processing BasicLayerProcessor: " +
            // layerProcessorInterface.getClass().getName(), this, commonStrings.PROCESS);
            // layerProcessorInterface.processConcurrent(this);
            // }
        }
    }

    @Override
    public void cleanup() throws Exception
    {
         //PrelogUtil.putF(new Integer(this.getSize()).toString(), this, this.commonStrings.CLEANUP);
        LayerProcessor layerProcessorInterface;

        int size = this.basicLayerProcessorArray.length;
        for (int index = 0; index < size; index++)
        {
            layerProcessorInterface = this.basicLayerProcessorArray[index];
            layerProcessorInterface.getLayerManager().cleanup();
        }

        super.cleanup();
    }
}
