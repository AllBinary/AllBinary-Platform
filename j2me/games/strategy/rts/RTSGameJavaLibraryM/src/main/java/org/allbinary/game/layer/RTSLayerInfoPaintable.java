/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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
package org.allbinary.game.layer;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.paint.InitUpdatePaintable;
import org.allbinary.graphics.paint.NullInitUpdatePaintable;

/**
 *
 * @author user
 */
public class RTSLayerInfoPaintable extends InitUpdatePaintable
{
    private InitUpdatePaintable initUpdatePaintable =
        NullInitUpdatePaintable.getInstance();
    
    public RTSLayerInfoPaintable()
        throws Exception
    {
    }

    public void update()
    {
        this.initUpdatePaintable.update();
    }
    
    public void paint(Graphics graphics)
    {
        this.initUpdatePaintable.paint(graphics);
    }

    public void updateRTSLayerInfo(InitUpdatePaintable hudPaintable)
    {
        this.initUpdatePaintable = hudPaintable;
    }
    
    public void updateRTSLayerInfo(SelectionHudPaintable hudPaintable)
    {
        hudPaintable.updateSelectionInfo();
        this.initUpdatePaintable = hudPaintable;
    }
}
