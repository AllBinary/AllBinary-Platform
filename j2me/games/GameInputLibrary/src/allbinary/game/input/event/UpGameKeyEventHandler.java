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
package allbinary.game.input.event;

import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class UpGameKeyEventHandler extends BasicEventHandler
{
   private static final UpGameKeyEventHandler gameKeyEventHandler = 
      new UpGameKeyEventHandler();

   private UpGameKeyEventHandler()
   {
   }

   public static UpGameKeyEventHandler getInstance()
   {
      return UpGameKeyEventHandler.gameKeyEventHandler;
   }
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((UpGameKeyEventListenerInterface) eventListenerInterface).onUpGameKeyEvent(
              (GameKeyEvent) eventObject);
   }
}
