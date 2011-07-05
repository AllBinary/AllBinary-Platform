/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/29/02
 *
 *
 * Modified By         When       ?
 *
 */

package allbinary.game.terrain;

import allbinary.logic.basic.util.event.EventListenerInterface;

public interface TerrainEventListenerInterface 
   extends EventListenerInterface
{
   public void onTerrainEvent(TerrainEvent trackingEvent)
      throws Exception;
}
