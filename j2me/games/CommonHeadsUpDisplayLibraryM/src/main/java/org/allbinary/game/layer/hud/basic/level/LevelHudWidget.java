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

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.graphics.hud.BasicHud;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.math.PrimitiveLongUtil;

public class LevelHudWidget extends BasicHud
   implements PaintableInterface
{
    //private final String LEVEL = "Lv ";

    private int level;
    private int maxlevel;

    //private String levelString;
    private final char[] levelString;
    private char[] levelNumberCharArray = NullUtil.getInstance().NULL_CHAR_ARRAY;
    private int levelNumberTotalDigits;
    private final int offset;

    private final PrimitiveLongUtil primitiveLongUtil;

    public LevelHudWidget(int maxlevel, int location, int direction)
            throws Exception
    {
        this(maxlevel, location, direction, MyFont.getInstance().getSize() * 4);
    }
    
    public LevelHudWidget(int maxlevel, int location, int direction, int maxWidth)
            throws Exception
    {
        super(location, direction, 14, maxWidth, 2, BasicColorFactory.getInstance().GREY);

        final MyFont myFont = MyFont.getInstance();
        
        this.primitiveLongUtil = new PrimitiveLongUtil(1000);

        final String LEVEL = "Lv ";
        this.levelString = LEVEL.toCharArray();
        //Add size for space
        this.offset = myFont.charsWidth(this.levelString, 0, this.levelString.length) + myFont.getSize();
        
        this.maxlevel = maxlevel;
        this.level = maxlevel;
        this.update();
    }

    private void update()
    {
        levelNumberCharArray = 
            this.primitiveLongUtil.getCharArray(this.level);        

        levelNumberTotalDigits = 
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
        if (this.level <= maxlevel)
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
        super.paint(graphics, 
                levelString, 0, levelString.length, 
                levelNumberCharArray, 0, levelNumberTotalDigits, 
                this.offset);
        
        //super.paint(graphics, levelString);
    }
    
    @Override
    public void paintThreed(Graphics graphics)
    {
    }
    
}
