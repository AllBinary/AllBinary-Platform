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
package allbinary.game.health;

import allbinary.animation.Animation;
import allbinary.game.graphics.hud.BasicHudFactory;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.color.BasicColorSetUtil;
import allbinary.layer.AllBinaryLayer;

public class HealthBarAnimation
extends Animation
{
    private final BasicColorFactory basicColorFactory = 
            BasicColorFactory.getInstance();

    protected final BasicColorSetUtil basicColorUtil = 
            BasicColorSetUtil.getInstance();

    protected BasicColor basicColor;
    protected int color;

    protected int x2;

    protected int thickness;

    private int location;

    protected AllBinaryLayer allbinaryLayer;

    public HealthBarAnimation(AllBinaryLayer layerInterface, int location)
    throws Exception
    {
        this.allbinaryLayer = layerInterface;

        this.location = location;

        BasicHudFactory basicHudFactory = 
                BasicHudFactory.getInstance();

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

    public void onHealthChange(int newX2)
    {
        x2 = newX2;
        
        int quarter = (this.allbinaryLayer.getWidth() >> 2);
        if (x2 > quarter * 3)
        {
            this.basicColor = this.basicColorFactory.GREEN;
            this.color = this.basicColor.intValue();
        }
        else if (x2 > quarter * 2)
        {
            this.basicColor = this.basicColorFactory.YELLOW;
            this.color = this.basicColor.intValue();
        }
        else if (x2 > quarter)
        {
            this.basicColor = this.basicColorFactory.ORANGE;
            this.color = this.basicColor.intValue();
        }
        else
        {
            this.basicColor = this.basicColorFactory.RED;
            this.color = this.basicColor.intValue();
        }
    }

    protected int getY()
    {
        BasicHudFactory basicHudFactory = 
            BasicHudFactory.getInstance();
        
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