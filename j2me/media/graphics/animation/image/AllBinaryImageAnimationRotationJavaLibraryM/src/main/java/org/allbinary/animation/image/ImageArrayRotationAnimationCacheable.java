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

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.cache.CacheableInterface;

public class ImageArrayRotationAnimationCacheable 
   extends AdjustedImageArrayRotationAnimation 
		implements CacheableInterface {

	//private Object key;
	
	public ImageArrayRotationAnimationCacheable(final Object object, final AnimationBehavior animationBehavior) 
	throws Exception 
	{
		super(object, animationBehavior);
		//this.key = key;
	}
	
	/*
	public AllBinaryImageRotationAnimationCacheable(
			MEImage[] imageArray, int angleIncrement, int totalAngle, int dx, int dy) 
	throws Exception 
	{
		super(imageArray, angleIncrement, totalAngle, dx, dy);
	}
	*/
	
        @Override
	public Object getKey() {
		return NullUtil.getInstance().NULL_OBJECT;
	}

	public String toString() {
		return StringUtil.getInstance().EMPTY_STRING;
	}
}
