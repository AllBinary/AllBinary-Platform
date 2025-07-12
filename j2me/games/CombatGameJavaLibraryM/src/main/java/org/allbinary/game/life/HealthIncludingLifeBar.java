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
package org.allbinary.game.life;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.health.Health;
import org.allbinary.game.health.HealthBarAnimation;
import org.allbinary.game.health.HealthBarTwodAnimation;
import org.allbinary.game.health.HealthInterface;
import org.allbinary.game.health.HealthListenerInterface;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.layer.AllBinaryLayer;

public class HealthIncludingLifeBar
extends Paintable
implements HealthListenerInterface
{
    private final HealthBarAnimation animationInterface;

    private final Life life;
    private final HealthInterface healthInterface;

    // private int direction;

    protected final AllBinaryLayer allbinaryLayer;
    
    public HealthIncludingLifeBar(
            AllBinaryLayer layerInterface,
            Life life,
            Health healthInterface, 
            HealthBarAnimation animationInterface, int direction)
            throws Exception
    {
        this.allbinaryLayer = layerInterface;

        this.animationInterface = animationInterface;

        this.life = life;
        this.healthInterface = healthInterface;
        this.healthInterface.addListener(this);

        this.onHealthChange();
    }

    public HealthIncludingLifeBar(AllBinaryLayer layerInterface,
            Life life,
            Health healthInterface, 
            int location,
            int direction)
            throws Exception
    {
        this(layerInterface, life, healthInterface, new HealthBarTwodAnimation(layerInterface, location), direction);
    }
    
    public void onHealthChange()
    {
        this.animationInterface.onHealthChange(
                ((this.healthInterface.getHealth() + (this.healthInterface.getMaxHealth() * this.life.get())) * this.allbinaryLayer.getWidth()) / (this.healthInterface.getMaxHealth() + (this.healthInterface.getMaxHealth() * this.life.getStartLives()))
                );
    }

    public void paint(Graphics graphics)
    {
        this.animationInterface.paint(graphics, 0, 0);
        
        // this.animationInterface.paint(graphics, this.layerInterface.getX() +
        // numOfHealth * 10, this.layerInterface.getY2() + 5);
    }

    public void paintThreed(Graphics graphics)
    {
        this.animationInterface.paintThreed(graphics, 0, 0, 0);
    }
}