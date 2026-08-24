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

import org.allbinary.animation.Animation;
import org.allbinary.layer.AllBinaryLayer;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class PickedUpLayerInterfaceFactory extends PickedUpLayerInterfaceFactoryBase {

    private final PickedUpLayerType pickedUpLayerType;
    private final IconLayer iconLayer;
    private final Animation animationInterface;

    @JsConstructor
    protected PickedUpLayerInterfaceFactory(
            PickedUpLayerType pickeUpLayerType,
            IconLayer iconLayer, Animation animationInterface) 
    {

        this.pickedUpLayerType = pickeUpLayerType;
        this.animationInterface = animationInterface;
        this.iconLayer = iconLayer;

    }

    @Override
    @JsMethod
    public AllBinaryLayer getIconLayer() {
        return this.iconLayer;
    }

    @Override
    @JsMethod
    public Animation getAnimationInterface() {
        return this.animationInterface;
    }

    @Override
    @JsMethod
    public PickedUpLayerType getPickedUpLayerType() {
        return this.pickedUpLayerType;
    }
}
