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

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.media.ScaleProperties;

public class VectorRotationAnimationInterfaceFactory 
    implements AnimationInterfaceFactoryInterface {

	private int currentPoints[][][];
	private BasicColor basicColor;
        protected final AnimationBehaviorFactory animationBehaviorFactory;

        public VectorRotationAnimationInterfaceFactory(final int[][][] currentPoints, final BasicColor basicColor) {
            this(currentPoints, basicColor, AnimationBehaviorFactory.getInstance());
        }
        
	public VectorRotationAnimationInterfaceFactory(final int[][][] currentPoints, final BasicColor basicColor, final AnimationBehaviorFactory animationBehaviorFactory) {
		this.currentPoints = currentPoints;
		this.setBasicColor(basicColor);
                this.animationBehaviorFactory = animationBehaviorFactory;
	}

	public Animation getInstance(final int instanceId) throws Exception {
		return new VectorRotationAnimation(this.currentPoints, this.getBasicColor(), this.animationBehaviorFactory.getOrCreateInstance());
	}

	protected void setBasicColor(BasicColor basicColor) {
		this.basicColor = basicColor;
	}

	protected BasicColor getBasicColor() {
		return basicColor;
	}

   public void setInitialScale(final ScaleProperties scaleProperties) {
       
   }

}
