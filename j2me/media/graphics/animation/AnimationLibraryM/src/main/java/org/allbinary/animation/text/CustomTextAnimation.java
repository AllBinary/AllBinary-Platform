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
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;

import org.allbinary.graphics.font.FontDebugFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

/**
 *
 * @author User
 */
public class CustomTextAnimation extends TextAnimation
{
    protected final FontDebugFactory fontDebugFactory = FontDebugFactory.getInstance();
    
    protected final int fontSize;
    
    protected Font font;

    public CustomTextAnimation(final String text, final int fontSize, final AnimationBehavior animationBehavior)
    {
        super(text, animationBehavior);
        
        this.fontSize = fontSize;
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("font: ").append(fontSize).append(" text: ").append(text).toString(), this, CommonStrings.getInstance().PROCESS));
        this.font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, fontSize);
        
        this.hack();
    }
    
    private int xtraWidth = 0;
    private void hack() {
        //TWB - Hack for HTML5 build since FontUtil microemu-playn kerning is not correct
        final Features features = Features.getInstance();
        final boolean isHTML = features.isDefault(HTMLFeatureFactory.getInstance().HTML);
        if(isHTML) {
            if(fontSize >= 15 && fontSize <= 23) {
                this.xtraWidth = (fontSize * this.getText().length * this.getText().length);
            } else if(fontSize < 15) {
                this.xtraWidth = (this.getText().length * this.getWidth() / 3);
            } else {
                this.xtraWidth = (fontSize * fontSize * this.getText().length * this.getWidth() / 1800);
            }
        }
    }
    
    public void paint(final Graphics graphics, final int x, final int y)
    {
        final Font existingFont = graphics.getFont();
        
        fontDebugFactory.setFont(this.font, graphics);
        
        super.paint(graphics, x + this.xtraWidth, y);
        
        fontDebugFactory.setFont(existingFont, graphics);
    }
  
    public void setScale(final float scaleX, final float scaleY) {
        if(scaleX != scaleY) {
            throw new RuntimeException();
        }

        LogUtil.put(LogFactory.getInstance(new StringMaker().append("setScale font: ").append((fontSize * scaleX)).append(" text: ").append(this.getText()).toString(), this, CommonStrings.getInstance().PROCESS));
        this.font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, (int) (fontSize * scaleX));
    }
    
    public int getWidth() {
        return this.font.stringWidth(this.textArray[0]);
    }

    public int getHeight() {
        return this.font.getHeight();
    }

}
