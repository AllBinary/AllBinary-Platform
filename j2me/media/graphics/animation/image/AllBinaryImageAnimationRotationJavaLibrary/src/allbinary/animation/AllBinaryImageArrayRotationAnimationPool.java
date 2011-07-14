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
package allbinary.animation;

import com.abcs.logic.util.cache.BasicArrayListResetablePool;
import com.abcs.logic.util.cache.CacheableInterface;
import com.abcs.logic.util.cache.CacheableInterfaceFactoryInterface;
import com.abcs.logic.util.cache.PoolInterface;

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
		
		((AllBinaryImageArrayRotationAnimationCacheable) cacheableInterface).init(
				allBinaryImageRotationAnimationInfo.getImageArray());
		//, 
				//allBinaryImageRotationAnimationInfo.getDx(), 
				//allBinaryImageRotationAnimationInfo.getDy());

		return cacheableInterface;
	}
}
