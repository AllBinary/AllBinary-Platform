/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 */

package org.allbinary.game.rts.technology.event;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class TechEventHandler extends BasicEventHandler
{
   private static final TechEventHandler eventHandler = new TechEventHandler();

   private TechEventHandler()
   {
   }

   public static TechEventHandler getInstance()
   {
      return TechEventHandler.eventHandler;
   }
   
   @Override
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

       final TechEventListenerInterface techEventListenerInterface = ((TechEventListenerInterface) eventListenerInterface);
       techEventListenerInterface.onTechEvent(eventObject);
   }   
}
