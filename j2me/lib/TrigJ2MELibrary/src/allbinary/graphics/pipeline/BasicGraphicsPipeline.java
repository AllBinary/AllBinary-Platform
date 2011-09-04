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
package allbinary.graphics.pipeline;

import org.allbinary.util.BasicArrayList;

import allbinary.graphics.GPoint;
import allbinary.graphics.PointFactory;
import allbinary.math.Angle;
import allbinary.math.NoDecimalTrigTable;

public class BasicGraphicsPipeline
{
   protected BasicArrayList pointBasicArrayList;

   public BasicGraphicsPipeline(BasicArrayList points)
   {
      pointBasicArrayList = points;
   }

   public void setInitMatrix(BasicArrayList points)
   {
      pointBasicArrayList = points;
   }

   public void createMatrix()
   {
   }

   public void translate(int x, int y) throws Exception
   {
      BasicArrayList newBasicArrayList = new BasicArrayList();
      int size = pointBasicArrayList.size();

      PointFactory pointFactory = PointFactory.getInstance();
      
      GPoint secondPoint;
      
      for(int index = 0; index < size; index++)
      {
          secondPoint = (GPoint) pointBasicArrayList.get(index);
         //Ignore end of lines
         if (secondPoint.getX() != 1000)
         {
            newBasicArrayList.add(pointFactory.getInstance(secondPoint.getX() + x, secondPoint.getY() + y));
         } else
         {
            newBasicArrayList.add(secondPoint);
         }
      }
      this.pointBasicArrayList = newBasicArrayList;
   }

   public void rotate(Angle angle) throws Exception
   {
      this.rotate(angle.getValue());
   }

   private final NoDecimalTrigTable noDecimalTrigTable = NoDecimalTrigTable.getInstance();
   
   private void rotate(short angle) throws Exception
   {
      BasicArrayList newBasicArrayList = new BasicArrayList();

      GPoint secondPoint;

      long y;
      long secondX;
      long secondY;
      GPoint point;
      
      PointFactory pointFactory = PointFactory.getInstance();
      
      int size = this.pointBasicArrayList.size();
      for(int index = 0; index < size; index++)
      {
          secondPoint = (GPoint) pointBasicArrayList.get(index);
         //LogUtil.put("Old: " + secondPoint.toString(), this, "rotate");
         //Ignore end of lines
         if (secondPoint.getX() != 1000)
         {
             y = secondPoint.getY() * noDecimalTrigTable.sin(angle);
             secondX = (secondPoint.getX() * noDecimalTrigTable.cos(angle)) - y;
             secondY = (secondPoint.getX() * noDecimalTrigTable.sin(angle)) + (secondPoint.getY() * noDecimalTrigTable.cos(angle));

            //LogUtil.put("Calculated: X: " + secondX + " Y: " + secondY, this, "rotate");

             point = pointFactory.getInstance((int) secondX / 10000, (int) secondY / 10000);

            //LogUtil.put("New: " + point.toString(), this, "rotate");
            newBasicArrayList.add(point);
         } else
         {
            newBasicArrayList.add(secondPoint);
         }
      }

      this.pointBasicArrayList = newBasicArrayList;
   }

   public void mirror(int width) throws Exception
   {
      int halfWidth = (width >> 1);
      BasicArrayList newBasicArrayList = new BasicArrayList();

      GPoint secondPoint;
      int newX;
      GPoint point;
      
      PointFactory pointFactory = PointFactory.getInstance();
      
      int size = this.pointBasicArrayList.size();
      for(int index = 0; index < size; index++)
      {
          secondPoint = (GPoint) pointBasicArrayList.get(index);
         //LogUtil.put("Old: " + secondPoint.toString(), this, "rotate");
         //Ignore end of lines
         if (secondPoint.getX() != 1000)
         {
             newX = 0;
            
            if(secondPoint.getX() > halfWidth)
            {
               newX = halfWidth - (secondPoint.getX() - halfWidth);
            }
            else
            {
               newX = halfWidth + (halfWidth - secondPoint.getX());
            }

            point = pointFactory.getInstance(newX, secondPoint.getY());

            //LogUtil.put("New: " + point.toString(), this, "rotate");
            newBasicArrayList.add(point);
         } else
         {
            newBasicArrayList.add(secondPoint);
         }
      }

      this.pointBasicArrayList = newBasicArrayList;
   }
   
   public BasicArrayList getMatrix()
   {
      return this.pointBasicArrayList;
   }
}