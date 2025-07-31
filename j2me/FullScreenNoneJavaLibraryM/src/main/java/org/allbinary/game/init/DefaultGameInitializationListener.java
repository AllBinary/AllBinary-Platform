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

import org.allbinary.game.configuration.event.GameInitializedEvent;
import org.allbinary.game.configuration.event.GameInitializedEventHandler;
import org.allbinary.game.configuration.event.GameInitializedListenerInterface;
import org.allbinary.game.resource.FeatureResourceInitializationUtil;
import org.allbinary.graphics.threed.SWTJOGLProcessor;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonStrings;

public class DefaultGameInitializationListener
    implements GameInitializedListenerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final SWTJOGLProcessor swtJOGLProcessor = SWTJOGLProcessor.getInstance();

    public DefaultGameInitializationListener()
    {
        GameInitializedEventHandler gameInitializedEventHandler =
            GameInitializedEventHandler.getInstance();

        gameInitializedEventHandler.removeAllListeners();

        gameInitializedEventHandler.addListener(
            (GameInitializedListenerInterface) this);
    }

    @Override
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }
    
    boolean firstTime = true;
    
    @Override
    public void onGameInitialized(final GameInitializedEvent gameInitializedEvent)
    {
        final String ON_GAME_INITIALIZED = "onGameInitialized";
        final CommonStrings commonStrings = CommonStrings.getInstance();
        try
        {
            logUtil.put(commonStrings.START, this, ON_GAME_INITIALIZED);

            while(!swtJOGLProcessor.glHolder.isCreated) {
                logUtil.put(commonStrings.UPDATE, this, ON_GAME_INITIALIZED);
                Thread.sleep(20);
            }
            
            FeatureResourceInitializationUtil.getInstance().init(gameInitializedEvent.getLevel());
            
            if(firstTime) {
                firstTime = false;
            } else {
                swtJOGLProcessor.onSurfaceChanged();
            }
            
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, ON_GAME_INITIALIZED, e);
        }
    }
}
