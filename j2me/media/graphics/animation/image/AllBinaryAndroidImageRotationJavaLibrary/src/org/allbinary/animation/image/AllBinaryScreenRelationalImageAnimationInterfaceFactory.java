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

import allbinary.animation.Animation;
import allbinary.animation.image.AllBinaryImageAnimation;
import allbinary.animation.image.BaseImageAnimationFactory;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.image.ImageScaleUtil;

public class AllBinaryScreenRelationalImageAnimationInterfaceFactory
extends BaseImageAnimationFactory
{
	private Image lastImage;
	
    public AllBinaryScreenRelationalImageAnimationInterfaceFactory(Image image)
        throws Exception
    {
    	//int width, int height
        super(image, 0, 0);
    }
    
    public Animation getInstance() throws Exception
    {
    	Image image = this.getImage();
    	
    	if(lastImage != null)
    	{
    		lastImage.getBitmap().recycle();
    	}

    	DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
    	
    	float width = displayInfoSingleton.getLastWidth();
    	float height = displayInfoSingleton.getLastHeight();
    	
    	float scaleX = width/image.getWidth();
    	float scaleY = height/image.getHeight();
    	float scale = scaleX; 
    	
    	if(scaleX < scaleY)
    	{
    		scale = scaleY; 
    	}
    	
    	lastImage = 
        		//Image.createImage(displayInfoSingleton.getLastWidth(), displayInfoSingleton.getLastHeight());
        		ImageScaleUtil.getInstance().createImage(GameFeatureImageCacheFactory.getInstance(), 
    			this.getImage(), scale, scale, false);
    	
        return new AllBinaryImageAnimation(lastImage);
    }    
}
