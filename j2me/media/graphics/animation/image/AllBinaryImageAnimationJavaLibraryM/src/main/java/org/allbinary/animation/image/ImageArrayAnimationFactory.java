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

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;

public class ImageArrayAnimationFactory 
implements AnimationInterfaceFactoryInterface
{
    private final Image[] imageArray;

    public ImageArrayAnimationFactory(final Image[] imageArray) 
    throws Exception
    {
    	this.imageArray = imageArray;
    }

    public Animation getInstance() throws Exception
    {
        return new ImageArrayAnimation(this.imageArray, AnimationBehavior.getInstance());
    }

    public void setInitialSize(final int width, final int height) {
        
    }
    
}
