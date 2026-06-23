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

import javax.microedition.lcdui.Font;
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
    
    private int textLine2Y;
        
    //protected
    public WaypointInfoHudPaintable() 
    {
        this.keyvalueDrawString = new KeyValueDrawString("Owner: ", this.textX);
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        super.updateMeasurement(graphics);

        final Font font = graphics.getFont();
        this.textLine2Y = (this.y + font.getHeight());
    }
    
    @Override
    public void updateSelectionInfo()
    {
        //WaypointLayer
        final RTSLayer rtsLayer = (RTSLayer) this.rtsLayerP;
        this.setName(rtsLayer.getName());

        this.setAnimationInterface(rtsLayer.getVerticleBuildAnimationInterface());

        this.keyvalueDrawString.update(rtsLayer.getParentLayer().getName());
    }

    public void setRtsLayer(RTSLayer rtsLayer)
    {
        this.rtsLayerP = rtsLayer;
    }

    public PathFindingLayerInterface getRtsLayer()
    {
        return this.rtsLayerP;
    }
    
    
    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);

        this.keyvalueDrawString.paint(graphics, this.textLine2Y);
        
        this.getAnimationInterface().paintXY(graphics, this.imageX, this.y);
    }

}
