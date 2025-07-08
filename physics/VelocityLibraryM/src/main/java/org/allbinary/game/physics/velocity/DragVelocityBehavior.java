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
package org.allbinary.game.physics.velocity;

import org.allbinary.logic.math.BasicDecimal;

/**
 *
 * @author user
 */
public class DragVelocityBehavior extends VelocityBehaviorBase {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    public static final DragVelocityBehavior instance = new DragVelocityBehavior();
    
   public void reduce(final BasicVelocityProperties velocityProperties, final int nominator, final int denominator)
   {
      //logUtil.put("VelocityX: " + this.getVelocityXBasicDecimal().getUnscaled(), this, "friction");
      //logUtil.put("VelocityY: " + this.getVelocityYBasicDecimal().getUnscaled(), this, "friction");
      if(velocityProperties.getVelocityXBasicDecimal().getUnscaled() != 0)
      {
         final BasicDecimal basicDecimal = velocityProperties.getVelocityXBasicDecimal();
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
         //this.setVelocityXBasicDecimal(basicDecimal);
         //logUtil.put("After Friction VelocityX: " + this.getVelocityXBasicDecimal().getUnscaled(), this, "friction");
      }

      if (velocityProperties.getVelocityYBasicDecimal().getUnscaled() != 0)
      {
         final BasicDecimal basicDecimal = velocityProperties.getVelocityYBasicDecimal();
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
         //this.setVelocityYBasicDecimal(basicDecimal);
         //logUtil.put("After Friction VelocityY: " + this.getVelocityYBasicDecimal().getUnscaled(), this, "friction");
      }
   }   

   public void reduceX(final BasicVelocityProperties velocityProperties, final int nominator, final int denominator)
   {
      final BasicDecimal basicDecimal = velocityProperties.getVelocityXBasicDecimal();
      if(basicDecimal.getUnscaled() != 0)
      {
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
      }
   }
   
   public void reduceY(final BasicVelocityProperties velocityProperties, final int nominator, final int denominator)
   {
      if (velocityProperties.getVelocityYBasicDecimal().getUnscaled() != 0)
      {
         final BasicDecimal basicDecimal = velocityProperties.getVelocityYBasicDecimal();
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
      }
   }
}
