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

    private RTSLayer rtsLayer;
    
    protected WaypointInfoHudPaintable() 
    {
        keyvalueDrawString = new KeyValueDrawString("Owner: ", this.textX);
    }

    /**
     * @param selectedLayer
     *            the selectedLayer to set
     */
    public void updateSelectionInfo()
    {
        this.setName(this.getRtsLayer().getName());

        this.setAnimationInterface(this.getRtsLayer().getVerticleBuildAnimationInterface());

        this.keyvalueDrawString.update(((WaypointLayer) this.getRtsLayer()).getParentLayer().getName());
    }
    
    public void paint(Graphics graphics)
    {
        super.paint(graphics);

        final int textLine2Y = (y + myFont.DEFAULT_CHAR_HEIGHT);
        this.keyvalueDrawString.paint(graphics, textLine2Y);
        
        this.getAnimationInterface().paint(graphics, this.imageX, y);
    }

    public void setRtsLayer(RTSLayer rtsLayer)
    {
        this.rtsLayer = rtsLayer;
    }

    protected RTSLayer getRtsLayer()
    {
        return rtsLayer;
    }
}
