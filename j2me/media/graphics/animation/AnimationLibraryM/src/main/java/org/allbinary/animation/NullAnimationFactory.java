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

import org.allbinary.media.ScaleProperties;

public class NullAnimationFactory implements 
    AnimationInterfaceFactoryInterface, ProceduralAnimationInterfaceFactoryInterface
{
   private static NullAnimationFactory NULL_ANIMATION_FACTORY = new NullAnimationFactory();
   
   private final Animation NULL_ANIMATION = new Animation();
   
   //public final Animation[] EMPTY_ARRAY = new Animation[0];
   
   private NullAnimationFactory()
   {
   }

   public static NullAnimationFactory getFactoryInstance()
   {
       return NULL_ANIMATION_FACTORY;
   }
   
   public Animation getInstance()
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