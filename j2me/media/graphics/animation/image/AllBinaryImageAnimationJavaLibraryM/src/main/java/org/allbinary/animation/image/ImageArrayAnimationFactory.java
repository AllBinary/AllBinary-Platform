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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.media.ScaleProperties;

public class ImageArrayAnimationFactory 
implements AnimationInterfaceFactoryInterface
{
    private int dx = 0;
    private int dy = 0;
    
    private final Image[] imageArray;

    private final AnimationBehaviorFactory animationBehaviorFactory;

    public ImageArrayAnimationFactory(final Image[] imageArray, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) 
        throws Exception {
        
        this.imageArray = imageArray;
        this.animationBehaviorFactory = animationBehaviorFactory;

    	this.dx = dx;
    	this.dy = dy;
        
//        if(animationBehaviorFactory == AnimationBehaviorFactory.getInstance()) {
//            ForcedLogUtil.log("Using default AnimationBehaviorFactory with IndexedAnimationFactory", this);
//        }
        
    }

    @Override
    public Animation getInstance(final int instanceId) throws Exception
    {
        if (this.dx != 0 || this.dy != 0) {
            return new AdjustedImageArrayAnimation(this.imageArray, this.dx, this.dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            return new ImageArrayAnimation(this.imageArray, AnimationBehavior.getInstance());
        }
    }

    @Override
    public void setInitialScale(final ScaleProperties scaleProperties) {
        
    }

}
