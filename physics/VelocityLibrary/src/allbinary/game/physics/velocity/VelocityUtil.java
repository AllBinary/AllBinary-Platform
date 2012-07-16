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
package allbinary.game.physics.velocity;

import allbinary.logic.math.BasicDecimal;

/**
 *
 * @author user
 */
public class VelocityUtil {

   public  static void reduce(BasicVelocityProperties velocityProperties, int nominator, int denominator)
   {
      //LogUtil.put(LogFactory.getInstance("VelocityX: " + this.getVelocityXBasicDecimal().getUnscaled(), this, "friction"));
      //LogUtil.put(LogFactory.getInstance("VelocityY: " + this.getVelocityYBasicDecimal().getUnscaled(), this, "friction"));
      if(velocityProperties.getVelocityXBasicDecimal().getUnscaled() != 0)
      {
         BasicDecimal basicDecimal = velocityProperties.getVelocityXBasicDecimal();
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
         //this.setVelocityXBasicDecimal(basicDecimal);
         //LogUtil.put(LogFactory.getInstance("After Friction VelocityX: " + this.getVelocityXBasicDecimal().getUnscaled(), this, "friction"));
      }

      if (velocityProperties.getVelocityYBasicDecimal().getUnscaled() != 0)
      {
         BasicDecimal basicDecimal = velocityProperties.getVelocityYBasicDecimal();
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
         //this.setVelocityYBasicDecimal(basicDecimal);
         //LogUtil.put(LogFactory.getInstance("After Friction VelocityY: " + this.getVelocityYBasicDecimal().getUnscaled(), this, "friction"));
      }
   }   

   public static void reduceX(BasicVelocityProperties velocityProperties, int nominator, int denominator)
   {
      BasicDecimal basicDecimal = velocityProperties.getVelocityXBasicDecimal();
      if(basicDecimal.getUnscaled() != 0)
      {
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
      }
   }
   
   public  static void reduceY(BasicVelocityProperties velocityProperties, int nominator, int denominator)
   {
      if (velocityProperties.getVelocityYBasicDecimal().getUnscaled() != 0)
      {
         BasicDecimal basicDecimal = velocityProperties.getVelocityYBasicDecimal();
         basicDecimal.multiply(nominator);
         basicDecimal.divide(denominator);
      }
   }
}
