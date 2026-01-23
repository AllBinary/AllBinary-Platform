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

public class NullRotationAnimationFactory 
implements AnimationInterfaceFactoryInterface,
ProceduralAnimationInterfaceFactoryInterface
{
   private static NullRotationAnimationFactory NULL_ROTATION_ANIMATION_FACTORY = new NullRotationAnimationFactory();
   public final RotationAnimation[] NULL_ROTATION_ANIMATION_ARRAY = new RotationAnimation[0];
   private final Animation NULL_ANIMATION = new NullRotationAnimation(AnimationBehavior.getInstance()) {
       
       @Override
       public void paint(final Graphics graphics, final int x, final int y) {
       }
   };
   
   private NullRotationAnimationFactory()
   {
   }

   public static NullRotationAnimationFactory getFactoryInstance()
   {
       return NULL_ROTATION_ANIMATION_FACTORY;
   }
   
   @Override
   public Animation getInstance(final int instanceId)
   {
       return NULL_ANIMATION;
   }
   
   @Override
   public Animation getInstance(Animation animationInterface) throws Exception
   {
       return NULL_ANIMATION;
   }

   @Override
   public void setInitialScale(final ScaleProperties scaleProperties) {
       
   }
   
}