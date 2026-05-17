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

import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.game.multiplayer.layer.RemoteInfo;
import org.allbinary.game.tick.TickableInterface;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.view.ViewPosition;

public class FallingPickupLayer 
extends PickupLayer 
implements TickableInterface
{
    private static final String NAME = "FallingPickupLayer";

    public FallingPickupLayer(final RemoteInfo remoteInfo) throws Exception
    {
        super(FallingPickupLayer.NAME, remoteInfo, 0, CountedPickedUpLayerInterfaceFactory.NULL_COUNTED_PICKUP_LAYER_FACTORY, NullAnimationFactory.getFactoryInstance().getInstance(0), new Rectangle(PointFactory.getInstance().ZERO_ZERO, 0, 0), ViewPosition.getInstanceD());
    }

    @Override
    public void processTick(AllBinaryLayerManager allBinaryLayerManager)
    {
        this.setPosition(this.x, this.y + 1, this.z);
    }

    @Override
    public boolean implmentsTickableInterface()
    {
        return true;
    }
}
