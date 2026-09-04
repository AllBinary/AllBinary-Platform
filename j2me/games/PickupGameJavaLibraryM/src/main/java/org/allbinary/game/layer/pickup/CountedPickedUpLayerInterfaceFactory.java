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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.ABHashtable;
import org.allbinary.util.BasicArrayList;

@JsType
public class CountedPickedUpLayerInterfaceFactory 
    extends PickedUpLayerInterfaceFactory 
    implements CountedPickedUpLayerInterfaceFactoryInterface
{
    @JsProperty
    public static final CountedPickedUpLayerInterfaceFactory NULL_COUNTED_PICKUP_LAYER_FACTORY = new CountedPickedUpLayerInterfaceFactory(PickedUpLayerTypeFactory.getInstance().NONE, IconLayer.NULL_ICON_LAYER, NullAnimationFactory.getFactoryInstance().getInstance(0));

    private final int id;

    @JsConstructor
    public CountedPickedUpLayerInterfaceFactory(
            final PickedUpLayerType pickeUpLayerType, final IconLayer iconLayer,
            final Animation animationInterface)
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
    @JsMethod
    public AllBinaryLayer getNextInstance(final ABHashtable hashtable, final int x, final int y, final int z)
    throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }
    
    @Override
    @JsMethod
    public int getTotal()
    {
        return -1;
    }

    @Override
    @JsMethod
    public int getId()
    {
        return this.id;
    }
}