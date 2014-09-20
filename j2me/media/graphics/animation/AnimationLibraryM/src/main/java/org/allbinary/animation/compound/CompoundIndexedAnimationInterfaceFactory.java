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
package org.allbinary.animation.compound;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.IndexedAnimation;

public class CompoundIndexedAnimationInterfaceFactory
    implements AnimationInterfaceFactoryInterface
{
    private AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray;

    public CompoundIndexedAnimationInterfaceFactory(
        AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray)
    {
        this.basicAnimationInterfaceFactoryInterfaceArray = basicAnimationInterfaceFactoryInterfaceArray;
    }

    public Animation getInstance() throws Exception
    {
        int size = this.basicAnimationInterfaceFactoryInterfaceArray.length;
        IndexedAnimation[] animationInterfaceArray =
            new IndexedAnimation[size];

        for (int index = 0; index < size; index++)
        {
            animationInterfaceArray[index] = (IndexedAnimation) this.basicAnimationInterfaceFactoryInterfaceArray[index].getInstance();
        }

        return new CompoundIndexedAnimation(animationInterfaceArray);
    }
}
