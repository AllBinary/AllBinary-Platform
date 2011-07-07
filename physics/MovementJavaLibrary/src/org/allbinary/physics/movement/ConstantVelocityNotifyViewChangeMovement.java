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
package org.allbinary.physics.movement;

import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.game.physics.velocity.BasicVelocityProperties;
import allbinary.layer.AllBinaryLayer;
import allbinary.logic.math.BasicDecimal;
import allbinary.logic.math.vector.AxisMathVectorUtil;
import allbinary.math.AngleFactory;

/**
 *
 * @author user
 */
public class ConstantVelocityNotifyViewChangeMovement extends Movement
{
   protected BasicVelocityProperties basicVelocityProperties;
   private BasicDecimal speedBasicDecimal;

   public ConstantVelocityNotifyViewChangeMovement()
   {
      this.speedBasicDecimal = ZERO_BIGDECIMAL;
      this.basicVelocityProperties = new BasicVelocityProperties();
   }
   
   public void init(short angle, BasicDecimal speedBasicDecimal)
   {
       this.speedBasicDecimal = speedBasicDecimal;
       basicVelocityProperties.setVelocity(this.speedBasicDecimal, AngleFactory.getInstance().getInstance(angle));
   }

   private final AxisMathVectorUtil axisMathVectorUtil = AxisMathVectorUtil.getInstance();
   
   public void moveOutsideRadius(AllBinaryLayer layer, int radius, short angle)
   {
       int scaleFactorValue = this.speedBasicDecimal.getScaledFactorValue();

      int xVector = (int) (axisMathVectorUtil.calculateX(radius, angle) / scaleFactorValue);
      int yVector = (int) (axisMathVectorUtil.calculateY(radius, angle) / scaleFactorValue);

      layer.move(xVector, yVector);
   }

   public void process(AllBinaryGameLayer layer)
   throws Exception
   {
       int x = this.basicVelocityProperties.getVelocityXBasicDecimal().getScaled();
       int y = this.basicVelocityProperties.getVelocityYBasicDecimal().getScaled();
       
       layer.move(x, y);
       
       if(x != 0 || y != 0)
       {
           layer.onViewPositionChangeEvent();
       }
       
   }
   
   public void stop()
   {
      this.basicVelocityProperties.zero();
   }
}
