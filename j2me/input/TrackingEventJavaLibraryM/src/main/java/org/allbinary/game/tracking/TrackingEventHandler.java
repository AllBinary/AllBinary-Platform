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
package org.allbinary.game.tracking;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class TrackingEventHandler extends BasicEventHandler
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private static final TrackingEventHandler instance = new TrackingEventHandler();

   public static TrackingEventHandler getInstance()
   {
      return TrackingEventHandler.instance;
   }
   
   //private final BasicArrayList list = new BasicArrayList();
   
   private TrackingEventHandler()
   {
   }

   /*
   public void addListener(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer)
   {
       if(!list.contains(collidableDestroyableDamageableLayer))
       {
           list.add(collidableDestroyableDamageableLayer);
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
               CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer = 
                       (CollidableDestroyableDamageableLayer) this.list.get(index);
               collidableDestroyableDamageableLayer.onMovement((TrackingEvent) eventObject);
           }
           catch (Exception e)
           {
               logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
           }
       }

       super.fireEvent(eventObject);
   }
   */
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((TrackingEventListenerInterface) eventListenerInterface).onMovement(
              (TrackingEvent) eventObject);
   }   
}
