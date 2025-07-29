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

public class UpGameKeyEventHandler extends UpGameKeyEventHandlerBase
{
   private static final UpGameKeyEventHandler instance = new UpGameKeyEventHandler();

   private static final UpGameKeyEventHandlerBase[] instanceArray = {
       new UpGameKeyEventHandlerBase(),
       new UpGameKeyEventHandlerBase(),
       new UpGameKeyEventHandlerBase(),
       new UpGameKeyEventHandlerBase(),
       new UpGameKeyEventHandlerBase(),
       new UpGameKeyEventHandlerBase()
   };

   public static UpGameKeyEventHandler getInstance()
   {
      return instance;
   }

   private final LocalPlayerInputIdFactory playerInputIdFactory = LocalPlayerInputIdFactory.getInstance();
   
   public UpGameKeyEventHandlerBase getInstance(int deviceId)
   {
       final int playerInputId = playerInputIdFactory.getPlayerForDevice(deviceId);
       return instanceArray[playerInputId];
   }

   public UpGameKeyEventHandlerBase getInstanceForPlayer(int playerInputId)
   {
       return instanceArray[playerInputId];
   }
   
   private UpGameKeyEventHandler()
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
