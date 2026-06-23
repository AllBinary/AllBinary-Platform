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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.opengles.OpenGLFeatureUtil;

import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.logic.math.MathUtil;
import org.allbinary.logic.math.PrimitiveLongSingleton;
import org.allbinary.logic.math.PrimitiveLongUtil;

public class VelocityWidget extends BasicHud
{
    
    //private final String KILOMETERS_PER_HOUR_STR = " km/h";
    private final char[] KILOMETERS_PER_HOUR_STR = {' ', 'k', 'm', '/', 'h'};
    private final int totalChars = this.KILOMETERS_PER_HOUR_STR.length;

    private int velocity;
    private int maxVelocity;

    //private String string = StringUtil.getInstance();
    private char[] string = PrimitiveLongSingleton.getInstance().ZERO;
    private int totalDigits = 1;

    private final int powerOfTenVelocity;
    private final PrimitiveLongUtil primitiveLongUtil;

    private int offset = 0;
    private int offset2 = 0;
    
    public VelocityWidget(int powerOfTenVelocity, int location, int direction,
            BasicColor basicColor) throws Exception
    {
        super(location, direction, 2, basicColor);

        this.powerOfTenVelocity = powerOfTenVelocity;
        this.maxVelocity = powerOfTenVelocity;
        this.velocity = 0;

        this.primitiveLongUtil = PrimitiveLongUtil.createPowerOfTen(powerOfTenVelocity);
     
        this.updateMaxHeight = 14;
    }


    @Override
    public void updateMeasurement(final Graphics graphics) {

        final Font font = graphics.getFont();
        this.updateMaxWidth = font.getSize() * (5 + MathUtil.getInstance().getTotalDigits(this.powerOfTenVelocity) + 1);
        
        super.updateMeasurement(graphics);
        
        this.offset = MyFontProcessor.defaultStringWidth(font, this.primitiveLongUtil.getMaxDigits()) + MyFontProcessor.defaultStringWidth(font, 2);
        this.offset2 = this.offset - MyFontProcessor.defaultStringWidth(font, this.totalDigits) - MyFontProcessor.defaultStringWidth(font, 2);
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
            this.string = this.primitiveLongUtil.getCharArray(this.velocity * 18);
            
            //I add one extra for open gl font issue
            if(OpenGLFeatureUtil.getInstance().isAnyThreed())
            {
                this.totalDigits = this.primitiveLongUtil.getCurrentTotalDigits() + 1;
            }
            else
            {
                this.totalDigits = this.primitiveLongUtil.getCurrentTotalDigits();
            }

            this.myFontProcessor = this.updateMyFontProcessor;
        }
    }

    public void reduce(int value)
    {
        this.set(this.velocity - value);
    }

    public void paint(Graphics graphics)
    {
        //super.paint(graphics, string, KILOMETERS_PER_HOUR_STR, offset2, offset);
        super.paintDXY(graphics,
                this.string, 0, this.totalDigits, 
                this.KILOMETERS_PER_HOUR_STR, 0, this.totalChars, 
                this.offset2, this.offset);
    }
}
