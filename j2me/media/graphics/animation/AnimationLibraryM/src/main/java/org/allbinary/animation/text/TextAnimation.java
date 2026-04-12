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

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

public class TextAnimation extends IndexedAnimation
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //private final int WIDTH = 13;
    //private final int HEIGHT = 8;
    
    protected String[] textArrayP = StringUtil.getInstance().ONE_EMPTY_STRING_ARRAY;
    
    private int anchor = Anchor.TOP_LEFT;
    
    public TextAnimation(final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
    }

    public TextAnimation(final String text, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        //this.textArray = new String[1];
        //this.textArray[0] = text;
        this.setText(text);
    }
    
    @Override
    public void nextFrame() throws Exception
    {
    }

    @Override
    public void paint(Graphics graphics, int x, int y)
    {
        this.basicSetColorUtil.setBasicColorP(
                graphics, this.getBasicColorP(), this.getColor());

        final int height = this.getHeight();
        final int size = textArrayP.length;
        for(int index = 0; index < size; index++) {
            //this.logUtil.putF(new StringMaker().append(textArray[index]).append(CommonSeps.getInstance().SPACE).append(x).append(CommonSeps.getInstance().SPACE).append(y).toString(), this, commonStrings.PROCESS);
            graphics.drawString(textArrayP[index], x, y + (index * height), anchor);
            //graphics.drawString(textArray[index], x + WIDTH, y + (index * height) + HEIGHT, anchor);
        }

    }

    public void setText(String text)
    {
        final BasicArrayList list = new BasicArrayList();

        if(text != null) {
            int index = 0;
            int startIndex = 0;
            int endIndex = 0;
            while (index >= 0) {
                startIndex = index;
                index = text.indexOf('\n', startIndex);
                endIndex = index;
                if (index < 0) {
                    endIndex = text.length();
                }
                //this.logUtil.putF(new StringMaker().append("startIndex: ").append(startIndex).append(" endIndex: ").append(endIndex).toString(), this, commonStrings.PROCESS);
                list.add(text.substring(startIndex, endIndex));
                if (index < 0) {
                    break;
                }
                index++;
            }
        }

        if(list.size() > 0) {
            final String[] textArray = new String[list.size()];
            final int size = list.size();
            for(int index = 0; index < size; index++) {
                textArray[index] = (String) list.get(index);
            }
            this.textArrayP = textArray;
            //this.textArray = (String[]) list.toArray(new String[list.size()]);
        } else {
            this.textArrayP = StringUtil.getInstance().ONE_EMPTY_STRING_ARRAY;
        }
    }

    public String[] getTextArray()
    {
        return textArrayP;
    }
    
    public int getHeight() {
        final MyFont myFont = MyFont.getInstance();
        return myFont.DEFAULT_CHAR_HEIGHT;
    }
    
}
