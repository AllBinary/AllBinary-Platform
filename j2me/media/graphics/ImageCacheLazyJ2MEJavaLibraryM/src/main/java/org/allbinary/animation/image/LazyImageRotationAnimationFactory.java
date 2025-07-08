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

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.media.ScaleProperties;

/**
 *
 * @author User
 */
public class LazyImageRotationAnimationFactory implements AnimationInterfaceFactoryInterface {
    //protected final LogUtil logUtil = LogUtil.getInstance();

    
    private final int layoutIndex;
    private final BaseImageAnimationFactory animationInterfaceFactoryInterface;
    
    public ScaleProperties scaleProperties = ScaleProperties.instance;
    
    public LazyImageRotationAnimationFactory(final int layoutIndex, final int associatedLazyAnimationId, final BaseImageAnimationFactory animationInterfaceFactoryInterface) {
        this.layoutIndex = layoutIndex;
        this.animationInterfaceFactoryInterface = animationInterfaceFactoryInterface;
        ImageCacheFactory.getInstance().hasAnyLazyAnimationFactories = true;
    }
    
    public Animation getInstance(final int instanceId) throws Exception {
        if(this.animationInterfaceFactoryInterface.getImage().isReady()) {
            this.animationInterfaceFactoryInterface.setInitialScale(scaleProperties);
            return this.animationInterfaceFactoryInterface.getInstance(instanceId);
        } else {
            return new LazyImageRotationAnimation(this.layoutIndex, instanceId, scaleProperties, this.animationInterfaceFactoryInterface, this.animationInterfaceFactoryInterface.animationBehaviorFactory.getOrCreateInstance());
        }
    }
    
    public void setInitialScale(final ScaleProperties scaleProperties) {
        //final CommonStrings commonStrings = CommonStrings.getInstance();
        //logUtil.put(scaleProperties.toString(), this, commonStrings.CONSTRUCTOR);
        this.scaleProperties = scaleProperties;
        //this.animationInterfaceFactoryInterface.setInitialScale(scaleProperties);
    }
    
}
