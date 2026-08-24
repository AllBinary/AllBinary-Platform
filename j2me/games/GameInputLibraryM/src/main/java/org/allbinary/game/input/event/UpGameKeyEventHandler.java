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

import jsinterop.annotations.JsType;

import org.allbinary.game.input.LocalPlayerInputIdFactory;
import org.allbinary.logic.util.event.EventListenerInterface;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
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

   @JsMethod
   public static UpGameKeyEventHandler getInstance()
   {
      return UpGameKeyEventHandler.instance;
   }

   private final LocalPlayerInputIdFactory playerInputIdFactory = LocalPlayerInputIdFactory.getInstance();
   
   @JsMethod
   public UpGameKeyEventHandlerBase getInstanceForDevice(int deviceId)
   {
       final int playerInputId = this.playerInputIdFactory.getPlayerForDevice(deviceId);
       return UpGameKeyEventHandler.instanceArray[playerInputId];
   }

   @JsMethod
   public UpGameKeyEventHandlerBase getInstanceForPlayer(int playerInputId)
   {
       return UpGameKeyEventHandler.instanceArray[playerInputId];
   }
   
   @JsConstructor
   private UpGameKeyEventHandler()
   {
   }

   @Override
   @JsMethod
   public void removeAllListeners()
    {
        super.removeAllListeners();

        for(int index = UpGameKeyEventHandler.instanceArray.length - 1; index >= 0; index--)
        {
            UpGameKeyEventHandler.instanceArray[index].removeAllListeners();
        }
    }

    @Override
    @JsMethod
    public void removeListenerSingleThreaded(EventListenerInterface eventListenerInterface)
    {
        super.removeListenerSingleThreaded(eventListenerInterface);

        for(int index = UpGameKeyEventHandler.instanceArray.length - 1; index >= 0; index--)
        {
            UpGameKeyEventHandler.instanceArray[index].removeListenerSingleThreaded(eventListenerInterface);
        }
    }
    
    @Override
    @JsMethod
    public void removeListener(EventListenerInterface eventListenerInterface)
    {
        super.removeListener(eventListenerInterface);

        for(int index = UpGameKeyEventHandler.instanceArray.length - 1; index >= 0; index--)
        {
            UpGameKeyEventHandler.instanceArray[index].removeListener(eventListenerInterface);
        }
    }
}
