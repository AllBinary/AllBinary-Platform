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
package allbinary.animation;

import javax.microedition.lcdui.Graphics;

import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorSetUtil;
import allbinary.graphics.color.BasicColorUtilFactory;

public class Animation implements AnimationInterface
{
    protected final BasicColorSetUtil basicColorUtil = 
        BasicColorUtilFactory.getInstance();

    private BasicColor basicColor;
    private int color;

    protected Animation()
    {
        //this.setBasicColor(BasicColor.WHITE);
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
    
    public BasicColor getBasicColor()
    {
        return basicColor;
    }

    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
        this.color = this.basicColor.intValue();
    }

    public int getColor()
    {
        return color;
    }
}
