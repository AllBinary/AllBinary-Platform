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
package allbinary.game.combat.destroy.event;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

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
                       (CollidableDestroyableDamageableLayer) this.list.objectArray[index];
               collidableDestroyableDamageableLayer.onDestroyed((DestroyedEvent) eventObject);
           }
           catch (Exception e)
           {
               LogUtil.put(new Log(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
           }
       }

       super.fireEvent(eventObject);
   }
   
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((DestroyedEventListenerInterface) eventListenerInterface).onDestroyed(
              (DestroyedEvent) eventObject);
   }
   
}
