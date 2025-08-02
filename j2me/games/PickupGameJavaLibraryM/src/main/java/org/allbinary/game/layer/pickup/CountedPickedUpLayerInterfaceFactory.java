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
package org.allbinary.game.layer.pickup;

import java.util.Hashtable;

import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class CountedPickedUpLayerInterfaceFactory 
    extends PickedUpLayerInterfaceFactory 
    implements CountedPickedUpLayerInterfaceFactoryInterface
{
    public static final CountedPickedUpLayerInterfaceFactory NULL_COUNTED_PICKUP_LAYER_FACTORY = new CountedPickedUpLayerInterfaceFactory(PickedUpLayerTypeFactory.getInstance().NONE, IconLayer.NULL_ICON_LAYER, NullAnimationFactory.getFactoryInstance().getInstance(0));

    private final int id;

    public CountedPickedUpLayerInterfaceFactory(
            PickedUpLayerType pickeUpLayerType, IconLayer iconLayer,
            Animation animationInterface)
    {
        super(pickeUpLayerType, iconLayer, animationInterface);

        //Automatically adds pickedup layer as to Singleton pool for
        //MakeCountedPartsSingletonArrayFactory
        //that creates the parts list or array
        BasicArrayList list = CountedPickedUpLayerInterfaceFactoryPool.getInstance().getList();
        this.id = list.size();

        list.add(this);
    }

    @Override
    public AllBinaryLayer getInstance(Hashtable hashtable, int x, int y, int z)
    throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }
    
    @Override
    public int getTotal()
    {
        return -1;
    }

    @Override
    public int getId()
    {
        return id;
    }
}