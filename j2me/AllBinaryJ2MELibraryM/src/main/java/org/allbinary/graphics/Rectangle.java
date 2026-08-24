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
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class Rectangle 
{
   private GPoint point;
   
   private int width;
   private int height;
   
   @JsConstructor
   public Rectangle(GPoint point, int width, int height)
           //throws Exception
   {
      //if(point == null)
         //throw new Exception("Invalid Point");
      
      this.point = point;
      this.width = width;
      this.height = height;
   }

   @JsMethod
   public GPoint getPoint()
   {
      return this.point;
   }

   @JsMethod
   public void setPoint(final GPoint point)
   {
      this.point = point;
   }
   
   @JsMethod
   public int getMaxX()
   {
      return this.point.getX() + this.width;
   }

   @JsMethod
   public int getMaxY()
   {
      return this.point.getY() + this.height;
   }
   
   @JsMethod
   public int getWidth()
   {
      return this.width;
   }

   @JsMethod
   public void setWidth(int width)
   {
      this.width = width;
   }

   @JsMethod
   public int getHeight()
   {
      return this.height;
   }

   @JsMethod
   public void setHeight(int height)
   {
      this.height = height;
   }
   
   @JsMethod
   public String toString()
   {
       final StringMaker stringBuffer = new StringMaker();
       stringBuffer.append(this.getPoint().toString());
       stringBuffer.append(CommonSeps.getInstance().SPACE);
       stringBuffer.append(CommonLabels.getInstance().WIDTH_LABEL);
       stringBuffer.appendint(this.getWidth());
       stringBuffer.append(CommonSeps.getInstance().SPACE);
       stringBuffer.append(CommonLabels.getInstance().HEIGHT_LABEL);
       stringBuffer.appendint(this.getHeight());
       stringBuffer.append(" MaxX: ");
       stringBuffer.appendint(this.getMaxX());
       stringBuffer.append(" MaxY: ");
       stringBuffer.appendint(this.getMaxY());
       return stringBuffer.toString();
   }
}
