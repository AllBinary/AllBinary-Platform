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
	
	public VectorExplosionAnimationFactory(BasicColor basicColor) {
		this.setBasicColor(basicColor);
	}

	private final VectorExplosionGenerator vectorExplosionGenerator = VectorExplosionGenerator.getInstance();
	
	public Animation getInstance(Animation animationInterface)
	throws Exception 
	{
	    VectorAnimationInterface vectorRotationAnimationInterface = 
			(VectorAnimationInterface) animationInterface;
		int frame = vectorRotationAnimationInterface.getFrame();
		int[][] framePoints = vectorRotationAnimationInterface.getPoints(frame);
		int[][][] points = vectorExplosionGenerator.getInstance(
		        framePoints, 6, vectorExplosionGenerator.ROTATION);
		
		return new VectorRotationAnimation(points, this.getBasicColor());
	}

	private void setBasicColor(BasicColor basicColor) {
		this.basicColor = basicColor;
	}

	private BasicColor getBasicColor() {
		return basicColor;
	}
}