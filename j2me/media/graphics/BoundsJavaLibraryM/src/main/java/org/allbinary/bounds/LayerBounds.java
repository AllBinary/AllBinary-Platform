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

import org.allbinary.bounds.event.BoundsChangeEvent;
import org.allbinary.bounds.event.BoundsChangeEventListener;

import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.graphics.Rectangle;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;

public class LayerBounds implements BoundsChangeEventListener
{   
   protected Rectangle rectangle;
   
   protected LayerBounds(Rectangle rectangle)
   {
      this.rectangle = rectangle;
   }
   
   public Rectangle getRectangle()
   {
      return rectangle;
   }
   
   public void visit(BoundsVisitorInterface boundsVisitorInterface)
   {  
   }
   
   public void onEvent(AllBinaryEventObject eventObject)
   {
       ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
   }
   
   public void onBoundsChangeEvent(BoundsChangeEvent boundsChangeEvent)
   {
       this.rectangle = boundsChangeEvent.getRectangle();
   }
}
