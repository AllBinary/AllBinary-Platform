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
package org.allbinary.game.layer.hud.basic.level;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.math.PrimitiveLongUtil;

public class LevelHudWidget extends BasicHud
   implements PaintableInterface
{
    public static LevelHudWidget createHud(int maxlevel, int location, int direction) throws Exception
    {
        return new LevelHudWidget(maxlevel, location, direction);
    }
    
    private int level;
    private int maxlevel;

    //private String levelString;
    private final char[] levelString;

    private char[] levelNumberCharArray = NullUtil.getInstance().NULL_CHAR_ARRAY;
    private int levelNumberTotalDigits;
    private int offset;

    private final PrimitiveLongUtil primitiveLongUtil;

    public LevelHudWidget(int maxlevel, int location, int direction)
            throws Exception
    {
        super(location, direction, 2, BasicColorFactory.getInstance().GREY);

        this.primitiveLongUtil = PrimitiveLongUtil.createPowerOfTen(1000);

        final String LEVEL = "Lv ";
        this.levelString = LEVEL.toCharArray();
        //Add size for space
        
        this.maxlevel = maxlevel;
        this.level = maxlevel;
        this.update();
        
        this.updateMaxHeight = 14;
    }

    public void updateMeasurement(final Graphics graphics) {
        
        final Font font = graphics.getFont();
        this.updateMaxWidth = font.getSize() * 4;
        this.offset = font.charsWidth(this.levelString, 0, this.levelString.length) + font.getSize();
        
        super.updateMeasurement(graphics);
    }
    
    private void update()
    {
        this.levelNumberCharArray = 
            this.primitiveLongUtil.getCharArray(this.level);        

        this.levelNumberTotalDigits = 
            this.primitiveLongUtil.getCurrentTotalDigits();
        
        //this.levelString = LEVEL + this.primitiveLongUtil.getString(this.level);
    }

    public void setLevel(int level)
    {
        this.level = level;
        this.update();
    }

    public void nextLevel()
    {
        this.level++;
        this.update();
    }

    public void previousLevel()
    {
        this.level--;
        if (this.level < 0)
        {
            this.level = 0;
        }
        this.update();
    }

    public boolean isComplete()
    {
        if (this.level <= this.maxlevel)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paintDX(graphics,
                this.levelString, 0, this.levelString.length, 
                this.levelNumberCharArray, 0, this.levelNumberTotalDigits, 
                this.offset);
        
        //super.paint(graphics, levelString);
    }
    
    @Override
    public void paintThreed(Graphics graphics)
    {
    }
    
}
