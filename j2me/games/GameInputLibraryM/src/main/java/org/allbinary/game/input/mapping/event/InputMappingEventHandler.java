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
package org.allbinary.game.input.mapping.event;

import org.allbinary.logic.basic.util.event.AllBinaryEventObject;
import org.allbinary.logic.basic.util.event.EventListenerInterface;
import org.allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class InputMappingEventHandler extends BasicEventHandler
{
   private static final InputMappingEventHandler gameKeyEventHandler = 
      new InputMappingEventHandler();

   private InputMappingEventHandler()
   {
   }

   public static InputMappingEventHandler getInstance()
   {
      return InputMappingEventHandler.gameKeyEventHandler;
   }
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((InputMappingEventListenerInterface) eventListenerInterface).onInputMappingEvent(
              (InputMappingEvent) eventObject);
   }
}
