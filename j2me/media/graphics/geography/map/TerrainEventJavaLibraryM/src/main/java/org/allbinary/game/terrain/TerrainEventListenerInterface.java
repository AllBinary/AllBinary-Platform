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

import org.allbinary.logic.basic.util.event.EventListenerInterface;

public interface TerrainEventListenerInterface 
   extends EventListenerInterface
{
   public void onTerrainEvent(TerrainEvent trackingEvent)
      throws Exception;
}
