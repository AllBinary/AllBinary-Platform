/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
import org.allbinary.graphics.GPoint;
import org.allbinary.layer.Layer;

/**
 *
 * @author User
 */
public class LinePathRelativeAnimation extends LinePathAnimation {
    
    private static final LinePathRelativeAnimation instance = new LinePathRelativeAnimation();

    /**
     * @return the instance
     */
    public static LinePathAnimation getInstance() {
        return instance;
    }
    
    public void paint(final Graphics graphics, final GPoint point, final GPoint nextPoint, final Layer tiledLayer) {
        
        graphics.drawLine(
            nextPoint.getX() - tiledLayer.getX(),
            nextPoint.getY() - tiledLayer.getY(),
            point.getX() - tiledLayer.getX(),
            point.getY() - tiledLayer.getY()
        );
        
    }

}
