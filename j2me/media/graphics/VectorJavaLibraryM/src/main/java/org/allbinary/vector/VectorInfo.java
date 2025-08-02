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

public class VectorInfo 
{
   private int width;
   private int height;
   private int[][] points;
   private int totalFrames;
   
   public VectorInfo(int width, int height, int[][] points, int totalFrames)
   {
       this.width = width;
       this.height = height;
       this.points = points;
       this.totalFrames = totalFrames;
   }

   public int getWidth()
   {
      return width;
   }

   private void setWidth(int width)
   {
      this.width = width;
   }

   public int getHeight()
   {
      return height;
   }

   private void setHeight(int height)
   {
      this.height = height;
   }

   public int[][] getPoints()
   {
      return points;
   }

   private void setPoints(int[][] points)
   {
      this.points = points;
   }

   public int getTotalFrames()
   {
      return totalFrames;
   }

   private void setTotalFrames(int totalFrames)
   {
      this.totalFrames = totalFrames;
   }
}
