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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class VirtualKeyboardEventHandler extends BasicEventHandler
{
   private static final VirtualKeyboardEventHandler gameKeyEventHandler = 
      new VirtualKeyboardEventHandler();

   private VirtualKeyboardEventHandler()
   {
   }

   private final CommonStrings commonStrings = CommonStrings.getInstance();

   public final VirtualKeyboardEvent SHOW_EVENT = new VirtualKeyboardEvent(BooleanFactory.getInstance().TRUE);
   public final VirtualKeyboardEvent HIDE_EVENT = new VirtualKeyboardEvent(BooleanFactory.getInstance().FALSE);

   public static VirtualKeyboardEventHandler getInstance()
   {
      return VirtualKeyboardEventHandler.gameKeyEventHandler;
   }
   
   @Override
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((VirtualKeyboardEventListenerInterface) 
              eventListenerInterface).onVirtualKeyboardEvent(
                      (VirtualKeyboardEvent) eventObject);
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
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "open", e));
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
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CLOSE, e));
        }

    }
   
}
