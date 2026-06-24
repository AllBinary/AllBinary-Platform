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
package org.allbinary.game.layer.hud.basic.life;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.game.life.Life;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.paint.PaintableInterface;

public class LivesHudWidget extends BasicHud
    implements PaintableInterface
{
    private final Life lifeInterface;
    
    private final int[] xArray;
    private final Animation animationInterface;

    /*
    public LivesGraphic(AnimationInterface animationInterface, int lives, int maxlives,
    int location, int direction) throws Exception {

    this(animationInterface, lives, maxlives, location, direction, BasicColor.GREEN);

    }
     */
    //, BasicColor basicColor
    
    public LivesHudWidget(Animation animationInterface, Life lifeInterface,
        int location, int direction) throws Exception
    {
    	//width = 16
        super(location, direction, 2, BasicColorFactory.getInstance().WHITE);

        this.lifeInterface = lifeInterface;
        
        final int size = (int) this.getLifeInterface().getMaxlives();
        this.xArray = new int[size];

        this.animationInterface = animationInterface;
        
        this.updateMaxWidth = this.lifeInterface.getMaxlives() * 16;
        this.updateMaxHeight = 16;
        
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {        
        super.updateMeasurement(graphics);
        
        int maxLives = (int) this.getLifeInterface().getMaxlives();
        for (int index = 0; index < maxLives; index++) {
            //width = 16
            this.xArray[index] = this.getX() + (index * 16);
        }
    }
    
    @Override
    public void onDisplayChangeEvent(DisplayChangeEvent displayChangeEvent)
    {
        super.onDisplayChangeEvent(displayChangeEvent);
        this.myFontProcessor = this.updateMyFontProcessor;
    }
    
    @Override
    public void setX(int x)
    {
        super.setX(x);
        this.myFontProcessor = this.updateMyFontProcessor;
    }

    @Override
    public void paint(Graphics graphics)
    {
        this.myFontProcessor.process(graphics);
        
        for (int index = (int) this.getLifeInterface().get(); --index >= 0;)
        {
            this.animationInterface.paintXY(graphics, this.xArray[index], this.getY());
        }
    }

    @Override
    public void paintThreed(Graphics graphics)
    {
    }
    
    public Life getLifeInterface()
    {
        return this.lifeInterface;
    }
}
