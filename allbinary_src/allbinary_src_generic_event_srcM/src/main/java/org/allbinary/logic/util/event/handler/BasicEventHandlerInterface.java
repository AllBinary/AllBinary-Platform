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
package org.allbinary.logic.util.event.handler;

import jsinterop.annotations.JsType;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import jsinterop.annotations.JsMethod;


@JsType
public interface BasicEventHandlerInterface
{
   @JsMethod
   void addListenerInterface(EventListenerInterface eventListenerInterface);

   //void addListeners(BasicArrayList basicArrayList);

   @JsMethod
   void fireEvent(AllBinaryEventObject eventObject) throws Exception;

   @JsMethod
   void removeAllListeners();

   @JsMethod
   void removeListener(EventListenerInterface eventListenerInterface);
}
