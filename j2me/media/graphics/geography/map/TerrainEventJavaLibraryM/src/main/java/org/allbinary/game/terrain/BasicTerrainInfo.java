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
package org.allbinary.game.terrain;

import org.allbinary.math.Angle;
import org.allbinary.math.AngleFactory;

public class BasicTerrainInfo {

    public static final BasicTerrainInfo NULL_BASIC_TERRAIN_INFO = new BasicTerrainInfo(AngleFactory.getInstance().NOT_ANGLE);
    
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
