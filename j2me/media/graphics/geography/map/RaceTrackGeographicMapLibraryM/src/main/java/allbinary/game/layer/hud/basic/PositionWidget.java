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
package allbinary.game.layer.hud.basic;

import javax.microedition.lcdui.Graphics;

import abcs.logic.basic.string.StringMaker;
import abcs.logic.basic.string.StringUtil;
import allbinary.game.graphics.hud.BasicHud;
import allbinary.graphics.color.BasicColor;

//TWB - Optimization needed
//could be optimized to display
//super.paint(graphics, positionString, currentPostString, offset2, offset);
public class PositionWidget extends BasicHud
{
    private int position;
    private String positionString = StringUtil.getInstance().EMPTY_STRING;

    public PositionWidget(int position, int location, int direction,
            BasicColor basicColor) throws Exception
    {
        super(location, direction, 10, 40, 2, basicColor);
    }

    public int get()
    {
        return this.position;
    }

    public void set(int position)
    {
        this.position = position;

        this.positionString = PositionWidget.toString(this.get());
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics, positionString);
    }

    public static String toString(int position)
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(Integer.toString(position));
        
        if (position == 1)
        {
            stringBuffer.append("st");
        }
        else if (position == 2)
        {
            stringBuffer.append("nd");
        }
        else if (position == 3)
        {
            stringBuffer.append("rd");
        }
        else
        {
            stringBuffer.append("th");
        }

        return stringBuffer.toString();
    }
}
