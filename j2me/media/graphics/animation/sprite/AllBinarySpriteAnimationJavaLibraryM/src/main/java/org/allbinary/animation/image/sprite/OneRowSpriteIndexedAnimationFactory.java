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
import javax.microedition.lcdui.game.Sprite;

import org.allbinary.animation.Animation;
import org.allbinary.animation.image.BaseImageAnimationFactory;
import org.allbinary.game.layer.SpriteFactory;

public class OneRowSpriteIndexedAnimationFactory 
extends BaseImageAnimationFactory 
{
   protected int dx;
   protected int dy;

   public OneRowSpriteIndexedAnimationFactory(final Image image, final int dx, final int dy)
           throws Exception {
      this(image);

      this.dx = dx;
      this.dy = dy;
   }

   public OneRowSpriteIndexedAnimationFactory(final int width, final int height, final Image image)
           throws Exception {

      //90 degrees per row with 4 rows
      //Future imp may include Control fidelity for non square frames
       super(image, width, height);
   }
   
   public OneRowSpriteIndexedAnimationFactory(final Image image)
      throws Exception 
   {

      //90 degrees per row with 4 rows
      //Future imp may include Control fidelity for non square frames
      super(image, image.getHeight(), image.getHeight());
   }

   public Animation getInstance() 
      throws Exception 
   {
       final Sprite sprite = SpriteFactory.getInstance().create(this.getImage(), this.width, this.height);

      if (dx != 0 || dy != 0) 
      {
         return new AdjustedSpriteIndexedAnimation(sprite, dx, dy);
      } else {
         return new SpriteIndexedAnimation(sprite);
      }
   }
}
