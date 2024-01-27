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

public class VectorExplosionAnimationFactory 
    implements ProceduralAnimationInterfaceFactoryInterface{

	private BasicColor basicColor;
        protected final AnimationBehavior animationBehavior;
        
        public VectorExplosionAnimationFactory(final BasicColor basicColor) {
            this(basicColor, AnimationBehavior.getInstance());
        }
	
	public VectorExplosionAnimationFactory(final BasicColor basicColor, final AnimationBehavior animationBehavior) {
            
            this.animationBehavior = animationBehavior;
            this.setBasicColor(basicColor);
	}

	private final VectorExplosionGenerator vectorExplosionGenerator = VectorExplosionGenerator.getInstance();
	
	public Animation getInstance(final Animation animationInterface)
	throws Exception 
	{
	    final VectorAnimationInterface vectorRotationAnimationInterface = 
			(VectorAnimationInterface) animationInterface;

		final int frame = vectorRotationAnimationInterface.getFrame();
		final int[][] framePoints = vectorRotationAnimationInterface.getPoints(frame);
		final int[][][] points = vectorExplosionGenerator.getInstance(
		        framePoints, 6, vectorExplosionGenerator.ROTATION);
		
		return new VectorRotationAnimation(points, this.getBasicColor(), this.animationBehavior);
	}

	private void setBasicColor(BasicColor basicColor) {
		this.basicColor = basicColor;
	}

	private BasicColor getBasicColor() {
		return basicColor;
	}
}