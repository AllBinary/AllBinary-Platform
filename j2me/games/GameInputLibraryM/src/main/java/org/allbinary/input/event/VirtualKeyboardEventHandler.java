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

import jsinterop.annotations.JsType;

import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class VirtualKeyboardEventHandler extends BasicEventHandler
{

   private static final VirtualKeyboardEventHandler gameKeyEventHandler = 
      new VirtualKeyboardEventHandler();

   @JsConstructor
   private VirtualKeyboardEventHandler()
   {
   }

   @JsProperty
   public final VirtualKeyboardEvent SHOW_EVENT = new VirtualKeyboardEvent(BooleanFactory.getInstance().TRUE);
   @JsProperty
   public final VirtualKeyboardEvent HIDE_EVENT = new VirtualKeyboardEvent(BooleanFactory.getInstance().FALSE);

   @JsMethod
   public static VirtualKeyboardEventHandler getInstance()
   {
      return VirtualKeyboardEventHandler.gameKeyEventHandler;
   }
   
   @Override
   @JsMethod
   protected void process(final AllBinaryEventObject eventObject,
           final EventListenerInterface eventListenerInterface) throws Exception {

      final VirtualKeyboardEventListenerInterface virtualKeyboardEventListenerInterface = (VirtualKeyboardEventListenerInterface) /*TS as unknown*/ eventListenerInterface;
      virtualKeyboardEventListenerInterface.onVirtualKeyboardEvent((VirtualKeyboardEvent) eventObject);
   }
   
    @JsMethod
    public void open()
    {
        try
        {
            Thread.sleep(120);
            this.fireEvent(this.SHOW_EVENT);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "open", e);
        }

    }

    @JsMethod
    public void close() throws Exception
    {
        try
        {
            Thread.sleep(120);
            this.fireEvent(this.HIDE_EVENT);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.CLOSE, e);
        }

    }
   
}
