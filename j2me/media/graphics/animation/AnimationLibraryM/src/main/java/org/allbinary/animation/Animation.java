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

import jsinterop.annotations.JsType;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class Animation implements AnimationInterface,
//implements 
OpenGLSurfaceChangedInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();
    
    @JsProperty
    protected final BasicColorSetUtil basicSetColorUtil = 
        BasicColorSetUtil.getInstance();

    @JsProperty
    protected BasicColor basicColor = BasicColorFactory.getInstance().WHITE;
    @JsProperty
    protected int colorP;
    @JsProperty
    protected BasicColor changeBasicColorP = BasicColorFactory.getInstance().NULL_COLOR;
    @JsProperty
    protected int changeColorP;
    @JsProperty
    public int alphaP;

    @JsConstructor
    public Animation()
    {
        //this.setBasicColorP(BasicColor.WHITE);
    }

    @JsMethod
    public void setAlpha(final int alpha) {
        this.alphaP = alpha;
    }

    @Override
    @JsMethod
    public void nextFrame() throws Exception
    {
    }

    @Override
    @JsMethod
    public void paintXY(final Graphics graphics, final int x, final int y)
    {
        this.basicSetColorUtil.setBasicColorP3(
            graphics, this.getBasicColorP(), this.getColor());
    }

    @Override
    @JsMethod
    public void paintThreedXYZ(final Graphics graphics, final int x, final int y, final int z)
    {
    }
    
    @JsMethod
    public boolean isThreed() {
        return false;
    }
    
    @JsMethod
    public BasicColor getBasicColorP()
    {
        return this.basicColor;
    }

    @JsMethod
    public void setBasicColorP(final BasicColor basicColor)
    {
        //this.logUtil.putF("setBasicColor", this, basicColor.toString());
        this.basicColor = basicColor;
        this.colorP = this.basicColor.intValue();
    }

    @JsMethod
    public void setBackgroundBasicColorP(final BasicColor basicColor)
    {
    }
    
    @JsMethod
    public BasicColor getChangeBasicColor()
    {
        return this.changeBasicColorP;
    }

    @JsMethod
    public void changeBasicColor(final BasicColor basicColor)
    {
        //this.logUtil.putF("setBasicColor", this, basicColor.toString());
        this.changeBasicColorP = basicColor;
        this.changeColorP = this.changeBasicColorP.intValue();
    }

    @JsMethod
    public int getChangeColor()
    {
        return this.changeColorP;
    }
    
    @JsMethod
    public int getColor()
    {
        return this.colorP;
    }
    
    @JsMethod
    public void setScale(final float scaleX, final float scaleY) {
        //throw new RuntimeException();
    }

    @JsMethod
    public void setMaxScale(final float maxScaleX, final float maxScaleY) {
        
    }
    
    @Override
    @JsMethod
    public void set(GL gl) throws Exception
    {
        
    }
    
    @JsMethod
    public void setDx(final int dx) {
    }

    @JsMethod
    public int getDx() {
        return 0;
    }    
    
    @JsMethod
    public void setDy(final int dy) {
    }

    @JsMethod
    public int getDy() {
        return 0;
    }    
    
}
