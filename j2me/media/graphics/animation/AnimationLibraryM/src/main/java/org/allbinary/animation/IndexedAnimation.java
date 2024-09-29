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
package org.allbinary.animation;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.math.PrimitiveIntUtil;

/**
 * 
 * @author user
 */
public class IndexedAnimation extends Animation 
    implements IndexedAnimationInterface
{
    protected final AnimationBehavior animationBehavior;
    
    private IndexedAnimation()
    {
        this.animationBehavior = null;
    }

    protected IndexedAnimation(final AnimationBehavior animationBehavior)
    {
        this.animationBehavior = animationBehavior;
    }
 
    /**
     * @return the animationBehavior
     */
    public AnimationBehavior getAnimationBehavior() {
        return animationBehavior;
    }
    
    public void reset()
    {
        this.animationBehavior.reset();
        this.setFrame(0);
    }
    
    public void setFrame(final int index)
    {

    }

    public int getFrame()
    {
        return 0;
    }

    public int getAnimationSize() throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
        //return this.getSize();
    }
    
    public int getSize()
    {
        return 0;
    }

    public void previousFrame()
    {

    }

    public boolean isLastFrame()
    {
        if(this.getFrame() == this.getSize() - 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void setSequence(final int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }
    
    public void setState(final IndexedAnimation indexedAnimation) {
        this.setFrame(indexedAnimation.getFrame());
        this.setDx(indexedAnimation.getDx());
        this.setDy(indexedAnimation.getDy());
    }
    
    //TWB - This is really only for ImageBaseRotationAnimation and is hackish
    public int getWidth() {
        return 0;
    }
    
}
