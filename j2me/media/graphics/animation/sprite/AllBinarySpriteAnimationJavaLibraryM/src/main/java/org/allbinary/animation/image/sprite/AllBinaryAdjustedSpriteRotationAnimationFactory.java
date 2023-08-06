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
package org.allbinary.animation.image.sprite;

import javax.microedition.lcdui.Image;

//TWB - Adjustments should be done in the resource creation and not at the animation level
public class AllBinaryAdjustedSpriteRotationAnimationFactory 
        extends AllBinarySpriteRotationAnimationFactory
{

   public AllBinaryAdjustedSpriteRotationAnimationFactory(final Image image, final int dx, final int dy)
           throws Exception {
      super(image, dx, dy);

      this.dx += -this.width / 5;
      this.dy += -this.height / 5;
   }

   public AllBinaryAdjustedSpriteRotationAnimationFactory(final Image image)
           throws Exception {
      this(image, 0, 0);
   }

   public AllBinaryAdjustedSpriteRotationAnimationFactory(final Image image, final String nullish)
           throws Exception {
      super(image, nullish);
   }
   
}
