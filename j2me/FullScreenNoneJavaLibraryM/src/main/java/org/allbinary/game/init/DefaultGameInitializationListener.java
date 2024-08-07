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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.event.GameInitializedEvent;
import org.allbinary.game.configuration.event.GameInitializedEventHandler;
import org.allbinary.game.configuration.event.GameInitializedListenerInterface;
import org.allbinary.game.resource.FeatureResourceInitializationUtil;
import org.allbinary.graphics.threed.SWTJOGLProcessor;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class DefaultGameInitializationListener
    implements GameInitializedListenerInterface
{

    private final SWTJOGLProcessor swtJOGLProcessor = SWTJOGLProcessor.getInstance();

    public DefaultGameInitializationListener()
    {
        GameInitializedEventHandler gameInitializedEventHandler =
            GameInitializedEventHandler.getInstance();

        gameInitializedEventHandler.removeAllListeners();

        gameInitializedEventHandler.addListener(
            (GameInitializedListenerInterface) this);
    }

    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }
    
    public void onGameInitialized(final GameInitializedEvent gameInitializedEvent)
    {
        final String ON_GAME_INITIALIZED = "onGameInitialized";
        final CommonStrings commonStrings = CommonStrings.getInstance();
        try
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.START, this, ON_GAME_INITIALIZED));

            while(!swtJOGLProcessor.glHolder.isCreated) {
                LogUtil.put(LogFactory.getInstance(commonStrings.UPDATE, this, ON_GAME_INITIALIZED));
                Thread.sleep(20);
            }
            
            FeatureResourceInitializationUtil.getInstance().init(gameInitializedEvent.getLevel());
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, ON_GAME_INITIALIZED, e));
        }
    }
}
