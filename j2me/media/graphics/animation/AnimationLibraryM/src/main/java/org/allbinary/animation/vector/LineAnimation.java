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

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.ColorCompositeInterface;
import org.allbinary.layer.PositionInterface;

public class LineAnimation
    extends Animation
    implements ColorCompositeInterface, PositionInterface
{
    private int[][] newLine = new int[2][2];
    private int[][] tempLine = null;
    private int[][] line = new int[2][2];

    public LineAnimation(BasicColor basicColor)
    {
        this.setBasicColor(basicColor);
    }

    public void init(int x, int y)
    {
        this.newLine[0][0] = x;
        this.newLine[0][1] = y;            

        this.newLine[1][0] = x;
        this.newLine[1][1] = y;

        this.tempLine = this.line;
        this.line = this.newLine;
        this.newLine = this.tempLine;
    }

    public void setPosition(int x, int y, int z)
    {
        this.newLine[0][0] = this.line[1][0];
        this.newLine[0][1] = this.line[1][1];            

        this.newLine[1][0] = x;
        this.newLine[1][1] = y;

        this.tempLine = this.line;
        this.line = this.newLine;
        this.newLine = this.tempLine;
    }

    /*
    public void setPosition(int x, int y)
    {
        if (tempLine == null)
        {
            this.newLine[0][0] = x;
            this.newLine[0][1] = y;            
        }
        else
        {
            this.newLine[0][0] = this.line[1][0];
            this.newLine[0][1] = this.line[1][1];            
        }

        this.newLine[1][0] = x;
        this.newLine[1][1] = y;

        this.tempLine = this.line;
        this.line = this.newLine;
        this.newLine = this.tempLine;
    }
    */

    public void paint(Graphics graphics, int x, int y)
    {
        final int[][] currentLine = this.line;
        
        this.basicSetColorUtil.setBasicColor(
                graphics, this.getBasicColor(), this.getColor());
        graphics.drawLine(
            currentLine[0][0], currentLine[0][1],
            currentLine[1][0], currentLine[1][1]);
    }

    public void nextFrame()
    {
    }
}
