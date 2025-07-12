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
package org.allbinary.collision;

import java.awt.*;

import org.allbinary.graphics.GPoint;

public class RectangleCollisionUtil
{
   
   private RectangleCollisionUtil()
   {
   }
   
   public static boolean isCollision(Rectangle rectangle,
      GPoint point)
   {
      if (point.getX() > ((int) rectangle.x + rectangle.getWidth()) ||
         point.getY() > ((int) rectangle.y + rectangle.getHeight()) ||
         point.getX() < rectangle.x ||
         point.getY() < rectangle.y)
      {
         return false;
      }
      else
      {
         return true;
      }
   }
   
public static boolean isCollision(
      Rectangle rectangle1,
      Rectangle rectangle2)
   {
      return isCollision(
         rectangle1.x,
         rectangle1.y,
         rectangle1.x + rectangle1.width,
         rectangle1.y + rectangle1.height,
         rectangle2.x,
         rectangle2.y,
         rectangle2.x + rectangle2.width,
         rectangle2.y + rectangle2.height
         );
   }
   
   private static boolean isCollision(int rectX1, int rectY1, int rectX2, int rectY2,
      int rect2X1, int rect2Y1, int rect2X2, int rect2Y2)
   {
      if (rect2X1 >= rectX2 ||
         rect2Y1 >= rectY2 ||
         rect2X2 <= rectX1 ||
         rect2Y2 <= rectY1)
      {
         return false;
      }
      else
      {
         return true;
      }
   }   
}
