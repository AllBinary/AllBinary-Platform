/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 */

package org.allbinary.game.layer.waypoint;

import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.SelectionHudPaintable;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.ProceduralAnimationInterfaceFactoryInterface;
import org.allbinary.game.identification.Group;
import org.allbinary.graphics.Rectangle;
import org.allbinary.game.multiplayer.layer.RemoteInfo;

public class VisualWaypointLayer
    extends WaypointLayer
{
    public VisualWaypointLayer(
            final RemoteInfo remoteInfo,
            final AdvancedRTSGameLayer parentLayer,
            final Group[] groupInterface,
            final String rootName,
            final String name,
            final AnimationInterfaceFactoryInterface animationInterfaceFactoryInterface,
            final ProceduralAnimationInterfaceFactoryInterface proceduralAnimationInterfaceFactoryInterface,
            final Rectangle rectangle, 
            final int x, final int y)
            throws Exception
    {
        super(remoteInfo,
                parentLayer,
                groupInterface,
                rootName, name,
                animationInterfaceFactoryInterface,
                proceduralAnimationInterfaceFactoryInterface,
                rectangle, 
                x, y);
    }

    @Override
    public SelectionHudPaintable createHudPaintable()
    {
        WaypointInfoHudPaintable rtsLayerHudPaintable = 
            WaypointInfoHudPaintableSingleton.getInstance();
        
        rtsLayerHudPaintable.setBasicColorP(this.allBinaryGameLayerManagerP.getForegroundBasicColor());
        rtsLayerHudPaintable.setRtsLayer(this);
        
        return rtsLayerHudPaintable;
    }

    @Override
    public SelectionHudPaintable getHudPaintable()
    {
        WaypointInfoHudPaintable rtsLayerHudPaintable = 
            WaypointInfoHudPaintableSingleton.getInstance();

        return rtsLayerHudPaintable;
    }

}
