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

public class UpKeyEventHandler extends UpKeyEventHandlerBase
{
   private static final UpKeyEventHandler instance = new UpKeyEventHandler();

   private static final UpKeyEventHandlerBase[] instanceArray = {
       new UpKeyEventHandlerBase(),
       new UpKeyEventHandlerBase(),
       new UpKeyEventHandlerBase(),
       new UpKeyEventHandlerBase(),
       new UpKeyEventHandlerBase(),
       new UpKeyEventHandlerBase()
   };

   public static UpKeyEventHandler getInstance()
   {
      return instance;
   }

   private final LocalPlayerInputIdFactory playerInputIdFactory = LocalPlayerInputIdFactory.getInstance();
   
   public UpKeyEventHandlerBase getInstance(int deviceId)
   {
       final int playerInputId = playerInputIdFactory.getPlayerForDevice(deviceId);
       return instanceArray[playerInputId];
   }

   public UpKeyEventHandlerBase getInstanceForPlayer(int playerInputId)
   {
       return instanceArray[playerInputId];
   }
   
   private UpKeyEventHandler()
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
    public void removeListener(EventListenerInterface eventListenerInterface)
    {
        super.removeListener(eventListenerInterface);

        for(int index = instanceArray.length - 1; index >= 0; index--)
        {
            instanceArray[index].removeListener(eventListenerInterface);
        }
    }
}
