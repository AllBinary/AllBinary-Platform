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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Graphics;
import org.allbinary.animation.Animation;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.RotationAnimation;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;

/**
 *
 * @author User
 */
public class LazyImageRotationAnimation extends RotationAnimation {

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public final BaseImageAnimationFactory animationInterfaceFactoryInterface;
    
    private Animation animation = new Animation() {
        public void paint(final Graphics graphics, final int x, final int y) {
            ImageCacheFactory.getInstance().insertFirst(LazyImageRotationAnimation.this);
            animation = NullAnimationFactory.getFactoryInstance().getInstance();
        }
    };

    public LazyImageRotationAnimation(final BaseImageAnimationFactory animationInterfaceFactoryInterface, final AnimationBehavior animationBehavior) {
        super(animationBehavior);

        this.animationInterfaceFactoryInterface = animationInterfaceFactoryInterface;
    }

    public void setRealAnimation() {
        try {
            this.animation = this.animationInterfaceFactoryInterface.getInstance();
            LogUtil.put(LogFactory.getInstance(this.animationInterfaceFactoryInterface.getImage().getName() + this.animation.getClass().getName(), this, "setRealAnimation"));
        } catch(Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.commonStrings.CONSTRUCTOR, e));
        }
    }
    
    public void paint(final Graphics graphics, final int x, final int y) {
        super.paint(graphics, x, y);
      
        try {
            this.animation.paint(graphics, x, y);
        } catch(Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.commonStrings.PROCESS, e));
        }
        
    }

    public void paintThreed(final Graphics graphics, final int x, final int y, final int z) {
        super.paintThreed(graphics, x, y, z);

        try {
            this.animation.paintThreed(graphics, x, y, z);
        } catch(Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.commonStrings.PROCESS, e));
        }

    }

}
