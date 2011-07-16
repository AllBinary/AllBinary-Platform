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
package allbinary.game.layer;

import allbinary.game.physics.FrictionProperties;
import allbinary.game.physics.friction.FrictionData;

public class VehicleFrictionProperties extends FrictionProperties
{
   private final int TIRE_FRICTION_NOMINATOR;
   private final int BRAKE_FRICTION_NOMINATOR;
   private final int AIR_FRICTION_NOMINATOR;
   private final int CRASH_FRICTION_NOMINATOR;

   public VehicleFrictionProperties(
      int tireFriction, int brakeFriction, 
      int airFriction, int crashFriction)
   {
      TIRE_FRICTION_NOMINATOR = FrictionData.getFrictionDenominator() - tireFriction;
      BRAKE_FRICTION_NOMINATOR = FrictionData.getFrictionDenominator() - brakeFriction;
      AIR_FRICTION_NOMINATOR = FrictionData.getFrictionDenominator() - airFriction;
      CRASH_FRICTION_NOMINATOR = FrictionData.getFrictionDenominator() - crashFriction;
   }

   public int getTireFrictionNominator()
   {
      return this.TIRE_FRICTION_NOMINATOR;
   }

   public int getBrakeFrictionNominator()
   {
      return this.BRAKE_FRICTION_NOMINATOR;
   }

   public int getAirFrictionNominator()
   {
      return this.AIR_FRICTION_NOMINATOR;
   }

   public int getCrashFrictionNominator()
   {
      return this.CRASH_FRICTION_NOMINATOR;
   }
}
