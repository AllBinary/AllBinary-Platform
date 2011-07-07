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
package allbinary.game.terrain;

import java.util.Hashtable;

import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class TerrainEventHandler extends BasicEventHandler
{
   private static Hashtable hashtable;

   private TerrainEventHandler()
   {
   }

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
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((TerrainEventListenerInterface) eventListenerInterface).onTerrainEvent(
              (TerrainEvent) eventObject);
   }
}
