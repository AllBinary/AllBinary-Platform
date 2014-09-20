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

import org.allbinary.logic.basic.NotImplemented;
import org.allbinary.logic.math.PrimitiveIntUtil;

/**
 * 
 * @author user
 */
public class IndexedAnimation extends Animation 
    implements IndexedAnimationInterface
{
    protected IndexedAnimation()
    {

    }

    public void setFrame(int index)
    {

    }

    public int getFrame()
    {
        return 0;
    }

    public int getAnimationSize() throws Exception
    {
        throw new Exception(NotImplemented.NAME);
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
    
    public void setSequence(int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }
}
