/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class PickedUpLayerInterfaceFactoryBase implements
        PickedUpLayerInterfaceFactoryInterface {

    @JsProperty
    public static final PickedUpLayerInterfaceFactoryBase NULL_PICKED_UP_LAYER_FACTORY_BASE = new PickedUpLayerInterfaceFactoryBase();
    
    @Override
    @JsMethod
    public AllBinaryLayer getIconLayer() {
        throw new RuntimeException();
    }

    @Override
    @JsMethod
    public Animation getAnimationInterface() {
        throw new RuntimeException();
    }

    @Override
    @JsMethod
    public PickedUpLayerType getPickedUpLayerType() {
        throw new RuntimeException();
    }

}
