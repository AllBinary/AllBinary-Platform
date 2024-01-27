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

public class VectorRotationAnimationInterfaceFactory implements
		AnimationInterfaceFactoryInterface {

	private int currentPoints[][][];
	private BasicColor basicColor;
        protected final AnimationBehavior animationBehavior;

        public VectorRotationAnimationInterfaceFactory(final int[][][] currentPoints, final BasicColor basicColor) {
            this(currentPoints, basicColor, AnimationBehavior.getInstance());
        }
        
	public VectorRotationAnimationInterfaceFactory(final int[][][] currentPoints, final BasicColor basicColor, final AnimationBehavior animationBehavior) {
		this.currentPoints = currentPoints;
		this.setBasicColor(basicColor);
                this.animationBehavior = animationBehavior;
	}

	public Animation getInstance() throws Exception {
		return new VectorRotationAnimation(this.currentPoints, this.getBasicColor(), this.animationBehavior);
	}

	protected void setBasicColor(BasicColor basicColor) {
		this.basicColor = basicColor;
	}

	protected BasicColor getBasicColor() {
		return basicColor;
	}

   public void setInitialSize(final int width, final int height) {
       
   }

}
