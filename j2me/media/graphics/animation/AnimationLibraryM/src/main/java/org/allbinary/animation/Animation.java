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
    //protected final LogUtil logUtil = LogUtil.getInstance();
    
    protected final BasicColorSetUtil basicSetColorUtil = 
        BasicColorSetUtil.getInstance();

    protected BasicColor basicColor;
    protected int color;
    protected BasicColor changeBasicColor;
    protected int changeColor;
    public int alpha;

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

    public void paint(final Graphics graphics, final int x, final int y)
    {
        this.basicSetColorUtil.setBasicColor(
            graphics, this.getBasicColor(), this.getColor());
    }

    public void paintThreed(final Graphics graphics, final int x, final int y, final int z)
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
        //logUtil.put("setBasicColor", this, basicColor.toString());
        this.basicColor = basicColor;
        this.color = this.basicColor.intValue();
    }

    public BasicColor getChangeBasicColor()
    {
        return this.changeBasicColor;
    }

    public void changeBasicColor(final BasicColor basicColor)
    {
        //logUtil.put("setBasicColor", this, basicColor.toString());
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
    
    public void setScale(final float scaleX, final float scaleY) {
        //throw new RuntimeException();
    }

    public void setMaxScale(final float maxScaleX, final float maxScaleY) {
        
    }
    
    public void set(GL gl) throws Exception
    {
        
    }
    
    public void setDx(final int dx) {
    }

    public int getDx() {
        return 0;
    }    
    
    public void setDy(final int dy) {
    }

    public int getDy() {
        return 0;
    }    
    
}
