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

import org.allbinary.animation.special.SpecialAnimation;
import org.allbinary.media.ScaleProperties;

public class NullSpecialAnimationFactory implements 
    AnimationInterfaceFactoryInterface, ProceduralAnimationInterfaceFactoryInterface
{
   private static NullSpecialAnimationFactory NULL_SPECIAL_ANIMATION_FACTORY = new NullSpecialAnimationFactory();
   
   private NullSpecialAnimationFactory()
   {
   }

   public static NullSpecialAnimationFactory getFactoryInstance()
   {
       return NULL_SPECIAL_ANIMATION_FACTORY;
   }

   @Override
   public Animation getInstance(final int instanceId) throws Exception
   {
       return SpecialAnimation.getInstance();
   }
   
   @Override
   public Animation getInstance(final Animation animationInterface)
       throws Exception
   {
       return SpecialAnimation.getInstance();
   }
   
   @Override
   public void setInitialScale(final ScaleProperties scaleProperties) {
       
   }
   
}