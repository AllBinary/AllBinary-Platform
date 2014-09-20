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
package org.allbinary.game.input.event;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.logic.basic.util.event.AllBinaryEventObject;
import org.allbinary.logic.basic.util.event.EventListenerInterface;
import org.allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class PressGameKeyEventHandler extends BasicEventHandler
{
   private static final PressGameKeyEventHandler instance = new PressGameKeyEventHandler();

   public static PressGameKeyEventHandler getInstance()
   {
      return PressGameKeyEventHandler.instance;
   }

   private final BasicArrayList list = new BasicArrayList();
   
   private PressGameKeyEventHandler()
   {
   }
   
   public void addListener(PlayerGameInput playerGameInput)
   {
       if(!list.contains(playerGameInput))
       {
           list.add(playerGameInput);
       }
   }

   public void removeAllListeners()
   {
       this.list.clear();
       super.removeAllListeners();
   }

   public void removeListener(EventListenerInterface eventListenerInterface)
   {
       this.list.remove(eventListenerInterface);
       super.removeListener(eventListenerInterface);
   }

   public void fireEvent(AllBinaryEventObject eventObject) throws Exception
   {        
       for (int index = this.list.size(); --index >= 0;)
       {
           try
           {
        	 //Add deviceId
               PlayerGameInput playerGameInput = (PlayerGameInput) this.list.objectArray[index];
               playerGameInput.onPressGameKeyEvent((GameKeyEvent) eventObject);
           }
           catch (Exception e)
           {
               LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
           }
       }

       super.fireEvent(eventObject);
   }
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((PressGameKeyEventListenerInterface) eventListenerInterface).onPressGameKeyEvent(
              (GameKeyEvent) eventObject);
   }
   
}
