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
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.ScaleProperties;

/**
 *
 * @author User
 */
public class CustomTextAnimationFactory 
    implements AnimationInterfaceFactoryInterface {
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final AnimationBehaviorFactory animationBehaviorFactory;
    
    public BasicColor basicColor = BasicColorFactory.getInstance().BLACK;
    private String text;

    private int initScaleHeight;

    private int dx = 0;
    private int dy = 0;

    protected Font font;
    
    public ScaleProperties scaleProperties;
    
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
        
        this.scaleProperties = new ScaleProperties();
        this.text = text;
        this.scaleProperties.scaleHeight = (int) fontSize - (fontSize / 4);
        this.initScaleHeight = this.scaleProperties.scaleHeight;
        this.animationBehaviorFactory = animationBehaviorFactory;
        
        this.font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, this.initScaleHeight);
    }

    @Override
    public Animation getInstance(final int instanceId) throws Exception {
        
        CustomTextAnimation customTextAnimation;
        if (dx != 0 || dy != 0) {
            customTextAnimation = new AdjustCustomTextAnimation(text, this.scaleProperties.scaleHeight, dx, dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            customTextAnimation = new CustomTextAnimation(text, this.scaleProperties.scaleHeight, this.animationBehaviorFactory.getOrCreateInstance());
        }

        customTextAnimation.setBasicColor(basicColor);
        return customTextAnimation;
    }
    
    @Override
    public void setInitialScale(final ScaleProperties scaleProperties) {
//        this.scaleProperties = scaleProperties;
//        logUtil.put(new StringMaker().append("setInitialSize - Font size: ").append(this.scaleProperties.scaleHeight).toString(), this, commonStrings.PROCESS);
//        this.font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, this.scaleProperties.scaleHeight);
//        final CommonLabels commonLabels = CommonLabels.getInstance();
//        logUtil.put(new StringMaker().append("setInitialSize - Font size: ").append(font.getSize()).append(commonLabels.WIDTH_LABEL).append(font.stringWidth(this.text)).append(" text: ").append(text).toString(), this, commonStrings.PROCESS);
    }

    public int getWidth() {
//        final CommonLabels commonLabels = CommonLabels.getInstance();
//        logUtil.put(new StringMaker().append("getWidth - font: ").append(font.getSize()).append(commonLabels.WIDTH_LABEL).append(font.stringWidth(this.text)).append(" text: ").append(text).toString(), this, commonStrings.PROCESS);
        return font.stringWidth(this.text);
    }

    public int getHeight() {
        
        return font.getHeight();
    }

}
