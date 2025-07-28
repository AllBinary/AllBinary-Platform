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
package org.allbinary.graphics.paint;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;

/**
 *
 * @author user
 */
public class AnimationCompositePaintable extends InitUpdatePaintable
{
    private final Animation animationInterface;
    
    public AnimationCompositePaintable(
        Animation animationInterface)
    {
        this.animationInterface = animationInterface;
    }

    @Override
    public void paint(Graphics graphics)
    {
        this.animationInterface.paint(graphics, 0, 0);
    }

    @Override
    public void paintThreed(Graphics graphics)
    {
        this.animationInterface.paintThreed(graphics, 0, 0, 0);
    }

}
