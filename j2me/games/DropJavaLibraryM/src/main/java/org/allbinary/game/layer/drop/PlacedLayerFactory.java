package org.allbinary.game.layer.drop;

import org.allbinary.layer.AllBinaryLayerFactoryInterface;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

public class PlacedLayerFactory
{
    private static final PlacedLayerFactory instance = new PlacedLayerFactory();

    private final BasicArrayList list = new BasicArrayList();

    private PlacedLayerFactory()
    {
    }

    public void clear()
    {
        this.list.clear();
    }

    public static PlacedLayerFactory getInstance()
    {
        return instance;
    }

    public int getSize()
    {
        return list.size();
    }

    private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();

    public AllBinaryLayerFactoryInterface getRandomInstance()
    {
        return (AllBinaryLayerFactoryInterface) this.basicArrayListUtil.getRandom(this.list);
    }

    public void add(AllBinaryLayerFactoryInterface layerInterfaceFactoryInterface)
    {
        this.list.add(layerInterfaceFactoryInterface);
    }
}
