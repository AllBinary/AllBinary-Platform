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
import allbinary.logic.math.BasicDecimal;
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
      
      for(int index = 0; index < size; index++)
      {
         GPoint secondPoint = (GPoint) pointBasicArrayList.get(index);
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

      int size = this.pointBasicArrayList.size();
      for(int index = 0; index < size; index++)
      {
         GPoint secondPoint = (GPoint) pointBasicArrayList.get(index);
         //LogUtil.put("Old: " + secondPoint.toString(), this, "rotate");
         //Ignore end of lines
         if (secondPoint.getX() != 1000)
         {
             long y = secondPoint.getY() * noDecimalTrigTable.sin(angle);
             long secondX = (secondPoint.getX() * noDecimalTrigTable.cos(angle)) - y;

             long secondY = (secondPoint.getX() * noDecimalTrigTable.sin(angle)) + (secondPoint.getY() * noDecimalTrigTable.cos(angle));

            //LogUtil.put("Calculated: X: " + secondX + " Y: " + secondY, this, "rotate");
            BasicDecimal secondXBasicDecimal = new BasicDecimal(secondX / 10);
            BasicDecimal secondYBasicDecimal = new BasicDecimal(secondY / 10);

            GPoint point = PointFactory.getInstance().getInstance(secondXBasicDecimal.getScaled(), secondYBasicDecimal.getScaled());

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

      int size = this.pointBasicArrayList.size();
      for(int index = 0; index < size; index++)
      {
         GPoint secondPoint = (GPoint) pointBasicArrayList.get(index);
         //LogUtil.put("Old: " + secondPoint.toString(), this, "rotate");
         //Ignore end of lines
         if (secondPoint.getX() != 1000)
         {
            int newX = 0;
            
            if(secondPoint.getX() > halfWidth)
            {
               newX = halfWidth - (secondPoint.getX() - halfWidth);
            }
            else
            {
               newX = halfWidth + (halfWidth - secondPoint.getX());
            }

            GPoint point = PointFactory.getInstance().getInstance(newX, secondPoint.getY());

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