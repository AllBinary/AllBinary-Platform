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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.view.ViewPosition;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class IconLayer extends AllBinaryLayer
{
    @JsProperty
    public static final IconLayer NULL_ICON_LAYER = new IconLayer(NullAnimationFactory.getFactoryInstance().getInstance(0), 0, 0);
    
    private Animation animationInterface;

    @JsConstructor
    public IconLayer(Animation animationInterface, 
            int width, int height) 
    {
        super(StringUtil.getInstance().EMPTY_STRING, new Rectangle(PointFactory.getInstance().ZERO_ZERO, width, height), ViewPosition.getInstanceD());

        this.animationInterface = animationInterface;
    }

    @Override
    @JsMethod
    public void paint(Graphics graphics)
    {
        this.animationInterface.paintXY(graphics, this.x, this.y);
    }

    @Override
    @JsMethod
    public void paintThreed(Graphics graphics)
    {
       this.animationInterface.paintThreedXYZ(graphics, this.x, this.y, this.z);
    }
    
    @JsMethod
    public Animation getAnimationInterface()
    {
        return this.animationInterface;
    }

    /*
     * private void setAnimationInterface(AnimationInterface animationInterface)
     * { this.animationInterface = animationInterface; }
     */
}
