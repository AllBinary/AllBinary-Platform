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
import allbinary.game.physics.acceleration.GravityUtil;
import allbinary.game.physics.velocity.VelocityProperties;
import allbinary.logic.math.BasicDecimal;

/**
 *
 * @author user
 */
public class BasicGravityConstantVelocityMovement 
   extends LimitedConstantVelocityMovement
{
   public BasicGravityConstantVelocityMovement(BasicDecimal speedBasicDecimal)
   {
       super(speedBasicDecimal, new VelocityProperties(
               (int) speedBasicDecimal.getUnscaled(), 
               (int) speedBasicDecimal.getUnscaled()
       ));
   }
   
   private final GravityUtil gravityUtil = GravityUtil.getInstance();
   
   public void process(AllBinaryGameLayer layer)
   throws Exception
   {
      super.process(layer);
      gravityUtil.process(this.getVelocityProperties());
   }
}
