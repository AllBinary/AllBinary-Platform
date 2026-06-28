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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class TextAnimation extends IndexedAnimation 
    implements UpdateMyFontInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //private final int WIDTH = 13;
    //private final int HEIGHT = 8;
    
    private final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    protected MyFontProcessor myFontProcessor = this.updateMyFontProcessor;
    
    protected String[] textArrayP = StringUtil.getInstance().ONE_EMPTY_STRING_ARRAY;

    private int anchor = Anchor.TOP_LEFT;

    private int fontHeight = 0;

    private TextChangeListener textChangeListener = TextChangeListener.getInstance();
    
    public TextAnimation(final String text, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        //this.textArray = new String[1];
        //this.textArray[0] = text;
        this.setText(text);
    }
    
    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.fontHeight = font.getHeight();
        this.textChangeListener.onMeasure();
        this.textChangeListener = TextChangeListener.getInstance();
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    @Override
    public void nextFrame() throws Exception
    {
    }

    @Override
    public void paintXY(Graphics graphics, int x, int y)
    {
        this.myFontProcessor.process(graphics);
        this.paintXYNoUpdate(graphics, x, y);
    }
    
    public void paintXYNoUpdate(Graphics graphics, int x, int y)
    {
        this.basicSetColorUtil.setBasicColorP3(
                graphics, this.getBasicColorP(), this.getColor());

        final int size = this.textArrayP.length;
        for(int index = 0; index < size; index++) {
            //this.logUtil.putF(new StringMaker().append(textArray[index]).append(CommonSeps.getInstance().SPACE).append(x).append(CommonSeps.getInstance().SPACE).append(y).toString(), this, this.commonStrings.PROCESS);
            graphics.drawString(this.textArrayP[index], x, y + (index * this.fontHeight), this.anchor);
            //graphics.drawString(textArray[index], x + WIDTH, y + (index * height) + HEIGHT, anchor);
        }

    }

    public void setTextWithOnMeasure(final String text, final TextChangeListener textChangeListener) {
        this.setText(text);
        this.textChangeListener = textChangeListener;
    }
    
    public void setText(final String text)
    {
        final BasicArrayList list = new BasicArrayListD();

        //this.logUtil.putF(new StringMaker().append(" text: ").append(text).toString(), this, this.commonStrings.PROCESS);        
        if(text != null && text.length() > 0) {
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
                //this.logUtil.putF(new StringMaker().append("startIndex: ").append(startIndex).append(" endIndex: ").append(endIndex).toString(), this, this.commonStrings.PROCESS);
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
        
        this.myFontProcessor = this.updateMyFontProcessor;
    }

    public String[] getTextArray()
    {
        return this.textArrayP;
    }

    public int getFontHeight() {
        return this.fontHeight;
    }
    
}
