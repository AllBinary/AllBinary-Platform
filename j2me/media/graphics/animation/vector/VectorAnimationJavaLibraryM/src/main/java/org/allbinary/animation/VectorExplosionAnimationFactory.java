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
        protected final AnimationBehaviorFactory animationBehaviorFactory;
        
        public VectorExplosionAnimationFactory(final BasicColor basicColor) {
            this(basicColor, AnimationBehaviorFactory.getInstance());
        }
	
	public VectorExplosionAnimationFactory(final BasicColor basicColor, final AnimationBehaviorFactory animationBehaviorFactory) {
            
            this.animationBehaviorFactory = animationBehaviorFactory;
            this.setBasicColorP(basicColor);
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
		
		return new VectorRotationAnimation(points, this.getBasicColorP(), this.animationBehaviorFactory.getOrCreateInstance());
	}

	private void setBasicColorP(BasicColor basicColor) {
		this.basicColor = basicColor;
	}

	private BasicColor getBasicColorP() {
		return basicColor;
	}
}