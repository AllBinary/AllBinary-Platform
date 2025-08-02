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
package org.allbinary.game.combat.destroy;

import org.allbinary.game.layer.CollidableCompositeLayer;

public class DestroyableSimpleBehavior extends DestroyableBaseBehavior
{
    protected final CollidableCompositeLayer ownerLayer;
    
    private boolean destroyed = false;
    
    public DestroyableSimpleBehavior(CollidableCompositeLayer ownerLayer)
    {
        this.ownerLayer = ownerLayer;
    }
    
    @Override
    public boolean isDestroyed()
    {
        return this.destroyed;
    }

    @Override
    public void setDestroyed(boolean destroyed)
    {
        this.destroyed = destroyed;
        
        if (this.isDestroyed())
        {
            DestroyedLayerProcessor.getInstance().add(this.ownerLayer);
        }
    }
}
