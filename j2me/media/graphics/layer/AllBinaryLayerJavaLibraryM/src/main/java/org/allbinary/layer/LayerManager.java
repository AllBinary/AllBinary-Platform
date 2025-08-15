package org.allbinary.layer;

import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.SynchObject;
import org.allbinary.util.BasicArrayList;

public class LayerManager
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final LayerManagerLoggingBase layerManagerLogging;
    private final BasicArrayList list = new BasicArrayList();
    private final SynchObject object = new SynchObject();

    public LayerManager(final LayerManagerLogging layerManagerLogging)
    {
        logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
        
        this.layerManagerLogging = layerManagerLogging;
    }

    public LayerManager()
    {
        this.layerManagerLogging = LayerManagerNoDebug.getInstance();
    }
    
    public boolean contains(final AllBinaryLayer layerInterface)
    {
        return this.list.contains(layerInterface);
    }
    
    public void insert(final AllBinaryLayer layerInterface) throws Exception
    {
        AllBinaryLayer nextLayerInterface;
        final int size = this.list.size();
        for(int index = 0; index < size; index++) {
            nextLayerInterface = (AllBinaryLayer) this.list.get(index);
            if(layerInterface.getZP() > nextLayerInterface.getZP()) {
                this.append(layerInterface, index);
                return;
            }
        }
        this.append(layerInterface);
    }
    
    public void append(final AllBinaryLayer layerInterface) throws Exception
    {
        this.layerManagerLogging.append(layerInterface);

        this.list.add(layerInterface);
    }

    public void append(final AllBinaryLayer layerInterface, final int index)
            throws Exception
    {
        this.layerManagerLogging.append(layerInterface, index);

        this.list.add(index, layerInterface);
    }
    
    public void remove(final AllBinaryLayer layerInterface)
            throws Exception
    {
    	synchronized(object)
    	{
            this.layerManagerLogging.remove(layerInterface);
    	    final boolean result = 
                    this.list.remove(layerInterface);
            this.layerManagerLogging.remove(this, layerInterface, result);
    	}
    }

    public Layer getLayerAt(final int index)
    {
        return (Layer) this.list.objectArray[index];
    }

    public int getSize()
    {
        return this.list.size();
    }

    public void cleanup() throws Exception
    {
        //logUtil.put(this.commonStrings.CLEANUP, this, this.commonStrings.CLEANUP);
        
    	synchronized(object)
    	{
    		this.list.clear();
                this.layerManagerLogging.clear();

    		System.gc();
    		System.gc();
    	}
    }

    //private boolean isFirst = true;
    //private final String PAINT = canvasStrings.PAINT;
    
    public void paint(Graphics g, int x, int y)
    {
    	synchronized(object)
    	{
            //if(this.isFirst) {
                //this.isFirst = false;
                //logUtil.put(commonStrings.START, this, PAINT);
            //}
            
        Layer comp;

            //if(this.isFirst) {
                //if(this.list.size() > 0) {
                    //this.isFirst = false;
                //}
                //logUtil.put(commonStrings.START, this, "size: " + this.list.size());
            //}
        
        for (int index = this.list.size(); --index >= 0;)
        {
            comp = (Layer) list.objectArray[index];
            if (comp != null && comp.isVisible())
            {
                comp.paint(g);
            }
        }
    	}
    }
}