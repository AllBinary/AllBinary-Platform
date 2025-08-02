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
package org.allbinary.game.health;

import javax.microedition.lcdui.Graphics;

import org.allbinary.layer.AllBinaryLayer;

public class HealthBarTwodAnimation extends HealthBarAnimation
{
    public HealthBarTwodAnimation(final AllBinaryLayer layerInterface, final int location)
    throws Exception
    {
        super(layerInterface, location);
    }

    @Override    
    public void paint(final Graphics graphics, final int x, final int y)
    {
        final int ax = this.allbinaryLayer.getViewPosition().getX();
        //int y = this.getY() - 1;

        this.basicSetColorUtil.setBasicColorP(
                graphics, this.basicColor, this.colorP);

        graphics.fillRect(ax, this.getY() - 1, x2, this.thickness);
        
        /*
        for (int index = 0; index < this.thickness; index++)
        {
            graphics.drawLine(x, y - index, x + x2, y - index);
        }
        */
    }
}