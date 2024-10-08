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

import javax.microedition.lcdui.Image;

import org.allbinary.image.GameFeatureImageCacheFactory;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.graphics.displayable.ScreenRelationalUtil;
import org.allbinary.media.image.ImageScaleUtil;

public class AllBinaryScreenRelationalImageAnimationInterfaceFactory
extends BaseImageAnimationFactory
{
    private Image lastImage;

    public AllBinaryScreenRelationalImageAnimationInterfaceFactory(final Image image)
        throws Exception
    {
        this(image, AnimationBehaviorFactory.getInstance());
    }
    
    public AllBinaryScreenRelationalImageAnimationInterfaceFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception
    {
    	//int width, int height
        super(image, 0, 0, animationBehaviorFactory);
        
    	//Image image = this.getImage();

    	if(lastImage != null)
    	{
            lastImage.getBitmap().recycle();
    	}

    	float scale = ScreenRelationalUtil.getInstance().getScale(image);

    	lastImage = 
        		//Image.createImage(displayInfoSingleton.getLastWidth(), displayInfoSingleton.getLastHeight());
        		ImageScaleUtil.getInstance().createImage(GameFeatureImageCacheFactory.getInstance(), 
    			this.getImage(), scale, scale, false);

    }
    
    @Override
    public Animation getInstance(final int instanceId) throws Exception
    {    	
        return new ImageAnimation(lastImage, this.animationBehaviorFactory.getOrCreateInstance());
    }    
}
