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

import javax.microedition.lcdui.Graphics;
import org.allbinary.media.ScaleProperties;

public class NullIndexedAnimationFactory implements 
    AnimationInterfaceFactoryInterface, ProceduralAnimationInterfaceFactoryInterface
{
   private static NullIndexedAnimationFactory NULL_ANIMATION_FACTORY = new NullIndexedAnimationFactory();
   private final Animation NULL_ANIMATION = new NullIndexedAnimation(AnimationBehavior.getInstance()) {
       public void paint(Graphics graphics, int x, int y) {
       }
   };
   
   private NullIndexedAnimationFactory()
   {
   }

   public static NullIndexedAnimationFactory getFactoryInstance()
   {
       return NULL_ANIMATION_FACTORY;
   }

   public Animation getInstance() throws Exception
   {
	   return NULL_ANIMATION;
   }
   
   public Animation getInstance(Animation animationInterface)
       throws Exception
   {
       return NULL_ANIMATION;
   }
   
   public void setInitialScale(final ScaleProperties scaleProperties) {
       
   }

}