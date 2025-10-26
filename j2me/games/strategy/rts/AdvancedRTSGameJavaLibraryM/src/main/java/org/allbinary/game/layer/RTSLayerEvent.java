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
package org.allbinary.game.layer;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class RTSLayerEvent extends AllBinaryEventObject
{
    private PathFindingLayerInterface rtsLayer = NullPathFindingLayer.NULL_PATH_FINDING_LAYER;

    public RTSLayerEvent(PathFindingLayerInterface rtsLayer)
    {
        super(rtsLayer);
    }

    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("RTSLayerEvent: \n");
        stringBuffer.append("RTSLayer: ");
        stringBuffer.append(this.getRtsLayer().toString());

        return stringBuffer.toString();
    }

    /**
     * @return the rtsLayer
     */
    public PathFindingLayerInterface getRtsLayer()
    {
        return rtsLayer;
    }

    /**
     * @param rtsLayer the rtsLayer to set
     */
    public void setRtsLayer(PathFindingLayerInterface rtsLayer)
    {
        this.rtsLayer = rtsLayer;
    }

}
