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

import org.allbinary.graphics.GPoint;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

public class Rectangle 
{
   private GPoint point;
   
   private int width;
   private int height;
   
   public Rectangle(GPoint point, int width, int height)
           //throws Exception
   {
      //if(point == null)
         //throw new Exception("Invalid Point");
      
      this.point = point;
      this.width = width;
      this.height = height;
   }

   public GPoint getPoint()
   {
      return point;
   }

   public void setPoint(final GPoint point)
   {
      this.point = point;
   }
   
   public int getMaxX()
   {
      return this.point.getX() + this.width;
   }

   public int getMaxY()
   {
      return this.point.getY() + this.height;
   }
   
   public int getWidth()
   {
      return width;
   }

   public void setWidth(int width)
   {
      this.width = width;
   }

   public int getHeight()
   {
      return height;
   }

   public void setHeight(int height)
   {
      this.height = height;
   }
   
   public String toString()
   {
       final StringMaker stringBuffer = new StringMaker();
       stringBuffer.append(this.getPoint().toString());
       stringBuffer.append(CommonSeps.getInstance().SPACE);
       stringBuffer.append(SpacialStrings.getInstance().WIDTH_LABEL);
       stringBuffer.append(this.getWidth());
       stringBuffer.append(CommonSeps.getInstance().SPACE);
       stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
       stringBuffer.append(this.getHeight());
       stringBuffer.append(" MaxX: ");
       stringBuffer.append(this.getMaxX());
       stringBuffer.append(" MaxY: ");
       stringBuffer.append(this.getMaxY());
       return stringBuffer.toString();
   }
}
