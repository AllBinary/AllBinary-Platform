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
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

/**
 *
 * @author User
 */
public class CustomTextAnimationFactory 
    implements AnimationInterfaceFactoryInterface {
 
    private final AnimationBehaviorFactory animationBehaviorFactory;
    
    private String text;

    //private int scaleWidth;
    private int scaleHeight;

    public CustomTextAnimationFactory(final String text, final int fontSize) {
        
        this(text, fontSize, AnimationBehaviorFactory.getInstance());
        
    }
    
    public CustomTextAnimationFactory(final String text, final int fontSize, final AnimationBehaviorFactory animationBehaviorFactory) {
        
        this.text = text;
        this.scaleHeight = (int) fontSize - (fontSize / 4);
        this.animationBehaviorFactory = animationBehaviorFactory;
    }

    public Animation getInstance()throws Exception {
        return new CustomTextAnimation(text, this.scaleHeight, this.animationBehaviorFactory.getOrCreateInstance());
    }
    
    //@Override
    public void setInitialSize(final int width, final int height) {
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("setInitialSize - font: ").append(height).toString(), this, CommonStrings.getInstance().PROCESS));
        //this.scaleWidth = width;
        final int fontSize = height;
        this.scaleHeight = (int) fontSize - (fontSize / 4);
    }

    public int getWidth() {
        final Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, this.scaleHeight);
        return font.stringWidth(this.text);
    }

    public int getHeight() {
        final Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, this.scaleHeight);
        return font.getHeight();
    }

}
