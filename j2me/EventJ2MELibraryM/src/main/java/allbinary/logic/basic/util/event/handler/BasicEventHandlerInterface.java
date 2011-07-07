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
package allbinary.logic.basic.util.event.handler;

import org.allbinary.util.BasicArrayList;

import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;

public interface BasicEventHandlerInterface
{
   void addListener(EventListenerInterface eventListenerInterface);

   void addListeners(BasicArrayList basicArrayList);

   void fireEvent(AllBinaryEventObject eventObject) throws Exception;

   void removeAllListeners();

   void removeListener(EventListenerInterface eventListenerInterface);
}
