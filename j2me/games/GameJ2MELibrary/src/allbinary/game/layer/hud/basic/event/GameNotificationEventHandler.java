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
package allbinary.game.layer.hud.basic.event;

import org.allbinary.util.BasicArrayList;

import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class GameNotificationEventHandler extends BasicEventHandler
{
   private static final GameNotificationEventHandler instance = 
      new GameNotificationEventHandler();

   private GameNotificationEventHandler()
   {
   }

   public synchronized void removeAllListeners()
   {
       //Keep the system specific listener around
       BasicArrayList list = this.getEventListenerInterfaceList();
       
       EventListenerInterface eventListenerInterface = null;
       
       if(list.size() > 0)
       {
           eventListenerInterface = (EventListenerInterface) list.get(0);
       }
       
       super.removeAllListeners();
       
       if(eventListenerInterface != null)
       {
           this.addListener(eventListenerInterface);
       }
   }
   
   public static GameNotificationEventHandler getInstance()
   {
      return instance;
   }
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface)
   throws Exception
   {

      ((GameNotificationListenerInterface) eventListenerInterface).onGameNotificationEvent(
              (GameNotificationEvent) eventObject);
   }
}
