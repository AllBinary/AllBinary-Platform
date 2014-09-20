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
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.vector.VectorInfo;

public class VectorToAdjustedImageArrayRotationAnimationFactory extends 
VectorToImageArrayRotationAnimationFactory
{
	private int dx;
	private int dy;

	public VectorToAdjustedImageArrayRotationAnimationFactory(
			VectorInfo vectorInfo, BasicColor basicColor, int dx, int dy) 
	    throws Exception {

		super(vectorInfo, basicColor);
		
		this.dx = dx;
		this.dy = dy;

		this.init();
	}
	
	public Animation getInstance() throws Exception {
		//return new AllBinarySpriteRotationAnimation(new MESprite(image,
		//width, height), dx, dy);
		
		return new AllBinaryAdjustedImageArrayRotationAnimation(
				this.getImageArray(), AngleInfo.getInstance(this.getAngleIncrement()), 
				AngleFactory.getInstance().TOTAL_ANGLE, dx, dy);
	}
	
}
