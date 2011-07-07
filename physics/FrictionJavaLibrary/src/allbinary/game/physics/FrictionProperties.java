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
package allbinary.game.physics;

import allbinary.game.physics.friction.FrictionData;
import allbinary.game.physics.velocity.VelocityProperties;
import allbinary.game.physics.velocity.VelocityUtil;

public class FrictionProperties
{
   public FrictionProperties()
   {
   }

   public void friction(VelocityProperties velocityProperties, int nominator)
   {
      VelocityUtil.reduce(velocityProperties, nominator, FrictionData.getFrictionDenominator());
   }
}