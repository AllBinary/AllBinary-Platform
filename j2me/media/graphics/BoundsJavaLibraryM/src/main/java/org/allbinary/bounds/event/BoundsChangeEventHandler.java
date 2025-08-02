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
package org.allbinary.bounds.event;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class BoundsChangeEventHandler extends BasicEventHandler
{
   private static BasicEventHandler SINGLETON = 
      new BoundsChangeEventHandler();
   
   private BoundsChangeEventHandler()
   {
   }

   public static BasicEventHandler getInstance()
   {
      return SINGLETON;
   }
   
   @Override
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

       final BoundsChangeEventListener boundsChangeEventListener = (BoundsChangeEventListener) eventListenerInterface;
       boundsChangeEventListener.onBoundsChangeEvent((BoundsChangeEvent) eventObject);
   }
   
}
