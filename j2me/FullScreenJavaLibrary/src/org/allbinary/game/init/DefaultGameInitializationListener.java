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
package org.allbinary.game.init;

import abcs.logic.communication.log.ForcedLogUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.configuration.event.GameInitializedEvent;
import allbinary.game.configuration.event.GameInitializedEventHandler;
import allbinary.game.configuration.event.GameInitializedListenerInterface;
import allbinary.game.resource.FeatureResourceInitializationUtil;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class DefaultGameInitializationListener
    implements GameInitializedListenerInterface
{

    public DefaultGameInitializationListener()
    {
        GameInitializedEventHandler gameInitializedEventHandler =
            GameInitializedEventHandler.getInstance();

        gameInitializedEventHandler.removeAllListeners();

        gameInitializedEventHandler.addListener(
            (GameInitializedListenerInterface) this);
    }

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(
                BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }

    public void onGameInitialized(GameInitializedEvent gameInitializedEvent)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(
                    "Start", this, "onGameInitialized"));

            FeatureResourceInitializationUtil.getInstance().init(
                    gameInitializedEvent.getLevel());
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                    "Exception", this, "onGameInitialized", e));
        }
    }
}
