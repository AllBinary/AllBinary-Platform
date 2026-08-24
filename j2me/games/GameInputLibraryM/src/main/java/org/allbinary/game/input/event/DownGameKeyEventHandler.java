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

    @JsMethod
    public static DownGameKeyEventHandler getInstance()
    {
        return DownGameKeyEventHandler.instance;
    }
    
    private final LocalPlayerInputIdFactory playerInputIdFactory = LocalPlayerInputIdFactory.getInstance();
    
   @JsMethod
   public DownGameKeyEventHandlerBase getInstanceForDevice(int deviceId)
   {
       final int playerInputId = this.playerInputIdFactory.getPlayerForDevice(deviceId);
       return DownGameKeyEventHandler.instanceArray[playerInputId];
   }
   
   @JsMethod
   public DownGameKeyEventHandlerBase getInstanceForPlayer(int playerInputId)
   {
       return DownGameKeyEventHandler.instanceArray[playerInputId];
   }
   
    @JsConstructor
    private DownGameKeyEventHandler()
    {
    }

    @Override
    @JsMethod
    public void removeAllListeners()
    {
        super.removeAllListeners();

        for(int index = DownGameKeyEventHandler.instanceArray.length - 1; index >= 0; index--)
        {
            DownGameKeyEventHandler.instanceArray[index].removeAllListeners();
        }
    }

    @Override
    @JsMethod
    public void removeListenerSingleThreaded(EventListenerInterface eventListenerInterface)
    {
        super.removeListenerSingleThreaded(eventListenerInterface);

        for(int index = DownGameKeyEventHandler.instanceArray.length - 1; index >= 0; index--)
        {
            DownGameKeyEventHandler.instanceArray[index].removeListenerSingleThreaded(eventListenerInterface);
        }
    }
    
    @Override
    @JsMethod
    public synchronized void removeListener(EventListenerInterface eventListenerInterface)
    {
        super.removeListener(eventListenerInterface);

        for(int index = DownGameKeyEventHandler.instanceArray.length - 1; index >= 0; index--)
        {
            DownGameKeyEventHandler.instanceArray[index].removeListener(eventListenerInterface);
        }
    }
}
