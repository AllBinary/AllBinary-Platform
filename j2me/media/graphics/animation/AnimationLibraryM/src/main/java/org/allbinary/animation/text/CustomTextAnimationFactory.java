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

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;

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
    
    public CustomTextAnimationFactory(final String text, final int fontSize, final AnimationBehaviorFactory animationBehaviorFactory) {
        
        this.scaleHeight = fontSize;
        this.animationBehaviorFactory = animationBehaviorFactory;
    }

    public Animation getInstance()throws Exception {
        return new CustomTextAnimation(text, this.scaleHeight, this.animationBehaviorFactory.getOrCreateInstance());
    }
    
    @Override
    public void setInitialSize(final int width, final int height) {
        //this.scaleWidth = width;
        this.scaleHeight = height;
    }

}
