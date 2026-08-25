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
package org.allbinary.vector;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

@JsType
public class VectorInfo 
{
   private int width;
   private int height;
   private int[][] points;
   private int totalFrames;
   
   @JsConstructor
   public VectorInfo(int width, int height, int[][] points, int totalFrames)
   {
       this.width = width;
       this.height = height;
       this.points = points;
       this.totalFrames = totalFrames;
   }

   @JsMethod
   public int getWidth()
   {
      return this.width;
   }

   @JsMethod
   private void setWidth(int width)
   {
      this.width = width;
   }

   @JsMethod
   public int getHeight()
   {
      return this.height;
   }

   @JsMethod
   private void setHeight(int height)
   {
      this.height = height;
   }

   @JsMethod
   public int[][] getPoints()
   {
      return this.points;
   }

   @JsMethod
   private void setPoints(int[][] points)
   {
      this.points = points;
   }

   @JsMethod
   public int getTotalFrames()
   {
      return this.totalFrames;
   }

   @JsMethod
   private void setTotalFrames(int totalFrames)
   {
      this.totalFrames = totalFrames;
   }
}
