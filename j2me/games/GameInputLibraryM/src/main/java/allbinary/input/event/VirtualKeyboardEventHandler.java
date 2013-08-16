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
package allbinary.input.event;

import abcs.logic.java.bool.BooleanFactory;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class VirtualKeyboardEventHandler extends BasicEventHandler
{
   private static final VirtualKeyboardEventHandler gameKeyEventHandler = 
      new VirtualKeyboardEventHandler();

   private VirtualKeyboardEventHandler()
   {
   }

   public final VirtualKeyboardEvent SHOW_EVENT = new VirtualKeyboardEvent(BooleanFactory.getInstance().TRUE);
   public final VirtualKeyboardEvent HIDE_EVENT = new VirtualKeyboardEvent(BooleanFactory.getInstance().FALSE);

   public static VirtualKeyboardEventHandler getInstance()
   {
      return VirtualKeyboardEventHandler.gameKeyEventHandler;
   }
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((VirtualKeyboardEventListenerInterface) 
              eventListenerInterface).onVirtualKeyboardEvent(
                      (VirtualKeyboardEvent) eventObject);
   }
}
