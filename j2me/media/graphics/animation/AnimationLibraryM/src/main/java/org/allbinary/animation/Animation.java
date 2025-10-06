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

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;

public class Animation implements AnimationInterface,
//implements 
OpenGLSurfaceChangedInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();
    
    protected final BasicColorSetUtil basicSetColorUtil = 
        BasicColorSetUtil.getInstance();

    protected BasicColor basicColor = BasicColorFactory.getInstance().WHITE;
    protected int colorP;
    protected BasicColor changeBasicColorP = BasicColorFactory.getInstance().NULL_COLOR;
    protected int changeColorP;
    public int alphaP;

    protected Animation()
    {
        //this.setBasicColorP(BasicColor.WHITE);
    }

    public void setAlpha(final int alpha) {
        this.alphaP = alpha;
    }

    @Override
    public void nextFrame() throws Exception
    {
    }

    @Override
    public void paint(final Graphics graphics, final int x, final int y)
    {
        this.basicSetColorUtil.setBasicColorP(
            graphics, this.getBasicColorP(), this.getColor());
    }

    @Override
    public void paintThreed(final Graphics graphics, final int x, final int y, final int z)
    {
    }
    
    public boolean isThreed() {
        return false;
    }
    
    public BasicColor getBasicColorP()
    {
        return this.basicColor;
    }

    public void setBasicColorP(final BasicColor basicColor)
    {
        //logUtil.put("setBasicColor", this, basicColor.toString());
        this.basicColor = basicColor;
        this.colorP = this.basicColor.intValue();
    }

    public BasicColor getChangeBasicColor()
    {
        return this.changeBasicColorP;
    }

    public void changeBasicColor(final BasicColor basicColor)
    {
        //logUtil.put("setBasicColor", this, basicColor.toString());
        this.changeBasicColorP = basicColor;
        this.changeColorP = this.changeBasicColorP.intValue();
    }

    public int getChangeColor()
    {
        return this.changeColorP;
    }
    
    public int getColor()
    {
        return this.colorP;
    }
    
    public void setScale(final float scaleX, final float scaleY) {
        //throw new RuntimeException();
    }

    public void setMaxScale(final float maxScaleX, final float maxScaleY) {
        
    }
    
    @Override
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
