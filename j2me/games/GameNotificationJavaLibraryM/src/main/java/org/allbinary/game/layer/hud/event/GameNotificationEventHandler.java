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
package org.allbinary.game.layer.hud.event;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.NullEventListener;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.util.BasicArrayList;

public class GameNotificationEventHandler extends BasicEventHandler
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private static final GameNotificationEventHandler instance = 
      new GameNotificationEventHandler();

   //private final BasicArrayList list = new BasicArrayList();
   
   private GameNotificationEventHandler()
   {
   }

   public boolean enabled = true;

   /*
   public void addListener(GameNotificationHud gameNotificationHud)
   {
       if(!list.contains(gameNotificationHud))
       {
           list.add(gameNotificationHud);
       }
   }

   public void removeListener(EventListenerInterface eventListenerInterface)
   {
       super.removeListener(eventListenerInterface);
   }

   public void fireEvent(AllBinaryEventObject eventObject) throws Exception
   {        
       for (int index = this.list.size(); --index >= 0;)
       {
           try
           {
               GameNotificationHud gameNotificationHud = (GameNotificationHud) this.list.get(index);
               gameNotificationHud.onGameNotificationEvent((GameNotificationEvent) eventObject);
           }
           catch (Exception e)
           {
               logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
           }
       }

       super.fireEvent(eventObject);
   }
    */
   
   //This is not the default optimal method
   @Override
   public synchronized void removeAllListeners()
   {
       EventListenerInterface eventListenerInterface = NullEventListener.NULL_EVENT_LISTENER;

       /*
       if(this.list.size() > 0)
       {
           eventListenerInterface = (EventListenerInterface) this.list.get(0);
       }
       
       this.list.clear();

       if(eventListenerInterface != null)
       {
           this.addListener((GameNotificationHud) eventListenerInterface);
       }
       */
       
       //Keep the system specific listener around
       BasicArrayList list = this.eventListenerInterfaceList;
       
       if(list.size() > 0)
       {
           eventListenerInterface = (EventListenerInterface) list.objectArray[0];
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

   @Override
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface)
   throws Exception
   {
       if(!enabled) {
           return;
       }

       final GameNotificationListenerInterface gameNotificationListenerInterface = 
           (GameNotificationListenerInterface) eventListenerInterface;
      gameNotificationListenerInterface.onGameNotificationEvent((GameNotificationEvent) eventObject);
   }
}
