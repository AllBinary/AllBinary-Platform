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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.string.CommonStrings;

public class GameKeyEventHandler
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final GameKeyEventHandler instance = new GameKeyEventHandler();

    public static GameKeyEventHandler getInstance()
    {
        return instance;
    }
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
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
        logUtil.put(eventListenerInterface.toString(), this, commonStrings.ADD_LISTENER);
        //ForcedLogUtil.log(eventListenerInterface.toString(), this);
        
        this.pressGameKeyEventHandler.addListenerSingleThreaded(eventListenerInterface);
        
        this.upGameKeyEventHandler.getInstanceForPlayer(playerInputId).addListenerSingleThreaded(eventListenerInterface);
        this.downGameKeyEventHandler.getInstanceForPlayer(playerInputId).addListenerSingleThreaded(eventListenerInterface);
    }

    public void addListener(
            EventListenerInterface eventListenerInterface)
    {
        logUtil.put(eventListenerInterface.toString(), this, commonStrings.ADD_LISTENER);
        //ForcedLogUtil.log(eventListenerInterface.toString(), this);
        
        this.pressGameKeyEventHandler.addListenerSingleThreaded(eventListenerInterface);
        this.upGameKeyEventHandler.addListenerSingleThreaded(eventListenerInterface);
        this.downGameKeyEventHandler.addListenerSingleThreaded(eventListenerInterface);
    }
    
    //synchronized 
    public void removeListener(
            EventListenerInterface eventListenerInterface)
    {
        logUtil.put(eventListenerInterface.toString(), this, commonStrings.REMOVE_LISTENER);
        //ForcedLogUtil.log(eventListenerInterface.toString(), this);
        
        this.pressGameKeyEventHandler.removeListenerSingleThreaded(eventListenerInterface);
        this.upGameKeyEventHandler.removeListenerSingleThreaded(eventListenerInterface);
        this.downGameKeyEventHandler.removeListenerSingleThreaded(eventListenerInterface);
        
        //logUtil.put(commonStrings.END, this, commonStrings.REMOVE_LISTENER);
    }
}
