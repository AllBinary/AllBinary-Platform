/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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

package org.allbinary.game.behavior.topview;

import org.allbinary.game.physics.FrictionProperties;
import org.allbinary.game.physics.friction.FrictionData;

public class CharacterFrictionProperties extends FrictionProperties
{
   private final int WATER_FRICTION_NOMINATOR;
   private final int AIR_FRICTION_NOMINATOR;
   private final int COLLISION_FRICTION_NOMINATOR;

   public CharacterFrictionProperties(
      int airFriction, int waterFriction, int collisionFriction)
   {
      AIR_FRICTION_NOMINATOR = FrictionData.getFrictionDenominator() - airFriction;
      COLLISION_FRICTION_NOMINATOR = FrictionData.getFrictionDenominator() - collisionFriction;
      WATER_FRICTION_NOMINATOR = FrictionData.getFrictionDenominator() - waterFriction;
   }

   public int getWATER_FRICTION_NOMINATOR() {
      return WATER_FRICTION_NOMINATOR;
   }

   public int getAIR_FRICTION_NOMINATOR() {
      return AIR_FRICTION_NOMINATOR;
   }

   public int getCOLLISION_FRICTION_NOMINATOR() {
      return COLLISION_FRICTION_NOMINATOR;
   }
}
