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
package org.allbinary.game.multiplayer.layer;

import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.graphics.Rectangle;
import org.allbinary.view.ViewPosition;

public class MultiPlayerGameLayer 
extends CollidableDestroyableDamageableLayer 
implements MultiPlayerGameLayerInterface
{
    public MultiPlayerGameLayer(RemoteInfo remoteInfo,
            Group[] groupInterface, Rectangle layerInfo, ViewPosition viewPosition)
    {
        super(groupInterface, layerInfo, viewPosition);
    }
    
    public MultiplayerBehavior getMultiplayerBehavior()
    {
        return null;
    }
    
    public void setPingInfo(boolean show)
    {
    }    

    protected void setFiring(short firing)
    {
    }
    
    protected short getFiring()
    {
        return 0;
    }
}
