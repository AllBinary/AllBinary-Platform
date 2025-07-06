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

package org.allbinary.game.layer.waypoint.event;

import org.allbinary.game.identification.Group;

/**
 *
 * @author user
 */
public class WaypointEventHandlerFactory {

    //One for each Group
    private static final WaypointEventHandler[] eventHandlerArray =
    {new WaypointEventHandler(),
     new WaypointEventHandler(),
     new WaypointEventHandler(),
     new WaypointEventHandler(),
     new WaypointEventHandler(),
     new WaypointEventHandler(),
     new WaypointEventHandler(),
     new WaypointEventHandler(),
     new WaypointEventHandler(),
     new WaypointEventHandler()
    };

    public static WaypointEventHandler getInstance(Group groupInterface)
    {
        return eventHandlerArray[groupInterface.getGroupId()];
    }

    public static void removeAll()
    {
        for(int index = eventHandlerArray.length - 1; index >= 0; index--)
        {
            eventHandlerArray[index].removeAllListeners();
        }
    }
}
