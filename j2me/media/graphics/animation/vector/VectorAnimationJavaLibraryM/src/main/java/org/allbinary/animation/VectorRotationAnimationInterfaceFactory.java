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
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.NullUtil;
import org.allbinary.media.ScaleProperties;

public class VectorRotationAnimationInterfaceFactory 
    implements AnimationInterfaceFactoryInterface {

	private int currentPoints[][][] = NullUtil.getInstance().NULL_INT_ARRAY_ARRAY_ARRAY;
	private BasicColor basicColor = BasicColorFactory.getInstance().WHITE;
        protected final AnimationBehaviorFactory animationBehaviorFactory;

        public VectorRotationAnimationInterfaceFactory(final int[][][] currentPoints, final BasicColor basicColor) {
            this(currentPoints, basicColor, AnimationBehaviorFactory.getInstance());
        }
        
	public VectorRotationAnimationInterfaceFactory(final int[][][] currentPoints, final BasicColor basicColor, final AnimationBehaviorFactory animationBehaviorFactory) {
		this.currentPoints = currentPoints;
		this.setBasicColorP(basicColor);
                this.animationBehaviorFactory = animationBehaviorFactory;
	}

        @Override
	public Animation getInstance(final int instanceId) throws Exception {
		return new VectorRotationAnimation(this.currentPoints, this.getBasicColorP(), this.animationBehaviorFactory.getOrCreateInstance());
	}

	protected void setBasicColorP(BasicColor basicColor) {
		this.basicColor = basicColor;
	}

	protected BasicColor getBasicColorP() {
		return basicColor;
	}

        @Override
   public void setInitialScale(final ScaleProperties scaleProperties) {
       
   }

}
