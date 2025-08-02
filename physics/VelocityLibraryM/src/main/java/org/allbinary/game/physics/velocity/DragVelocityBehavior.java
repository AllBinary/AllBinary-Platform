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
  
    @Override
   public void reduce(final BasicVelocityProperties velocityProperties, final int nominator, final int denominator)
   {
      //logUtil.put("VelocityX: " + this.getVelocityXBasicDecimalP().getUnscaled(), this, "friction");
      //logUtil.put("VelocityY: " + this.getVelocityYBasicDecimalP().getUnscaled(), this, "friction");
      if(velocityProperties.getVelocityXBasicDecimalP().getUnscaled() != 0L)
      {
         final BasicDecimal basicDecimal = velocityProperties.getVelocityXBasicDecimalP();
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
         //this.setVelocityXBasicDecimal(basicDecimal);
         //logUtil.put("After Friction VelocityX: " + this.getVelocityXBasicDecimalP().getUnscaled(), this, "friction");
      }

      if (velocityProperties.getVelocityYBasicDecimalP().getUnscaled() != 0L)
      {
         final BasicDecimal basicDecimal = velocityProperties.getVelocityYBasicDecimalP();
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
         //this.setVelocityYBasicDecimal(basicDecimal);
         //logUtil.put("After Friction VelocityY: " + this.getVelocityYBasicDecimalP().getUnscaled(), this, "friction");
      }
   }   

   @Override
   public void reduceX(final BasicVelocityProperties velocityProperties, final int nominator, final int denominator)
   {
      final BasicDecimal basicDecimal = velocityProperties.getVelocityXBasicDecimalP();
      if(basicDecimal.getUnscaled() != 0L)
      {
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
      }
   }
   
   @Override
   public void reduceY(final BasicVelocityProperties velocityProperties, final int nominator, final int denominator)
   {
      if (velocityProperties.getVelocityYBasicDecimalP().getUnscaled() != 0L)
      {
         final BasicDecimal basicDecimal = velocityProperties.getVelocityYBasicDecimalP();
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
      }
   }
}
