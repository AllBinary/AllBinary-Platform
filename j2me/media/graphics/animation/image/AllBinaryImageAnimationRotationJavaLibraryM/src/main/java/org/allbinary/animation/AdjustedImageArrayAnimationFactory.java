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

import javax.microedition.lcdui.Image;

public class AdjustedImageArrayAnimationFactory 
implements AnimationInterfaceFactoryInterface
{
	private final Image[] imageArray;

    private int dx;
    private int dy;

    public AdjustedImageArrayAnimationFactory(Image[] imageArray, int dx, int dy) 
    throws Exception
    {
    	this.imageArray = imageArray;
    	this.dx = dx;
    	this.dy = dy;
    }

    public Animation getInstance() throws Exception
    {
        return new AdjustedImageArrayAnimation(this.imageArray, dx, dy);
    }

    public void setInitialSize(final int width, final int height) {
        
    }
    
}
