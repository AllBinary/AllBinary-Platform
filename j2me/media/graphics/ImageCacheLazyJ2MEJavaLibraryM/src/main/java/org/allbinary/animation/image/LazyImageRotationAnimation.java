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
import javax.microedition.lcdui.Image;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.NullRotationAnimationFactory;
import org.allbinary.animation.RotationAnimation;
import org.allbinary.game.resource.GDResources;
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

    private IndexedAnimation animation;

    public LazyImageRotationAnimation(final BaseImageAnimationFactory animationInterfaceFactoryInterface, final AnimationBehavior animationBehavior) {
        super(animationBehavior);

        animation = new IndexedAnimation(animationBehavior) {
            
            private int index;
            
            public void setFrame(final int index) {
                this.index = index;
            }

            public int getFrame() {
                return this.index;
            }
            
            public void paint(final Graphics graphics, final int x, final int y) {

                try {
                    ImageCacheFactory.getInstance().insertFirst(LazyImageRotationAnimation.this);
                    animation = (IndexedAnimation) NullRotationAnimationFactory.getFactoryInstance().getInstance();
                } catch (Exception e) {
                    LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e));
                }

            }
        };
        
        this.animationInterfaceFactoryInterface = animationInterfaceFactoryInterface;
    }

    public void setRealAnimation() {
        try {
            final IndexedAnimation animation = this.animation;
            this.animation = (IndexedAnimation) this.animationInterfaceFactoryInterface.getInstance();
            this.animation.setFrame(animation.getFrame());
            LogUtil.put(LogFactory.getInstance(this.animationInterfaceFactoryInterface.getImage().getName() + this.animation.getClass().getName(), this, "setRealAnimation"));
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.commonStrings.CONSTRUCTOR, e));
        }
    }

    public void nextFrame() throws Exception
    {
        this.animation.nextFrame();
    }
    
    public void reset()
    {
        this.animation.reset();
    }

    public void setFrame(final int index) {
        this.animation.setFrame(index);
    }

    public int getFrame() {
        return this.animation.getFrame();
    }
    
    public int getAnimationSize() throws Exception
    {
        return this.animation.getAnimationSize();
    }
    
    public int getSize()
    {
        return this.animation.getSize();
    }

    public void previousFrame()
    {
        this.animation.previousFrame();
    }

    public boolean isLastFrame()
    {
        return this.animation.isLastFrame();
    }
    
    public void setSequence(final int[] sequence)
    {
        this.animation.setSequence(sequence);
    }

    public int[] getSequence()
    {
        return this.animation.getSequence();
    }
    
    public void paint(final Graphics graphics, final int x, final int y) {

        try {
            this.animation.paint(graphics, x, y);
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.commonStrings.PROCESS, e));
        }

    }

    public void paintThreed(final Graphics graphics, final int x, final int y, final int z) {

        try {
            this.animation.paintThreed(graphics, x, y, z);
        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.commonStrings.PROCESS, e));
        }

    }

}
