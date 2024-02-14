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
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.image.BaseImageAnimationFactory;
import org.allbinary.game.layer.SpriteFactory;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.media.image.ImageScaleUtil;

public class OneRowJ2SESpriteIndexedAnimationFactory 
extends BaseImageAnimationFactory 
{
    private final ImageCache imageCache = ImageCacheFactory.getInstance();
    private final ImageScaleUtil imageScaleUtil = ImageScaleUtil.getInstance();
    
   protected int dx;
   protected int dy;
   
   public int scaleWidth;
   public int scaleHeight;
      
   public OneRowJ2SESpriteIndexedAnimationFactory(final Image image, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
           throws Exception {
      this(image, animationBehaviorFactory);

      this.dx = dx;
      this.dy = dy;
   }

   public OneRowJ2SESpriteIndexedAnimationFactory(final int width, final int height, final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
           throws Exception {

      //90 degrees per row with 4 rows
      //Future imp may include Control fidelity for non square frames
       super(image, width, height, animationBehaviorFactory);
   }
   
   public OneRowJ2SESpriteIndexedAnimationFactory(final Image image, final AnimationBehaviorFactory animationBehaviorFactory)
      throws Exception 
   {

      //90 degrees per row with 4 rows
      //Future imp may include Control fidelity for non square frames
      super(image, image.getHeight(), image.getHeight(), animationBehaviorFactory);
   }

   public Animation getInstance() 
      throws Exception 
   {
//       final CommonStrings commonStrings = CommonStrings.getInstance();
//       final StringMaker stringMaker = new StringMaker();
//       LogUtil.put(LogFactory.getInstance(stringMaker.append("scaleWidth: ").append(scaleWidth).append(" scaleHeight: ").append(scaleHeight).toString(), this, commonStrings.PROCESS));
       Sprite sprite;
       final Image image = this.getImage();
       if(scaleWidth != 0 && scaleHeight != 0) {
           final float scaleX = ((float) scaleWidth) / ((float) this.width);
           final float scaleY = ((float) scaleHeight) / ((float) this.height);
//           stringMaker.delete(0, stringMaker.length());
//           LogUtil.put(LogFactory.getInstance(stringMaker.append("width: ").append(width).append(" height: ").append(height).toString(), this, commonStrings.PROCESS));
//           stringMaker.delete(0, stringMaker.length());
//           LogUtil.put(LogFactory.getInstance(stringMaker.append("0scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));
           Image scaledImage;
           if ((scaleX == 1 && scaleY == 1) || (scaleX == 0 || scaleY == 0)) {
               scaledImage = image;
               sprite = SpriteFactory.getInstance().create(image, this.width, this.height);
           } else {
//               stringMaker.delete(0, stringMaker.length());
//               LogUtil.put(LogFactory.getInstance(stringMaker.append("scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));
               scaledImage = imageScaleUtil.createImage(imageCache, image, scaleX, 1, scaleY, 1, true);
//               stringMaker.delete(0, stringMaker.length());
//               LogUtil.put(LogFactory.getInstance(stringMaker.append("scaledImage.getHeight(): ").append(scaledImage.getHeight()).append(" this.height * scaleY: ").append(this.height * scaleY).toString(), this, commonStrings.PROCESS));
               sprite = SpriteFactory.getInstance().create(scaledImage, (int) (this.width * scaleX), (int) (this.height * scaleY));
           }
           
       } else {
           sprite = SpriteFactory.getInstance().create(image, this.width, this.height);
       }

      if (dx != 0 || dy != 0) 
      {
         return new AdjustedSpriteIndexedAnimation(sprite, dx, dy, this.animationBehaviorFactory.getOrCreateInstance());
      } else {
         return new SpriteIndexedAnimation(sprite, this.animationBehaviorFactory.getOrCreateInstance());
      }
   }
   
   public void setInitialSize(final int width, final int height) {
       this.scaleWidth = width;
       this.scaleHeight = height;
   }
   
}
