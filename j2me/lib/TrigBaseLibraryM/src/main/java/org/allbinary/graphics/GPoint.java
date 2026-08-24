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

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.math.PositionStrings;
import org.allbinary.string.CommonSeps;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class GPoint 
{
   @JsMethod
   public static GPoint getInstance(final GPoint point) {
      return new GPoint(point.getX(), point.getY(), point.getZ());
   }

   private final int x;
   private final int y;
   private final int z;

   //protected
   @JsConstructor
   public GPoint(final int x, final int y, final int z)
   {
      this.x = x;
      this.y = y;
      this.z = z;
   }
   
   @JsMethod
   public int getX()
   {
      return this.x;
   }
   
   @JsMethod
   public int getY()
   {
      return this.y;
   }

   @JsMethod
   public int getZ()
   {
      return this.z;
   }

   @JsMethod
   public int getRawX()
   {
      return this.x;
   }
   
   @JsMethod
   public int getRawY()
   {
      return this.y;
   }

   @JsMethod
   public int getRawZ()
   {
      return this.z;
   }
   
   @JsMethod
   public String toString()
   {
       return GPoint.toStringStatic(this.getX(), this.getY(), this.getZ());
   }
   
   private static final String POINT_LABEL = "Point: ";
   
   @JsMethod
   public static String toStringStatic(final int x, final int y, final int z)
   {
      final StringMaker stringBuffer = new StringMaker();

      final PositionStrings positionStrings = 
          PositionStrings.getInstance();

      stringBuffer.append(GPoint.POINT_LABEL);
      stringBuffer.append(positionStrings.X_LABEL);
      stringBuffer.appendint(x);
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(positionStrings.Y_LABEL);
      stringBuffer.appendint(y);
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(positionStrings.Z_LABEL);
      stringBuffer.appendint(z);

      return stringBuffer.toString();
   }
}
