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

import org.allbinary.logic.util.cache.BasicArrayListResetablePool;
import org.allbinary.logic.util.cache.CacheableInterface;
import org.allbinary.logic.util.cache.CacheableInterfaceFactoryInterface;
import org.allbinary.logic.util.cache.PoolInterface;

public class AllBinaryImageArrayRotationAnimationPool 
   extends BasicArrayListResetablePool
{
	private static PoolInterface IMAGE_ROTATION_ANIMATION_POOL = 
		new AllBinaryImageArrayRotationAnimationPool(
				new AllBinaryImageArrayRotationAnimationCacheableInterfaceFactory());
	
	public AllBinaryImageArrayRotationAnimationPool(CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface)
	{
		super(cacheableInterfaceFactoryInterface);
	}
	
	public static PoolInterface getInstance()
	{
		return AllBinaryImageArrayRotationAnimationPool.IMAGE_ROTATION_ANIMATION_POOL;
	}
	
	public CacheableInterface remove(Object key) throws Exception
	{
		//CacheableInterface cacheableInterface = 
		//	this.cacheableInterfaceFactoryInterface.getInstance(key);
		CacheableInterface cacheableInterface = super.remove(key);

		AllBinaryImageArrayRotationAnimationInfo allBinaryImageRotationAnimationInfo = 
			(AllBinaryImageArrayRotationAnimationInfo) key;
		
		((AllBinaryImageArrayRotationAnimationCacheable) cacheableInterface).setImageArray(
				allBinaryImageRotationAnimationInfo.getImageArray());
		//, 
				//allBinaryImageRotationAnimationInfo.getDx(), 
				//allBinaryImageRotationAnimationInfo.getDy());

		return cacheableInterface;
	}
}
