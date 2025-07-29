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

import org.allbinary.game.input.LocalPlayerInputIdFactory;
import org.allbinary.logic.util.event.EventListenerInterface;

public class DownKeyEventHandler extends DownKeyEventHandlerBase
{
    private static final DownKeyEventHandler instance = new DownKeyEventHandler();
    
    private static final DownKeyEventHandlerBase[] instanceArray = 
    {
        new DownKeyEventHandlerBase(),
        new DownKeyEventHandlerBase(),
        new DownKeyEventHandlerBase(),
        new DownKeyEventHandlerBase(),
        new DownKeyEventHandlerBase(),
        new DownKeyEventHandlerBase(),        
    };

    public static DownKeyEventHandler getInstance()
    {
        return instance;
    }
    
    private final LocalPlayerInputIdFactory playerInputIdFactory = LocalPlayerInputIdFactory.getInstance();
    
   public DownKeyEventHandlerBase getInstance(int deviceId)
   {
       final int playerInputId = playerInputIdFactory.getPlayerForDevice(deviceId);
       return instanceArray[playerInputId];
   }
   
   public DownKeyEventHandlerBase getInstanceForPlayer(int playerInputId)
   {
       return instanceArray[playerInputId];
   }
   
    private DownKeyEventHandler()
    {
    }

    @Override
    public void removeAllListeners()
    {
        super.removeAllListeners();

        for(int index = instanceArray.length - 1; index >= 0; index--)
        {
            instanceArray[index].removeAllListeners();
        }
    }

    @Override
    public void removeListenerSingleThreaded(EventListenerInterface eventListenerInterface)
    {
        super.removeListenerSingleThreaded(eventListenerInterface);

        for(int index = instanceArray.length - 1; index >= 0; index--)
        {
            instanceArray[index].removeListenerSingleThreaded(eventListenerInterface);
        }
    }
    
    @Override
    public synchronized void removeListener(EventListenerInterface eventListenerInterface)
    {
        super.removeListener(eventListenerInterface);

        for(int index = instanceArray.length - 1; index >= 0; index--)
        {
            instanceArray[index].removeListener(eventListenerInterface);
        }
    }
}
