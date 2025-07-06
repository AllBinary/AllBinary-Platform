/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 */

package org.allbinary.game.layer.building.event;

import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.logic.util.event.EventListenerInterface;

public interface BuildingEventListenerInterface
   extends EventListenerInterface
{
   void onBuildingEvent(RTSLayerEvent event)
      throws Exception;
}
