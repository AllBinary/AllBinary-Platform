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

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.NullIndexedAnimation;
import org.allbinary.animation.RotationAnimation;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.ScaleProperties;

/**
 *
 * @author User
 */
public class LazyImageRotationAnimation extends RotationAnimation {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final CommonStrings commonStrings = CommonStrings.getInstance();

    public final int layoutIndex;
    public final int instanceId;
    public final BaseImageAnimationFactory animationInterfaceFactoryInterface;

    private final IndexedAnimation NULL_INDEX_ANIMATION;
    private IndexedAnimation animation;

    public ScaleProperties scaleProperties = ScaleProperties.instance;
    
    //private float scaleX;
    //private float scaleY;
    
    public LazyImageRotationAnimation(final int layoutIndex, final int instanceId, final ScaleProperties scaleProperties, final BaseImageAnimationFactory animationInterfaceFactoryInterface, final AnimationBehavior animationBehavior) {
        super(animationBehavior);

        this.layoutIndex = layoutIndex;
        this.instanceId = instanceId;
        this.animationInterfaceFactoryInterface = animationInterfaceFactoryInterface;
        
        final ImageCache imageCache = ImageCacheFactory.getInstance();
        imageCache.add(this);
        
        this.scaleProperties = scaleProperties;

        NULL_INDEX_ANIMATION = new NullIndexedAnimation(animationBehavior) {
            public void paint(final Graphics graphics, final int x, final int y) {
            }
        };
        
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
                    animation = NULL_INDEX_ANIMATION;
                } catch (Exception e) {
                    logUtil.put(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e);
                }

            }
            
            public void paintThreed(final Graphics graphics, final int x, final int y, final int z) {

                try {
                    ImageCacheFactory.getInstance().insertFirst(LazyImageRotationAnimation.this);
                    animation = NULL_INDEX_ANIMATION;
                } catch (Exception e) {
                    logUtil.put(commonStrings.EXCEPTION, this, commonStrings.PROCESS, e);
                }
                
            }
            
        };

    }

    private static final String SET_REAL_ANIMATION = "setRealAnimation";
    
    public void setRealAnimation() {
        try {
            final IndexedAnimation animation = this.animation;
            this.animationInterfaceFactoryInterface.setInitialScale(scaleProperties);
            this.animation = (IndexedAnimation) this.animationInterfaceFactoryInterface.getInstance(this.instanceId);
            this.animation.setState(animation);
            //this.animation.setScale(this.scaleX, this.scaleY);
            //logUtil.put(new StringMaker().append(this.toString()).append(this.animation.getClass().getName()).toString(), this, SET_REAL_ANIMATION);
        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, SET_REAL_ANIMATION, e);
        }
    }

    @Override
    public void setScale(final float scaleX, final float scaleY) {
        //this.scaleX = scaleX;
        //this.scaleY = scaleY;
        this.animation.setScale(scaleX, scaleY);
    }
    
    @Override
    public AnimationBehavior getAnimationBehavior() {
        return this.animation.getAnimationBehavior();
    }
    
    @Override
    public void set(final GL gl) throws Exception
    {
        this.animation.set(gl);
    }
    
    @Override
    public void setAlpha(final int alpha) {
        this.animation.setAlpha(alpha);
    }

    @Override
    public void setDx(final int dx) {
        this.animation.setDx(dx);
    }

    @Override
    public void setDy(final int dy) {
        this.animation.setDy(dy);
    }

    @Override
    public void setMaxScale(final float maxScaleX, final float maxScaleY) {
        this.animation.setMaxScale(maxScaleX, maxScaleY);
    }

    @Override
    public void changeBasicColor(final BasicColor basicColor)
    {
        this.animation.changeBasicColor(basicColor);
    }

    @Override
    public BasicColor getBasicColorP()
    {
        return this.animation.getBasicColorP();
    }
    
    @Override
    public BasicColor getChangeBasicColor()
    {
        return this.animation.getChangeBasicColor();
    }
    
    @Override
    public int getChangeColor()
    {
        return this.animation.getChangeColor();
    }
    
    @Override
    public int getColor()
    {
        return this.animation.getColor();
    }
    
    @Override
    public int getDx() {
        return this.animation.getDx();
    }    
    
    @Override
    public int getDy() {
        return this.animation.getDy();
    }    

    @Override
    public boolean isThreed() {
        return this.animation.isThreed();
    }
    
    @Override
    public void nextFrame() throws Exception
    {
        this.animation.nextFrame();
    }
    
    @Override
    public void reset()
    {
        this.animation.reset();
    }
    
    @Override
    public void setFrame(final int index) {
        this.animation.setFrame(index);
    }

    @Override
    public int getFrame() {
        return this.animation.getFrame();
    }
    
    @Override
    public int getAnimationSize() throws Exception
    {
        return this.animation.getAnimationSize();
    }
    
    @Override
    public int getSize()
    {
        return this.animation.getSize();
    }

    @Override
    public void previousFrame()
    {
        this.animation.previousFrame();
    }

    @Override
    public boolean isLastFrame()
    {
        return this.animation.isLastFrame();
    }
    
    @Override
    public void setSequence(final int[] sequence)
    {
        this.animation.setSequence(sequence);
    }

    @Override
    public int[] getSequence()
    {
        return this.animation.getSequence();
    }

    @Override
    public int getWidth() {
        return this.animation.getWidth();
    }
    
    public void paint(final Graphics graphics, final int x, final int y) {

        try {
            this.animation.paint(graphics, x, y);
        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, this.commonStrings.PROCESS, e);
        }

    }

    public void paintThreed(final Graphics graphics, final int x, final int y, final int z) {

        try {
            this.animation.paintThreed(graphics, x, y, z);
        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, this.commonStrings.PROCESS, e);
        }

    }

    public String toString() {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        final Image image = this.animationInterfaceFactoryInterface.getImage();
        return new StringMaker().append(super.toString()).append(commonSeps.SPACE).append(image.getName()).append(commonSeps.SPACE).append(image.getWidth()).append(commonSeps.SPACE).append(image.getHeight()).toString();
    }
}
