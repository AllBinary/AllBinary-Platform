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
package org.allbinary.animation.vector;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.media.ScaleProperties;

/**
 *
 * @author user
 */
public class LineAnimationFactory
implements AnimationInterfaceFactoryInterface
{
    private BasicColor basicColor;

    public LineAnimationFactory(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    public Animation getInstance(final int instanceId) throws Exception
    {
        return new LineAnimation(this.basicColor);
    }
    
   public void setInitialScale(final ScaleProperties scaleProperties) {
       
   }
    
}
