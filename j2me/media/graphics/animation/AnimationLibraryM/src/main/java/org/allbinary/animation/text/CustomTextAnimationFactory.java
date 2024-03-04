/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;

/**
 *
 * @author User
 */
public class CustomTextAnimationFactory 
    implements AnimationInterfaceFactoryInterface {
 
    private final AnimationBehaviorFactory animationBehaviorFactory;
    
    public BasicColor basicColor = BasicColorFactory.getInstance().BLACK;
    private String text;

    private int initScaleHeight;
    //private int scaleWidth;
    private int scaleHeight;

    private int dx;
    private int dy;

    public CustomTextAnimationFactory(final String text, final int fontSize, final int dx, final int dy) {
        
        this(text, fontSize, AnimationBehaviorFactory.getInstance());
        
        this.dx = dx;
        this.dy = dy;
    }
    
    public CustomTextAnimationFactory(final String text, final int fontSize, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) {
        
        this(text, fontSize, animationBehaviorFactory);
        
        this.dx = dx;
        this.dy = dy;
    }
        
    public CustomTextAnimationFactory(final String text, final int fontSize) {
        
        this(text, fontSize, AnimationBehaviorFactory.getInstance());
        
    }
    
    public CustomTextAnimationFactory(final String text, final int fontSize, final AnimationBehaviorFactory animationBehaviorFactory) {
        
        this.text = text;
        this.initScaleHeight = this.scaleHeight = (int) fontSize - (fontSize / 4);
        this.animationBehaviorFactory = animationBehaviorFactory;
    }

    public Animation getInstance()throws Exception {
        
        CustomTextAnimation customTextAnimation;
        if (dx != 0 || dy != 0) {
            customTextAnimation = new AdjustCustomTextAnimation(text, this.scaleHeight, dx, dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            customTextAnimation = new CustomTextAnimation(text, this.scaleHeight, this.animationBehaviorFactory.getOrCreateInstance());
        }

        customTextAnimation.setBasicColor(basicColor);
        return customTextAnimation;
    }
    
    //@Override
    public void setInitialSize(final int width, final int height) {
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("setInitialSize - font: ").append(height).toString(), this, CommonStrings.getInstance().PROCESS));
        //this.scaleWidth = width;
        final int fontSize = height;
        this.scaleHeight = (int) fontSize - (fontSize / 4);
    }

    public int getWidth() {
        final Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, this.initScaleHeight);
        return font.stringWidth(this.text);
    }

    public int getHeight() {
        final Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, this.initScaleHeight);
        return font.getHeight();
    }

}
