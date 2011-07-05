/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: Sep 29, 2007, 7:09:17 AM
 *
 *
 * Modified By         When       ?
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
