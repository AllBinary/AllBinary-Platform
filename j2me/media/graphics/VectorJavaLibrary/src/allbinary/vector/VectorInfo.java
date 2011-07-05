/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: Oct 19, 2007, 3:37:37 PM
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.vector;

public class VectorInfo 
{
   private int width;
   private int height;
   private int[][] points;
   private int totalFrames;
   
   public VectorInfo(int width, int height, int[][] points, int totalFrames)
   {
      this.setWidth(width);
      this.setHeight(height);
      this.setPoints(points);
      this.setTotalFrames(totalFrames);
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
