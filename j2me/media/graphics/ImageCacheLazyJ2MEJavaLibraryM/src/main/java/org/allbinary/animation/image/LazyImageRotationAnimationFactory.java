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
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.media.ScaleProperties;

/**
 *
 * @author User
 */
public class LazyImageRotationAnimationFactory implements AnimationInterfaceFactoryInterface {
    
    private final BaseImageAnimationFactory animationInterfaceFactoryInterface;
    
    public ScaleProperties scaleProperties = ScaleProperties.instance;
    
    public LazyImageRotationAnimationFactory(final BaseImageAnimationFactory animationInterfaceFactoryInterface) {
        this.animationInterfaceFactoryInterface = animationInterfaceFactoryInterface;
    }
    
    public Animation getInstance() throws Exception {
        if(this.animationInterfaceFactoryInterface.getImage().getImage() != null) {
            this.animationInterfaceFactoryInterface.setInitialScale(scaleProperties);
            return this.animationInterfaceFactoryInterface.getInstance();
        } else {
            return new LazyImageRotationAnimation(scaleProperties, this.animationInterfaceFactoryInterface, this.animationInterfaceFactoryInterface.animationBehaviorFactory.getOrCreateInstance());
        }        
    }
    
    public void setInitialScale(final ScaleProperties scaleProperties) {
        //final CommonStrings commonStrings = CommonStrings.getInstance();
        //LogUtil.put(LogFactory.getInstance(scaleProperties.toString(), this, commonStrings.CONSTRUCTOR));
        this.scaleProperties = scaleProperties;
        //this.animationInterfaceFactoryInterface.setInitialScale(scaleProperties);
    }
    
}
