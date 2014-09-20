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
package allbinary.media.graphics.geography.map.racetrack;

import org.allbinary.game.physics.friction.FrictionData;

public class RaceTrackFrictionProperties
{
   private final int ROAD_FRICTION_NOMINATOR;
   private final int GROUND_FRICTION_NOMINATOR;

   public RaceTrackFrictionProperties(int roadFriction, int groundFriction)
   {
      ROAD_FRICTION_NOMINATOR = FrictionData.getFrictionDenominator() - roadFriction;
      GROUND_FRICTION_NOMINATOR = FrictionData.getFrictionDenominator() - groundFriction;
   }

   public int getRoadFrictionNominator()
   {
      return this.ROAD_FRICTION_NOMINATOR;
   }

   public int getGroundFrictionNominator()
   {
      return this.GROUND_FRICTION_NOMINATOR;
   }
}
