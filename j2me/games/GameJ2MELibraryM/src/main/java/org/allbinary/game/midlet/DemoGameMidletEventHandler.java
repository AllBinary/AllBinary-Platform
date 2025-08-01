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
package org.allbinary.game.midlet;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class DemoGameMidletEventHandler extends BasicEventHandler
{
   private static final DemoGameMidletEventHandler gameKeyEventHandler = 
      new DemoGameMidletEventHandler();

   private DemoGameMidletEventHandler()
   {
   }

   public static DemoGameMidletEventHandler getInstance()
   {
      return DemoGameMidletEventHandler.gameKeyEventHandler;
   }
   
   @Override
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

       final DemoGameMidletEventListener demoGameMidletEventListener = 
           (DemoGameMidletEventListener) eventListenerInterface;
      demoGameMidletEventListener.onDemoGameMidletEvent((DemoGameMidletEvent) eventObject);
   }   
}
