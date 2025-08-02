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

import org.allbinary.game.layer.AllBinaryGameLayer;
import org.allbinary.game.physics.velocity.BasicVelocityProperties;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.logic.math.vector.AxisMathVectorUtil;
import org.allbinary.math.AngleFactory;

/**
 *
 * @author user
 */
public class ConstantVelocityNotifyViewChangeMovement extends Movement
{
   protected BasicVelocityProperties basicVelocityProperties;
   private BasicDecimal speedBasicDecimal;

   private final AxisMathVectorUtil axisMathVectorUtil = AxisMathVectorUtil.getInstance();

   public ConstantVelocityNotifyViewChangeMovement()
   {
      this.speedBasicDecimal = BasicDecimal.ZERO_BIGDECIMAL;
      this.basicVelocityProperties = new BasicVelocityProperties();
   }
 
   @Override
   public void init(final BasicDecimal speedBasicDecimal, final int angle, final int otherAngle)
   {
       this.speedBasicDecimal = speedBasicDecimal;

       AngleFactory angleFactory = AngleFactory.getInstance();

       basicVelocityProperties.setVelocity(speedBasicDecimal, 
               angleFactory.getInstance(angle), angleFactory.getInstance(otherAngle));
   }

   public void moveOutsideRadius(AllBinaryLayer layer, long radius, int angle, int otherAngle)
   {
       int scaleFactorValue = this.speedBasicDecimal.getScaledFactorValue();

      int xVector = (int) (axisMathVectorUtil.calculateX(radius, angle) / scaleFactorValue);
      int yVector = (int) (axisMathVectorUtil.calculateY(radius, angle) / scaleFactorValue);
      int zVector = (int) (axisMathVectorUtil.calculateZ(radius, otherAngle) / scaleFactorValue);

      layer.move(xVector, yVector, zVector);
   }

   @Override
   public void process(AllBinaryGameLayer layer)
   throws Exception
   {
       int x = this.basicVelocityProperties.getVelocityXBasicDecimalP().getScaled();
       int y = this.basicVelocityProperties.getVelocityYBasicDecimalP().getScaled();
       int z = this.basicVelocityProperties.getVelocityZBasicDecimalP().getScaled();
       
       layer.move(x, y, z);
       
       if(x != 0 || y != 0 || z != 0)
       {
           layer.onViewPositionChangeEvent();
       }
   }
   
   @Override
   public void stop()
   {
      this.basicVelocityProperties.zero();
   }
}
