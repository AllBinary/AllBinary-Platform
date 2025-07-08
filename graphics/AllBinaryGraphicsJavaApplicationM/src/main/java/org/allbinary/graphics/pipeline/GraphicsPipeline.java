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
package org.allbinary.graphics.pipeline;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.util.BasicArrayList;

public class GraphicsPipeline extends BasicGraphicsPipeline
{
    //protected final LogUtil logUtil = LogUtil.getInstance();
   
   public GraphicsPipeline(BasicArrayList points)
   {
      super(points);
   }
   
      //Double rotate = new Double(Math.toRadians(angle.doubleValue()));
      //graphicsPipe.rotate(rotate.doubleValue());   
   public void rotateTheta(double aTheta)
           throws Exception
   {  
      BasicArrayList newVector = new BasicArrayList();
      int size = this.pointBasicArrayList.size();
      for(int index = 0; index < size; index++)
      {
         GPoint secondPoint = (GPoint) pointBasicArrayList.get(index);
         
         double secondX = (secondPoint.getX() * Math.cos(aTheta)) -
         (secondPoint.getY() * Math.sin(aTheta));
         
         double secondY = (secondPoint.getX() * Math.sin(aTheta)) +
         (secondPoint.getY() * Math.cos(aTheta));
         
         //LogUtil.put("Calculated: X: " + secondX + " Y: " + secondY, this, "rotate");
         
         GPoint point = PointFactory.getInstance().getInstance(((int) secondX), ((int) secondY));
         
         newVector.add(point);
      }
      this.pointBasicArrayList = newVector;
   }
}