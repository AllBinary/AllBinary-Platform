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
package org.allbinary.game.input.event;

import org.allbinary.game.input.PlayerInputIdFactory;
import org.allbinary.logic.util.event.EventListenerInterface;

public class DownGameKeyEventHandler extends DownGameKeyEventHandlerBase
{
    private static final DownGameKeyEventHandler instance = new DownGameKeyEventHandler();
    
    private static final DownGameKeyEventHandlerBase[] instanceArray = 
    {
        new DownGameKeyEventHandlerBase(),
        new DownGameKeyEventHandlerBase(),
        new DownGameKeyEventHandlerBase(),
        new DownGameKeyEventHandlerBase(),
        new DownGameKeyEventHandlerBase(),
        new DownGameKeyEventHandlerBase(),        
    };

    public static DownGameKeyEventHandler getInstance()
    {
        return instance;
    }
    
    private final PlayerInputIdFactory playerInputIdFactory = PlayerInputIdFactory.getInstance();
    
   public DownGameKeyEventHandlerBase getInstance(int deviceId)
   {
       final int playerInputId = this.playerInputIdFactory.getPlayerForDevice(deviceId);
       return instanceArray[playerInputId];
   }
   
   public DownGameKeyEventHandlerBase getInstanceForPlayer(int playerInputId)
   {
       return instanceArray[playerInputId];
   }
   
    private DownGameKeyEventHandler()
    {
    }

    public void removeAllListeners()
    {
        super.removeAllListeners();

        for(int index = instanceArray.length - 1; index >= 0; index--)
        {
            instanceArray[index].removeAllListeners();
        }
    }

    public void removeListenerSingleThreaded(EventListenerInterface eventListenerInterface)
    {
        super.removeListenerSingleThreaded(eventListenerInterface);

        for(int index = instanceArray.length - 1; index >= 0; index--)
        {
            instanceArray[index].removeListenerSingleThreaded(eventListenerInterface);
        }
    }
    
    public synchronized void removeListener(EventListenerInterface eventListenerInterface)
    {
        super.removeListener(eventListenerInterface);

        for(int index = instanceArray.length - 1; index >= 0; index--)
        {
            instanceArray[index].removeListener(eventListenerInterface);
        }
    }
}
