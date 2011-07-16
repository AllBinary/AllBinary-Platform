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

import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.graphics.Rectangle;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

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
       ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
   }
   
   public void onBoundsChangeEvent(BoundsChangeEvent boundsChangeEvent)
   {
       this.rectangle = boundsChangeEvent.getRectangle();
   }
}
