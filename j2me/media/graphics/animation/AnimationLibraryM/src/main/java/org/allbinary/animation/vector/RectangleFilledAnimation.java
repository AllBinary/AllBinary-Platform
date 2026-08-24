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
package org.allbinary.animation.vector;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.ColorCompositeInterface;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class RectangleFilledAnimation 
extends Animation 
implements ColorCompositeInterface
{
   @JsProperty
   protected int widthP;
   @JsProperty
   protected int heightP;
   
   @JsConstructor
   public RectangleFilledAnimation(final int width, final int height, final BasicColor basicColor) {
      this.widthP = width;
      this.heightP = height;
      this.setBasicColorP(basicColor);
   }

   @Override
   @JsMethod
   public void nextFrame() {
   }

   @Override
   @JsMethod
   public void paintXY(final Graphics graphics, final int x, final int y) {
       this.basicSetColorUtil.setBasicColorP3(
               graphics, this.getBasicColorP(), this.getColor());

      graphics.fillRect(x, y, this.widthP, this.heightP);
   }

    /**
     * @param width the width to set
     */
    @JsMethod
    public void setWidth(final int width)
    {
        this.widthP = width;
    }

    /**
     * @param height the height to set
     */
    @JsMethod
    public void setHeight(final int height)
    {
        this.heightP = height;
    }
}
