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
import org.allbinary.animation.AnimationInterfaceFactoryInterface;

/**
 *
 * @author User
 */
public class CustomTextAnimationFactory 
    implements AnimationInterfaceFactoryInterface {
 
    private String text;
    private int fontSize;

    public CustomTextAnimationFactory(final String text, final int fontSize) {
        
    }

    public Animation getInstance()throws Exception {
        return new CustomTextAnimation(text, fontSize);
    }
    
    public void setInitialSize(final int width, final int height) {
        
    }

}
