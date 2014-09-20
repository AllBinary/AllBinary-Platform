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

//TWB - Adjustments should be done in the resource creation and not at the animatoin level
public class AdjustedOneRowSpriteIndexedAnimationFactory 
        extends OneRowSpriteIndexedAnimationFactory
{

   public AdjustedOneRowSpriteIndexedAnimationFactory(Image image, int dx)
           throws Exception {
      super(image, dx, 0);

      this.dx += -(this.width >> 2);
   }
   
   public AdjustedOneRowSpriteIndexedAnimationFactory(Image image, int dx, int dy)
           throws Exception {
      super(image, dx, dy);

      this.dx += -(this.width >> 2);
      this.dy += -(this.height >> 2);
   }
}
