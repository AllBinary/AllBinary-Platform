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

import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.logic.math.PrimitiveLongSingleton;
import org.allbinary.logic.math.PrimitiveLongUtil;

public class NumberStringHud extends BasicHud
    implements PaintableInterface {
    
    private final PrimitiveLongUtil primitiveLongUtil;
    
    private final String prependString;
    //private final String PREPEND_STRING;
    private final char[] PREPEND_STRING;

    private int value;
    private int max;
    private int offset;
    //private int halfOffset;
    //private String valueString;
    private char[] valueString;
    private int valueTotalDigits;

    public NumberStringHud(final String prependString, final int max, final int location, final int direction, final int bufferZone, final BasicColor basicColor) {
        super(location, direction, bufferZone, basicColor);

        this.prependString = prependString;
        this.PREPEND_STRING = prependString.toCharArray();
        //this.PREPEND_STRING = prependString;

        this.valueString = PrimitiveLongSingleton.getInstance().NUMBER_CHAR_ARRAYS[0];
        //this.valueString = PrimitiveLongUtil.NUMBER_STRING_ARRAY[0];

        //Note score must be (10 X 10^n) - 1
        this.primitiveLongUtil = PrimitiveLongUtil.createPowerOfTen(max + 1);

        this.max = max;
        this.value = 0;

        if (direction == 0) {
//            throw new Exception(BasicHudFactory.getInstance().DIRECTION_EXCEPTION);
            throw new RuntimeException(BasicHudFactory.getInstance().DIRECTION_EXCEPTION);
        }
    }
    
    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.offset = font.stringWidth(this.prependString) + MyFontProcessor.defaultCharWidth(font);
        super.updateMeasurement(graphics);
    }

    public int get() {
        return this.value;
    }

    public void add(final int value) {
        this.set(this.value + value);
    }

    public void set(final int value) {
        this.value = value;
        if (this.value > this.max) {
            this.value = 0;
        }
        this.valueString = this.primitiveLongUtil.getCharArray(this.value);
        //this.valueString = this.primitiveLongUtil.getString(this.value);
        this.valueTotalDigits = this.primitiveLongUtil.getCurrentTotalDigits();
    }

    public void reduce(final int value) {
        this.set(this.value - value);
    }

    @Override
    public void paint(final Graphics graphics) {
        
        super.paintDX(graphics,
            this.PREPEND_STRING, 0, this.PREPEND_STRING.length,
            this.valueString, 0, this.valueTotalDigits,
            this.offset);
        //super.paint(graphics, PREPEND_STRING, valueString, offset);
    }

    public void paintXY(final Graphics graphics, final int x, final int y) {
        
        this.myFontProcessor.process(graphics);
        
        char[] charArray = this.PREPEND_STRING;
        //int offset = 0;
        int len = this.PREPEND_STRING.length;
        char[] charArray2 = this.valueString;
        //int offset2 = 0;
        int len2 = this.valueTotalDigits;

        this.basicSetColorUtil.setBasicColorP(graphics, this.getBasicColorP());

        graphics.drawChars(charArray, 0, //offset, 
            len, x, y, 0);

        graphics.drawChars(charArray2, 0, //offset2, 
            len2, x - this.offset, y, 0);
    }

    @Override
    public void paintThreed(final Graphics graphics) {
    }

}
