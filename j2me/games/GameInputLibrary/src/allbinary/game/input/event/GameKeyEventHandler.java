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
package allbinary.game.input.event;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.basic.util.event.EventListenerInterface;

public class GameKeyEventHandler
{
    private static final GameKeyEventHandler instance = new GameKeyEventHandler();

    public static GameKeyEventHandler getInstance()
    {
        return instance;
    }

    public synchronized void removeAllListeners()
    {
        PressGameKeyEventHandler.getInstance().removeAllListeners();
        UpGameKeyEventHandler.getInstance().removeAllListeners();
        DownGameKeyEventHandler.getInstance().removeAllListeners();
    }

    /*
    public synchronized void addListeners(BasicArrayList vector)
    {
        PressGameKeyEventHandler.getInstance().addListeners(vector);
        UpGameKeyEventHandler.getInstance().addListeners(vector);
        DownGameKeyEventHandler.getInstance().addListeners(vector);
    }
    */

    //synchronized 
    public void addListener(
            EventListenerInterface eventListenerInterface)
    {
        LogUtil.put(LogFactory.getInstance(eventListenerInterface.toString(), this, "addListener"));
        //ForcedLogUtil.log(eventListenerInterface.toString(), this);
        
        PressGameKeyEventHandler.getInstance().addListenerSingleThreaded(eventListenerInterface);
        UpGameKeyEventHandler.getInstance().addListenerSingleThreaded(eventListenerInterface);
        DownGameKeyEventHandler.getInstance().addListenerSingleThreaded(eventListenerInterface);
    }

    //synchronized 
    public void removeListener(
            EventListenerInterface eventListenerInterface)
    {
        LogUtil.put(LogFactory.getInstance(eventListenerInterface.toString(), this, "removeListener"));
        //ForcedLogUtil.log(eventListenerInterface.toString(), this);
        
        PressGameKeyEventHandler.getInstance().removeListenerSingleThreaded(eventListenerInterface);
        UpGameKeyEventHandler.getInstance().removeListenerSingleThreaded(eventListenerInterface);
        DownGameKeyEventHandler.getInstance().removeListenerSingleThreaded(eventListenerInterface);
        
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END, this, "removeListener"));
    }
}
