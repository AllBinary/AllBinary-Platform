package org.allbinary.layer;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.SynchObject;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;
import org.allbinary.logic.ABSystemWrapper;


@JsType
public class LayerManager
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final ABSystemWrapper systemWrapper = ABSystemWrapper.getInstance();
    
    private final LayerManagerLoggingBase layerManagerLogging;
    private final BasicArrayList list = new BasicArrayListD();
    private final SynchObject object = new SynchObject();

    @JsConstructor
    public LayerManager(final LayerManagerLoggingBase layerManagerLogging)
    {
        this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
        
        this.layerManagerLogging = layerManagerLogging;
    }
    
    @JsMethod
    public boolean contains(final AllBinaryLayer layerInterface)
    {
        return this.list.contains(layerInterface);
    }

    @JsMethod
    public void update(final AllBinaryLayer layerInterface) throws Exception
    {
        boolean had = this.list.remove(layerInterface);
        
        if(had) {
            AllBinaryLayer nextLayerInterface;
            final int size = this.list.size();
            for (int index = 0; index < size; index++) {
                nextLayerInterface = (AllBinaryLayer) this.list.get(index);
                if (layerInterface.getZP() > nextLayerInterface.getZP()) {
                    this.list.addAt(index, layerInterface);
                    return;
                }
            }
            this.list.add(layerInterface);
        }
    }

    @JsMethod
    public void insert(final AllBinaryLayer layerInterface) throws Exception
    {
        AllBinaryLayer nextLayerInterface;
        final int size = this.list.size();
        for(int index = 0; index < size; index++) {
            nextLayerInterface = (AllBinaryLayer) this.list.get(index);
            if(layerInterface.getZP() > nextLayerInterface.getZP()) {
                this.appendAt(layerInterface, index);
                return;
            }
        }
        this.append(layerInterface);
    }
    
    @JsMethod
    public void append(final AllBinaryLayer layerInterface) throws Exception
    {
        this.layerManagerLogging.append(layerInterface);

        this.list.add(layerInterface);
    }

    @JsMethod
    public void appendAt(final AllBinaryLayer layerInterface, final int index)
            throws Exception
    {
        this.layerManagerLogging.appendAt(layerInterface, index);

        this.list.addAt(index, layerInterface);
    }
    
    @JsMethod
    public void remove(final AllBinaryLayer layerInterface)
            throws Exception
    {
    	synchronized(this.object)
    	{
            this.layerManagerLogging.remove(layerInterface);
    	    final boolean result = 
                    this.list.remove(layerInterface);
            this.layerManagerLogging.removeResult(this, layerInterface, result);
    	}
    }

    @JsMethod
    public Layer getLayerAt(final int index)
    {
        return (Layer) this.list.objectArray[index];
    }

    @JsMethod
    public int getSize()
    {
        return this.list.size();
    }

    @JsMethod
    public void cleanup() throws Exception
    {
        //this.logUtil.putF(this.commonStrings.CLEANUP, this, this.commonStrings.CLEANUP);
        
    	synchronized(this.object)
    	{
    		this.list.clear();
                this.layerManagerLogging.clear();

                this.systemWrapper.gc();
                this.systemWrapper.gc();
    	}
    }

    //private boolean isFirst = true;
    //private final String PAINT = canvasStrings.PAINT;
    
    @JsMethod
    public void paint(Graphics g, int x, int y)
    {
    	synchronized(this.object)
    	{
            //if(this.isFirst) {
                //this.isFirst = false;
                //this.logUtil.putF(this.commonStrings.START, this, PAINT);
            //}
            
        Layer comp;

            //if(this.isFirst) {
                //if(this.list.size() > 0) {
                    //this.isFirst = false;
                //}
                //this.logUtil.putF(this.commonStrings.START, this, "size: " + this.list.size());
            //}
        
        for (int index = this.list.size(); --index >= 0;)
        {
            comp = (Layer) this.list.objectArray[index];
            if (comp != null && comp.isVisible())
            {
                comp.paint(g);
            }
        }
    	}
    }
}