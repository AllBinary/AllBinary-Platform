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
package org.allbinary.animation.text;

import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.animation.Animation;
import org.allbinary.graphics.Anchor;

public class TextAnimation  extends Animation
{
    private String text = StringUtil.getInstance().EMPTY_STRING;
    
    private int anchor = Anchor.TOP_LEFT;
    
    public TextAnimation()
    {
    }

    public TextAnimation(String text)
    {
        this.text = text;
    }
    
    public void nextFrame() throws Exception
    {
    }

    public void paint(Graphics graphics, int x, int y)
    {
        this.basicColorUtil.setBasicColor(
                graphics, this.getBasicColor(), this.getColor());

        graphics.drawString(text, x, y, anchor);
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }
}
