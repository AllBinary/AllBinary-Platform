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

import jsinterop.annotations.JsType;

import org.allbinary.game.identification.Group;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.graphics.Rectangle;
import org.allbinary.view.ViewPositionBase;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class MultiPlayerGameLayer 
extends CollidableDestroyableDamageableLayer 
implements MultiPlayerGameLayerInterface
{
    @JsConstructor
    public MultiPlayerGameLayer(final RemoteInfo remoteInfo,
            final Group[] groupInterface, final String name, final Rectangle layerInfo, final ViewPositionBase viewPosition)
    {
        super(groupInterface, name, layerInfo, viewPosition);
    }
    
    @Override
    @JsMethod
    public MultiplayerBehavior getMultiplayerBehavior()
    {
        return MultiplayerBehavior.NULL_MULTIPLAYER_BEHAVIOR;
    }
    
    @JsMethod
    public void setPingInfo(boolean show)
    {
    }    

    @JsMethod
    protected void setFiring(short firing)
    {
    }
    
    @JsMethod
    protected short getFiring()
    {
        return 0;
    }
}
