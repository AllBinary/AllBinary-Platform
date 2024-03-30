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

import org.allbinary.media.ScaleProperties;

public class AnimationInterfaceFactoryInterfaceComposite
    implements AnimationInterfaceFactoryInterface
{
    private final AnimationInterfaceFactoryInterface[] animationInterfaceFactoryInterfaceArray;

    public AnimationInterfaceFactoryInterfaceComposite(
            final AnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray)
    {
        this.animationInterfaceFactoryInterfaceArray = basicAnimationInterfaceFactoryInterfaceArray;
    }

    public Animation getInstance() throws Exception
    {
        return null;
    }

    /**
     * @return the basicAnimationInterfaceFactoryInterfaceArray
     */
    public AnimationInterfaceFactoryInterface[] getAnimationInterfaceFactoryInterfaceArray() {
        return animationInterfaceFactoryInterfaceArray;
    }

   public void setInitialScale(final ScaleProperties scaleProperties) {
       
   }
    
}
