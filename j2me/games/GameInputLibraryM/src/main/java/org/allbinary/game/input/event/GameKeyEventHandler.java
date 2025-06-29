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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.EventListenerInterface;

public class GameKeyEventHandler
{
    private static final GameKeyEventHandler instance = new GameKeyEventHandler();

    public static GameKeyEventHandler getInstance()
    {
        return instance;
    }
    
    private final String ADD_LISTENER_METHOD_NAME = "addListener";
    private final String REMOVE_LISTENER_METHOD_NAME = "removeListener";

    private final PressGameKeyEventHandler pressGameKeyEventHandler = 
            PressGameKeyEventHandler.getInstance();
    private final UpGameKeyEventHandler upGameKeyEventHandler = 
            UpGameKeyEventHandler.getInstance();
    private final DownGameKeyEventHandler downGameKeyEventHandler = 
            DownGameKeyEventHandler.getInstance();
    public synchronized void removeAllListeners()
    {
        this.pressGameKeyEventHandler.removeAllListeners();
        this.upGameKeyEventHandler.removeAllListeners();
        this.downGameKeyEventHandler.removeAllListeners();
    }

    /*
    public synchronized void addListeners(BasicArrayList vector)
    {
        this.pressGameKeyEventHandler.addListeners(vector);
        this.upGameKeyEventHandler.addListeners(vector);
        this.downGameKeyEventHandler.addListeners(vector);
    }
    */

    //synchronized 
    public void addListener(
            EventListenerInterface eventListenerInterface, int playerInputId)
    {
        LogUtil.put(LogFactory.getInstance(eventListenerInterface.toString(), this, ADD_LISTENER_METHOD_NAME));
        //ForcedLogUtil.log(eventListenerInterface.toString(), this);
        
        this.pressGameKeyEventHandler.addListenerSingleThreaded(eventListenerInterface);
        
        this.upGameKeyEventHandler.getInstanceForPlayer(playerInputId).addListenerSingleThreaded(eventListenerInterface);
        this.downGameKeyEventHandler.getInstanceForPlayer(playerInputId).addListenerSingleThreaded(eventListenerInterface);
    }

    public void addListener(
            EventListenerInterface eventListenerInterface)
    {
        LogUtil.put(LogFactory.getInstance(eventListenerInterface.toString(), this, ADD_LISTENER_METHOD_NAME));
        //ForcedLogUtil.log(eventListenerInterface.toString(), this);
        
        this.pressGameKeyEventHandler.addListenerSingleThreaded(eventListenerInterface);
        this.upGameKeyEventHandler.addListenerSingleThreaded(eventListenerInterface);
        this.downGameKeyEventHandler.addListenerSingleThreaded(eventListenerInterface);
    }
    
    //synchronized 
    public void removeListener(
            EventListenerInterface eventListenerInterface)
    {
        LogUtil.put(LogFactory.getInstance(eventListenerInterface.toString(), this, REMOVE_LISTENER_METHOD_NAME));
        //ForcedLogUtil.log(eventListenerInterface.toString(), this);
        
        this.pressGameKeyEventHandler.removeListenerSingleThreaded(eventListenerInterface);
        this.upGameKeyEventHandler.removeListenerSingleThreaded(eventListenerInterface);
        this.downGameKeyEventHandler.removeListenerSingleThreaded(eventListenerInterface);
        
        //LogUtil.put(LogFactory.getInstance(commonStrings.END, this, "removeListener"));
    }
}
