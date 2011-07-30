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

import javax.microedition.lcdui.Graphics;

import allbinary.game.graphics.hud.BasicHudFactory;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.color.BasicColorSetUtil;
import allbinary.graphics.paint.Paintable;
import allbinary.layer.AllBinaryLayer;

public class HealthBar extends Paintable implements HealthListenerInterface
{
    private AllBinaryLayer allbinaryLayer;
    // private AnimationInterface animationInterface;
    private HealthInterface healthInterface;

    protected final BasicColorSetUtil basicColorUtil = 
        BasicColorSetUtil.getInstance();
    
    private BasicColor basicColor;
    private int color;
    
    private int x2;
    
    private int thickness;

    private int location;

    // private int direction;

    public HealthBar(AllBinaryLayer layerInterface,
    // AnimationInterface animationInterface,
            Health healthInterface, int location, int direction)
            throws Exception
    {

        this.location = location;

        BasicHudFactory basicHudFactory = 
            BasicHudFactory.getInstance();
        
        if (this.location != basicHudFactory.TOPLEFT
                && this.location != basicHudFactory.BOTTOMLEFT)
        {
            throw new Exception("Location Not Valid");
        }

        this.allbinaryLayer = layerInterface;
        // this.animationInterface = animationInterface;
        this.healthInterface = healthInterface;
        this.healthInterface.addListener(this);

        this.onHealthChange();

        if (this.allbinaryLayer.getWidth() > 40)
        {
            this.thickness = 3;
        }
        else if (this.allbinaryLayer.getWidth() > 20)
        {
            this.thickness = 2;
        }
        else
        {
            this.thickness = 1;
        }
    }

    private final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
    
    public void onHealthChange()
    {
        x2 = this.healthInterface.getHealth() * this.allbinaryLayer.getWidth() / this.healthInterface.getMaxHealth();
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

    private int getY()
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
    
    public void paint(Graphics graphics)
    {

        int x = this.allbinaryLayer.getViewPosition().getX();
        //int y = this.getY() - 1;

        this.basicColorUtil.setBasicColor(
                graphics, this.basicColor, this.color);

        graphics.fillRect(x, this.getY() - 1, x2, this.thickness);
        
        /*
        for (int index = 0; index < this.thickness; index++)
        {
            graphics.drawLine(x, y - index, x + x2, y - index);
        }
        */
        
        // this.animationInterface.paint(graphics, this.layerInterface.getX() +
        // numOfHealth * 10, this.layerInterface.getY2() + 5);
    }
}
