package org.allbinary.layer;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

public class LayerInterfaceManager
{
    private final LayerInterfaceManagerLoggingBase layerInterfaceManagerLogging;
    private final BasicArrayList list = new BasicArrayList();
    private final Object object = new Object();

    public LayerInterfaceManager(final LayerInterfaceManagerLogging layerInterfaceManagerLogging)
    {
        this.layerInterfaceManagerLogging = layerInterfaceManagerLogging;
    }

    public LayerInterfaceManager()
    {
        this.layerInterfaceManagerLogging = LayerInterfaceManagerNoDebug.getInstance();
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
            if(layerInterface.z < nextLayerInterface.z) {
                this.append(layerInterface, index);
                return;
            }
        }
        this.append(layerInterface);
    }
    
    public void append(final AllBinaryLayer layerInterface) throws Exception
    {
        this.layerInterfaceManagerLogging.append(layerInterface);

        this.list.add(layerInterface);
    }

    public void append(final AllBinaryLayer layerInterface, final int index)
            throws Exception
    {
        this.layerInterfaceManagerLogging.append(layerInterface, index);

        this.list.add(index, layerInterface);
    }
    
    public void remove(final AllBinaryLayer layerInterface)
            throws Exception
    {
    	synchronized(object)
    	{
            this.layerInterfaceManagerLogging.remove(layerInterface);
    	    final boolean result = 
                    this.list.remove(layerInterface);
            this.layerInterfaceManagerLogging.remove(layerInterface, result);
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
        //LogUtil.put(LogFactory.getInstance("cleanup", this, "cleanup"));
        
    	synchronized(object)
    	{
    		this.list.clear();

    		System.gc();
    		System.gc();
    	}
    }

    //private boolean isFirst = true;
    //private final String PAINT = "paint";
    
    public void paint(Graphics g, int x, int y)
    {
    	synchronized(object)
    	{
            //if(this.isFirst) {
                //this.isFirst = false;
                //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, PAINT));
            //}
            
        Layer comp;

            //if(this.isFirst) {
                //if(this.list.size() > 0) {
                    //this.isFirst = false;
                //}
                //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "size: " + this.list.size()));
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