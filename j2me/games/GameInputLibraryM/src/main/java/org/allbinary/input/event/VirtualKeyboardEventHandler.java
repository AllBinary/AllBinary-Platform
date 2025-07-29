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
package org.allbinary.input.event;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.string.CommonStrings;

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
   
   @Override
   protected void process(final AllBinaryEventObject eventObject,
           final EventListenerInterface eventListenerInterface) throws Exception {

       final VirtualKeyboardEventListenerInterface virtualKeyboardEventListenerInterface = (VirtualKeyboardEventListenerInterface) eventListenerInterface;
      virtualKeyboardEventListenerInterface.onVirtualKeyboardEvent((VirtualKeyboardEvent) eventObject);
   }
   
    public void open()
    {
        try
        {
            Thread.sleep(120);
            this.fireEvent(this.SHOW_EVENT);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "open", e);
        }

    }

    public void close() throws Exception
    {
        try
        {
            Thread.sleep(120);
            this.fireEvent(this.HIDE_EVENT);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CLOSE, e);
        }

    }
   
}
