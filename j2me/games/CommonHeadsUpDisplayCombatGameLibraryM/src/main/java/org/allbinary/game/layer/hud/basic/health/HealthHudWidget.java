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
package org.allbinary.game.layer.hud.basic.health;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.game.health.Health;
import org.allbinary.game.health.HealthListenerInterface;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.NoTimeDelayHelper;
import org.allbinary.time.TimeDelayHelper;

public class HealthHudWidget extends BasicHud
    implements PaintableInterface, HealthListenerInterface
{
    private int healthScale;
    protected Animation animationInterface;
    protected Health healthInterface;
    int max;

    private int[] xArray;

    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();
    
    /*
    public HealthGraphic(AnimationInterface animationInterface,
    HealthInterface healthInterface, int location,
    int direction)
    throws Exception
    {
    this(animationInterface, healthInterface, location, direction, BasicColor.RED);
    }
     */
    //, BasicColor basicColor
    public HealthHudWidget(Animation animationInterface,
        Health healthInterface, int location,
        int direction) throws Exception
    {
    	//width = 16
        super(location, direction, 2, BasicColorFactory.getInstance().WHITE);

        this.animationInterface = animationInterface;
        this.healthInterface = healthInterface;
        this.healthInterface.addListener(this);

        this.healthScale = (this.healthInterface.getMaxHealth() / 6) + 1;

        this.onHealthChange();
        
        this.updateMaxWidth = healthInterface.getMaxHealth() * 16;
        this.updateMaxHeight = 16;

        this.xArray = new int[30];
        this.update();
    }

//    @Override
//    public void updateMeasurement(final Graphics graphics) {        
//        super.updateMeasurement(graphics);
//
//    }

    private final void update()
    {
        if(this.xArray != null)
        {
            for (int index = 0; index < this.xArray.length; index++)
            {
            	//width = 16
                this.xArray[index] = this.getX() + (index * 16);
            }
        }
    }

    @Override
    public void setX(int x)
    {
        super.setX(x);
        this.update();
    }

    private TimeDelayHelper timeDelayHelper = NoTimeDelayHelper.SINGLETON;
    private final TimeDelayHelper slowBeatTimeDelayHelper = new TimeDelayHelper(1280);
    private final TimeDelayHelper mediumBeatTimeDelayHelper = new TimeDelayHelper(640);
    private final TimeDelayHelper fastBeatTimeDelayHelper = new TimeDelayHelper(320);

    @Override
    public void onHealthChange()
    {
        this.max = (this.healthInterface.getHealth() / this.healthScale);

        this.timeDelayHelper = NoTimeDelayHelper.SINGLETON;

        if (this.max <= 1 && this.healthInterface.isAlive())
        {
            this.max = 1;
            if (this.healthScale - this.healthInterface.getHealth() > (this.healthScale * 2) / 3)
            {
                this.timeDelayHelper = this.slowBeatTimeDelayHelper;
            }
            else if (this.healthScale - this.healthInterface.getHealth() > this.healthScale / 3)
            {
                this.timeDelayHelper = this.mediumBeatTimeDelayHelper;
            }
            else
            {
                this.timeDelayHelper = this.fastBeatTimeDelayHelper;
            }

        }
    }

    @Override
    public void paint(Graphics graphics)
    {
        this.myFontProcessor.process(graphics);
        
        for (int index = 0; index < this.max; index++)
        {
            if (this.timeDelayHelper.isTime(this.gameTickTimeDelayHelper.startTime))
            {
                this.animationInterface.paintXY(graphics, this.xArray[index], this.getY());
            }
        }
    }
    
    @Override
    public void paintThreed(Graphics graphics)
    {
    }    
}
