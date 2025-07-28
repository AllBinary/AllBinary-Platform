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

import org.allbinary.animation.Animation;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.layer.AllBinaryLayer;

public class HealthBarAnimation extends Animation
{
    private final BasicColorFactory basicColorFactory = 
            BasicColorFactory.getInstance();
    
    protected int x2;

    protected int thickness;

    private int location;

    protected AllBinaryLayer allbinaryLayer;

    public HealthBarAnimation(final AllBinaryLayer layerInterface, final int location)
    throws Exception
    {
        this.allbinaryLayer = layerInterface;

        this.location = location;

        final BasicHudFactory basicHudFactory = BasicHudFactory.getInstance();

        if (this.location != basicHudFactory.TOPLEFT
                && this.location != basicHudFactory.BOTTOMLEFT)
        {
            throw new Exception("Location Not Valid");
        }

        if (layerInterface.getWidth() > 40)
        {
            this.thickness = 3;
        }
        else if (layerInterface.getWidth() > 20)
        {
            this.thickness = 2;
        }
        else
        {
            this.thickness = 1;
        }
    }

    public void onHealthChange(final int newX2)
    {
        x2 = newX2;
        
        int quarter = (this.allbinaryLayer.getWidth() >> 2);
        if (x2 > quarter * 3)
        {
            this.basicColorP = this.basicColorFactory.GREEN;
            this.colorP = this.basicColorP.intValue();
        }
        else if (x2 > quarter * 2)
        {
            this.basicColorP = this.basicColorFactory.YELLOW;
            this.colorP = this.basicColorP.intValue();
        }
        else if (x2 > quarter)
        {
            this.basicColorP = this.basicColorFactory.ORANGE;
            this.colorP = this.basicColorP.intValue();
        }
        else
        {
            this.basicColorP = this.basicColorFactory.RED;
            this.colorP = this.basicColorP.intValue();
        }
    }

    protected int getY()
    {
        final BasicHudFactory basicHudFactory = BasicHudFactory.getInstance();
        
        if (this.location == basicHudFactory.TOPLEFT)
        {
            return this.allbinaryLayer.getViewPosition().getY() - 4;
        }
        else if (this.location == basicHudFactory.BOTTOMLEFT)
        {
            return this.allbinaryLayer.getViewPosition().getY2() + 4;
        }
        return -1;
    }
}