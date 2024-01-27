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
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.graphics.displayable.ScreenRelationalUtil;
import org.allbinary.media.image.ImageScaleUtil;

public class AllBinaryScreenRelationalImageAnimationInterfaceFactory
extends BaseImageAnimationFactory
{
    private Image lastImage;

    public AllBinaryScreenRelationalImageAnimationInterfaceFactory(final Image image)
        throws Exception
    {
        this(image, AnimationBehavior.getInstance());
    }
    
    public AllBinaryScreenRelationalImageAnimationInterfaceFactory(final Image image, final AnimationBehavior animationBehavior)
        throws Exception
    {
    	//int width, int height
        super(image, 0, 0, animationBehavior);
        
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
    
    public Animation getInstance() throws Exception
    {    	
        return new ImageAnimation(lastImage, this.animationBehavior);
    }    
}
