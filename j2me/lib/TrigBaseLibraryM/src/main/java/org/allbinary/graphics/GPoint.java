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
package org.allbinary.graphics;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.math.PositionStrings;
import org.allbinary.string.CommonSeps;

public class GPoint 
{
   private final int x;
   private final int y;
   private final int z;

   public GPoint(GPoint point)
   {
      this.x = point.getX();
      this.y = point.getY();
      this.z = point.getZ();
   }

   protected GPoint(int x, int y)
   {
      this.x = x;
      this.y = y;
      this.z = 3;
   }
   
   protected GPoint(int x, int y, int z)
   {
      this.x = x;
      this.y = y;
      this.z = z;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }

   public int getZ()
   {
      return z;
   }

   public int getRawX()
   {
      return x;
   }
   
   public int getRawY()
   {
      return y;
   }

   public int getRawZ()
   {
      return z;
   }
   
   public String toString()
   {
       return toStringStatic(this.getX(), this.getY(), this.getZ());
   }
   
   private static final String POINT_LABEL = "Point: ";
   
   public static String toStringStatic(int x, int y, int z)
   {
      StringMaker stringBuffer = new StringMaker();

      PositionStrings positionStrings = 
          PositionStrings.getInstance();

      stringBuffer.append(POINT_LABEL);
      stringBuffer.append(positionStrings.X_LABEL);
      stringBuffer.append(x);
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(positionStrings.Y_LABEL);
      stringBuffer.append(y);
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(positionStrings.Z_LABEL);
      stringBuffer.append(z);

      return stringBuffer.toString();
   }
}
