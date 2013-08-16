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
package allbinary.game.terrain;

import allbinary.math.Angle;

public class BasicTerrainInfo {

   private Angle angle;

   public BasicTerrainInfo(Angle angle)
   {
      this.angle = angle;
   }
   
   public Angle getAngle() {
      return angle;
   }

   public void setAngle(Angle angle) {
      this.angle = angle;
   }
}
