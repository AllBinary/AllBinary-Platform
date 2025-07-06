/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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

package org.allbinary.game.layer.building.event;

import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class BuildingEventHandler extends BasicEventHandler
{
   private static BuildingEventHandler eventHandler =
      new BuildingEventHandler();

   private BuildingEventHandler()
   {
   }

   public static BuildingEventHandler getInstance()
   {
      return BuildingEventHandler.eventHandler;
   }
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((BuildingEventListenerInterface) eventListenerInterface).onBuildingEvent(
              (RTSLayerEvent) eventObject);
   }   
}
