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
package org.allbinary.game.terrain;

import java.util.Hashtable;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.util.BasicArrayList;

public class TerrainEventHandler extends BasicEventHandler
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private static Hashtable hashtable;

   public static void init()
   {
      TerrainEventHandler.hashtable = new Hashtable();
   }
   
   public static TerrainEventHandler getInstance(Object object)
   {
      TerrainEventHandler eventHandler = (TerrainEventHandler)
              TerrainEventHandler.hashtable.get(object);

      if(eventHandler == null)
      {
         eventHandler = new TerrainEventHandler();
         TerrainEventHandler.hashtable.put(object, eventHandler);
      }

      return eventHandler;
   }
   
   private final BasicArrayList list = new BasicArrayList();
   
   private TerrainEventHandler()
   {
   }

   public void addListener(TerrainEventListener terrainEventListener)
   {
       if(!list.contains(terrainEventListener))
       {
           list.add(terrainEventListener);
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
               TerrainEventListener terrainEventListener = (TerrainEventListener) this.list.get(index);
               terrainEventListener.onTerrainEvent((TerrainEvent) eventObject);
           }
           catch (Exception e)
           {
               logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
           }
       }

       super.fireEvent(eventObject);
   }

   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((TerrainEventListenerInterface) eventListenerInterface).onTerrainEvent(
              (TerrainEvent) eventObject);
   }
}
