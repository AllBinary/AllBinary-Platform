/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer.waypoint;

import javax.microedition.lcdui.Graphics;
import org.allbinary.game.layer.NullPathFindingLayer;
import org.allbinary.game.layer.PathFindingLayerInterface;

import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.SelectionHudPaintable;

import org.allbinary.graphics.draw.KeyValueDrawString;

/**
 * 
 * @author user
 */
public class WaypointInfoHudPaintable extends SelectionHudPaintable
{
    private final KeyValueDrawString keyvalueDrawString;

    protected PathFindingLayerInterface rtsLayerP = NullPathFindingLayer.NULL_PATH_FINDING_LAYER;
    
    //protected
    public WaypointInfoHudPaintable() 
    {
        keyvalueDrawString = new KeyValueDrawString("Owner: ", this.textX);
    }

    /**
     * @param selectedLayer
     *            the selectedLayer to set
     */
    @Override
    public void updateSelectionInfo()
    {
        //WaypointLayer
        final RTSLayer rtsLayer = (RTSLayer) this.rtsLayerP;
        this.setName(rtsLayer.getName());

        this.setAnimationInterface(rtsLayer.getVerticleBuildAnimationInterface());

        this.keyvalueDrawString.update(rtsLayer.getParentLayer().getName());
    }
    
    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);

        final int textLine2Y = (y + myFont.DEFAULT_CHAR_HEIGHT);
        this.keyvalueDrawString.paint(graphics, textLine2Y);
        
        this.getAnimationInterface().paint(graphics, this.imageX, y);
    }

    public void setRtsLayer(RTSLayer rtsLayer)
    {
        this.rtsLayerP = rtsLayer;
    }

    public PathFindingLayerInterface getRtsLayer()
    {
        return rtsLayerP;
    }
}
