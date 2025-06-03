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
package org.allbinary.game.layer.hud.basic;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.opengles.OpenGLFeatureUtil;

import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.math.MathUtil;
import org.allbinary.logic.math.PrimitiveLongSingleton;
import org.allbinary.logic.math.PrimitiveLongUtil;

public class VelocityWidget extends BasicHud
{
    
    //private final String KILOMETERS_PER_HOUR_STR = " km/h";
    private final char[] KILOMETERS_PER_HOUR_STR = {' ', 'k', 'm', '/', 'h'};
    private final int totalChars = KILOMETERS_PER_HOUR_STR.length;

    private int velocity;
    private int maxVelocity;

    //private String string = StringUtil.getInstance();
    private char[] string = PrimitiveLongSingleton.getInstance().ZERO;
    private int totalDigits = 1;

    private final PrimitiveLongUtil primitiveLongUtil;

    private final int offset;
    private int offset2;
    
    public VelocityWidget(int powerOfTenVelocity, int location, int direction,
            BasicColor basicColor) throws Exception
    {
        super(location, direction, 14, MyFont.getInstance().getSize() * (5 + MathUtil.getInstance().getTotalDigits(powerOfTenVelocity) + 1), 2, basicColor);

        this.maxVelocity = powerOfTenVelocity;
        this.velocity = 0;

        this.primitiveLongUtil = new PrimitiveLongUtil(powerOfTenVelocity);
     
        final MyFont myFont = MyFont.getInstance();
        this.offset = myFont.stringWidth(this.primitiveLongUtil.getMaxDigits()) + myFont.stringWidth(2);
    }

    public int get()
    {
        return this.velocity;
    }

    public void add(int value)
    {
        this.set(this.velocity + value);
    }

    public void set(int value)
    {
        int lastVelocity = this.velocity;

        this.velocity = value;
        if (this.velocity > this.maxVelocity)
        {
            this.velocity = 0;
        }

        if (lastVelocity != this.velocity)
        {
            //this.string = primitiveLongUtil.getString(this.velocity * 18);
            this.string = primitiveLongUtil.getCharArray(this.velocity * 18);
            
            //I add one extra for open gl font issue
            if(OpenGLFeatureUtil.getInstance().isAnyThreed())
            {
                this.totalDigits = primitiveLongUtil.getCurrentTotalDigits() + 1;
            }
            else
            {
                this.totalDigits = primitiveLongUtil.getCurrentTotalDigits();
            }

            final MyFont myFont = MyFont.getInstance();
            this.offset2 = this.offset - myFont.stringWidth(this.totalDigits) - myFont.stringWidth(2);
        }
    }

    public void reduce(int value)
    {
        this.set(this.velocity - value);
    }

    public void paint(Graphics graphics)
    {
        //super.paint(graphics, string, KILOMETERS_PER_HOUR_STR, offset2, offset);
        super.paint(graphics, 
                string, 0, this.totalDigits, 
                KILOMETERS_PER_HOUR_STR, 0, this.totalChars, 
                offset2, offset);
    }
}
