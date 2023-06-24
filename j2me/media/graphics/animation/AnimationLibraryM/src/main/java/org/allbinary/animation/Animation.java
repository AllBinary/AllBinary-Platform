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
package org.allbinary.animation;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;

import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorSetUtil;

public class Animation implements AnimationInterface,
//implements 
OpenGLSurfaceChangedInterface
{
    protected final BasicColorSetUtil basicColorUtil = 
        BasicColorSetUtil.getInstance();

    protected BasicColor basicColor;
    protected int color;
    protected BasicColor changeBasicColor;
    protected int changeColor;
    protected int alpha;

    protected Animation()
    {
        //this.setBasicColor(BasicColor.WHITE);
    }

    public void setAlpha(final int alpha) {
        this.alpha = alpha;
    }

    public void nextFrame() throws Exception
    {
    }

    public void paint(Graphics graphics, int x, int y)
    {
    }

    public void paintThreed(Graphics graphics, int x, int y, int z)
    {
    }
    
    public boolean isThreed() {
        return false;
    }
    
    public BasicColor getBasicColor()
    {
        return this.basicColor;
    }

    public void setBasicColor(final BasicColor basicColor)
    {
        //LogUtil.put(LogFactory.getInstance("setBasicColor", this, basicColor.toString()));
        this.basicColor = basicColor;
        this.color = this.basicColor.intValue();
    }

    public BasicColor getChangeBasicColor()
    {
        return this.changeBasicColor;
    }

    public void changeBasicColor(final BasicColor basicColor)
    {
        //LogUtil.put(LogFactory.getInstance("setBasicColor", this, basicColor.toString()));
        this.changeBasicColor = basicColor;
        this.changeColor = this.changeBasicColor.intValue();
    }

    public int getChangeColor()
    {
        return this.changeColor;
    }
    
    public int getColor()
    {
        return this.color;
    }
    
    public void set(GL gl) throws Exception
    {
        
    }
}
