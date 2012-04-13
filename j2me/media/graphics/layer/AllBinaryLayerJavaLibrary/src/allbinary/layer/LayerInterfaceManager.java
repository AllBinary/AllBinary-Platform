package allbinary.layer;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

public class LayerInterfaceManager
{
    private final BasicArrayList list = new BasicArrayList();

    public LayerInterfaceManager()
    {
    }
    
    public boolean contains(AllBinaryLayer layerInterface)
    {
        return this.list.contains(layerInterface);
    }
    
    public void append(AllBinaryLayer layerInterface) throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("append: " + layerInterface, this, "append"));

        this.list.add(layerInterface);
    }

    public void append(AllBinaryLayer layerInterface, int index)
            throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("append: " + layerInterface, this, "append"));

        this.list.add(index, layerInterface);
    }

    public void remove(AllBinaryLayer layerInterface)
            throws Exception
    {
        // LogUtil.put(LogFactory.getInstance("Remove: " + layerInterface, this, "remove"));

        this.list.remove(layerInterface);
    }

    public Layer getLayerAt(int index)
    {
        return (Layer) this.list.objectArray[index];
    }

    public int getSize()
    {
        return this.list.size();
    }

    public void cleanup() throws Exception
    {
        this.list.clear();

        System.gc();
        System.gc();
    }

    public void paint(Graphics g, int x, int y)
    {
        Layer comp;

        for (int index = this.list.size(); --index >= 0;)
        {
            comp = (Layer) list.objectArray[index];
            if (comp.isVisible())
            {
                comp.paint(g);
            }
        }
    }
}