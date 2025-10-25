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
package org.allbinary.game.combat.destroy.event;

import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.util.BasicArrayList;

public class DestroyedEventHandler extends BasicEventHandler
{

   private static final DestroyedEventHandler instance = 
      new DestroyedEventHandler();

   public synchronized static DestroyedEventHandler getInstance()
   {
      return DestroyedEventHandler.instance;
   }
   
   private final BasicArrayList list = new BasicArrayList();
   
   private DestroyedEventHandler()
   {
   }

   public void addListener(CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer)
   {
       if(!list.contains(collidableDestroyableDamageableLayer))
       {
           list.add(collidableDestroyableDamageableLayer);
       }
   }

   @Override
   public synchronized void removeAllListeners()
   {
       this.list.clear();
       super.removeAllListeners();
   }

   @Override
   public synchronized void removeListener(EventListenerInterface eventListenerInterface)
   {
       this.list.remove(eventListenerInterface);
       super.removeListener(eventListenerInterface);
   }

   @Override
   public void fireEvent(AllBinaryEventObject eventObject) throws Exception
   {        
       for (int index = this.list.size(); --index >= 0;)
       {
           try
           {
               CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer = 
                       (CollidableDestroyableDamageableLayer) this.list.objectArray[index];
               collidableDestroyableDamageableLayer.onDestroyed((DestroyedEvent) eventObject);
           }
           catch (Exception e)
           {
               logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
           }
       }

       super.fireEvent(eventObject);
   }
   
   @Override
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

       final DestroyedEventListenerInterface destroyedEventListenerInterface = (DestroyedEventListenerInterface) eventListenerInterface;
       destroyedEventListenerInterface.onDestroyed((DestroyedEvent) eventObject);
   }
   
}
