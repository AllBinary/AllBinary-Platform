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

import org.allbinary.animation.Animation;
import org.allbinary.layer.AllBinaryLayer;

/**
 *
 * @author User
 */
public class PickedUpLayerInterfaceFactoryBase implements
        PickedUpLayerInterfaceFactoryInterface {

    public static final PickedUpLayerInterfaceFactoryBase NULL_PICKED_UP_LAYER_FACTORY_BASE = new PickedUpLayerInterfaceFactoryBase();
    
    @Override
    public AllBinaryLayer getIconLayer() {
        throw new RuntimeException();
    }

    @Override
    public Animation getAnimationInterface() {
        throw new RuntimeException();
    }

    @Override
    public PickedUpLayerType getPickedUpLayerType() {
        throw new RuntimeException();
    }

}
