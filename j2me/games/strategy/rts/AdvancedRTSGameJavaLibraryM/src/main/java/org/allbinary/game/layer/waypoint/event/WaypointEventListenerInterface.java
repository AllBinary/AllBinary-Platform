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

package org.allbinary.game.layer.waypoint.event;

import org.allbinary.game.layer.RTSLayerEvent;
import org.allbinary.logic.util.event.EventListenerInterface;

public interface WaypointEventListenerInterface
   extends EventListenerInterface
{
   public void onWaypointEvent(RTSLayerEvent event)
      throws Exception;
}
