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

public class BaseAnimationInterfaceFactoryInterfaceComposite
    implements AnimationInterfaceFactoryInterface
{
    private BasicAnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray;

    public BaseAnimationInterfaceFactoryInterfaceComposite(
            BasicAnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray)
    {
        this.basicAnimationInterfaceFactoryInterfaceArray = basicAnimationInterfaceFactoryInterfaceArray;
    }

    @Override
    public Animation getInstance(final int instanceId) throws Exception
    {
        return NullAnimationFactory.getFactoryInstance().getInstance(0);
    }

    /**
     * @return the basicAnimationInterfaceFactoryInterfaceArray
     */
    public BasicAnimationInterfaceFactoryInterface[] getBasicAnimationInterfaceFactoryInterfaceArray() {
        return basicAnimationInterfaceFactoryInterfaceArray;
    }

    /**
     * @param basicAnimationInterfaceFactoryInterfaceArray the basicAnimationInterfaceFactoryInterfaceArray to set
     */
    public void setBasicAnimationInterfaceFactoryInterfaceArray(BasicAnimationInterfaceFactoryInterface[] basicAnimationInterfaceFactoryInterfaceArray) {
        this.basicAnimationInterfaceFactoryInterfaceArray = basicAnimationInterfaceFactoryInterfaceArray;
    }

    @Override
    public void setInitialScale(final ScaleProperties scaleProperties) {

    }
    
}
