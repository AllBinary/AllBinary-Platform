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
   private static NullIndexedAnimationFactory NULL_INDEXED_ANIMATION_FACTORY = new NullIndexedAnimationFactory();
   private final Animation NULL_ANIMATION = new NullIndexedAnimation(AnimationBehavior.getInstance()) {
       
       @Override
       public void paint(final Graphics graphics, final int x, final int y) {
       }
   };
   
   private NullIndexedAnimationFactory()
   {
   }

   public static NullIndexedAnimationFactory getFactoryInstance()
   {
       return NULL_INDEXED_ANIMATION_FACTORY;
   }

   @Override
   public Animation getInstance(final int instanceId) throws Exception
   {
	   return NULL_ANIMATION;
   }
   
   @Override
   public Animation getInstance(final Animation animationInterface)
       throws Exception
   {
       return NULL_ANIMATION;
   }
   
   @Override
   public void setInitialScale(final ScaleProperties scaleProperties) {
       
   }

}