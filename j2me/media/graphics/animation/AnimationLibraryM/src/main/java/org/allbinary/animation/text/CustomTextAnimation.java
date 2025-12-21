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
import org.allbinary.graphics.font.FontDebugFactory;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class CustomTextAnimation extends TextAnimation
    implements GetTextInterface
{

    protected final FontDebugFactory fontDebugFactory = FontDebugFactory.getInstance();
    
    protected final int fontSize;
    
    protected Font font;

    private String lastText = StringUtil.getInstance().EMPTY_STRING;
    protected boolean hasChanged = true;
    
    public CustomTextAnimation(final String text, final int fontSize, final AnimationBehavior animationBehavior)
    {
        super(text, animationBehavior);
        
        this.fontSize = fontSize;
        this.font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, fontSize);
        //final CommonLabels commonLabels = CommonLabels.getInstance();
        //logUtil.put(new StringMaker().append("Font size: ").append(fontSize).append(' ').append(font.getSize()).append(commonLabels.WIDTH_LABEL).append(font.stringWidth(text)).append(" text: ").append(text).toString(), this, commonStrings.PROCESS);
        
        //this.hack();
    }
    
//    private int xtraWidth = 0;
//    private void hack() {
//        //TWB - Hack for HTML5 build since FontUtil microemu-playn kerning is not correct
//        final Features features = Features.getInstance();
//        final boolean isHTML = features.isDefault(HTMLFeatureFactory.getInstance().HTML);
//        if(isHTML) {
//            if(fontSize >= 15 && fontSize <= 23) {
//                this.xtraWidth = (fontSize * this.getText().length * this.getText().length);
//            } else if(fontSize < 15) {
//                this.xtraWidth = (this.getText().length * this.getWidth() / 3);
//            } else {
//                this.xtraWidth = (fontSize * fontSize * this.getText().length * this.getWidth() / 1800);
//            }
//        }
//    }
    
    @Override
    public void paint(final Graphics graphics, final int x, final int y)
    {
        final Font existingFont = graphics.getFont();
        
        fontDebugFactory.setFont(this.font, graphics);
        
        //super.paint(graphics, x + this.xtraWidth, y);
        super.paint(graphics, x, y);
        
        fontDebugFactory.setFont(existingFont, graphics);
    }
  
    @Override
    public void setScale(final float scaleX, final float scaleY) {
        if(scaleX != scaleY) {
            throw new RuntimeException();
        }

        //logUtil.put(new StringMaker().append("setScale font: ").append((fontSize * scaleX)).append(" text: ").append(this.getText()).toString(), this, commonStrings.PROCESS);
        this.hasChanged = true;
        this.font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, (int) (fontSize * scaleX));
//        if(this.getText().length > 0) {
//            logUtil.put(new StringMaker().append("setScale font: ").append(font.getSize()).append(" text: ").append(this.getText()[0]).toString(), this, commonStrings.PROCESS);
//        }
    }
    
    @Override
    public void setText(final String text)
    {
        if(this.lastText != text) {
            this.hasChanged = true;
            this.lastText = text;
            super.setText(text);
        }

//        if(font != null) {
//            final CommonLabels commonLabels = CommonLabels.getInstance();
//            logUtil.put(new StringMaker().append("setText - font: ").append(font.getSize()).append(commonLabels.WIDTH_LABEL).append(font.stringWidth(text)).append(" text: ").append(text).toString(), this, commonStrings.PROCESS);
//        }
    }

    public String getText() {
        return this.lastText;
    }

    private int textWidth;
    @Override
    public int getWidth() {
        if(this.hasChanged) {
            this.textWidth = this.font.stringWidth(this.textArray[0]);
            this.hasChanged = false;
        }
        return this.textWidth;
    }

    @Override
    public int getHeight() {
        return this.font.getHeight();
    }

}
