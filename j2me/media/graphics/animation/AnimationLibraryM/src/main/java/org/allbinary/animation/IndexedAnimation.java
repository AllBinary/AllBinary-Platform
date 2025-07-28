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

import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.string.CommonStrings;

/**
 * 
 * @author user
 */
public class IndexedAnimation extends Animation 
    implements IndexedAnimationInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    protected final AnimationBehavior animationBehaviorP;
    
    private IndexedAnimation()
    {
        this.animationBehaviorP = AnimationBehavior.getInstance();
    }

    protected IndexedAnimation(final AnimationBehavior animationBehavior)
    {
        this.animationBehaviorP = animationBehavior;
    }
 
    /**
     * @return the animationBehavior
     */
    public AnimationBehavior getAnimationBehavior() {
        return animationBehaviorP;
    }
   
    @Override
    public void reset()
    {
        this.animationBehaviorP.reset();
        this.setFrame(0);
    }
    
    @Override
    public void setFrame(final int index)
    {

    }

    @Override
    public int getFrame()
    {
        return 0;
    }

    public int getAnimationSize() throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
        //return this.getSize();
    }
    
    @Override
    public int getSize()
    {
        return 0;
    }

    @Override
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
    
    @Override
    public void setSequence(final int[] sequence)
    {

    }

    @Override
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
