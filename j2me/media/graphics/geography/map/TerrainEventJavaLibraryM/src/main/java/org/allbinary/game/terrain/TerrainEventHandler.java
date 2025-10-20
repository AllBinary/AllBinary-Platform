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

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.util.BasicArrayList;

public class TerrainEventHandler extends BasicEventHandler
{

   private static Hashtable hashtable = NullUtil.getInstance().NULL_TABLE;

   public static void init()
   {
      TerrainEventHandler.hashtable = new Hashtable();
   }
   
   public static TerrainEventHandler getInstance(final Object object)
   {
      Object eventHandlerCanBeNull = TerrainEventHandler.hashtable.get(object);

      if(eventHandlerCanBeNull == null)
      {
         eventHandlerCanBeNull = new TerrainEventHandler();
         TerrainEventHandler.hashtable.put(object, eventHandlerCanBeNull);
      }

      return (TerrainEventHandler) eventHandlerCanBeNull;
   }
   
   private final BasicArrayList list = new BasicArrayList();
   
   private TerrainEventHandler()
   {
   }

   public void addListener(final TerrainEventListener terrainEventListener)
   {
       if(!list.contains(terrainEventListener))
       {
           list.add(terrainEventListener);
       }
   }

   @Override
   public void removeAllListeners()
   {
       this.list.clear();
       super.removeAllListeners();
   }

   @Override
   public void removeListener(final EventListenerInterface eventListenerInterface)
   {
       this.list.remove(eventListenerInterface);
       super.removeListener(eventListenerInterface);
   }

   @Override
   public void fireEvent(final AllBinaryEventObject eventObject) throws Exception
   {        
       for (int index = this.list.size(); --index >= 0;)
       {
           try
           {
               final TerrainEventListener terrainEventListener = (TerrainEventListener) this.list.get(index);
               terrainEventListener.onTerrainEvent((TerrainEvent) eventObject);
           }
           catch (Exception e)
           {
               logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
           }
       }

       super.fireEvent(eventObject);
   }

   @Override
   protected void process(final AllBinaryEventObject eventObject,
           final EventListenerInterface eventListenerInterface) throws Exception {

       final TerrainEventListenerInterface terrainEventListenerInterface = (TerrainEventListenerInterface) eventListenerInterface;
       terrainEventListenerInterface.onTerrainEvent((TerrainEvent) eventObject);
   }
}
