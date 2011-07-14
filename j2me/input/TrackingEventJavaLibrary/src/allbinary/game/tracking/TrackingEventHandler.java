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
package allbinary.game.tracking;

import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class TrackingEventHandler extends BasicEventHandler
{
   private static final TrackingEventHandler eventHandler = 
      new TrackingEventHandler();

   private TrackingEventHandler()
   {
   }

   public static TrackingEventHandler getInstance()
   {
      return TrackingEventHandler.eventHandler;
   }
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((TrackingEventListenerInterface) eventListenerInterface).onMovement(
              (TrackingEvent) eventObject);
   }   
}
