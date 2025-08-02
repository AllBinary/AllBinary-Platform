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

import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.view.ViewPosition;

public class IconLayer extends AllBinaryLayer
{
    public static final IconLayer NULL_ICON_LAYER = new IconLayer(NullAnimationFactory.getFactoryInstance().getInstance(0), 0, 0);
    
    private Animation animationInterface;

    public IconLayer(Animation animationInterface, 
            int width, int height) 
    {
        super(new Rectangle(PointFactory.getInstance().ZERO_ZERO, width, height), new ViewPosition());

        this.animationInterface = animationInterface;
    }

    @Override
    public void paint(Graphics graphics)
    {
        this.animationInterface.paint(graphics, x, y);
    }

    @Override
    public void paintThreed(Graphics graphics)
    {
       this.animationInterface.paintThreed(graphics, x, y, z);
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
