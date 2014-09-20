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
import javax.microedition.media.Player;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.caption.CaptionIndexedAnimation;
import org.allbinary.animation.image.AllBinaryImageAnimation;
import org.allbinary.media.audio.Sound;

public class ImageCaptionIndexedAnimationFactory 
implements AnimationInterfaceFactoryInterface
{
   private Image captionImage;
   private Image spriteMovieImage;
   
   private int frameWidth;
   private int frameHeight;
   
   private int captionDx;
   private int captionDy;
   
   private int dx;
   private int dy;
         
   private int time;
   
   private Sound soundInterface;
   
   public ImageCaptionIndexedAnimationFactory(
      Image captionImage, Image spriteMovieImage, 
      Sound soundInterface,
      int frameWidth, int frameHeight, 
      int captionDx, int captionDy, int dx, int dy, int time)
   {
      this.captionImage = captionImage;
      this.spriteMovieImage = spriteMovieImage;

      this.frameWidth = frameWidth;
      this.frameHeight = frameHeight;
      
      this.captionDx = captionDx;
      this.captionDy = captionDy;
      
      this.dx = dx;
      this.dy = dy;
      
      this.time = time;
      
      this.soundInterface = soundInterface;
   }

   public Animation getInstance() throws Exception
   {
      Animation animationInterface = new AllBinaryImageAnimation(this.captionImage);

      Sprite sprite = new Sprite(this.spriteMovieImage, this.frameWidth, this.frameHeight);

      IndexedAnimation movieIndexedAnimationInterface = new SpriteIndexedAnimation(sprite);
      
      Player player = this.soundInterface.getPlayer();
      
      if(player == null)
      {
         throw new Exception("Sound Was not Initialized");
      }
      
      return  new CaptionIndexedAnimation(
              animationInterface, movieIndexedAnimationInterface, player, this.captionDx, this.captionDy, dx, dy, time);
   }
}