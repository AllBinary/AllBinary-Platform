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
package org.allbinary.bounds;

import allbinary.graphics.Rectangle;

public class TopULayerBounds extends LayerBounds
{   
   public TopULayerBounds(Rectangle rectangle)
   {
       super(rectangle);
   }
   
   public void visit(BoundsVisitorInterface boundsVisitorInterface)
   {  
      if (boundsVisitorInterface.getX() > this.getRectangle().getMaxX())
      {
         boundsVisitorInterface.maxX();
      }
      
      if (boundsVisitorInterface.getX() < this.getRectangle().getPoint().getX())
      {
         boundsVisitorInterface.minX();
      }

      if (boundsVisitorInterface.getY() < this.getRectangle().getPoint().getY())
      {
         boundsVisitorInterface.minY();
      }
      
   }
}
