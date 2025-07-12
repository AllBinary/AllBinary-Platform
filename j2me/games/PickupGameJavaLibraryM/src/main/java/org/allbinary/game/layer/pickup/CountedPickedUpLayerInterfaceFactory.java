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
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class CountedPickedUpLayerInterfaceFactory 
    extends PickedUpLayerInterfaceFactory 
    implements CountedPickedUpLayerInterfaceFactoryInterface
{
    private final int id;

    protected CountedPickedUpLayerInterfaceFactory(
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

    public AllBinaryLayer getInstance(Hashtable hashtable, int x, int y, int z)
    throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }
    
    public int getTotal()
    {
        return -1;
    }

    public int getId()
    {
        return id;
    }
}