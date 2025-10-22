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
package org.allbinary.game.layer;

import org.allbinary.game.physics.velocity.VelocityProperties;

public class VehicleProperties
{
    
   private final VelocityProperties velocityProperties;
   private VehicleFrictionProperties vehicleFrictionProperties;
   //private final int TAIL_SPIN_SPEED = 10 * 1000 * 1000;

   public VehicleProperties(VelocityProperties velocityProperties, 
           VehicleFrictionProperties vehicleFrictionProperties)
   {
      this.velocityProperties = velocityProperties;
      this.vehicleFrictionProperties = vehicleFrictionProperties;
   }

   public VehicleFrictionProperties getVehicleFrictionProperties()
   {
      return vehicleFrictionProperties;
   }

   public void setVehicleFrictionProperties(VehicleFrictionProperties vehicleFrictionProperties)
   {
      this.vehicleFrictionProperties = vehicleFrictionProperties;
   }

   public VelocityProperties getVelocityProperties()
   {
      return velocityProperties;
   }
}