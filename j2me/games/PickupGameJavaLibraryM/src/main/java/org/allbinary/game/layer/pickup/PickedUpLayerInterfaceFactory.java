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

import org.allbinary.animation.Animation;
import org.allbinary.layer.AllBinaryLayer;

public class PickedUpLayerInterfaceFactory implements
        PickedUpLayerInterfaceFactoryInterface {

    private final PickedUpLayerType pickedUpLayerType;
    private final IconLayer iconLayer;
    private final Animation animationInterface;

    protected PickedUpLayerInterfaceFactory(
            PickedUpLayerType pickeUpLayerType,
            IconLayer iconLayer, Animation animationInterface) 
    {

        this.pickedUpLayerType = pickeUpLayerType;
        this.animationInterface = animationInterface;
        this.iconLayer = iconLayer;

    }

    @Override
    public AllBinaryLayer getIconLayer() {
        return iconLayer;
    }

    @Override
    public Animation getAnimationInterface() {
        return animationInterface;
    }

    @Override
    public PickedUpLayerType getPickedUpLayerType() {
        return pickedUpLayerType;
    }
}
