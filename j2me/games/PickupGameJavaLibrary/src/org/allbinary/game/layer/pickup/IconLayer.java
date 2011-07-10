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
package org.allbinary.game.layer.pickup;

import javax.microedition.lcdui.Graphics;

import allbinary.animation.Animation;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.layer.AllBinaryLayer;
import allbinary.view.ViewPosition;

public class IconLayer extends AllBinaryLayer
{
    private Animation animationInterface;

    public IconLayer(Animation animationInterface, 
            int width, int height) throws Exception
    {
        super(new Rectangle(PointFactory.getInstance().ZERO_ZERO, width, height), new ViewPosition());

        this.animationInterface = animationInterface;
    }

    public void paint(Graphics graphics)
    {
        this.animationInterface.paint(graphics, x, y);
    }

    public void paintThreed(Graphics graphics)
    {
       this.animationInterface.paintThreed(graphics, x, y, 3);
    }
    
    public Animation getAnimationInterface()
    {
        return animationInterface;
    }

    /*
     * private void setAnimationInterface(AnimationInterface animationInterface)
     * { this.animationInterface = animationInterface; }
     */
}
