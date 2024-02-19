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

import org.allbinary.logic.string.StringUtil;
import org.allbinary.animation.Animation;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.util.BasicArrayList;

public class TextAnimation  extends Animation
{
    protected String[] textArray = {StringUtil.getInstance().EMPTY_STRING};
    
    private int anchor = Anchor.TOP_LEFT;
    
    public TextAnimation()
    {
    }

    public TextAnimation(String text)
    {
        //this.textArray = new String[1];
        //this.textArray[0] = text;
        this.setText(text);
    }
    
    public void nextFrame() throws Exception
    {
    }

    public void paint(Graphics graphics, int x, int y)
    {
        this.basicSetColorUtil.setBasicColor(
                graphics, this.getBasicColor(), this.getColor());

        final int height = this.getHeight();
        final int size = textArray.length;
        for(int index = 0; index < size; index++) {
            graphics.drawString(textArray[index], x, y + (index * height), anchor);
        }

    }

    public void setText(String text)
    {
        final BasicArrayList list = new BasicArrayList();

        int index = 0;
        int startIndex = 0;
        int endIndex = 0;
        while(index >= 0) {
            startIndex = index;
            index = text.indexOf('\n', startIndex);
            endIndex = index;
            if(index < 0) {
                endIndex = text.length();
            }
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("startIndex: ").append(startIndex).append(" endIndex: ").append(endIndex).toString(), this, CommonStrings.getInstance().PROCESS));
            list.add(text.substring(startIndex, endIndex));
            if(index < 0) break;
            index++;
        }

        this.textArray = (String[]) list.toArray(new String[list.size()]);
    }

    public String[] getText()
    {
        return textArray;
    }
    
    public int getHeight() {
        return MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
    }
    
}
